package com.melvin.melvincommon.network.service;


import com.melvin.melvincommon.bean.HistoryBean;
import com.melvin.melvincommon.bean.WeatherBean;
import com.melvin.melvincommon.network.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @Author Melvin
 * @CreatedDate 2017/8/2 16:23
 * @Description ${在这里获取service}
 * @Update by       MelvinCen
 * @Date 2017/8/2 16:23
 * @Description ${TODO}
 */

public interface ApiService {

    @GET("japi/toh")
    Observable<BaseResponse<List<HistoryBean>>> getHistory(@QueryMap Map<String, String> params);

    @GET("{url}")
    Observable<BaseResponse<WeatherBean>> getWeather(@Path("url") String url, @QueryMap Map<String, String> params);

}
