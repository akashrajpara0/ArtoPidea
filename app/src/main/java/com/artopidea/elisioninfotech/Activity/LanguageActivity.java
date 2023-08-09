package com.artopidea.elisioninfotech.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.artopidea.elisioninfotech.R;

public class LanguageActivity extends AppCompatActivity {

    LinearLayout language_1_english;
    LinearLayout language_2_hindi;
    LinearLayout language_3_spanish;
    LinearLayout language_4_portuguese;
    LinearLayout language_5_korean;

    ImageView language_1_english_img;
    ImageView language_2_hindi_img;
    ImageView language_3_spanish_img;
    ImageView language_4_portuguese_img;
    ImageView language_5_korean_img;

    ImageView save_language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();

        language_1_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelected();
                language_1_english_img.setImageResource(R.drawable.language_round_selected);
            }
        });

        language_2_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelected();
                language_2_hindi_img.setImageResource(R.drawable.language_round_selected);
            }
        });

        language_3_spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelected();
                language_3_spanish_img.setImageResource(R.drawable.language_round_selected);
            }
        });

        language_4_portuguese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelected();
                language_4_portuguese_img.setImageResource(R.drawable.language_round_selected);
            }
        });

        language_5_korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelected();
                language_5_korean_img.setImageResource(R.drawable.language_round_selected);
            }
        });

        save_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void unSelected() {
        language_1_english_img.setImageResource(R.drawable.language_round_unselected);
        language_2_hindi_img.setImageResource(R.drawable.language_round_unselected);
        language_3_spanish_img.setImageResource(R.drawable.language_round_unselected);
        language_4_portuguese_img.setImageResource(R.drawable.language_round_unselected);
        language_5_korean_img.setImageResource(R.drawable.language_round_unselected);
    }

    private void idBinding() {
        language_1_english = (LinearLayout) findViewById(R.id.language_1_english);
        language_2_hindi = (LinearLayout) findViewById(R.id.language_2_hindi);
        language_3_spanish = (LinearLayout) findViewById(R.id.language_3_spanish);
        language_4_portuguese = (LinearLayout) findViewById(R.id.language_4_portuguese);
        language_5_korean = (LinearLayout) findViewById(R.id.language_5_korean);
        language_1_english_img = (ImageView) findViewById(R.id.language_1_english_img);
        language_2_hindi_img = (ImageView) findViewById(R.id.language_2_hindi_img);
        language_3_spanish_img = (ImageView) findViewById(R.id.language_3_spanish_img);
        language_4_portuguese_img = (ImageView) findViewById(R.id.language_4_portuguese_img);
        language_5_korean_img = (ImageView) findViewById(R.id.language_5_korean_img);
        save_language = (ImageView) findViewById(R.id.save_language);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}