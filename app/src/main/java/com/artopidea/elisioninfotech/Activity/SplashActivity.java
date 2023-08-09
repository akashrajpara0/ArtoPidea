package com.artopidea.elisioninfotech.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.artopidea.elisioninfotech.R;
import com.artopidea.elisioninfotech.Utils.AppPrefrence;
import com.artopidea.elisioninfotech.Utils.FirebaseUtil;

import org.checkerframework.checker.units.qual.A;

public class SplashActivity extends AppCompatActivity {

    AppPrefrence appPrefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appPrefrence = new AppPrefrence(SplashActivity.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (appPrefrence.get_user_isLogin().equals("1")) {
                    startActivity(new Intent(SplashActivity.this, DashBoardActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                }
                finish();
            }
        }, 2000);
    }

}