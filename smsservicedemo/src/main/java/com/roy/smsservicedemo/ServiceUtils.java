package com.roy.smsservicedemo;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by Roy Sun on 2016/5/20.
 */
public class ServiceUtils {

    public static boolean isServiceRunning(Context context, String serviceName) {

        boolean isRunning = false;

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(50);

        for (ActivityManager.RunningServiceInfo info : runningServices) {
            if (info.getClass()
                    .getName()
                    .equals(serviceName)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
