package com.jeiker.intent1;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        Button btnGoPhone = (Button) findViewById(R.id.btn_go_phone);
        Button btnSendMessage = (Button) findViewById(R.id.btn_send_message);

        Button btnSendImageMessage = (Button) findViewById(R.id.btn_send_image_message);
        Button btnGoMap = (Button) findViewById(R.id.btn_go_map);

        Button btnRecordSound = (Button) findViewById(R.id.btn_record_sound);
        Button btnGoSettings = (Button) findViewById(R.id.btn_go_settings);

        btnGoHome.setOnClickListener(this);
        btnGoBaidu.setOnClickListener(this);

        btnGoPhone.setOnClickListener(this);
        btnSendMessage.setOnClickListener(this);

        btnSendImageMessage.setOnClickListener(this);
        btnGoMap.setOnClickListener(this);

        btnRecordSound.setOnClickListener(this);
        btnGoSettings.setOnClickListener(this);
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
            case R.id.btn_go_phone:
                goPhone();
                break;
            case R.id.btn_send_message:
                sendMessage();
                break;
            case R.id.btn_send_image_message:
                sendImageMessage();
                break;
            case R.id.btn_go_map:
                goMap();
                break;
            case R.id.btn_record_sound:
                recordSound();
                break;
            case R.id.btn_go_settings:
                goSettings();
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

    private void goPhone() {
        //1.拨打电话
        // 给移动客服10086拨打电话
        Uri uri = Uri.parse("tel:10086");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    private void sendMessage() {
        //2.发送短信
        // 给10086发送内容为“Hello”的短信
        Uri uri = Uri.parse("smsto:10086");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "Hello");
        startActivity(intent);
    }

    private void sendImageMessage() {
        //3.发送彩信（相当于发送带附件的短信）
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra("sms_body", "Hello");
        Uri uri = Uri.parse("content://media/external/images/media/23");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/png");
        startActivity(intent);
    }

    private void goMap() {
        //6.显示地图:
        // 打开Google地图中国北京位置（北纬39.9，东经116.3）
        Uri uri = Uri.parse("geo:39.9,116.3");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void recordSound() {
        //21.打开录音机
        Intent mi = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivity(mi);
    }

    private void goSettings() {
        //12.进入手机设置界面:
        // 进入无线网络设置界面（其它可以举一反三）
        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        startActivityForResult(intent, 0);
    }
}
