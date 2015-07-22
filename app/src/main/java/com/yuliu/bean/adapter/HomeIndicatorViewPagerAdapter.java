package com.yuliu.bean.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hellonobase.DatabaseHelper;
import com.google.android.gms.ads.AdView;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorViewPagerAdapter;
import com.umeng.analytics.MobclickAgent;
import com.ytb.music_video.R;
import com.yuliu.activity.AppApplication;
import com.yuliu.bean.MyPlayList;
import com.yuliu.bean.PageViewHolder;
import com.yuliu.util.MyContants;
import com.yuliu.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

public class HomeIndicatorViewPagerAdapter extends IndicatorViewPagerAdapter {
    public LayoutInflater inflate;
    public Activity mActivity;
    public AppApplication application;
    public List<MyPlayList> mPlaylists = new ArrayList<MyPlayList>();
    public AdView mAdView;
    public DatabaseHelper databaseHelper;

    public HomeIndicatorViewPagerAdapter(LayoutInflater inflate, Activity mContext, AppApplication application,
                                         DatabaseHelper databaseHelper) {
        super();
        this.inflate = inflate;
        this.mActivity = mContext;
        this.application = application;
        // this.mAdView = mAdView;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public View getViewForTab(int postion, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.tab_top, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(mPlaylists.get(postion).title);
        return convertView;
    }

    @Override
    public View getViewForPage(final int postion, View convertView, ViewGroup container) {

        final int mypostion;
        mypostion = postion;
        final PageViewHolder holder;
        if (convertView == null) {
            holder = new PageViewHolder();
            convertView = inflate.inflate(R.layout.ytb_page, null);
            holder.mListView = (XListView) convertView.findViewById(R.id.listView1);
            holder.progressBar1 = (ProgressBar) convertView.findViewById(R.id.progressBar1);
            convertView.setTag(holder);
        } else {
            holder = (PageViewHolder) convertView.getTag();
        }

        final String id = mPlaylists.get(postion).id;
        final VideoAdapter adapter = new VideoAdapter(inflate, mActivity, application, databaseHelper);
        holder.mListView.setAdapter(adapter);
        holder.mListView.setPullRefreshEnable(false);// 能刷新
        holder.mListView.setPullLoadEnable(true);// 能加载更多
        holder.progressBar1.setVisibility(View.VISIBLE);
        holder.mListView.setXListViewListener(new IXListViewListener() {
            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                UIHelper.getInstance().getPlayListItems(application, id, "", adapter, holder,
                        UIHelper.getInstance().REFRESH);

            }

            @Override
            public void onLoadMore() {
                UIHelper.getInstance().getPlayListItems(application, id, adapter.nextPageToken, adapter, holder,
                        UIHelper.getInstance().MORE);
                if (MyContants.isOnLine) {
                    MobclickAgent.onEvent(mActivity, "more");
                }
            }
        });

        UIHelper.getInstance().getPlayListItems(application, id, "", adapter, holder, UIHelper.getInstance().REFRESH);
        // UIHelper.getInstance().onLoad(holder.mListView);
        return convertView;

    }

    @Override
    public int getCount() {
        return mPlaylists.size();
    }

}
