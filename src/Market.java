import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Market holds all the wallets and allows for the transfer of currencies between them
 */
public class Market {
    private ArrayList<Wallet> wallets;
    /**
     *
     * @param wallets
     */
    public Market(ArrayList<Wallet> wallets){
        this.wallets = wallets;
    }

    /**
     *
     * @param transferFrom
     * @param transferToo
     * @param coinToTransfer
     * @param amountInCoin
     */
    public void transfer(Wallet transferFrom, Wallet transferToo, Coin coinToTransfer, double amountInCoin){
        try {
            transferFrom.removeNumCoins(coinToTransfer, amountInCoin);
            transferToo.addNumCoins(coinToTransfer, amountInCoin);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
