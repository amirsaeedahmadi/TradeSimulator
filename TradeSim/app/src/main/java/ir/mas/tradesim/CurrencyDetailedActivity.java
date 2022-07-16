package ir.mas.tradesim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ir.mas.tradesim.Model.Adad;
import ir.mas.tradesim.Model.Currency;
import ir.mas.tradesim.databinding.ActivityCurrencyDetailedBinding;

public class CurrencyDetailedActivity extends AppCompatActivity {

    private ActivityCurrencyDetailedBinding binding;
    Intent intent;
    Currency currency;
    TextView priceToSellView, priceToBuyView;
    Button sellButton, buyButton;

    private void setPrices() {
        //TODO: probably it needs modification to update the prices
        priceToSellView.setText(Adad.parse(currency.getPrice(), getBaseContext()));
        priceToBuyView.setText(Adad.parse(currency.getPriceToBuy(), getBaseContext()));
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
        setPrices();

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        toolBarLayout.setTitle(currency.toString());
        toolBarLayout.setBackgroundResource(currency.getLogo());


        toolbar.setLogo(currency.getPngLogo());
        FloatingActionButton fab = binding.fab;
        fab.setImageTintList( ColorStateList.valueOf(Color.rgb(242, 104, 34))
        );


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
//                System.out.println(">>>HOVERED!!!");
//                return false;
//            }
//        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("RestrictedApi")
//            @Override
//            public void onClick(View view) {
//                System.out.println(toolbar.isInLayout());
//                System.out.println(toolbar.isTitleTruncated());
//                System.out.println(toolBarLayout.isLaidOut());
////                System.out.println(this.set);
//            }
//        });
    }
}