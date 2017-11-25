package com.nowboarding.baseproject.function;

import com.nowboarding.baseproject.data.http.OkhttpModule;
import com.nowboarding.baseproject.data.http.RetrofitModule;
import com.nowboarding.baseproject.data.http.module.LocalServiceModule;
import com.nowboarding.baseproject.function.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by David Liu on 24/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */
@Singleton
@Component(modules = {
        AppModule.class,
        OkhttpModule.class,
        RetrofitModule.class,
        LocalServiceModule.class})
public interface AppComponent {

}
