package com.nowboarding.baseproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nowboarding.apparazzibusiness.R;
import com.nowboarding.baseproject.base.AppBaseActivity;
import com.nowboarding.baseproject.base.MyApplication;

/**
 * Created by David Liu on 25/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class MainActivity extends AppBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setupActivityComponent() {
//        MyApplication.get(this)
//                .getAppComponent()
//                .addSub(new AppBaseViewModule(this))
//                .inject(this);
    }

}
