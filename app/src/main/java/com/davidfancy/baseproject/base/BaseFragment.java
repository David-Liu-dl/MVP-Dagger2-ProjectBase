package com.davidfancy.baseproject.base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.davidfancy.baseproject.R;

/**
 * Created by David Liu on 5/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class BaseFragment extends Fragment {

    private String toolbarTitle;

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(toolbarTitle)){
            setToolbarTitle(toolbarTitle);
        }
    }

    protected void showSimpleAlertDialog(@Nullable String title, @Nullable String message, @Nullable String buttonText) {
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

    protected void showSimpleActionDialog(@Nullable String title, @Nullable String message, @Nullable String buttonText, @Nullable MaterialDialog.SingleButtonCallback onPositiveCallback) {
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
                .Builder(getContext())
                .positiveColor(getResources().getColor(R.color.black))
                .negativeColor(getResources().getColor(R.color.black))
                .negativeText(getString(R.string.action_cancel))
                .autoDismiss(true)
                .canceledOnTouchOutside(true);
    }

    protected void showLongToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    protected void showLongToast(int strResId){
        showLongToast(getString(strResId));
    }

    protected void showShortToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showShortToast(int strResId){
        showShortToast(getString(strResId));
    }

    protected void replaceFragmentWith(Fragment fragment, boolean backable){
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.push_left_in, R.anim.push_right_out)
                .replace(R.id.fragment_container, fragment);

        if (backable){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    protected void addFragmentWith(Fragment fragment, boolean backable){
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.push_left_in, R.anim.push_right_out)
                .add(R.id.fragment_container, fragment);

        if (backable){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    protected void backToPreviousFragment(){
        getActivity()
                .getSupportFragmentManager()
                .popBackStack();
    }

    protected void setToolbarTitle(int resId){
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar == null){
            return;
        }
        this.toolbarTitle = getString(resId);
        actionBar.setTitle(toolbarTitle);
    }

    protected void setToolbarTitle(String title){
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar == null){
            return;
        }
        this.toolbarTitle = title;
        actionBar.setTitle(title);
    }

    protected void setToolbarVisibility(int visibility){
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar == null){
            return;
        }

        if (visibility != View.VISIBLE){
            actionBar.hide();
        }else {
            actionBar.show();
        }
    }
}
