package com.materialdesigndemo.module;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.CircularArray;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import com.materialdesigndemo.R;
import com.materialdesigndemo.model.DesignItem;
import com.materialdesigndemo.module.adapter.DesignLoaderMoreAdapter;
import com.materialdesigndemo.module.adapter.BaseLoadingAdapter;

/**
 * Created by sunwei on 2015/12/4.
 * Email: lx_sunwei@163.com.
 * Description: recycleView 加载更多
 */
public class LoadingMoreActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DesignLoaderMoreAdapter mDesignLoaderMoreAdapter;
    private CircularArray<DesignItem> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_loading);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView_loading);

        mToolbar.setTitle("Loading More");

        mDatas = new CircularArray<>();

        for (int i = 0; i < 29; i++) {
            mDatas.addLast(new DesignItem("" + i, "" + i));
        }

        /*mLayoutManager = new LinearLayoutManager(
                LoadingMoreActivity.this, LinearLayoutManager.VERTICAL, false);*/

        /*mLayoutManager = new GridLayoutManager(
                LoadingMoreActivity.this, 3, GridLayoutManager.VERTICAL, false);*/

        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);




        mRecyclerView.setLayoutManager(mLayoutManager);

        mDesignLoaderMoreAdapter = new DesignLoaderMoreAdapter(mRecyclerView, mDatas);

        mRecyclerView.setAdapter(mDesignLoaderMoreAdapter);

        mDesignLoaderMoreAdapter.setOnLoadingListener(new BaseLoadingAdapter.OnLoadingListener() {
            @Override
            public void loading() {
                new LoadAsyncTask().execute();
            }
        });
    }

    private class LoadAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //mGridLoadingAdapter.setLoadingError();
            mDesignLoaderMoreAdapter.setLoadingComplete();

            int size = mDatas.size();
            for (int i = size + 1; i < size + 6; i++) {
                mDatas.addLast(new DesignItem("" + i, i + ""));
            }
            mDesignLoaderMoreAdapter.notifyItemRangeInserted(mDatas.size() - 5, 5);
        }
    }
}
