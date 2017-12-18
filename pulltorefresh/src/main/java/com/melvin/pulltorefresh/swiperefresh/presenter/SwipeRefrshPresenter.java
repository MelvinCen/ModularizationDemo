package com.melvin.pulltorefresh.swiperefresh.presenter;

import android.os.Handler;

import com.melvin.melvincommon.base.BasePresenter;
import com.melvin.pulltorefresh.swiperefresh.model.SwipeRefreshModel;
import com.melvin.pulltorefresh.swiperefresh.ui.SwipeRefreshView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/23 13:04
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/23 13:04
 * @Description ${TODO}
 */

public class SwipeRefrshPresenter extends BasePresenter<SwipeRefreshView> {

    // 当前分页页数，从0开始
    private int currentPage = 0;

    public SwipeRefrshPresenter(SwipeRefreshView swipeRefreshView) {
        attachView(swipeRefreshView);
    }

    public void pullRefresh() {
        currentPage = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> data = SwipeRefreshModel.getInstance().getPullRefreshData(currentPage);
                getView().pullRefresh(data);
                Logger.e("pullRefresh获取到数据返回" + data.toString());
            }
        }, 1000);

    }

    public void loadMore() {
        currentPage++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> loadMoreData = SwipeRefreshModel.getInstance().getLoadMoreData(currentPage);
                if (loadMoreData.size() > 0) {
                    getView().loadMore(loadMoreData);
                    Logger.e("loadMore获取到数据返回" + loadMoreData.toString());
                } else {
                    Logger.e("loadMore获取到数据失败");
                    getView().loadMoreFailure();
                }

            }
        }, 1000);


    }


}
