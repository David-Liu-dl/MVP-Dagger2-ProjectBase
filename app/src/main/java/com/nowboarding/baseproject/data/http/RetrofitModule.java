package com.nowboarding.baseproject.data.http;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.nowboarding.baseproject.base.ConstantInterface;
import com.nowboarding.baseproject.data.http.retrofit.LocalRetrofit;
import okhttp3.OkHttpClient;

/**
 * Created by David Liu on 10/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

@Module
public class RetrofitModule {
    @Singleton
    @Provides
    @Named("default")
    public LocalRetrofit providerRetrofit(@Named("default") OkHttpClient okHttpClient, ConstantInterface constantInterface){
        return new LocalRetrofit(okHttpClient, constantInterface);
    }

    @Singleton
    @Provides
    @Named("noAuth")
    public LocalRetrofit providerRetrofitNoAuth(@Named("noAuth") OkHttpClient okHttpClient, ConstantInterface constantInterface){
        return new LocalRetrofit(okHttpClient, constantInterface);
    }
}
