package com.nowboarding.baseproject.function.mvpview;

/**
 * Created by David Liu on 20/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface MvpView<T> {
    void attachView(T view);
    void detachView();
    T getView();
}
