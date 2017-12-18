package com.melvin.pulltorefresh.main;

import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.mzule.activityrouter.annotation.Router;
import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BasePresenter;
import com.melvin.pulltorefresh.R;

@Router("PullToRefreshMainActivity")
public class PullToRefreshMainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private PullToRefreshMainAdapter mAdapter;
    private String[] mDataResources;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.pull_to_refresh_activity_main;
    }

    @Override
    protected void initView() {
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.pull_to_refresh_main_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter = new PullToRefreshMainAdapter(this,mDataResources));


    }

    private void initData() {
        Resources resources = getResources();
        mDataResources = resources.getStringArray(R.array.pull_to_refresh_list);
    }
}
