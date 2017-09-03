package com.jeiker.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by xiao on 17/9/3.
 * 主要是对数据库操作的工具类
 */

public class DbManger {

    private static MySqliteHelper helper;

    // 单例的方式，获取 MySqliteHelper 对象
    public static MySqliteHelper getIntance(Context context) {
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据 sql 语句在数据库中执行语句
     * @param db 数据库对象
     * @param sql sql语句
     */
    public static void execSQL(SQLiteDatabase db, String sql) {
        if (db != null) {
            if (sql != null && !"".equals(sql)) {
                db.execSQL(sql);
            }
        }
    }
}
