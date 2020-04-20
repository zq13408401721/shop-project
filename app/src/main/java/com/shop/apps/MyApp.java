package com.shop.apps;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

public class MyApp extends Application {

    public static Context myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        initUM();
    }

    private void initUM(){
        //以清单文件中的频道设置为准 s1=null
        UMConfigure.init(this, "5e01a81b570df38f46000ec5", null, UMConfigure.DEVICE_TYPE_PHONE, null);
        //另一种设置方式  需要在清单文件中配置友盟app-key
        //UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        //手动统计模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL);
        UMConfigure.setLogEnabled(true);
    }
}
