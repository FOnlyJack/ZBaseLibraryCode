package com.lecarlink.zframwork.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.lecarlink.zframwork.manager.ActivityCollector;
import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZBaseApplication extends MultiDexApplication {

    private static Context instance;
	private static Map<String, Object> gloableData = new HashMap<String, Object>();

    public static class Config {

        public static final boolean DEVELOPER_MODE = false;
    }

    public static Context getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

	    instance = this;

        if (ZBaseApplication.Config.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {
                    ActivityCollector.getInstance().setCurrentActivity(activity);
                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                }
            });
        }
        iniLogger();
    }

    private void iniLogger(){
        Logger.init()                         // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
//              .hideThreadInfo()               // default shown
//              .logLevel(LogLevel.FULL)        // default LogLevel.FULL
//              .methodOffset(2)                // default 0
                .logTool(new AndroidLogTool()); // custom log tool, optional
    }



	/*******************************************************Application数据操作API（开始）********************************************************/

	/**
	 * 往Application放置数据（最大不允许超过5个）
	 * @param strKey 存放属性Key
	 * @param strValue 数据对象
	 */
	public static void assignData(String strKey, Object strValue) {
		if (gloableData.size() > 5) {
			throw new RuntimeException("超过允许最大数");
		}
		gloableData.put(strKey, strValue);
	}

	/**
	 * 从Applcaiton中取数据
	 * @param strKey 存放数据Key
	 * @return 对应Key的数据对象
	 */
	public static Object gainData(String strKey) {
		return gloableData.get(strKey);
	}

	/**
	 * 从Application中移除数据
	 */
	public static void removeData(String key){
		if(gloableData.containsKey(key)) gloableData.remove(key);
	}

    /**
     * 判断应用是否进入后台 网上抄的
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    /*******************************************************Application数据操作API（结束）********************************************************/


	// 在内存低时,发送广播可以释放一些内存
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    // /退出整个应用
    public void exit() {
        ActivityCollector.getInstance().exit(this);
    }

}
