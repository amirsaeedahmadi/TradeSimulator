package ir.mas.tradesim.view.registeration;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.List;

import ir.mas.tradesim.R;
import ir.mas.tradesim.database.MyRoomDatabase;
import ir.mas.tradesim.database.UserDao;
import ir.mas.tradesim.database.UserDb;
import ir.mas.tradesim.model.Currency;
import ir.mas.tradesim.model.User;
import ir.mas.tradesim.view.MainActivity;
import ir.mas.tradesim.view.Request;


public class StartActivity extends AppCompatActivity {

    public static UserDao userDao;
    public static List<UserDb> userList;




    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        MyRoomDatabase database = MyRoomDatabase.getInstance(getBaseContext());
        userDao = database.userDao();

        userList = userDao.getAllUsers();

        if (userList.isEmpty()) {
            intent = new Intent(getBaseContext(), SignInActivity.class);
            new MyTimer().execute(intent);
        }
        else {
            UserDb userDb = userDao.getAllUsers().get(0);
            Request.token = userDb.getAuthToken();
            Currency.refresh();
            User.setInstance(new User(userDb.getAuthToken(), userDb.getNickname(),
                    userDb.getRialCredit(),userDb.getRialEquivalent()));

            intent = new Intent(getBaseContext(), MainActivity.class);
            new MyTimer().execute(intent);
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