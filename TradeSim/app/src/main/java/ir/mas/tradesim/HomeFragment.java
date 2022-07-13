package ir.mas.tradesim;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;

import ir.mas.tradesim.Exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.Model.Currency;
import ir.mas.tradesim.Model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    CurrencyRecyclerViewAdapter adapter;
    RecyclerView currencyRecyclerView;
    TextView totalEquivalentRialTextView, rialCreditTextView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
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
        // Inflate the layout for this fragment
        //
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Currency.initialize();
        currencyRecyclerView = root.findViewById(R.id.CurrencyRecyclerView);
        currencyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CurrencyRecyclerViewAdapter(getContext());
        currencyRecyclerView.setAdapter(adapter);
        totalEquivalentRialTextView = root.findViewById(R.id.totalEquivalentRialTextView);
        totalEquivalentRialTextView.setText(R.string.total_equivalent_rial_text);
        String t = (String) totalEquivalentRialTextView.getText();
        try {
            double x = Currency.getTotalRial()+ User.getInstance().getRialCredit();
            String xy = new BigDecimal(x).toPlainString();
            if (xy.length() > 12) {
                totalEquivalentRialTextView.setText(t+xy.substring(1, 12));
            } else {
                totalEquivalentRialTextView.setText(t+xy);
            }
        } catch (NotAbleToUpdateException e) {
            totalEquivalentRialTextView.setText(t+R.string.not_able_to_update);
        }
        rialCreditTextView = root.findViewById(R.id.rialCreditTextView);
        rialCreditTextView.setText(R.string.rial_credit);
        t = (String) rialCreditTextView.getText();
        double  x1 = User.getInstance().getRialCredit();
        String x1y = new BigDecimal(x1).toPlainString();
        if (x1y.length() > 12) {
            rialCreditTextView.setText(t+x1y.substring(1, 12));
        } else {
            rialCreditTextView.setText(t+x1y);
        }

        return root;
    }
}