package com.lecarlink.zframwork.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecarlink.zframwork.activity.BaseActivity;
import com.lecarlink.zframwork.base.IBaseViewer;
import com.lecarlink.zframwork.customviews.NetStateAlert;
import com.lecarlink.zframwork.utils.ZClassUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;


/**
 * BaseFragment 继承有生命周期监测的父类
 */
public abstract class BaseFragment extends LifecycleFragment implements IBaseViewer {

    private BaseActivity mActivity;

    private View mLayoutView;

    public boolean mIsViewInited;
    public boolean isVisible = false;

    // 标识view 是否初始化完成
    protected boolean isViewInit = false;

    private NetStateAlert netStateAlert;
    //Fragment-需要动态获取Fragment的名字
    private final String mPageName = "Fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mLayoutView == null) {
            mLayoutView = getCreateView(inflater, container);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mLayoutView.getParent();
        if (parent != null) {
            parent.removeView(mLayoutView);
        }
        ButterKnife.bind(this, mLayoutView);
        initView();
        createNetStateAlert();
        isVisible = true;
        return mLayoutView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(ZClassUtils.getCurrentClassName() + "BaseFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(ZClassUtils.getCurrentClassName() + "BaseFragment");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBusiness();
        if (!mIsViewInited) {
            mIsViewInited = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    /**
     * 初始化布局
     */
    public abstract int getLayoutRes();

    /**
     * 初始化视图
     */
    public abstract void initView();

    public void initBusiness() {

    }

    /**
     * 获取Fragment布局文件的View
     *
     * @param inflater
     * @param container
     * @return
     */
    private View getCreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    /**
     * 获取当前Fragment状态
     *
     * @return true为正常 false为未加载或正在删除
     */
    private boolean getStatus() {
        return (isAdded() && !isRemoving());
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

    public View getLayoutView() {
        return mLayoutView;
    }

    public <T extends View> T findView(int ResourceID) {
        return (T) mLayoutView.findViewById(ResourceID);
    }

    public <T extends View> T findView(int ResourceID, View.OnClickListener listener) {
        View view;
        view = mLayoutView.findViewById(ResourceID);
        if (listener != null)
            view.setOnClickListener(listener);
        return (T) view;
    }

    public void intentTo(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);

    }

    public void intentTo(Class<?> cls, Bundle bundle, int code) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, code);
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showProgress(flag, message);
            }
        }
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
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.hideProgress();
            }
        }
    }

    @Override
    public void showToast(int resId) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(resId);
            }
        }
    }

    @Override
    public void showToast(String msg) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(msg);
            }
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void close() {
    }

    /**
     * 要实现懒加载  可以放到父类里 让子类实现
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible();
            //可见
        }
        {
            isVisible = false;
            onInvisible();
            //不可见
        }
    }

    /**
     * 不可见
     */
    private void onInvisible() {

    }

    /**
     * 可见  实现懒加载  数据
     */
    private void onVisible() {
        loadDate();
    }

    /**
     * 延迟加载
     * 子类可以重写此方法
     */
    protected void loadDate() {

    }

    private void createNetStateAlert() {
        // if(netStateAlert == null){
        netStateAlert = new NetStateAlert(getActivity());
        // }
        netStateAlert.setCancelable(false);
        netStateAlert.setTitle("数据加载中...");
    }

    /**
     * 设置点击返回键时,不消失
     */
    public void setNetStateAlertNoCallableOutSide() {
        netStateAlert.setCanceledOnTouchOutside(false);
    }

    public void showNetStateAlert() {
        if (null != netStateAlert) {
            if (getActivity() != null && !getActivity().isFinishing()) {
                netStateAlert.show();
            }
        }
    }

    public void showNetStateAlert(String show) {
        // if(netStateAlert == null){
        netStateAlert = new NetStateAlert(getActivity());
        // }
        netStateAlert.setCancelable(false);
        netStateAlert.setTitle(show);
        showNetStateAlert();

    }

    public void hiddenNetStateAlert() {
        if (null != netStateAlert && netStateAlert.isShowing()) {
            if (getActivity() != null && !getActivity().isFinishing()) {
                netStateAlert.hide();
            }
        }
    }
}
