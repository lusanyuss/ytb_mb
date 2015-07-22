package com.example.hellonobase;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * A simple demonstration object we are creating and persisting to the database.
 */
public class CacheData implements Serializable {

    // id is generated by the database and set on the object automagically
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(index = true)
    public String key;
    @DatabaseField
    public String value;
    @DatabaseField
    public String next_page;
    @DatabaseField
    public long time;
    @DatabaseField
    public Date date;

    // @DatabaseField
    // boolean even;

    public CacheData() {
        // needed by ormlite
    }

    public CacheData(long millis) {
        this.date = new Date(millis);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNext_page() {
        return next_page;
    }

    public void setNext_page(String next_page) {
        this.next_page = next_page;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
