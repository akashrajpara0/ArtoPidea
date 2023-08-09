package com.artopidea.elisioninfotech.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.artopidea.elisioninfotech.ApiService;
import com.artopidea.elisioninfotech.Model.RegisterModel;
import com.artopidea.elisioninfotech.Model.UserModel;
import com.artopidea.elisioninfotech.R;
import com.artopidea.elisioninfotech.Utils.AndroidUtil;
import com.artopidea.elisioninfotech.Utils.FirebaseUtil;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity implements TextWatcher {

    ImageView password_show_image;
    EditText number_edit_text;
    EditText otp_edit_text;
    EditText user_name_edit_text;
    TextView sign_up_text;
    TextView sign_in_text;

    TextView verify_otp_text;
    TextView skip_text;
    TextView resend_code_text;
    ProgressBar progressBar;
    CircleImageView profile_image;

    String myValue = "off";

    private long timeLeftInMillis = 60000;
    Long timeoutSeconds = 60L;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    UserModel userModel;
    CountDownTimer countDownTimer;
    GoogleSignInClient client;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();
        resend_code_text.setEnabled(false);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))  // Add your Web client ID from Firebase Console
                .requestEmail()
                .build();

        client = GoogleSignIn.getClient(this, options);

        findViewById(R.id.googleSignInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_name_edit_text.getText().length() > 3) {
                    Intent signInIntent = client.getSignInIntent();
                    startActivityForResult(signInIntent, 1234);
                } else {
                    Toast.makeText(SignUpActivity.this, "Enter valid user name !", Toast.LENGTH_SHORT).show();
                }
            }
        });


        password_show_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myValue.equals("off")) {
                    otp_edit_text.setTransformationMethod(null);
                    password_show_image.setImageResource(R.drawable.password_show_off_image);
                    myValue = "on";
                } else if (myValue.equals("on")) {
                    otp_edit_text.setTransformationMethod(new PasswordTransformationMethod());
                    password_show_image.setImageResource(R.drawable.password_show_on_image);
                    myValue = "off";
                }
            }
        });


        skip_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, DashBoardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        sign_in_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });

        verify_otp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtp("+91" + number_edit_text.getText().toString(), false);
            }
        });

        sign_up_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countDownTimer.cancel();
                timeLeftInMillis = 0;
                resend_code_text.setText("Resend Code");
                resend_code_text.setEnabled(false);

                progressBar.setVisibility(View.VISIBLE);
                sign_up_text.setEnabled(false);
                sign_up_text.setBackgroundResource(R.drawable.bg_3);

                String enterOtp = otp_edit_text.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, enterOtp);
                signIn(credential);
            }
        });

        resend_code_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtp("+91" + number_edit_text.getText().toString(), true);
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);
    }

    public void sendOtp(String phoneNumber, boolean isResend) {
        progressBar.setVisibility(View.VISIBLE);
        sendInProgress(true);

        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signIn(phoneAuthCredential);
                        sendInProgress(false);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        AndroidUtil.showToast(getApplicationContext(), "OTP Verifaction failed");
                        Log.e("Akash", "onVerificationFailed: " + e.getMessage().toString());
                        sendInProgress(false);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        resendingToken = forceResendingToken;
                        AndroidUtil.showToast(getApplicationContext(), "OTP sent successfully");

                        progressBar.setVisibility(View.INVISIBLE);
                        verify_otp_text.setEnabled(false);
                        verify_otp_text.setBackgroundResource(R.drawable.bg_3);
                        verify_otp_text.setVisibility(View.GONE);

                        otp_edit_text.setEnabled(true);
                        sign_up_text.setEnabled(false);
                        sign_up_text.setBackgroundResource(R.drawable.bg_3);
                        sign_up_text.setVisibility(View.VISIBLE);

                        startResendTimer();
                    }
                });

        if (isResend) {
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }

    public void setUsername() {
        String username = user_name_edit_text.getText().toString();
        String usernumber = "+91" + number_edit_text.getText().toString();

        if (username.isEmpty() || username.length() < 3) {
            user_name_edit_text.setError("Username length should be at least 3 chars");
            return;
        }

        if (userModel != null) {
            userModel.setUsername(username);
        } else {
            userModel = new UserModel(usernumber, username, Timestamp.now());
        }

        FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(SignUpActivity.this, DashBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }

    public void sendInProgress(boolean isProgress) {
        if (isProgress) {
            verify_otp_text.setEnabled(false);
            verify_otp_text.setBackgroundResource(R.drawable.bg_3);
        } else {
            verify_otp_text.setEnabled(true);
            verify_otp_text.setBackgroundResource(R.drawable.bg_2);
        }
    }

    public void signIn(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://artopidea.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    apiService = retrofit.create(ApiService.class);

                    RegisterModel data = new RegisterModel();

                    data.setName(user_name_edit_text.getText().toString());
                    data.setPhone(number_edit_text.getText().toString());
                    String firebaseUID = FirebaseUtil.currentUserId();
                    data.setFirebase_user_id(firebaseUID);

                    SharedPreferences sharedPreferences = getSharedPreferences("fireBaseUID", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("fUID", firebaseUID);
                    myEdit.apply();

                    Call<Void> call = apiService.insertData(data);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {

                                progressBar.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(SignUpActivity.this, DashBoardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                                // Data inserted successfully
                            } else {
                                // Handle API error
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Handle network failure
                        }
                    });
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    sign_up_text.setEnabled(true);
                    sign_up_text.setBackgroundResource(R.drawable.bg_2);
                    AndroidUtil.showToast(getApplicationContext(), "OTP verification failed");
                }
            }
        });
    }

    public void startResendTimer() {

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                resend_code_text.setText("Resend Code");
                resend_code_text.setEnabled(true);
                resend_code_text.setTextColor(Color.WHITE);
            }
        };
        countDownTimer.start();
    }

    private void updateTimerText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        resend_code_text.setText("Resend Code in " + seconds + " seconds");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();

                                    String userName = firebaseUser.getDisplayName();
                                    String userEmail = firebaseUser.getEmail();
                                    String firebaseUID = mAuth.getUid();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("https://artopidea.com/api/")
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    apiService = retrofit.create(ApiService.class);

                                    RegisterModel data = new RegisterModel();

                                    data.setEmail(userEmail);
                                    data.setName(user_name_edit_text.getText().toString());
                                    data.setFirebase_user_id(firebaseUID);

                                    SharedPreferences sharedPreferences = getSharedPreferences("fireBaseUID", MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                    myEdit.putString("fUID", firebaseUID);
                                    myEdit.apply();

                                    SharedPreferences sharedPreferences1 = getSharedPreferences("USER_EMAIL", MODE_PRIVATE);
                                    SharedPreferences.Editor myEdit1 = sharedPreferences1.edit();
                                    myEdit1.putString("userEmail", userEmail);
                                    myEdit1.apply();

                                    Call<Void> call = apiService.insertData(data);
                                    call.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            if (response.isSuccessful()) {
                                                // Data inserted successfully
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Intent intent = new Intent(SignUpActivity.this, DashBoardActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);

                                            } else {
                                                // Handle API error
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                            // Handle network failure
                                        }
                                    });
                                } else {
                                    Toast.makeText(SignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } catch (ApiException e) {
                Log.e("Akash", "onActivityResult: " + e.getMessage());
                // Handle Google Sign-In failure
            }
        }

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                profile_image.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.e("Akash", "firebaseAuthWithGoogle: " + user.getDisplayName());
                    } else {
                        Log.e("Akash", "faided: ");
                        // Handle Firebase authentication failure
                    }
                });
    }

    private void idBinding() {
        password_show_image = (ImageView) findViewById(R.id.password_show_image);
        number_edit_text = (EditText) findViewById(R.id.number_edit_text);
        otp_edit_text = (EditText) findViewById(R.id.otp_edit_text);
        user_name_edit_text = (EditText) findViewById(R.id.user_name_edit_text);
        sign_up_text = (TextView) findViewById(R.id.sign_up_text);
        sign_in_text = (TextView) findViewById(R.id.sign_in_text);
        verify_otp_text = (TextView) findViewById(R.id.verify_otp_text);
        skip_text = (TextView) findViewById(R.id.skip_text);
        resend_code_text = (TextView) findViewById(R.id.resend_code_text);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);

        number_edit_text.addTextChangedListener(this);
        otp_edit_text.addTextChangedListener(this);
        user_name_edit_text.addTextChangedListener(this);

        otp_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (otp_edit_text.getText().length() == 6) {
                    sign_up_text.setEnabled(true);
                    sign_up_text.setBackgroundResource(R.drawable.bg_2);
                } else {
                    sign_up_text.setEnabled(false);
                    sign_up_text.setBackgroundResource(R.drawable.bg_3);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        validateFields();
    }

    protected void validateFields() {
        if (user_name_edit_text.getText().length() > 3
                && number_edit_text.getText().length() > 0
                && number_edit_text.getText().length() < 11) {
            verify_otp_text.setEnabled(true);
            verify_otp_text.setBackgroundResource(R.drawable.bg_2);
        } else {
            verify_otp_text.setEnabled(false);
            verify_otp_text.setBackgroundResource(R.drawable.bg_3);
        }
    }
}