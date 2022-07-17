package ir.mas.tradesim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import ir.mas.tradesim.Exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.Model.Adad;
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
        typeImageView = findViewById(R.id.transactionTypeImageView);
        currencyImageView = findViewById(R.id.currencyLogoView);
        currencyView = findViewById(R.id.CurrencyEditTextNumber);
        rialView = findViewById(R.id.RialEditTextNumber);
        performButton = findViewById(R.id.performButton);
        cancelButton = findViewById(R.id.cancelButton);
        // setting the element
        codeView.setText(currency.getCode());
        if (transactionType == TransactionType.BUY) {
            typeTextView.setText(R.string.buy);
            typeImageView.setImageResource(R.drawable.ic_down);
        } else {
            typeTextView.setText(R.string.sell);
            typeImageView.setImageResource(R.drawable.ic_up);
        }
        currencyImageView.setImageResource(currency.getLogo());
        // edit texts
//        currencyView.setOnClickListener(new );
        currencyView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (currencyView.getText().toString().equals("")) {
                        rialView.setText("0");
                }
                else {
                    try {
                        rialView.setText(Adad.parse(
                                currency.getPrice()*Double.parseDouble(currencyView.getText().toString())));
                    } catch (NotAbleToUpdateException e) {
                        e.printStackTrace();
                    }
//                    currencyView.setText(Adad.parse(currencyView.));
                }
                return false;
            }
        });
        rialView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (rialView.getText().toString().equals(""))
                    currencyView.setText("0");
                else {
                    try {
                        currencyView.setText(Adad.parse(
                                Double.parseDouble(rialView.getText().toString())/currency.getPrice()
                        ));
                    } catch (NotAbleToUpdateException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }
}