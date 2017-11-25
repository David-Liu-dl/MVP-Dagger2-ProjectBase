package com.nowboarding.baseproject.function;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.nowboarding.baseproject.base.BaseApplication;
import com.nowboarding.baseproject.base.ConstantInterface;

/**
 * Created by David Liu on 10/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

@Module
public class AppModule {
    private Context context;
    private Authenticator authenticator;
    private ConstantInterface constantInterface;

    public AppModule(BaseApplication application, Authenticator authenticator, ConstantInterface constantInterface) {
        this.context = application;
        this.authenticator = authenticator;
        this.constantInterface = constantInterface;
    }

    @Singleton
    @Provides
    public Context ProviderApplicationContext(){
        return context;
    }

    @Singleton
    @Provides
    @Named("default")
    public SharedPreferences providerDefaultSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    @Named("encode")
    public SharedPreferences providerEncodeSharedPreferences(){
        return context.getSharedPreferences("encode",Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public Authenticator providerAuthenticator(){
        return this.authenticator;
    }

    @Singleton
    @Provides
    public ConstantInterface providerConstant(){
        return this.constantInterface;
    }
}
