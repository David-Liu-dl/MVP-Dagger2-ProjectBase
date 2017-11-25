package com.nowboarding.baseproject.data.http.retrofit;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nowboarding.baseproject.base.ConstantInterface;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David Liu on 10/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class LocalRetrofit {
    private static Retrofit retrofit;
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public LocalRetrofit(OkHttpClient okHttpClient, ConstantInterface constantInterface) {
        retrofit = new Retrofit.Builder()
                .baseUrl(constantInterface.getBaseUrl())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
