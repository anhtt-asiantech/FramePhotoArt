package com.anhttvn.textinputlibary;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.anhttvn.textinputlibary.adapter.AdapterFont;
import com.anhttvn.textinputlibary.model.Eventbus;
import com.anhttvn.textinputlibary.model.Font_vn;
import com.anhttvn.textinputlibary.model.IconText;
import com.anhttvn.textinputlibary.util.ColorPickerDialog;
import com.anhttvn.textinputlibary.util.ListFont;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class Textinput extends Activity implements ColorPickerDialog.OnColorSelectedListener, AdapterFont.OnClickFont,
        SeekBar.OnSeekBarChangeListener{
    private ListView list_font_pager;
    private AdapterFont mAdapter;
    private List<Font_vn> mListFont = new ArrayList<>();
    private EditText input_text;
    private SeekBar sek_bar_size;
    private int mColor =-16514044 ,mzise = 20;
    private Typeface mTypeFace;
    private IconText iconText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ListFont listFont = new ListFont();
        Eventbus.getBus().register(this);
        listFont.addListFont(this);

        mListFont = listFont.listFont;
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Eventbus.getBus().unregister(this);
    }

    private void init(){
        list_font_pager = findViewById(R.id.list_font_pager);
        input_text = findViewById(R.id.input_text);
        sek_bar_size = findViewById(R.id.sek_bar_size);
        sek_bar_size.setOnSeekBarChangeListener(this);
        mAdapter = new AdapterFont(this,mListFont,this);
        list_font_pager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        sek_bar_size.setProgress(mzise);
        if(iconText != null){
            input_text.setText(iconText.getName());
        }


    }
    //Handle button
    public void Picker1Click(View arg0) {
        int initialColor = Color.WHITE;

        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, initialColor, this);
        colorPickerDialog.show();

    }

    @Override
    public void onColorSelected(int color) {
        mColor = color;

        input_text.setTextColor(color);
    }


    @Override
    public void clickFont(int position) {
        mTypeFace = mListFont.get(position).getText();
        input_text.setTypeface(mListFont.get(position).getText());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        input_text.setTextSize(Float.valueOf(progress));
        mzise = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    public void clickDone(View view){
        IconText iconText = new IconText();
        iconText.setColor(mColor);
        iconText.setFont(mTypeFace);
        iconText.setSize(mzise);
        iconText.setName(input_text.getText().toString());
        iconText.setEditText(input_text);
        if(input_text.getText().toString().length()>0){
            Eventbus.getBus().post(iconText);
        }

        finish();

    }
    public  void clickExit(View view){
        finish();
    }
    @Subscribe
    public void getIconText(IconText data) {
        iconText = data;
        mColor =data.getColor();
        mTypeFace = data.getFont();
        mzise = data.getSize();
        setTextHello(data);

    }
    private void setTextHello(IconText text){

        input_text.setText(text.getName());
    }
    private void getData(){
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            iconText = (IconText) bundle.getSerializable("texticon");
            setTextHello(iconText);
        }
    }
}