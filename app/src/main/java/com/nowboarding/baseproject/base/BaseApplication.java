package com.nowboarding.baseproject.base;

import android.app.Application;
import com.nowboarding.baseproject.function.Authenticator;

/**
 * Created by David Liu on 10/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public abstract class BaseApplication extends Application {
    public abstract Authenticator getAuthenticator();
    public abstract ConstantInterface getConstant();
}
