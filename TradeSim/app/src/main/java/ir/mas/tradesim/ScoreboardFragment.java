package ir.mas.tradesim;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import ir.mas.tradesim.Model.TopUser;
import ir.mas.tradesim.enums.CommandTags;
import ir.mas.tradesim.enums.Views;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreboardFragment extends Fragment {
    RecyclerView topUsersRecyclerView;
    TopUsersRecyclerViewAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScoreboardFragment() {
        // get data from server
        // TODO: revise this method
        try {
            Request.setCommandTag(CommandTags.SHOW_SCOREBOARD);
            Request.setCurrentMenu(Views.SCOREBOARD_VIEW);
            // TODO: next two lines are appropriate for login menu, remove them in final version!
//            Request.addData(Strings.USERNAME.getLabel(), userUsername);
//            Request.addData(Strings.PASSWORD.getLabel(), passwordUsername);
            Request.sendToServer();

            // you can get message that server returns with this method and show that in ui:


        } catch (JSONException e) {
            // TODO: handle upcoming errors in client
            e.printStackTrace();
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScoreboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreboardFragment newInstance(String param1, String param2) {
        ScoreboardFragment fragment = new ScoreboardFragment();
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
        View root = inflater.inflate(R.layout.fragment_scoreboard, container, false);
        TopUser.initialize();
        topUsersRecyclerView = root.findViewById(R.id.topUsersRecyclerView);
        topUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TopUsersRecyclerViewAdapter(getContext());
        topUsersRecyclerView.setAdapter(adapter);
        return root;
    }
}