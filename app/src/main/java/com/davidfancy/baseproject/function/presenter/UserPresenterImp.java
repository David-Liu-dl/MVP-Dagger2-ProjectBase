package com.davidfancy.baseproject.function.presenter;

import android.content.Context;

import com.davidfancy.baseproject.base.BasePresenterImp;
import com.davidfancy.baseproject.base.TaskBaseSubscriber;
import com.davidfancy.baseproject.data.http.restfulapi.UserApi;
import com.davidfancy.baseproject.data.http.restfulres.HttpResult;
import com.davidfancy.baseproject.function.mvpview.UserBaseContract;
import com.davidfancy.baseproject.util.HttpUtil;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by David Liu on 17/1/18.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class UserPresenterImp extends BasePresenterImp<UserBaseContract.View>
        implements UserBaseContract.Presenter {

    public static final int TASK_LOGIN = 100;

    private final Context context;
    private final UserApi noAuthUserApi;
    private final UserApi userApi;

    @Inject
    public UserPresenterImp(UserBaseContract.View view, Context context, @Named("noAuth") UserApi noAuthUserApi, @Named("default") UserApi userApi) {
        this.attachView(view);
        this.context = context;
        this.noAuthUserApi = noAuthUserApi;
        this.userApi = userApi;
    }

    @Override
    public void login(String email) {
        Observable<HttpResult<Void>> observable = noAuthUserApi.userLogin();
        Subscriber<HttpResult<Void>> subscriber = new TaskBaseSubscriber<HttpResult<Void>>(this, TASK_LOGIN) {
            /**
             * onTaskSuccessForData always be called even view is GC
             * this method for store the data
             * @param userLoginResponseHttpResult
             */
            @Override
            protected void onTaskSuccessForData(HttpResult<Void> userLoginResponseHttpResult) {
                // TODO: 17/1/18 Store the data & Operate the DB
            }

            @Override
            public void onTaskSuccessForUI(HttpResult<Void> userLoginResponseHttpResult) {
                getView().onLogin(email);
            }
        };

        HttpUtil.subscribe(observable, subscriber);
    }

    @Override
    public void fragmentLogin(String email, int fragmentId) {

        /**
         * For demo
         */
//        Observable<HttpResult<Void>> observable = noAuthUserApi.userFragmentLogin(fragmentId);
//        Subscriber<HttpResult<Void>> subscriber = new TaskBaseSubscriber<HttpResult<Void>>(this, TASK_LOGIN) {
//            @Override
//            protected void onTaskSuccessForData(HttpResult<Void> userLoginResponseHttpResult) {
//            }
//
//            @Override
//            public void onTaskSuccessForUI(HttpResult<Void> userLoginResponseHttpResult) {
//                getView().onFragmentLogin(email, fragmentId);
//            }
//        };
//
//        HttpUtil.subscribe(observable, subscriber);

        /**
         * Fake Response, demo for success
         */
        getView().onFragmentLogin(email, fragmentId);
    }
}
