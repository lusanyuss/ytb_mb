package com.yuliu.util;

public class MyTime {
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Long date) {
        if (date == null) {
            return null;
        }
        long diff = System.currentTimeMillis() - date;
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + " years ago";
        }
        if (diff > month) {
            r = (diff / month);
            return r + " months ago";
        }
        if (diff > day) {
            r = (diff / day);
            return r + " days ago";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + " hours ago";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + " minutes ago";
        }
        return "a moment ago";
    }
}
