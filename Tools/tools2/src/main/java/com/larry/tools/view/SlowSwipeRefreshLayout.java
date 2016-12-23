package com.larry.tools.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 说明：灵敏度低的下拉刷新
 * 作者：mainTel
 * 时间：2016/11/25 14:50
 * 备注：
 */
public class SlowSwipeRefreshLayout extends SwipeRefreshLayout {
    public SlowSwipeRefreshLayout(Context context) {
        super(context);
    }

    private int mTouchSlop;
    // 上一次触摸时的X坐标
    private float mPrevY;
    private float mPrevX;

    public SlowSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 触发移动事件的最短距离，如果小于这个距离就不触发移动控件
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                mPrevY = ev.getY();
                mPrevX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                float moveX = ev.getX();
//
                float diffY = Math.abs(moveY - mPrevY);
//                float diffX = Math.abs(moveX - mPrevX);
//                if (diffX > diffY) {
//                    Log.e("SlowSwipeRefreshLayout", "moveY" + moveY + "  " + "mPrevY" + mPrevY);
//                    Log.e("SlowSwipeRefreshLayout", "moveX" + moveX + "  " + "mPrevX" + mPrevX);
//                    return false;
//                }

                float diffX = Math.abs(moveX - mPrevX);
                if (diffX > 100) {
                    return false;
                }
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
