package com.shop.models.api;

import com.shop.models.bean.ChaptersBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface WanApi {

    @GET("wxarticle/chapters/json")
    Flowable<ChaptersBean> getChapters();


}
