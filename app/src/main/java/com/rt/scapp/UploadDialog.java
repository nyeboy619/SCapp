package com.rt.scapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;

public class UploadDialog extends Dialog {

    ProgressBar pb;
    MainActivity activity;
    Thread thread;

    public UploadDialog(@NonNull MainActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.upload_dialog);
        pb = findViewById(R.id.pb);
        pb.setMax(1000);
        thread = new Thread(){
            public void run(){
                for(int i = 0 ; i <= 1000 ; i++){
                    pb.setProgress(i);
                }
                interrupt();
                dismiss();
            }
        };
    }

    @Override
    public void show() {
        super.show();
        thread.start();

    }

}
