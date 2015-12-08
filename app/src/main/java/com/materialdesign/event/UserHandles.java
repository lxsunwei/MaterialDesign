package com.materialdesign.event;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by sunwei on 2015/11/9.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class UserHandles {

    public void onClickUser(View view) {
        Snackbar.make(view, "已点击", Snackbar.LENGTH_SHORT).show();
    }
}
