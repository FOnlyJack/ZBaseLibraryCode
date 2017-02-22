package com.lecarlink.zframwork.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * 生命周期监测的工具类
 */
public final class ZLifecycleUtil {

    public enum LifecycleState {
        CALL_TO_SUPER,
        RETURN_FROM_SUPER
    }

    public static void recLifeCycle(Object obj, LifecycleState state) {
        recLifeCycle(obj.toString(), state);
    }

    public static void recLifeCycle(Class<?> callingClass, LifecycleState state) {
        String className = callingClass.getSimpleName();
        recLifeCycle(className, state);
    }

    private static String getNote(LifecycleState state) {

        final String note;
        switch (state) {

            case CALL_TO_SUPER:
                note = "→☐";
                break;
            case RETURN_FROM_SUPER:
                note = "☐→";
                break;
            default:
                note = null;
        }
        return note;
    }

    private static void recLifeCycle(String objInfo, LifecycleState state) {

//        return;
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        String methodName = s[4].getMethodName();
        String note = getNote(state);

        Log.i("LifecycleLog", objInfo + "." + methodName + (TextUtils.isEmpty(note) ? "" : " / " + note));
    }

}
