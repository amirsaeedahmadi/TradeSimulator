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
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.LinearLayout;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.app.AppCompatDelegate;
        import androidx.fragment.app.FragmentContainerView;
        import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mPrefs;
    FragmentContainerView fragment;
    ImageButton settings, statistics, home, history, scoreboard;
    LinearLayout bar;
    FragmentTransaction transaction;
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
                transaction.commit();
            }
        });

        home = findViewById(R.id.homeButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, new HomeFragment());
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
                transaction.commit();
            }
        });

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
}