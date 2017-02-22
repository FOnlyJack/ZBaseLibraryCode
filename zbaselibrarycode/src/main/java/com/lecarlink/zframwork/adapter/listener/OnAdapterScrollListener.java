package com.lecarlink.zframwork.adapter.listener;

import android.widget.AbsListView;

/**
 *  listview通用滑动监听
 */
public interface OnAdapterScrollListener extends AbsListView.OnScrollListener {
    //往上滑到顶部
    void onTopWhenScrollIdle(AbsListView view);
    //往下滑到底部
    void onBottomWhenScrollIdle(AbsListView view);

}
