package com.anhttvn.framesphotoart.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.anhttvn.framesphotoart.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<String> mListImages;
    private LayoutInflater mLayout;
    private int mPosition;
    private Onclick mOnclick;

    /**
     * custom adapter contrustor
     * @param context
     * @param listImage
     * @param position
     * @param click
     */
    public GridAdapter(Context context, ArrayList<String> listImage, int position,Onclick click){
        mContext = context;
        mListImages = listImage;
        mPosition = position;
        mOnclick = click;
        mLayout = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListImages.size();
    }

    @Override
    public Object getItem(int position) {
        return mListImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag()+"");
        switch (v.getId()){
            case R.id.item_img:
                mPosition = position;
                notifyDataSetChanged();
                mOnclick.onClick(mPosition);
                break;
        }
    }

    private class ViewHolder{
        private ImageView img_icon;
    }
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView ==null){
            convertView = mLayout.inflate(R.layout.custom_book_frame,null);
            holder = new ViewHolder();
            holder.img_icon =  convertView.findViewById(R.id.item_img);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        InputStream inputstream= null;
        try {
            inputstream = mContext.getAssets().open("image/"
                    +mListImages.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        holder.img_icon.setImageDrawable(drawable);
        holder.img_icon.setOnClickListener(this);
        holder.img_icon.setTag(position);
        return convertView;
    }
    public interface Onclick{
        void onClick(int position);
    }
}