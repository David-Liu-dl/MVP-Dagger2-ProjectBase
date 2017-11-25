package com.nowboarding.baseproject.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

/**
 * Created by David Liu on 5/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public abstract class AppBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected void setUpToolbar(Toolbar toolbar){
        if (toolbar != null){
            this.setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    protected void showSimpleAlertDialog(String title, String message, String buttonText, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AppBaseActivity.this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(buttonText, onClickListener);
        builder.show();
    }

    protected void showLongToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    protected void showLongToast(int strResId){
        showLongToast(getString(strResId));
    }

    protected void showShortToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showShortToast(int strResId){
        showShortToast(getString(strResId));
    }

    protected abstract void setupActivityComponent();
}
