package com.yanyuanquan.android.autolistfragmentlibrary;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by apple on 16/2/24.
 */
public class SwipeRefreshLayoutCompat extends SwipeRefreshLayout{

    public SwipeRefreshLayoutCompat(Context context) {
        super(context);
    }

    public SwipeRefreshLayoutCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
            if (canRefresh){
                return super.onTouchEvent(ev);
            }
        return false;
    }

    boolean canRefresh =true;
    public void setCanRefresh(boolean canRefresh){
        this.canRefresh = canRefresh;
    }


}
