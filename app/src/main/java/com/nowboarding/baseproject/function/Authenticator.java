package com.nowboarding.baseproject.function;

import android.content.Context;

import com.nowboarding.baseproject.data.http.restfulres.RefreshTokenRequest;

/**
 * Created by David Liu on 24/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface Authenticator {
    String getAccessToken(Context context);
    void storeAccessToken(Context context, String token);
    String getRefreshToken(Context context);
    void storeRefreshToken(Context context, String token);
    RefreshTokenRequest getRefreshTokenRequest();
}
