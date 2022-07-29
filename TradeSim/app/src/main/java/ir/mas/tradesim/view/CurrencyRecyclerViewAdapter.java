package ir.mas.tradesim.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.mas.tradesim.R;
import ir.mas.tradesim.exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.model.Adad;
import ir.mas.tradesim.model.Currency;

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
        TextView nameView, codeView, priceView, creditView, rialCreditView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            logoView = itemView.findViewById(R.id.currencyLogoImageView);
            nameView = itemView.findViewById(R.id.currencyNameTextView);
            codeView = itemView.findViewById(R.id.currencyCodeTextView);
            priceView = itemView.findViewById(R.id.currencyPriceTextView);
            creditView = itemView.findViewById(R.id.currencyCreditTextView);
            rialCreditView = itemView.findViewById(R.id.currencyRialCreditTextView);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if (getItemCount() != 0) {
//            //holder.courseName.setText(courses.get(position).courseName);
//            return;
//
//        }
        System.out.println("we are in currencyRecyclerViewAdapter");
        Currency currency = Currency.getCurrencies().get(position);
       // String x = new BigDecimal(currency.getCredit()).toPlainString();

        holder.logoView.setImageResource(currency.getLogo());

        /*Picasso.Builder builder = new Picasso.Builder(inflater.getContext());
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                exception.printStackTrace();
            }
        });
        builder.build().load(currency.logo)
                .into(holder.logoView);*/

        holder.nameView.setText(currency.getName());
        holder.codeView.setText(currency.getCode());
        if ((currency.getPrice() == -1)) {
            holder.priceView.setText(R.string.not_able_to_update);
        } else {
            holder.priceView.setText(Adad.parse(currency.getPrice(), mContext));
        }
        holder.creditView.setText(Adad.parse(currency.getCredit(), mContext));
//        if (currency.getCredit() != 0) {
//            if (x.length() > 10) {
//                holder.creditView.setText(
//                        x.substring(0, 10));
//            } else
//            holder.creditView.setText(x);
//        } else {
//            holder.creditView.setText("0.00");
//        }
//        System.out.println(">>>"+currency.getCode()+" : "+ new Float(currency.getCredit()).);
//        System.out.println(">>>"+currency.getCode()+" : "+
//                x.substring(0, (x.length() > 10 ? 10 : x.length()-1)));

//        try {
//            String y = new BigDecimal(currency.getRialEquivalent()).toPlainString();
//            if (currency.getRialEquivalent() != 0) {
//                if (y.length() > 10) {
//                    holder.rialCreditView.setText(y.substring(0,10));
//                } else {
//                    holder.rialCreditView.setText(y);
//                }
//            } else {
//                holder.rialCreditView.setText("0.00");
//            }
//        } catch (NotAbleToUpdateException e) {
//            holder.rialCreditView.setText(R.string.not_able_to_update);
//        }
        try {
            holder.rialCreditView.setText(Adad.parse(currency.getRialEquivalent(), mContext));
        } catch (NotAbleToUpdateException e) {
            holder.rialCreditView.setText(R.string.not_able_to_update);

        }

        //todo: to add onclickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CurrencyDetailedActivity.class);
                intent.putExtra("currency code", currency.getCode());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return Currency.getCurrencies().size();
    }
}
