package com.hotel.interfaces.main;

import com.hotel.interfaces.IBasePersenter;
import com.hotel.interfaces.IBaseView;
import com.hotel.models.bean.ApkBean;

public interface SplashConstact {

    interface View extends IBaseView {
        void getVersionInfoReturn(ApkBean result);

        //下载成功
        void downApkSuccess();
        //下载失败
        void downApkFail();

    }

    interface Persenter extends IBasePersenter<View> {
        void getVersionInfo(String packageName);
        //下载apk
        void downApk(String url, String path, DownloadListener downloadListener);
    }

}
