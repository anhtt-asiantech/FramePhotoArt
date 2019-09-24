package com.anhttvn.framesphotoart.version_2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.google.android.gms.ads.AdView;

public class Function_v2 extends BaseActivity {

    private RelativeLayout rl_camera, rl_folder,rl_album;
    private AdView mAdsFunction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_v2);
        init();
    }
    private void init(){
        rl_camera = findViewById(R.id.camera);
        rl_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhotoArt.class);
                intent.putExtra("keyValue",1);
                startActivity(intent);
            }
        });
        // folder
        rl_folder = findViewById(R.id.folder);
        rl_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhotoArt.class);
                intent.putExtra("keyValue",2);
                startActivity(intent);
            }
        });
        //album
        rl_album = findViewById(R.id.album);
        rl_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Setting_v2.class);
                intent.putExtra("keyValue",3);
                startActivity(intent);
            }
        });
        mAdsFunction = findViewById(R.id.adsFunction);
        runAdview(mAdsFunction);
    }

}
