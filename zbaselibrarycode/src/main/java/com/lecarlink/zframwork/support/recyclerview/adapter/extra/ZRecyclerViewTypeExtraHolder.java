package com.lecarlink.zframwork.support.recyclerview.adapter.extra;

import android.view.View;
import com.lecarlink.zframwork.support.recyclerview.adapter.ZRecyclerViewHolder;


public class ZRecyclerViewTypeExtraHolder extends ZRecyclerViewHolder {
    public ZRecyclerViewTypeExtraHolder(View itemView) {
        super(itemView);
    }

    /**
     * 保存当前position（list index，不包括headerView和footerView）
     */
    private int realItemPosition;

    public int getRealItemPosition() {
        return realItemPosition;
    }

    protected void setRealItemPosition(int realItemPosition) {
        this.realItemPosition = realItemPosition;
    }

}
