package com.lecarlink.zframwork.adapter.listener;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

/**
 * listview的 item 长按事件的封装
 */
public abstract class OnConvertViewLongClickListener implements View.OnLongClickListener {
    private View convertView;
    private int[] positionIds;

    public OnConvertViewLongClickListener(View convertView, int... positionIds) {
        this.convertView = convertView;
        this.positionIds = positionIds;
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override
    public boolean onLongClick(View v) {
        int len = positionIds.length;
        int[] positions = new int[len];
        for (int i = 0; i < len; i++) {
            positions[i] = (int) convertView.getTag(positionIds[i]);
        }
        return onLongClickCallBack(v, positions);
    }

    public abstract boolean onLongClickCallBack(View registedView, int... positionIds);

}
