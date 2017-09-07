package com.jeiker.android_file;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editName;
    private EditText editDetail;
    private Button btnSave;
    private Button btnClean;
    private Button btnRead;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取应用上下文
        mContext = getApplicationContext();
        // 绑定视图元素
        bindViews();
    }


    private void bindViews() {
        editDetail = (EditText) findViewById(R.id.editdetail);
        editName = (EditText) findViewById(R.id.editname);
        btnClean = (Button) findViewById(R.id.btnclean);
        btnSave = (Button) findViewById(R.id.btnsave);
        btnRead = (Button) findViewById(R.id.btnread);

        btnClean.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnRead.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 清空
            case R.id.btnclean:
                editDetail.setText("");
                editName.setText("");
                break;
            // 写
            case R.id.btnsave:
                FileHelper fHelper = new FileHelper(mContext);
                String filename = editName.getText().toString();
                String fileDetail = editDetail.getText().toString();
                try {
                    fHelper.save(filename, fileDetail);
                    Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            // 读
            case R.id.btnread:
                String detail = "";
                FileHelper fHelper2 = new FileHelper(getApplicationContext());
                try {
                    String fName = editName.getText().toString();
                    detail = fHelper2.read(fName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
