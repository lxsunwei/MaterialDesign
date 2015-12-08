package com.materialdesigndemo.module;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.materialdesigndemo.R;
import com.materialdesigndemo.model.DesignItem;
import com.materialdesigndemo.module.network.VolleyPost;
import com.materialdesigndemo.utils.ToolbarUtils;

import java.util.TreeMap;

/**
 * Created by sunwei on 2015/12/7.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class NetworkActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_network);
        mTv = (TextView) findViewById(R.id.tv_network);

        ToolbarUtils.show(NetworkActivity.this, mToolbar, true);


        String url = "http://www.baidu.com";
        TreeMap<String, String> params = new TreeMap<>();

        VolleyPost<String> volleyPost = new VolleyPost<>(NetworkActivity.this);
        volleyPost.post(url, String.class, params,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = "";
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String s = "";
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
