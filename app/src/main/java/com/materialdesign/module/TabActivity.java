package com.materialdesign.module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.materialdesign.R;
import com.materialdesign.utils.ToolbarUtils;
import com.materialdesign.view.tab.TabLayout;

/**
 * Created by sunwei on 2015/12/25.
 * Email: lx_sunwei@163.com.
 * Description: tab
 */
public class TabActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayoutTop;
    private TabLayout mTabLayoutBottom;
    private ViewPager mViewPagerTop;
    private ViewPager mViewPagerBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_tab);
        mTabLayoutBottom = (TabLayout) findViewById(R.id.tabLayout_tabBottom);
        mTabLayoutTop = (TabLayout) findViewById(R.id.tabLayout_tabTop);
        mViewPagerTop = (ViewPager) findViewById(R.id.viewPager_tabTop);
        mViewPagerBottom = (ViewPager) findViewById(R.id.viewPager_tabBottom);

        ToolbarUtils.show(TabActivity.this, mToolbar, true);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPagerTop.setAdapter(pagerAdapter);
        mViewPagerBottom.setAdapter(pagerAdapter);

        mTabLayoutTop.setTabTextColors(R.color.colorNormal, R.color.colorPrimary);
        mTabLayoutTop.setUnderlineIndicator(true);
        mTabLayoutTop.setDividerIndicator(true);
        mTabLayoutTop.addTab(mTabLayoutTop.newTab().setTabText("Tab1"));
        mTabLayoutTop.addTab(mTabLayoutTop.newTab().setTabText("Tab2"));

        mTabLayoutTop.setViewPager(mViewPagerTop);


        mTabLayoutBottom.setTabTextColors(R.color.colorNormal, R.color.colorPrimary);
        mTabLayoutBottom.addTab(mTabLayoutBottom.newTab().setIcon(R.drawable.tab1_selector)
                .setTabText("Tab1"));
        mTabLayoutBottom.addTab(mTabLayoutBottom.newTab().setIcon(R.drawable.tab2_selector)
                .setTabText("Tab2"));

        mTabLayoutBottom.setViewPager(mViewPagerBottom);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new Fragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
