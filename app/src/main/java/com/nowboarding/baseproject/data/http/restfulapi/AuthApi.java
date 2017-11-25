package com.nowboarding.baseproject.data.http.restfulapi;

import com.nowboarding.baseproject.data.http.restfulreq.RefreshTokenResponse;
import com.nowboarding.baseproject.data.http.restfulres.HttpResult;
import com.nowboarding.baseproject.data.http.restfulres.RefreshTokenRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by David Liu on 6/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface AuthApi {
    @POST("api/auth/refresh")
    Call<HttpResult<RefreshTokenResponse>> refreshToken(@Body RefreshTokenRequest refreshTokenRequest);
}
