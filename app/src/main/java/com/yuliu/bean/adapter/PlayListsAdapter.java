package com.yuliu.bean.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.ytb.music_video.R;
import com.yuliu.bean.PageData;

import java.util.ArrayList;
import java.util.List;

public class PlayListsAdapter extends BaseAdapter {

    public Activity mContext;
    public PageData mPageData;
    public PlaylistListResponse mResponse;
    public List<Playlist> mList = new ArrayList<Playlist>();

    public PlayListsAdapter(Activity context) {
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mCurrentRow = convertView;
        final PostHolder holder;
        Playlist mResult = getItem(position);

        if (mCurrentRow == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            mCurrentRow = inflater.inflate(R.layout.channels_item, parent, false);
            holder = new PostHolder();
            holder.title = (TextView) mCurrentRow.findViewById(R.id.title);
            mCurrentRow.setTag(holder);
        } else {
            holder = (PostHolder) convertView.getTag();
        }
        holder.title.setText(mResult.getSnippet().getTitle());

        return mCurrentRow;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Playlist getItem(int position) {
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
    }

}