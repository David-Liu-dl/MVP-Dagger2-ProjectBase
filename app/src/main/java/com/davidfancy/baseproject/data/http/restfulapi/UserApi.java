package com.davidfancy.baseproject.data.http.restfulapi;

import com.davidfancy.baseproject.data.http.restfulres.HttpResult;

import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by David Liu on 6/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface UserApi {
    // demo api
    @POST("api/user/login")
    Observable<HttpResult<Void>> userLogin();

    // demo api
    @POST("api/user/{fragment_id}/login")
    Observable<HttpResult<Void>> userFragmentLogin(@Path("fragment_id") int fragmentId);
}
