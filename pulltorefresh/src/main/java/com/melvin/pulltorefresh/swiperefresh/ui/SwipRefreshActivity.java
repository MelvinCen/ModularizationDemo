package com.melvin.pulltorefresh.swiperefresh.ui;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.pulltorefresh.R;
import com.melvin.pulltorefresh.swiperefresh.presenter.SwipeRefrshPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class SwipRefreshActivity extends BaseActivity<SwipeRefrshPresenter> implements SwipeRefreshView {


    private SwipeRefreshLayout  mSwipeRefreshView;
    private RecyclerView        mRecyclerView;
    private SwipeRefreshAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private TextView            mTvloading;//footer中的正在加载字样
    private TextView            mTvloadDone;//footer中的加载完成字样
    private TextView            mTvloadFailure;//footer中的加载失败字样
    private View                mFooterView;//footer
    public static final int FOOTER_SHOW_LOADING = 1;
    public static final int FOOTER_SHOW_DONE = 2;
    public static final int FOOTER_SHOW_FAILURE = 3;

    private int lastVisibleItemPosition;//屏幕最后一个可见条目的位置
    private boolean isLoading = false;//是否正在加载更多

    @Override
    protected SwipeRefrshPresenter createPresenter() {
        return new SwipeRefrshPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.pull_to_refresh_activity_swip_refresh;
    }

    @Override
    protected void initView() {

        mSwipeRefreshView = (SwipeRefreshLayout) findViewById(R.id.swipe_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.swipe_recyclerview);


        //设置下拉刷新
        // 设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mSwipeRefreshView.setProgressViewOffset(true, 50, 200);
        // 设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        mSwipeRefreshView.setSize(SwipeRefreshLayout.LARGE);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeRefreshView.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mLinearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //第一次进入加载
        Logger.e("第一次进入主动刷新");
        firstInShowRefreshAnim();
        mPresenter.pullRefresh();

        mSwipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Logger.e("OnRefreshListener -- onRefresh()");
                //第一次进入的时候是没有adapter的因此不用清除，后面几次都需要清除
//                if (mAdapter != null) {
//                    mAdapter.clear();
//                    mAdapter.notifyItemRemoved(mAdapter.getItemCount());
//                }
                mPresenter.pullRefresh();

            }
        });

        mFooterView = LayoutInflater.from(this).inflate(R.layout.pull_to_refresh_loadingview, mRecyclerView, false);
        mTvloading = (TextView) mFooterView.findViewById(R.id.tv_swipe_footer_loading);
        mTvloadDone = (TextView) mFooterView.findViewById(R.id.tv_swipe_footer_done);
        mTvloadFailure = (TextView) mFooterView.findViewById(R.id.tv_swipe_footer_failure);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //当滑动状态为空闲 并且 屏幕最后一个显示的条目是倒数第二个的时候 才允许加载， 注意这里是允许，还没有加载！！！
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                    //判断swipeRefresh是否在刷新,过滤掉下拉刷新
                    boolean refreshing = mSwipeRefreshView.isRefreshing();
                    if (refreshing) {
                        return;
                    }

                    if (!isLoading) {
                        isLoading = true;
                        if (!mAdapter.hasFooterView()) {
                            mAdapter.addFooterView(mFooterView);
                        }
                        mPresenter.loadMore();
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });


    }

    @Override
    public void onRequestSuccessData(String data) {

    }

    @Override
    public void pullRefresh(ArrayList<String> datas) {
        Logger.e("回调activity中的pullRefresh" + datas.toString());
        mSwipeRefreshView.setRefreshing(false);//刷新结束
        //第一次进入刷新
        if (mAdapter == null) {
            Logger.e("第一次进入设置mAdapter");
            mAdapter = new SwipeRefreshAdapter(this, datas, R.layout.pull_to_refresh_swipe_item);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
            mAdapter.enableLoadAnimation();
        }
        //非第一次
        else {
            Logger.e("刷新添加数据");
            mAdapter.clear();
            mAdapter.notifyItemRemoved(mAdapter.getItemCount());
            mAdapter.addAll(datas);
        }



    }

    @Override
    public void loadMore(ArrayList<String> datas) {
        isLoading = false;
        mAdapter.addAll(datas);
        changeFooterStatus(2);
        removeFooter();

    }

    @Override
    public void loadMoreFailure() {
        isLoading = false;
        changeFooterStatus(3);
        removeFooter();
    }

    /**
     * 加载更多无论成功或者失败后都要删除footer
     */
    private void removeFooter() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.removeFooterView();
            }
        },1000);

    }

    /**
     * 更改footer状态
     * @param state  1，加载中 2，加载完成 3，加载失败
     */
    private void changeFooterStatus(int state){
        switch (state) {
            case 1:
                mTvloading.setVisibility(View.VISIBLE);
                mTvloadDone.setVisibility(View.GONE);
                mTvloadFailure.setVisibility(View.GONE);
                break;
            case 2:
                mTvloading.setVisibility(View.GONE);
                mTvloadDone.setVisibility(View.VISIBLE);
                mTvloadFailure.setVisibility(View.GONE);
                break;
            case 3:
                mTvloading.setVisibility(View.GONE);
                mTvloadDone.setVisibility(View.GONE);
                mTvloadFailure.setVisibility(View.VISIBLE);
                break;
            default:
                mTvloading.setVisibility(View.VISIBLE);
                mTvloadDone.setVisibility(View.GONE);
                mTvloadFailure.setVisibility(View.GONE);
                break;
        }
    }


    /**
     * 第一次进入时加载swipeRefresh的旋转动画,只是动画而已，数据加载请调用其他方法
     */
    private void firstInShowRefreshAnim(){
        mSwipeRefreshView.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshView.setRefreshing(true);
            }
        });
    }

}
