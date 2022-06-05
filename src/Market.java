import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Market holds all the wallets and allows for the transfer of currencies between them
 */
public class Market {
    private ArrayList<Wallet> wallets;

    public Market(ArrayList<Wallet> wallets){
        this.wallets = wallets;
    }
    public void transfer(Wallet transferFrom, Wallet transferToo, Coin coinToTransfer, double amountInCoin){
            transferFrom.removeNumCoins(coinToTransfer,amountInCoin);
            transferToo.addNumCoins(coinToTransfer,amountInCoin);
    }
}
