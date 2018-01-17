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

public abstract class UserBaseViewFragment extends TaskBaseFragment implements UserBaseContract.View{

    @Inject
    protected UserPresenterImp userPresenterImp;

    @Override
    public void onDestroy() {
        super.onDestroy();
        userPresenterImp.detachView();
    }

    @Override
    protected void setupActivityComponent() {
        MyApplication.get(getActivity())
                .getAppComponent()
                .addSub(new UserBaseViewModule(this))
                .inject(this);
    }

    @Override
    public void onLogin(String username) {

    }

    @Override
    public void onFragmentLogin(String username, int fragmentId) {

    }
}
