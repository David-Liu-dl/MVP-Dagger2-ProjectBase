package com.davidfancy.baseproject.data.http;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.davidfancy.baseproject.base.ConstantInterface;
import com.davidfancy.baseproject.data.http.restfulapi.AuthApi;
import com.davidfancy.baseproject.data.http.restfulreq.RefreshTokenResponse;
import com.davidfancy.baseproject.data.http.restfulres.HttpResult;
import com.davidfancy.baseproject.data.http.restfulres.RefreshTokenRequest;
import com.davidfancy.baseproject.util.HttpUtil;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;

/**
 * Created by David Liu on 10/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

@Module
public class OkhttpModule {

    @Singleton
    @Provides
    @Named("cache")
    public OkHttpClient providerAutoCacheOkHttpClient(Context context, com.davidfancy.baseproject.function.Authenticator authenticator){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + 3600 * 6 + " ,max-stale=2419200";
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        };

        Interceptor authInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String authToken = authenticator.getAccessToken(context);
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + authToken)
                        .method(original.method(), original.body());
                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        };

        return new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("default")
    public OkHttpClient providerOkHttpClient(Context context, com.davidfancy.baseproject.function.Authenticator authenticator, ConstantInterface constantInterface){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor authInterceptor = chain -> {
            String authToken = authenticator.getAccessToken(context);
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer " + authToken)
                    .method(original.method(), original.body());
            Request newRequest = requestBuilder.build();
            return chain.proceed(newRequest);
        };

        okhttp3.Authenticator okAuthenticator = (route, response) -> {
            AuthApi authApi = HttpUtil.createServiceWithoutToken(AuthApi.class, constantInterface);
            RefreshTokenRequest refreshTokenRequest = authenticator.getRefreshTokenRequest();
            Call<HttpResult<RefreshTokenResponse>> call = authApi.refreshToken(refreshTokenRequest);
            retrofit2.Response<HttpResult<RefreshTokenResponse>> refreshRep = call.execute();
            HttpResult<RefreshTokenResponse> refreshTokenHttpResponse = refreshRep.body();

            if (!refreshTokenHttpResponse.isStatus()){
                Toast.makeText(context, "Refresh Token Error: " + refreshTokenHttpResponse.getError().getMessage(), Toast.LENGTH_LONG).show();
                return null;
            }

            RefreshTokenResponse refreshTokenResponse = refreshTokenHttpResponse.getData();
            authenticator.storeAccessToken(context, refreshTokenResponse.getAccessToken());
            authenticator.storeRefreshToken(context, refreshTokenResponse.getRefreshToken());

            return response.request()
                    .newBuilder()
                    .header("Authorization", "Bearer " + authenticator.getAccessToken(context))
                    .build();
        };


        return new OkHttpClient.Builder()
                .authenticator(okAuthenticator)
                .addInterceptor(authInterceptor)
                .addNetworkInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("noAuth")
    public OkHttpClient providerOkHttpClientNoAuth(Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }
}
