package com.artopidea.elisioninfotech.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.artopidea.elisioninfotech.Api.ApiController;
import com.artopidea.elisioninfotech.Utils.AppPrefrence;
import com.artopidea.elisioninfotech.R;
import com.artopidea.elisioninfotech.Model.LogInModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    ImageView password_show_image;
    EditText number_edit_text;
    TextView sign_in_text;
    TextView sign_up_text;
    TextView skip_text;
    ProgressBar progressBar;

    String userEmail = "";
    String userPhone = "";
    String userFirebaseUserId = "";
    AppPrefrence appPrefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        appPrefrence = new AppPrefrence(SignInActivity.this);

        SharedPreferences sharedPreferences = getSharedPreferences("fireBaseUID", MODE_PRIVATE);
        userFirebaseUserId = sharedPreferences.getString("fUID", "");

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();

        progressBar.setVisibility(View.GONE);

        sign_up_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        skip_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        sign_in_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                progressBar.setVisibility(View.VISIBLE);

                sign_in_text.setEnabled(false);
                sign_in_text.setBackgroundResource(R.drawable.bg_3);
                sign_in_text.setEnabled(false);
                loginData(userEmail, userPhone, userFirebaseUserId);
            }
        });

        findViewById(R.id.googleSignInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                progressBar.setVisibility(View.VISIBLE);

                sign_in_text.setEnabled(false);
                sign_in_text.setBackgroundResource(R.drawable.bg_3);
                sign_in_text.setEnabled(false);
                loginData(appPrefrence.get_user_email(), userPhone, userFirebaseUserId);
            }
        });

        number_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                userPhone = number_edit_text.getText().toString();
                if (android.util.Patterns.PHONE.matcher(number_edit_text.getText().toString()).matches()) {
                    sign_in_text.setEnabled(true);
                    sign_in_text.setBackgroundResource(R.drawable.bg_2);
                } else {
                    sign_in_text.setEnabled(false);
                    sign_in_text.setBackgroundResource(R.drawable.bg_3);
                }
            }
        });
    }

    private void loginData(String user_email, String user_phone, String user_firebase_user_id) {

        Call<LogInModel> call = ApiController.getInstance()
                .getapi()
                .getregister(user_email, user_phone, user_firebase_user_id);

        call.enqueue(new Callback<LogInModel>() {
            @Override
            public void onResponse(Call<LogInModel> call, Response<LogInModel> response) {
                LogInModel obj = response.body();

                if (obj.getStatus().equals("1")) {
                    appPrefrence.set_token(obj.getLogInData().getToken());
                    appPrefrence.set_user_isLogin("1");
                    Intent intent = new Intent(SignInActivity.this, DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    progressBar.setVisibility(View.GONE);
                    sign_in_text.setEnabled(true);
                    sign_in_text.setBackgroundResource(R.drawable.bg_2);
                    sign_in_text.setEnabled(true);
                    Toast.makeText(SignInActivity.this, "User Not Exits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LogInModel> call, Throwable t) {
                Log.e("Akash", "onResponse: ");
            }
        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void idBinding() {
        password_show_image = (ImageView) findViewById(R.id.password_show_image);
        number_edit_text = (EditText) findViewById(R.id.number_edit_text);
        sign_in_text = (TextView) findViewById(R.id.sign_in_text);
        sign_up_text = (TextView) findViewById(R.id.sign_up_text);
        skip_text = (TextView) findViewById(R.id.skip_text);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}