package com.hotel.persenter.login;

import com.hotel.base.BasePersenter;
import com.hotel.common.CommonSubscriber;
import com.hotel.interfaces.login.LoginConstract;
import com.hotel.models.HttpManager;
import com.hotel.models.bean.UserBean;
import com.hotel.utils.RxUtils;

public class LoginPersenter extends BasePersenter<LoginConstract.View> implements LoginConstract.Persenter {
    @Override
    public void login(String nickname, String password) {

    }
}
