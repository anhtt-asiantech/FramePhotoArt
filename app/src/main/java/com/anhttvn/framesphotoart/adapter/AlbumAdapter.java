package com.anhttvn.framesphotoart.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhttvn.framesphotoart.R;
import com.anhttvn.framesphotoart.model.Photo;

import java.util.List;

public class AlbumAdapter extends BaseAdapter implements View.OnClickListener{

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Photo> mList;
    private OnClickDetail onClick;
    public AlbumAdapter(Context context,List<Photo> list,OnClickDetail click){
        mContext = context;
        this.mList = list;
        onClick = click;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_album_adapter, null);
        }
        TextView text = (TextView) convertView.findViewById(R.id.textView);
        text.setText(mList.get(position).getPath());
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        Bitmap image = mList.get(position).getImage();

        if (image != null){
            imageView.setImageBitmap(image);
        }
        else {
            // If no image is provided, display a folder icon.
            imageView.setImageResource(R.drawable.ic_phone_iphone_24dp);
        }
        imageView.setOnClickListener(this);
        imageView.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag() + "");
        switch (v.getId()){
            case R.id.imageView:
                onClick.onclickDetail(position);
                break;
        }
    }

    public interface OnClickDetail{
        void onclickDetail(int position);
    }
}
