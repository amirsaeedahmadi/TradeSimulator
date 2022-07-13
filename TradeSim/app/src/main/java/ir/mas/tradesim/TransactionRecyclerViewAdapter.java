package ir.mas.tradesim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;

import ir.mas.tradesim.Exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.Model.Adad;
import ir.mas.tradesim.Model.Currency;
import ir.mas.tradesim.Model.Transaction;
import ir.mas.tradesim.Model.TransactionType;

public class TransactionRecyclerViewAdapter extends RecyclerView.Adapter<TransactionRecyclerViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context mContext;

    public TransactionRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TransactionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionRecyclerViewAdapter.ViewHolder(inflater.inflate(R.layout.transaction_linear_layout, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView logoView, typeView;
        TextView nameView, dateView, amountView, rialView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            logoView = itemView.findViewById(R.id.logoImageView);
            nameView = itemView.findViewById(R.id.nameTextView);
            dateView = itemView.findViewById(R.id.dateTextView);
            typeView = itemView.findViewById(R.id.typeImageView);
            amountView = itemView.findViewById(R.id.amountTextView);
            rialView = itemView.findViewById(R.id.rialAmountTextView);
        }

        @Override
        public void onClick(View view) {
            //
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionRecyclerViewAdapter.ViewHolder holder, int position) {
//        if (getItemCount() != 0) {
//            //holder.courseName.setText(courses.get(position).courseName);
//            return;
//
//        }

        //todo: to add other attributes ...
        Transaction transaction = Transaction.getTransactions().get(position);
        holder.logoView.setImageResource(transaction.getCurrency().getLogo());
        holder.nameView.setText(transaction.getCurrency().getName());
        holder.dateView.setText(transaction.getDate().toString());
        if (transaction.getType() == TransactionType.BUY) {
            holder.typeView.setImageResource(R.drawable.ic_down);
        } else {
            holder.typeView.setImageResource(R.drawable.ic_up);
        }
        holder.amountView.setText(
                Adad.parse(transaction.getCurrencyAmount())+" "+transaction.getCurrency().getCode());
        holder.rialView.setText(Adad.parse(transaction.getRialAmount())+" T");

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
        System.out.println(Transaction.getTransactions().size());
        return Transaction.getTransactions().size();
    }

}
