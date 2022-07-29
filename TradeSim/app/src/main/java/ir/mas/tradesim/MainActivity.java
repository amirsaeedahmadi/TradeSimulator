package ir.mas.tradesim;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

import ir.mas.tradesim.model.Currency;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mPrefs;
    FragmentContainerView fragment;
    ImageButton settings, statistics, home, history, scoreboard;
    LinearLayout bar;
    FragmentTransaction transaction;
    SearchView searchView;
    ListView listView;
    ArrayAdapter adapter;
    public static boolean check = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CurrencyDetailedActivity.setLogoSetterIsShownOff();
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrefs = getPreferences(MODE_PRIVATE);
        String isNightModeOn = mPrefs.getString("DarkMode", "");
        System.out.println(isNightModeOn);
        if (isNightModeOn.equals("True")) {
            setTheme(R.style.Theme_TradeSimNight);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        bar = findViewById(R.id.linearLayout);
        history = findViewById(R.id.historyButton);
        history.setOnClickListener(view -> {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerView, new TransactionsFragment());
            transaction.addToBackStack(null);
            goTo(history);
            transaction.commit();
        });

        home = findViewById(R.id.homeButton);
        home.setOnClickListener(view -> {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerView, new HomeFragment());
            goTo(home);
            transaction.commit();
        });

        settings = findViewById(R.id.settingButton);
        settings.setOnClickListener(view -> {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerView, new SettingsFragment());
            transaction.addToBackStack(null);
            goTo(settings);
            transaction.commit();
        });

        scoreboard = findViewById(R.id.scoreboardImageView);
        scoreboard.setOnClickListener(view -> {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerView, new ScoreboardFragment());
            transaction.addToBackStack(null);
            goTo(scoreboard);
            transaction.commit();
        });

        statistics = findViewById(R.id.statisticsButton);
        /*statistics.setOnClickListener(view -> {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerView, new ScoreboardFragment());
            transaction.addToBackStack(null);
            goTo(scoreboard);
            transaction.commit();
        });*/


        searchView = findViewById(R.id.searchBar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Currency currency1 = Currency.getCurrencyByName(
                        (s.toCharArray()[0]+"").toUpperCase()+s.toLowerCase().substring(1)),
                        currency2 = Currency.getCurrencyByCode(s.toUpperCase(Locale.ROOT));
                if (currency1 != null) {
                    Intent intent = new Intent(getBaseContext(), CurrencyDetailedActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("currency code", currency1.getCode());
                    getBaseContext().startActivity(intent);
                } else if (currency2 != null) {
                    Intent intent = new Intent(getBaseContext(), CurrencyDetailedActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("currency code", currency2.getCode());
                    getBaseContext().startActivity(intent);
                } else {
//                    Toast toast = new Toast(getBaseContext());
//                    toast.setText(R.string.not_found_such_currency);
                    Toast toast = Toast.makeText(getBaseContext(), R.string.not_found_such_currency,
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        goTo(home);
        home.callOnClick();

    }

    public SharedPreferences getMPrefs() {
        return mPrefs;
    }
//    @SuppressLint("ResourceAsColor")
    private void goTo(@NonNull ImageButton imageButton) {
        home.setBackgroundColor(getResources().getColor(R.color.lightBar));
        settings.setBackgroundColor(getResources().getColor(R.color.lightBar));
        history.setBackgroundColor(getResources().getColor(R.color.lightBar));
        scoreboard.setBackgroundColor(getResources().getColor(R.color.lightBar));
        statistics.setBackgroundColor(getResources().getColor(R.color.lightBar));
        imageButton.setBackgroundColor(getResources().getColor(R.color.darkBar));
        if (imageButton == home) {
//            searchView.setActivated(true);
            searchView.setVisibility(View.VISIBLE);
        } else {
//            searchView.setActivated(false);
            searchView.setVisibility(View.INVISIBLE);
        }
//        settings.setBackgroundColor(getResources().getColor(R.color.lightBar));
    }
}

enum Fragment {
    HOME, SETTINGS, STATISTICS, HISTORY, SCOREBOARD;
}