package com.jeiker.json1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initView() {
        Button btnSimple = (Button) findViewById(R.id.btn_simple);
        Button btnGson = (Button) findViewById(R.id.btn_gson);
        Button btnFastjson = (Button) findViewById(R.id.btn_fastjson);

        btnSimple.setOnClickListener(this);
        btnGson.setOnClickListener(this);
        btnFastjson.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                parseSimpleJson();
                break;
            case R.id.btn_gson:
                gsonParse();
                break;
            case R.id.btn_fastjson:
                fastjsonParse();
                break;
        }

    }

    /**
     * 原生解析json
     */
    private void parseSimpleJson() {
        // jsonStr -> Object
        String json = "{ \"id\": \"1\", \"name\": \"jeiker\",\"age\": \"20\"}";
        Person person = new Person();
        try {
            JSONObject jsonObject = new JSONObject(json);
            person.setId(jsonObject.getString("id"));
            person.setName(jsonObject.getString("name"));
            person.setAge(jsonObject.getString("age"));
            Log.i("SimpleJson", person.toString());
            Toast.makeText(this, person.toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Object -> jsonStr
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", person.getId());
            jsonObject.put("name", person.getName());
            jsonObject.put("age", person.getAge());
            String jsonStr = jsonObject.toString();
            Log.i("SimpleJson", jsonStr);
            Toast.makeText(this, jsonStr, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gson 解析
     */
    private void gsonParse() {
        // jsonStr -> Object
        String json = "{\"id\": \"1\", \"name\": \"jeiker\",\"age\": \"20\"}";
        Person person = new Gson().fromJson(json, Person.class);
        Log.i("Gson", person.toString());
        Toast.makeText(this, person.toString(), Toast.LENGTH_SHORT).show();
        // Object -> jsonStr
        String jsonStr = new Gson().toJson(person);
        Log.i("Gson", jsonStr);
        Toast.makeText(this, jsonStr, Toast.LENGTH_SHORT).show();
    }

    /**
     * Fastjson 解析
     */
    private void fastjsonParse() {
        // jsonStr -> Object
        String json = "{ \"id\": \"1\", \"name\": \"jeiker\",\"age\": \"20\"}";
        Person person = JSON.parseObject(json, Person.class);
        Log.i("Fastjson", person.toString());
        Toast.makeText(this, person.toString(), Toast.LENGTH_SHORT).show();
        // Object -> jsonStr
        String jsonStr = JSON.toJSONString(person);
        Log.i("Fastjson", jsonStr);
        Toast.makeText(this, jsonStr, Toast.LENGTH_SHORT).show();
    }
}
