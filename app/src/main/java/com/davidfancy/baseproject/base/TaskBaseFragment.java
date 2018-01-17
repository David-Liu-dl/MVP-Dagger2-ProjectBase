package com.davidfancy.baseproject.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.davidfancy.baseproject.function.mvpview.TaskBaseView;

/**
 * Created by David Liu on 28/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public abstract class TaskBaseFragment extends AppBaseFragment implements TaskBaseView{
    protected ProgressDialog progressDialog;
    protected boolean showProgressBar = false;
    protected boolean progressBarTouchCancellable = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCanceledOnTouchOutside(progressBarTouchCancellable);
    }

    @Override
    public void onTaskStart(@Nullable int taskId, boolean showProgressBar) {
        if (showProgressBar){
            showProgressDialog();
            return;
        }

        if (this.showProgressBar){
            showProgressDialog();
        }
    }

    @Override
    public void onTaskSuccess(@Nullable int taskId, @Nullable Object data) {
        hideProgressDialog();
    }

    @Override
    public void onTaskFailure(@Nullable int taskId, @Nullable Object data, @Nullable String msg) {
        hideProgressDialog();
        showSimpleAlertDialog(null, msg, "OK");
    }

    public void showProgressDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
