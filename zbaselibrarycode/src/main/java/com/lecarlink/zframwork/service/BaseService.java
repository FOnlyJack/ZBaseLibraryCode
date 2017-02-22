package com.lecarlink.zframwork.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.orhanobut.logger.Logger;

/**
 * BaseService  打印相应的生命周期
 */


public class BaseService extends Service {
	private static final String TAG = "BaseService" ;
	public static final String ACTION = "com.base.BaseService";

	@Override
	public IBinder onBind(Intent intent) {
		Logger.v(TAG, "BaseService onBind");
		return null;
	}
	@Override
	public boolean onUnbind(Intent intent) {
		Logger.v(TAG, "BaseService onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		Logger.v(TAG, "BaseService onRebind");
		super.onRebind(intent);
	}

	@Override
	public void onTaskRemoved(Intent rootIntent) {
		Logger.v(TAG, "BaseService onTaskRemoved");
		super.onTaskRemoved(rootIntent);
	}

	@Override
	public void onCreate() {
		Logger.v(TAG, "BaseService onCreate");
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Logger.v(TAG, "BaseService onStart");
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Logger.v(TAG, "BaseService onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
}
