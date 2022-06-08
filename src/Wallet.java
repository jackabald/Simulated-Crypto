import java.util.NoSuchElementException;
import java.util.Random;
import java.util.ArrayList;

/**
 * Wallet holds multiple types of different coins and tracks the amount owned of each coin
 * @author Jack Archibald
 */
public class Wallet {
    private ArrayList<Double> amountOwned;
    private ArrayList<Coin> coins;

    /**
     * Wallet constructor that uses parallel arrays to hold coins and amount of coins held
     * @param amountOwned ArrayList that corresponds with coins to represent the amount owned of each coin.
     * @param coins ArrayList of coins owned in the Wallet.
     * @throws IllegalArgumentException amountOwned and coins ArrayLists must be equal size.
     */
    public Wallet(ArrayList<Double> amountOwned, ArrayList<Coin> coins) throws IllegalArgumentException {
        if(amountOwned.size() != coins.size()){
            throw new IllegalArgumentException("Cannot create wallet without equal sized coins and amountOwned " +
                    "ArrayLists");
        }
        this.amountOwned = amountOwned;
        this.coins = coins;
    }

    /**
     * Linear Search method to find a coin in a wallet
     * @param coin The coin to search for
     * @return the integer index of the coin in the Arraylist
     * @throws NullPointerException Coin is null
     * @throws NoSuchElementException Coin is not found in the wallet
     */
    public int searchCoin(Coin coin) throws NullPointerException,NoSuchElementException {
        int index = -1;
        if (coin == null) {
            throw new NullPointerException("coin cannot be null");
        }
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).equals(coin)) {
                index = i;
            }
        }
        if(index == -1){
            throw new NoSuchElementException("no coin with that name found");
        }
        return index;
    }

    /**
     * Adds the number of coins to the wallet. If the coin is not found in the wallet already it will add the coin to
     * the first available index.
     * @param coin The coin to be added
     * @param amount The amount of the coin to be added
     */
    public void addNumCoins(Coin coin, double amount) {
        try {
            int index = searchCoin(coin);
            amountOwned.set(index, amountOwned.get(index) + amount);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }catch(NoSuchElementException e){
            coins.add(coin);
            amountOwned.add(amount);
        }
    }

    /**
     * Removes the number of coins from the wallet. If the coin is not found in the wallet or null the exception message
     * is displayed.
     * @param coin The coin to be removed
     * @param amount The amount of the coin to be removed
     * @throws IllegalArgumentException Wallet does not have enough balance to remove this amount
     */
    public void removeNumCoins(Coin coin, double amount) throws IllegalArgumentException{
        try {
            int index = searchCoin(coin);
            if(amountOwned.get(index) - amount < 0){
                throw new IllegalArgumentException("negative balance, not enough coins in wallet to remove");
            }
            amountOwned.set(index, amountOwned.get(index) - amount);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds the amount in USD of how much a coin is held.
     * @param coin The coin to find USD amount
     * @return The double amount of USD owned from a specific coin in the wallet.
     */
    public double coinAmountOwnedUSD(Coin coin){
        try{
            int index = searchCoin(coin);
            double coinToUSD = coins.get(index).getUSDPerCoin();
            double numCoins = amountOwned.get(index);
            return coinToUSD * numCoins;
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }catch(NoSuchElementException e){
            e.getMessage();
        }
        return -1;
    }

    /**
     * Finds the total wallet worth of all coins
     * @return Total wallet worth
     */
    public double totalWalletWorth(){
        double total = 0;
        for(int i = 0; i < coins.size(); i++){
            double coinToUSD = coins.get(i).getUSDPerCoin();
            double numCoins = amountOwned.get(i);
            total += numCoins * coinToUSD;
        }
        return total;
    }
}
