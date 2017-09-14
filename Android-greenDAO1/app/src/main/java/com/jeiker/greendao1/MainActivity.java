package com.jeiker.greendao1;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jeiker.greendao1.entity.User;
import com.jeiker.greendao1.greendao.gen.DaoMaster;
import com.jeiker.greendao1.greendao.gen.DaoSession;
import com.jeiker.greendao1.greendao.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MainActivity instances;

    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        instances = this;
        setDatabase();

//        initUser();
    }

    private void initView() {
        Button btnSave = (Button) findViewById(R.id.btn_save);
        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        Button btnSelect = (Button) findViewById(R.id.btn_select);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                saveUser();
                break;
            case R.id.btn_delete:
                deleteUser();
                break;
            case R.id.btn_update:
                updateUser();
                break;
            case R.id.btn_select:
                selectUser();
                break;
        }

    }

    public static MainActivity getInstances() {
        return instances;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        mUserDao = mDaoSession.getUserDao();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

//    private void initUser() {
//        mUserDao = MainActivity.getInstances().getDaoSession().getUserDao();
//    }

    private void saveUser() {
        Toast.makeText(this, "新增数据===> 1:jeiker,2:xiao2,3:xiao3", Toast.LENGTH_SHORT).show();

        User mUser1 = new User((long) 1, "jeiker");
        User mUser2 = new User((long) 2, "xiao2");
        User mUser3 = new User((long) 3, "xiao3");
        mUserDao.insert(mUser1);//添加一个
        mUserDao.insert(mUser2);//添加一个
        mUserDao.insert(mUser3);//添加一个
    }

    private void deleteUser() {
        Toast.makeText(this, "删除===> id=1的数据", Toast.LENGTH_SHORT).show();

        Long id = 1L;
        mUserDao.deleteByKey(id);
    }

    private void updateUser() {
        Toast.makeText(this, "更新===> 2:jeikerxiao", Toast.LENGTH_SHORT).show();

        User mUser = new User((long) 2, "jeikerxiao");
        mUserDao.update(mUser);
    }

    private void selectUser() {
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for (int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName() + ",";
        }
        Toast.makeText(this, "查询全部===> " + userName, Toast.LENGTH_SHORT).show();
    }
}
