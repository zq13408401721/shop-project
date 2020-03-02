package com.shop.interfaces.login;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.UserBean;

public interface LoginConstract {

    interface View extends IBaseView{
        void loginReturn(UserBean result);
    }

    interface Persenter extends IBasePersenter<View>{
        void login(String nickname,String password);
    }

}
