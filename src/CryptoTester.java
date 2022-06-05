import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 */
public class CryptoTester {
    /**
     *
     * @return
     */
    public static boolean testCoin(){
        Coin BTC = new Coin("Bitcoin", 30234.23);
        if(!BTC.getCoinName().equals("Bitcoin")){
            System.out.println("getCoinName() returned wrong name");
            return false;
        }
        if(Math.abs(BTC.getUSDPerCoin() - 30234.23) > 0.0001){
            System.out.println("getUSDPerCoin() returned wrong coin price");
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public static boolean testWallet(){
        ArrayList<Coin> coins = new ArrayList<Coin>();
        Coin BTC = new Coin("Bitcoin",30234.23);
        Coin ETH = new Coin("Ethereum", 2300.66);
        coins.add(BTC);
        coins.add(ETH);
        ArrayList<Double> amountOwned = new ArrayList<Double>();
        amountOwned.add(0.0051);
        try{
            Wallet wallet = new Wallet(amountOwned,coins);
            System.out.println("Wallet with different ArrayList lengths should throw exception");
            return false;
        }catch(IllegalArgumentException e){
        }
        amountOwned.add(0.0332);
        try{
            Wallet wallet = new Wallet(amountOwned,coins);
        }catch(Exception e){
            System.out.println("Wallet should not throw an exception with same length ArrayLists");
            return false;
        }
        Wallet wallet = new Wallet(amountOwned,coins);
        try {
            wallet.searchCoin(ETH);
        }catch(Exception e){
            System.out.println("Exception should not be thrown when searching for a coin in the wallet");
            return false;
        }
        Coin LTC = new Coin("Litecoin", 68.45);
        try{
            wallet.searchCoin(LTC);
            System.out.println("NoSuchElementException should be thrown for a coin not found in wallet");
            return false;
        }catch(NoSuchElementException e){
        }
        try{
            wallet.searchCoin(null);
            System.out.println("NullPointerException should be thrown when searching for a null coin");
            return false;
        }catch(NullPointerException e){
        }

        return true;
    }

    /**
     *
     * @return
     */
    public static boolean testMarket(){
        return true;
    }

    /**
     *
     * @return
     */
    public static boolean runAllTests(){
        return testCoin() && testWallet() && testMarket();
    }

    /**
     * Main method
     * @param args No arguments used
     */
    public static void main(String args[]){
        System.out.println(runAllTests());
    }
}
