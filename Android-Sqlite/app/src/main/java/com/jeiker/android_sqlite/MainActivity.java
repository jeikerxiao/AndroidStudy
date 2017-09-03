package com.jeiker.android_sqlite;

import android.content.ContentValues;
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

    /**
     * 点击插入数据 API
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insertApi:
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(Constant._ID, 3);
                values.put(Constant.NAME, "晓");
                values.put(Constant.AGE, 25);
                long result = db.insert(Constant.TABLE_NAME, null, values);
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "插入数据成功！", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "插入数据失败！", Toast.LENGTH_LONG).show();
                }
                db.close();
                break;
            case R.id.btn_updateApi:
                db = helper.getWritableDatabase();
                /**
                 * update(String table, ContentValues values, String whereClause, String[] whereArgs)
                 * String table         表示修改的数据库表名
                 * ContentValues values 表示键为String 类型的 hashmap
                 * String whereClause   表示修改条件
                 * String[] whereArgs   表示修改条件的占位符
                 */
                ContentValues values1 = new ContentValues();
                values1.put(Constant.NAME, "月");
                int resutl = db.update(Constant.TABLE_NAME, values1, Constant._ID + "=?", new String[]{"3"});
                db.close();
                break;
        }
    }
}
