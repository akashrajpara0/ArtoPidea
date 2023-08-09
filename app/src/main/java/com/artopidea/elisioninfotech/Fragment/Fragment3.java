package com.artopidea.elisioninfotech.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.artopidea.elisioninfotech.Activity.BuyProActivity;
import com.artopidea.elisioninfotech.Activity.LanguageActivity;
import com.artopidea.elisioninfotech.Activity.SettingGeneralActivity;
import com.artopidea.elisioninfotech.Activity.SignInActivity;
import com.artopidea.elisioninfotech.Activity.SplashActivity;
import com.artopidea.elisioninfotech.R;
import com.artopidea.elisioninfotech.Utils.AppPrefrence;
import com.artopidea.elisioninfotech.Utils.FirebaseUtil;
import com.bumptech.glide.Glide;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment3 extends Fragment {

    LinearLayout setting_1_img_language;
    LinearLayout setting_2_img_general;
    LinearLayout setting_3_img_logout_account;
    LinearLayout setting_4_img_delete_account;

    CircleImageView profile_image;
    ImageView sign_in_image;
    ImageView banner_buy_pro;
    TextView user_name_text;
    AppPrefrence appPrefrence;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        idBinding(view);

        appPrefrence = new AppPrefrence(getActivity());

        if (appPrefrence.get_user_isLogin().equals("1")) {
            sign_in_image.setVisibility(View.INVISIBLE);
            setting_3_img_logout_account.setVisibility(View.VISIBLE);
            setting_4_img_delete_account.setVisibility(View.VISIBLE);
        } else {
            sign_in_image.setVisibility(View.VISIBLE);
            setting_3_img_logout_account.setVisibility(View.GONE);
            setting_4_img_delete_account.setVisibility(View.GONE);
        }
        user_name_text.setText(appPrefrence.get_user_name());

        Glide.with(this)
                .load(new File(appPrefrence.get_user_profile()))
                .into(profile_image);

        sign_in_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        SignInActivity.class));
            }
        });

        banner_buy_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        BuyProActivity.class));
            }
        });

        setting_1_img_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        LanguageActivity.class));
            }
        });

        setting_2_img_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        SettingGeneralActivity.class));
            }
        });

        setting_3_img_logout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUtil.logout();
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return view;
    }

    public void idBinding(View view) {
        setting_1_img_language = (LinearLayout) view.findViewById(R.id.setting_1_img_language);
        setting_2_img_general = (LinearLayout) view.findViewById(R.id.setting_2_img_general);
        setting_3_img_logout_account = (LinearLayout) view.findViewById(R.id.setting_3_img_logout_account);
        setting_4_img_delete_account = (LinearLayout) view.findViewById(R.id.setting_4_img_delete_account);
        sign_in_image = (ImageView) view.findViewById(R.id.sign_in_image);
        banner_buy_pro = (ImageView) view.findViewById(R.id.banner_buy_pro);
        user_name_text = (TextView) view.findViewById(R.id.user_name_text);
        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
    }
}