package ir.mas.tradesim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ir.mas.tradesim.Model.Currency;
import ir.mas.tradesim.Model.TransactionType;

public class TransactionPerformActivity extends AppCompatActivity {
    TransactionType transactionType;
    Currency currency;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_perform);
        //getting the data from the intent
        intent = getIntent();
        transactionType = (TransactionType) intent.getSerializableExtra("type");
        currency = Currency.getCurrencyByCode(intent.getStringExtra("code"));
        //
    }
}