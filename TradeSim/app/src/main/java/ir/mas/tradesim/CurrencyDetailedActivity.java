package ir.mas.tradesim;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import ir.mas.tradesim.Model.Currency;
import ir.mas.tradesim.databinding.ActivityCurrencyDetailedBinding;

public class CurrencyDetailedActivity extends AppCompatActivity {

    private ActivityCurrencyDetailedBinding binding;
    Intent intent;
    Currency currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCurrencyDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
        currency = Currency.getCurrencyByCode(intent.getStringExtra("currency code"));

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        toolBarLayout.setTitle(currency.toString());
        toolbar.setLogo(currency.getPngLogo());
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