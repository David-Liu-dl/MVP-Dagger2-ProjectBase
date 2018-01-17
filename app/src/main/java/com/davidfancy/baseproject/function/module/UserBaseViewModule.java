package com.davidfancy.baseproject.function.module;

import com.davidfancy.baseproject.function.ActivityScope;
import com.davidfancy.baseproject.function.mvpview.UserBaseContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 17/1/18.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

@Module
public class UserBaseViewModule {
    private UserBaseContract.View view;

    public UserBaseViewModule(UserBaseContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public UserBaseContract.View providerView(){
        return view;
    }
}
