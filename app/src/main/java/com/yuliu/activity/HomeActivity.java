package com.yuliu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hellonobase.DatabaseHelper;
import com.example.hellonobase.HistoryVideo;
import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggableView;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.ytb.music_video.R;
import com.yuliu.bean.PageItem;
import com.yuliu.bean.adapter.HistoryAdapter;
import com.yuliu.bean.adapter.HomeIndicatorViewPagerAdapter;
import com.yuliu.event.PlayByHtmlEvent;
import com.yuliu.event.PlayByYouTubeEvent;
import com.yuliu.event.PlayEvent;
import com.yuliu.listener.MyPlaybackEventListener;
import com.yuliu.listener.MyPlayerStateChangeListener;
import com.yuliu.util.AnalyticsUtil;
import com.yuliu.util.Auth;
import com.yuliu.util.MyAdListener;
import com.yuliu.util.MyContants;
import com.yuliu.util.MyLog;
import com.yuliu.util.ResourceUtil;
import com.yuliu.util.SharedHelper;
import com.yuliu.util.UIHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;
import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

public class HomeActivity extends YouTubeFailureRecoveryActivity {

    /**
     * The log tag.
     */
    public static final String LOG_TAG = "MainActivity_ad";
    public static boolean isFlag = false;
    private static Boolean isExit = false;
    /**
     * The view to show the ad.
     */

    public String mPageName = "MainAllActivity";
    public IndicatorViewPager indicatorViewPager;
    public HomeIndicatorViewPagerAdapter adapter;
    // public ImageView more, collect;
    // public ImageView mFestival;
    public DatabaseHelper databaseHelper = null;
    public ScrollIndicatorView indicator;
    public ViewPager viewPager;
    public LinearLayout shuping;
    public LinearLayout hengping;
    public RelativeLayout mainUILayout;
    public View login_layout;
    // public ListView draggable_listView;
    // public SearchAdapter draggable_listView_adapter;
    public LinearLayout layout;
    public TelephonyManager mTelephonyManager;
    public LayoutInflater inflater;
    public Activity mActivity;
    public AppApplication application;
    public ImageView menu_btn;
    public SlidingMenu mSlidingMenu;
    public View[] menu;
    public TextView title;
    public View menu8;
    /**
     * Initialize and configure the DraggablePanel widget with two fragments and
     * some attributes.
     */

    public Handler mHandler = new Handler();
    private YouTubePlayerView mPlayerView;
    private View main_view;
    private View history_view;
    private XListView history_listView1;
    private HistoryAdapter mHistoryAdapter;
    private MyAdListener adView_play_listener;
    private MyAdListener adView_view_listener;
    private MyAdListener adView_big_play_listener;
    private MyAdListener adView_chao_big_play_listener;
    private AdView adView_play;
    private AdView adView_view;
    private AdView adView_big_play;
    private AdView adView_chao_big_play;
    private WebView mWebView;
    private LinearLayout mWebLayout;
    private ImageView pailie;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        AnalyticsUtil.sendScreenName(this.getClass().getSimpleName());
        EventBus.getDefault().register(this);
        MyLog.v("onCreate");
        // 初始化语言
        setContentView(R.layout.main_ui);
        mActivity = this;
        inflater = this.getLayoutInflater();
        application = (AppApplication) this.getApplication();
        application.isBig = SharedHelper.getIsBigShow(mActivity);
        databaseHelper = getHelper();
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);

        if (MyContants.isOnLine) {
            MobclickAgent.openActivityDurationTrack(false);
            MobclickAgent.updateOnlineConfig(this);
        }
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        MyLog.v("设备号码:" + mTelephonyManager.getDeviceId());


        addAdmob();// 加载广告
        initDraggableView();
        initView();
        initWebView();
        initVideo();
        initSlidingMenu();
        initViewPager();// 初始化viewpager

        // 初始化数据
        int postion = SharedHelper.getPostion(mActivity);
        initData(postion);
        MobclickAgent.onEvent(mActivity, UIHelper.getInstance().mChannels[postion][0]);
        AnalyticsUtil.sendEvent("SlidingMenu", UIHelper.getInstance().mChannels[postion][0], "");
    }

    public void initSlidingMenu() {
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        mSlidingMenu.setShadowDrawable(R.drawable.shadow);
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        mSlidingMenu.setFadeDegree(0.35f);
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        mSlidingMenu.setMenu(R.layout.slidingmenu);

        mSlidingMenu.setOnClosedListener(new OnClosedListener() {
            @Override
            public void onClosed() {
                // TODO Auto-generated method stub
                if (!application.draggableView.isClosed() && application.draggableView.isShown()
                        && application.mPlayer != null && application.mPlayer.getCurrentTimeMillis() > 0) {
                    application.mPlayer.play();
                }
                menu_btn.setImageResource(R.drawable.menu);
                application.loadInterstitial();
                AnalyticsUtil.sendEvent("SlidingMenu", "Close", "");
            }
        });

        mSlidingMenu.setOnOpenedListener(new OnOpenedListener() {
            @Override
            public void onOpened() {
                // TODO Auto-generated method stub
                if (!application.draggableView.isClosed() && application.draggableView.isShown()
                        && application.mPlayer != null && application.mPlayer.isPlaying()) {
                    application.mPlayer.pause();
                }
                menu_btn.setImageResource(R.drawable.menu_shu);
                // application.showInterstitial();
                AnalyticsUtil.sendEvent("SlidingMenu", "Open", "");
            }
        });

        menu_btn = (ImageView) findViewById(R.id.menu_btn);
        menu_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.toggle();
                AnalyticsUtil.sendEvent("HomeActivity", "title_btn", "");
            }
        });

        menu = new View[UIHelper.getInstance().mChannels.length];
        for (int i = 0; i < menu.length; i++) {
            final int postion = i;
            menu[postion] = findViewById(ResourceUtil.getId(mActivity, "menu" + postion));
            menu[postion].setVisibility(View.VISIBLE);
            ((TextView) menu[postion].findViewById(R.id.textView1))
                    .setText(UIHelper.getInstance().mChannels[postion][0]);
            ((ImageView) menu[postion].findViewById(R.id.imageView1)).setImageResource(ResourceUtil.getDrawableId(
                    mActivity, UIHelper.getInstance().mChannels[postion][2]));
            menu[postion].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    main_view.setVisibility(View.VISIBLE);
                    history_view.setVisibility(View.GONE);

                    mSlidingMenu.toggle();
                    for (View mView : menu) {
                        mView.setBackgroundResource(R.color.transparent);
                    }
                    menu8.setBackgroundResource(R.color.transparent);
                    v.setBackgroundResource(R.color.btn_bg_selecter);
                    initData(postion);

                    MobclickAgent.onEvent(mActivity, "menu" + postion);
                    AnalyticsUtil.sendEvent("SlidingMenu", UIHelper.getInstance().mChannels[postion][0], "");
                }
            });
        }
        // menu[0].setBackgroundResource(R.color.btn_bg_selecter);
        title = (TextView) findViewById(R.id.title);

        // 历史按钮
        menu8 = findViewById(ResourceUtil.getId(mActivity, "menu" + 8));
        ((TextView) menu8.findViewById(R.id.textView1)).setText("History");
        ((ImageView) menu8.findViewById(R.id.imageView1)).setImageResource(ResourceUtil.getDrawableId(mActivity,
                "menu_bg_" + 8));
        menu8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                main_view.setVisibility(View.GONE);
                history_view.setVisibility(View.VISIBLE);
                List<PageItem> mItems = getAlData();

                mHistoryAdapter.mList = mItems;
                mHistoryAdapter.notifyDataSetChanged();

                if (mHistoryAdapter.mList.size() > 0) {
                    history_listView1.setSelection(1);
                }

                mSlidingMenu.toggle();
                for (View mView : menu) {
                    mView.setBackgroundResource(R.color.transparent);
                }
                v.setBackgroundResource(R.color.btn_bg_selecter);
                title.setText(MyContants.History);
                SharedHelper.setPostion(mActivity, 0);
                MobclickAgent.onEvent(mActivity, "history");
                AnalyticsUtil.sendEvent("SlidingMenu", "history", "");
            }
        });
    }

    public void initData(int postion) {
        adapter = new HomeIndicatorViewPagerAdapter(inflater, mActivity, application, databaseHelper);
        adapter.mPlaylists = application.mPlaylists.get(postion);
        indicatorViewPager.setAdapter(adapter);
        indicatorViewPager.notifyDataSetChanged();


        menu[postion].setBackgroundResource(R.color.btn_bg_selecter);
        title.setText(UIHelper.getInstance().mChannels[postion][0]);
        SharedHelper.setPostion(mActivity, postion);
    }

    public void initViewPager() {
        mainUILayout = (RelativeLayout) findViewById(R.id.m_ui);
        shuping = (LinearLayout) findViewById(R.id.shuping);
        hengping = (LinearLayout) findViewById(R.id.hengping);
        // viewPager.setPageMargin((int) getResources().getDimensionPixelOffset(
        // R.dimen.yx4dp));
        indicator = (ScrollIndicatorView) findViewById(R.id.guide_indicator);
        indicator.setScrollBar(new ColorBar(this, getResources().getColor(R.color.transparent), 5));

        viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        viewPager.setOffscreenPageLimit(1);

        indicatorViewPager = new IndicatorViewPager(this, indicator, viewPager);
        indicatorViewPager.menu = mSlidingMenu;

    }

    public void addAdmob() {
        // 首页广告
        adView_view = (AdView) findViewById(R.id.adView_view);
        adView_view_listener = new MyAdListener(mActivity, "adView_view");
        adView_view.setAdListener(adView_view_listener);
        UIHelper.getInstance().ShowAd(adView_view);
        // Create an ad.
        // 播放小广告
        adView_play = (AdView) findViewById(R.id.adView_play);
        adView_play_listener = new MyAdListener(mActivity, "adView_play");
        adView_play.setAdListener(adView_play_listener);
        UIHelper.getInstance().ShowAd(adView_play);

        // 播放大广告
        // adView_big_play = (AdView) findViewById(R.id.adView_big_play);
        // adView_big_play_listener = new MyAdListener(mActivity,
        // "adView_big_play");
        // adView_big_play.setAdListener(adView_big_play_listener);
        // UIHelper.getInstance().ShowAd(adView_big_play);
        //
        // // 播放超大广告
        adView_chao_big_play = (AdView) findViewById(R.id.adView_chao_big_play);
        adView_chao_big_play_listener = new MyAdListener(mActivity, "adView_chao_big_play");
        adView_chao_big_play.setAdListener(adView_chao_big_play_listener);
        UIHelper.getInstance().ShowAd(adView_chao_big_play);

    }

    /**
     * 为程序创建桌面快捷方式
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        MyLog.v("onRestart");
        isFlag = true;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.v("onStop");
        CloseWebView();
    }

    @Override
    protected void onDestroy() {
        if (adView_play != null) {
            adView_play.destroy();
        }
        if (adView_view != null) {
            adView_view.destroy();
        }
        if (adView_big_play != null) {
            adView_big_play.destroy();
        }
        if (adView_chao_big_play != null) {
            adView_chao_big_play.destroy();
        }

        EventBus.getDefault().unregister(this);
        super.onDestroy();
        MyLog.v("onDestroy");

        if (application.mPlayer != null) {
            application.mPlayer.release();
            application.mPlayer = null;
        }

        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    /**
     * You'll need this in your class to get the helper from the manager once
     * per class.
     */

    @Override
    protected void onResume() {
        adview_resume();
        super.onResume();
        MyLog.v("onResume");
        // 统计
        if (MyContants.isOnLine) {
            MobclickAgent.onPageStart(mPageName);
            MobclickAgent.onResume(mActivity);
        }
    }

    @Override
    public void onPause() {

        adview_pause();
        super.onPause();
        MyLog.v("onPause");
        if (MyContants.isOnLine) {
            MobclickAgent.onPageEnd(mPageName);
            MobclickAgent.onPause(mActivity);
        }

    }

    @Override
    public void onBackPressed() {
        // 请求竖屏

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (application.draggableView.isMaximized() && application.draggableView.isShown()) {
                application.draggableView.minimize();
            } else {
                exitBy2Click();

            }
        }
        return false;
    }

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "Press again to exit the program", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            application.showInterstitial();
            finish();
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    private void initView() {
        pailie = (ImageView) findViewById(R.id.pailie);
        pailie.setSelected(application.isBig);
        main_view = findViewById(R.id.main_view);
        main_view.setVisibility(View.VISIBLE);
        history_view = findViewById(R.id.history_view);
        history_view.setVisibility(View.GONE);
        history_listView1 = (XListView) findViewById(R.id.history_listView1);

        application.video_title = (TextView) findViewById(R.id.video_title);
        application.draggableView = (DraggableView) findViewById(R.id.draggable_view);

        List<PageItem> mPageItems = getAlData();
        mHistoryAdapter = new HistoryAdapter(this);
        mHistoryAdapter.mList = mPageItems;
        history_listView1.setAdapter(mHistoryAdapter);
        history_listView1.setPullRefreshEnable(false);// 能刷新
        history_listView1.setPullLoadEnable(true);// 能加载更多
        history_listView1.mFooterView.setVisibility(View.GONE);

        history_listView1.setXListViewListener(new IXListViewListener() {
            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                UIHelper.getInstance().onLoad(history_listView1);

            }

            @Override
            public void onLoadMore() {
                UIHelper.getInstance().onLoad(history_listView1);
            }
        });

        pailie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelect = !pailie.isSelected();
                pailie.setSelected(isSelect);
                if (isSelect) {
                    application.isBig = true;
                    int postion = SharedHelper.getPostion(mActivity);
                    initData(postion);
                    MobclickAgent.onEvent(mActivity, "大排序");
                    AnalyticsUtil.sendEvent("app", "大排序", "");
                } else {
                    application.isBig = false;
                    int postion = SharedHelper.getPostion(mActivity);
                    initData(postion);
                    MobclickAgent.onEvent(mActivity, "小排序");
                    AnalyticsUtil.sendEvent("app", "小排序", "");
                }
                SharedHelper.setIsBigShow(mActivity, isSelect);
            }
        });

        UIHelper.getInstance().onLoad(history_listView1);

    }

    /**
     * Method triggered when the iv_thumbnail widget is clicked. This method
     * maximize the DraggablePanel.
     */
    void onContainerClicked() {
        application.draggableView.maximize();
    }

    public void onEventMainThread(PlayByHtmlEvent mEvent) {

        AnalyticsUtil.sendEvent("play", "PlayByHtmlEvent", "");

        mWebView.setVisibility(View.VISIBLE);
        mWebLayout.setVisibility(View.VISIBLE);
        if (mPlayerView != null) {
            mPlayerView.setVisibility(View.GONE);
        }
        if (MyContants.isOnLine) {
            MobclickAgent.onEvent(mActivity, "html_play");
        }

        String html = getHtml(mEvent.mItem.videoid);
        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // application.showInterstitial();

            }
        }, 3000);
    }

    public void CloseWebView() {
        if (mWebView != null && mWebView.isShown()) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        }
    }

    public void onEventMainThread(PlayByYouTubeEvent mEvent) {
        AnalyticsUtil.sendEvent("play", "PlayByYouTubeEvent", "");
        mWebView.setVisibility(View.GONE);
        mWebLayout.setVisibility(View.GONE);

        if (mPlayerView != null) {
            mPlayerView.setVisibility(View.VISIBLE);
        }
        if (application.mPlayer != null) {
            application.mPlayer.loadVideo(mEvent.mItem.videoid);

        }
    }

    public void onEventMainThread(PlayEvent mEvent) {

        AnalyticsUtil.sendEvent("play", "PlayEvent", "");
        if (MyContants.isOnLine) {
            MobclickAgent.onEvent(mActivity, "click_play_btn");
        }

        if (application.draggableView != null) {
            application.draggableView.setVisibility(View.VISIBLE);
            application.draggableView.maximize();
        }
        application.video_title.setText(mEvent.mItem.title);
        application.mPageItem = mEvent.mItem;
        // 存储记录
        addToDatabase(mEvent.mItem);
        if (application.is_install_youtube) {
            CloseWebView();
            EventBus.getDefault().post(new PlayByYouTubeEvent(mEvent.mItem));
        } else {
            EventBus.getDefault().post(new PlayByHtmlEvent(mEvent.mItem));
        }

//		mHandler.postDelayed(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				application.showInterstitial();
//			}
//		}, 5000);
    }

    private void addToDatabase(final PageItem mResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Dao<HistoryVideo, Integer> mDao = databaseHelper.getHistoryVideo();
                    List<HistoryVideo> datas = mDao.queryForEq("video_id", mResult.videoid);

                    if (datas != null && datas.size() == 1) {
                        // 已经收藏
                        MyLog.v("已经存储");
                        HistoryVideo mHistoryVideo = datas.get(0);
                        mDao.delete(mHistoryVideo);
                        mDao.create(mHistoryVideo);
                    } else if (datas == null || datas.size() == 0) {

                        try {
                            List<HistoryVideo> list = mDao.queryForAll();
                            if (list.size() > MyContants.History_num) {
                                mDao.delete(list.get(0));
                            }
                        } catch (SQLException e) {
                        }

                        HistoryVideo data = new HistoryVideo(System.currentTimeMillis());
                        data.image_url = mResult.imageurl;
                        data.title = mResult.title;
                        data.video_id = mResult.videoid;
                        data.published_at = mResult.publishedAt;
                        mDao.create(data);
                        AnalyticsUtil.sendEvent("history", "addDatabase", "");
                        MyLog.v("储存历史记录");

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String getHtml(String videoId) {
        // TODO Auto-generated method stub
        String html = "<html><body><iframe width=\"100%\" height=\"90%\" src=\"http://www.youtube.com/embed/"
                + videoId
                + "?loop=1&vq=small\" border=\"0\" frameborder=\"0\"  marginwidth=\"0\" marginheight=\"0\" scrolling=\"no\" allowtransparency=\"yes\"  frameborder=\"0\"></iframe></body></html>";
        return html;
    }

    public void initWebView() {

        mWebLayout = (LinearLayout) findViewById(R.id.mWebLayout);
        mWebView = (WebView) findViewById(R.id.mWebview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT > 7) {
            settings.setPluginState(PluginState.ON);
        }
    }

    public void initVideo() {
        mPlayerView = (YouTubePlayerView) findViewById(R.id.mPlayerView);

        if (application.is_install_youtube) {
            mPlayerView.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);
            mWebLayout.setVisibility(View.GONE);
            if (application.mPlayer != null) {
                application.mPlayer.release();
                application.mPlayer = null;
            }
            // TODO Auto-generated method stub
            // 播放节目初始化
            mPlayerView.initialize(Auth.apiKey, new OnInitializedListener() {
                @Override
                public void onInitializationSuccess(Provider arg0, YouTubePlayer player, boolean arg2) {
                    application.mPlayer = player;
                    application.mPlayer.setPlayerStyle(PlayerStyle.DEFAULT);

                    application.playerStateChangeListener = new MyPlayerStateChangeListener();
                    application.playerStateChangeListener.setApplication(application);

                    application.playbackEventListener = new MyPlaybackEventListener();
                    application.playbackEventListener.setApplication(application);

                    application.mPlayer.setPlayerStateChangeListener(application.playerStateChangeListener);
                    application.mPlayer.setPlaybackEventListener(application.playbackEventListener);

                }

                @Override
                public void onInitializationFailure(Provider arg0, YouTubeInitializationResult arg1) {

                }
            });
        } else {
            mPlayerView.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
        }
    }

    public void initDraggableView() {
        // TODO Auto-generated method stub
        application.draggableView = (DraggableView) findViewById(R.id.draggable_view);
        application.draggableView.setDraggableListener(new DraggableListener() {
            @Override
            public void onMinimized() {
                MyLog.v("onMinimized");
                AnalyticsUtil.sendEvent("DraggableView", "onMinimized", "");
                if (application.mPlayer != null) {
                    application.mPlayer.setPlayerStyle(PlayerStyle.MINIMAL);
                }
                if (application.draggableView.isMinimized()) {
                    application.draggableView.maximize();
                }
                application.loadInterstitial();
                if (adView_play != null) {
                    adView_play.resume();
                }
                if (adView_view != null) {
                    adView_view.pause();
                }
                if (adView_big_play != null) {
                    adView_big_play.pause();
                }
                if (adView_chao_big_play != null) {
                    adView_chao_big_play.pause();
                }

            }

            @Override
            public void onMaximized() {
                // TODO Auto-generated method stub
                MyLog.v("onMaximized");
                AnalyticsUtil.sendEvent("DraggableView", "onMaximized", "");
                if (application.mPlayer != null) {
                    application.mPlayer.setPlayerStyle(PlayerStyle.DEFAULT);
                }

                // mHandler.postDelayed(new Runnable()
                // {
                // @Override
                // public void run()
                // {
                // // TODO Auto-generated method stub
                // application.showInterstitial();
                // }
                // }, 5000);

                if (adView_play != null) {
                    adView_play.pause();
                }
                if (adView_view != null) {
                    adView_view.resume();
                }
                if (adView_big_play != null) {
                    adView_big_play.resume();
                }
                if (adView_chao_big_play != null) {
                    adView_chao_big_play.resume();
                }

            }

            @Override
            public void onClosedToRight() {
                // TODO Auto-generated method stub
                AnalyticsUtil.sendEvent("DraggableView", "onClosedToRight", "");
                CloseWebView();
            }

            @Override
            public void onClosedToLeft() {
                // TODO Auto-generated method stub
                AnalyticsUtil.sendEvent("DraggableView", "onClosedToLeft", "");
                CloseWebView();
            }
        });
        application.DraggableView_Hide();
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return mPlayerView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * You'll need this in your class to get the helper from the manager once
     * per class.
     */
    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    /**
     * Do our sample database stuff as an example.
     */
    public List<PageItem> getAlData() {

        List<PageItem> mPageItems = new ArrayList<PageItem>();
        List<HistoryVideo> list = new ArrayList<HistoryVideo>();
        try {
            Dao<HistoryVideo, Integer> simpleDao = getHelper().getHistoryVideo();
            list = simpleDao.queryForAll();
            for (HistoryVideo historyVideo : list) {
                PageItem item = new PageItem();

                item.imageurl = historyVideo.image_url;
                item.publishedAt = historyVideo.published_at;
                item.title = historyVideo.title;
                item.videoid = historyVideo.video_id;

                mPageItems.add(0, item);
            }
        } catch (SQLException e) {
        }
        return mPageItems;
    }

    private void adview_resume() {
        if (adView_play != null) {
            adView_play.resume();
        }
        if (adView_view != null) {
            adView_view.resume();
        }
        if (adView_big_play != null) {
            adView_big_play.resume();
        }
        if (adView_chao_big_play != null) {
            adView_chao_big_play.resume();
        }
    }

    private void adview_pause() {
        if (adView_play != null) {
            adView_play.pause();
        }
        if (adView_view != null) {
            adView_view.pause();
        }
        if (adView_big_play != null) {
            adView_big_play.pause();
        }
        if (adView_chao_big_play != null) {
            adView_chao_big_play.pause();
        }
    }
}
