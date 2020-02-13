package com.shop.interfaces.test;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.ChaptersBean;

public interface TestConstract {

    interface View extends IBaseView{
        void getChaptersReturn(ChaptersBean result);
    }

    interface Persenter extends IBasePersenter<View>{
        void getChapters();
    }

}
