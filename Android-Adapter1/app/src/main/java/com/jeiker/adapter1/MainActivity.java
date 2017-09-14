package com.jeiker.adapter1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

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
        List<Map<String, Object>> listItem = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showItem = new HashMap<>();
            showItem.put("head_image", imgIds[i]);
            showItem.put("name", names[i]);
            showItem.put("says", says[i]);
            listItem.add(showItem);
        }

        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listItem, R.layout.list_item, new String[]{"head_image", "name", "says"}, new int[]{R.id.imgtou, R.id.name, R.id.says});
        ListView listView = (ListView) findViewById(R.id.list_test);
        //动态加载顶部View和底部View
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.view_header, null, false);
        View footView = inflater.inflate(R.layout.view_footer, null, false);

        //添加表头和表尾需要写在setAdapter方法调用之前！！！
        listView.addHeaderView(headView);
        listView.addFooterView(footView);

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
        Toast.makeText(this,"你点击了第" + i + "项",Toast.LENGTH_SHORT).show();
    }
}
