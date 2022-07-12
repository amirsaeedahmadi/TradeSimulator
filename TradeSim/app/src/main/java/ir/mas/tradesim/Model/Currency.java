package ir.mas.tradesim.Model;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ir.mas.tradesim.Exceptions.NotAbleToUpdateException;

public class Currency {
    static ArrayList<Currency> currencies = new ArrayList<Currency>();

    String name;//e.g. Monero
    String code;//e.g. XMR
    double price;//e.g. 1000000 (IRR) //=-1 if not accessible to update the value
    double credit;//e.g. 0.01
    int logo;//Id of the Logo e.g. R.id.monero

    public Currency(String name, String code, int logo)
            throws DuplicateNameException, DuplicateCodeException {
        this.name = name;
        this.code = code;
        this.logo = logo;
        this.price = -1;
        this.credit = 0;

        if (getCurrencyByName(name) != null) {
            throw new DuplicateNameException(name);
        }
        if (getCurrencyByCode(code) != null) {
            throw new DuplicateCodeException(code);
        }

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
            if (currency.name == name) {
                return currency;
            }
        }
        return null;
    }

    public static Currency getCurrencyByCode(String code) {
        for (Currency currency :
                currencies) {
            if (currency.code == code) {
                return currency;
            }
        }
        return null;
    }

    public static ArrayList<Currency> getCurrencies() {
        return currencies;
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
        else throw new NotAbleToUpdateException();
    }

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
}