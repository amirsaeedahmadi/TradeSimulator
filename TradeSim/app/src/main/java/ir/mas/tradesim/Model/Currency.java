package ir.mas.tradesim.Model;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ir.mas.tradesim.Exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.Exceptions.NotEnoughValueException;
import ir.mas.tradesim.R;

public class Currency {
    static ArrayList<Currency> currencies = new ArrayList<Currency>();

    String name;//e.g. Monero
    String code;//e.g. XMR
    double price;//e.g. 1000000 (IRR) //=-1 if not accessible to update the value
    double credit;//e.g. 0.01
    int logo;//Id of the Logo e.g. R.id.monero
    int pngLogo;

    private Currency(String name, String code, int logo, int pngLogo)
            throws DuplicateNameException, DuplicateCodeException {
        this.name = name;
        this.code = code;
        this.logo = logo;
        this.pngLogo = pngLogo;
        this.price = -1;
        this.credit = 0;

        if (getCurrencyByName(name) != null) {
            throw new DuplicateNameException(name);
        }
        if (getCurrencyByCode(code) != null) {
            throw new DuplicateCodeException(code);
        }

        currencies.add(this);

    }

    @Override
    public String toString() {
        return name+"("+code+")";
    }

    public boolean update() {
        //TODO: to refresh the price and info
        return false;//shows if it was successful or not
    }

    public void increaseCredit(Double amount){
        this.credit += amount;
    }

    public void decreaseCredit(Double amount) throws NotEnoughValueException {
        if (amount > credit) {
            throw new NotEnoughValueException();
        } else credit -= amount;
    }

    static class DuplicateNameException extends Exception {
        public DuplicateNameException(String name) {
            super("There already exists a Currency with name \""+name+"\"");
        }
    }

    static class DuplicateCodeException extends Exception {
        public DuplicateCodeException(String code) {
            super("There already exists a Currency with code \""+code+"\"");
        }

    }
    public static Currency getCurrencyByName(String name) {
        for (Currency currency :
                currencies) {
            if (currency.name.equals(name)) {
                return currency;
            }
        }
        return null;
    }

    public static Currency getCurrencyByCode(String code) {
        for (Currency currency :
                currencies) {
            if (currency.code.equals(code)) {
                return currency;
            }
        }
        return null;
    }

    public static ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    /*TODO: to modify it completely, now it is just for simulation and should be modified
      TODO ... to get the data from the server
    */
    public static void refresh() {
        currencies.get(0).setPrice(4_017.492_2);// 10000IRR
        currencies.get(1).setPrice(634_425.882_3);
        currencies.get(2).setPrice(34_102.643_3);
        currencies.get(3).setPrice(1_540);
        currencies.get(4).setPrice(1.962_4);

        currencies.get(0).setCredit(0.5);
        currencies.get(1).setCredit(0.000_01);
        currencies.get(2).setCredit(0.001);
        currencies.get(3).setCredit(10);
    }

    /**
     * <p>This method initializes Currency.currencies.
     * Creating an instance of Currency is only possible by this method.
     * To add more currencies, this method should be modified.
     * To Refactor it can read the data from a file, or from the server, but now it is static
     * </p>
     * @author Mahdi Teymoori Anar*/
    public static void initialize() {
        try {
            currencies = new ArrayList<Currency>();
            new Currency("Monero", "XMR", R.drawable.ic_monero_xmr_logo, R.drawable.monero_xmr_png);
            new Currency("Bitcoin", "BTC", R.drawable.ic_bitcoin_btc_logo, R.drawable.bitcoin_btc_png);
            new Currency("Ethereum", "ETH", R.drawable.ic_ethereum_eth_logo, R.drawable.ethereum_eth_png);
            new Currency("Litecoin", "LTC", R.drawable.ic_litecoin_ltc_logo, R.drawable.litecoin_ltc_png);
            new Currency("Dogecoin", "DOGE", R.drawable.ic_dogecoin_doge_logo, R.drawable.dogecoin_doge_png);
            new Currency("Decentraland Mana", "MANA", R.drawable.ic_decentraland_mana_logo, R.drawable.decentraland_mana_png);
            new Currency("Shiba Inu", "SHIB", R.drawable.ic_shiba_inu_shib_logo, R.drawable.shiba_inu_shib_png);
            new Currency("The Sandbox", "SAND", R.drawable.ic_the_sandbox_sand_logo, R.drawable.the_sandbox_sand_png);
            new Currency("Bitcoin Cash", "BCH", R.drawable.ic_bitcoin_cash_bch_logo, R.drawable.bitcoin_cash_bch_png);
            new Currency("Ethereum Classic", "ETC", R.drawable.ic_ethereum_classic_etc_logo, R.drawable.ethereum_classic_etc_png);
            new Currency("Solana", "SOL", R.drawable.ic_solana_sol_logo, R.drawable.solana_sol_png);
            new Currency("Filecoin", "FIL", R.drawable.ic_filecoin_fil_logo, R.drawable.filecoin_fil_png);
            new Currency("Polygon", "MATIC", R.drawable.ic_polygon_matic_logo, R.drawable.polygon_matic_png);
            new Currency("Wrapped Bitcoin", "WBTC", R.drawable.ic_wrapped_bitcoin_wbtc_logo, R.drawable.wrapped_bitcoin_wbtc_png);
            new Currency("Floki Inu", "FLOKI", R.drawable.ic_floki_inu_floki_logo, R.drawable.floki_inu_floki_png);
            new Currency("HTMLcoin", "HTML", R.drawable.ic_html_coin_html_logo, R.drawable.htmlcoin_html_png);
            new Currency("Bitcoin Private", "BTCP", R.drawable.ic_bitcoin_private_btcp_logo, R.drawable.bitcoin_private_btcp_png);
            new Currency("Zilliqa", "ZIL", R.drawable.ic_zilliqa_zil_logo, R.drawable.zilliqa_zil_png);
        } catch (DuplicateNameException e) {
            e.printStackTrace();
        } catch (DuplicateCodeException e) {
            e.printStackTrace();
        }
        refresh();
    }

    /**
     * @author Mahdi Teymoori Anar
     * @return total Rial credit, sum of all Rial values
     * @implNote It does not count the Rial credit which is not on any other currency, just currencies' equivalent Rial
     * @throws NotAbleToUpdateException if unable to calculate the equivalent Rial of even one currency
     * */
    public static double getTotalRial() throws NotAbleToUpdateException {
        double total = 0;
        for (Currency currency :
                currencies) {
            total += currency.getRialEquivalent();
        }
        return total;
    }







    /**
     * @author Mahdi Teymoori Anar
     * @return the Rial equivalent value of the user's credit which equals to credit * price
     * @throws NotAbleToUpdateException if not able to update the price which means price = -1
     * */
    public double getRialEquivalent() throws NotAbleToUpdateException {
        if (price != -1) {
            return this.credit * this.price;
        }
        else if (this.credit == 0) {return 0;} else throw new NotAbleToUpdateException();
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0 || price == -1) {
            this.price = price;
        } else throw new IllegalArgumentException(
                "the value "+price+" for the price of a currency is illegal");
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getPngLogo() {
        return pngLogo;
    }

    public void setPngLogo(int pngLogo) {
        this.pngLogo = pngLogo;
    }

    /**
     * This method calculates and returns the price to but from the price to sell (price) and the exchange fee
     * @return double: the price to buy
     * @author Mahdi Teymoori Anar*/
    public double getPriceToBuy() {
        return this.price*1.02;
    }
}