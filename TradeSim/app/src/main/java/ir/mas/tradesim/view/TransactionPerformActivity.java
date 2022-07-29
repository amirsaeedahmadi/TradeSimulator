package ir.mas.tradesim.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.mas.tradesim.R;
import ir.mas.tradesim.exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.exceptions.NotEnoughValueException;
import ir.mas.tradesim.model.Adad;
import ir.mas.tradesim.model.Currency;
import ir.mas.tradesim.model.Transaction;
import ir.mas.tradesim.model.TransactionType;

//TODO: IMPORTANT REFACTORINGS ...
public class TransactionPerformActivity extends AppCompatActivity {
    TransactionType transactionType;
    InputCurrencyOrRial inputCurrencyOrRial;
    Currency currency;
    Intent intent;
    TextView codeView, typeTextView;
    ImageView typeImageView, currencyImageView;
    EditText currencyView, rialView;
    Button performButton, cancelButton;
    Double amount;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_perform);
        //getting the data from the intent

        context = getBaseContext();

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
//        currencyImageView.setImageResource(currency.getLogo());
        // edit texts
//        currencyView.setOnClickListener(new );
        currencyView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                inputCurrencyOrRial = InputCurrencyOrRial.CURRENCY;
                if (currencyView.getText().toString().equals("")) {
                        rialView.setText("0");
                }
                else {
                    try {
                        amount = Double.parseDouble(currencyView.getText().toString());
                        rialView.setText(Adad.parse(
                                currency.getPrice()*amount));
                    } catch (NotAbleToUpdateException e) {
                        e.makeToast(getBaseContext());
                    }
//                    currencyView.setText(Adad.parse(currencyView.));
                }
                return false;
            }
        });
        rialView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                inputCurrencyOrRial = InputCurrencyOrRial.RIAL;
                if (rialView.getText().toString().equals(""))
                    currencyView.setText("0");
                else {
                    try {
                        amount = Double.parseDouble(rialView.getText().toString());
                        currencyView.setText(Adad.parse(
                                amount/currency.getPrice()
                        ));
                    } catch (NotAbleToUpdateException e) {
                        e.makeToast(getBaseContext());
                    }
                }
                return false;
            }
        });
        // cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(intent);
            }
        });

        performButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transaction transaction;
                switch (inputCurrencyOrRial) {
                    case RIAL:
                        transaction = new Transaction(transactionType, currency,
                                amount/currency.getPrice(), amount);
                        try {
                            transaction.perform();
                        } catch (NotEnoughValueException e) {
                            e.printStackTrace();
                        }
                        break;
                    case CURRENCY:
                        transaction = new Transaction(transactionType, currency,
                                amount, amount*currency.getPrice());
                        try {
                            transaction.perform();
                        } catch (NotEnoughValueException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        Toast.makeText(getBaseContext(), R.string.not_able_to_update, Toast.LENGTH_SHORT);
                }
                intent = new Intent(getBaseContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(intent);
            }
        });
    }

    enum InputCurrencyOrRial {
        CURRENCY, RIAL
    }
}