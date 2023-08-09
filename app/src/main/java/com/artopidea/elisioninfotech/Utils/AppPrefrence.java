package com.artopidea.elisioninfotech.Utils;

import android.content.Context;
import android.content.SharedPreferences;


public class AppPrefrence {

    public static final String USER_PREFS = "USER PREFS";
    public SharedPreferences appSharedPref;
    public SharedPreferences.Editor prefEditor;

    public String token = "token";
    public String user_email = "user_email";
    public String user_name = "user_name";
    public String user_profile = "user_profile";
    public String user_isLogin = "user_isLogin";


    public AppPrefrence(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PREFS, 0);
        this.appSharedPref = sharedPreferences;
        this.prefEditor = sharedPreferences.edit();
    }


    public String get_token() {
        return this.appSharedPref.getString(this.token, " ");
    }

    public void set_token(String str) {
        this.prefEditor.putString(this.token, str).commit();
    }

    public String get_user_email() {
        return this.appSharedPref.getString(this.user_email, " ");
    }

    public void set_user_email(String str) {
        this.prefEditor.putString(this.user_email, str).commit();
    }

    public String get_user_name() {
        return this.appSharedPref.getString(this.user_name, " ");
    }

    public void set_user_name(String str) {
        this.prefEditor.putString(this.user_name, str).commit();
    }

    public String get_user_profile() {
        return this.appSharedPref.getString(this.user_profile, " ");
    }

    public void set_user_profile(String str) {
        this.prefEditor.putString(this.user_profile, str).commit();
    }

    public String get_user_isLogin() {
        return this.appSharedPref.getString(this.user_isLogin, " ");
    }

    public void set_user_isLogin(String str) {
        this.prefEditor.putString(this.user_isLogin, str).commit();
    }


}