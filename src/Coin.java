/**
 * Coin creates a currency to add to a wallet
 */
public class Coin {
    private String coinName;
    private final double USDPerCoin;

    public Coin(String coinName, double USDPerCoin, double numCoinsHeld){
        this.coinName = coinName;
        this.USDPerCoin = USDPerCoin;
    }
    public String getCoinName(){
        return coinName;
    }
    public double getUSDPerCoin(){
        return USDPerCoin;
    }
}
