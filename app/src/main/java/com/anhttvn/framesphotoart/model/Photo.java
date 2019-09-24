package com.anhttvn.framesphotoart.model;

import android.graphics.Bitmap;

import java.io.Serializable;

import lombok.Data;

@Data
public class Photo implements Serializable {
    public Photo(String path, boolean isDirectory, Bitmap image) {
        this.path = path;
        this.isDirectory = isDirectory;
        this.image = image;
    }

    private String path;
    private boolean isDirectory;
    private Bitmap image;
}