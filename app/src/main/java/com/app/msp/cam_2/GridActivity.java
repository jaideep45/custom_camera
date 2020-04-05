package com.app.msp.cam_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    private GridImageAdapter gridImageAdapter;
    private ArrayList<ArrayList<String>> imageList;
    private ArrayList<Bitmap> bitmapsList;
    private boolean noImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        noImages = new Boolean(Boolean.FALSE);
        bitmapsList = new ArrayList<Bitmap>();
        imageList = new  ArrayList<ArrayList<String>>();

        gridView = (GridView) findViewById(R.id.grid_view);

        fetchImages();
        populateImages();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(),CameraActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void fetchImages(){
        bitmapsList = new ArrayList<Bitmap>();
        imageList = new  ArrayList<ArrayList<String>>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        File imageDir = new File(Environment.getExternalStorageDirectory().toString()+
                "/camera_app");
        if((imageDir.exists()))
        {
            File[] mediaFiles = imageDir.listFiles();
            if (mediaFiles == null ){
                noImages = Boolean.TRUE;
                return;
            }
            else {
                for (File file : mediaFiles) {
                    Bitmap t_bitmap = convertToBitmap(file);
                    if (t_bitmap != null) {
                        bitmapsList.add(t_bitmap);
                        ArrayList<String> details = new ArrayList<String>();
                        details.add(readFileName(file));
                        details.add(file.getAbsolutePath());
                        details.add(dateFormat.format(file.lastModified()));
                        imageList.add(details);
                    }
                }
            }//for
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED
//                    , Uri.parse(Environment.getExternalStorageDirectory().toString()+"/camera_app")));
        }//if
        else
        {
            imageDir.mkdirs();
        }

    }

    private void populateImages(){
        if (noImages == Boolean.TRUE){
            gridView.setVisibility(View.GONE);
        }
        gridImageAdapter = new GridImageAdapter(getApplicationContext(), bitmapsList,imageList);
        gridView.setAdapter(gridImageAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
////                Intent intent = new Intent(getApplicationContext(),Preview.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("name",imageList.get(position).get(0));
//                bundle.putString("path",imageList.get(position).get(1));
//                bundle.putString("time",imageList.get(position).get(2));
//                Log.e("Clicked",bundle.getString("name"));
//
////                intent.putExtra("EXTRA",bundle);
////                startActivity(intent);
//
//            }
//        });
        Log.e("GridView","Done Gridview");
    }

    public static Bitmap convertToBitmap(File file)
    {
        String path="";
        try
        {
            path = file.getPath();
        } catch (Exception e1)
        {}

        Bitmap bmp = null;
        try
        {
            bmp = BitmapFactory.decodeFile(path);

        }catch(OutOfMemoryError e)
        {
            return null;
        }

        return Bitmap.createScaledBitmap(bmp,100,100,true);
    }

    public String readFileName(File file){
        String name = file.getName();
        return name;
    }

}


