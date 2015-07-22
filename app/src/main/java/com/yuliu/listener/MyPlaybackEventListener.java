package com.yuliu.listener;

import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.yuliu.activity.AppApplication;
import com.yuliu.util.MyLog;

public final class MyPlaybackEventListener implements PlaybackEventListener {
    String playbackState = "NOT_PLAYING";
    String bufferingState = "";

    AppApplication application;

    public MyPlaybackEventListener() {
        super();
    }

    public AppApplication getApplication() {
        return application;
    }

    public void setApplication(AppApplication application) {
        this.application = application;
    }

    @Override
    public void onPlaying() {
        playbackState = "PLAYING";
        MyLog.v(playbackState);
    }

    @Override
    public void onBuffering(boolean isBuffering) {
        bufferingState = isBuffering ? "(BUFFERING)" : "";
        MyLog.v(bufferingState);

    }

    @Override
    public void onStopped() {
        playbackState = "STOPPED";
        MyLog.v(playbackState);
    }

    @Override
    public void onPaused() {
        playbackState = "PAUSED";
        MyLog.v(playbackState);
    }

    @Override
    public void onSeekTo(int endPositionMillis) {
        playbackState = "ONSEEKTO";
        MyLog.v(playbackState);
    }
}