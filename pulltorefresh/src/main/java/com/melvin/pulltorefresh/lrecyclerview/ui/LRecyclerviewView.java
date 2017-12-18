package com.melvin.pulltorefresh.lrecyclerview.ui;

import com.melvin.melvincommon.base.BaseView;

import java.util.ArrayList;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/20 10:57
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/20 10:57
 * @Description ${TODO}
 */

public interface LRecyclerviewView extends BaseView<String> {

    void clearDataBeforePull();

    void pullRefresh(ArrayList<String> newDatas,int requestCount);

    void loadMore(ArrayList<String> newDatas,int requestCount);

    void noMore();

    void networkError(int requestCount);
}
