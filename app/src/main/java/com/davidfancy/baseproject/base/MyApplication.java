package com.davidfancy.baseproject.base;

import android.content.Context;

import com.davidfancy.baseproject.data.http.restfulres.RefreshTokenRequest;
import com.davidfancy.baseproject.function.AppComponent;
import com.davidfancy.baseproject.function.AppModule;
import com.davidfancy.baseproject.function.Authenticator;
import com.davidfancy.baseproject.function.DaggerAppComponent;

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

    private Authenticator authenticator = new Authenticator() {
        @Override
        public String getAccessToken(Context context) {
            return null;
        }

        @Override
        public void storeAccessToken(Context context, String token) {

        }

        @Override
        public String getRefreshToken(Context context) {
            return null;
        }

        @Override
        public void storeRefreshToken(Context context, String token) {

        }

        @Override
        public RefreshTokenRequest getRefreshTokenRequest() {
            return null;
        }
    };


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
        return authenticator;
    }

    @Override
    public ConstantInterface getConstant() {
        return new C();
    }
}
