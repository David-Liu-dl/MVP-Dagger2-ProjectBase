package com.nowboarding.baseproject.base;

/**
 * Created by David Liu on 25/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class C implements ConstantInterface {
    public static class api{
        public static final String BASE = "";
    }

    @Override
    public String getBaseUrl() {
        return api.BASE;
    }
}
