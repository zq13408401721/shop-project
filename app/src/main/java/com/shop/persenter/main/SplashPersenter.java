package com.shop.persenter.main;

import android.util.Log;

import com.shop.apps.MyApp;
import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.main.DownloadListener;
import com.shop.interfaces.main.SplashConstact;
import com.shop.models.HttpManager;
import com.shop.models.bean.ApkBean;
import com.shop.utils.RxUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPersenter extends BasePersenter<SplashConstact.View> implements SplashConstact.Persenter {
    //获取版本号
    @Override
    public void getVersionInfo(String packageName) {
        addSubscribe(HttpManager.getInstance().getApkApi().getVersion(packageName)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<ApkBean>(mView){

            @Override
            public void onNext(ApkBean apkBean) {
                mView.getVersionInfoReturn(apkBean);
            }
        }));
    }

    //下载apk
    @Override
    public void downApk(String url,String path,DownloadListener downloadListener) {
        HttpManager.getInstance().getApkApi().downLoadFile(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //开启子线程把服务器端的apk写入到本地SD卡
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        writeResponseBodyToDisk(response.body(),downloadListener,path);
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mView.downApkFail();
            }
        });
    }

    /**
     * 把服务的apk文件写入本地
     * @param body
     * @param downloadListener
     * @param path
     * @return
     */
    private boolean writeResponseBodyToDisk(ResponseBody body,final DownloadListener downloadListener,String path) {
        if (downloadListener!=null)
            downloadListener.start();
        try {
            // 改成自己需要的存储位置
            File file = new File(path);
            Log.e("TAG","writeResponseBodyToDisk() file="+file.getPath());
            if (file.exists()) {
                file.delete();
            }
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    //计算当前下载百分比，并经由回调传出
                    if (downloadListener!=null)
                        downloadListener.onProgress((int) (100 * fileSizeDownloaded / fileSize));
                    Log.d("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                if (downloadListener!=null)
                    downloadListener.onFinish(file.getPath());
                outputStream.flush();

                return true;
            } catch (IOException e) {
                if (downloadListener!=null)
                    downloadListener.onError(""+e.getMessage());
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
