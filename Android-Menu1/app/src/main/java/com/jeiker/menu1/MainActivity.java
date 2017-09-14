package com.jeiker.menu1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //1.定义不同颜色的菜单项的标识:
    final private int RED = 110;
    final private int GREEN = 111;
    final private int BLUE = 112;
    final private int YELLOW = 113;
    final private int GRAY = 114;
    final private int CYAN = 115;
    final private int BLACK = 116;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // add(菜单项的组号，ID，排序号，标题)
        menu.add(1, RED, 4, "红色");
        menu.add(1, GREEN, 2, "绿色");
        menu.add(1, BLUE, 3, "蓝色");
        menu.add(1, YELLOW, 1, "黄色");
        menu.add(1, GRAY, 5, "灰色");
        menu.add(1, CYAN, 6, "蓝绿色");
        menu.add(1, BLACK, 7, "黑色");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case RED:
                mTextView.setTextColor(Color.RED);
                break;
            case GREEN:
                mTextView.setTextColor(Color.GREEN);
                break;
            case BLUE:
                mTextView.setTextColor(Color.BLUE);
                break;
            case YELLOW:
                mTextView.setTextColor(Color.YELLOW);
                break;
            case GRAY:
                mTextView.setTextColor(Color.GRAY);
                break;
            case CYAN:
                mTextView.setTextColor(Color.CYAN);
                break;
            case BLACK:
                mTextView.setTextColor(Color.BLACK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
