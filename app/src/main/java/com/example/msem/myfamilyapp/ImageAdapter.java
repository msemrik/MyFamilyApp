package com.example.msem.myfamilyapp;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private DisplayMetrics displayMetrics;

    public ImageAdapter(Context c, DisplayMetrics metrics) {
        mContext = c;
        displayMetrics = metrics;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(mThumbIds[position]);

            int width = imageView.getDrawable().getIntrinsicWidth();
            int height = imageView.getDrawable().getIntrinsicHeight();

            float aspect = height / width;
            if (aspect >= 1) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(new GridView.LayoutParams(params));
            } else {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(new GridView.LayoutParams(params));
                //imageView.getLayoutParams().height = (int) (displayMetrics.heightPixels / 4);
            }
            imageView.getLayoutParams().height = (int) (displayMetrics.heightPixels / 3);
            imageView.requestLayout();
            //imageView.setScaleType(ImageView.ScaleType.);
            //imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);


        //imageView.setLayoutParams(params);
        //
        //imageView.getLayoutParams().width = (int) (displayMetrics.widthPixels / 2);
        //imageView.getLayoutParams().width  = 10;
        return imageView;
    }

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

    // references to our images
    private Integer[] mThumbIds;

    public Integer[] getmThumbIds() {
        return mThumbIds;
    }

    public void setmThumbIds(Integer[] mThumbIds) {
        this.mThumbIds = mThumbIds;
    }
}
