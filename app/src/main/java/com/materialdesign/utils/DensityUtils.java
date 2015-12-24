package com.materialdesign.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by sunwei on 2015/12/22.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class DensityUtils {

    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
