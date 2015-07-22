package com.yuliu.util;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.yuliu.activity.AppApplication;

public final class AnalyticsUtil {

    private AnalyticsUtil() {
    }

    public static void sendScreenName(String screenName) {
        sendScreenName(AppApplication.TrackerName.GLOBAL_TRACKER, screenName);
    }

    public static void sendScreenName(AppApplication.TrackerName trackerName, String screenName) {
        Tracker t = getTracker(trackerName);
        if (t != null) {
            t.setScreenName(screenName);
            t.send(new HitBuilders.AppViewBuilder().build());
        }
    }

    public static void sendEvent(String category, String action, String label) {
        sendEvent(AppApplication.TrackerName.GLOBAL_TRACKER, category, action, label);
    }

    public static void sendEvent(AppApplication.TrackerName trackerName, String category, String action, String label) {
        Tracker t = getTracker(trackerName);
        if (t != null) {

            t.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
        }
    }

    private static Tracker getTracker(AppApplication.TrackerName trackerName) {

        if (AppApplication.mApplication != null) {
            return AppApplication.mApplication.getTracker(trackerName);
        } else {
            return null;
        }
    }
}
