package ir.mas.tradesim.model;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ir.mas.tradesim.R;
import ir.mas.tradesim.exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.exceptions.NotEnoughValueException;
import ir.mas.tradesim.view.Request;
import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Views;

public class Currency {

    public static ArrayList<Currency> currencies = new ArrayList<Currency>();

    static double rialExchange = 27760.1;
    // TODO: GET LIVE DOLLAR PRICE FROM IRANIAN SITES
    String name;
    String code;
    String color;
    int rank;
    double change;
    String sparkData;
    double price;//e.g. 1000000 (IRR) //=-1 if not accessible to update the value
    double credit;//e.g. 0.01
    public String logo;//Id of the Logo e.g. R.id.monero

    static public boolean updateChecker;

    public Currency(String name, String code, String color, String logo, int rank, double change, String sparkData, double price)
            throws DuplicateNameException, DuplicateCodeException {
        this.name = name;
        this.price = price;
        this.code = code;
        this.logo = logo;
        this.rank = rank;
        this.change = change;
        this.sparkData = sparkData;
        this.credit = 0;
        this.color = color;

        if (getCurrencyByName(name) != null) {
            throw new DuplicateNameException(name);
        }
        if (getCurrencyByCode(code) != null) {
            throw new DuplicateCodeException(code);
        }

        currencies.add(this);

    }

    public static float[] getSparkDataByCurrency(String code){
        Currency currency = getCurrencyByCode(code);
        Type type = new TypeToken<float[]>() {}.getType();
        return new Gson().fromJson(currency.sparkData, type);
    }

    @Override
    public String toString() {
        return name+"("+code+")";
    }

//    public boolean update() {
//        //refreshes the price and info
//        if (updatePrice())
//            return true;
//        else if (updateCredit())
//            return true;
//        return false;
//
//    }

//    private boolean updateCredit() {
//
//        updateChecker = false;
//        new UpdateCreditForUser().execute();
//        return updateChecker;
//    }

//    class UpdateCreditForUser extends AsyncTask<Void, Void, Boolean> {
//
//        @Override
//        protected Boolean doInBackground(Void... voids) {
//
//            try {
//                Request.setCommandTag(CommandTags.GET_CREDIT);
//                Request.setCurrentMenu(Views.MAIN_VIEW);
//                Request.addData(Strings.CURRENCY_CODE.getLabel(), code.toUpperCase());
//                Request.addData(Strings.PRIVATE_KEY.getLabel(), User.getInstance().getUsername());
//                Request.sendToServer();
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//                return false;
//            }
//
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean connectChecker) {
//            super.onPostExecute(connectChecker);
//            try {
//                if (Request.isSuccessful() && connectChecker) {
//                    credit = Double.parseDouble(Request.getMessage());
//                    updateChecker = true;
//
//                } else {
//                    updateChecker = false;
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }



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
        name = name.toLowerCase();
        for (Currency currency :
                currencies) {
            if (currency.name.toLowerCase().equals(name)) {
                return currency;
            }
        }
        return null;
    }

    public static Currency getCurrencyByCode(String code) {
        code = code.toUpperCase();
        for (Currency currency :
                currencies) {
            if (currency.code.toUpperCase().equals(code)) {
                return currency;
            }
        }
        return null;
    }

    public static ArrayList<Currency> getCurrencies() {
        return currencies;
    }


    public static boolean refresh() {
        new UpdateCurrencies().execute();
        return updateChecker;
    }

    static class UpdateCurrencies extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                Request.setCommandTag(CommandTags.GET_CURRENCIES);
                Request.setCurrentMenu(Views.REGISTER_VIEW);
                Request.sendToServer("");

            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }


        @Override
        protected void onPostExecute(Boolean connectChecker) {
            super.onPostExecute(connectChecker);
            try {
                if (Request.response != null){
                    if (Request.isSuccessful() & connectChecker) {

                        System.out.println(Request.getMessage());

                        Currency.currencies = new Gson().fromJson(Request.getMessage(), new TypeToken<ArrayList<Currency>>() {
                        }.getType());
                        System.out.println(Currency.currencies);

                        updateChecker = true;

                    } else {
                        updateChecker = false;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                updateChecker = false;
            }

        }
    }

    /**
     * <p>This method initializes Currency.currencies.
     * Creating an instance of Currency is only possible by this method.
     * To add more currencies, this method should be modified.
     * To Refactor it can read the data from a file, or from the server, but now it is static
     * </p>
     * @author Mahdi Teymoori Anar*/
    public static void initialize() {
//        try {
//            currencies = new ArrayList<Currency>();
//            new Currency("Monero", "XMR", R.drawable.ic_monero_xmr_logo, R.drawable.monero_xmr_png);
//            new Currency("Bitcoin", "BTC", R.drawable.ic_bitcoin_btc_logo, R.drawable.bitcoin_btc_png);
//            new Currency("Ethereum", "ETH", R.drawable.ic_ethereum_eth_logo, R.drawable.ethereum_eth_png);
//            new Currency("Litecoin", "LTC", R.drawable.ic_litecoin_ltc_logo, R.drawable.litecoin_ltc_png);
//            new Currency("Dogecoin", "DOGE", R.drawable.ic_dogecoin_doge_logo, R.drawable.dogecoin_doge_png);
//            new Currency("Decentraland Mana", "MANA", R.drawable.ic_decentraland_mana_logo, R.drawable.decentraland_mana_png);
//            new Currency("Shiba Inu", "SHIB", R.drawable.ic_shiba_inu_shib_logo, R.drawable.shiba_inu_shib_png);
//            new Currency("The Sandbox", "SAND", R.drawable.ic_the_sandbox_sand_logo, R.drawable.the_sandbox_sand_png);
//            new Currency("Bitcoin Cash", "BCH", R.drawable.ic_bitcoin_cash_bch_logo, R.drawable.bitcoin_cash_bch_png);
//            new Currency("Ethereum Classic", "ETC", R.drawable.ic_ethereum_classic_etc_logo, R.drawable.ethereum_classic_etc_png);
//            new Currency("Solana", "SOL", R.drawable.ic_solana_sol_logo, R.drawable.solana_sol_png);
//            new Currency("Filecoin", "FIL", R.drawable.ic_filecoin_fil_logo, R.drawable.filecoin_fil_png);
//            new Currency("Polygon", "MATIC", R.drawable.ic_polygon_matic_logo, R.drawable.polygon_matic_png);
//            new Currency("Wrapped Bitcoin", "WBTC", R.drawable.ic_wrapped_bitcoin_wbtc_logo, R.drawable.wrapped_bitcoin_wbtc_png);
//            new Currency("Floki Inu", "FLOKI", R.drawable.ic_floki_inu_floki_logo, R.drawable.floki_inu_floki_png);
//            new Currency("HTMLcoin", "HTML", R.drawable.ic_html_coin_html_logo, R.drawable.htmlcoin_html_png);
//            new Currency("Bitcoin Private", "BTCP", R.drawable.ic_bitcoin_private_btcp_logo, R.drawable.bitcoin_private_btcp_png);
//            new Currency("Zilliqa", "ZIL", R.drawable.ic_zilliqa_zil_logo, R.drawable.zilliqa_zil_png);
//        } catch (DuplicateNameException e) {
//            e.printStackTrace();
//        } catch (DuplicateCodeException e) {
//            e.printStackTrace();
//        }
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
        return this.price;
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
        if (this.name.equals("Bitcoin")){
            return R.drawable.ic_bitcoin_btc_logo;
        }
        else if (this.name.equals("Ethereum")){
            return R.drawable.ic_ethereum_eth_logo;
        }
        else if (this.name.equals("Solana")){
            return R.drawable.ic_solana_sol_logo;
        }
        else if (this.name.equals("Dogecoin")){
            return R.drawable.ic_dogecoin_doge_logo;
        }
        else if (this.name.equals("Polygon")){
            return R.drawable.ic_polygon_matic_logo;
        }
        else if (this.name.equals("SHIBA INU")){
            return R.drawable.ic_shiba_inu_shib_logo;
        }
        else if (this.name.equals("Monero")){
            return R.drawable.ic_monero_xmr_logo;
        }
        else if (this.name.equals("Tether USD")){
            return R.drawable.tether_usd;
        }
        else if (this.name.equals("USDC")){
            return R.drawable.usdc;
        }
        else if (this.name.equals("Binance Coin")){
            return R.drawable.binance_coin;
        }
        else if (this.name.equals("Binance USD")){
            return R.drawable.binance_usd;
        }
        else if (this.name.equals("Cardano")){
            return R.drawable.cardano;
        }
        else if (this.name.equals("XRP")){
            return R.drawable.xrp;
        }
        else if (this.name.equals("HEX")){
            return R.drawable.hex;
        }
        else if (this.name.equals("Polkadot")){
            return R.drawable.polkadot;
        }
        else if (this.name.equals("Avalanche")){
            return R.drawable.avalanche;
        }
        else if (this.name.equals("Chainlink")){
            return R.drawable.chainlink;
        }
        else if (this.name.equals("TRON")){
            return R.drawable.tron;
        }
        else if (this.name.equals("Lido Staked Ether")){
            return R.drawable.staked_ether;
        }

        return R.drawable.ic_shiba_inu_shib_logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

//    public int getPngLogo() {
//        return pngLogo;
//    }
//
//    public void setPngLogo(int pngLogo) {
//        this.pngLogo = pngLogo;
//    }

    /**
     * This method calculates and returns the price to but from the price to sell (price) and the exchange fee
     * @return double: the price to buy
     * @author Mahdi Teymoori Anar*/
    public double getPriceToBuy() {
        return this.price*1.02;
    }
}