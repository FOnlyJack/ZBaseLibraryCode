package com.lecarlink.zframwork.support.recyclerview.adapter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.lecarlink.zframwork.R;
import com.lecarlink.zframwork.adapter.typeadapter.ZAdapterTypeRender;


public abstract class ZRecyclerViewTypeAdapter extends RecyclerView.Adapter<ZRecyclerViewHolder> {
    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override
    public ZRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ZAdapterTypeRender<ZRecyclerViewHolder> render = getAdapterTypeRender(viewType);
        ZRecyclerViewHolder holder = render.getReusableComponent();
        holder.itemView.setTag(R.id.ab__id_adapter_item_type_render, render);
        render.fitEvents();
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override
    public void onBindViewHolder(ZRecyclerViewHolder holder, int position) {
        ZAdapterTypeRender<ZRecyclerViewHolder> render = (ZAdapterTypeRender<ZRecyclerViewHolder>) holder.itemView.getTag(R.id.ab__id_adapter_item_type_render);
        render.fitDatas(position);
    }

    /**
     * 根据指定position的item获取对应的type，然后通过type实例化一个AdapterTypeRender的实现
     *
     * @return
     */
    public abstract ZAdapterTypeRender<ZRecyclerViewHolder> getAdapterTypeRender(int type);
}
