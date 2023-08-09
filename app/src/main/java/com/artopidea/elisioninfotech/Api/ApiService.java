package com.artopidea.elisioninfotech.Api;

import com.artopidea.elisioninfotech.Model.LogInModel;
import com.artopidea.elisioninfotech.Model.PromptsModel;
import com.artopidea.elisioninfotech.Model.PromptsStyleModel;
import com.artopidea.elisioninfotech.Model.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("register_user")
    Call<Void> insertData(@Body RegisterModel data);

    @FormUrlEncoded
    @POST("login_user")
    Call<LogInModel> getregister(
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("firebase_user_id") String firebase_user_id
    );

    @POST("get_prompts")
    Call<PromptsModel> getprompts(@Header("Authorization") String str);

    @POST("get_style")
    Call<PromptsStyleModel> getpromptsStyle(@Header("Authorization") String str);

}