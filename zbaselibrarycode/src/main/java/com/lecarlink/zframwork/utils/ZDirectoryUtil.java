package com.lecarlink.zframwork.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class ZDirectoryUtil {

    //android5.1 init.rc中
//	mkdir /mnt/shell/emulated 0700 shell shell
//	mkdir /storage/emulated 0555 root root
//	mkdir /storage/sdcard1 0000 system system
//	mkdir /storage/usbotg 0700 system system
//	mkdir /mnt/media_rw/usbotg 0700 media_rw media_rw
//	mkdir /mnt/media_rw/sdcard1 0700 media_rw media_rw
//
//	export EXTERNAL_STORAGE /storage/emulated/legacy
//	export PRIMARY_STORAGE /storage/sdcard0
//	export SECONDARY_STORAGE /storage/sdcard1
//	export EMULATED_STORAGE_SOURCE /mnt/shell/emulated
//	export EMULATED_STORAGE_TARGET /storage/emulated
//
//	# Support legacy paths
//	symlink /storage/emulated/legacy /sdcard
//	symlink /storage/emulated/legacy /mnt/sdcard
//	symlink /mnt/shell/emulated/0 /storage/emulated/legacy
//	symlink /storage/emulated/legacy /storage/sdcard0

    private static final String EXTERNAL_STORAGE = "/storage/emulated/legacy";
    private static final String PRIMARY_STORAGE = "/storage/sdcard0";
    private static final String SECONDARY_STORAGE = "/storage/sdcard1";
    private static final String EMULATED_STORAGE_SOURCE = "/mnt/shell/emulated";
    private static final String EMULATED_STORAGE_TARGET = "/storage/emulated";

    private static File getRootDirectory(Context context) {
        File ret = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ret = Environment.getExternalStorageDirectory();
        } else {
            ret = context.getFilesDir();
        }
        return ret;
    }

    public static File getImageDirectory(Context context) {
        File dir = new File(getRootDirectory(context), "image");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public static File getPropertiesDirectory(Context context) {
        File dir = new File(getRootDirectory(context), "properties");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public static File getFileDirectory(Context context) {
        File dir = new File(getRootDirectory(context), "file");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public static File getSoundDirectory(Context context) {
        File dir = new File(getRootDirectory(context), "speech");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }
}
