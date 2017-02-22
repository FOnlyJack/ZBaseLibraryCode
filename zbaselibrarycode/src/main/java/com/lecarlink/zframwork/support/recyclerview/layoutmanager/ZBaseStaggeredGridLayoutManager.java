package com.lecarlink.zframwork.support.recyclerview.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.lecarlink.zframwork.support.recyclerview.listener.OnRecyclerViewScrollLocationListener;
import com.lecarlink.zframwork.utils.ZTextUtil;

import java.util.Arrays;


public class ZBaseStaggeredGridLayoutManager extends StaggeredGridLayoutManager implements RecyclerViewScrollManager.OnScrollManagerLocation {
    private static final String TAG = ZBaseStaggeredGridLayoutManager.class.getSimpleName();

    private RecyclerViewScrollManager recyclerViewScrollManager;

    public void setOnRecyclerViewScrollListener(RecyclerView recyclerView, OnRecyclerViewScrollLocationListener onRecyclerViewScrollLocationListener) {
        ensureRecyclerViewScrollManager();
        recyclerViewScrollManager.setOnRecyclerViewScrollLocationListener(onRecyclerViewScrollLocationListener);
        recyclerViewScrollManager.setOnScrollManagerLocation(this);
        recyclerViewScrollManager.registerScrollListener(recyclerView);
    }

    public ZBaseStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public boolean isScrolling() {
        if (null != recyclerViewScrollManager) {
            return recyclerViewScrollManager.isScrolling();
        }
        return false;
    }

    public RecyclerViewScrollManager getRecyclerViewScrollManager() {
        ensureRecyclerViewScrollManager();
        return recyclerViewScrollManager;
    }
    private void ensureRecyclerViewScrollManager(){
        if (null == recyclerViewScrollManager) {
            recyclerViewScrollManager = new RecyclerViewScrollManager();
        }
    }


    @Override
    public boolean isTop(RecyclerView recyclerView) {
        int[] into = findFirstVisibleItemPositions(null);
        return !ZTextUtil.isEmpty(into) && 0 == into[0];
    }

    @Override
    public boolean isBottom(RecyclerView recyclerView) {
        int into[] = findLastCompletelyVisibleItemPositions(null);
        int lastPosition = recyclerView.getAdapter().getItemCount() - 1;
        Arrays.sort(into);
        return !ZTextUtil.isEmpty(into) && lastPosition == into[into.length - 1];
    }
}
