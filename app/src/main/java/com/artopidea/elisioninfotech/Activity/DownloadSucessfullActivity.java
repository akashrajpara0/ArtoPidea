package com.artopidea.elisioninfotech.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.artopidea.elisioninfotech.R;

public class DownloadSucessfullActivity extends AppCompatActivity {

    ImageView close_image;
    ImageView animation_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_sucessfull);

        idBinding();

        Glide.with(this).load(R.drawable.animation_download).into(animation_download);

        findViewById(R.id.close_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void idBinding() {
        close_image = (ImageView) findViewById(R.id.close_image);
        animation_download = (ImageView) findViewById(R.id.animation_download);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}