package com.anhttvn.framesphotoart.version_2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.util.ChangeFont;
import com.google.android.gms.ads.AdView;
import com.google.android.material.button.MaterialButton;

public class Function_v2 extends BaseActivity {

    private MaterialButton btn_camera,btn_folder,btn_setting;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_v2);
        init();
    }
    private void init(){
        btn_camera = findViewById(R.id.btn_camera);
        btn_folder = findViewById(R.id.btn_folder);
        btn_setting = findViewById(R.id.btn_setting);

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhotoArt.class);
                intent.putExtra("keyValue",1);
                startActivity(intent);
            }
        });
        // folder
        btn_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhotoArt.class);
                intent.putExtra("keyValue",2);
                startActivity(intent);
            }
        });
        //album
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Setting_v2.class);
                intent.putExtra("keyValue",3);
                startActivity(intent);
            }
        });
    }

}
