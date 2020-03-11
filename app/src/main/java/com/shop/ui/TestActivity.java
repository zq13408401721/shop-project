package com.shop.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

import java.io.File;

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

                upload(picturePath);
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


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
}
