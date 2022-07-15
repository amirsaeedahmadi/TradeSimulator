package ir.mas.tradesim;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import ir.mas.tradesim.Model.Currency;
import ir.mas.tradesim.databinding.ActivityCurrencyBinding;

public class CurrencyActivity extends AppCompatActivity {

    private ActivityCurrencyBinding binding;
    Currency currency;
    Intent intent;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCurrencyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
        System.out.println(">>>"+intent.getStringExtra("currency code"));
        currency = Currency.getCurrencyByCode(intent.getStringExtra("currency code"));
//        System.out.println("currency == null : "+currency == null);
//        System.out.println("name : " +
//                ""+currency.getName());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        toolBarLayout.setTitle(currency.getName() + "(" + currency.getCode() + ")");
//        toolbar.setLogo(currency.getLogo());
//        logo = findViewById(R.id.theCurrencyLogoImageView);
//        logo.setImageResource(currency.getLogo());
//        if (currency != null) {
//            toolBarLayout.setTitle(currency.getName() + "(" + currency.getCode() + ")");
//        } else {
//            intent = getIntent();
//            System.out.println(">>>"+intent.getStringExtra("currency code"));
//            currency = Currency.getCurrencyByCode(intent.getStringExtra("currency code"));
//            toolBarLayout.setTitle(currency.getName() + "(" + currency.getCode() + ")");
//
//        }

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}