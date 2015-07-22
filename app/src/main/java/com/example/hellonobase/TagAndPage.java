package com.example.hellonobase;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class TagAndPage {

    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(index = true)
    public String tag;
    @DatabaseField
    public String pageToken;
    @DatabaseField
    public Date date;

    public TagAndPage() {
        // needed by ormlite
    }

    public TagAndPage(long millis) {
        this.date = new Date(millis);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
