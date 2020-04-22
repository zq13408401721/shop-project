package com.hotel.interfaces.login;

import com.hotel.interfaces.IBasePersenter;
import com.hotel.interfaces.IBaseView;
import com.hotel.models.bean.UserBean;

public interface LoginConstract {

    interface View extends IBaseView{
        void loginReturn(UserBean result);
    }

    interface Persenter extends IBasePersenter<View>{
        void login(String nickname,String password);
    }

}
