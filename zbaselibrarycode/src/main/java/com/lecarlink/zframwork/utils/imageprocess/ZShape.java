package com.lecarlink.zframwork.utils.imageprocess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import com.lecarlink.zframwork.utils.ZViewUtil;


public class ZShape {

    /**
     * 生成圆角的背景
     *
     * @param color
     * @return
     */
    public static ShapeDrawable generateCornerShapeDrawable(int color, int corner) {
        return generateCornerShapeDrawable(color, corner, corner, corner, corner);
    }

    public static ShapeDrawable generateCornerShapeDrawable(int color, int topLeftCorner, int topRightCorner, int bottomRightCorner, int bottomLeftCorner) {
        Shape shape = new RoundRectShape(new float[]{topLeftCorner, topLeftCorner, topRightCorner, topRightCorner, bottomRightCorner, bottomRightCorner, bottomLeftCorner, bottomLeftCorner}, null, null);
        ShapeDrawable sd = new ShapeDrawable(shape);
        sd.getPaint().setColor(color);
        sd.getPaint().setStyle(Paint.Style.FILL);
        return sd;
    }

    public static ShapeDrawable generateCornerStrokeDrawable(int color, float width, int corner) {
        return generateCornerStrokeDrawable(color, width, corner, corner, corner, corner);
    }

    public static ShapeDrawable generateCornerStrokeDrawable(int color, float width, int topLeftCorner, int topRightCorner, int bottomRightCorner, int bottomLeftCorner) {
        Shape shape = new RoundRectShape(new float[]{topLeftCorner, topLeftCorner, topRightCorner, topRightCorner, bottomRightCorner, bottomRightCorner, bottomLeftCorner, bottomLeftCorner}, null, null);
        ShapeDrawable sd = new ShapeDrawable(shape);
        sd.getPaint().setColor(color);
        sd.getPaint().setStyle(Paint.Style.STROKE);
        sd.getPaint().setAntiAlias(true);
        sd.getPaint().setStrokeWidth(width);
        return sd;
    }

    public static StateListDrawable selectorClickSimple(Drawable normal, Drawable pressed) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        drawable.addState(new int[]{}, normal);
        return drawable;
    }

    public static StateListDrawable selectorClickColorCornerSimple(int normalColor, int pressedColor, int corner) {
        return selectorClickSimple(
                generateCornerShapeDrawable(normalColor, corner),
                generateCornerShapeDrawable(pressedColor, corner)
        );
    }

    public static ShapeDrawable generateBackgroundDrawable(int color) {
        Shape shape = new OvalShape();
        ShapeDrawable sd = new ShapeDrawable(shape);
        Paint paint = sd.getPaint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        return sd;
    }


    public static Drawable selectorCornerRippleCompat(int normalColor, int pressedColor) {
        return selectorCornerRippleCompat(normalColor, pressedColor, 0);
    }
    public static Drawable selectorCornerRippleCompat(int normalColor, int pressedColor, int corner) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = new RippleDrawable(ZViewUtil.createColorStateList(normalColor, pressedColor),
                    generateCornerShapeDrawable(normalColor, corner), null);
        }else{
            drawable = selectorClickColorCornerSimple(normalColor, pressedColor, corner);
        }
        return drawable;
    }

    /**
     * 为Drawable着色
     * @param context
     * @param drawable
     * @param color
     * @param mode
     * @return
     */
    public static Drawable setDrawableColorFilterCompat(Context context, Drawable drawable, int color, PorterDuff.Mode mode) {
        Drawable icon = drawable.mutate();
        icon.setColorFilter(color, mode);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            return icon;
        }

//      Note that on KitKat, setting a ColorFilter on a Drawable contained in a StateListDrawable
//      apparently doesn't work, although it does on later versions, so we have to render the colored
//      bitmap into a BitmapDrawable and then put that into the StateListDrawable

        Bitmap bitmap = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

}
