package ir.mas.tradesim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.mas.tradesim.model.Adad;
import ir.mas.tradesim.model.TopUser;

public class TopUsersRecyclerViewAdapter extends RecyclerView.Adapter<TopUsersRecyclerViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context mContext;

    public TopUsersRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TopUsersRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopUsersRecyclerViewAdapter.ViewHolder(inflater.inflate(R.layout.top_user_linear_layout, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nicknameView, rankView, rialView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nicknameView = itemView.findViewById(R.id.topUserNicknameTextView);
            rankView = itemView.findViewById(R.id.topUserRankTextView);
            rialView = itemView.findViewById(R.id.topUserRialTextView);

        }

        @Override
        public void onClick(View view) {
            //
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TopUsersRecyclerViewAdapter.ViewHolder holder, int position) {
//        if (getItemCount() != 0) {
//            //holder.courseName.setText(courses.get(position).courseName);
//            return;
//
//        }
        TopUser topUser = TopUser.getTopUsers().get(position);
        holder.nicknameView.setText(topUser.getNickname());
        holder.rialView.setText(Adad.parse(topUser.getTotalEquivalentRial(), mContext));
        holder.rankView.setText(Adad.parse(topUser.getRank(), mContext));

        //todo: to add onclickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return TopUser.getTopUsers().size();
    }
}

