package ir.mas.tradesim.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robinhood.spark.SparkView;

import ir.mas.tradesim.R;
import ir.mas.tradesim.model.User;


public class StatsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    SparkView sparkView;
    TextView textView;

    public StatsFragment() {}

    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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
        View root = inflater.inflate(R.layout.fragment_stats, container, false);
        sparkView = root.findViewById(R.id.sparkView2);
        textView = root.findViewById(R.id.plotTitle);
        User.getInstance().throughTime.add("9000");
        User.getInstance().throughTime.add("9900");
        User.getInstance().throughTime.add("9950");
        float[] yData = new float[User.getInstance().throughTime.size()];
        for (int i = 0; i < User.getInstance().throughTime.size(); i++) {
            yData[i] = Float.parseFloat(User.getInstance().throughTime.get(i));
        }
        sparkView.setAdapter(new CurrencySparkAdapter(yData));
        return root;
    }
}