package ir.mas.tradesim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import ir.mas.tradesim.Model.Currency;
import ir.mas.tradesim.databinding.ActivityCurrencyDetailedBinding;

public class CurrencyDetailedActivity extends AppCompatActivity {

    private ActivityCurrencyDetailedBinding binding;
    Intent intent;
    Currency currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCurrencyDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
        currency = Currency.getCurrencyByCode(intent.getStringExtra("currency code"));

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        toolBarLayout.setTitle(currency.toString());
        toolBarLayout.setBackgroundResource(currency.getLogo());
        toolBarLayout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                System.out.println("Drag");
                return false;
            }
        });

        toolBarLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                System.out.println(">>>scroll 2 change i = "+i+", i1 = "+i1+", i2 = "+i2+", i3 = "+i3);
            }
        });
        toolbar.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                System.out.println(">>>scroll 1 change i = "+i+", i1 = "+i1+", i2 = "+i2+", i3 = "+i3);
            }
        });
        toolbar.setLogo(currency.getPngLogo());
        FloatingActionButton fab = binding.fab;
        fab.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int i) {
                System.out.println(">>>CHANGED!!!!!!!!!!!!!");
            }
        });
        fab.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                System.out.println(">>>HOVER CHANGED!!!!!!!!!!!!!");
                return false;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                System.out.println(toolbar.isInLayout());
                System.out.println(toolbar.isTitleTruncated());
                System.out.println(toolBarLayout.isLaidOut());
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TextView textView = findViewById(R.id.descriptionTextView);
//        toolBarLayout.setOnHoverListener(new View.OnHoverListener() {
//            @Override
//            public boolean onHover(View view, MotionEvent motionEvent) {
//                System.out.println(">>>HOVERED!!!");
//                return false;
//            }
//        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("RestrictedApi")
//            @Override
//            public void onClick(View view) {
//                System.out.println(toolbar.isInLayout());
//                System.out.println(toolbar.isTitleTruncated());
//                System.out.println(toolBarLayout.isLaidOut());
////                System.out.println(this.set);
//            }
//        });
    }
}