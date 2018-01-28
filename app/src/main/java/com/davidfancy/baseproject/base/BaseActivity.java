package com.davidfancy.baseproject.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.davidfancy.baseproject.R;

/**
 * Created by David Liu on 5/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void setUpToolbar(Toolbar toolbar){
        if (toolbar != null){
            this.setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    protected void showSimpleAlertDialog(String title, String message, String buttonText) {
        MaterialDialog.Builder builder = getBaseDialogBuilder();

        if (!TextUtils.isEmpty(title)){
            builder.title(title);
        }

        if (!TextUtils.isEmpty(message)){
            builder.content(message);
        }

        if (!TextUtils.isEmpty(buttonText)){
            builder.positiveText(buttonText);
        }else {
            builder.positiveText(getString(R.string.action_ok));
        }

        builder.build()
                .show();
    }

    protected void showSimpleActionDialog(String title, String message, String buttonText, MaterialDialog.SingleButtonCallback onPositiveCallback) {
        MaterialDialog.Builder builder = getBaseDialogBuilder();

        if (!TextUtils.isEmpty(title)){
            builder.title(title);
        }

        if (!TextUtils.isEmpty(message)){
            builder.content(message);
        }

        if (onPositiveCallback != null){
            builder.onPositive(onPositiveCallback);
        }

        if (!TextUtils.isEmpty(buttonText)){
            builder.positiveText(buttonText);
        }else {
            builder.positiveText(getString(R.string.action_ok));
        }

        builder.build()
                .show();
    }

    protected MaterialDialog.Builder getBaseDialogBuilder(){
        return new MaterialDialog
                .Builder(this)
                .positiveColor(getResources().getColor(R.color.black))
                .negativeColor(getResources().getColor(R.color.black))
                .negativeText(getString(R.string.action_cancel))
                .autoDismiss(true)
                .canceledOnTouchOutside(true);
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
}
