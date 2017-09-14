package com.jeiker.butterkinfe1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_1) Button btn_1;
    @BindView(R.id.btn_2) Button btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }
    
    @OnClick(R.id.btn_1)
    public void btn1Click() {
        Toast.makeText(this, "按钮1点击！", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_2)
    public void btn2Click() {
        Toast.makeText(this, "按钮2点击！", Toast.LENGTH_SHORT).show();
    }
}
