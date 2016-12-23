package com.larry.tools.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast统一管理类
 */
public class ToastUtilsSingle {

    private static Toast toast;

    /**
     * 短时间
     * @param context
     * @param content
     */
    public static void showShortToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 长时间
     * @param context
     * @param content
     */
    public static void showLongToast(Context context,
                                      String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}