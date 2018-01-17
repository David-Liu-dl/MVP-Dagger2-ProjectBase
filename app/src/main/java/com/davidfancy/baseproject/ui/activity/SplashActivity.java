package com.davidfancy.baseproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.davidfancy.baseproject.base.BaseActivity;

/**
 * Created by David Liu on 25/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class SplashActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toMainActivity();
    }

    private void toMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
