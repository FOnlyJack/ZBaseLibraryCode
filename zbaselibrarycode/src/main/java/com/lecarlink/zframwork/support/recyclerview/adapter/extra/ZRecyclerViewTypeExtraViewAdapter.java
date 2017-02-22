package com.lecarlink.zframwork.support.recyclerview.adapter.extra;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.lecarlink.zframwork.R;
import com.lecarlink.zframwork.adapter.typeadapter.ZAdapterTypeRender;


public abstract class ZRecyclerViewTypeExtraViewAdapter extends RecyclerView.Adapter<ZRecyclerViewTypeExtraHolder> {
    private static final int TYPE_HEADER_VIEW = 0x7683;
    private View headerView;
    private static final int TYPE_FOOTER_VIEW = 0x7684;
    private View footerView;
    private int extraCount;

    protected ZRecyclerViewTypeExtraViewAdapter(View headerView, View footerView) {
        this.headerView = headerView;
        this.footerView = footerView;
        extraCount += hasHeaderView() ? 1 : 0;
        extraCount += hasFooterView() ? 1 : 0;
    }

    public boolean hasHeaderView() {
        return null != headerView;
    }

    public boolean hasFooterView() {
        return null != footerView;
    }

    public int innerPositionToRealItemPosition(int innerPosition) {
        return hasHeaderView() ? innerPosition - 1 : innerPosition;
    }

    public int realItemPositionToInnerPosition(int realItemPosition) {
        return hasHeaderView() ? realItemPosition + 1 : realItemPosition;
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override
    public ZRecyclerViewTypeExtraHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ZAdapterTypeRender<ZRecyclerViewTypeExtraHolder> render = getAdapterTypeRender(viewType);
        ZRecyclerViewTypeExtraHolder holder = render.getReusableComponent();
        holder.itemView.setTag(R.id.ab__id_adapter_item_type_render, render);
        render.fitEvents();
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    @Override
    public void onBindViewHolder(ZRecyclerViewTypeExtraHolder holder, int innerPosition) {
        ZAdapterTypeRender<ZRecyclerViewTypeExtraHolder> render = (ZAdapterTypeRender<ZRecyclerViewTypeExtraHolder>) holder.itemView.getTag(R.id.ab__id_adapter_item_type_render);
        /**
         * 计算该item在list中的index（不包括headerView和footerView）
         */
        int realItemPosition = innerPositionToRealItemPosition(innerPosition);
        render.fitDatas(realItemPosition);
        /**
         * 重新设置item在list中的index（不包括headerView和footerView）
         */
        holder.setRealItemPosition(realItemPosition);
    }

    /**
     * 通过类型获得对应的render（不包括headerView和footerView）
     *
     * @param type
     * @return
     */
    public abstract ZAdapterTypeRender<ZRecyclerViewTypeExtraHolder> getAdapterTypeRenderExcludeExtraView(int type);

    /**
     * 获取item的数量（不包括headerView和footerView）
     *
     * @return
     */
    public abstract int getItemCountExcludeExtraView();

    /**
     * 通过realItemPosition得到该item的类型（不包括headerView和footerView）
     *
     * @param realItemPosition
     * @return
     */
    public abstract int getItemViewTypeExcludeExtraView(int realItemPosition);

    public ZAdapterTypeRender<ZRecyclerViewTypeExtraHolder> getAdapterTypeRender(int type) {
        switch (type) {
            case TYPE_HEADER_VIEW:
                return new ZRecyclerViewTypeExtraRender(headerView);
            case TYPE_FOOTER_VIEW:
                return new ZRecyclerViewTypeExtraRender(footerView);
            default:
                return getAdapterTypeRenderExcludeExtraView(type);
        }
    }

    @Override
    public int getItemCount() {
        return getItemCountExcludeExtraView() + extraCount;
    }

    @Override
    public int getItemViewType(int innerPosition) {
        if (null != headerView && 0 == innerPosition) { // header
            return TYPE_HEADER_VIEW;
        } else if (null != footerView && getItemCount() - 1 == innerPosition) { // footer
            return TYPE_FOOTER_VIEW;
        }
        return getItemViewTypeExcludeExtraView(innerPositionToRealItemPosition(innerPosition));
    }
}
