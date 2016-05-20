package com.roy.smsservicedemo;

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
import android.widget.TextView;

public class SmsService extends Service {
    private static final String TAG = "SmsService";

    SmsReceiver mSmsReceiver;

    // 注册广播接收者

    private class SmsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            Object[] pdus = (Object[]) extras.get("pdus");

            for (Object pdu : pdus) {
                SmsMessage sm = SmsMessage.createFromPdu((byte[]) pdu);

                Log.d(TAG, sm.getOriginatingAddress() + "===" + sm.getMessageBody());
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return  null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: service start");
        mSmsReceiver = new SmsReceiver();
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mSmsReceiver, filter);

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: service stop");
        unregisterReceiver(mSmsReceiver);
        super.onDestroy();

    }
}
