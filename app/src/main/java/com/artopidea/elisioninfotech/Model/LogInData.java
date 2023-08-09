package com.artopidea.elisioninfotech.Model;

public class LogInData {

    String id;
    String name;
    String email;
    String phone;
    String otp;
    String firebase_user_id;
    String profile_img;
    String is_premium;
    String token;
    String fcm_id;
    String created_at;
    String updated_at;

    public LogInData() {
    }

    public LogInData(String id, String name, String email, String phone, String otp, String firebase_user_id, String profile_img, String is_premium, String token, String fcm_id, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.otp = otp;
        this.firebase_user_id = firebase_user_id;
        this.profile_img = profile_img;
        this.is_premium = is_premium;
        this.token = token;
        this.fcm_id = fcm_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getFirebase_user_id() {
        return firebase_user_id;
    }

    public void setFirebase_user_id(String firebase_user_id) {
        this.firebase_user_id = firebase_user_id;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(String is_premium) {
        this.is_premium = is_premium;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFcm_id() {
        return fcm_id;
    }

    public void setFcm_id(String fcm_id) {
        this.fcm_id = fcm_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
