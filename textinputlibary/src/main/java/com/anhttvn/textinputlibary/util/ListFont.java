package com.anhttvn.textinputlibary.util;

import android.content.Context;
import android.graphics.Typeface;
import com.anhttvn.textinputlibary.model.Font_vn;
import java.util.ArrayList;
import java.util.List;

public class ListFont {
    public List<Font_vn> listFont = new ArrayList<>();
    private String fileFont;

    private Context mContext;
    private String font[] = {"Aller_Rg", "Allura-Regular", "AmaticSC-Regular","blackjack"
            , "CaviarDreams", "Chunkfive", "Courier New", "DancingScript-Regular", "GoodDog",
            "GrandHotel-Regular", "KaushanScript-Regular", "Lato-Regular", "Lobster_1.3", "MontserratAlternates-ExtraBold", "OpenSans-Italic",
            "Pacifico", "PTN57F", "Quicksand-BoldItalic", "Raleway-ExtraLightItalic", "Roboto-BlackItalic", "Roboto-Medium",
            "Sail-Regular", "SourceSansPro-BlackIt", "Times New Roman", "Windsong"};
    private Typeface typeface;

    public void addListFont(Context context){
        mContext = context;
        Font_vn font_vn = new Font_vn();
        font_vn.setPosition(0);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[0] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[0]);
        listFont.add(font_vn);


        font_vn = new Font_vn();
        font_vn.setPosition(1);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[1] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[1]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(2);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[2] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[2]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(3);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[3] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[3]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(4);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[4] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[4]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(5);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[5] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[5]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(6);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[6] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[6]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(7);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[7] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[7]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(8);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[8] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[8]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(9);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[9] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[9]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(10);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[10] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[10]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(11);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[11] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[11]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(12);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[12] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[12]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(13);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[13] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[13]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(14);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[14] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[14]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(15);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[15] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[15]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(16);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[16] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[16]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(17);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[17] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[17]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(18);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[18] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[18]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(19);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[19] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[19]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(20);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[20] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[20]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(21);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[21] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[21]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(22);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[22] + ".otf");
        font_vn.setText(typeface);
        font_vn.setName(font[22]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(23);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[23] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[23]);
        listFont.add(font_vn);

        font_vn = new Font_vn();
        font_vn.setPosition(24);
        font_vn.setCheck(false);
        typeface = Typeface.createFromAsset(mContext.getResources().getAssets(), "font/" +
                font[24] + ".ttf");
        font_vn.setText(typeface);
        font_vn.setName(font[24]);
        listFont.add(font_vn);
    }
}
