package com.nowboarding.baseproject.util;

import android.app.Activity;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by David Liu on 5/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class LogUtil {

    public static void logDebug(Activity activity, String msg){
        interceptLog();
        Log.d(activity.getClass().getSimpleName(), msg);
    }

    public static void logDebug(Fragment fragment, String msg){
        interceptLog();
        Log.d(fragment.getClass().getSimpleName(), msg);
    }

    public static void logDebug(String tag, String msg){
        interceptLog();
        Log.d(tag, msg);
    }

    private static void interceptLog(){
        if (!BuildConfig.DEBUG){
            return;
        }
    }
}
