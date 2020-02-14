package com.shop.models.api;

import com.shop.models.bean.IndexBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ShopApi {

    @GET("index")
    Flowable<IndexBean> getIndexData();

}
