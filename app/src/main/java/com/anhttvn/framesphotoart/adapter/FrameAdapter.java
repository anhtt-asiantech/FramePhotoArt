package com.anhttvn.framesphotoart.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.anhttvn.framesphotoart.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FrameAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private ArrayList<String> mListImages;
    private Context mContext;
    private int mViewShow =0;
    private OnSelectFrame onSelect;
    public FrameAdapter(ArrayList<String> list,Context context, OnSelectFrame on){
        mContext = context;
        mListImages = list;
        onSelect = on;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
           view = LayoutInflater.from(mContext).inflate(R.layout.item_frame_photo, parent, false);
         return new MyFrameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mListImages != null){

                InputStream inputstream= null;
                try {

                        inputstream = mContext.getAssets().open("image/"
                                +mListImages.get(position));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Drawable drawable = Drawable.createFromStream(inputstream, null);
                ((MyFrameViewHolder) holder).mImg_frame.setImageDrawable(drawable);
                ((MyFrameViewHolder) holder).mllClick.setOnClickListener(this);
                ((MyFrameViewHolder) holder).mllClick.setTag(position);

        }
    }

    @Override
    public int getItemViewType(int position) {
       return position;
    }

    @Override
    public int getItemCount() {
        return mListImages.size();
    }

    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag() +"");
        switch (v.getId()){
            case R.id.rl_photo:
                onSelect.onSelectFame(position);

        }
    }

    public class MyFrameViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout mllClick;
        public ImageView mImg_frame;
        public MyFrameViewHolder(View view){
            super(view);
            mllClick = view.findViewById(R.id.rl_photo);
            mImg_frame = view.findViewById(R.id.img_frame_photo);
        }
    }
    public interface OnSelectFrame{
        void onSelectFame(int position);

    }
}
