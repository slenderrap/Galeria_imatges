package com.project.galeria_imatges;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static int RC_PHOTO_PICKER = 0;
    ActivityResultLauncher<Intent> galerySomeActivityResultLauncher;
    ActivityResultLauncher<Intent> cameraSomeActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //afegim result per la galeria
        galerySomeActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            ImageView imageView = findViewById(R.id.imageView);
                            imageView.setImageURI(uri);
                        }
                    }
                });

        //fem boto per cridar a la galeria
        Button buttonGaleria = findViewById(R.id.buttonGalery);
        buttonGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create Intent
                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.getAction(getDataDir(new Uri ));
                intent.setType("image/jpg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                //Launch activity to get result
                galerySomeActivityResultLauncher.launch(intent);
            }
        });
        Button buttonCamera = findViewById(R.id.buttonCamera);
    //afegim result camera
        cameraSomeActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            ImageView imageView = findViewById(R.id.imageView);
                            imageView.setImageURI(uri);

                        }}

                }
        );
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create Intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Launch activity to get result
                cameraSomeActivityResultLauncher.launch(intent);
            }
                }
        );

    }
}


