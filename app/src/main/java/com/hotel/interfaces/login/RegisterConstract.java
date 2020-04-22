package com.hotel.interfaces.login;

import com.hotel.interfaces.IBasePersenter;
import com.hotel.interfaces.IBaseView;

public interface RegisterConstract {

    interface View extends IBaseView{

    }

    interface Persenter extends IBasePersenter<View>{
        void getVerify();
    }

}
