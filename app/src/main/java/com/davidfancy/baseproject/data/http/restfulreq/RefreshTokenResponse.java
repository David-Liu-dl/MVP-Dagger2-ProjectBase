package com.davidfancy.baseproject.data.http.restfulreq;

/**
 * Created by David Liu on 2/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface RefreshTokenResponse {
    String getAccessToken();
    String getRefreshToken();
}
