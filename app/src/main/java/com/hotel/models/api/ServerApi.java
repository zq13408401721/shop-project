package com.hotel.models.api;

import com.hotel.models.bean.ExploreBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ServerApi {

    @GET("v2/explore_tabs")
    Flowable<ExploreBean> getExplore(@QueryMap Map<String,String> map);

}
