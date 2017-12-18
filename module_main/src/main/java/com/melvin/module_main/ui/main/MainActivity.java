package com.melvin.module_main.ui.main;

import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BasePresenter;

import test.melvin.module_main.R;


public class MainActivity extends BaseActivity {


    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private String[] mDataResources;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.module_main_activity_main;
    }

    @Override
    protected void initView() {
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //分割线，这里用cardview的padding因此废弃
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        mRecyclerView.setAdapter(mMainAdapter = new MainAdapter(this,mDataResources));



    }

    /**
     * 初始化数据
     */
    private void initData() {
        Resources resources = getResources();
        mDataResources = resources.getStringArray(R.array.module_main_list);
    }


}
