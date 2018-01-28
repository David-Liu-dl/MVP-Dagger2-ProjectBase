package com.davidfancy.baseproject.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.davidfancy.baseproject.R;
import com.davidfancy.baseproject.function.mvpview.TaskBaseView;

/**
 * Created by David Liu on 28/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public abstract class TaskBaseActivity extends AppBaseActivity implements TaskBaseView{

    protected boolean showProgressBar = false;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
    }

    @Override
    public void onTaskStart(int taskId, boolean showProgressBar) {
        if (showProgressBar){
            showProgressDialog();
            return;
        }

        if (this.showProgressBar){
            showProgressDialog();
        }
    }

    @Override
    public void onTaskSuccess(int taskId, @Nullable Object data) {
        hideProgressDialog();
    }

    @Override
    public void onTaskFailure(int taskId, @Nullable Object data, @Nullable String msg) {
        hideProgressDialog();
        showSimpleAlertDialog(null, msg, getString(R.string.action_ok));
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
