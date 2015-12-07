package com.materialdesigndemo.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.materialdesigndemo.model.DesignItem;
import com.materialdesigndemo.utils.VolleyPost;

import java.util.TreeMap;

/**
 * Created by sunwei on 2015/12/7.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = "http://www.baidu.com";
        TreeMap<String, String> params = new TreeMap<>();

        VolleyPost<DesignItem> volleyPost = new VolleyPost<>(NetworkActivity.this);
        volleyPost.post(url, DesignItem.class, params,
                new Response.Listener<DesignItem>() {
                    @Override
                    public void onResponse(DesignItem response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }
}
