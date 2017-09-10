package com.jeiker.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by xiao on 17/9/10.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    private final String ACTION_BOOT = "com.jeiker.demo.MY_BROADCAST";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_BOOT.equals(intent.getAction())) {
            Toast.makeText(context, "收到自己发的广播", Toast.LENGTH_SHORT).show();
        }
    }
}
