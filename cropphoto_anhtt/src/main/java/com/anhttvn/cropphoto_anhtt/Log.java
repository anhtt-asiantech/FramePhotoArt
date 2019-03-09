package com.anhttvn.cropphoto_anhtt;

/**
 * Created by Huu Duan on 10/12/2017.
 */
class Log {

    private static final String TAG = "android-crop";

    public static void e(String msg) {
        android.util.Log.e(TAG, msg);
    }

    public static void e(String msg, Throwable e) {
        android.util.Log.e(TAG, msg, e);
    }

}