package com.artopidea.elisioninfotech.Api;

import android.app.Activity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static ApiService create(Activity activity) {
        return (ApiService) new Retrofit.Builder().baseUrl("https://artopidea.com/api/")
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiService.class);
    }
}