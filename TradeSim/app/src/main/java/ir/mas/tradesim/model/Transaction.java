package ir.mas.tradesim.model;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ir.mas.tradesim.R;
import ir.mas.tradesim.database.TransactionDb;
import ir.mas.tradesim.view.Request;
import ir.mas.tradesim.view.TransactionPerformActivity;
import ir.mas.tradesim.database.CurrencyConverters;
import ir.mas.tradesim.database.DateConverters;
import ir.mas.tradesim.database.TransactionTypeConverters;
import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Strings;
import ir.mas.tradesim.enums.Views;
import ir.mas.tradesim.exceptions.NotEnoughValueException;
import ir.mas.tradesim.view.registeration.StartActivity;


public class Transaction {
    public static int nextId = 1;
    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static boolean hasInitialized = false;
    private Context context;
    private int transactionId;
    private TransactionType type;
    private Currency currency;
    private double currencyAmount;
    private double rialAmount;
    private Date date;

    public static Transaction thisTransaction;

    public Transaction(TransactionType type, Currency currency, double currencyAmount,
                       double rialAmount, Date date) {
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


    public void perform() throws NotEnoughValueException {

        thisTransaction = this;
        if (type == TransactionType.SELL) {
            new SendTransactionToServer(true).execute();

        } else {
            new SendTransactionToServer(false).execute();
        }
    }

    class SendTransactionToServer extends AsyncTask<Void, Void, String> {

        boolean transactionTypeChecker;
        boolean checker;

        public SendTransactionToServer(boolean transactionTypeChecker) {
            super();
            this.transactionTypeChecker = transactionTypeChecker;
        }

        @Override
        protected String doInBackground(Void... voids) {

            checker = false;

            try {
                Request.setCurrentMenu(Views.TRANSACTION_VIEW);

                if (this.transactionTypeChecker) {
                    Request.setCommandTag(CommandTags.SELL);
                } else {
                    Request.setCommandTag(CommandTags.BUY);
                }

               Request.addData(Strings.TRANSACTION.getLabel(), new Gson().toJson(thisTransaction));
//                Request.addData(Strings.PRIVATE_KEY.getLabel(), User.getInstance().getAuthToken());
                Request.sendToServer("");

            } catch (Exception e) {
                String message = "Could not connect to the server!";
                System.out.println(message);
                e.printStackTrace();
                checker = false;
                return message;
            }

            String message = "Connected to the Server";
            System.out.println(message);
            checker = true;
            return message;

        }

        @Override
        protected void onPostExecute(String message) {
            super.onPostExecute(message);
            System.out.println(message);

            if (!checker) {
                Toast.makeText(TransactionPerformActivity.context, R.string.connection_error, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                if (Request.isSuccessful()) {
                    Toast.makeText(TransactionPerformActivity.context,
                            R.string.transaction_perform_successful,
                            Toast.LENGTH_SHORT).show();

                    StartActivity.transactionDao.insert(new TransactionDb(thisTransaction.transactionId, thisTransaction.type,
                            thisTransaction.currency, thisTransaction.currencyAmount,
                            thisTransaction.rialAmount));
                    StartActivity.transactionList = StartActivity.transactionDao.getAllTransactions();
                    if (this.transactionTypeChecker){

                    User.getInstance().setRialCredit(User.getInstance().getRialCredit()
                            - thisTransaction.getRialAmount());
                    }
                    else{
                        User.getInstance().setRialCredit(User.getInstance().getRialCredit()
                                + thisTransaction.getRialAmount());
                    }
                    User.getInstance().throughTime.add(User.getInstance().rialCredit+ "");

                } else {
                    Toast.makeText(TransactionPerformActivity.context, R.string.invalid, Toast.LENGTH_LONG).show();
                    Transaction.transactions.remove(thisTransaction);
                }
            } catch (JSONException e) {
                Toast.makeText(TransactionPerformActivity.context, R.string.response_error, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
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

    public static List<Transaction> getTransactions() {
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
