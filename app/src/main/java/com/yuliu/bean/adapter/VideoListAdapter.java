package com.yuliu.bean.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.squareup.picasso.Picasso;
import com.ytb.music_video.R;
import com.yuliu.bean.PageData;
import com.yuliu.bean.PageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoListAdapter extends BaseAdapter {

    public Map<View, YouTubeThumbnailLoader> mLoaders;
    public Activity mContext;
    public PageData mPageData;
    public List<PageItem> mList = new ArrayList<PageItem>();

    public VideoListAdapter(Activity context) {
        this.mContext = context;
        mLoaders = new HashMap<View, YouTubeThumbnailLoader>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mCurrentRow = convertView;
        final PostHolder holder;
        PageItem mResult = mList.get(position);

        if (mCurrentRow == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            mCurrentRow = inflater.inflate(R.layout.ytb_page_item_big, parent, false);
            holder = new PostHolder();
            holder.txtTitle = (TextView) mCurrentRow.findViewById(R.id.title);
            holder.thumbnailView = (ImageView) mCurrentRow.findViewById(R.id.thumbnail);
            mCurrentRow.setTag(holder);
        } else {
            holder = (PostHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(mResult.imageurl).placeholder(R.drawable.default_video_bg)
                .into(holder.thumbnailView);
        holder.txtTitle.setText(mResult.title);

        return mCurrentRow;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
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
        TextView txtTime;
    }

}