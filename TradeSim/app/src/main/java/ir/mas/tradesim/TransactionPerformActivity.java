package ir.mas.tradesim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import ir.mas.tradesim.Model.TransactionType;

public class TransactionPerformActivity extends AppCompatActivity {
    TransactionType transactionType;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_perform);
    }
}