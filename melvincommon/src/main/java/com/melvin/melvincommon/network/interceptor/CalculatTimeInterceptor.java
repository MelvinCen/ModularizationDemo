package com.melvin.melvincommon.network.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author Melvin
 * @CreatedDate 2017/9/28 15:32
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/9/28 15:32
 * @Description ${TODO}
 */

public class CalculatTimeInterceptor implements Interceptor {

    public static String TAG = "CalculatTimeInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;
        Log.e(TAG,"----------网络访问用时:"+duration+"毫秒----------");
        return response;
    }
}
