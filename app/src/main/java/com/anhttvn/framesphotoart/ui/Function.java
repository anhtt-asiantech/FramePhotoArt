package com.anhttvn.framesphotoart.ui;


import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;

import com.anhttvn.framesphotoart.util.Config;

import com.anhttvn.framesphotoart.util.StickerImageView;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class Function extends BaseActivity  {

    private AdView mAds_view;
    private ImageView imgFrame;
    private StickerImageView imgPhoto;
    private ArrayList<String> list = new ArrayList<>();
    private Config mConfig;


    RelativeLayout.LayoutParams parms;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        init();


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

    public void clickHide(View view){

    }



}
