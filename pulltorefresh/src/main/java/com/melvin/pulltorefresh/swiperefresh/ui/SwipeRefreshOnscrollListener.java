package com.melvin.pulltorefresh.swiperefresh.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/24 15:18
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/24 15:18
 * @Description ${TODO}
 */

public abstract class SwipeRefreshOnscrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLinearLayoutManager;

    private int lastVisibleItemPosition;

    private SwipeRefreshAdapter mAdapter;

    private boolean isLoading = false;

    private TextView mTvloading;
    private TextView mTvloadDone;
    private TextView mTvloadFailure;
    private View     mFooterView;
    public static final int FOOTER_SHOW_LOADING = 1;
    public static final int FOOTER_SHOW_DONE = 2;
    public static final int FOOTER_SHOW_FAILURE = 3;

    public SwipeRefreshOnscrollListener(LinearLayoutManager linearLayoutManager,
                                        SwipeRefreshAdapter swipeRefreshAdapter) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.mAdapter = swipeRefreshAdapter;

//        mFooterView = LayoutInflater.from(CommonRefUtils.getContext())
//                .inflate(R.layout.pull_to_refresh_loadingview, recyclerView,false);
//        mTvloading = (TextView) mFooterView.findViewById(R.id.tv_swipe_footer_loading);
//        mTvloadDone = (TextView) mFooterView.findViewById(R.id.tv_swipe_footer_done);
//        mTvloadFailure = (TextView) mFooterView.findViewById(R.id.tv_swipe_footer_failure);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mAdapter.getItemCount()){

            if (!isLoading) {
                isLoading = true;
                onLoadMore();
            }

        }
    }

    public abstract void onLoadMore();

    public void setIsLoadingStatus(boolean isLoading){
        this.isLoading = isLoading;
    }
}
