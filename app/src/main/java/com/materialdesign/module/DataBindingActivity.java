package com.materialdesign.module;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.materialdesign.R;
import com.materialdesign.databinding.ActivityDataBindingBinding;
import com.materialdesign.event.UserHandles;
import com.materialdesign.model.User;
import com.materialdesign.utils.ToolbarUtils;

/**
 * Created by sunwei on 2015/11/9.
 * Email: lx_sunwei@163.com.
 * Description: 数据绑定
 */
public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(DataBindingActivity.this,
                R.layout.activity_data_binding);
        User user = new User("0", "data-binding");
        binding.setUser(user);

        UserHandles userHandles = new UserHandles();
        binding.setHandles(userHandles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dataBinding);

        ToolbarUtils.show(DataBindingActivity.this, toolbar, true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

