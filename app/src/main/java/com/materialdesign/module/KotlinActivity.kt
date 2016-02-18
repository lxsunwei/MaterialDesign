package com.materialdesign.module

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.materialdesign.R
import com.materialdesign.utils.ToolbarUtils

/**
 * Created by sunwei on 2016/2/18.
 * Email: lx_sunwei@163.com.
 * Description:
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin);

        ToolbarUtils.show(this, findViewById(R.id.toolbar_kotlin) as Toolbar?, true);

        var mTvKotlin: TextView ?= findViewById(R.id.tv_kotlin) as TextView;

        mTvKotlin?.text = "Kotlin";

    }

    override fun onSupportNavigateUp(): Boolean {
        finish();
        return true;
    }

}
