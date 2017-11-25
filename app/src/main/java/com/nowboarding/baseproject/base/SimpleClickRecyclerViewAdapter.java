package com.nowboarding.baseproject.base;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by David Liu on 26/10/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public abstract class SimpleClickRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public interface SimpleClickListener<T>{
        void onItemClick(int position, T item);
    }

    public interface SimpleDoubleClickListener<T>{
        void onItemDoubleClick(int position, T item);
    }

    protected SimpleClickListener<T> simpleClickListener;
    protected SimpleDoubleClickListener<T> simpleDoubleClickListener;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public final void onBindViewHolder(VH holder, int position) {
        this.onBindViewHolderWithSimpleListener(holder, position);
        GestureDetector gestureDetector = new GestureDetector(holder.itemView.getContext(),new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (simpleDoubleClickListener != null){
                    simpleDoubleClickListener.onItemDoubleClick(position, getItemInPosition(position));
                }
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (simpleClickListener != null){
                    simpleClickListener.onItemClick(position, getItemInPosition(position));
                }
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
        holder.itemView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }

    public SimpleClickListener<T> getSimpleClickListener() {
        return simpleClickListener;
    }

    public void setSimpleClickListener(SimpleClickListener<T> simpleClickListener) {
        this.simpleClickListener = simpleClickListener;
    }

    public SimpleDoubleClickListener<T> getSimpleDoubleClickListener() {
        return simpleDoubleClickListener;
    }

    public void setSimpleDoubleClickListener(SimpleDoubleClickListener<T> simpleDoubleClickListener) {
        this.simpleDoubleClickListener = simpleDoubleClickListener;
    }

    public abstract void onBindViewHolderWithSimpleListener(VH holder, int position);

    public abstract T getItemInPosition(int position);
}
