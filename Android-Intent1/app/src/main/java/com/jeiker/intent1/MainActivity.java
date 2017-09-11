package com.jeiker.intent1;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button btnGoHome = (Button) findViewById(R.id.btn_go_home);
        Button btnGoBaidu = (Button) findViewById(R.id.btn_go_baidu);

        btnGoHome.setOnClickListener(this);
        btnGoBaidu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_home:
                goHome();
                break;
            case R.id.btn_go_baidu:
                goBaidu();
                break;
        }

    }

    private void goHome() {
        Log.i(TAG, "点击跳转Home按钮");
        Toast.makeText(this, "点击跳转Home按钮", Toast.LENGTH_SHORT).show();

        Intent it = new Intent();
        it.setAction(Intent.ACTION_MAIN);
        it.addCategory(Intent.CATEGORY_HOME);
        startActivity(it);
    }

    private void goBaidu() {
        Log.i(TAG, "点击跳转百度按钮");
        Toast.makeText(this, "点击跳转百度按钮", Toast.LENGTH_SHORT).show();

        Intent it = new Intent();
        it.setAction(Intent.ACTION_VIEW);
        it.setData(Uri.parse("https://www.baidu.com"));
        startActivity(it);
    }

}
