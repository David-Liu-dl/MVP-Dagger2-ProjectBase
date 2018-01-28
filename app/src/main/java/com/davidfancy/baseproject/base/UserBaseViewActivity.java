package com.davidfancy.baseproject.base;

import com.davidfancy.baseproject.function.module.UserBaseViewModule;
import com.davidfancy.baseproject.function.mvpview.UserBaseContract;
import com.davidfancy.baseproject.function.presenter.UserPresenterImp;

import javax.inject.Inject;

/**
 * Created by David Liu on 27/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public abstract class UserBaseViewActivity extends TaskBaseActivity implements UserBaseContract.View{

    protected UserPresenterImp userPresenter;

    @Inject
    public void setUserPresenter(UserPresenterImp userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    protected void setupActivityComponent() {
        MyApplication.get(this)
                .getAppComponent()
                .addSub(new UserBaseViewModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.detachView();
    }


    @Override
    public void onLogin(String username) {

    }

    @Override
    public void onFragmentLogin(String username, int fragmentId) {

    }
}
