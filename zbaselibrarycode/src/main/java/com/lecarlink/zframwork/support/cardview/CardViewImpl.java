package com.lecarlink.zframwork.support.cardview;



import android.content.Context;

/**
 * Interface for platform specific CardView implementations.
 */
interface CardViewImpl {

    void initialize(CardViewDelegate cardView, Context context, int backgroundColor, float radius, CardShadow cardShadow);

    void setRadius(CardViewDelegate cardView, float radius);

    float getRadius(CardViewDelegate cardView);

    void initStatic();
}
