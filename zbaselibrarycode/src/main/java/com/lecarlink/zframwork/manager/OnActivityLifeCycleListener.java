package com.lecarlink.zframwork.manager;

import android.os.Bundle;


public interface OnActivityLifeCycleListener {
    @Deprecated
    public void onActivityCreateCallback(Bundle savedInstanceState);

    public void onActivityResumeCallback();

    public void onActivityPauseCallback();

    public void onActivityDestroyCallback();


}
