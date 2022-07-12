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

        import android.Manifest;
        import android.animation.AnimatorSet;
        import android.animation.ObjectAnimator;
        import android.os.Bundle;
        import android.widget.ImageButton;
        import android.widget.LinearLayout;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.app.AppCompatDelegate;
        import androidx.core.app.ActivityCompat;
        import androidx.fragment.app.FragmentContainerView;
        import androidx.fragment.app.FragmentTransaction;

        import ir.mas.tradesim.Model.Currency;

public class MainActivity extends AppCompatActivity {

    FragmentContainerView fragment;
    ImageButton setting;
    ImageButton home;
    LinearLayout bar;
    public static boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
//        setting = findViewById(R.id.settingButton);
//        home = findViewById(R.id.homeButton);
//        fragment = findViewById(R.id.fragmentContainerView);
//
//        ObjectAnimator scaleDownXS = ObjectAnimator.ofFloat(
//                setting, "scaleX", 0.8f);
//        ObjectAnimator scaleDownYS = ObjectAnimator.ofFloat(
//                setting, "scaleY", 0.8f);
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
//                    setting.getLayoutParams();
//            layoutParams.weight = 3.0f;
//
//            setting.setImageResource(R.drawable.setting_icon_filled);
//            home.setImageResource(R.drawable.home_icon);
//
//            setting.setLayoutParams(layoutParams);
//            layoutParams = (LinearLayout.LayoutParams)
//                    home.getLayoutParams();
//            layoutParams.weight = 1.0f;
//            home.setLayoutParams(layoutParams);
//
//            transaction.addToBackStack(null);
//            transaction.commit();
//
//        }
//        setting.setOnClickListener(view -> {
//            bar.setBackgroundColor(getResources().getColor(R.color.other_light_grey));
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragmentContainerView, new SettingsFragment());
//
//            setting.setImageResource(R.drawable.setting_icon_filled);
//            home.setImageResource(R.drawable.home_icon);
//
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
//                    setting.getLayoutParams();
//            layoutParams.weight = 3.0f;
//            setting.setLayoutParams(layoutParams);
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
//                    setting, "scaleX", 1f);
//            ObjectAnimator scaleDownY12 = ObjectAnimator.ofFloat(
//                    setting, "scaleY", 1f);
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
//            setting.setImageResource(R.drawable.setting_icon);
//            home.setImageResource(R.drawable.home_icon_filled);
//
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
//                    setting.getLayoutParams();
//            layoutParams.weight = 1.0f;
//            setting.setLayoutParams(layoutParams);
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
//                    setting, "scaleX", 0.8f);
//            ObjectAnimator scaleDownY22 = ObjectAnimator.ofFloat(
//                    setting, "scaleY", 0.8f);
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
}