import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.ArrayList;

/**
 * Wallet holds multiple types of different coins and tracks the amount owned of each coin
 */
public class Wallet {
    private ArrayList<Double> amountOwned;
    private ArrayList<Coin> coins;

    public Wallet(ArrayList<Double> amountOwned, ArrayList<Coin> coins) throws IllegalArgumentException {
        if(amountOwned.size() != coins.size()){
            throw new IllegalArgumentException("Cannot create wallet without equal sized coins and amountOwned ArrayLists");
        }
        this.amountOwned = amountOwned;
        this.coins = coins;
    }
    public int searchCoin(String coinName) throws NullPointerException,NoSuchElementException {
        int index = -1;
        if (coinName == null) {
            throw new NullPointerException("coinName cannot be null");
        }
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getCoinName().toLowerCase().equals(coinName.toLowerCase())) {
                index = i;
            }
        }
        if(index == -1){
            throw new NoSuchElementException("no coin with that name found");
        }
        return index;
    }
    public void addNumCoins(String coinName, double amount) throws NoSuchElementException {
        try {
            int index = searchCoin(coinName);
            amountOwned.set(index, amountOwned.get(index) + amount);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
    public void removeNumCoins(String coinName, double amount) throws NoSuchElementException{
        try {
            int index = searchCoin(coinName);
            amountOwned.set(index, amountOwned.get(index) - amount);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
    public double coinAmountOwnedUSD(String coinName){
        try{
            int index = searchCoin(coinName);
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
