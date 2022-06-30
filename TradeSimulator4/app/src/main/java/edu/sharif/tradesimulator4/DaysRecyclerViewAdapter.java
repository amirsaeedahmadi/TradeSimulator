package edu.sharif.tradesimulator4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sharif.tradesimulator4.Model.Day;


public class DaysRecyclerViewAdapter extends RecyclerView.Adapter<DaysRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Day> days;
    private LayoutInflater inflater;

    private Context mContext;

    public DaysRecyclerViewAdapter(Context context, ArrayList<Day> days){

        this.mContext = context;
        this.days = days;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.layout_day, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView temperatureTextView, temperatureFeelsLikeTextView, windSpeedTextView;
        ImageView weatherImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
            temperatureFeelsLikeTextView = itemView.findViewById(R.id.temperatureFeelsLikeTextView);
            windSpeedTextView = itemView.findViewById(R.id.windSpeedTextView);
            weatherImageView = itemView.findViewById(R.id.weatherImageView);


        }
    }


    @Override
    public void onBindViewHolder(@NonNull DaysRecyclerViewAdapter.ViewHolder holder, int position) {

        Day currentDay = days.get(position);

        holder.temperatureTextView.setText(currentDay.getTemperature());
        holder.temperatureFeelsLikeTextView.setText(currentDay.getTemperature_feels_like());
        holder.windSpeedTextView.setText(currentDay.getWind_speed() + " KM / Hour");
        int resId = mContext.getResources().getIdentifier(currentDay.weatherIcon,
                "drawable", mContext.getPackageName());
        holder.weatherImageView.setImageResource(resId);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println(currentDay.toString());
//
//                Intent detailsActivityIntent = new Intent(mContext, DetailsActivity.class);
//                detailsActivityIntent.putExtra("dayIndex", days.indexOf(currentDay));
//                mContext.startActivity(detailsActivityIntent);



            }
        });
    }


    public void updateDataSet() {

        notifyDataSetChanged();

    }




    @Override
    public int getItemCount() {
        return days.size();
    }
}
