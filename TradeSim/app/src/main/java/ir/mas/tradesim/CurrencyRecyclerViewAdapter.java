package ir.mas.tradesim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.mas.tradesim.Model.Currency;

public class CurrencyRecyclerViewAdapter extends RecyclerView.Adapter<CurrencyRecyclerViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context mContext;

    public CurrencyRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.currency_linear_layout, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView logoView;
        TextView nameView, codeView;//todo: other attributes ...
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            logoView = itemView.findViewById(R.id.currencyLogoImageView);
            nameView = itemView.findViewById(R.id.currencyNameTextView);
            codeView = itemView.findViewById(R.id.currencyCodeTextView);
        }

        @Override
        public void onClick(View view) {
            //
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if (getItemCount() != 0) {
//            //holder.courseName.setText(courses.get(position).courseName);
//            return;
//
//        }
        Currency currency = Currency.getCurrencies().get(position);
        holder.logoView.setImageResource(currency.getLogo());
        holder.nameView.setText(currency.getName());
        holder.codeView.setText(currency.getCode());
        //todo: to add other attributes ...

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
        return Currency.getCurrencies().size();
    }
}
