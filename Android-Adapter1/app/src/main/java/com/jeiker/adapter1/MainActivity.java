package com.jeiker.adapter1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] names = new String[]{"暗夜猎手 - 薇恩", "刀锋之影 - 泰隆", "疾风剑豪 - 亚索"};
    private String[] says = new String[]{"木已成舟。", "刀下生，刀下死。", "长路漫漫，唯剑作伴。"};
    private int[] imgIds = new int[]{R.drawable.icon1, R.drawable.icon2, R.drawable.icon3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListView();
    }

    private void initListView() {
        List<Map<String, Object>> listitem = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<>();
            showitem.put("head_image", imgIds[i]);
            showitem.put("name", names[i]);
            showitem.put("says", says[i]);
            listitem.add(showitem);
        }

        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.list_item, new String[]{"head_image", "name", "says"}, new int[]{R.id.imgtou, R.id.name, R.id.says});
        ListView listView = (ListView) findViewById(R.id.list_test);
        listView.setAdapter(myAdapter);
    }
}
