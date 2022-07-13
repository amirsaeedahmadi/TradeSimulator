package ir.mas.tradesim.Model;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {
    private static int nextId = 1;
    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
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
