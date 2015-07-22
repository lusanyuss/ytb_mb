package com.yuliu.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.example.hellonobase.DatabaseHelper;
import com.umeng.analytics.MobclickAgent;
import com.ytb.music_video.R;
import com.yuliu.bean.MyChannels;
import com.yuliu.bean.MyPlayList;
import com.yuliu.util.AnalyticsUtil;
import com.yuliu.util.MyContants;
import com.yuliu.util.MyLog;
import com.yuliu.util.SharedHelper;
import com.yuliu.util.ToastFactory;
import com.yuliu.util.UIHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WelcomeActivity extends BaseActivity {
    public static final String INIT_CHANNELS_CACHE = "INIT_CHANNELS_CACHE";
    public Handler mHandler = new Handler();
    public AppApplication mApplication;
    public AsyncTask<Void, Void, HashMap<Integer, List<MyPlayList>>> mAsyncTask;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_welcome);
        AnalyticsUtil.sendScreenName(this.getClass().getSimpleName());
        mApplication = (AppApplication) getApplication();
        mApplication.loadInterstitial();
        mAsyncTask = new AsyncTask<Void, Void, HashMap<Integer, List<MyPlayList>>>() {
            @Override
            protected HashMap<Integer, List<MyPlayList>> doInBackground(Void... params) {
                try {
                    long oldtime = SharedHelper.getCacheTime(mApplication, INIT_CHANNELS_CACHE);
                    long currentTime = System.currentTimeMillis();
                    if (oldtime != 0 && currentTime - oldtime <= MyContants.CacheTime) {
                        MyLog.v("使用缓存");
                        MyChannels mChannels = MyChannels.String2obj(SharedHelper.getCacheString(mApplication,
                                INIT_CHANNELS_CACHE));
                        mApplication.mPlaylists = mChannels.channelHashMap;
                    } else {
                        MyLog.v("使用网络");
                        mApplication.mPlaylists = getPlayListByChannelId(mApplication, databaseHelper);
                        if (mApplication.mPlaylists.size() == UIHelper.getInstance().mChannels.length) {
                            MyChannels mChannels = new MyChannels();
                            mChannels.channelHashMap = mApplication.mPlaylists;
                            String jsonString = MyChannels.obj2String(mChannels);
                            SharedHelper.setCacheString(mApplication, INIT_CHANNELS_CACHE, jsonString);
                        }
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return mApplication.mPlaylists;
            }

            @Override
            protected void onPostExecute(HashMap<Integer, List<MyPlayList>> result) {
                super.onPostExecute(result);
                if (result != null && result.size() == UIHelper.getInstance().mChannels.length) {
                    Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    mApplication.showInterstitial();
                } else {
                    SharedHelper.setCacheTime(mApplication, INIT_CHANNELS_CACHE, 0);
                    ToastFactory.showToast(mApplication, "Network anomaly");
                }
                mAsyncTask = null;
            }

        }.execute();

    }

    public void onResume() {
        super.onResume();
        if (MyContants.isOnLine) {
            MobclickAgent.onResume(this);
        }
    }

    public void onPause() {
        super.onPause();
        if (MyContants.isOnLine) {
            MobclickAgent.onPause(this);
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (mAsyncTask != null && mAsyncTask.isCancelled() == false) {
            mAsyncTask.cancel(false);
            mAsyncTask = null;
        }
        finish();
    }

    private HashMap<Integer, List<MyPlayList>> getPlayListByChannelId(final AppApplication mApplication,
                                                                      DatabaseHelper databaseHelper) throws Exception {

        final HashMap<Integer, List<MyPlayList>> mPlaylists = new HashMap<Integer, List<MyPlayList>>();
        final CountDownLatch latch = new CountDownLatch(UIHelper.getInstance().mChannels.length);// 两个工人的协作
        for (int i = 0; i < UIHelper.getInstance().mChannels.length; i++) {
            final int postion = i;
            Runnable mRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        List<MyPlayList> mLists = new ArrayList<MyPlayList>();
                        String key = UIHelper.getInstance().mChannels[postion][0] + "_"
                                + UIHelper.getInstance().mChannels[postion][1];
                        mLists = UIHelper.getInstance().getHomeById(UIHelper.getInstance().mChannels[postion][1]);
                        if (mLists != null && mLists.size() > 0) {
                            mPlaylists.put(postion, mLists);
                        }
                        latch.countDown();// 工人完成工作，计数器减一
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            };
            Thread worker1 = new Thread(mRunnable);
            worker1.start();
        }
        latch.await();
        return mPlaylists;
    }

}
