package com.anhttvn.framesphotoart.version_2.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.google.android.gms.ads.AdView;

public class Setting_v2 extends BaseActivity {
    private Button btn_album, btn_share, btn_review,btn_unistall;
    private TextView tv_setting;
    private AdView view_ads_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_v2);
        init();
        eventClick();
    }
    private void init(){
        btn_album = findViewById(R.id.btn_album);
        btn_share = findViewById(R.id.btn_share);
        btn_review = findViewById(R.id.btn_review);
        btn_unistall = findViewById(R.id.btn_unistall);
        tv_setting = findViewById(R.id.txt_setting);
        view_ads_setting = findViewById(R.id.view_ads_setting);
        runAdview(view_ads_setting);
        setFont();
    }
    private void setFont(){
        Typeface type = Typeface.createFromAsset(getAssets(),"font/Beyond Wonderland.ttf");
        btn_album.setTypeface(type);
        btn_unistall.setTypeface(type);
        btn_review.setTypeface(type);
        btn_share.setTypeface(type);
        tv_setting.setTypeface(type);
    }
    private void eventClick(){
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing App");
                i.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.anhttvn.framesphotoart");
                startActivity(Intent.createChooser(i, "Sharing App"));
            }
        });
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="https://play.google.com/store/apps/details?id=com.anhttvn.framesphotoart";
                initOpenUlr(url);
            }
        });
        btn_unistall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+getApplicationContext().getPackageName()));
                startActivity(intent);
            }
        });
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Album_v2.class);
                startActivity(intent);
            }
        });
    }
    private void initOpenUlr(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
