package com.anhttvn.framesphotoart.ui;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.ImageView;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.util.Frame;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class Demo extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        init();
    }

    public void init(){
        String imagePath = "icon/logo_tta.png";
        AssetManager mngr = getAssets();
        InputStream is=null;
        try {
            is = mngr.open(imagePath);
        } catch (IOException e1) {  e1.printStackTrace();}

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(is);

        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
            }
        }
        Point point = new Point();
        point.set(bitmap.getWidth(),bitmap.getHeight());
        getTransparentCenter(bitmap,point);
        Frame frameA = new Frame("icon/b.png", 10, 93, 430, 409, 4);
        Bitmap mergedBitmap = frameA. mergeWith(this, bitmap);
        ImageView imageView = (ImageView) findViewById(R.id.img_demo);
        imageView.setImageBitmap(mergedBitmap);
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

        return new PointF(x,y);
    }
}
