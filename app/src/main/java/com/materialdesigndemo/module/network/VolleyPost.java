package com.materialdesigndemo.module.network;

import android.content.Context;

import com.android.volley.Response;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sunwei on 2015/12/7.
 * Email: lx_sunwei@163.com.
 * Description: Volley 请求
 */
public class VolleyPost<T> {

    private Context mContext;

    public static final String TAG = "VolleyPost";

    public VolleyPost(Context context) {
        mContext = context;
    }


    public void post(String url, Class<T> clazz,
                     Map<String, String> params,
                     Response.Listener<T> listener,
                     Response.ErrorListener errorListener) {

        Map<String, String> heads = new TreeMap<>();

        GsonRequest<T> gsonRequest = new GsonRequest<>(
                url,
                clazz,
                heads,
                params,
                listener,
                errorListener
        );

        gsonRequest.setTag(TAG);
        VolleyInstance.get(mContext).getRequestQueue().add(gsonRequest);
    }
}
