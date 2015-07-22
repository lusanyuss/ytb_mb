package com.yuliu.listener;

import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.umeng.analytics.MobclickAgent;
import com.yuliu.activity.AppApplication;
import com.yuliu.event.PlayByHtmlEvent;
import com.yuliu.util.MyContants;
import com.yuliu.util.MyLog;

import de.greenrobot.event.EventBus;

public final class MyPlayerStateChangeListener implements PlayerStateChangeListener {
    public String playerState = "UNINITIALIZED";
    public AppApplication application;

    public void setApplication(AppApplication application) {
        this.application = application;
    }

    @Override
    public void onLoading() {
        playerState = "LOADING";
        MyLog.v(playerState);
    }

    @Override
    public void onLoaded(String videoId) {
        playerState = "LOADED";
        MyLog.v(playerState);
        playerState = String.format("LOADED %s", videoId);
        if (application.mPlayer != null && !application.mPlayer.isPlaying()) {
            application.mPlayer.play();
        }
    }

    @Override
    public void onAdStarted() {
        playerState = "AD_STARTED";
        MyLog.v(playerState);
    }

    @Override
    public void onVideoStarted() {

        playerState = "VIDEO_STARTED";
        MyLog.v(playerState);
        if (MyContants.isOnLine) {
            MobclickAgent.onEvent(application, "ytb_play");
        }
    }

    @Override
    public void onVideoEnded() {

        playerState = "VIDEO_ENDED";
        MyLog.v(playerState);

    }

    @Override
    public void onError(ErrorReason reason) {

        playerState = "ERROR (" + reason + ")";
        MyLog.v(playerState);
        boolean flag = reason == ErrorReason.NETWORK_ERROR || reason == ErrorReason.USER_DECLINED_RESTRICTED_CONTENT
                || reason == ErrorReason.NOT_PLAYABLE;
        if (flag) {
            if (application.mPageItem != null) {
                MyLog.v("html");
                EventBus.getDefault().post(new PlayByHtmlEvent(application.mPageItem));
            }
        }

        if (MyContants.isOnLine) {
            MobclickAgent.onEvent(application, "ytb_play_error");
        }

    }

}
