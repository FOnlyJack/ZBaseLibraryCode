package com.lecarlink.zframwork.activity;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import com.lecarlink.zframwork.utils.ZLifecycleUtil;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 继承鸿洋大神的Auto,可打印生命周期的activity
 */
public class LifecycleActivity  extends AutoLayoutActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onCreate(savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        // Inflate the menu; this adds items to the action bar if it is present.
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onActivityResult(requestCode, resultCode, data);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onAttachedToWindow() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onAttachedToWindow();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onAttachFragment(fragment);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onConfigurationChanged(newConfig);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onContentChanged() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onContentChanged();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onDestroy() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onDestroy();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onDetachedFromWindow() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onDetachedFromWindow();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onNewIntent(intent);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onPause() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onPause();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onPostCreate(savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);

    }

    @Override
    protected void onPostResume() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onPostResume();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        boolean result = super.onPrepareOptionsMenu(menu);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);

        return result;
    }

    @Override
    protected void onRestart() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onRestart();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onResume() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onResume();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onRestoreInstanceState(savedInstanceState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onWindowFocusChanged(hasFocus);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onUserLeaveHint() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onUserLeaveHint();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onStart() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onStart();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);

    }

    @Override
    protected void onStop() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onStop();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onSaveInstanceState(outState);
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }

    @Override
    public void onUserInteraction() {
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.CALL_TO_SUPER);
        super.onUserInteraction();
        ZLifecycleUtil.recLifeCycle(this, ZLifecycleUtil.LifecycleState.RETURN_FROM_SUPER);
    }
}
