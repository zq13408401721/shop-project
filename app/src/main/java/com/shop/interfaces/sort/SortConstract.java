package com.shop.interfaces.sort;

import com.shop.interfaces.IBasePersenter;
import com.shop.interfaces.IBaseView;
import com.shop.models.bean.SortBean;
import com.shop.models.bean.SortDetailGoodsBean;
import com.shop.models.bean.SortDetailTabBean;
import com.shop.models.bean.SortGoodsBean;

public interface SortConstract {

    interface View extends IBaseView{

        void getSortReturn(SortBean result);

        void getCurrentSortDataReturn(SortGoodsBean result);

    }

    interface Persenter extends IBasePersenter<View>{

        void getSort();

        void getCurrentSortData(int id);

    }


    interface DetailView extends IBaseView{

        void getSortDetailTabReturn(SortDetailTabBean result);

        void getSortDetailGoodsReturn(SortDetailGoodsBean result);

    }


    interface DetailPersenter extends IBasePersenter<DetailView>{

        //获取分类详情页的导航列表数据
        void getSortDetailTab(int id);

        //获取分类详情页当前的商品列表数据
        void getSortDetailGoods(int id,int page,int size);
    }



}
