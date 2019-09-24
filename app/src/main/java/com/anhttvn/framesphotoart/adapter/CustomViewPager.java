package com.anhttvn.framesphotoart.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.model.Photo;

import java.util.List;

public class CustomViewPager extends PagerAdapter implements View.OnClickListener{
    private Context mContext;
    private List<Photo> mList;
    private Onclick mOnclick;
    public CustomViewPager(Context context, List<Photo> list, Onclick onclick){
        mContext = context;
        mList = list;
        mOnclick = onclick;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        Button img_delete = itemView.findViewById(R.id.img_delete);
        Button img_share = itemView.findViewById(R.id.img_share_detail);
        Bitmap image = mList.get(position).getImage();
        imageView.setImageBitmap(image);
        container.addView(itemView);

        img_delete.setOnClickListener(this);
        img_share.setOnClickListener(this);
        img_delete.setTag(position);
        img_share.setTag(position);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag() + "");
        switch (v.getId()){
            case R.id.img_delete:
                mOnclick.clickDelete(position);
                break;
            case R.id.img_share_detail:
                mOnclick.clickSharre(position);
                break;
        }
    }
    public interface Onclick{
        void clickDelete(int position);
        void clickSharre(int position);
    }
}

