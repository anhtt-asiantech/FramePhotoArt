package com.anhttvn.textinputlibary.model;

import android.graphics.Typeface;
import android.widget.EditText;

import java.io.Serializable;

import lombok.Data;

@Data
public class IconText implements Serializable {
    private String name;
    private int color;
    private Typeface font;
    private int size;
    private EditText editText;
}
