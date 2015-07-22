package com.yuliu.bean.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.ThumbnailDetails;
import com.squareup.picasso.Picasso;
import com.ytb.music_video.R;
import com.yuliu.bean.PageData;

import java.util.ArrayList;
import java.util.List;

public class ChannelsAdapter extends BaseAdapter {

    public Activity mActivity;
    public PageData mPageData;
    public ChannelListResponse mResponse;
    public List<Channel> mList = new ArrayList<Channel>();
    public int postion = -1;

    public ChannelsAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mCurrentRow = convertView;
        final PostHolder holder;
        Channel mResult = getItem(position);

        if (mCurrentRow == null) {
            LayoutInflater inflater = mActivity.getLayoutInflater();
            mCurrentRow = inflater.inflate(R.layout.channels_item, parent, false);
            holder = new PostHolder();
            holder.title = (TextView) mCurrentRow.findViewById(R.id.title);
            holder.thumbnail = (ImageView) mCurrentRow.findViewById(R.id.thumbnail);
            holder.description = (TextView) mCurrentRow.findViewById(R.id.description);
            holder.view_count = (TextView) mCurrentRow.findViewById(R.id.view_count);
            mCurrentRow.setTag(holder);
        } else {
            holder = (PostHolder) convertView.getTag();
        }
        holder.title.setText(mResult.getSnippet().getTitle());
        holder.description.setText(mResult.getSnippet().getDescription());

        ThumbnailDetails mThumbnailDetails = mResult.getSnippet().getThumbnails();
        if (mThumbnailDetails != null) {
            Thumbnail hThumbnail = mThumbnailDetails.getHigh();
            if (hThumbnail != null) {
                Picasso.with(mActivity).load(mResult.getSnippet().getThumbnails().getHigh().getUrl())
                        .placeholder(R.drawable.image_default_color).into(holder.thumbnail);
            }
        }

        holder.view_count.setText(mResult.getStatistics().getViewCount() + " views" + "   "
                + mResult.getStatistics().getVideoCount() + "videos");

        return mCurrentRow;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Channel getItem(int position) {
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
        ImageView thumbnail;
        TextView description;
        TextView view_count;

    }

}