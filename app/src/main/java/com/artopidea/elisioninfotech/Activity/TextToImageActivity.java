package com.artopidea.elisioninfotech.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.artopidea.elisioninfotech.Adapter.PromptsStyleAdapter;
import com.artopidea.elisioninfotech.Api.ApiService;
import com.artopidea.elisioninfotech.Utils.AppPrefrence;
import com.artopidea.elisioninfotech.Model.PromptsData;
import com.artopidea.elisioninfotech.Model.PromptsModel;
import com.artopidea.elisioninfotech.Model.PromptsStyleData;
import com.artopidea.elisioninfotech.Model.PromptsStyleModel;
import com.artopidea.elisioninfotech.R;
import com.artopidea.elisioninfotech.Api.RetrofitBuilder;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TextToImageActivity extends AppCompatActivity {

    ImageView get_premium_img;
    ImageView get_result_img;
    TextView get_inspired_text;
    EditText prompts_edit_text;
    RecyclerView prompts_style_rv;
    ProgressBar progressBar;

    PromptsStyleAdapter promptsStyleAdapter;
    ApiService create;
    private final ArrayList<PromptsData> listPrompts = new ArrayList<>();
    private final ArrayList<PromptsStyleData> listPromptsStyle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_image);

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();
        progressBar.setVisibility(View.VISIBLE);

        create = RetrofitBuilder.create(TextToImageActivity.this);
        create.getprompts("Bearer " + new AppPrefrence(this).get_token()).enqueue(new Callback<PromptsModel>() {
            @Override
            public void onFailure(Call<PromptsModel> call, Throwable th) {

            }

            @Override
            public void onResponse(Call<PromptsModel> call, Response<PromptsModel> response) {
                listPrompts.addAll(response.body().getData());
            }
        });

        create.getpromptsStyle("Bearer " + new AppPrefrence(this).get_token()).enqueue(new Callback<PromptsStyleModel>() {
            @Override
            public void onFailure(Call<PromptsStyleModel> call, Throwable th) {

            }

            @Override
            public void onResponse(Call<PromptsStyleModel> call, Response<PromptsStyleModel> response) {
                listPromptsStyle.addAll(response.body().getData());

                LinearLayoutManager layoutManager = new LinearLayoutManager(TextToImageActivity.this,
                        LinearLayoutManager.HORIZONTAL, false);
                prompts_style_rv.setLayoutManager(layoutManager);
                promptsStyleAdapter = new PromptsStyleAdapter(TextToImageActivity.this, listPromptsStyle);
                prompts_style_rv.setAdapter(promptsStyleAdapter);
                progressBar.setVisibility(View.GONE);
            }
        });


        get_result_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(TextToImageActivity.this,
                        TextToImageProgressActivity.class);
                startActivity(newIntent);
            }
        });

        get_premium_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(TextToImageActivity.this,
                        BuyProActivity.class);
                startActivity(newIntent);
            }
        });

        get_inspired_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listPrompts != null) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(listPrompts.size());
                    prompts_edit_text.setText(listPrompts.get(randomIndex).getPrompt_text());
                }
            }
        });
    }

    private void idBinding() {
        get_premium_img = (ImageView) findViewById(R.id.get_premium_img);
        get_result_img = (ImageView) findViewById(R.id.get_result_img);
        get_inspired_text = (TextView) findViewById(R.id.get_inspired_text);
        prompts_edit_text = (EditText) findViewById(R.id.prompts_edit_text);
        prompts_style_rv = (RecyclerView) findViewById(R.id.prompts_style_rv);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}