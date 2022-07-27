package ir.mas.tradesim.model;

import android.content.Context;
import android.widget.TextView;

import java.math.BigDecimal;

import ir.mas.tradesim.exceptions.NotAbleToUpdateException;
import ir.mas.tradesim.R;

/**
 * This is a class which helps with showing numbers and handling exceptions
 *  which has been written for this project (it might not work appropriately on another project)
 *  @author Mahdi Teymoori Anar
 *   */
public class Adad {
    private double value;
    private int limit = 6;

    public Adad(double value) {
        this.value = value;
    }

    public static String parse(double value) throws NotAbleToUpdateException {
        if (value < 0) {
            throw new NotAbleToUpdateException();
        }
        return new Adad(value).toString();
    }

    public static String parse(double value, int limit) {
        Adad adad = new Adad(value);
        adad.setLimit(limit);
        return adad.toString();
    }

    public static String parse(double value, Context context) {
        String str = null;
        try {
            str = parse(value);
        } catch (NotAbleToUpdateException e) {
            TextView textView = new TextView(context);
            textView.setText(R.string.not_able_to_update);
            return textView.getText().toString();
        }
        return castLang(str, context);
    }

    public static String reparseString(String str, Context context) {
        return recastLang(str, context);
    }

    public static Double reparse(String str, Context context) {
        return Double.parseDouble(recastLang(str, context));
    }

    private static String castLang(String str, Context context) {
        TextView textView = new TextView(context);
        char [] star = str.toCharArray();
        String nums = "";
        textView.setText(R.string.n0);nums+=textView.getText();
        textView.setText(R.string.n1);nums+=textView.getText();
        textView.setText(R.string.n2);nums+=textView.getText();
        textView.setText(R.string.n3);nums+=textView.getText();
        textView.setText(R.string.n4);nums+=textView.getText();
        textView.setText(R.string.n5);nums+=textView.getText();
        textView.setText(R.string.n6);nums+=textView.getText();
        textView.setText(R.string.n7);nums+=textView.getText();
        textView.setText(R.string.n8);nums+=textView.getText();
        textView.setText(R.string.n9);nums+=textView.getText();
        textView.setText(R.string.nFloatingPoint);nums+=textView.getText();
        char[] arr = nums.toCharArray();
        int j;
        String ans = "";
        for (int i = 0; i < star.length; i++) {
            if (star[i] != '.')
                ans += arr[Integer.parseInt(String.valueOf(star[i]))];
            else ans += arr[10];
        }
        return ans;
    }

    private static String recastLang(String str, Context context) {
        TextView textView = new TextView(context);
        char [] star = str.toCharArray();
        String nums = "";
        textView.setText(R.string.n0);nums+=textView.getText();
        textView.setText(R.string.n1);nums+=textView.getText();
        textView.setText(R.string.n2);nums+=textView.getText();
        textView.setText(R.string.n3);nums+=textView.getText();
        textView.setText(R.string.n4);nums+=textView.getText();
        textView.setText(R.string.n5);nums+=textView.getText();
        textView.setText(R.string.n6);nums+=textView.getText();
        textView.setText(R.string.n7);nums+=textView.getText();
        textView.setText(R.string.n8);nums+=textView.getText();
        textView.setText(R.string.n9);nums+=textView.getText();
        textView.setText(R.string.nFloatingPoint);nums+=textView.getText();
        char[] arr = nums.toCharArray();
        int j;
        String ans = "";
        for (int i = 0; i < star.length; i++) {
            if (star[i] != arr[10])
                ans += Integer.parseInt(String.valueOf(star[i]));
            else if (star[i] == arr[10])
                ans += '.';
            else throw new IllegalArgumentException();
        }
        return ans;
    }



    @Override
    public String toString() {
        String ans = new BigDecimal(value).toPlainString();
        char[] arr = ans.toCharArray();
        int point = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '.') {
                point = i;
                break;
            }
        }
        if (point == -1)
            return ans;
        if (arr.length - point - 1 > limit)
            return ans.substring(0, point+limit+1);
        return ans;
    }

    // getters and setters

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit > 0)
            this.limit = limit;
        else throw new IllegalArgumentException();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
