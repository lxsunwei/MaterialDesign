package com.materialdesigndemo.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.materialdesigndemo.R;
import com.materialdesigndemo.utils.ToolbarUtils;

/**
 * Created by sunwei on 2015/11/3.
 * Email: lx_sunwei@163.com.
 * Description: material design 按钮颜色
 */
public class ButtonColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_color);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_buttonColor);

        ToolbarUtils.show(ButtonColorActivity.this, toolbar, true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
