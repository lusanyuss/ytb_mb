package com.yuliu.bean;

import com.alibaba.fastjson.JSON;

public class MyPlayList {
    public String id = "";
    public String postion = "";
    public String channel_id = "";
    public String title = "";

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
