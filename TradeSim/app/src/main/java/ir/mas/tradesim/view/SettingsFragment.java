package ir.mas.tradesim.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

import ir.mas.tradesim.R;
import ir.mas.tradesim.database.UserDb;
import ir.mas.tradesim.model.User;
import ir.mas.tradesim.view.registeration.StartActivity;


public class SettingsFragment extends Fragment {

    public static SharedPreferences mPrefs;
    TextView nicknameView, usernameView;
    Button logoutButton;
    Switch darkMode, autoDarkMode;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }
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
        usernameView.setText(User.getInstance().getAuthToken());
        nicknameView.setText(User.getInstance().getNickname());
        logoutButton = root.findViewById(R.id.logout_button);
        //TODO: to set the avatar image
        //TODO: to add the functionality of buttons

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity.userDao.deleteUsers();
                Intent intent = new Intent(getContext(), StartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        darkMode = root.findViewById(R.id.darkModeSwitch);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            darkMode.setChecked(true);
        } else {
            boolean isNightModeOn = StartActivity.userList.get(0).isDarkMode();
            if (isNightModeOn) {
                darkMode.setChecked(true);
            }
        }
        darkMode.setOnCheckedChangeListener((compoundButton, b) -> {
            if (darkMode.isChecked()){
                HomeFragment.changed = true;
                MainActivity.check = true;
                User.getInstance().setDarkMode(true);
                StartActivity.userDao.deleteUsers();
                UserDb user = new UserDb(User.getInstance().getAuthToken(),
                        User.getInstance().getNickname(), User.getInstance().getRialCredit(),
                        User.getInstance().getRialEquivalent(),
                        new Gson().toJson(User.getInstance().throughTime));


                user.setDarkMode(true);
                StartActivity.userDao.insert(user);
                getActivity().setTheme(R.style.Theme_TradeSimNight);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }
            else{
                HomeFragment.changed = true;
                MainActivity.check = true;
                User.getInstance().setDarkMode(false);
                StartActivity.userDao.deleteUsers();
                UserDb user = new UserDb(User.getInstance().getAuthToken(),
                        User.getInstance().getNickname(), User.getInstance().getRialCredit(),
                        User.getInstance().getRialEquivalent(),
                        new Gson().toJson(User.getInstance().throughTime));
                user.setDarkMode(false);
                StartActivity.userDao.insert(user);
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