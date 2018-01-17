package com.davidfancy.baseproject.util;

import android.app.Activity;
import android.util.DisplayMetrics;

import com.google.gson.Gson;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.COMPLEX_UNIT_IN;
import static android.util.TypedValue.COMPLEX_UNIT_MM;
import static android.util.TypedValue.COMPLEX_UNIT_PT;
import static android.util.TypedValue.COMPLEX_UNIT_PX;
import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * Created by David Liu on 2/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class BaseUtil {

    public static float applyDimension(Activity activity, int unit, float value) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        switch (unit) {
            case COMPLEX_UNIT_PX:
                return value;
            case COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f/72);
            case COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f/25.4f);
        }
        return 0;
    }

    public static <T> T deepCopyObject(T o){
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(o), (Class<T>) o.getClass());
    }

}
