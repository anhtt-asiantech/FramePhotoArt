package com.anhttvn.framesphotoart.version_2.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.anhttvn.framesphotoart.BaseActivity;
import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.adapter.CustomViewPager;
import com.anhttvn.framesphotoart.model.Photo;
import com.anhttvn.framesphotoart.util.ChangeFont;
import com.anhttvn.framesphotoart.util.ImageFileFilter;
import com.google.android.gms.ads.AdView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends BaseActivity implements CustomViewPager.Onclick{
    ViewPager mView_pager;
    TextView mtv_number;

    private Bitmap photo;
    private String path;

    private String mFile;

    private List<Photo> mList = new ArrayList<>();
    private CustomViewPager mAdapter;
    private int currentPage;
    private AdView ads_detail;
    private int mItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    void init(){
        mView_pager = findViewById(R.id.pager_view);
        mtv_number = findViewById(R.id.tv_number);
        ads_detail = findViewById(R.id.ads_detail);
        runAdview(ads_detail);
        getData();
        path = MediaStore.Images.Media.insertImage(getContentResolver(), photo,  "PhotoArt", "description");
        initAdapterViewPager();
        mItem = currentPage+1;
        mtv_number.setText(mItem + "/" + mList.size());
        mtv_number.setTypeface(new ChangeFont().fontIcon(this));
        eventViewPager();


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
    /**
     * viewpager
     */
    private void initAdapterViewPager(){
        if(mList != null){
            mAdapter = new CustomViewPager(this,mList,this);
            mView_pager.setAdapter(mAdapter);
            mView_pager.setCurrentItem(currentPage);
        }

    }

    private void eventViewPager(){
        mView_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mItem = position+1;
                mtv_number.setText(mItem +" / " + mList.size());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * getData
     */
    private void getData(){
        Intent i = getIntent();
        mFile = i.getStringExtra("path");
        currentPage = i.getIntExtra("position",0);
        photo = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("PhotoDetail"), 0, getIntent().getByteArrayExtra("PhotoDetail").length);
        mList = returnList();
    }
    public void deleteFiles(String path) {

        File file = new File(path);
        if (file.exists()) {
            String deleteCmd = "rm -r " + path;
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(deleteCmd);
            } catch (IOException e) {
            }
            getData();
            if(mList.size() == 0){
                finish();
            }else{
                initAdapterViewPager();
            }

        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void clickDelete(int position) {
        mFile = mList.get(position).getPath();
        currentPage = position;
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_delete_24dp)
                .setTitle(getResources().getString(R.string.text_delete))
                .setMessage(getResources().getString(R.string.text_ask_finish))
                .setPositiveButton(getResources().getString(R.string.text_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteFiles(mFile);

                    }
                }).setNegativeButton(getResources().getString(R.string.text_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();

    }

    @Override
    public void clickSharre(int position) {
        share(position);
    }

    private void share(int position){
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        mList.get(position).getImage().compress(Bitmap.CompressFormat.JPEG, 90, bs);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bs.toByteArray(),0,bs.toByteArray().length);

        path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,  "PhotoArt", "description");
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.parse(path);
        intent.putExtra(Intent.EXTRA_SUBJECT, "PhotoArt");
        //intent.putExtra(Intent.EXTRA_TEXT, this.getResources().getString(R.string.link_app));
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share Image"));
    }
}

