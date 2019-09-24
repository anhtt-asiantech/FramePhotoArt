package com.anhttvn.framesphotoart.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.anhttvn.framesphotoart.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class IconAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private ArrayList<String> mListImages;
    private Context mContext;
    private int mViewShow =0;
    private OnclickSelectIcon mClick;
    public IconAdapter(ArrayList<String> list, Context context,  OnclickSelectIcon click){
        mContext = context;
        mListImages = list;
        mClick = click;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_adapter_icon, parent, false);
        return new MyIconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mListImages != null){

            InputStream inputstream= null;
            try {
                    inputstream = mContext.getAssets().open("icon/"
                            +mListImages.get(position));

            } catch (IOException e) {
                e.printStackTrace();
            }
            Drawable drawable = Drawable.createFromStream(inputstream, null);
            ((MyIconViewHolder) holder).mImg_item.setImageDrawable(drawable);
            ((MyIconViewHolder) holder).mllClick.setOnClickListener(this);
            ((MyIconViewHolder) holder).mllClick.setTag(position);

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
            case R.id.click_icon:
                mClick.selectIcon(position);
                break;

        }
    }
    public class MyIconViewHolder extends RecyclerView.ViewHolder{

        public RelativeLayout mllClick;
        public ImageView mImg_item;
        public MyIconViewHolder(View view){
            super(view);
            mllClick = view.findViewById(R.id.click_icon);
            mImg_item = view.findViewById(R.id.icon_item);
        }
    }
    public interface OnclickSelectIcon{
        void selectIcon(int position);
    }
}
