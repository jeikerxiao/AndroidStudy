package com.jeiker.android_sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jeiker.utils.Constant;
import com.jeiker.utils.DbManger;
import com.jeiker.utils.MySqliteHelper;

public class MainActivity extends AppCompatActivity {
    // 数据库helper 对象
    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 实例化helper 对象
        helper = DbManger.getIntance(this);
    }

    /**
     * 点击创建数据库
     * @param view
     */
    public void createDb(View view) {
        /**
         * getReadableDatabase() 创建或打开数据库
         * getWritableDatabase() 创建或打开数据库
         * 默认情况下，两个函数都表示打开或创建可读可写的数据库对象
         * 如果磁盘已满或者是数据库本身权限等情况下，
         * getReadableDatabase() 打开的是只读数据库
         */
//        helper.getReadableDatabase();
        SQLiteDatabase db = helper.getWritableDatabase();
    }

    /**
     * 点击插入数据
     * @param view
     */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                SQLiteDatabase db = helper.getWritableDatabase();
                String InsertSql = "insert into " + Constant.TABLE_NAME + " values(1,'xiao',20)";
                DbManger.execSQL(db, InsertSql);
                String InsertSql2 = "insert into " + Constant.TABLE_NAME + " values(2,'jeiker',23)";
                DbManger.execSQL(db, InsertSql2);
                db.close();
                break;

            case R.id.btn_update:
                db = helper.getWritableDatabase();
                String updateSql = "update " + Constant.TABLE_NAME + " set " +
                        Constant.NAME + "='jeikerxiao' where " +
                        Constant._ID + "=1";
                DbManger.execSQL(db, updateSql);
                db.close();
                break;

            case R.id.btn_delete:
                db = helper.getWritableDatabase();
                String deleteSql = "delete from " + Constant.TABLE_NAME + " where " + Constant._ID + "=2";
                DbManger.execSQL(db, deleteSql);
                db.close();
                break;
        }
        Toast.makeText(this, "数据库操作成功！", Toast.LENGTH_LONG).show();
    }
}
