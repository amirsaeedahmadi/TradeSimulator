package ir.mas.tradesim.Model;

import java.math.BigDecimal;

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

    public static String parse(double value) {
        return new Adad(value).toString();
    }

    public static String parse(double value, int limit) {
        Adad adad = new Adad(value);
        adad.setLimit(limit);
        return adad.toString();
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
