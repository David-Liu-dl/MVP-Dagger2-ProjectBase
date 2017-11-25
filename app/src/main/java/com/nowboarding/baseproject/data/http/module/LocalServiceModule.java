package com.nowboarding.baseproject.data.http.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.nowboarding.baseproject.data.http.restfulapi.AuthApi;
import com.nowboarding.baseproject.data.http.retrofit.LocalRetrofit;

/**
 * Created by David Liu on 10/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

@Module
public class LocalServiceModule {

    @Singleton
    @Provides
    @Named("default")
    public AuthApi providerUserService(@Named("default") LocalRetrofit retrofit){
        return retrofit.getRetrofit().create(AuthApi.class);
    }

    @Singleton
    @Provides
    @Named("noAuth")
    public AuthApi providerUserServiceNoAuth(@Named("noAuth") LocalRetrofit retrofit){
        return retrofit.getRetrofit().create(AuthApi.class);
    }
}
