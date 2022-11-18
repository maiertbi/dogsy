package com.dogsy.presentation.classes;

import android.graphics.drawable.Drawable;

public class PhotoItem {
    private Drawable iconDrawable ;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }

}