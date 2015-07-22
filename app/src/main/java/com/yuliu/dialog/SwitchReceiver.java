package com.yuliu.dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SwitchReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Intent it = new Intent(context, MainAllActivity.class);
        // it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 这个必须加
        // context.startActivity(it);

        // Intent i = getBaseContext().getPackageManager()
        // .getLaunchIntentForPackage(getBaseContext().getPackageName());
        // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // startActivity(i);
    }

}
