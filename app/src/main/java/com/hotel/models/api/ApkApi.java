package com.hotel.models.api;

import com.hotel.models.bean.ApkBean;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApkApi {

    @GET("/getversion")
    Flowable<ApkBean> getVersion(@Query("pkname") String pkname);

    //下载文件的接口
    @Streaming
    @GET
    Call<ResponseBody> downLoadFile(@Url String url);

}
