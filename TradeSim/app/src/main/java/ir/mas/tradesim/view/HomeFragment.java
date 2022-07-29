package ir.mas.tradesim.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.mas.tradesim.R;
import ir.mas.tradesim.exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.model.Adad;
import ir.mas.tradesim.model.Currency;
import ir.mas.tradesim.model.User;


public class HomeFragment extends Fragment {

    public static boolean changed;
    CurrencyRecyclerViewAdapter adapter;
    RecyclerView currencyRecyclerView;
    TextView totalEquivalentRialTextView, rialCreditTextView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
//            Currency.initialize();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        Currency.initialize();
        currencyRecyclerView = root.findViewById(R.id.CurrencyRecyclerView);
        currencyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CurrencyRecyclerViewAdapter(getContext());
        currencyRecyclerView.setAdapter(adapter);
        totalEquivalentRialTextView = root.findViewById(R.id.totalEquivalentRialTextView);
        totalEquivalentRialTextView.setText(R.string.total_equivalent_rial_text);
        String t = (String) totalEquivalentRialTextView.getText();
        try {
            totalEquivalentRialTextView.setText(t+Adad.parse(
                    Currency.getTotalRial()+ User.getInstance().getRialCredit(), getContext()));
//            double x = Currency.getTotalRial()+ User.getInstance().getRialCredit();
//            String xy = new BigDecimal(x).toPlainString();
//            if (xy.length() > 12) {
//                totalEquivalentRialTextView.setText(t+xy.substring(0, 12));
//            } else {
//                totalEquivalentRialTextView.setText(t+xy);
//            }
        } catch (NotAbleToUpdateException e) {
            totalEquivalentRialTextView.setText(t+R.string.not_able_to_update);
        }
        rialCreditTextView = root.findViewById(R.id.rialCreditTextView);
        rialCreditTextView.setText(R.string.rial_credit);
        t = (String) rialCreditTextView.getText();
//        double  x1 = User.getInstance().getRialCredit();
//        String x1y = new BigDecimal(x1).toPlainString();
//        if (x1y.length() > 12) {
//            rialCreditTextView.setText(t+x1y.substring(0, 12));
//        } else {
//            rialCreditTextView.setText(t+x1y);
//        }
        rialCreditTextView.setText(t+Adad.parse(User.getInstance().getRialCredit(), getContext()));
        return root;
    }
}