package com.anhttvn.framesphotoart.version_2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.version_2.ui.Function_v2;

public class Main_v2 extends BaseActivity {
    Button btn_start;
    private TextView tv_logo_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
        init();
    }
    private void init(){
        btn_start = findViewById(R.id.btn_start);
        tv_logo_name = findViewById(R.id.tv_logo_name);
        Typeface type = Typeface.createFromAsset(getAssets(),"font/Beyond Wonderland.ttf");
        tv_logo_name.setTypeface(type);
        btn_start.setTypeface(type);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Function_v2.class));
            }
        });
    }

}
