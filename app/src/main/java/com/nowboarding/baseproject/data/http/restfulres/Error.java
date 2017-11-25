package com.nowboarding.baseproject.data.http.restfulres;

/**
 * Created by David Liu on 24/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class Error {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
