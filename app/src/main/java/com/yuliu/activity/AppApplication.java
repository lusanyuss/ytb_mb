package com.yuliu.activity;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.pedrovgs.DraggableView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.ytb.music_video.R;
import com.yuliu.bean.MyHomeTag;
import com.yuliu.bean.MyPlayList;
import com.yuliu.bean.MyTagManager;
import com.yuliu.bean.PageItem;
import com.yuliu.bean.adapter.ChannelsAdapter;
import com.yuliu.listener.MyPlaybackEventListener;
import com.yuliu.listener.MyPlayerStateChangeListener;
import com.yuliu.util.MyLog;
import com.yuliu.util.StringUtils;
import com.yuliu.util.UIHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class AppApplication extends Application {

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;
    public static final int PAGE_SIZE = 20;// 默认分页大小
    public static HashMap<Integer, List<MyPlayList>> mPlaylists = new HashMap<Integer, List<MyPlayList>>();
    public static AppApplication mApplication;
    public YouTubePlayerView mPlayerView;
    public YouTubePlayer mPlayer;
    public MyPlayerStateChangeListener playerStateChangeListener;
    public MyPlaybackEventListener playbackEventListener;
    public DraggableView draggableView;
    public ChannelsAdapter[] mChannelsAdapter;
    public boolean login = false; // 登录状态
    public int loginUid = 0; // 登录用户的id
    public Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();
    public AdView mAdView;
    public InterstitialAd mInterstitialAd;
    public ArrayList<MyHomeTag> myHomeTags;
    public TextView video_title;
    public ImageView video_back;
    public boolean is_chaye_open = false;
    public boolean is_install_youtube = false;
    public PageItem mPageItem;
    public boolean isBig = true;
    public HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    public synchronized Tracker getTracker(TrackerName trackerName) {
        if (!mTrackers.containsKey(trackerName)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this.getApplicationContext());
            Tracker t = (trackerName == TrackerName.APP_TRACKER) ? analytics.newTracker("")
                    : (trackerName == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker)
                    : analytics.newTracker("");
            mTrackers.put(trackerName, t);
        }
        return mTrackers.get(trackerName);
    }

    public void DraggableView_Show() {
        if (draggableView != null) {
            draggableView.setVisibility(View.VISIBLE);
            draggableView.maximize();
        }
    }

    public boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    public void DraggableView_Hide() {
        draggableView.setVisibility(View.GONE);
        draggableView.closeToRight();
    }

    public void initInterstitial() {

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.PLAY_START_CHAYESHI));
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                // TODO Auto-generated method stub
                super.onAdClosed();
                MyLog.v("mInterstitialAd-onAdClosed");

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // TODO Auto-generated method stub
                super.onAdFailedToLoad(errorCode);
                MyLog.v("mInterstitialAd-onAdFailedToLoad");
            }

            @Override
            public void onAdLeftApplication() {
                // TODO Auto-generated method stub
                super.onAdLeftApplication();
                MyLog.v("mInterstitialAd-onAdLeftApplication");
            }

            @Override
            public void onAdLoaded() {
                // TODO Auto-generated method stub
                super.onAdLoaded();
                MyLog.v("mInterstitialAd-onAdLoaded");
            }

            @Override
            public void onAdOpened() {
                // TODO Auto-generated method stub
                super.onAdOpened();
                MyLog.v("mInterstitialAd-onAdOpened");
                is_chaye_open = true;
            }

        });

        mInterstitialAd.loadAd(UIHelper.getInstance().getAdRequest());
    }

    public void loadInterstitial() {
        if (!mInterstitialAd.isLoaded()) {
            mInterstitialAd.loadAd(UIHelper.getInstance().getAdRequest());
        }

    }

    public void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    // public static int[] pageNum = new int[10];// 记录当前适配器数据页码
    public void pauseVideo() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }

    /**
     * Resume the video reproduced in the YouTubePlayer.
     */
    public void playVideo() {
        if (!mPlayer.isPlaying()) {
            mPlayer.play();
        }
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mApplication = this;
        // 初始化tag
        is_install_youtube = isAppInstalled(this.getApplicationContext(), "com.google.android.youtube");
        myHomeTags = MyTagManager.getInstance().initMyHomeTag();
        initInterstitial();
    }

    /**
     * 检测当前系统声音是否为正常模式
     *
     * @return
     */
    public boolean isAudioNormal() {
        AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        return mAudioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public int getNetworkType() {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null) info = new PackageInfo();
        return info;
    }

    /**
     * 判断缓存数据是否可读
     *
     * @param cachefile
     * @return
     */
    public boolean isReadDataCache(String cachefile) {
        return readObject(cachefile) != null;
    }

    /**
     * 判断缓存是否存在
     *
     * @param cachefile
     * @return
     */
    public boolean isExistDataCache(String cachefile) {
        boolean exist = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists()) exist = true;
        return exist;
    }

    /**
     * 清除缓存目录
     *
     * @param dir     目录
     * @param numDays 当前系统时间
     * @return
     */
    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    /**
     * 将对象保存到内存缓存中
     *
     * @param key
     * @param value
     */
    public void setMemCache(String key, Object value) {
        memCacheRegion.put(key, value);
    }

    /**
     * 从内存缓存中获取对象
     *
     * @param key
     * @return
     */
    public Object getMemCache(String key) {
        return memCacheRegion.get(key);
    }

    /**
     * 保存磁盘缓存
     *
     * @param key
     * @param value
     * @throws IOException
     */
    public void setDiskCache(String key, String value) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("cache_" + key + ".data", Context.MODE_PRIVATE);
            fos.write(value.getBytes());
            fos.flush();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取磁盘缓存数据
     *
     * @param key
     * @return
     * @throws IOException
     */
    public String getDiskCache(String key) throws IOException {
        FileInputStream fis = null;
        try {
            fis = openFileInput("cache_" + key + ".data");
            byte[] datas = new byte[fis.available()];
            fis.read(datas);
            return new String(datas);
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 保存对象
     *
     * @param ser
     * @param file
     * @throws IOException
     */
    public boolean saveObject(Serializable ser, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(file, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 读取对象
     *
     * @param file
     * @return
     * @throws IOException
     */
    public Serializable readObject(String file) {
        if (!isExistDataCache(file)) return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public enum TrackerName {
        APP_TRACKER, GLOBAL_TRACKER, ECOMMERCE_TRACKER,
    }

}