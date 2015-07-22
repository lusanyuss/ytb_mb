package com.yuliu.bean;

import java.util.ArrayList;
import java.util.List;

public class PageData {
    public List<PageItem> mResults = new ArrayList<PageItem>();
    public String nextPageToken = "";
    public String prePageToken = "";
}
