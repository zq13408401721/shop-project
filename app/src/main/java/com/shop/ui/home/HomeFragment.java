package com.shop.ui.home;

import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.R;
import com.shop.base.BaseAdapter;
import com.shop.base.BaseFragment;
import com.shop.interfaces.home.HomeConstract;
import com.shop.models.bean.IndexBean;
import com.shop.persenter.home.HomePersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomeConstract.Persenter> implements HomeConstract.View, BaseAdapter.ItemClickHandler {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    BrandAdapter brandAdapter;
    List<IndexBean.DataBean.BrandListBean> list;



    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        brandAdapter = new BrandAdapter(list,context);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setAdapter(brandAdapter);
        brandAdapter.setOnItemClickHandler(this);
    }

    @Override
    protected void initData() {
        persenter.getHomeData();
    }

    @Override
    protected HomeConstract.Persenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void getHomeDataReturn(IndexBean result) {
        //刷新Brand列表数据
        brandAdapter.updata(result.getData().getBrandList());
    }

    /**
     * 接口回调的方法
     * @param position
     * @param holder
     */
    @Override
    public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
        IndexBean.DataBean.BrandListBean bean = list.get(position);
        ((TextView)holder.getView(R.id.txt_name)).setText(bean.getName()+"新的名字");
        Log.i("brand-click",String.valueOf(position));
    }
}