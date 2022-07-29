package ir.mas.tradesim.model;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

import ir.mas.tradesim.MainActivity;
import ir.mas.tradesim.R;
import ir.mas.tradesim.Request;
import ir.mas.tradesim.StartActivity;
import ir.mas.tradesim.TransactionPerformActivity;
import ir.mas.tradesim.database.CurrencyConverters;
import ir.mas.tradesim.database.DateConverters;
import ir.mas.tradesim.database.TransactionTypeConverters;
import ir.mas.tradesim.database.UserDb;
import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Strings;
import ir.mas.tradesim.enums.Views;
import ir.mas.tradesim.exceptions.NotEnoughValueException;


@Entity(tableName = "transaction_table")
public class Transaction {
    private static int nextId = 1;
    private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static boolean hasInitialized = false;
    private Context context;
    @PrimaryKey
    @NonNull
    private int transactionId;
    @TypeConverters(TransactionTypeConverters.class)
    private TransactionType type;
    @TypeConverters(CurrencyConverters.class)
    private Currency currency;
    private double currencyAmount;
    private double rialAmount;
    @TypeConverters(DateConverters.class)
    private Date date;

    @Ignore
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

    /**
     * @author Mahdi Teymoori Anar
     * This method, performs a transaction.
     * @throws NotEnoughValueException if the credit is not enough to perform the transaction*/
    public void perform() throws NotEnoughValueException {

        if (type == TransactionType.SELL) {
//            try {
//                currency.decreaseCredit(this.currencyAmount);
//                User.getInstance().increaseRialCredit(this.rialAmount);
//            } catch (NotEnoughValueException e) {
//                throw e;
//            }
            new SendTransactionToServer(true).execute();

        } else {
//            try {
//                User.getInstance().decreaseRialCredit(rialAmount);
//                currency.increaseCredit(this.currencyAmount);
//            } catch (NotEnoughValueException e) {
//                throw e;
//            }
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

                Request.addData(Strings.TRANSACTION_ID.getLabel(), String.valueOf(transactionId));
                Request.addData(Strings.CURRENCY_CODE.getLabel(), currency.getCode());
                Request.addData(Strings.CURRENCY_AMOUNT.getLabel(), String.valueOf(currencyAmount));
                Request.addData(Strings.RIAL_AMOUNT.getLabel(), String.valueOf(rialAmount));
                Request.sendToServer();

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

                    Toast.makeText(TransactionPerformActivity.context, R.string.transaction_perform_successful, Toast.LENGTH_SHORT).show();


//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            StartActivity.userDao.deleteUsers();
//                            StartActivity.userDao.insert(new UserDb(User.getInstance().getAuthToken(),
//                                    User.getInstance().getNickname(), User.getInstance().getRialCredit(),
//                                    User.getInstance().getRialEquivalent()));
//                        }
//                    }).start();

                } else {
                    Toast.makeText(TransactionPerformActivity.context, R.string.invalid, Toast.LENGTH_LONG).show();
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
