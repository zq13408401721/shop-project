package com.hotel.common;

import com.hotel.apps.MyApp;

import java.io.File;

public class Constant {

    public static final String BASE_URL = "https://api.airbnb.cn/"; //wanandroid基础地址

    public static final String BASE_APK_URL = "http://cdwan.cn:9000/";   //apk的接口管理基础地址

    //网络缓存的地址
    public static final String PATH_DATA = MyApp.myApp.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/com.hotel";

    public static final String WELCOME_READ = "WELCOME_READ"; //引导页面是否已经查看




    public static final String PRICE_MODEL = "$元起";

    public static String session_id;

    public static String token;


}
