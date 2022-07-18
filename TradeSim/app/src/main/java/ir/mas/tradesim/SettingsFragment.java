package ir.mas.tradesim;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import ir.mas.tradesim.Model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    public static SharedPreferences mPrefs;
    TextView nicknameView, usernameView;

    Switch darkMode, autoDarkMode;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        usernameView = root.findViewById(R.id.usernameTextView);
        nicknameView = root.findViewById(R.id.nicknameTextView);
        usernameView.setText(User.getInstance().getUsername());
        nicknameView.setText(User.getInstance().getNickname());
        //TODO: to set the avatar image
        //TODO: to add the functionality of buttons


        darkMode = root.findViewById(R.id.darkModeSwitch);
        mPrefs = StartActivity.mPrefs;
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            darkMode.setChecked(true);
        } else {
            String isNightModeOn = mPrefs.getString("DarkMode", "");
            System.out.println(isNightModeOn);
            if (isNightModeOn.equals("True")) {
                darkMode.setChecked(true);
            }
        }
        darkMode.setOnCheckedChangeListener((compoundButton, b) -> {
            if (darkMode.isChecked()){
                HomeFragment.changed = true;
                MainActivity.check = true;
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("DarkMode", "True");
//                prefsEditor.apply();
                prefsEditor.commit();
                getActivity().setTheme(R.style.Theme_TradeSimNight);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }
            else{
                HomeFragment.changed = true;
                MainActivity.check = true;
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("DarkMode", "False");
//                prefsEditor.apply();
                prefsEditor.commit();
                getActivity().setTheme(R.style.Theme_TradeSim);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        /*
        TODO: This
        autoDarkMode = root.findViewById(R.id.autoDarkModeSwitch);
        String isAutoNightModeOn = mPrefs.getString("AutoDarkMode", "");
        System.out.println(isAutoNightModeOn);
        if (isAutoNightModeOn.equals("True")) {
            autoDarkMode.setChecked(true);
        }
        autoDarkMode.setOnCheckedChangeListener((compoundButton, b) -> {
            if (autoDarkMode.isChecked()){
                HomeFragment.changed = true;
                MainActivity.check = true;
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("AutoDarkMode", "True");
//                prefsEditor.apply();
                prefsEditor.commit();
                getActivity().setTheme(R.style.Theme_TradeSimNight);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }
            else{
                HomeFragment.changed = true;
                MainActivity.check = true;
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("DarkMode", "False");
//                prefsEditor.apply();
                prefsEditor.commit();
                getActivity().setTheme(R.style.Theme_TradeSim);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });*/
        return root;
    }
}