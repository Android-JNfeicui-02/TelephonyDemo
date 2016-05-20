package com.roy.smsservicedemo2;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsService extends Service {

    private static final String TAG = "SmsService";

    SmsReceiver mReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // 创建一个短信监听的 广播接收器
    private class SmsReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();

            Object[] pdus = (Object[]) extras.get("pdus");

            for (Object pdu : pdus) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);

                // 是 发件人的号码
                String address = smsMessage.getOriginatingAddress();
                // 短信的具体内容
                String messageBody = smsMessage.getMessageBody();

                // 开启一个网络请求 把短信内容和 发件人 上传到服务器
//                获取这个人的地理位置 SIM卡的ID， 短信内容 电话内容

                Log.d(TAG, "onReceive: "  + address + " === " + messageBody);
            }

        }
    }

    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate: start");
        mReceiver = new SmsReceiver();

        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        // 设置优先级
        // 如果是在manifest里面声明了 receiver 则按照顺序 来读取
        //如果是用代码　声明，　则优先级　比　manifest高

        filter.setPriority(Integer.MAX_VALUE);

        registerReceiver(mReceiver, filter);

        super.onCreate();
    }

    @Override
    public void onDestroy() {

        Log.d(TAG, "onDestroy: destroy");
        unregisterReceiver(mReceiver);

        super.onDestroy();
    }
}
