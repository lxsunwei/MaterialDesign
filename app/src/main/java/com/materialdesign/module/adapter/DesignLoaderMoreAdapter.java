package com.materialdesign.module.adapter;

import android.support.v4.util.CircularArray;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.materialdesign.R;
import com.materialdesign.model.DesignItem;

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
        public CardView cardView;
        public DesignViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_design);
            cardView = (CardView) view.findViewById(R.id.cardView_designer);

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
        if (position == 10) {
            //设置瀑布流的条目大小
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(260, 360);
            lp.setMargins(10, 40, 10, 80);
            viewHolder.cardView.setLayoutParams(lp);
        } else {
            //设置瀑布流的条目大小
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(16, 16, 16, 16);
            viewHolder.cardView.setLayoutParams(lp);
        }

        viewHolder.textView.setText(designItem.name);
    }
}
