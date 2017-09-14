package com.jeiker.alertdialog1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_dialog1;
    private Button btn_dialog2;
    private Button btn_dialog3;
    private Button btn_dialog4;

    private Context mContext;
    private boolean[] checkItems;

    private AlertDialog alert;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mContext = MainActivity.this;

        btn_dialog1 = (Button) findViewById(R.id.btn_dialog1);
        btn_dialog2 = (Button) findViewById(R.id.btn_dialog2);
        btn_dialog3 = (Button) findViewById(R.id.btn_dialog3);
        btn_dialog4 = (Button) findViewById(R.id.btn_dialog4);

        btn_dialog1.setOnClickListener(this);
        btn_dialog2.setOnClickListener(this);
        btn_dialog3.setOnClickListener(this);
        btn_dialog4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dialog1:
                dialog1();
                break;
            case R.id.btn_dialog2:
                dialog2();
                break;
            case R.id.btn_dialog3:
                dialog3();
                break;
            case R.id.btn_dialog4:
                dialog4();
                break;
        }
    }

    /**
     * 普通对话框
     */
    private void dialog1() {
        alert = null;
        builder = new AlertDialog.Builder(this);
        alert = builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("系统提示：")
                .setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "你点击了中立按钮~", Toast.LENGTH_SHORT).show();
                    }
                }).create();             //创建AlertDialog对象
        alert.show();                    //显示对话框
    }

    /**
     * 列表对话框
     */
    private void dialog2() {
        final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
        alert = null;
        builder = new AlertDialog.Builder(mContext);
        alert = builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("选择你喜欢的课程")
                .setItems(lesson, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                    }
                }).create();
        alert.show();
    }

    /**
     * 单选对话框
     */
    private void dialog3() {
        final String[] fruits = new String[]{"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
        alert = null;
        builder = new AlertDialog.Builder(mContext);
        alert = builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("选择你喜欢的水果，只能选一个哦~")
                .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "你选择了" + fruits[which], Toast.LENGTH_SHORT).show();
                    }
                }).create();
        alert.show();
    }

    /**
     * 多选对话框
     */
    private void dialog4() {
        final String[] menu = new String[]{"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
        //定义一个用来记录个列表项状态的boolean数组
        checkItems = new boolean[]{false, false, false, false};
        alert = null;
        builder = new AlertDialog.Builder(mContext);
        alert = builder.setIcon(R.mipmap.ic_launcher)
                .setMultiChoiceItems(menu, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkItems[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result = "";
                        for (int i = 0; i < checkItems.length; i++) {
                            if (checkItems[i])
                                result += menu[i] + " ";
                        }
                        Toast.makeText(getApplicationContext(), "客官你点了:" + result, Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alert.show();
    }

}
