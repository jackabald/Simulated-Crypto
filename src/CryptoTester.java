import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of the Coin, Wallet, and Market class.
 * @author Jack Archibald
 */
public class CryptoTester {
    /**
     * Tests the Coin class
     * @return true for correct implementations, false if otherwise
     */
    public static boolean testCoin(){
        Coin BTC = new Coin("Bitcoin", 30234.23);
        // test getName()
        if(!BTC.getCoinName().equals("Bitcoin")){
            System.out.println("getCoinName() returned wrong name");
            return false;
        }
        // test getUSDPerCoin()
        if(Math.abs(BTC.getUSDPerCoin() - 30234.23) > 0.0001){
            System.out.println("getUSDPerCoin() returned wrong coin price");
            return false;
        }
        return true;
    }

    /**
     * Tests the Wallet class
     * @return true for correct implementations, false if otherwise
     */
    public static boolean testWallet(){
        ArrayList<Coin> coins = new ArrayList<Coin>();
        Coin BTC = new Coin("Bitcoin",30234.23);
        Coin ETH = new Coin("Ethereum", 2300.66);
        coins.add(BTC);
        coins.add(ETH);
        ArrayList<Double> amountOwned = new ArrayList<Double>();
        amountOwned.add(0.0051);
        // Test wallet constructor
        try{
            Wallet wallet = new Wallet(amountOwned,coins);
            System.out.println("Wallet with different ArrayList lengths should throw exception");
            return false;
        }catch(IllegalArgumentException e){
        }
        amountOwned.add(0.03);
        try{
            Wallet wallet = new Wallet(amountOwned,coins);
        }catch(Exception e){
            System.out.println("Wallet should not throw an exception with same length ArrayLists");
            return false;
        }
        Wallet wallet = new Wallet(amountOwned,coins);
        // Test searchCoin()
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
        // Test addNumCoins()
        try{
            wallet.addNumCoins(LTC,1);
            if(wallet.coinAmountOwnedUSD(LTC) - 68.45 > 0.0001){
                System.out.println("addNumCoins() did not add the expected amount of coins to the wallet");
                return false;
            }
        }catch(Exception e){
            System.out.println("addNumCoins() threw an unexpected exception");
            return false;
        }
        // Test removeNumCoins()
        try{
            wallet.removeNumCoins(BTC,0.0025);
            if(wallet.coinAmountOwnedUSD(BTC) - (BTC.getUSDPerCoin()*0.0026) > 0.0001){
                System.out.println("removeNumCoins() did not remove the correct value of coins");
                return false;
            }
        }catch(Exception e){
            System.out.println("removeNumCoins() threw an unexpected exception");
            return false;
        }
        try{
            wallet.removeNumCoins(BTC,0.07);
            System.out.println("removeNumCoins() failed to throw an exception when removing more coins than in wallet");
            return false;
        }catch(IllegalArgumentException e){
        }
        // test totalWalletWorth()
        double walletWorth = (BTC.getUSDPerCoin() * 0.0051) + (ETH.getUSDPerCoin() * 0.03);
        if(wallet.totalWalletWorth() - walletWorth > 0.0001){
            System.out.println("totalWalletWorth() did not sum the wallet to the correct total value");
            return false;
        }
        return true;
    }

    /**
     * Tests the Market Class
     * @return true for correct implementations, false if otherwise
     */
    public static boolean testMarket(){
        // establishing market
        Coin BTC = new Coin("Bitcoin",30234.23);
        Coin ETH = new Coin("Ethereum", 2300.66);
        Coin LTC = new Coin("Litecoin", 68.45);
        ArrayList<Coin> coins1 = new ArrayList<Coin>();
        ArrayList<Double> amountOwned1 = new ArrayList<Double>();
        coins1.add(BTC);
        coins1.add(ETH);
        amountOwned1.add(0.004);
        amountOwned1.add(0.04);
        Wallet wallet1 = new Wallet(amountOwned1,coins1);
        ArrayList<Coin> coins2 = new ArrayList<Coin>();
        ArrayList<Double> amountOwned2 = new ArrayList<Double>();
        coins2.add(BTC);
        coins2.add(LTC);
        amountOwned2.add(0.001);
        amountOwned2.add(1.5);
        Wallet wallet2 = new Wallet(amountOwned2,coins2);
        ArrayList<Wallet> wallets = new ArrayList<Wallet>();
        // Testing invalid market constructor call
        try{
            Market market = new Market(wallets);
            System.out.println("invalid Market constructor call should throw an exception");
            return false;
        }catch(IllegalArgumentException e){
        }
        wallets.add(wallet1);
        wallets.add(wallet2);
        // Valid market constructor call.
        Market market = new Market(wallets);
        // Testing transfer()
        try {
            market.transfer(0, 1, BTC, 0.002);
            if(wallet2.coinAmountOwnedUSD(BTC) - (BTC.getUSDPerCoin()*0.003) > 0.0001){
                System.out.println("transfer() failed to deliver the correct amount of coins to the receiving wallet");
                return false;
            }
            if(wallet1.coinAmountOwnedUSD(BTC) - (BTC.getUSDPerCoin() * 0.002) > 0.0001){
                System.out.println("transfer() failed to remove the correct amount of coins from the sending wallet");
                return false;
            }
        }catch(Exception e){
            System.out.println("transfer() threw an unexpected exception");
            return false;
        }
        // Testing transfer() when trying to transfer more value than stored in wallet.
        try {
            market.transfer(1, 0, BTC, 5.2);
            if(wallet2.coinAmountOwnedUSD(BTC) - (BTC.getUSDPerCoin()*0.003) > 0.0001){
                System.out.println("transfer() removed coins when trying to transfer an amount larger than in wallet");
                return false;
            }
            if(wallet1.coinAmountOwnedUSD(BTC) - (BTC.getUSDPerCoin() * 0.002) > 0.0001){
                System.out.println("transfer() added coins when trying to transfer an amount larger than in wallet");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception should be caught and handled by transfer()");
            return false;
        }
        // Testing transfer() with a coin not found in the receiving wallet
        try{
            market.transfer(1,0,LTC,1);
            if(wallet2.coinAmountOwnedUSD(LTC) - (LTC.getUSDPerCoin()*0.5) > 0.0001){
                System.out.println("transfer() did not remove the correct amount when sending a coin not found in the receivers wallet");
                return false;
            }
            if(wallet1.coinAmountOwnedUSD(LTC) - (LTC.getUSDPerCoin()*1) > 0.0001){
                System.out.println("transfer() did not correctly send a coin to a wallet that currently is not found in that wallet");
                return false;
            }
        }catch(Exception e){
            System.out.println("No exception should be thrown when transfering a coin not found in the receiving walllet");
            return false;
        }
        return true;
    }

    /**
     * returns the outcome of all tests
     * @return true for correct implementations, false if otherwise
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
