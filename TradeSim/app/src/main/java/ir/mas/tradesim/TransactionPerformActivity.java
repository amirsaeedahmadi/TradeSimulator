package ir.mas.tradesim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import ir.mas.tradesim.Model.Currency;
import ir.mas.tradesim.Model.TransactionType;

public class TransactionPerformActivity extends AppCompatActivity {
    TransactionType transactionType;
    Currency currency;
    Intent intent;
    TextView codeView, typeTextView;
    ImageView typeImageView, currencyImageView;
    EditText currencyView, rialView;
    Button performButton, cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_perform);
        //getting the data from the intent
        intent = getIntent();
        transactionType = (TransactionType) intent.getSerializableExtra("type");
        currency = Currency.getCurrencyByCode(intent.getStringExtra("code"));
        // getting the elements
        codeView = findViewById(R.id.codeTextView);
        typeTextView = findViewById(R.id.typeTextView);
        typeImageView = findViewById(R.id.typeImageView);
        currencyImageView = findViewById(R.id.currencyLogoView);
        currencyView = findViewById(R.id.CurrencyEditTextNumber);
        rialView = findViewById(R.id.RialEditTextNumber);
        performButton = findViewById(R.id.performButton);
        cancelButton = findViewById(R.id.cancelButton);
        //
    }
}