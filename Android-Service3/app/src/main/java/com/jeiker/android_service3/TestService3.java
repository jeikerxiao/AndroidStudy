package com.jeiker.android_service3;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by xiao on 17/9/10.
 */

public class TestService3 extends IntentService {
    private final String TAG = "TestService3";

    //必须实现父类的构造方法
    public TestService3() {
        super("TestService3");
    }

    //必须重写的核心方法
    @Override
    protected void onHandleIntent(Intent intent) {
        //Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
        String action = intent.getExtras().getString("param");
        if (action.equals("s1")) Log.i(TAG, "启动service1");
        else if (action.equals("s2")) Log.i(TAG, "启动service2");
        else if (action.equals("s3")) Log.i(TAG, "启动service3");

        //让服务休眠2秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //重写其他方法,用于查看方法的调用顺序
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind() 方法被调用。");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate() 方法被调用。");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand() 方法被调用。");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void setIntentRedelivery(boolean enabled) {
        Log.i(TAG, "setIntentRedelivery() 方法被调用。");
        super.setIntentRedelivery(enabled);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy() 方法被调用。");
        super.onDestroy();
    }

}
