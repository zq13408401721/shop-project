package com.shop.interfaces.main;

/**
 * 文件下载监听接口
 */
public interface DownloadListener {

    void start();
    void onProgress(int p);
    void onFinish(String path);
    void onError(String msg);

}
