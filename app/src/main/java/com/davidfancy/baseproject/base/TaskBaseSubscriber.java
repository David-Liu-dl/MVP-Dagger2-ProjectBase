package com.davidfancy.baseproject.base;


import com.davidfancy.baseproject.data.http.restfulres.HttpResultInterface;
import com.davidfancy.baseproject.function.mvpview.MvpView;
import com.davidfancy.baseproject.function.mvpview.TaskBaseView;

import rx.Subscriber;

/**
 * Created by David Liu on 28/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public abstract class TaskBaseSubscriber<T extends HttpResultInterface> extends Subscriber<T> {

    private MvpView<? extends TaskBaseView> mvpView;
    private int taskId;

    public TaskBaseSubscriber(MvpView<? extends TaskBaseView> weakReferenceTaskBaseView, int taskId) {
        this.mvpView = weakReferenceTaskBaseView;
        this.taskId = taskId;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mvpView.getView() == null){
            return;
        }
        TaskBaseView view = mvpView.getView();
        if (view != null){
            view.onTaskStart(taskId, false);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mvpView.getView() == null){
            return;
        }
        TaskBaseView view = mvpView.getView();
        if (view != null){
            view.onTaskFailure(taskId, null, e.getMessage());
        }
    }

    @Override
    public void onNext(T t) {
        // update data when success
        if (t.isStatus()){
            onTaskSuccessForData(t);
        }

        // update UI
        if (mvpView.getView() == null){
            return;
        }

        TaskBaseView view = mvpView.getView();
        if (view != null){
            if (t.isStatus()){
                view.onTaskSuccess(taskId, t);
                onTaskSuccessForUI(t);
            }else {
                view.onTaskFailure(taskId, t, t.getError().getMessage());
            }
        }
    }

    @Override
    public void onCompleted() {}

    public abstract void onTaskSuccessForUI(T t);

    protected void onTaskSuccessForData(T t){}
}
