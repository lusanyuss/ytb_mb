package com.yuliu.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedHelper {
    /***/
    private static final String PreferenceName = "baidu";

    public static long getLastTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        long code = sp.getLong("time", 0L);
        return code;
    }

    public static void setLastTime(Context context, long time) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putLong("time", time).commit();
    }

    public static String getPlayLists(Context context, String postion) {

        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        String code = sp.getString(postion + "_pl", "");
        return code;
    }

    // 每一个类别的标题缓存
    public static void setPlayLists(Context context, String postion, String time) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putString(postion + "_pl", time).commit();
    }

    // 每一页的播放列表缓存
    public static String getPlayListsItem(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        String code = sp.getString(key, "");
        return code;
    }

    public static void setPlayListsItem(Context context, String key, String data) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putString(key, data).commit();
    }

    // 每一页的播放列表缓存time

    public static long getPlayListsItemTime(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        long code = sp.getLong(key, 0L);
        return code;
    }

    public static void setPlayListsItemTime(Context context, String key, long time) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putLong(key, time).commit();
    }

    public static boolean getIsFirst(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        boolean code = sp.getBoolean("isfirst", true);
        return code;
    }

    public static void setIsFirst(Context context, boolean isfirst) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putBoolean("isfirst", isfirst).commit();
    }

    public static int getPostion(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        return sp.getInt("postion", 0);
    }

    public static void setPostion(Context context, int postion) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putInt("postion", postion).commit();
    }

    public static boolean getIsBigShow(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        boolean code = sp.getBoolean("isBig", true);
        return code;
    }

    public static void setIsBigShow(Context context, boolean isBig) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putBoolean("isBig", isBig).commit();
    }

    // 缓存时间模块
    public static long getCacheTime(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        long code = sp.getLong(key, 0);
        return code;
    }

    public static void setCacheTime(Context context, String key, long time) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putLong(key, time).commit();
    }

    // 缓存数据模块

    public static String getCacheString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_READABLE);
        String code = sp.getString(key, "");
        return code;
    }

    public static void setCacheString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, context.MODE_WORLD_WRITEABLE);
        sp.edit().putString(key, value).commit();
        setCacheTime(context, key, System.currentTimeMillis());
    }

}
