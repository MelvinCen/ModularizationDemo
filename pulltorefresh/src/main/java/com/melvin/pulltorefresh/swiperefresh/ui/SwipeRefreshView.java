package com.melvin.pulltorefresh.swiperefresh.ui;

import com.melvin.melvincommon.base.BaseView;

import java.util.ArrayList;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/23 13:00
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/23 13:00
 * @Description ${TODO}
 */

public interface SwipeRefreshView extends BaseView<String> {


    void pullRefresh(ArrayList<String> datas);

    void loadMore(ArrayList<String> datas);

    void loadMoreFailure();


}
