package com.shop.ui.home;

import com.shop.R;
import com.shop.base.BaseFragment;
import com.shop.base.BasePersenter;
import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.home.HomeConstract;
import com.shop.models.bean.IndexBean;
import com.shop.persenter.home.*;

public class HomeFragment extends BaseFragment<HomeConstract.Persenter> implements HomeConstract.View {
    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        persenter.getHomeData();
    }

    @Override
    protected HomeConstract.Persenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void getHomeDataReturn(IndexBean result) {

    }
}