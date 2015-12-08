package com.materialdesign.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.materialdesign.R;
import com.materialdesign.utils.ToolbarUtils;

/**
 * Created by sunwei on 2015/11/7.
 * Email: lx_sunwei@163.com.
 * Description: 百分比布局
 */
public class PercentLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_percentLayout);

        ToolbarUtils.show(PercentLayoutActivity.this, toolbar, true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
