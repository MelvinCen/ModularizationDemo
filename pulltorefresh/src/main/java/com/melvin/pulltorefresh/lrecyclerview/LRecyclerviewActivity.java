package com.melvin.pulltorefresh.lrecyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.utils.ToastUtils;
import com.melvin.pulltorefresh.R;
import com.melvin.pulltorefresh.lrecyclerview.presenter.LRecyclerviewPresenter;
import com.melvin.pulltorefresh.lrecyclerview.ui.LRecyclerviewView;

import java.util.ArrayList;

public class LRecyclerviewActivity extends BaseActivity<LRecyclerviewPresenter> implements LRecyclerviewView{


    private Toolbar                mToolbar;
    private LRecyclerView          mLRecyclerView;
    private MyLRecyclerviewAdapter mMyLRecyclerviewAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    @Override
    protected LRecyclerviewPresenter createPresenter() {
        return new LRecyclerviewPresenter(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.pull_to_refresh_activity_lrecyclerview;
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.lrecyclerview_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLRecyclerView = (LRecyclerView) findViewById(R.id.lrecyclerView);
        //自己定义的adapter
        mMyLRecyclerviewAdapter = new MyLRecyclerviewAdapter(this);
        //框架中封装了一层adapter，主要是多item的判断，就是刷新的item，还有hearder、footer以及点击事件等，再把自己的adapter作为参数
        //传进去
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mMyLRecyclerviewAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        //布局管理
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //上啦下拉动画
        mLRecyclerView.setRefreshProgressStyle(ProgressStyle.BallBeat);
        mLRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        mLRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);

        //分割线，这里使用的DividerDecoration类是作者继承原生类写的，建造者模式。
        DividerDecoration dividerDecoration = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
//                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.color_00)
                .build();

        mLRecyclerView.addItemDecoration(dividerDecoration);

        //添加头部
        View headerView = LayoutInflater.from(this).inflate(R.layout.pull_to_refresh_lrecyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);

        mLRecyclerViewAdapter.addHeaderView(headerView);


        //下拉刷新监听
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.pullRefresh();
            }
        });
        //上拉加载监听
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMore();
            }
        });

        mLRecyclerView.setHeaderViewColor(R.color.colorAccent,android.R.color.black,android.R.color.white);
        mLRecyclerView.setFooterViewColor(R.color.colorAccent,android.R.color.black,android.R.color.white);
        //设置底部加载文字提示
        mLRecyclerView.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mMyLRecyclerviewAdapter.getDataList().size() > position) {
                    String clickedStr = mMyLRecyclerviewAdapter.getDataList().get(position);
                    ToastUtils.showToastSafe(clickedStr + "被点击");

                }

            }
        });

        //进入刷新加载一次
        mLRecyclerView.refresh();

    }

    @Override
    public void clearDataBeforePull() {
        mMyLRecyclerviewAdapter.clear();
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void pullRefresh(ArrayList<String> newDatas,int requestCount) {
        mMyLRecyclerviewAdapter.addAll(newDatas);
        mLRecyclerView.refreshComplete(requestCount);
    }

    @Override
    public void loadMore(ArrayList<String> newDatas,int requestCount) {
        mMyLRecyclerviewAdapter.addAll(newDatas);
        mLRecyclerView.refreshComplete(requestCount);
    }

    @Override
    public void noMore() {
        mLRecyclerView.setNoMore(true);
    }

    @Override
    public void networkError(int requestCount) {
        mLRecyclerView.refreshComplete(requestCount);
        mLRecyclerViewAdapter.notifyDataSetChanged();
        mLRecyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
            @Override
            public void reload() {
                mPresenter.loadMore();
            }
        });
    }

    @Override
    public void onRequestSuccessData(String data) {

    }



}
