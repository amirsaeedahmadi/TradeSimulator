package ir.mas.tradesim.view;

import com.robinhood.spark.SparkAdapter;

public class CurrencySparkAdapter extends SparkAdapter {
    private float[] yData;

    public CurrencySparkAdapter(float[] yData) {
        this.yData = yData;
    }

    @Override
    public int getCount() {
        return yData.length;
    }

    @Override
    public Object getItem(int index) {
        return yData[index];
    }

    @Override
    public float getY(int index) {
        return yData[index];
    }
}
