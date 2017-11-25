package com.nowboarding.baseproject.base;

import android.content.Context;

import com.nowboarding.baseproject.function.AppComponent;
import com.nowboarding.baseproject.function.AppModule;
import com.nowboarding.baseproject.function.Authenticator;
import com.nowboarding.baseproject.function.DaggerAppComponent;

/**
 * Created by David Liu on 25/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class MyApplication extends BaseApplication {

    private static AppComponent appComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    private void setupApplicationComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, getAuthenticator(), getConstant()))
                .build();
    }

    public AppComponent getAppComponent() {
        if(appComponent == null){
            this.setupApplicationComponent();
        }
        return appComponent;
    }

    @Override
    public Authenticator getAuthenticator() {
        return null;
    }

    @Override
    public ConstantInterface getConstant() {
        return new C();
    }
}
