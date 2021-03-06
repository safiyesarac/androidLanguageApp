package com.example.tre;



import android.content.Context;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class AndroidImageAdapter extends PagerAdapter {
    Context mContext;

    AndroidImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    private int[] sliderImagesId = new int[]{
            R.drawable.homepage,R.drawable.homepage,R.drawable.homepage,R.drawable.homepage,R.drawable.homepage
    };

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(sliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}
