package com.lecarlink.zframwork.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Map;
import java.util.Set;

public class ZPrefsUtil {
    private static ZPrefsUtil ZPrefsUtil;
    public Context context;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    public synchronized static ZPrefsUtil getInstance(){
        return ZPrefsUtil;
    }

    public static void init(Context context, String prefsname, int mode) {
        ZPrefsUtil = new ZPrefsUtil();
        ZPrefsUtil.context = context;
        ZPrefsUtil.prefs = ZPrefsUtil.context.getSharedPreferences(prefsname, mode);
        ZPrefsUtil.editor = ZPrefsUtil.prefs.edit();
    }

    private ZPrefsUtil() {
    }


    public boolean getBoolean(String key, boolean defaultVal){
        return this.prefs.getBoolean(key, defaultVal);
    }
    public boolean getBoolean(String key){
        return this.prefs.getBoolean(key, false);
    }


    public String getString(String key, String defaultVal){
        return this.prefs.getString(key, defaultVal);
    }
    public String getString(String key){
        return this.prefs.getString(key, null);
    }

    public int getInt(String key, int defaultVal){
        return this.prefs.getInt(key, defaultVal);
    }
    public int getInt(String key){
        return this.prefs.getInt(key, 0);
    }


    public float getFloat(String key, float defaultVal){
        return this.prefs.getFloat(key, defaultVal);
    }
    public float getFloat(String key){
        return this.prefs.getFloat(key, 0f);
    }

    public long getLong(String key, long defaultVal){
        return this.prefs.getLong(key, defaultVal);
    }
    public long getLong(String key){
        return this.prefs.getLong(key, 0l);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defaultVal){
        return this.prefs.getStringSet(key, defaultVal);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key){
        return this.prefs.getStringSet(key, null);
    }

    public Map<String, ?> getAll(){
        return this.prefs.getAll();
    }


    public ZPrefsUtil putString(String key, String value){
        editor.putString(key, value);
//        editor.commit();
        return this;
    }

    public ZPrefsUtil putInt(String key, int value){
        editor.putInt(key, value);
//        editor.commit();
        return this;
    }

    public ZPrefsUtil putFloat(String key, float value){
        editor.putFloat(key, value);
//        editor.commit();
        return this;
    }

    public ZPrefsUtil putLong(String key, long value){
        editor.putLong(key, value);
//        editor.commit();
        return this;
    }

    public ZPrefsUtil putBoolean(String key, boolean value){
        editor.putBoolean(key, value);
//        editor.commit();
        return this;
    }
    public void commit(){
        editor.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ZPrefsUtil putStringSet(String key, Set<String> value){
        editor.putStringSet(key, value);
        editor.commit();
        return this;
    }


}
