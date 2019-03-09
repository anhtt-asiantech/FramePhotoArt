package com.anhttvn.framesphotoart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.adapter.GridAdapter;
import com.anhttvn.framesphotoart.util.Config;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class IconSelect extends BaseActivity implements GridAdapter.Onclick {

    private GridView mGridView;
    private GridAdapter mAdapter;
    private ArrayList<String> mList = new ArrayList<>();
    private Config mConfig;
    private int mPosition = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame_adapter);
        init();
    }
    /**
     * init view
     */
    private void init(){
        mGridView = findViewById(R.id.grid_quainon);

    }
    private void initData(){
        mConfig = new Config(this);
        mConfig.getPhoto();
        mList = mConfig.mListIcon;
    }
    private void adapterPhoto(){
        mAdapter = new GridAdapter(this,mList,1,this);
        mGridView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
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
        intent.putExtra("icon",position);
        sendBroadcast(intent);
    }
}
