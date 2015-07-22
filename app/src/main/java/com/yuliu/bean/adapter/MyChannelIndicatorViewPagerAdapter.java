package com.yuliu.bean.adapter;

//package com.yuliu.bean.adapter;
//
//import java.util.List;
//
//import me.maxwin.view.XListView;
//import me.maxwin.view.XListView.IXListViewListener;
//import android.content.Intent;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.api.services.youtube.model.Channel;
//import com.google.api.services.youtube.model.GuideCategory;
//import com.google.api.services.youtube.model.PlaylistListResponse;
//import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorViewPagerAdapter;
//import com.yuliu.activity.AppApplication;
//import com.yuliu.activity.ChannelMainActivity;
//import com.yuliu.activity.MainAllActivity;
//import com.yuliu.activity.R;
//import com.yuliu.bean.PageViewHolder;
//import com.yuliu.util.UIHelper;
//
//public class MyChannelIndicatorViewPagerAdapter extends IndicatorViewPagerAdapter
//{
//	public LayoutInflater inflate;
//	public ChannelMainActivity mActivity;
//	public AppApplication application;
//
//	public MyChannelIndicatorViewPagerAdapter(LayoutInflater inflate, ChannelMainActivity mActivity,
//			AppApplication application)
//	{
//		super();
//		this.inflate = inflate;
//		this.mActivity = mActivity;
//		this.application = application;
//	}
//
//	public PlaylistListResponse mResponse = null;
//
//	@Override
//	public View getViewForTab(int postion, View convertView, ViewGroup container)
//	{
//		if (convertView == null)
//		{
//			convertView = inflate.inflate(R.layout.tab_top, container, false);
//		}
//		TextView textView = (TextView) convertView;
//		textView.setText(UIHelper.M_GUIDE_CATEGORIES.get(postion).getSnippet().getTitle());
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
//		final GuideCategory mPlaylistItem = UIHelper.M_GUIDE_CATEGORIES.get(postion);
//		if (application.mChannelsAdapter[postion] != null)
//		{
//
//		}
//		else
//		{
//
//			application.mChannelsAdapter[postion] = new ChannelsAdapter(mActivity);
//			application.mChannelsAdapter[postion].postion = postion;
//		}
//
//		holder.mListView.setAdapter(application.mChannelsAdapter[postion]);
//		holder.mListView.setPullRefreshEnable(true);// 能刷新
//		holder.mListView.setPullLoadEnable(true);// 能加载更多
//
//		holder.mListView.setXListViewListener(new IXListViewListener()
//		{
//
//			@Override
//			public void onRefresh()
//			{
//				// TODO Auto-generated method stub
//				if (!UIHelper.isLoading)
//				{
//					UIHelper.getChannels(mPlaylistItem.getId(), "", application.mChannelsAdapter[postion],
//							UIHelper.REFRESH);
//				}
//				UIHelper.onLoad(holder.mListView);
//
//			}
//
//			@Override
//			public void onLoadMore()
//			{
//				if (!UIHelper.isLoading)
//				{
//					if (application.mChannelsAdapter[postion].mResponse != null
//							&& !TextUtils.isEmpty(application.mChannelsAdapter[postion].mResponse.getNextPageToken()))
//					{
//						String pageToken = application.mChannelsAdapter[postion].mResponse.getNextPageToken();
//						UIHelper.getChannels(mPlaylistItem.getId(), pageToken, application.mChannelsAdapter[postion],
//								UIHelper.MORE);
//					}
//					else
//					{
//						Toast.makeText(mActivity, "no more", 0).show();
//					}
//
//				}
//				UIHelper.onLoad(holder.mListView);
//			}
//		});
//
//		holder.mListView.setOnItemClickListener(new OnItemClickListener()
//		{
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
//			{
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(mActivity, MainAllActivity.class);
//				List<Channel> mList = application.mChannelsAdapter[postion].mList;
//				Channel mChannel = mList.get(arg2 - 1);
//				intent.putExtra("relatedPlaylists", mChannel.getId());
//				mActivity.startActivity(intent);
//			}
//
//		});
//
//		UIHelper.getChannelsInit(mPlaylistItem.getId(), "", application.mChannelsAdapter[postion], UIHelper.REFRESH);
//		UIHelper.onLoad(holder.mListView);
//		return convertView;
//
//	}
//
//	@Override
//	public int getCount()
//	{
//		return UIHelper.M_GUIDE_CATEGORIES.size();
//	}
//
// }
