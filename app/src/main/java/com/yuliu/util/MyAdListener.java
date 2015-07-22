package com.yuliu.util;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.umeng.analytics.MobclickAgent;

public class MyAdListener extends AdListener {
    /**
     * Called when an ad is clicked and about to return to the
     */
    public Context mActivity;
    public String mString = "";
    public String state = "";

    public MyAdListener(Context mActivity, String mString) {
        super();
        this.mActivity = mActivity;
        this.mString = mString + "_";
    }

    @Override
    public void onAdClosed() {
        // MobclickAgent.onEvent(mActivity, mString + "onAdClosed");
        MyLog.v(mString + "onAdClosed");
    }

    /**
     * Called when an ad failed to load.
     */
    @Override
    public void onAdFailedToLoad(int error) {
        // MobclickAgent.onEvent(mActivity, mString + "onAdFailedToLoad");
        state = "onAdFailedToLoad";
        MyLog.v(mString + "onAdFailedToLoad");
    }

    /**
     * Called when an ad is clicked and going to start a new Activity that will
     * leave the application (e.g. breaking out to the Browser or Maps
     * application).
     */
    @Override
    public void onAdLeftApplication() {
        // MobclickAgent.onEvent(mActivity, mString + "onAdLeftApplication");
        MyLog.v(mString + "onAdLeftApplication");
    }

    /**
     * Called when an Activity is created in front of the app (e.g. an
     * interstitial is shown, or an ad is clicked and launches a new Activity).
     */
    @Override
    public void onAdOpened() {
        MyLog.v(mString + "onAdOpened");
        if (MyContants.isOnLine) {
            MobclickAgent.onEvent(mActivity, mString + "onAdOpened");
        }
    }

    /**
     * Called when an ad is loaded.
     */
    @Override
    public void onAdLoaded() {
        MyLog.v(mString + "onAdLoaded");
        // MobclickAgent.onEvent(mActivity, mString + "onAdLoaded");
    }
}
