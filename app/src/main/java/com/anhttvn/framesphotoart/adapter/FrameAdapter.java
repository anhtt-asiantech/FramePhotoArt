package com.anhttvn.framesphotoart.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


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
    public FrameAdapter(ArrayList<String> list,Context context){
        mContext = context;
        mListImages = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(mViewShow == 1){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_quangcao, parent, false);
            return new QuangCaoViewholder(view);
        }else{
            view = LayoutInflater.from(mContext).inflate(R.layout.item_frame_photo, parent, false);
            return new MyFrameViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mListImages != null){
            if(mViewShow == 1){
                AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice("D7F715D75F72FD215E30ED47E4B8A56C").build();
                ((QuangCaoViewholder) holder).adview_banner.loadAd(adRequest);

            }else{
                InputStream inputstream= null;
                try {
                    inputstream = mContext.getAssets().open("image/"
                            +mListImages.get(position));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Drawable drawable = Drawable.createFromStream(inputstream, null);
                ((MyFrameViewHolder) holder).mImg_frame.setImageDrawable(drawable);
                ((MyFrameViewHolder) holder).mCardView.setOnClickListener(this);
                ((MyFrameViewHolder) holder).mCardView.setTag(position);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 2: case 9: case 16: case 25:
                return mViewShow = 1;
                default:
                 return mViewShow = 0;
        }
    }

    @Override
    public int getItemCount() {
        return mListImages.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class MyFrameViewHolder extends RecyclerView.ViewHolder{

        public CardView mCardView;
        public ImageView mImg_frame;
        public MyFrameViewHolder(View view){
            super(view);
            mCardView = view.findViewById(R.id.card_view);
            mImg_frame = view.findViewById(R.id.img_frame_photo);
        }
    }
    public  class QuangCaoViewholder extends RecyclerView.ViewHolder{
        public AdView adview_banner;
       // public AdSize adSize;
        public QuangCaoViewholder(View view){
            super(view);
           // adSize = new AdSize(250,200);
            adview_banner =  view.findViewById(R.id.adview_banner);
            //adview_banner.setAdSize(adSize);

        }
    }
}
