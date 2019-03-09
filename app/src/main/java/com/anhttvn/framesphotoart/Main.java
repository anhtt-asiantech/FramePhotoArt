package com.anhttvn.framesphotoart;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anhttvn.framesphotoart.ui.Album;
import com.anhttvn.framesphotoart.ui.Demo;
import com.anhttvn.framesphotoart.ui.Function;
import com.anhttvn.framesphotoart.ui.Setting;


public class Main extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void clicStart(View view){
        startActivity(new Intent(this,Function.class));
    }
    public void clickAlbum(View view){
        startActivity(new Intent(this,Demo.class));
    }
    public void clickSetting(View view){
        startActivity(new Intent(this,Setting.class));
    }

}
