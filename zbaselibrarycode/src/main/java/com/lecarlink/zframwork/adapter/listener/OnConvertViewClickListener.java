package com.lecarlink.zframwork.adapter.listener;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import java.util.List;

/**
 * listview的item点击事件的封装
 */
public abstract class OnConvertViewClickListener implements View.OnClickListener{

    private View convertView;
    private int[] positionIds;
    public OnConvertViewClickListener(View convertView, int... positionIds) {
        this.convertView = convertView;
        this.positionIds = positionIds;
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override
    public void onClick(View v) {
        int len = positionIds.length;
        int[] positions = new int[len];
        for(int i = 0; i < len; i++){
            positions[i] = (int) convertView.getTag(positionIds[i]);
        }
        onClickCallBack(v, positions);
    }

    public abstract void onClickCallBack(View registedView, int... positionIds);

}
