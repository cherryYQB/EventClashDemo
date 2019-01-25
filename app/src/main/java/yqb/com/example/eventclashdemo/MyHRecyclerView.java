package yqb.com.example.eventclashdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyHRecyclerView extends RecyclerView {

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    public MyHRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyHRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //当手指按下的时候
                x1 = ev.getX();
                y1 = ev.getY();
                //不允许父View拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = ev.getX();
                y2 = ev.getY();
                /*if(y1 - y2 > 50) {
                    Log.i("tag", "向上滑...");
                } else if(y2 - y1 > 50) {
                    Log.i("tag", "向下滑...");
                } else if(x1 - x2 > 50) {
                    Log.i("tag", "向左滑...");
                } else if(x2 - x1 > 50) {
                    Log.i("tag", "向右滑...");
                }*/
                if(y1 - y2 > 50 || y2 - y1 > 50) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else if(x1 - x2 > 50 || x2 - x1 > 50){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
