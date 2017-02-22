package com.lecarlink.zframwork.manager;

import android.os.Bundle;


public interface OnActivityLifeCycleFullListener extends OnActivityLifeCycleListener {
    public void onActivityStartCallBack();

    public void onActivityRestartCallBack();

    public void onActivityStopCallBack();

    public void onActivitySaveInstanceStateCallBack(Bundle outState);

    public void onActivityRestoreInstanceStateCallBack(Bundle savedInstanceState);
}
