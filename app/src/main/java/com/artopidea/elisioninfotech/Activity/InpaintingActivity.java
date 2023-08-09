package com.artopidea.elisioninfotech.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.artopidea.elisioninfotech.R;

public class InpaintingActivity extends AppCompatActivity {

    ImageView upload_image;
    ImageView upload_mask_image;
    ImageView upload_image_small;
    ImageView upload_mask_image_small;
    TextView get_result_text;
    ImageView uploaded_image;
    ImageView uploaded_mask_image;
    CardView card_uploaded;
    CardView card_uploaded_mask;
    Uri imageUri1;
    Uri imageUri2;
    String myValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpainting);

        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        idBinding();

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myValue = "1";
                openGallery();
            }
        });

        upload_image_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myValue = "1";
                openGallery();
            }
        });

        upload_mask_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myValue = "2";
                openGallery();
            }
        });

        upload_mask_image_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myValue = "2";
                openGallery();
            }
        });

        get_result_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(InpaintingActivity.this,
                        InpaintingProgressActivity.class);
                newIntent.putExtra("IMAGE_URI_KEY1", imageUri1);
                newIntent.putExtra("IMAGE_URI_KEY2", imageUri2);
                startActivity(newIntent);
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                if (myValue.equals("1")) {
                    upload_image.setVisibility(View.GONE);
                    uploaded_image.setImageBitmap(bitmap);
                    card_uploaded.setVisibility(View.VISIBLE);

                    imageUri1 = data.getData();

                } else if (myValue.equals("2")) {
                    upload_mask_image.setVisibility(View.GONE);
                    uploaded_mask_image.setImageBitmap(bitmap);
                    card_uploaded_mask.setVisibility(View.VISIBLE);

                    imageUri2 = data.getData();

                    get_result_text.setEnabled(true);
                    get_result_text.setBackgroundResource(R.drawable.bg_2);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void idBinding() {
        upload_image = (ImageView) findViewById(R.id.upload_image);
        upload_mask_image = (ImageView) findViewById(R.id.upload_mask_image);
        upload_image_small = (ImageView) findViewById(R.id.upload_image_small);
        upload_mask_image_small = (ImageView) findViewById(R.id.upload_mask_image_small);
        get_result_text = (TextView) findViewById(R.id.get_result_text);
        uploaded_image = (ImageView) findViewById(R.id.uploaded_image);
        uploaded_mask_image = (ImageView) findViewById(R.id.uploaded_mask_image);
        card_uploaded = (CardView) findViewById(R.id.card_uploaded);
        card_uploaded_mask = (CardView) findViewById(R.id.card_uploaded_mask);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}