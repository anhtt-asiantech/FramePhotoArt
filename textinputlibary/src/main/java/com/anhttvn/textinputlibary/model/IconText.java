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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Typeface getFont() {
        return font;
    }

    public void setFont(Typeface font) {
        this.font = font;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}
