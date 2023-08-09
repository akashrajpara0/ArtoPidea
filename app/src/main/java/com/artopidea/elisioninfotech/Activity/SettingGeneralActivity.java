package com.artopidea.elisioninfotech.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.artopidea.elisioninfotech.R;

public class SettingGeneralActivity extends AppCompatActivity {

    LinearLayout setting_5_img_rate_us;
    LinearLayout setting_6_img_share_now;
    LinearLayout setting_7_img_feedback;
    LinearLayout setting_8_img_about_us;
    LinearLayout setting_9_img_privacy_policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_general);

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();
    }

    private void idBinding() {
        setting_5_img_rate_us = (LinearLayout) findViewById(R.id.setting_5_img_rate_us);
        setting_6_img_share_now = (LinearLayout) findViewById(R.id.setting_6_img_share_now);
        setting_7_img_feedback = (LinearLayout) findViewById(R.id.setting_7_img_feedback);
        setting_8_img_about_us = (LinearLayout) findViewById(R.id.setting_8_img_about_us);
        setting_9_img_privacy_policy = (LinearLayout) findViewById(R.id.setting_9_img_privacy_policy);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}