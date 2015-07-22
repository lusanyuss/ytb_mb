package com.yuliu.bean.adapter;

//package com.yuliu.bean.adapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import me.maxwin.view.XListView;
//import me.maxwin.view.XListView.IXListViewListener;
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.ads.AdView;
//import com.google.api.services.youtube.model.Playlist;
//import com.google.api.services.youtube.model.PlaylistListResponse;
//import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorViewPagerAdapter;
//import com.yuliu.activity.AppApplication;
//import com.yuliu.activity.R;
//import com.yuliu.bean.PageViewHolder;
//import com.yuliu.util.MyUIHelper;
//
//public class MyIndicatorViewPagerAdapter extends IndicatorViewPagerAdapter
//{
//
//	public AdView adView_play;
//	public LayoutInflater inflate;
//	public Activity mActivity;
//	public AppApplication application;
//	public MyUIHelper myUIHelper=MyUIHelper.getInstance();
//
//	public MyIndicatorViewPagerAdapter(AdView adView_play, LayoutInflater inflate, Activity mContext,
//			AppApplication application)
//	{
//		super();
//		this.inflate = inflate;
//		this.mActivity = mContext;
//		this.application = application;
//		this.mAdView = adView_play;
//	}
//
//	public PlaylistListResponse mResponse = null;
//	public List<Playlist> mPlaylists = new ArrayList<Playlist>();
//
//	@Override
//	public View getViewForTab(int postion, View convertView, ViewGroup container)
//	{
//		if (convertView == null)
//		{
//			convertView = inflate.inflate(R.layout.tab_top, container, false);
//		}
//		TextView textView = (TextView) convertView;
//		textView.setText(mPlaylists.get(postion).getSnippet().getTitle());
//		return convertView;
//	}
//
//	@Override
//	public View getViewForPage(final int postion, View convertView, ViewGroup container)
//	{
//
//		final int mypostion;
//		mypostion = postion;
//		final PageViewHolder holder;
//		if (convertView == null)
//		{
//			holder = new PageViewHolder();
//			convertView = inflate.inflate(R.layout.ytb_page, null);
//			holder.mListView = (XListView) convertView.findViewById(R.id.listView1);
//			convertView.setTag(holder);
//		}
//		else
//		{
//			holder = (PageViewHolder) convertView.getTag();
//		}
//
//		final Playlist mPlaylistItem = mPlaylists.get(postion);
//		final VideoAdapter adapter = new VideoAdapter(adView_play, inflate, mActivity, application);
//
//		holder.mListView.setAdapter(adapter);
//		holder.mListView.setPullRefreshEnable(true);// 能刷新
//		holder.mListView.setPullLoadEnable(true);// 能加载更多
//
//		holder.mListView.setXListViewListener(new IXListViewListener()
//		{
//			@Override
//			public void onRefresh()
//			{
//				// TODO Auto-generated method stub
//				if (!myUIHelper.isLoading)
//				{
//					myUIHelper.getPlayListItems(mPlaylistItem.getId(), "", adapter, myUIHelper.REFRESH);
//				}
//				myUIHelper.onLoad(holder.mListView);
//
//			}
//
//			@Override
//			public void onLoadMore()
//			{
//				if (!myUIHelper.isLoading)
//				{
//					if (adapter.nextPageToken != null)
//					{
//						myUIHelper.getPlayListItems(mPlaylistItem.getId(), adapter.nextPageToken, adapter, myUIHelper.MORE);
//					}
//					else
//					{
//						Toast.makeText(mActivity, "no more", 0).show();
//					}
//
//				}
//				myUIHelper.onLoad(holder.mListView);
//			}
//		});
//
//		myUIHelper.getPlayListItemsInit(mPlaylistItem.getId(), "", adapter, myUIHelper.REFRESH);
//		myUIHelper.onLoad(holder.mListView);
//		return convertView;
//
//	}
//
//	@Override
//	public int getCount()
//	{
//		return mPlaylists.size();
//	}
//
// }
