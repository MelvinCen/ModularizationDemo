package com.melvin.pulltorefresh.lrecyclerview.model;

import com.melvin.melvincommon.base.BaseModel;
import com.melvin.melvincommon.utils.CommonRefUtils;
import com.melvin.melvincommon.utils.NetworkAvailableUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/20 10:35
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/20 10:35
 * @Description ${TODO}
 */

public class LRecyclerviewModel extends BaseModel {

    private ArrayList<String> newDatas = new ArrayList();

    public static LRecyclerviewModel getInstance() {
        return getPresent(LRecyclerviewModel.class);
    }

    /**
     * @param currentDataSize 目前数据集的数据个数
     * @return
     */
    public ArrayList<String> requestData(final int currentDataSize, final int totalCount) {


        if (NetworkAvailableUtils.isNetworkAvailable(CommonRefUtils.getContext())) {
            //有网络的状态下
            //组装10个数据
            for (int i = 0; i < 10; i++) {
                if (newDatas.size() + currentDataSize >= totalCount) {
                    break;
                }
                newDatas.add("item" + (currentDataSize + i));
            }

        } else {
            //模拟一下网络请求失败的情况
        }


        Logger.e("newDatas2 = " + newDatas);
        return newDatas;

    }


}
