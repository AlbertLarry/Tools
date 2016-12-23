package com.larry.tools.log;

import android.util.Log;

/**
 * Created by PC on 2016/11/14.
 */
public class LogUtils {
    /**
     * 是否需要输出日志
     */
    public static boolean isDebug = true;
    public static String TAG = "larry";

    /**
     * 输出log
     *
     * @param str
     */
    public static void showLog(String str) {
        if (isDebug) {
            Log.e(TAG, str);
        }
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }
}
