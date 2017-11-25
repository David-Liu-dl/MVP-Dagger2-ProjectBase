package com.nowboarding.baseproject.function.mvpview;

import android.support.annotation.Nullable;

/**
 * Created by David Liu on 27/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface TaskBaseView {
    void onTaskStart(int taskId, boolean showProgressBar);
    void onTaskSuccess(int taskId, @Nullable Object data);
    void onTaskFailure(int taskId, @Nullable Object data, @Nullable String msg);
}
