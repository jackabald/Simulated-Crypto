/**
 * Coin creates a currency to add to a wallet
 */
public class Coin {
    private String coinName;
    private final double USDPerCoin;

    /**
     *
     * @param coinName
     * @param USDPerCoin
     */
    public Coin(String coinName, double USDPerCoin){
        this.coinName = coinName;
        this.USDPerCoin = USDPerCoin;
    }

    /**
     *
     * @return
     */
    public String getCoinName(){
        return coinName;
    }

    /**
     *
     * @return
     */
    public double getUSDPerCoin(){
        return USDPerCoin;
    }
}
