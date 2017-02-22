package com.lecarlink.zframwork.broadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class BaseBroadCastReceiver extends BroadcastReceiver{

	/**
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
		}
	}

}
