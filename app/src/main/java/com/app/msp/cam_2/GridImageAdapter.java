package com.app.msp.cam_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class GridImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Bitmap> mBitmaps;
    private ArrayList<ArrayList<String>> mImages;

    public GridImageAdapter(Context c, ArrayList<Bitmap> bitMaps, ArrayList<ArrayList<String>> images){
        mContext = c;
        mBitmaps = bitMaps;
        mImages = images;
    }

    @Override
    public int getCount() {
        return mBitmaps.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        final String imName = mImages.get(i).get(0);
        final String imPath = mImages.get(i).get(1);
        final String imTime = mImages.get(i).get(2);

        if (view == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) view;
            Log.e("Happened 9 9 9 9 9 99 9 9 9 9 9  9","ahsd asdasd asd asd asd as da sd");
        }
        imageView.setImageBitmap(mBitmaps.get(i));
        imageView.setClickable(Boolean.TRUE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("TAG","view clicked viewclicked");
                Intent intent = new Intent(mContext,Preview.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",imName);
                bundle.putString("path",imPath);
                bundle.putString("time",imTime);
                Log.e("Clicked",bundle.getString("name"));
                intent.putExtra("bundle",bundle);
                mContext.startActivity(intent);
            }
        });
        return imageView;
    }
}

