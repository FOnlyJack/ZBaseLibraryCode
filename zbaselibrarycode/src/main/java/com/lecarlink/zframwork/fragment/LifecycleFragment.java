package com.lecarlink.zframwork.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecarlink.zframwork.utils.ZLifecycleUtil;

/**
 * 可打印生命周期的fragment
 */
public class LifecycleFragment  extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onActivityCreated(savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onViewCreated(view, savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onActivityResult(requestCode, resultCode, data);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);

    }

    @Override
    public void onAttach(Activity activity) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onAttach(activity);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onConfigurationChanged(newConfig);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onCreate(savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onCreateOptionsMenu(menu, inflater);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onDestroy() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onDestroy();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onDestroyOptionsMenu() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onDestroyOptionsMenu();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onDestroyView() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onDestroyView();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onDetach() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onDetach();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onInflate(activity, attrs, savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onPause() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onPause();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onPrepareOptionsMenu(final Menu menu) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onPrepareOptionsMenu(menu);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onResume() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onResume();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onSaveInstanceState(outState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onStart() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onStart();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onStop() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onStop();

        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onViewStateRestored(savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onHiddenChanged(hidden);
        if (hidden) {
            //do when hidden
        } else {
            //do when show
        }
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Do your Work
        } else {
            // Do your Work
        }
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }
}
