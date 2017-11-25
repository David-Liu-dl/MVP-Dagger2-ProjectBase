package com.nowboarding.baseproject.util;

import com.nowboarding.baseproject.base.ConstantInterface;
import com.nowboarding.baseproject.data.http.retrofit.LocalRetrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by David Liu on 10/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class HttpUtil {

    public static <S> S createServiceWithoutToken(Class<S> serviceClass, ConstantInterface constantInterface){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient client = httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS).build();
        LocalRetrofit retrofit = new LocalRetrofit(client, constantInterface);
        return retrofit.getRetrofit().create(serviceClass);
    }

    /**
     * for quickly writing an subscriber
     * @param observable
     * @param subscriber
     * @param <T>
     */
    public static <T> void subscribe(Observable<T> observable, Subscriber<T> subscriber){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
