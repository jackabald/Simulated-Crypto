import java.util.ArrayList; 

/**
 * Market holds all the wallets and allows for the transfer of currencies between them
 * @author Jack Archibald
 */
public class Market {
    private ArrayList<Wallet> wallets;

    /**
     * Market constructor
     * @param wallets ArrayList of Wallets to be used in the market
     * @throws IllegalArgumentException wallets cannot be null or have less than 2 wallets
     */
    public Market(ArrayList<Wallet> wallets) throws IllegalArgumentException{
        if (wallets == null || wallets.size() < 2){
            throw new IllegalArgumentException("Cannot create a Market with null or only one wallet");
        }
        this.wallets = wallets;
    }

    /**
     * Method that allows for the transactions of coins between wallets.
     * @param indexOfSender index in the wallets ArrayList of the sender of the coin(must own the coin and have
     *                      sufficient funds).
     * @param indexOfReceiver index in the wallets ArrayList of the receiver of the coin.
     * @param coinToTransfer The coin that will be transferred.
     * @param amountInCoin The amount of the coin that will be transferred.
     */
    public void transfer(int indexOfSender, int indexOfReceiver, Coin coinToTransfer, double amountInCoin){
        try {
            wallets.get(indexOfSender).removeNumCoins(coinToTransfer, amountInCoin);
            wallets.get(indexOfReceiver).addNumCoins(coinToTransfer, amountInCoin);
        }catch(Exception e){
            //this will print out any exceptions thrown when trying to transfer. Uncomment to see caught exceptions.
            //System.out.println(e.getMessage());
        }
    }
}
