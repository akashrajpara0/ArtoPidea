package com.artopidea.elisioninfotech.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.artopidea.elisioninfotech.R;

public class RemoveTextDownloadActivity extends AppCompatActivity {

    TextView remove_watermark_text;
    TextView publish_now_text;
    TextView download_now_text;
    ImageView imageview_1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_text_download);

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();

        imageUri = getIntent().getParcelableExtra("IMAGE_URI_KEY");
        imageview_1.setImageURI(imageUri);

        remove_watermark_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeWatermarkDialog();
            }
        });

        download_now_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RemoveTextDownloadActivity.this,
                        DownloadSucessfullActivity.class));
            }
        });
    }

    public void removeWatermarkDialog() {
        Dialog myDialog = new Dialog(this);
        myDialog.requestWindowFeature(1);
        myDialog.setContentView(R.layout.dialog_remove_watermark);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        myDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        myDialog.show();

        TextView remove_ad_text = (TextView) myDialog.findViewById(R.id.remove_ad_text);
        TextView watch_ad_text = (TextView) myDialog.findViewById(R.id.watch_ad_text);

        remove_ad_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        watch_ad_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

    }

    private void idBinding() {
        remove_watermark_text = (TextView) findViewById(R.id.remove_watermark_text);
        publish_now_text = (TextView) findViewById(R.id.publish_now_text);
        download_now_text = (TextView) findViewById(R.id.download_now_text);
        imageview_1 = (ImageView) findViewById(R.id.imageview_1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}