package com.yuliu.util;

import android.content.Context;
import android.widget.Toast;

import com.yuliu.activity.AppApplication;

public class ToastFactory {

    protected static Toast toast = null;
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showToast(Context context, String s, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, s, duration);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > duration) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    public static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getString(resId), duration);
    }

    public static void showToast(Context ctx, String msg) {
        showToast(ctx, msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId), Toast.LENGTH_SHORT);
    }

    public static void showToast(String msg) {
        showToast(AppApplication.mApplication, msg, Toast.LENGTH_SHORT);
    }

}
