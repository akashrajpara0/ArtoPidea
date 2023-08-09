package com.artopidea.elisioninfotech.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artopidea.elisioninfotech.R;

public class ImageRemixActivity extends AppCompatActivity {

    ImageView upload_image;
    ImageView upload_image_small;
    TextView get_result_text;
    ImageView uploaded_image;
    CardView card_uploaded;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_remix);

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
                openGallery();
            }
        });

        upload_image_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        get_result_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(ImageRemixActivity.this,
                        ImageRemixProgressActivity.class);
                newIntent.putExtra("IMAGE_URI_KEY", imageUri);
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
                upload_image.setVisibility(View.GONE);
                uploaded_image.setImageBitmap(bitmap);
                card_uploaded.setVisibility(View.VISIBLE);
                get_result_text.setEnabled(true);
                get_result_text.setBackgroundResource(R.drawable.bg_2);

                imageUri = data.getData();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void idBinding() {
        upload_image = (ImageView) findViewById(R.id.upload_image);
        upload_image_small = (ImageView) findViewById(R.id.upload_image_small);
        get_result_text = (TextView) findViewById(R.id.get_result_text);
        uploaded_image = (ImageView) findViewById(R.id.uploaded_image);
        card_uploaded = (CardView) findViewById(R.id.card_uploaded);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}