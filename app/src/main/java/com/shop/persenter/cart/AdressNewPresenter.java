package com.shop.persenter.cart;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.cart.ShoppingConstact;
import com.shop.models.HttpManager;
import com.shop.models.bean.AddressBean;
import com.shop.models.bean.AdressSaveBean;
import com.shop.models.bean.RegionBean;
import com.shop.utils.RxUtils;

import java.util.Map;

public class AdressNewPresenter extends BasePersenter<ShoppingConstact.AdressNewView> implements ShoppingConstact.AdressNewPresenter{

    //保存地址
    @Override
    public void saveAdress(Map map) {
        addSubscribe(HttpManager.getInstance().getShopApi().saveAdress(map)
                .compose(RxUtils.<AdressSaveBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AdressSaveBean>(mView){

                    @Override
                    public void onNext(AdressSaveBean saveBean) {
                        mView.saveAdressReturn(saveBean);
                    }
                }));
    }

    //获取省市区数据
    @Override
    public void getRegion(int parentId) {
        addSubscribe(HttpManager.getInstance().getShopApi().getRegion(parentId)
                .compose(RxUtils.<RegionBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RegionBean>(mView){

                    @Override
                    public void onNext(RegionBean regionBean) {
                        mView.getRegionReturn(regionBean);
                    }
                }));
    }
}
