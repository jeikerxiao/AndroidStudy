package com.jeiker.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xiao on 17/9/10.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
