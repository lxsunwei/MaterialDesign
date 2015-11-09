package com.materialdesigndemo.module;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.materialdesigndemo.R;
import com.materialdesigndemo.model.DesignItem;

import java.util.List;

/**
 * Created by sunwei on 2015/8/6.
 * Email: lx_sunwei@163.com.
 */
public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.ViewHolder> {

    private List<DesignItem> mDatas;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDesign;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);

            tvDesign = (TextView) view.findViewById(R.id.tv_design);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }

    public DesignAdapter(List<DesignItem> datas) {
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_item_design, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        final DesignItem designItem = mDatas.get(i);

        viewHolder.tvDesign.setText(designItem.name);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (designItem.name) {
                    case "ButtonColor":
                        Intent viewFlipper = new Intent(mContext, ButtonColorActivity.class);
                        mContext.startActivity(viewFlipper);
                        break;
                    case "PercentLayout":
                        Intent percentLayout = new Intent(mContext, PercentLayoutActivity.class);
                        mContext.startActivity(percentLayout);
                        break;
                    case "CustomTabs":
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        builder.setToolbarColor(mContext.getResources().getColor(
                                R.color.colorPrimary));
                        builder.setShowTitle(true);
                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.launchUrl((MainActivity) mContext,
                                Uri.parse("https://www.baidu.com"));
                        break;
                    case "DataBinding":
                        Intent dataBinding = new Intent(mContext, DataBindingActivity.class);
                        mContext.startActivity(dataBinding);
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}