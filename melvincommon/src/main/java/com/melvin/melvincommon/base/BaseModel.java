package com.melvin.melvincommon.base;

import android.text.TextUtils;

import com.melvin.melvincommon.network.BaseRetrofit;
import com.melvin.melvincommon.network.service.ApiService;
import com.orhanobut.logger.Logger;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author Melvin
 * @CreatedDate 2017/8/3 15:27
 * @Description ${TODO}
 * @Update by       MelvinCen
 * @Date 2017/8/3 15:27
 * @Description ${TODO}
 */

public class BaseModel extends BaseRetrofit {

    private static final String TAG = "BaseModel";

    protected ApiService mApiService;

    protected Map<String, String> mParams = new HashMap<>();

    public BaseModel() {
        super();
        mApiService = mRetrofit.create(ApiService.class);
    }


    @Override
    protected Map<String, String> getCommonMap() {
        Map<String, String> commonMap = new HashMap<>();
        return commonMap;
    }

    /**
     * 添加单个参数
     * @param key
     * @param value
     */
    protected void addParams(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            Logger.e("the key is null");
            return;
        }
        mParams.put(key, value);
    }

    /**
     * 添加多个参数，以map的形式
     * @param params
     */
    protected void addParams(Map<String, String> params) {
        if (null == params) {
            Logger.e("the map is null");
            return;
        }
        mParams.putAll(params);
    }

}
