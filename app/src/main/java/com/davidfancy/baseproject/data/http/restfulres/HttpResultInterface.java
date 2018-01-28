package com.davidfancy.baseproject.data.http.restfulres;

/**
 * Created by David Liu on 28/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface HttpResultInterface<T> {
    /**
     * Response data from Api call
     * @return T
     */
    T getData();

    void setData(T data);

    /**
     * Return the status of Api Call (Successful or Failed)
     * @return boolean
     */
    boolean isStatus();

    void setStatus(boolean status);

    /**
     * Get error with message when failure on Api call
     * @return Error
     */
    Error getError();

    void setError(Error error);
}
