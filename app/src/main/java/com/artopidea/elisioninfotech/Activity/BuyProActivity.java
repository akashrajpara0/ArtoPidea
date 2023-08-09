package com.artopidea.elisioninfotech.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.artopidea.elisioninfotech.R;

public class BuyProActivity extends AppCompatActivity {

    LinearLayout layout_year;
    LinearLayout layout_week;
    ImageView image_year;
    ImageView image_week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pro);

        findViewById(R.id.close_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();

        layout_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelected();
                layout_year.setBackgroundResource(R.drawable.bg_1);
                image_year.setImageResource(R.drawable.buy_now_selected);
            }
        });

        layout_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelected();
                layout_week.setBackgroundResource(R.drawable.bg_1);
                image_week.setImageResource(R.drawable.buy_now_selected);
            }
        });
    }

    public void unSelected() {
        image_year.setImageResource(R.drawable.buy_now_unselected);
        image_week.setImageResource(R.drawable.buy_now_unselected);
        layout_year.setBackgroundResource(R.drawable.background_3);
        layout_week.setBackgroundResource(R.drawable.background_3);
    }

    private void idBinding() {
        layout_year = (LinearLayout) findViewById(R.id.layout_year);
        layout_week = (LinearLayout) findViewById(R.id.layout_week);
        image_year = (ImageView) findViewById(R.id.image_year);
        image_week = (ImageView) findViewById(R.id.image_week);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}