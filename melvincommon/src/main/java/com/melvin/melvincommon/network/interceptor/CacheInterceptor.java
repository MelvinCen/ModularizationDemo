package com.melvin.melvincommon.network.interceptor;

import com.melvin.melvincommon.utils.CommonRefUtils;
import com.melvin.melvincommon.utils.NetworkAvailableUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author Melvin
 * @CreatedDate 2017/8/16 15:13
 * @Description ${TODO}
 * @Update by       MelvinCen
 * @Date 2017/8/16 15:13
 * @Description ${TODO}
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();//获取请求
        boolean networkAvailable = NetworkAvailableUtils.isNetworkAvailable(CommonRefUtils.getContext());

        //有网络就去网络读取，没有网络则读取缓存
        if (!networkAvailable) {//离线缓存
            request = request.newBuilder()
                    .cacheControl(FORCE_CACHE1)
                    .build();
        } else {
            //在线缓存
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        }

        Response originalResponse = chain.proceed(request);

        if (networkAvailable) {//在线缓存
            int maxAge = 60;// 在线缓存在6s内可读取
            String cacheControl = request.cacheControl().toString();
            Logger.e("在线缓存在1分钟内可读取: " + cacheControl);
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }

        else {//离线缓存
            int maxTime = 28*24*60*60;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale="+maxTime)
                    .build();
        }

    }

    //---修改了系统方法--这是设置在多长时间范围内获取缓存里面
    public static final CacheControl FORCE_CACHE1 = new CacheControl.Builder()
            .onlyIfCached()
            .maxStale(7, TimeUnit.SECONDS)//这里是7s，CacheControl.FORCE_CACHE--是int型最大值
            .build();
}
