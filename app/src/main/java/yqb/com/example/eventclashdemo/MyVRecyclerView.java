package yqb.com.example.eventclashdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyVRecyclerView extends RecyclerView {

    private float mLastY = 0;// 记录上次Y位置

    public MyVRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyVRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getY();
                //不允许父View拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float nowY = ev.getY();
                RecyclerView.LayoutManager layoutManager = getLayoutManager();
                //Log.i("tag", "layoutManager.getItemCount():"+layoutManager.getItemCount());
                //Log.i("tag", "layoutManager.getChildCount():"+layoutManager.getChildCount());

                LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                int lastItemPosition = linearManager.findLastVisibleItemPosition();
                int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                //Log.i("tag", "firstItemPosition:"+firstItemPosition+", lastItemPosition:"+lastItemPosition);
                //Log.i("tag", "nowY:"+nowY+", mLastY:"+mLastY);
                if((firstItemPosition == 0 && nowY > mLastY)
                        || (lastItemPosition == layoutManager.getItemCount()-1 && nowY < mLastY)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                mLastY = nowY;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
