package com.roy.teldemo;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mEtShow;
    String   name, number;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtShow = (EditText) findViewById(R.id.et_show);
    }


    // 点击按钮转到联系人列表
    public void show(View view) {
        //
        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts
                .CONTENT_URI), 0);
        Log.d(TAG, "CONTENT_URI: " + ContactsContract.Contacts
                .CONTENT_URI);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "requestCode: " + requestCode);
        Log.d(TAG, "resultCode: " + resultCode);

        if (resultCode == Activity.RESULT_OK) {

            // ContentResolver对象 实现和其他应用通讯
            ContentResolver resolver = getContentResolver();

            // 通过Intent拿到数据的Uri
            Uri contactData = data.getData();
            Log.d(TAG, "contactData: " + contactData);
            // content://com.android.contacts/contacts/lookup/0r1-4D2F4B4D14/1

            // 用Cursor 来 操作 数据
            Cursor cursor = managedQuery(contactData, null, null, null, null);
            cursor.moveToFirst();

//            cursor获取数据表里的数据
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.d(TAG, "name: " + name);

            // 拿到 数据库中 表头为 "_id" 的里面的数据
            String contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts
                                                                              ._ID));
            Log.d(TAG, "contactID: " + contactID);  // contactID: 1

            // 返回一个Cursor对象
            Cursor query = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                          ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactID,
                                          null, null);
            while (query.moveToNext()) {
                // 已经拿到过 索引 所以 根据索引号 取出来 里面的数据
                number = query.getString(
                        query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Log.d(TAG, "onActivityResult:  name =" + name + "  Number: " + number);


                mEtShow.setText(number);
//                mEtShow.setText(number + "(" + name + ")");
            }

            query.close();
            cursor.close();

        }
    }
}
