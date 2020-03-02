package com.shop.persenter.login;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.login.RegisterConstract;
import com.shop.models.HttpManager;
import com.shop.models.bean.VerifyBean;
import com.shop.utils.RxUtils;

public class RegisterPersenter extends BasePersenter<RegisterConstract.View> implements RegisterConstract.Persenter {
    @Override
    public void getVerify() {
        addSubscribe(HttpManager.getInstance().getShopApi().getVerify()
        .compose(RxUtils.<VerifyBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<VerifyBean>(mView){

            @Override
            public void onNext(VerifyBean verifyBean) {
                mView.getVerifyReturn(verifyBean);
            }
        }));
    }

}
