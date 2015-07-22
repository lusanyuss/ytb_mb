package com.yuliu.bean;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;

public class MyChannels {
    public HashMap<Integer, List<MyPlayList>> channelHashMap = new HashMap<Integer, List<MyPlayList>>();

    public static MyChannels String2obj(String mString) {
        return JSON.parseObject(mString, MyChannels.class);
    }

    public static String obj2String(MyChannels mChannels) {
        return JSON.toJSONString(mChannels);
    }

    public HashMap<Integer, List<MyPlayList>> getChannelHashMap() {
        return channelHashMap;
    }

    public void setChannelHashMap(HashMap<Integer, List<MyPlayList>> channelHashMap) {
        this.channelHashMap = channelHashMap;
    }

}
