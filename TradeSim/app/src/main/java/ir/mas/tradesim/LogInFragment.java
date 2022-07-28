package ir.mas.tradesim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import ir.mas.tradesim.database.UserDb;
import ir.mas.tradesim.model.Currency;
import ir.mas.tradesim.model.User;
import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Strings;
import ir.mas.tradesim.enums.Views;

public class LogInFragment extends Fragment {

    EditText privateKeyView;
    Button signInButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String key;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LogInFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LogInFragment newInstance(String param1, String param2) {
        LogInFragment fragment = new LogInFragment();
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

        View root = inflater.inflate(R.layout.fragment_log_in, container, false);
        privateKeyView = root.findViewById(R.id.PrivateKeyForSignInView);
        signInButton = root.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String privateKey = privateKeyView.getText().toString();
                if (privateKey.equals("")) {
                    Toast.makeText(getContext(), R.string.empty_input, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (privateKey.length() < 5) {
                    Toast.makeText(getContext(), R.string.too_short_private_key, Toast.LENGTH_LONG).show();
                    return;
                }
                login(privateKey);
            }
        });
        return root;
    }


    private void login(String key) {

        LogInFragment.key = key;


        new GetDataFromServer().execute();
    }

    class GetDataFromServer extends AsyncTask<Void, Void, String> {

        boolean checker = false;

        @Override
        protected String doInBackground(Void... voids) {

            try {
                Request.setCommandTag(CommandTags.LOGIN);
                Request.setCurrentMenu(Views.REGISTER_VIEW);
                Request.addData(Strings.PRIVATE_KEY.getLabel(), key);
                Request.sendToServer();

            } catch (Exception e) {
//                Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_SHORT).show();
                String message = getString(R.string.connection_error);
                System.out.println(message);
                e.printStackTrace();
                return message;
            }

            String message = getString(R.string.connection_success);
            System.out.println(message);
            checker = true;
            return message;

        }

        @Override
        protected void onPostExecute(String message) {
            super.onPostExecute(message);
            System.out.println(message);

            if (!checker) {
                Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                if (Request.isSuccessful()) {
                    Currency.refresh();
                    String authToken = key;
                    String nickname = Request.getResponse().getString(Strings.NICKNAME
                                    .getLabel());
                    String rial_credit = Request.getResponse()
                            .getString(Strings.RIAL_CREDIT.getLabel());

                    User.setInstance(new User(authToken, nickname, Double.parseDouble(rial_credit)
                            , 0));

                    System.out.println(Request.getResponse().getString("currencies"));


                    StartActivity.userDao.deleteUsers();
                    StartActivity.userDao.insert(new UserDb(User.getInstance().getAuthToken(),
                            User.getInstance().getNickname(), User.getInstance().getRialCredit(),
                            User.getInstance().getRialEquivalent()));
                    StartActivity.userList = StartActivity.userDao.getAllUsers();

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(intent);
                } else {
                    Toast.makeText(getContext(), R.string.invalid, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getContext(), R.string.response_error, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
    }
}