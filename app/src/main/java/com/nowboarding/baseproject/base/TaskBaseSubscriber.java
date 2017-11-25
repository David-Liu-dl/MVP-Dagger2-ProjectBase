package com.nowboarding.baseproject.base;

import com.nowboarding.baseproject.data.http.restfulres.HttpResultInterface;
import com.nowboarding.baseproject.function.mvpview.TaskBaseView;

import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * Created by David Liu on 28/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public abstract class TaskBaseSubscriber<T extends HttpResultInterface> extends Subscriber<T> {
    public interface WeakReferenceTaskBaseView{
        WeakReference<? extends TaskBaseView> getWeakReferenceView();
    }

    private WeakReferenceTaskBaseView weakReferenceTaskBaseView;
    private int taskId;

    public TaskBaseSubscriber(WeakReferenceTaskBaseView weakReferenceTaskBaseView, int taskId) {
        this.weakReferenceTaskBaseView = weakReferenceTaskBaseView;
        this.taskId = taskId;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (weakReferenceTaskBaseView.getWeakReferenceView() == null){
            return;
        }
        TaskBaseView view = weakReferenceTaskBaseView.getWeakReferenceView().get();
        if (view != null){
            view.onTaskStart(taskId, false);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (weakReferenceTaskBaseView.getWeakReferenceView() == null){
            return;
        }
        TaskBaseView view = weakReferenceTaskBaseView.getWeakReferenceView().get();
        if (view != null){
            view.onTaskFailure(taskId, null, e.getMessage());;
        }
    }

    @Override
    public void onNext(T t) {
        onTaskSuccessForData(t);
        if (weakReferenceTaskBaseView.getWeakReferenceView() == null){
            return;
        }
        TaskBaseView view = weakReferenceTaskBaseView.getWeakReferenceView().get();
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
    public void onCompleted() {

    }

    public abstract void onTaskSuccessForUI(T t);

    protected void onTaskSuccessForData(T t){
    }
}
