package com.artopidea.elisioninfotech.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.artopidea.elisioninfotech.R;

public class SettingActivity extends AppCompatActivity {

    LinearLayout setting_1_img_language;
    LinearLayout setting_2_img_general;
    LinearLayout setting_3_img_logout_account;
    LinearLayout setting_4_img_delete_account;

    ImageView sign_in_image;
    ImageView banner_buy_pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        idBinding();

        sign_in_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,
                        SignInActivity.class));
            }
        });

        banner_buy_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,
                        BuyProActivity.class));
            }
        });

        setting_1_img_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,
                        LanguageActivity.class));
            }
        });

        setting_2_img_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,
                        SettingGeneralActivity.class));
            }
        });
    }

    private void idBinding() {
        setting_1_img_language = (LinearLayout) findViewById(R.id.setting_1_img_language);
        setting_2_img_general = (LinearLayout) findViewById(R.id.setting_2_img_general);
        setting_3_img_logout_account = (LinearLayout) findViewById(R.id.setting_3_img_logout_account);
        setting_4_img_delete_account = (LinearLayout) findViewById(R.id.setting_4_img_delete_account);
        sign_in_image = (ImageView) findViewById(R.id.sign_in_image);
        banner_buy_pro = (ImageView) findViewById(R.id.banner_buy_pro);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}