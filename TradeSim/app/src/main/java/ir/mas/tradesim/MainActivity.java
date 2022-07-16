package ir.mas.tradesim;
/*
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}*/

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowInsets;
        import android.widget.ArrayAdapter;
        import android.widget.ImageButton;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.SearchView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.app.AppCompatDelegate;
        import androidx.fragment.app.FragmentContainerView;
        import androidx.fragment.app.FragmentTransaction;

        import java.util.Locale;

        import ir.mas.tradesim.Model.Currency;

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
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, new TransactionsFragment());
                transaction.addToBackStack(null);
                goTo(history);
                transaction.commit();
            }
        });

        home = findViewById(R.id.homeButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, new HomeFragment());
                goTo(home);
                transaction.commit();
            }
        });

        settings = findViewById(R.id.settingButton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, new SettingsFragment());
                transaction.addToBackStack(null);
                goTo(settings);
                transaction.commit();
            }
        });

        scoreboard = findViewById(R.id.scoreboardImageView);
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, new ScoreboardFragment());
                transaction.addToBackStack(null);
                goTo(scoreboard);
                transaction.commit();
            }
        });

        statistics = findViewById(R.id.statisticsButton);
        //TODO

        searchView = findViewById(R.id.searchBar);
        searchView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                System.out.println("OOOOOKKKKK");
                return null;
            }
        });
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

//        SettingsFragment.mPrefs = getPreferences(MODE_PRIVATE);
//        String viewMode = SettingsFragment.mPrefs.getString("DarkMode", "False");
//        if (viewMode.equals("True")){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }
//
//        bar = findViewById(R.id.linearLayout);
//        settings = findViewById(R.id.settingButton);
//        home = findViewById(R.id.homeButton);
//        fragment = findViewById(R.id.fragmentContainerView);
//
//        ObjectAnimator scaleDownXS = ObjectAnimator.ofFloat(
//                settings, "scaleX", 0.8f);
//        ObjectAnimator scaleDownYS = ObjectAnimator.ofFloat(
//                settings, "scaleY", 0.8f);
//        scaleDownXS.setDuration(800);
//        scaleDownYS.setDuration(800);
//        AnimatorSet scaleDownS = new AnimatorSet();
//
//        scaleDownS.play(scaleDownXS).with(scaleDownYS);
//        scaleDownS.start();
//        if (check){
//            bar.setBackgroundColor(getResources().getColor(R.color.other_light_grey));
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragmentContainerView, new SettingsFragment());
//
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
//                    settings.getLayoutParams();
//            layoutParams.weight = 3.0f;
//
//            settings.setImageResource(R.drawable.setting_icon_filled);
//            home.setImageResource(R.drawable.home_icon);
//
//            settings.setLayoutParams(layoutParams);
//            layoutParams = (LinearLayout.LayoutParams)
//                    home.getLayoutParams();
//            layoutParams.weight = 1.0f;
//            home.setLayoutParams(layoutParams);
//
//            transaction.addToBackStack(null);
//            transaction.commit();
//
//        }
//        settings.setOnClickListener(view -> {
//            bar.setBackgroundColor(getResources().getColor(R.color.other_light_grey));
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragmentContainerView, new SettingsFragment());
//
//            settings.setImageResource(R.drawable.setting_icon_filled);
//            home.setImageResource(R.drawable.home_icon);
//
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
//                    settings.getLayoutParams();
//            layoutParams.weight = 3.0f;
//            settings.setLayoutParams(layoutParams);
//            layoutParams = (LinearLayout.LayoutParams)
//                    home.getLayoutParams();
//            layoutParams.weight = 1.0f;
//            home.setLayoutParams(layoutParams);
//
//            transaction.addToBackStack(null);
//            transaction.commit();
//
//            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(home,
//                    "scaleX", 0.8f);
//            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(home,
//                    "scaleY", 0.8f);
//            scaleDownX.setDuration(800);
//            scaleDownY.setDuration(800);
//            AnimatorSet scaleDown = new AnimatorSet();
//
//            ObjectAnimator scaleDownX12 = ObjectAnimator.ofFloat(
//                    settings, "scaleX", 1f);
//            ObjectAnimator scaleDownY12 = ObjectAnimator.ofFloat(
//                    settings, "scaleY", 1f);
//            scaleDownX12.setDuration(800);
//            scaleDownY12.setDuration(800);
//            AnimatorSet scaleDown12 = new AnimatorSet();
//
//            scaleDown12.play(scaleDownX12).with(scaleDownY12);
//            scaleDown12.start();
//            scaleDown.play(scaleDownX).with(scaleDownY);
//            scaleDown.start();
//
//        });
//        home.setOnClickListener(view -> {
//            bar.setBackgroundColor(getResources().getColor(R.color.light_grey));
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragmentContainerView, new HomeFragment());
//
//            settings.setImageResource(R.drawable.setting_icon);
//            home.setImageResource(R.drawable.home_icon_filled);
//
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
//                    settings.getLayoutParams();
//            layoutParams.weight = 1.0f;
//            settings.setLayoutParams(layoutParams);
//            layoutParams = (LinearLayout.LayoutParams)
//                    home.getLayoutParams();
//            layoutParams.weight = 3.0f;
//            home.setLayoutParams(layoutParams);
//
//            transaction.addToBackStack(null);
//            transaction.commit();
//
//            ObjectAnimator scaleDownX2 = ObjectAnimator.ofFloat(
//                    home, "scaleX", 1f);
//            ObjectAnimator scaleDownY2 = ObjectAnimator.ofFloat(
//                    home, "scaleY", 1f);
//            scaleDownX2.setDuration(800);
//            scaleDownY2.setDuration(800);
//            AnimatorSet scaleDown2 = new AnimatorSet();
//
//            ObjectAnimator scaleDownX22 = ObjectAnimator.ofFloat(
//                    settings, "scaleX", 0.8f);
//            ObjectAnimator scaleDownY22 = ObjectAnimator.ofFloat(
//                    settings, "scaleY", 0.8f);
//            scaleDownX22.setDuration(800);
//            scaleDownY22.setDuration(800);
//            AnimatorSet scaleDown22 = new AnimatorSet();
//
//            scaleDown22.play(scaleDownX22).with(scaleDownY22);
//            scaleDown22.start();
//            scaleDown2.play(scaleDownX2).with(scaleDownY2);
//            scaleDown2.start();
//        });
//    }
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