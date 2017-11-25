package com.nowboarding.baseproject.data.http.restfulres;

/**
 * Created by David Liu on 28/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface HttpResultInterface<T> {
    T getData();

    void setData(T data);

    boolean isStatus();

    void setStatus(boolean status);

    Error getError();

    void setError(Error error);
}
