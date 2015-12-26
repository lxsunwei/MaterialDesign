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
import com.materialdesign.widget.tab.TabLayout;

/**
 * Created by sunwei on 2015/12/25.
 * Email: lx_sunwei@163.com.
 * Description:
 */
public class TabActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_tab);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_tab);
        mViewPager = (ViewPager) findViewById(R.id.viewPager_tab);

        ToolbarUtils.show(TabActivity.this, mToolbar, true);

        android.support.design.widget.TabLayout t;

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab1_selector).setTabText("Tab1"));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.tab2_selector).setTabText("Tab2"));

        mTabLayout.setViewPager(mViewPager);
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
}
