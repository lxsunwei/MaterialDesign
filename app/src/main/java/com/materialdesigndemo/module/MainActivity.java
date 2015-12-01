package com.materialdesigndemo.module;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.materialdesigndemo.R;
import com.materialdesigndemo.model.DesignItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<DesignItem> mDatas;
    private CollapsingToolbarLayout mToolbarLayout;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setSupportActionBar(mToolbar);
        mToolbarLayout.setTitle("design");
        mToolbarLayout.setExpandedTitleColor(Color.parseColor("#000000"));
        mToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        //setTitle("design");
        //mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        mDatas = new ArrayList<>();

        mDatas.add(new DesignItem("0", "ButtonColor"));

        mDatas.add(new DesignItem("1", "PercentLayout"));

        mDatas.add(new DesignItem("2", "CustomTabs"));

        mDatas.add(new DesignItem("3", "DataBinding"));

        for (int i = 0; i < 10; i++) {
            mDatas.add(new DesignItem("tmp", "tmp"));
        }

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(
                MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DesignAdapter adapter = new DesignAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_open, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.tv_open) {
            mDrawerLayout.openDrawer(mNavigationView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbarLayout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl);
        mNavigationView = (NavigationView) findViewById(R.id.main_navigationView);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.main_recycleViewScroll) {

                }
                return false;
            }
        });
    }
}
