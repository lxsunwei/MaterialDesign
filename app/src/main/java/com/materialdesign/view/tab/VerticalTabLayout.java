/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.materialdesign.view.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 垂直tab
 */
public class VerticalTabLayout extends ScrollView {

    //正常文字颜色
    private int mTabTextColorNormal = android.R.color.darker_gray;
    //选中文字颜色
    private int mTabTextColorSelected = android.R.color.holo_blue_light;

    //tab字体颜色
    private ColorStateList mTabTextColorStateList = new ColorStateList(
            new int[][]{{android.R.attr.state_selected}, {}},
            new int[]{getResources().getColor(mTabTextColorSelected),
                    getResources().getColor(mTabTextColorNormal)});

    private List<Tab> mTabs = new ArrayList<>();

    private boolean hasMiddleTabView = false;

    private int mMiddleIconResId;

    public void setTabViewMiddleIcon(@DrawableRes int resId) {
        mMiddleIconResId = resId;
        hasMiddleTabView = true;
    }

    /**
     * @param tab tab
     */
    public void addTab(Tab tab) {
        mTabs.add(tab);
    }

    public Tab newTab() {
        return new Tab();
    }

    /**
     * tab文字颜色
     *
     * @param tabTextColorNormal   normal
     * @param tabTextColorSelected selected
     */
    public void setTabTextColors(@ColorRes int tabTextColorNormal, @ColorRes int tabTextColorSelected) {
        int[][] stats = new int[][]{{android.R.attr.state_selected}, {}};
        int[] colors = new int[]{getResources().getColor(tabTextColorSelected),
                getResources().getColor(tabTextColorNormal)};
        mTabTextColorStateList = new ColorStateList(stats, colors);
    }

    /**
     * tabView背景色
     *
     * @param tabViewBackgroundColorNormal   normal
     * @param tabViewBackgroundColorSelected selected
     */
    public void setTabViewBackgroundColors(@ColorRes int tabViewBackgroundColorNormal,
                                           @ColorRes int tabViewBackgroundColorSelected) {
        mTabStrip.setTabViewBackgroundColors(tabViewBackgroundColorNormal,
                tabViewBackgroundColorSelected);
    }

    /**
     * 是否显示 tab 分割线
     *
     * @param b boolean
     */
    public void setDividerIndicator(boolean b) {
        mTabStrip.mDividerIndicator = b;
    }

    /**
     * 是否显示底部线
     *
     * @param b boolean
     */
    public void setUnderlineIndicator(boolean b) {
        mTabStrip.mUnderlineIndicator = b;
    }

    /**
     * 字体大小
     *
     * @param size sp
     */
    public void setTabTextSize(int size) {
        mTabViewTextCustomSize = size;
    }

    /**
     * 上下边距
     *
     * @param size dp
     */
    public void setTabViewPadding(int size) {
        mTabViewCustomPadding = size;
    }

    /**
     * 底部border颜色
     *
     * @param colorRes
     */
    public void setBottomBorderColor(@ColorRes int colorRes) {
        mTabStrip.setTabViewBottomBorderColor(colorRes);
    }

    public interface OnTabViewSelectedListener {
        void selected(int position);
    }

    public OnTabViewSelectedListener mOnTabViewSelectedListener;

    public void setOnTabViewSelectedListener(OnTabViewSelectedListener onTabViewSelectedListener) {
        mOnTabViewSelectedListener = onTabViewSelectedListener;
    }

    public List<Tab> getTabs() {
        return mTabs;
    }

    public void tabUpdate() {
        for (int i = 0; i < mTabStrip.getChildCount(); i++) {
            View view = mTabStrip.getChildAt(i);
            ((TextView) view.findViewById(R.id.tv_tabText)).setText(mTabs.get(i).tabText);
        }
    }

    public class Tab {
        public int icon;
        public String tabText;

        public Tab() {
        }

        public Tab setIcon(@DrawableRes int icon) {
            this.icon = icon;
            return this;
        }

        public Tab setTabText(String tabText) {
            this.tabText = tabText;
            return this;
        }

        public Tab setTabText(@StringRes int stringId) {
            this.tabText = getResources().getString(stringId);
            return this;
        }
    }

    /**
     * Allows complete control over the colors drawn in the tab layout. Set with.
     */
    public interface TabColorizer {

        /**
         * @return return the color of the indicator used when {@code position} is selected.
         */
        int getIndicatorColor(int position);

        /**
         * @return return the color of the divider drawn to the right of {@code position}.
         */
        int getDividerColor(int position);

    }

    private static final int TITLE_OFFSET_DIPS = 24;
    private int mTabViewDefaultPadding = 0;
    private int mTabViewCustomPadding = 8;
    private int mTabViewTextDefaultSize = 12;
    private int mTabViewTextMediumSize = 18;
    private int mTabViewTextCustomSize = 0;

    private int mTitleOffset;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    private final VerticalTabStrip mTabStrip;

    public VerticalTabLayout(Context context) {
        this(context, null);
    }

    public VerticalTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Disable the Scroll Bar
        setVerticalScrollBarEnabled(false);
        // Make sure that the Tab Strips fills this View
        setFillViewport(true);

        mTitleOffset = (int) (TITLE_OFFSET_DIPS * getResources().getDisplayMetrics().density);

        mTabStrip = new VerticalTabStrip(context);
        addView(mTabStrip, (int)(48 * getResources().getDisplayMetrics().density),
                LayoutParams.MATCH_PARENT);
    }

    /**
     * Sets the colors to be used for indicating the selected tab. These colors are treated as a
     * circular array. Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setSelectedIndicatorColors(int... colors) {
        mTabStrip.setSelectedIndicatorColors(colors);
    }

    /**
     * Sets the colors to be used for tab dividers. These colors are treated as a circular array.
     * Providing one color will mean that all tabs are indicated with the same color.
     */
    public void setDividerColors(int... colors) {
        mTabStrip.setDividerColors(colors);
    }

    /**
     * Set the {@link ViewPager.OnPageChangeListener}. When using {@link VerticalTabLayout} you are
     * required to set any {@link ViewPager.OnPageChangeListener} through this method. This is so
     * that the layout can update it's scroll position correctly.
     *
     * @see ViewPager#setOnPageChangeListener(ViewPager.OnPageChangeListener)
     */
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPagerPageChangeListener = listener;
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    public void setViewPager(ViewPager viewPager) {
        mTabStrip.removeAllViews();

        mViewPager = viewPager;
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    /**
     * Create a default view to be used for tabs. This is called if a custom tab view is not set via
     * .
     */
    protected View createDefaultTabView(Context context) {
        View tabView = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        TextView textView = (TextView) tabView.findViewById(R.id.tv_tabText);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTabViewTextDefaultSize);
        textView.setTypeface(Typeface.DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // If we're running on Honeycomb or newer, then we can use the Theme's
            // selectableItemBackground to ensure that the View has a pressed state
            TypedValue outValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                    outValue, true);
            //textView.setBackgroundResource(outValue.resourceId);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // If we're running on ICS or newer, enable all-caps to match the Action Bar tab style
            textView.setAllCaps(true);
        }

        int padding = (int) (mTabViewDefaultPadding * getResources().getDisplayMetrics().density);
        textView.setPadding(0, padding, 0, padding);

        return tabView;
    }

    private void populateTabStrip() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final OnClickListener tabClickListener = new TabClickListener();

        for (int i = 0; i < adapter.getCount(); i++) {

            View tabView = createDefaultTabView(getContext());

            TextView tabText = (TextView) tabView.findViewById(R.id.tv_tabText);

            ImageView tabIcon = (ImageView) tabView.findViewById(R.id.iv_tabIcon);

            if (mTabs.size() > i) {
                Tab tab = mTabs.get(i);
                if (tab.icon != 0) {
                    tabIcon.setImageResource(tab.icon);
                } else {
                    tabText.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTabViewTextMediumSize);
                    int padding = (int) (mTabViewCustomPadding * getResources().getDisplayMetrics().density);
                    tabView.setPadding(0, padding, 0, padding);
                }

                if (tab.tabText != null) {
                    tabText.setText(tab.tabText);
                }
            }

            tabText.setTextColor(mTabTextColorStateList);

            if (mTabViewTextCustomSize != 0) {
                tabText.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTabViewTextCustomSize);
            }

            tabView.setOnClickListener(tabClickListener);
            LinearLayout.LayoutParams lp =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1);
            if (i == 0) {
                tabText.setSelected(true);
                tabIcon.setSelected(true);
            }
            mTabStrip.addView(tabView, lp);
        }

        if (hasMiddleTabView) {
            View tabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_layout, null);

            ImageView tabIcon = (ImageView) tabView.findViewById(R.id.iv_tabIcon);

            TextView textView = (TextView) tabView.findViewById(R.id.tv_tabText);

            textView.setVisibility(GONE);

            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                    (int)(36 * getResources().getDisplayMetrics().density),
                    (int)(36 * getResources().getDisplayMetrics().density));
            tabIcon.setLayoutParams(iconParams);
            tabIcon.setImageResource(mMiddleIconResId);

            tabView.setOnClickListener(tabClickListener);
            LinearLayout.LayoutParams lp =
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

            mTabStrip.addView(tabView, 2, lp);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mViewPager != null) {
            scrollToTab(mViewPager.getCurrentItem(), 0);
        }
    }

    private void scrollToTab(int tabIndex, int positionOffset) {
        final int tabStripChildCount = mTabStrip.getChildCount();
        if (tabStripChildCount == 0 || tabIndex < 0 || tabIndex >= tabStripChildCount) {
            return;
        }

        View selectedChild = mTabStrip.getChildAt(tabIndex);
        if (selectedChild != null) {
            int targetScrollX = selectedChild.getLeft() + positionOffset;

            if (tabIndex > 0 || positionOffset > 0) {
                // If we're not at the first child and are mid-scroll, make sure we obey the offset
                targetScrollX -= mTitleOffset;
            }

            scrollTo(targetScrollX, 0);
        }
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = mTabStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }

            mTabStrip.onViewPagerPageChanged(position, positionOffset);

            View selectedTitle = mTabStrip.getChildAt(position);
            int extraOffset = (selectedTitle != null)
                    ? (int) (positionOffset * selectedTitle.getWidth())
                    : 0;
            scrollToTab(position, extraOffset);

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                mTabStrip.onViewPagerPageChanged(position, 0f);
                scrollToTab(position, 0);
            }

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageSelected(position);
            }
        }

    }

    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                if (v == mTabStrip.getChildAt(i)) {
                    if (mOnTabViewSelectedListener!= null) {
                        mOnTabViewSelectedListener.selected(i);
                    }
                    break;
                }
            }

            if (hasMiddleTabView && v == mTabStrip.getChildAt(2)) {
                return;
            }

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                View view = mTabStrip.getChildAt(i);

                tabViewSelector(view, false);
            }

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                if (v == mTabStrip.getChildAt(i)) {
                    mViewPager.setCurrentItem(i, false);
                    tabViewSelector(v, true);
                    return;
                }
            }
        }
    }

    /**
     * 切换tab
     *
     * @param view     tabView
     * @param selector 是否选中
     */
    private void tabViewSelector(View view, boolean selector) {
        LinearLayout layout = null;
        if (LinearLayout.class.isInstance(view)) {
            layout = (LinearLayout) view;
        }

        for (int y = 0; y < layout.getChildCount(); y++) {
            layout.getChildAt(y).setSelected(selector);
        }
    }
}
