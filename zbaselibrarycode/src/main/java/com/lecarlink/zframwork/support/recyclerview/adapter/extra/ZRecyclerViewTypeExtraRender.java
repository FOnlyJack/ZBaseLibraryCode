package com.lecarlink.zframwork.support.recyclerview.adapter.extra;

import android.view.View;
import com.lecarlink.zframwork.adapter.typeadapter.ZAdapterTypeRender;

/**
 * 带有header或者footer的view
 */
/*public*/ class ZRecyclerViewTypeExtraRender implements ZAdapterTypeRender<ZRecyclerViewTypeExtraHolder> {
    protected ZRecyclerViewTypeExtraHolder holder;

    protected ZRecyclerViewTypeExtraRender(View extraView) {
        holder = new ZRecyclerViewTypeExtraHolder(extraView);
    }

    @Override
    public ZRecyclerViewTypeExtraHolder getReusableComponent() {
        return holder;
    }

    @Override
    public void fitEvents() {

    }

    @Override
    public void fitDatas(int position) {

    }
}
