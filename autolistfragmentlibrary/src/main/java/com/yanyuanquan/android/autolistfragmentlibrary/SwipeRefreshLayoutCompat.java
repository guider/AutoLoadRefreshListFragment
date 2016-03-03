package com.yanyuanquan.android.autolistfragmentlibrary;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;

/**
 * Created by apple on 16/2/24.
 */
public class SwipeRefreshLayoutCompat extends SwipeRefreshLayout {
    private View childView;

    public SwipeRefreshLayoutCompat(Context context, View childView) {
        super(context);
        this.childView = childView;
    }

    public SwipeRefreshLayoutCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (canRefresh) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean canChildScrollUp() {
        if (childView == null)
            return false;

        /**
         * 解决SwipeRefreshLayout 内部有多个View时 滑动冲突。
         *
         */
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (childView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) childView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return childView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(childView, -1);
        }
    }

    boolean canRefresh = true;

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
    }


}
