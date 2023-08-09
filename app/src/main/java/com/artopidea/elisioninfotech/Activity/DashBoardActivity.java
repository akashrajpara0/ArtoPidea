package com.artopidea.elisioninfotech.Activity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.READ_MEDIA_VIDEO;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.artopidea.elisioninfotech.Fragment.Fragment1;
import com.artopidea.elisioninfotech.Fragment.Fragment2;
import com.artopidea.elisioninfotech.Fragment.Fragment3;
import com.artopidea.elisioninfotech.Model.UserModel;
import com.artopidea.elisioninfotech.R;
import com.artopidea.elisioninfotech.Utils.FirebaseUtil;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class DashBoardActivity extends AppCompatActivity {

    private final int BELOW_ANDROID_13 = 101;
    private final int ABOVE_ANDROID_13 = 102;

    MeowBottomNavigation bottomNav;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        if (givePermissionGranted()) {
            moveOnScreen();
        } else {
            take_Permission();
        }

        getUsername();

        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.add(new MeowBottomNavigation.Model(1, R.drawable.dashboard_bottom_1_selected));
        bottomNav.add(new MeowBottomNavigation.Model(2, R.drawable.dashboard_bottom_2_selected));
        bottomNav.add(new MeowBottomNavigation.Model(3, R.drawable.dashboard_bottom_3_selected));

        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment = null;
                if (item.getId() == 1) {
                    fragment = new Fragment1();
                } else if (item.getId() == 2) {
                    fragment = new Fragment2();
                } else if (item.getId() == 3) {
                    fragment = new Fragment3();
                }

                loadFragment(fragment);
            }
        });

        bottomNav.show(2, true);

        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });

    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_container, fragment, null)
                .commit();
    }

    private void moveOnScreen() {

    }

    public boolean givePermissionGranted() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {

            int read_external_storage = ContextCompat.checkSelfPermission(DashBoardActivity.this, READ_EXTERNAL_STORAGE);

            return read_external_storage == PackageManager.PERMISSION_GRANTED;

        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {

            int read_media_images = ContextCompat.checkSelfPermission(DashBoardActivity.this, READ_MEDIA_IMAGES);
            int read_media_video = ContextCompat.checkSelfPermission(DashBoardActivity.this, READ_MEDIA_VIDEO);

            return read_media_images == PackageManager.PERMISSION_GRANTED
                    && read_media_video == PackageManager.PERMISSION_GRANTED;

        }

        return false;
    }

    public void getUsername() {
        if (FirebaseUtil.isLoggedIn()) {
            FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {
                        userModel = task.getResult().toObject(UserModel.class);
                        if (userModel != null) {
                            FirebaseUtil.userName = userModel.getUsername();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case BELOW_ANDROID_13:
                boolean gotPermission1 = grantResults.length > 0;

                for (int result : grantResults) {
                    gotPermission1 &= result == PackageManager.PERMISSION_GRANTED;
                }

                if (gotPermission1) {
                    moveOnScreen();
                } else {
                    Toast.makeText(DashBoardActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;

            case ABOVE_ANDROID_13:
                boolean gotPermission2 = grantResults.length > 0;

                for (int result : grantResults) {
                    gotPermission2 &= result == PackageManager.PERMISSION_GRANTED;
                }

                if (gotPermission2) {
                    moveOnScreen();
                } else {
                    Toast.makeText(DashBoardActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void take_Permission() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {

            ActivityCompat.requestPermissions(DashBoardActivity.this,
                    new String[]{READ_MEDIA_IMAGES, READ_MEDIA_VIDEO}, ABOVE_ANDROID_13);

        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {

            ActivityCompat.requestPermissions(DashBoardActivity.this,
                    new String[]{READ_EXTERNAL_STORAGE}, BELOW_ANDROID_13);
        }
    }

    private long BackPressedTime;

    @Override
    public void onBackPressed() {
        if (BackPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        BackPressedTime = System.currentTimeMillis();
    }
}