package com.shop.interfaces.main;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.ApkBean;

public interface SplashConstact {

    interface View extends IBaseView{
        void getVersionInfoReturn(ApkBean result);

        //下载成功
        void downApkSuccess();
        //下载失败
        void downApkFail();

    }

    interface Persenter extends IBasePersenter<View>{
        void getVersionInfo(String packageName);
        //下载apk
        void downApk(String url,String path,DownloadListener downloadListener);
    }

}
