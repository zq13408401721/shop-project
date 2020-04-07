package com.shop.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.shop.R;
import com.shop.base.BaseActivity;
import com.shop.common.Constant;
import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.test.TestConstract;
import com.shop.models.api.ShopApi;
import com.shop.models.bean.ChaptersBean;
import com.shop.persenter.test.TestPersenter;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestActivity extends BaseActivity<TestConstract.Persenter> implements TestConstract.View {

    @BindView(R.id.btn_upload)
    Button btnUpload;

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    MenuItem menuItem;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        persenter.getChapters();
    }

    @Override
    protected TestConstract.Persenter createPersenter() {
        return new TestPersenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_item,menu);
        menuItem = menu.findItem(R.id.search_view);
        searchView.setMenuItem(menuItem);
        return true;
    }

    @Override
    public void getChaptersReturn(ChaptersBean result) {
        Log.i("Test",result.getData().toString());

        Message msg = new Message();
        msg.what = 1;
        msg.obj = "error";
        handler.sendMessage(msg);

        applogin();

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

    }

    /**
     * 测试传递json参数
     */
    private void applogin(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_SHOP_URL)
                .build();
        ShopApi shopApi = retrofit.create(ShopApi.class);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("classname","1905");
            jsonObject.put("number",28);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),jsonObject.toString());
        Call<ResponseBody> call = shopApi.applogin(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("success",response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("fail",t.getMessage());
            }
        });

    }


    private void uploadImage(){
        PictureSelector.create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(200, 200, 1, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                //获取图片本地路径
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);

                //upload(picturePath);

                httpUplaod(picturePath);
            }
        }
    }

    private void upload(String url){
        File file = new File(url);
        RequestBody reqBody = RequestBody.create(MediaType.parse("image/png"),file);

        MultipartBody.Part part = MultipartBody.Part.createFormData("file",file.getName(),reqBody);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yun918.cn/study/public/")
                .build();
        RequestBody keyBody = RequestBody.create(MediaType.parse("multipart/form-data"),"part");
        Call<ResponseBody> call = retrofit.create(ShopApi.class).uploadImg(keyBody,part);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("success",response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("fail",t.getMessage());
            }
        });

    }

    /**
     * http upload
     * @param path
     */
    private void httpUplaod(String path){
        String uploadUrl = "http://yun918.cn/study/public/file_upload.php";

        byte[] body_data = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int pos = 0;
        byte[] buffer = new byte[8*1024];

        try {
            while((pos = bis.read(buffer)) != -1){
                baos.write(buffer,0,pos);
                baos.flush();
            }
            body_data = baos.toByteArray();
            baos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        String result = submitBody(uploadUrl,path,body_data);
        Log.i("result:",result);
    }

    private String submitBody(String url,String filePath,byte[] body_data){
        HttpURLConnection httpURLConn = null;
        BufferedInputStream bis = null;
        DataOutputStream dos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            URL urlObj = new URL(url);
            httpURLConn = (HttpURLConnection) urlObj.openConnection();
            httpURLConn.setDoInput(true);
            httpURLConn.setDoOutput(true);
            httpURLConn.setRequestMethod("POST");
            httpURLConn.setUseCaches(false);
            httpURLConn.setRequestProperty("Connecttion","Keep-Alive");
            httpURLConn.setRequestProperty("Accept","*/*");
            httpURLConn.setRequestProperty("Accept-Encoding","gzip,deflate");
            httpURLConn.setRequestProperty("Cache-Control","no-cache");
            //httpURLConn.setRequestProperty("Content-Type","multipart/form-data;");
            httpURLConn.connect();
            dos = new DataOutputStream(httpURLConn.getOutputStream());
            dos.writeBytes("Content-Disposition:multipart/form-data; key=\"file_dir\"");

            if(body_data != null && body_data.length > 0){
                String fileName = filePath.substring(filePath.lastIndexOf(File.separatorChar));
                dos.writeBytes("Content-Disposition:image/png; file=\""+fileName+"\"");
                dos.write(body_data);
            }
            dos.flush();

            byte[] buffer = new byte[8*1024];
            int pos = 0;
            if(httpURLConn.getResponseCode() == 200){
                bis = new BufferedInputStream(httpURLConn.getInputStream());
                while((pos = bis.read(buffer)) != -1){
                    baos.write(buffer,0,pos);
                    baos.flush();
                }
            }
            return new String(baos.toByteArray(),"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dos.close();
                bis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
}
