package com.anhttvn.framesphotoart.ui;


import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;

import com.anhttvn.framesphotoart.util.Config;

import com.anhttvn.framesphotoart.util.StickerImageView;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Function extends BaseActivity  {

    private AdView mAds_view;
    private ImageView imgFrame;
    private StickerImageView imgPhoto;
    private ArrayList<String> list = new ArrayList<>();
    private Config mConfig;

    // value item
    private int mPosition = 0;
    // value photo
    private Bitmap bmp;

    RelativeLayout.LayoutParams parms;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        init();
        viewItem();
        initShowPhoto();
    }
    /**
     * init view layout
     */
    private void init(){
        mAds_view = findViewById(R.id.ads_function);
        imgPhoto = findViewById(R.id.img_photo);
        imgFrame = findViewById(R.id.img_frame);
        mConfig = new Config(this);
        mConfig.getPhoto();
        list = mConfig.mListPhoto;
    }
    private void viewItem(){

        InputStream inputstream= null;
        try {
            inputstream = this.getAssets().open("image/"
                    +list.get(mPosition));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        imgFrame.setImageDrawable(drawable);
        bmp = ((BitmapDrawable)drawable).getBitmap();
    }

    public void initShowPhoto(){
        imgPhoto.setImageResource(R.drawable.a);
        Point point = new Point();
        point.set(bmp.getWidth(),bmp.getHeight());
        getTransparentCenter(bmp,point);
    }

    private PointF getTransparentCenter(Bitmap bitmap,Point viewSize){
        List<Point> transparentPonit = new ArrayList<>();
        for(int i= 0 ;i<bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                int pixel = bitmap.getPixel(i, j);
                if ((pixel & 0xff000000) == 0) {
                    transparentPonit.add(new Point(i, j));
                }
            }
        }
        int totalX = 0;
        int totalY = 0;
        for(Point transparent : transparentPonit){
            totalX += transparent.x;
            totalY += transparent.y;
        }
        float centerX = (float) totalX/transparentPonit.size();
        float centerY = (float) totalY/transparentPonit.size();
        float x = viewSize.x * centerX / bitmap.getWidth();
        float y = viewSize.y *centerY / bitmap.getHeight();
        imgPhoto.setX(x);
        imgPhoto.setY(y);

        return new PointF(x,y);
    }
    public void clickHide(View view){

    }



}
