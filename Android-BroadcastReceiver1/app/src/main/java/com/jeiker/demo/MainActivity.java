package com.jeiker.demo;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DynamicBRReceiver mDynamicBRReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 注册 监听广播
        mDynamicBRReceiver = new DynamicBRReceiver();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mDynamicBRReceiver, itFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消广播，释放资源
        unregisterReceiver(mDynamicBRReceiver);
    }
}
