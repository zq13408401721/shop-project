package com.shop.persenter.login;

import com.shop.base.BasePersenter;
import com.shop.common.CommonSubscriber;
import com.shop.interfaces.login.LoginConstract;
import com.shop.models.HttpManager;
import com.shop.models.bean.UserBean;
import com.shop.utils.RxUtils;

public class LoginPersenter extends BasePersenter<LoginConstract.View> implements LoginConstract.Persenter {
    @Override
    public void login(String nickname, String password) {
        addSubscribe(HttpManager.getInstance().getShopApi().login(nickname,password)
        .compose(RxUtils.<UserBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<UserBean>(mView){

            @Override
            public void onNext(UserBean userBean) {
                if(userBean.getErrno() == 0){
                    mView.loginReturn(userBean);
                }else{
                    mView.showTips(userBean.getErrmsg());
                }
            }
        }));
    }
}
