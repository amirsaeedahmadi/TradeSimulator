package edu.sharif.tradesimulator4;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sharif.tradesimulator4.R;

public class SettingsFragment extends Fragment {

    public static SharedPreferences mPrefs;

    SwitchCompat darkMode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container
                , false);



        darkMode = root.findViewById(R.id.darkModeSwitch);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            darkMode.setChecked(true);
        }
        darkMode.setOnCheckedChangeListener((compoundButton, b) -> {
            if (darkMode.isChecked()){
                HomeFragment.changed = true;
                MainActivity.check = true;
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("DarkMode", "True");
                prefsEditor.apply();
                getActivity().setTheme(R.style.Theme_TradeSimulator4night);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }
            else{
                HomeFragment.changed = true;
                MainActivity.check = true;
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("DarkMode", "False");
                prefsEditor.apply();
                getActivity().setTheme(R.style.Theme_TradeSimulator4);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        return root;
    }
}