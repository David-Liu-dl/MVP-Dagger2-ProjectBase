package com.nowboarding.baseproject.base;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nowboarding.apparazzibusiness.R;

/**
 * Created by David Liu on 5/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class BaseFragment extends Fragment {

    protected void showSimpleAlertDialog(String title, String message, String buttonText, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(buttonText, onClickListener);
        builder.show();
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

    protected void replaceFragmentWith(int fragmentContainerId, Fragment fragment, boolean backable){
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.push_left_in, R.anim.push_right_out)
                .replace(fragmentContainerId, fragment);

        if (backable){
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    protected void addFragmentWith(int fragmentContainerId, Fragment fragment, boolean backable){
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.push_left_in, R.anim.push_right_out)
                .add(fragmentContainerId, fragment);

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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(resId);
    }

    protected void setToolbarTitle(String title){
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }
}
