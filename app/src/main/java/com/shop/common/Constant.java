package com.shop.common;

import com.shop.apps.MyApp;

import java.io.File;

public class Constant {

    public static final String BASE_WAN_URL = "https://www.wanandroid.com/";


    //网络缓存的地址
    public static final String PATH_DATA = MyApp.myApp.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/shop";


}
