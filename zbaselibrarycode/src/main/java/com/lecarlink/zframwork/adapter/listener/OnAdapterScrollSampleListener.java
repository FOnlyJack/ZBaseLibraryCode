package com.lecarlink.zframwork.adapter.listener;

import android.widget.AbsListView;

/**
 * listview 实现自己定义的
 */
public class OnAdapterScrollSampleListener implements OnAdapterScrollListener{
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}

    @Override
    public void onTopWhenScrollIdle(AbsListView view) {}

    @Override
    public void onBottomWhenScrollIdle(AbsListView view) {}
}
