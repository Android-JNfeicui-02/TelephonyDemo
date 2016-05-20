package com.roy.smsservicedemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox mCb;
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, SmsService.class);


        mCb  = (CheckBox) findViewById(R.id.checkBox);
        mTextView  = (TextView) findViewById(R.id.textView);

        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    mTextView.setText("服务已开启");
                    mTextView.setTextColor(getResources().getColor(R.color.colorAccent));

                    startService(intent);
                }else {
                    mTextView.setText("服务未开启");
                    mTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    stopService(intent);
                }
            }
        });

    }
}
