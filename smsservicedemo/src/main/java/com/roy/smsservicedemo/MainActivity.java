package com.roy.smsservicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox cb;

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent service = new Intent(this, SmsService.class);

        cb = (CheckBox) findViewById(R.id.cb_service);
        mTextView  = (TextView) findViewById(R.id.textView);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    mTextView.setText("服务已开启");
                    mTextView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                    startService(service);
                } else {
                    mTextView.setText("服务未开启");
                    mTextView.setTextColor(getResources().getColor(android.R.color.secondary_text_light));
                    stopService(service);
                }
            }
        });

    }
}
