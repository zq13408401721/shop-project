package com.shop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shop.apps.MyApp;
import com.shop.base.BaseActivity;
import com.shop.common.Constant;
import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.main.DownloadListener;
import com.shop.interfaces.main.SplashConstact;
import com.shop.models.bean.ApkBean;
import com.shop.persenter.main.SplashPersenter;
import com.shop.utils.ApkUtils;
import com.shop.utils.SpUtils;
import com.shop.utils.SystemUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;

public class SplashActivity extends BaseActivity<SplashConstact.Persenter> implements SplashConstact.View {

    private static String TAG = SplashActivity.class.getSimpleName();

    private ApkBean apkBean;  //apk版本信息

    private AlertDialog alert;   //更新提示框

    private AlertDialog downProgress; //提示下载进度框

    private String apkPath; //apk下载到本地的路径

    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.txt_jump)
    TextView txtJump;

    private boolean isComeIn;

    //重写统计
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        txtJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isComeIn = true;
                gotoMain();
            }
        });
    }

    @Override
    protected void initData() {
        //通过包名请求版本信息
        persenter.getVersionInfo(this.getPackageName());
    }

    @Override
    protected SplashConstact.Persenter createPersenter() {
        return new SplashPersenter();
    }

    /**
     * 首先进行版本检查
     * 如果需要更新，下载最新版本如果不需要更新直接进入主页
     */
    private void checkUpdate(Long newVersion){
        //请求网络接口
        boolean bool = false;
        Long currentVersion = SystemUtils.getVersionCode(this, this.getPackageName());
        bool = newVersion > currentVersion ? true : false;
        if(bool){
            //提示更新
            showUpdateDialog();
        }else{
            //跳转到主页
            if(!SpUtils.getInstance().getBoolean(Constant.WELCOME_READ)){
                Intent intent = new Intent(this,WelComeActivity.class);
                startActivity(intent);
                finish();
            }else{
                //插入广播
                imgBg.setVisibility(View.VISIBLE);
                txtJump.setVisibility(View.VISIBLE);
                JumpTime();
            }
        }
    }

    private void JumpTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<5){
                    if(isComeIn) break;
                    try {
                        i++;
                        int time = 5-i;
                        txtJump.post(new Runnable() {
                            @Override
                            public void run() {
                                txtJump.setText("点击跳转"+String.valueOf(time));
                            }
                        });
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //时间到了直接跳主页
                if(!isComeIn){
                    isComeIn = true;
                    gotoMain();
                }
            }
        }).start();
    }


    /**
     * 弹出更新提示的框
     */
    private void showUpdateDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.alert_layout_update,null);
        alert = new AlertDialog.Builder(this).setView(view).create();
        alert.setCancelable(false);
        TextView txtDes = view.findViewById(R.id.txt_des);
        Button btnOk = view.findViewById(R.id.btn_ok);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        //根据数据判断是否是强制更新
        btnCancel.setVisibility(apkBean.getIsupdate() == 0 ? View.VISIBLE : View.GONE);
        txtDes.setText("最新版"+apkBean.getVersion()+"/n"+apkBean.getDescription());
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(apkBean.getApkurl())){
                    alert.dismiss();
                    downApk(apkBean.getApkurl());
                }else{
                    Toast.makeText(context,"apk下载地址无效",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果你选中取消更新，直接进入主页
                gotoMain();
            }
        });
        alert.show();
    }

    /**
     * 下载引用
     */
    private void downApk(String url){
        String fileName;
        if(url.endsWith(".apk")){
            int start = url.lastIndexOf("/");
            fileName = url.substring(start+1,url.length());
        }else{
            fileName = "shop.apk";
        }

        View view = LayoutInflater.from(context).inflate(R.layout.alert_down_progress,null);
        downProgress = new AlertDialog.Builder(context).setView(view).create();
        downProgress.setCancelable(false);
        TextView txtProgress = view.findViewById(R.id.txt_progress);
        ProgressBar progressBar = view.findViewById(R.id.progress);
        progressBar.setProgress(0);
        txtProgress.setText("0%");
        downProgress.show();
        apkPath = Environment.getExternalStorageDirectory() +"/"+ fileName;
        //调用apk的下载
        persenter.downApk(url, apkPath, new DownloadListener() {
            @Override
            public void start() {

            }

            @Override
            public void onProgress(int p) {
                progressBar.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(p);
                        txtProgress.setText(p+"%");
                    }
                });

            }

            @Override
            public void onFinish(String path) {
                installApk();
                downProgress.dismiss();
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    //安装apk 静默安装
    private void installApk(){
        boolean bool = ApkUtils.install(apkPath);
        Log.i("install","安装是否成功："+bool);
        if(!bool) {
            if (apkBean.getIsupdate() != 0) {
                gotoMain();
            }
        }
    }

    //跳转到主页
    private void gotoMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    //请求版本信息
    @Override
    public void getVersionInfoReturn(ApkBean result) {
        apkBean = result;
        checkUpdate((long) apkBean.getVersioncode());
    }

    //下载成功 安装apk
    @Override
    public void downApkSuccess() {

    }

    //下载失败
    @Override
    public void downApkFail() {
        if(apkBean != null && apkBean.getIsupdate() == 0){
            gotoMain();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onDestroy();
    }
}
