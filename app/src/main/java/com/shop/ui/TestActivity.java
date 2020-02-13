package com.shop.ui;

import android.util.Log;

import com.shop.R;
import com.shop.base.BaseActivity;
import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.test.TestConstract;
import com.shop.models.bean.ChaptersBean;
import com.shop.persenter.test.TestPersenter;

public class TestActivity extends BaseActivity<TestConstract.Persenter> implements TestConstract.View {
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
    }
}
