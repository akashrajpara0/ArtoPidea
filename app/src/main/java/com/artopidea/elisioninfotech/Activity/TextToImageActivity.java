package com.artopidea.elisioninfotech.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.artopidea.elisioninfotech.R;

public class TextToImageActivity extends AppCompatActivity {

    TextView get_result_text;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_image);

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();


        get_result_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(TextToImageActivity.this,
                        TextToImageProgressActivity.class);
                startActivity(newIntent);
            }
        });
    }

    private void idBinding() {
        get_result_text = (TextView) findViewById(R.id.get_result_text);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}