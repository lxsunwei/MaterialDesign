package com.materialdesigndemo.module.adapter;

import android.support.v4.util.CircularArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.materialdesigndemo.R;
import com.materialdesigndemo.model.DesignItem;

/**
 * Created by sunwei on 2015/12/4.
 * Email: lx_sunwei@163.com.
 * Description: 滑动到底部加载更多
 */
public class DesignLoaderMoreAdapter extends BaseLoadingAdapter<DesignItem> {

    private CircularArray<DesignItem> mDesignItems;

    public DesignLoaderMoreAdapter(RecyclerView recyclerView, CircularArray<DesignItem> datas) {
        super(recyclerView, datas);

        mDesignItems = datas;
    }

    //正常条目
    public  class DesignViewHolder extends RecyclerView.ViewHolder {
        public TextView  textView;
        public DesignViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_design);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_design, parent, false);
        return new DesignViewHolder(view);
    }

    @Override
    public void onBindNormalViewHolder(RecyclerView.ViewHolder holder, int position) {
        DesignViewHolder viewHolder = (DesignViewHolder)holder;
        DesignItem designItem = mDesignItems.get(position);
        viewHolder.textView.setText(designItem.name);
    }
}
