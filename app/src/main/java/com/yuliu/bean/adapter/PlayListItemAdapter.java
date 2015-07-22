package com.yuliu.bean.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.ytb.music_video.R;
import com.yuliu.bean.PageData;

import java.util.ArrayList;
import java.util.List;

public class PlayListItemAdapter extends BaseAdapter {

    public Activity mContext;
    public PageData mPageData;
    public PlaylistItemListResponse mResponse;
    public List<PlaylistItem> mList = new ArrayList<PlaylistItem>();

    public PlayListItemAdapter(Activity context) {
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mCurrentRow = convertView;
        final PostHolder holder;
        PlaylistItem mResult = getItem(position);

        if (mCurrentRow == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            mCurrentRow = inflater.inflate(R.layout.channels_item, parent, false);
            holder = new PostHolder();
            holder.title = (TextView) mCurrentRow.findViewById(R.id.title);
            holder.time = (TextView) mCurrentRow.findViewById(R.id.time);
            mCurrentRow.setTag(holder);
        } else {
            holder = (PostHolder) convertView.getTag();
        }
        holder.title.setText(mResult.getSnippet().getTitle());
        holder.time.setText(mResult.getSnippet().getPublishedAt().toString());
        return mCurrentRow;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public PlaylistItem getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class PostHolder {
        TextView id;
        TextView title;
        TextView time;
        TextView views;
    }

}