package com.jeiker.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by xiao on 17/9/3.
 *
 * SQLiteOpenHelper
 * 1.提供了onCreate() onUpgrade() 等创建数据库，更新数据库的方法
 * 2.提供了获取数据库对象的函数
 */

public class MySqliteHelper extends SQLiteOpenHelper {

    /**
     * 构造函数
     * @param context   上下文对象
     * @param name      表示创建数据库的名称
     * @param factory   游标工厂
     * @param version   表示创建数据库的版本 >= 1
     */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    /**
     * 使用常量类的简单构造函数
     * @param context
     */
    public MySqliteHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    /**
     * 当数据库创建时回调的函数
     * @param database 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.i("MySqliteHelper", "===> onCreate");
        String sql = "create table " + Constant.TABLE_NAME + "(" +
                Constant._ID + " Integer primary key," +
                Constant.NAME + " varchar(10)," +
                Constant.AGE + " Integer)";
        // 执行 sql 语句
        database.execSQL(sql);
    }

    /**
     * 当数据库版本更新时回调的函数
     * @param database 数据库对象
     * @param i 数据库旧版本
     * @param i1 数据库新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        Log.i("MySqliteHelper", "===> onUpgrade");

    }

    @Override
    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        Log.i("MySqliteHelper", "===> onOpen");
    }
}
