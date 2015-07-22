package com.yuliu.bean.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ytb.music_video.R;
import com.yuliu.activity.AppApplication;
import com.yuliu.bean.PageItem;
import com.yuliu.event.PlayEvent;
import com.yuliu.util.MyTime;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class HistoryAdapter extends BaseAdapter {

    public List<PageItem> mList = new ArrayList<PageItem>();
    public LayoutInflater inflate;
    public Activity mActivity;
    public AppApplication application;

    public HistoryAdapter(Activity mActivity) {
        super();
        this.mActivity = mActivity;
        this.inflate = mActivity.getLayoutInflater();
        this.application = (AppApplication) mActivity.getApplication();
        // this.mAdView = mAdView;
    }

    // public AdView mAdView;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View mCurrentRow = convertView;
        final PostHolder holder;
        final PageItem mResult = mList.get(position);

        if (mCurrentRow == null) {
            mCurrentRow = inflate.inflate(R.layout.ytb_page_item, parent, false);
            holder = new PostHolder();
            holder.start_play = (ImageButton) mCurrentRow.findViewById(R.id.start_play);
            holder.txtTitle = (TextView) mCurrentRow.findViewById(R.id.title);
            holder.thumbnailView = (ImageView) mCurrentRow.findViewById(R.id.thumbnail);
            holder.publishedAt = (TextView) mCurrentRow.findViewById(R.id.publishedAt);
            holder.item = mCurrentRow.findViewById(R.id.item);
            mCurrentRow.setTag(holder);
        } else {
            holder = (PostHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(mResult.imageurl)) {
            Picasso.with(mActivity).load(mResult.imageurl).placeholder(R.drawable.default_video_bg)
                    .into(holder.thumbnailView);
        }

        holder.publishedAt.setText(MyTime.getTimeFormatText(Long.valueOf(mResult.publishedAt)).toString());
        holder.txtTitle.setText(mResult.title);
        holder.item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new PlayEvent(mResult));
            }
        });

        return mCurrentRow;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public PageItem getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class PostHolder {
        ImageView thumbnailView;
        TextView txtTitle;
        TextView publishedAt;
        ImageButton start_play;
        View item;
    }

}