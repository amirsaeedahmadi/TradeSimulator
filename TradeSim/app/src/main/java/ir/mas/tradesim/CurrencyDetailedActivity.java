package ir.mas.tradesim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.robinhood.spark.SparkView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ir.mas.tradesim.exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.model.Adad;
import ir.mas.tradesim.model.Currency;
import ir.mas.tradesim.model.TransactionType;
import ir.mas.tradesim.databinding.ActivityCurrencyDetailedBinding;

public class CurrencyDetailedActivity extends AppCompatActivity {

    private ActivityCurrencyDetailedBinding binding;
    Intent intent;
    Currency currency;
    TextView priceToSellView, priceToBuyView, creditView, equivalentRialView;
    Button sellButton, buyButton;
    CollapsingToolbarLayout toolBarLayout;
    Toolbar toolbar;
    AsyncTask<CurrencyDetailedActivity, Object, Boolean> logoSetter;
    View include;
    SparkView sparkView;
    boolean isShown = false;
    int confidence = 10;

//    void perform() {
//        if (!isShown && include.getY() <= toolbar.getHeight() + confidence) {
//            toolbar.setLogo(currency.getPngLogo());
//            isShown = true;
//        }
//        if (isShown && include.getY() > toolbar.getHeight() + confidence) {
//            isShown = false;
//            toolbar.setLogo(null);
//        }
//    }

    private void setPrices() {
        //TODO: probably it needs modification to update the prices
        if (priceToSellView == null || priceToBuyView == null) {
            return;
        }
        try {
            priceToSellView.setText(Adad.parse(currency.getPrice(), getBaseContext()));
            priceToBuyView.setText(Adad.parse(currency.getPriceToBuy(), getBaseContext()));
            creditView.setText(Adad.parse(currency.getCredit(), getBaseContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            equivalentRialView.setText(Adad.parse(currency.getRialEquivalent(), getBaseContext()));
        } catch (NotAbleToUpdateException e) {
            equivalentRialView.setText(Adad.parse(-1, getBaseContext()));
        }
    }

    @Override
    protected void onDestroy() {
        endTask();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        endTask();
        super.onStop();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        endTask();
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCurrencyDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
        currency = Currency.getCurrencyByCode(intent.getStringExtra("currency code"));
        priceToSellView = findViewById(R.id.priceToSellTextView);
        priceToBuyView = findViewById(R.id.priceToBuyTextView);
        creditView = findViewById(R.id.yourCreditTextView);
        equivalentRialView = findViewById(R.id.equivalentRialTextView);
        sparkView = findViewById(R.id.sparkView);
        //sparkView.setBaseLineColor();
        sparkView.setAdapter(new CurrencySparkAdapter(Currency.getSparkDataByCurrency(
                intent.getStringExtra("currency code"))));
        setPrices();

        toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        toolBarLayout.setTitle(currency.toString());
//        toolBarLayout.setBackgroundResource(currency.getLogo());

        toolbar.setLogo(null);
        FloatingActionButton fab = binding.fab;
        fab.setImageTintList( ColorStateList.valueOf(Color.rgb(242, 104, 34))
        );

        buyButton = findViewById(R.id.buyButtonView);
        sellButton = findViewById(R.id.sellButtonView);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), TransactionPerformActivity.class);
                intent.putExtra("type", TransactionType.BUY);
                intent.putExtra("code", currency.getCode());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(intent);
            }
        });
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), TransactionPerformActivity.class);
                intent.putExtra("type", TransactionType.SELL);
                intent.putExtra("code", currency.getCode());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                endTask();
                getBaseContext().startActivity(intent);
            }
        });
        include = findViewById(R.id.includeView);


        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                ColorStateList.valueOf(Color.rgb(255, 50, 50));
                setPrices();
                Snackbar.make(view, R.string.prices_refreshed, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TextView textView = findViewById(R.id.descriptionTextView);
//        toolBarLayout.setOnHoverListener(new View.OnHoverListener() {
//            @Override
//            public boolean onHover(View view, MotionEvent motionEvent) {
//                return false;
//            }
//        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("RestrictedApi")
//            @Override
//            public void onClick(View view) {
//            }
//        });
//        while (true) {
//            perform();
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        logoSetter = new LogoSetter();
        LogoSetter.isShown = false;
        LogoSetter.toContinue = true;
        logoSetter.execute(new CurrencyDetailedActivity[] {this});
    }
    private void endTask() {
        if (logoSetter != null) {
            logoSetter.cancel(true);
            LogoSetter.toContinue = false;
        }
        LogoSetter.isShown = false;
    }

    public static void setLogoSetterIsShownOff() {
        LogoSetter.isShown = false;
        LogoSetter.toContinue = true;
    }
}

class LogoSetter extends AsyncTask<CurrencyDetailedActivity, Object, Boolean> {
    CurrencyDetailedActivity activity;
    static boolean isShown = false;
    int confidence = 10;
    static boolean toContinue = true;

    /**
     * @param currencyDetailedActivities
     * @deprecated
     */
    @Override
    protected Boolean doInBackground(CurrencyDetailedActivity... currencyDetailedActivities) {
        activity = currencyDetailedActivities[0];
//        isShown = false;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!isCancelled() && toContinue) {
            if (!isShown && activity.include.getY() <= activity.toolbar.getHeight() + confidence) {
                isShown = true;
                return true;
            }
            else if (isShown && activity.include.getY() > activity.toolbar.getHeight() + confidence) {
                isShown = false;
                return false;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean toShow) {
        if (isCancelled() || !toContinue) return;
//        if (toShow)
////            activity.toolbar.setLogo(activity.currency.getPngLogo());
//        else activity.toolbar.setLogo(null);
        if (!isCancelled() && toContinue) new LogoSetter().execute(new CurrencyDetailedActivity[] {activity});
    }
}