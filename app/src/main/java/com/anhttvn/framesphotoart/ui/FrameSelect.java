package com.anhttvn.framesphotoart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;

import com.anhttvn.framesphotoart.adapter.GridAdapter;

import com.anhttvn.framesphotoart.util.Config;

import java.util.ArrayList;



public class FrameSelect  extends BaseActivity  implements GridAdapter.Onclick{

    private GridView mGridView;
    private GridAdapter mAdapter;
    private ArrayList<String> mList = new ArrayList<>();
    private Config mConfig;
    private int mPosition = 0;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame_adapter);
        init();
        initData();
        adapterPhoto();
    }

    /**
     * init view
     */
    private void init(){
        mGridView = findViewById(R.id.grid_quainon);

    }

    /**
     * init data
     */
    private void initData(){
        mConfig = new Config(this);
        mConfig.getPhoto();
        mList = mConfig.mListPhoto;
    }

    private void adapterPhoto(){
        mAdapter = new GridAdapter(this,mList,1,this);
        mGridView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    public void clickBack(View view){
        finish();
    }

    @Override
    public void onClick(int position) {
        mPosition = position;
        sendData( mPosition);
        finish();
    }
    private void sendData(int position){
        Intent intent = new Intent();
        intent.setAction("com.anhttvn.framesphotoart");
        intent.putExtra("position",position);
        sendBroadcast(intent);
    }


}
