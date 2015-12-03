package com.materialdesigndemo.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.materialdesigndemo.R;

/**
 * Created by sunwei on 2015/12/2.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class RefreshLayout extends SwipeRefreshLayout {

    private static final String TAG = "RefreshLayout";
    private View mLoaderView;
    private LayoutParams mLp;

    private View mTarget;

    public RefreshLayout(Context context) {
        this(context, null);
    }


    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getLoaderView();
    }

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     */
    public boolean canChildScrollDown() {
        return ViewCompat.canScrollVertically(getChildAt(0), 1);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (canChildScrollDown()) {
            Log.d(TAG, "可滑动到底----");
        } else {
            Log.d(TAG, "不可滑动到底----");


        }
    }

    private void getLoaderView() {
        mLoaderView = (LinearLayout) LayoutInflater.from(getContext()).inflate(
                R.layout.loading_view, this, false);
        //layout.setVisibility(GONE);
        try {
            mLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            //requestLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
