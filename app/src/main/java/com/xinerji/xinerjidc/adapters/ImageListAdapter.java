package com.xinerji.xinerjidc.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.activity.FragmentActivity;
import com.xinerji.xinerjidc.fragment.ImageDetailFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ImageListAdapter extends BaseAdapter
{
    private Context context;
    private List<Bitmap> imgPic;
    public ImageListAdapter(Context c, List<Bitmap> thePic)
    {
        context = c;
        imgPic = thePic;
    }
    public int getCount() {
        if(imgPic != null)
            return imgPic.size();
        else
            return 0;
    }

    //---returns the ID of an item---
    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    //---returns an ImageView view---
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        Bitmap srcBmp = imgPic.get(position);
        Bitmap dstBmp;
        if (srcBmp.getWidth() >= srcBmp.getHeight()){

            dstBmp = Bitmap.createBitmap(
                    srcBmp,
                    srcBmp.getWidth()/2 - srcBmp.getHeight()/2,
                    0,
                    srcBmp.getHeight(),
                    srcBmp.getHeight()
            );

        }else{

            dstBmp = Bitmap.createBitmap(
                    srcBmp,
                    0,
                    srcBmp.getHeight()/2 - srcBmp.getWidth()/2,
                    srcBmp.getWidth(),
                    srcBmp.getWidth()
            );
        }


        imageView.setImageBitmap(dstBmp);
        imageView.setId(position);
        imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
        imageView.setFocusable(false);
        imageView.setFocusableInTouchMode(false);

/*
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,
                        "The favorite list would appear on clicking this icon",
                        Toast.LENGTH_LONG).show();

            ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, imageDetailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            }
        });
*/

        return imageView;
    }
}


