package ir.mas.tradesim.exceptions;

import android.content.Context;
import android.widget.Toast;

import ir.mas.tradesim.R;

public class NotAbleToUpdateException extends Exception{
    public NotAbleToUpdateException() {
        super();
    }
    public void makeToast(Context context) {
        Toast.makeText(context, R.string.not_able_to_update, Toast.LENGTH_SHORT).show();
    }
}
