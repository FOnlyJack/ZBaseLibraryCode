package com.lecarlink.zframwork.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lecarlink.zframwork.application.ZBaseApplication;
import com.lecarlink.zframwork.base.IBaseViewer;
import com.lecarlink.zframwork.fragment.BaseFragment;
import com.lecarlink.zframwork.manager.ActivityCollector;
import com.lecarlink.zframwork.view.CustomAlertDialog;

import java.util.List;

import butterknife.ButterKnife;

/**
 * BaseActivity 继承有生命周期监测的父类
 */
public abstract class BaseActivity extends LifecycleActivity implements IBaseViewer {

    private ProgressDialog mProgressDialog;
    protected boolean mIsLauncher;

    /**
     * 初始化布局
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化控制中心
     */
    protected void initBusiness() {}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //强制页面纵向
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        ActivityCollector.getInstance().addActivity(this);
        setLayout();
        ButterKnife.bind(this);
        initView();
        initBusiness();
    }

    @Override
    protected void onResume() {
        super.onResume();
       /* MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(this);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    /*    MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(this);*/
    }
    /**
     * 默认设置内容视图用布局提供，可以扩展。
     */
    protected void setLayout() {
        int res = getLayoutRes();
        if (res > 0) {
            setContentView(getLayoutRes());
        }
    }

    //--------------------------Fragment相关--------------------------//

    /**
     * 获取Fragment管理器
     *
     * @return
     */
    public FragmentManager getBaseFragmentManager() {
        return getSupportFragmentManager();
    }

    /**
     * 获取Fragment事物管理
     *
     * @return
     */
    public FragmentTransaction getFragmentTransaction() {
        return getBaseFragmentManager().beginTransaction();
    }

    /**
     * 替换一个Fragment
     *
     * @param res
     * @param fragment
     */
    public void replaceFragment(int res, BaseFragment fragment) {
        replaceFragment(res, fragment, false);
    }

    /**
     * 替换一个Fragment并设置是否加入回退栈
     *
     * @param res
     * @param fragment
     * @param isAddToBackStack
     */
    public void replaceFragment(int res, BaseFragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.replace(res, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

    }

    /**
     * 添加一个Fragment
     *
     * @param res
     * @param fragment
     */
    public void addFragment(int res, Fragment fragment) {
        addFragment(res, fragment, null, false);
    }

    /**
     * 添加一个Fragment
     *
     * @param res
     * @param fragment
     */
    public void addFragment(int res, Fragment fragment, String tag, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = hideAllFragment();
        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(res, fragment, tag);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    /**
     * 移除一个Fragment
     *
     * @param fragment
     */
    public void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    /**
     * 显示一个Fragment
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        if (fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }
    }

    /**
     * 隐藏一个Fragment
     *
     * @param fragment
     */
    public void hideFragment(Fragment fragment) {
        if (!fragment.isHidden()) {
            FragmentTransaction fragmentTransaction = getFragmentTransaction();
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
        }
    }


    /**
     * 显示单选对话框
     *
     * @param title           标题
     * @param message         提示信息
     * @param strings         选项数组
     * @param checkedItem     默认选中
     * @param onClickListener 点击事件的监听
     */
    public void showRadioButtonDialog(String title, String message, String[] strings, int checkedItem, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        builder.setSingleChoiceItems(strings, checkedItem, onClickListener);
        builder.create();
        builder.show();
    }

    /**
     * 显示单选对话框
     *
     * @param title           标题
     * @param strings         选项数组
     * @param onClickListener 点击事件的监听
     */
    public void showRadioButtonDialog(String title, String[] strings, DialogInterface.OnClickListener onClickListener) {
        showRadioButtonDialog(title, null, strings, 0, onClickListener);
    }

    /**
     * 弹出自定义对话框
     */
    public void showConfirmDialog(String title, View.OnClickListener positiveListener) {
        CustomAlertDialog confirmDialog = new CustomAlertDialog(this, title, positiveListener);
        confirmDialog.show();
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(flag);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage(message);
        }

        mProgressDialog.show();
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void showProgress(boolean flag) {
        showProgress(flag, "");
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog == null)
            return;

        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showToast(int resId) {
        showToast(getString(resId));
    }

    @Override
    public void showToast(String msg) {
        if (!isFinishing()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void close() {
        finish();
    }

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (!mIsLauncher) {
            super.onBackPressed();
            return;
        } else {
            if (getSupportFragmentManager().popBackStackImmediate()) {
                return;
            }

            if (System.currentTimeMillis() - mBackPressed <= TIME_INTERVAL) {
                //可添加自己自定义的动作 退出应用
                super.onBackPressed();
                ((ZBaseApplication) getApplication()).exit();
                return;
            } else {
                Toast.makeText(getBaseContext(), "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                mBackPressed = System.currentTimeMillis();
            }
        }
    }

    public void clearBackStack() {
        getSupportFragmentManager().popBackStackImmediate(null, 0);
    }


    public void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    public void replaceFragment(boolean addToBackStack, Fragment fragment, int containerId) {
        invalidateOptionsMenu();
        String fragmentTag = fragment.getClass().getName();
        boolean fragmentPopped = getSupportFragmentManager().popBackStackImmediate(fragmentTag, 0);

        if (!fragmentPopped && getSupportFragmentManager().findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(containerId, fragment, fragmentTag);
            if (addToBackStack) {
                transaction.addToBackStack(fragmentTag);
            }
            transaction.commit();
        }
    }

    public FragmentTransaction hideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (null != fragments) {
            for (Fragment fragment : fragments) {
                if (fragment == null || fragment.isHidden()) {
                    continue;
                }
                transaction.hide(fragment);
            }
        }
        return transaction;
    }

}
