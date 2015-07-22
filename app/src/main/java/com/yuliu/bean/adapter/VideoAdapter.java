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

import com.example.hellonobase.DatabaseHelper;
import com.squareup.picasso.Picasso;
import com.ytb.music_video.R;
import com.yuliu.activity.AppApplication;
import com.yuliu.bean.PageItem;
import com.yuliu.event.PlayEvent;
import com.yuliu.util.MyContants;
import com.yuliu.util.MyTime;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class VideoAdapter extends BaseAdapter {

    public List<PageItem> mList = new ArrayList<PageItem>();
    public String nextPageToken = "";
    public String currentPageToken = "";
    public DatabaseHelper databaseHelper = null;
    // public AdView mAdView;
    public LayoutInflater inflate;
    public Activity mActivity;
    public AppApplication application;

    public VideoAdapter(LayoutInflater inflate, Activity mActivity, AppApplication application,
                        DatabaseHelper databaseHelper) {
        super();
        // this.mAdView = mAdView;
        this.inflate = inflate;
        this.mActivity = mActivity;
        this.application = application;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View mCurrentRow = convertView;
        final PostHolder holder;
        final PageItem mResult = mList.get(position);

        if (mCurrentRow == null) {
            if (application.isBig) {
                mCurrentRow = inflate.inflate(R.layout.ytb_page_item_big, parent, false);
            } else {
                mCurrentRow = inflate.inflate(R.layout.ytb_page_item, parent, false);
            }

            holder = new PostHolder();
            holder.start_play = (ImageButton) mCurrentRow.findViewById(R.id.start_play);
            holder.txtTitle = (TextView) mCurrentRow.findViewById(R.id.title);
            holder.thumbnailView = (ImageView) mCurrentRow.findViewById(R.id.thumbnail);
            holder.publishedAt = (TextView) mCurrentRow.findViewById(R.id.publishedAt);
            holder.views_count = (TextView) mCurrentRow.findViewById(R.id.views_count);
            holder.time = (TextView) mCurrentRow.findViewById(R.id.time);
            mCurrentRow.setTag(holder);
        } else {
            holder = (PostHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(mResult.imageurl)) {
            Picasso.with(mActivity).load(mResult.imageurl).placeholder(R.drawable.default_video_bg)
                    .into(holder.thumbnailView);
        }

        long viewcount = Long.valueOf(mResult.viewCount);
        if (viewcount > 1000) {
            holder.views_count.setText(viewcount / 1000 + "k" + MyContants.views);
        } else if (viewcount > 1000000) {
            holder.views_count.setText(viewcount / 1000000 + "M" + MyContants.views);
        } else {
            holder.views_count.setText(viewcount + MyContants.views);
        }

        String tString = mResult.duration;
        if (tString.contains(MyContants.M)) {
            tString = tString.replace(MyContants.M, ":");
        }

        if (tString.contains(MyContants.H)) {
            tString = tString.replace(MyContants.H, "");
        }
        if (tString.contains(MyContants.S)) {
            tString = tString.replace(MyContants.S, "");
        }

        if (tString.contains(MyContants.PT)) {
            tString = tString.replace(MyContants.PT, "");
        }

        holder.time.setText(tString);
        holder.publishedAt.setText(MyTime.getTimeFormatText(Long.valueOf(mResult.publishedAt)).toString());
        holder.txtTitle.setText(mResult.title);

        if (application.isBig) {

            holder.start_play.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new PlayEvent(mResult));

                }
            });

        } else {
            mCurrentRow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new PlayEvent(mResult));

                }
            });
        }

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
        TextView time;
        TextView views_count;
        ImageButton start_play;
    }

}