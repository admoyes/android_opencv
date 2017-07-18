package com.example.amoyes.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.net.Uri;
import java.io.InputStream;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!OpenCVLoader.initDebug()) {
            Log.e(this.getClass().getSimpleName(), " OpenCVLoader.initDebug(), not working.");
        }
        else {
            Log.e(this.getClass().getSimpleName(), " OpenCVLoader.initDebug(), working.");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == PICK_IMAGE) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // get the image
                Log.e(this.getClass().getSimpleName(), " extras is not null.");
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);

                    ImageView imageView;
                    imageView = (ImageView) findViewById(R.id.selected_image);
                    imageView.setImageBitmap(bitmap);
                }
                catch (Exception e) {
                    Log.e(this.getClass().getSimpleName(), e.getMessage());
                }
            }
            else {
                Log.e(this.getClass().getSimpleName(), " extras is null.");
            }
        }

    }

    public void selectImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
    }

    public void procesImage(View view) {

    }

}
