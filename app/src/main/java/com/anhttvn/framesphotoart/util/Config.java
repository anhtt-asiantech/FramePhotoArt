package com.anhttvn.framesphotoart.util;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Config {

    public static ArrayList<String> mListPhoto = new ArrayList<>();
    public static ArrayList<String> mListIcon = new ArrayList<>();
    private String [] images;
    private Context mContext;
    public Config(Context context){
        mContext = context;
    }
    /**
     * get photo from asset
     */
    public ArrayList<String> getPhoto(){
        try {
            images =mContext.getAssets().list("image");
            mListPhoto = new ArrayList<String>(Arrays.asList(images));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mListPhoto;

    }

    public ArrayList<String> getIcon(){
        try {
            images = mContext.getAssets().list("icon");
            mListIcon = new ArrayList<String>(Arrays.asList(images));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  mListIcon;
    }
}
