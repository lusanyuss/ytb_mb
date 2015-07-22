package com.yuliu.util;

public class ShareInfo {

    public static final String NAME = "shareInfo";

    final String subject;
    final String url;
    final String imagePath;
    final String des;

    public ShareInfo(String subject, String url, String imagePath, String des) {
        this.subject = subject;
        this.url = url;
        this.imagePath = imagePath;
        this.des = des;
    }

    public static String getName() {
        return NAME;
    }

    public String getSubject() {
        return subject;
    }

    public String getUrl() {
        return url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDes() {
        return des;
    }

}
