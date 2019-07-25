package com.rt.scapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    final int TAKE_PICTURE = 1;

    private ImageView imgidpic;
    private EditText etfn,etage,etbday,etadd,etidno,etdate;
    private Button btup;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgidpic = findViewById(R.id.imgidpic);

        etfn = findViewById(R.id.etfn);
        etage = findViewById(R.id.etage);
        etbday = findViewById(R.id.etbday);
        etadd = findViewById(R.id.etadd);
        etidno = findViewById(R.id.etidno);
        etadd = findViewById(R.id.etdi);

        btup = findViewById(R.id.btup);

        imgidpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        btup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadDialog dialog = new UploadDialog(MainActivity.this);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(getApplicationContext(),"Information uploaded successfully :)",Toast.LENGTH_LONG).show();
                        imgidpic.setImageResource(R.drawable.ic_camera);
                    }
                });
            }
        });


    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {

                    imgidpic.setImageBitmap( cropImage((Bitmap) data.getExtras().get("data")));
                }
        }
    }

    public Bitmap cropImage(Bitmap image){
        return Bitmap.createBitmap(image,0,0,image.getWidth(),image.getWidth());
    }

}
