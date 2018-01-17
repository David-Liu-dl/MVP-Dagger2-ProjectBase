package com.davidfancy.baseproject.function;

import com.davidfancy.baseproject.data.http.OkhttpModule;
import com.davidfancy.baseproject.data.http.RetrofitModule;
import com.davidfancy.baseproject.data.http.module.LocalServiceModule;
import com.davidfancy.baseproject.function.component.UserBaseViewComponent;
import com.davidfancy.baseproject.function.module.UserBaseViewModule;

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
    UserBaseViewComponent addSub(UserBaseViewModule userBaseViewModule);
}
