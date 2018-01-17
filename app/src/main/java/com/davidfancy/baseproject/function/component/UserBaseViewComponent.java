package com.davidfancy.baseproject.function.component;


import com.davidfancy.baseproject.base.UserBaseViewActivity;
import com.davidfancy.baseproject.base.UserBaseViewFragment;
import com.davidfancy.baseproject.function.ActivityScope;
import com.davidfancy.baseproject.function.module.UserBaseViewModule;

import dagger.Subcomponent;

/**
 * Created by David Liu on 27/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

@ActivityScope
@Subcomponent(modules = UserBaseViewModule.class)
public interface UserBaseViewComponent {
    void inject(UserBaseViewActivity userBaseViewActivity);
    void inject(UserBaseViewFragment userBaseViewFragment);
}
