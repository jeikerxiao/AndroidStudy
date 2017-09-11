package com.jeiker.demo;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMsgs();
//
//        insertMsg();
//
//        getContacts();
//
//        queryContact("18812345678");
//
//        try {
//            AddContact();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (OperationApplicationException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 获取短信
     */
    private void getMsgs(){
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();
        //获取的是哪些列的信息
        Cursor cursor = resolver.query(uri, new String[]{"address","date","type","body"}, null, null, null);
        while(cursor.moveToNext())
        {
            String address = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            String body = cursor.getString(3);
            Log.i(TAG, "===> 地址:" + address);
            Log.i(TAG, "===> 时间:" + date);
            Log.i(TAG, "===> 类型:" + type);
            Log.i(TAG, "===> 内容:" + body);
            Log.i(TAG, "======================");
        }
        cursor.close();
    }

    /**
     * 插入短信信
     */
    private void insertMsg() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        ContentValues conValues = new ContentValues();
        conValues.put("address", "广东深圳");
        conValues.put("type", 1);
        conValues.put("date", System.currentTimeMillis());
        conValues.put("body", "好好学习，天天向上");
        resolver.insert(uri, conValues);
        Log.e("insertMsg", "短信插入完毕~");
    }

    /**
     * 获取联系人
     */
    private void getContacts(){
        //①查询raw_contacts表获得联系人的id
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //查询联系人数据
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while(cursor.moveToNext())
        {
            //获取联系人姓名,手机号码
            String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.i(TAG, "姓名:" + cName);
            Log.i(TAG, "号码:" + cNum);
            Log.i(TAG, "======================");
        }
        cursor.close();
    }

    /**
     * 根据手机号查询联系人
     * @param number
     */
    private void queryContact(String number){
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + number);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"display_name"}, null, null, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(0);
            Log.i(TAG, number + "对应的联系人名称：" + name);
        }
        cursor.close();
    }

    /**
     * 添加联系人
     * @throws RemoteException
     * @throws OperationApplicationException
     */
    private void AddContact() throws RemoteException, OperationApplicationException {
        //使用事务添加联系人
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri =  Uri.parse("content://com.android.contacts/data");

        ContentResolver resolver = getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null)
                .build();
        operations.add(op1);

        //依次是姓名，号码，邮编
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", "jeiker")
                .build();
        operations.add(op2);

        ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data1", "18811112222")
                .withValue("data2", "2")
                .build();
        operations.add(op3);

        ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                .withValue("data1", "jeiker@126.com")
                .withValue("data2", "2")
                .build();
        operations.add(op4);

        //将上述内容添加到手机联系人中~
        resolver.applyBatch("com.android.contacts", operations);
        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
    }
}
