/**
 * Coin creates a currency to add to a wallet
 * @author Jack Archibald
 */
public class Coin {
    private String coinName;
    private final double USDPerCoin;

    /**
     * Coin constructor
     * @param coinName Name of the coin
     * @param USDPerCoin USD value of 1 coin
     */
    public Coin(String coinName, double USDPerCoin){
        this.coinName = coinName;
        this.USDPerCoin = USDPerCoin;
    }

    /**
     * getter method for coin name
     * @return coin name
     */
    public String getCoinName(){
        return coinName;
    }

    /**
     * getter method for the price of the coin
     * @return price of the coin
     */
    public double getUSDPerCoin(){
        return USDPerCoin;
    }
}
