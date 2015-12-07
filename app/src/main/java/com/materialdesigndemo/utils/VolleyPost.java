package com.materialdesigndemo.utils;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.materialdesigndemo.module.network.GsonRequest;
import com.materialdesigndemo.module.network.VolleyInstance;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sunwei on 2015/12/7.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class VolleyPost<T> {

    private Context mContext;
    private static VolleyPost sVolleyPostInstance;

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


    public interface OnErrorListener {
        Response.ErrorListener errorListener();
    }

    public OnErrorListener onErrorListener;

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

}
