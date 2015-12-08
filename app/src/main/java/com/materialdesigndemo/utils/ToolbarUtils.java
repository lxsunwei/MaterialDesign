package com.materialdesigndemo.utils;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by sunwei on 2015/12/8.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class ToolbarUtils {

    public static void show(AppCompatActivity activity, Toolbar toolbar, boolean back) {
        activity.setSupportActionBar(toolbar);

        ActionBar bar = activity.getSupportActionBar();

        if (back) {
            setOptions(bar, ActionBar.DISPLAY_HOME_AS_UP);
        }

    }

    private static void setOptions(ActionBar bar, int flags) {
        int change = bar.getDisplayOptions() ^ flags;
        bar.setDisplayOptions(change, flags);
    }
}
