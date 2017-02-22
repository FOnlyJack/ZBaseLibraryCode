package com.lecarlink.zframwork.support.cardview;



import android.graphics.drawable.Drawable;

/**
 * Interface provided by CardView to implementations.
 * <p>
 * Necessary to resolve circular dependency between base CardView and platform implementations.
 */
interface CardViewDelegate {

    void setBackgroundDrawable(Drawable paramDrawable);

    Drawable getBackground();
}
