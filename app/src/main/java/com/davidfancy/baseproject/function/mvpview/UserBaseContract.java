package com.davidfancy.baseproject.function.mvpview;

/**
 * Created by David Liu on 27/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public interface UserBaseContract {
    interface View extends TaskBaseContract.View{
        void onLogin(String username);
        void onFragmentLogin(String username, int fragmentId);
    }

    interface Presenter extends TaskBaseContract.Presenter{
        void login(String username);
        void fragmentLogin(String username, int fragmentId);
    }
}
