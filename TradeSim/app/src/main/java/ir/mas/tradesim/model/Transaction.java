package ir.mas.tradesim.model;

import java.util.ArrayList;
import java.util.Date;

import ir.mas.tradesim.exceptions.NotEnoughValueException;

public class Transaction {
    private static int nextId = 1;
    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static boolean hasInitialized = false;
    private int transactionId;
    private TransactionType type;
    private Currency currency;
    private double currencyAmount;
    private double rialAmount;
    private Date date;

    public Transaction(TransactionType type, Currency currency, double currencyAmount, double rialAmount, Date date) {
        this.type = type;
        this.currency = currency;
        this.currencyAmount = currencyAmount;
        this.rialAmount = rialAmount;
        this.date = date;
        this.transactionId = nextId;
        nextId ++;
        transactions.add(this);
    }

    public Transaction(TransactionType type, Currency currency, double currencyAmount, double rialAmount) {
        this.type = type;
        this.currency = currency;
        this.currencyAmount = currencyAmount;
        this.rialAmount = rialAmount;
        this.date = new Date();
        this.transactionId = nextId;
        nextId ++;
        transactions.add(this);
    }

    /**
     * @author Mahdi Teymoori Anar
     * This method, performs a transaction.
     * @throws NotEnoughValueException if the credit is not enough to perform the transaction*/
    public void perform() throws NotEnoughValueException {//TODO: send to the server
        if (type == TransactionType.SELL) {
            try {
                currency.decreaseCredit(this.currencyAmount);
                User.getInstance().increaseRialCredit(this.rialAmount);
            } catch (NotEnoughValueException e) {
                throw e;
            }
        } else {
            try {
                User.getInstance().decreaseRialCredit(rialAmount);
                currency.increaseCredit(this.currencyAmount);
            } catch (NotEnoughValueException e) {
                throw e;
            }
        }
    }


    /*
    * TODO : get from the server */
    public static void initialize() {
        if (!hasInitialized) {
//            transactions = new ArrayList<Transaction>();
            new Transaction(TransactionType.BUY, Currency.getCurrencyByCode("BTC"),
                    0.0001, Currency.getCurrencyByCode("BTC").getPrice() * 0.0001);
            new Transaction(TransactionType.BUY, Currency.getCurrencyByCode("XMR"),
                    0.1, Currency.getCurrencyByCode("XMR").getPrice() * 0.1);
            new Transaction(TransactionType.BUY, Currency.getCurrencyByCode("ETH"),
                    0.001, Currency.getCurrencyByCode("ETH").getPrice() * 0.001);
            new Transaction(TransactionType.BUY, Currency.getCurrencyByCode("DOGE"),
                    100, Currency.getCurrencyByCode("DOGE").getPrice() * 100);
            new Transaction(TransactionType.SELL, Currency.getCurrencyByCode("BTC"),
                    0.0001, Currency.getCurrencyByCode("BTC").getPrice() * 0.0001);
            new Transaction(TransactionType.SELL, Currency.getCurrencyByCode("XMR"),
                    0.001, Currency.getCurrencyByCode("XMR").getPrice() * 0.001);
            new Transaction(TransactionType.SELL, Currency.getCurrencyByCode("ETH"),
                    0.0005, Currency.getCurrencyByCode("ETH").getPrice() * 0.0005);
            new Transaction(TransactionType.SELL, Currency.getCurrencyByCode("DOGE"),
                    1, Currency.getCurrencyByCode("DOGE").getPrice() * 1);
            hasInitialized = true;
        }
    }

    //getters and setters

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static void setTransactions(ArrayList<Transaction> transactions) {
        Transaction.transactions = transactions;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public double getRialAmount() {
        return rialAmount;
    }

    public void setRialAmount(double rialAmount) {
        this.rialAmount = rialAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
