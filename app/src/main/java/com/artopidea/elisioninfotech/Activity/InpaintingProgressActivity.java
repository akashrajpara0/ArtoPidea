package com.artopidea.elisioninfotech.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.artopidea.elisioninfotech.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class InpaintingProgressActivity extends AppCompatActivity {

    ImageView close_image;
    ImageView animation_progress;
    LinearProgressIndicator linearProgessbar;
    Uri imageUri1;
    Uri imageUri2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpainting_progress);

        idBinding();

        Glide.with(this).load(R.drawable.animation_progress).into(animation_progress);

        imageUri1 = getIntent().getParcelableExtra("IMAGE_URI_KEY1");
        imageUri2 = getIntent().getParcelableExtra("IMAGE_URI_KEY2");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InpaintingProgressActivity.this,
                        InpaintingDownloadActivity.class);
                intent.putExtra("IMAGE_URI_KEY1", imageUri1);
                intent.putExtra("IMAGE_URI_KEY2", imageUri2);
                startActivity(intent);
            }
        }, 4000);

        close_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void idBinding() {
        close_image = (ImageView) findViewById(R.id.close_image);
        animation_progress = (ImageView) findViewById(R.id.animation_progress);
        linearProgessbar = (LinearProgressIndicator) findViewById(R.id.linearProgessbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}