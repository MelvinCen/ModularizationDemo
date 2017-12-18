package com.melvin.pulltorefresh.lrecyclerview.presenter;

import android.os.Handler;

import com.melvin.melvincommon.base.BasePresenter;
import com.melvin.pulltorefresh.lrecyclerview.model.LRecyclerviewModel;
import com.melvin.pulltorefresh.lrecyclerview.ui.LRecyclerviewView;

import java.util.ArrayList;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/20 10:57
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/20 10:57
 * @Description ${TODO}
 */

public class LRecyclerviewPresenter extends BasePresenter<LRecyclerviewView> {

    /**每一页展示多少条数据*/
    private int request_count = 10;

    /**已经获取到多少条数据了*/
    private int mCurrentCounter = 0;

    /**
     * 服务器端一共多少条数据
     * 如果服务器没有返回总数据或者总页数，这里设置为最大值比如10000，什么时候没有数据了根据接口返回判断
     * 这里我直接就放在这里了
     */
    private static final int TOTAL_COUNTER = 34;


    public LRecyclerviewPresenter(LRecyclerviewView lRecyclerviewView) {
        attachView(lRecyclerviewView);
    }

    public void pullRefresh(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCurrentCounter = 0;
                getView().clearDataBeforePull();
                ArrayList<String> newDatas = LRecyclerviewModel.getInstance().requestData(mCurrentCounter,TOTAL_COUNTER);
                getView().pullRefresh(newDatas, request_count);
                mCurrentCounter += newDatas.size();
            }
        },1000);


    }

    public void loadMore(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter < TOTAL_COUNTER) {
                    ArrayList<String> newDatas = LRecyclerviewModel.getInstance().requestData(mCurrentCounter,TOTAL_COUNTER);
                    if (newDatas.size() == 0) {
                        //网络错误，点击可以重新加载
                        getView().networkError(request_count);
                    } else {
                        getView().loadMore(newDatas, request_count);
                    }

                } else {
                    getView().noMore();
                }
            }
        },1000);

    }
}
