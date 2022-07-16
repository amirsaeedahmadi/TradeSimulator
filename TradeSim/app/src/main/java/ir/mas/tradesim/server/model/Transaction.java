package ir.mas.tradesim.server.model;

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
}
