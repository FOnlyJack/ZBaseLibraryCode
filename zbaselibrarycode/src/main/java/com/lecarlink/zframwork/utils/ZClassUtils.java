package com.lecarlink.zframwork.utils;


public class ZClassUtils {
    public static String getCurrentClassName() {
        int level = 1;
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        String className = stacks[level].getClassName();
        return className;
    }
}
