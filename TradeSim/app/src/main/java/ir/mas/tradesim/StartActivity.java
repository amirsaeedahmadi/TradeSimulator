package ir.mas.tradesim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.ArrayList;

import ir.mas.tradesim.model.Currency;
import ir.mas.tradesim.model.User;
import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Views;

public class StartActivity extends AppCompatActivity {
    public static SharedPreferences mPrefs;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mPrefs = this.getPreferences(MODE_PRIVATE);
        Request.token = mPrefs.getString("Token", "");
        if (Request.token.equals("")) {
            // TODO : go to the sign up/in menu
            intent = new Intent(getBaseContext(), SignInActivity.class);
            new MyTimer().execute(intent);
        } else {

            User.getInstance().setUsername(mPrefs.getString("username", "<USERNAME>"));
            User.getInstance().setNickname(mPrefs.getString("nickname", "<NICKNAME>"));
            User.getInstance().setRialCredit(Double.parseDouble(mPrefs.getString("rialCredit", "0")));


//            Currency.initialize();


            new PrepareMainMenu().execute();

            intent = new Intent(getBaseContext(), MainActivity.class);
            new MyTimer().execute(intent);
        }
    }

    class PrepareMainMenu extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Request.setCommandTag(CommandTags.GET_CURRENCIES);
                Request.setCurrentMenu(Views.REGISTER_VIEW);
                Request.sendToServer();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                if (Request.isSuccessful()) {
                    System.out.println(Request.getMessage());

                    Currency.currencies = new Gson().fromJson(Request.getMessage(), new TypeToken<ArrayList<Currency>>() {
                    }.getType());
                    System.out.println(Currency.currencies);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    class MyTimer extends AsyncTask<Intent, Void, Void> {

        Intent intent;
        /**
         * @param intents
         * @deprecated
         */
        @Override
        protected Void doInBackground(Intent... intents) {
            intent = intents[0];
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * @param unused
         * @deprecated
         */
        @Override
        protected void onPostExecute(Void unused) {
//            super.onPostExecute(unused);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getBaseContext().startActivity(intent);
        }
    }
}