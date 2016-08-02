package com.materialdesign;

import android.app.Application;


import com.facebook.stetho.Stetho;
import com.materialdesign.utils.CrashHandler;

/**
 * Created by sunwei on 2015/11/27.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class MainApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler crashHandler = CrashHandler.getInstance();
        //crashHandler.init(getApplicationContext());
        Stetho.initializeWithDefaults(this);
    }
}
