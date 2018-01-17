package com.davidfancy.baseproject.data.http.restfulres;

/**
 * Created by David Liu on 24/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class HttpResult<T> implements HttpResultInterface<T>{
    /**
     * T can be an object or a List<T>
     */
    private T data;

    /**
     * status can be true or false
     * true : successful
     * false : failure
     */
    private boolean status;

    /**
     * {@corelated with status}
     * if status is false, error will contains message and code.
     */
    private Error error;

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean isStatus() {
        return status;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public Error getError() {
        return error;
    }

    @Override
    public void setError(Error error) {
        this.error = error;
    }
}
