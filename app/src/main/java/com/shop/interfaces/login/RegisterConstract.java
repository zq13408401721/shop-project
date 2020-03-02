package com.shop.interfaces.login;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.VerifyBean;

public interface RegisterConstract {

    interface View extends IBaseView{
        void getVerifyReturn(VerifyBean result);
    }

    interface Persenter extends IBasePersenter<View>{
        void getVerify();
    }

}
