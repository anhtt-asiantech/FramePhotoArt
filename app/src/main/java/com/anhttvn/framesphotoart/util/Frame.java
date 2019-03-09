package com.anhttvn.framesphotoart.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Frame {

    //filename of frame
    private String mFrameName;

    //Rect of picture area in frame
    private final Rect mPictureRect;

    //degree of rotation to fit picture and frame.
    private final float mRorate;

    public Frame(String frameName,int left, int top, int right, int bottom, float rorate) {
        mFrameName = frameName;
        mPictureRect = new Rect(left, top, right, bottom);
        mRorate = rorate;
    }
    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        Log.d("anhtt ","dd :" +filePath);
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }

        return bitmap;
    }
    public Bitmap mergeWith(Context context, Bitmap pictureBitmap) {
        Bitmap frameBitmap = getBitmapFromAsset(context, mFrameName);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(frameBitmap.getWidth(), frameBitmap.getHeight(), conf);
        Canvas canvas = new Canvas(bitmap);

        Matrix matrix = getMatrix(pictureBitmap);
        canvas.drawBitmap(pictureBitmap, matrix, null);

        canvas.drawBitmap(frameBitmap, 0, 0, null);

        return bitmap;

    }

    Matrix getMatrix(Bitmap pictureBitmap) {
        float widthRatio = mPictureRect.width() /  (float) pictureBitmap.getWidth();
        float heightRatio = mPictureRect.height() / (float) pictureBitmap.getHeight();

        float ratio;

        if (widthRatio > heightRatio) {
            ratio = widthRatio;

        } else {
            ratio = heightRatio;
        }

        float width = pictureBitmap.getWidth() * ratio;
        float height = pictureBitmap.getHeight() * ratio;
        float left = mPictureRect.left - (width - mPictureRect.width()) / 2f;
        float top = mPictureRect.top - (height - mPictureRect.height()) / 2f;

        Matrix matrix = new Matrix();
        matrix.postRotate(mRorate);
        matrix.postScale(ratio, ratio);
        matrix.postTranslate(left, top);

        return matrix;
    }
}