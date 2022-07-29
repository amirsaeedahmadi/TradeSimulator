package ir.mas.tradesim.view.registeration;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import org.json.JSONException;

import java.util.Random;

import ir.mas.tradesim.R;
import ir.mas.tradesim.database.UserDb;
import ir.mas.tradesim.model.Currency;
import ir.mas.tradesim.model.User;
import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Strings;
import ir.mas.tradesim.enums.Views;
import ir.mas.tradesim.view.MainActivity;
import ir.mas.tradesim.view.Request;


public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText nicknameView, privateKeyView;
    Button signUpButton, generateRandomKeyButton;

    // TODO: Rename and change types of parameters
    private static String privateKey;
    private static String nickname;

    public SignUpFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
            privateKey = getArguments().getString(ARG_PARAM1);
            nickname = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);
        privateKeyView = root.findViewById(R.id.privateKeyForSignUpView);
        nicknameView = root.findViewById(R.id.nicknameInputView);
        signUpButton = root.findViewById(R.id.signUpButton);
        generateRandomKeyButton = root.findViewById(R.id.generateRandomKeyButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String privateKey = privateKeyView.getText().toString();
                String nickname = nicknameView.getText().toString();
                if (privateKey.equals("")||nickname.equals(""))
                    Toast.makeText(getContext(), R.string.empty_input, Toast.LENGTH_SHORT).show();
                else if (privateKey.length() < 5)
                    Toast.makeText(getContext(), R.string.too_short_private_key, Toast.LENGTH_LONG).show();
                else signUp(nickname, privateKey);
            }
        });
        generateRandomKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                StringBuilder key = new StringBuilder();
                int character;
                char[] chars =
                        "1234567890wertyuiop[]asdfghjkl;'zxcvbnm,./\\!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:ZXCVBNM<>?-=     \t".
                                toCharArray();
                for (int i = 0; i < 256; i++) {
                    character = random.nextInt(chars.length-1);
                    key.append(chars[character]);
                }
                privateKeyView.setText(key.toString());
            }
        });
        return root;
    }

    private void signUp(String nickname, String privateKey) {
        SignUpFragment.privateKey = privateKey;
        SignUpFragment.nickname = nickname;

        new GetDataFromServerSignUp().execute();

    }

    class GetDataFromServerSignUp extends AsyncTask<Void, Void, String> {

        boolean checker = false;

        @Override
        protected String doInBackground(Void... voids) {

            try {
                Request.setCommandTag(CommandTags.REGISTER);
                Request.setCurrentMenu(Views.REGISTER_VIEW);
                Request.addData(Strings.PRIVATE_KEY.getLabel(), privateKey);
                Request.addData(Strings.NICKNAME.getLabel(), nickname);
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

                    String authToken = Request.getResponse().getString(Strings.PRIVATE_KEY
                            .getLabel());
                    String nickname = Request.getResponse().getString(Strings.NICKNAME
                            .getLabel());
                    String rial_credit = Request.getResponse()
                            .getString(Strings.RIAL_CREDIT.getLabel());

                    User.setInstance(new User(authToken, nickname, Double.parseDouble(rial_credit),
                            0));

                    StartActivity.userDao.deleteUsers();
                    StartActivity.userDao.insert(new UserDb(User.getInstance().getAuthToken(),
                            User.getInstance().getNickname(), User.getInstance().getRialCredit(),
                            User.getInstance().getRialEquivalent(),
                            new Gson().toJson(User.getInstance().throughTime)));
                    StartActivity.userList = StartActivity.userDao.getAllUsers();

                    System.out.println(Request.getMessage());
                    System.out.println(Currency.currencies);

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