package com.shop.interfaces.home;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.BrandBean;
import com.shop.models.bean.BrandGoodsBean;

public interface BrandConstract {

    interface View extends IBaseView{

        //获取品牌详情页数据返回
        void getBrandInfoReturn(BrandBean result);

        //获取品牌详情页的商品列表返回
        void getBrandGoodsReturn(BrandGoodsBean result);
    }

    interface Persenter extends IBasePersenter<View>{

        //获取品牌详情页数据
        void getBrandInfo(String id);

        //获取品牌详情页的商品列表数据
        void getBrandGoods(String brandId,int page,int size);


    }

}
