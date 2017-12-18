package com.melvin.pulltorefresh.swiperefresh.model;

import com.melvin.melvincommon.base.BaseModel;
import com.melvin.melvincommon.utils.CommonRefUtils;
import com.melvin.melvincommon.utils.NetworkAvailableUtils;

import java.util.ArrayList;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/23 14:40
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/23 14:40
 * @Description ${TODO}
 */

public class SwipeRefreshModel extends BaseModel {

    public static SwipeRefreshModel getInstance(){
        return getPresent(SwipeRefreshModel.class);
    }

    public ArrayList<String> firstloadData(){
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("第一次加载的数据" + i);
        }

        return data;
    }

    public ArrayList<String> getPullRefreshData(int page){
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("我是下拉刷新加载的数据,第" + i + "个，第" + page + "页");
        }

        return data;
    }

    public ArrayList<String> getLoadMoreData(int page){
        ArrayList<String> data = new ArrayList<>();
        if (NetworkAvailableUtils.isNetworkAvailable(CommonRefUtils.getContext())) {

            for (int i = 0; i < 10; i++) {
                data.add("我是上拉加载的数据,第" + i + "个，第" + page + "页");
            }
            return data;
        } else {
            return data;
        }
    }
}
