package com.davidfancy.baseproject.data.http.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.davidfancy.baseproject.data.http.restfulapi.AuthApi;
import com.davidfancy.baseproject.data.http.restfulapi.UserApi;
import com.davidfancy.baseproject.data.http.retrofit.LocalRetrofit;

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
    public UserApi providerUserService(@Named("default") LocalRetrofit retrofit){
        return retrofit.getRetrofit().create(UserApi.class);
    }

    @Singleton
    @Provides
    @Named("noAuth")
    public UserApi providerUserServiceNoAuth(@Named("noAuth") LocalRetrofit retrofit){
        return retrofit.getRetrofit().create(UserApi.class);
    }

    @Singleton
    @Provides
    public AuthApi providerAuthServiceNoAuth(@Named("noAuth") LocalRetrofit retrofit){
        return retrofit.getRetrofit().create(AuthApi.class);
    }
}
