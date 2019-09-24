package com.anhttvn.framesphotoart.version_2.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.adapter.AlbumAdapter;
import com.anhttvn.framesphotoart.model.Photo;
import com.anhttvn.framesphotoart.util.ChangeFont;
import com.anhttvn.framesphotoart.util.ImageFileFilter;
import com.google.android.gms.ads.AdView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Album_v2 extends BaseActivity implements AlbumAdapter.OnClickDetail{

    GridView mlistPhoto;
    TextView mTv_album;
    private AdView adview_album;
    private List<Photo> mList = new ArrayList<>();
    private AlbumAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        init();

    }
    private void init(){
        mlistPhoto = findViewById(R.id.gridView);
        mTv_album = findViewById(R.id.tv_album);
        adview_album = findViewById(R.id.adview_album);
        runAdview(adview_album);
        initAdapter();
    }
    private void initAdapter(){

        mList = returnList();
        if(mList != null){
            mAdapter = new AlbumAdapter(this,mList,this);
            mlistPhoto.setAdapter(mAdapter);
        }
        mTv_album.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
    }
    private List<Photo> returnList (){
        List<Photo> list = new ArrayList<>();
        File listFile= new File(android.os.Environment.getExternalStorageDirectory(),"PhotoArt");
        if (listFile.exists() && listFile.isDirectory()) {
            File[] files = listFile.listFiles(new ImageFileFilter());
            for (File file : files) {

                // Add the directories containing images or sub-directories
                if (file.isDirectory()
                        && file.listFiles(new ImageFileFilter()).length > 0) {

                    list.add(new Photo(file.getAbsolutePath(), true, null));
                }
                // Add the images
                else {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    list.add(new Photo(file.getAbsolutePath(), false, bitmap));
                }
            }
            return list;
        }else{
            list = null;
            return list;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        initAdapter();
        super.onResume();
    }

    @Override
    public void onclickDetail(int position) {
        Photo photo = mList.get(position);
        Intent intent = new Intent(this,PhotoActivity.class);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        photo.getImage().compress(Bitmap.CompressFormat.JPEG, 90, bs);
        intent.putExtra("PhotoDetail", bs.toByteArray());
        intent.putExtra("path", photo.getPath());
        intent.putExtra("position", position);
        startActivity(intent);
    }

}