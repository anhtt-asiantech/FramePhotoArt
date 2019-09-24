package com.anhttvn.textinputlibary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anhttvn.textinputlibary.R;
import com.anhttvn.textinputlibary.model.Font_vn;

import java.util.List;

public class AdapterFont  extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private List<Font_vn> listFont;
    private OnClickFont mOnclick;
    private LayoutInflater mLayout;
    public AdapterFont(Context context,List<Font_vn> list, OnClickFont click){
        mContext = context;
        listFont = list;
        mOnclick = click;
        mLayout = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listFont.size();
    }

    @Override
    public Object getItem(int position) {
        return listFont.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderText holderText;
        if(convertView == null){
            convertView = mLayout.inflate(R.layout.item_adapter_text,null);
            holderText = new ViewHolderText();
            holderText.tvText =  convertView.findViewById(R.id.text_item);
            convertView.setTag(holderText);
        }else{
            holderText = (ViewHolderText)convertView.getTag();
        }
        holderText.tvText.setText(listFont.get(position).getName());
        holderText.tvText.setTypeface(listFont.get(position).getText());
        holderText.tvText.setOnClickListener(this);
        holderText.tvText.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag()+"");
        if (v.getId() == R.id.text_item) {
            mOnclick.clickFont(position);
        }
    }


    private class ViewHolderText{
        TextView tvText;
    }
    public interface OnClickFont{
        void clickFont(int position);
    }
}
