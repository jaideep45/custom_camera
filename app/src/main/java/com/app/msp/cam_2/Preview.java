package com.app.msp.cam_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Preview extends AppCompatActivity {

    private ImageView mImageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        String path = new String();

        Bundle bundle = getIntent().getBundleExtra("bundle");
        path = bundle.getString("path");
        if (path == null){
            finish();
        }
        if(this.getSupportActionBar()!=null){
            this.getSupportActionBar().hide();
        }

        mImageView = (ImageView) findViewById(R.id.image);
        mImageView.setImageBitmap(BitmapFactory.decodeFile(path));
        textView = (TextView) findViewById(R.id.texto);
        String t = new String("Name: " +bundle.getString("name")+"\n"+"Path: "+bundle.getString("path")+"\n"+"Time: "+bundle.getString("time"));
        textView.setText(t);
        textView.setTextColor(Color.WHITE);


    }
}
