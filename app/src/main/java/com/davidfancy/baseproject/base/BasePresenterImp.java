package com.davidfancy.baseproject.base;

import android.util.Log;

import com.davidfancy.baseproject.function.mvpview.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by David Liu on 20/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class BasePresenterImp<T> implements MvpView<T> {
    protected WeakReference<T> view;
    /**
     * bind p with v
     * @param view
     */
    @Override
    public void attachView(T view){
        this.view = new WeakReference<>(view);
    }

    @Override
    public void detachView(){
        if (view != null){
            view.clear();
            view = null;
            Log.i("BasePresenter","已经GC...");
        }
    }

    @Override
    public T getView() {
        return view.get();
    }
}
