package com.shop.ui.home;

import android.content.Intent;
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
import com.shop.ui.home.activity.BrandActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomeConstract.Persenter> implements HomeConstract.View, BaseAdapter.ItemClickHandler {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView_News)
    RecyclerView recyclerViewNews;

    BrandAdapter brandAdapter;
    List<IndexBean.DataBean.BrandListBean> list;

    NewsAdapter newsAdapter;
    List<IndexBean.DataBean.NewGoodsListBean> newsList;


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

        //初始化新品列表
        newsList = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsList,context);
        recyclerViewNews.setLayoutManager(new GridLayoutManager(context,2));
        recyclerViewNews.setAdapter(newsAdapter);
        //避免当前类中多个列表的点击接口回调的冲突，建议使用匿名的类实例
        newsAdapter.setOnItemClickHandler(new BaseAdapter.ItemClickHandler() {
            @Override
            public void itemClick(int position, BaseAdapter.BaseViewHolder holder) {
                Log.i("newsItemClick",String.valueOf(position));
            }
        });

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
        //刷新新品发布列表数据
        newsAdapter.updata(result.getData().getNewGoodsList());
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
        //跳转到brand详情页
        Intent intent = new Intent(getContext(), BrandActivity.class);
        intent.putExtra("brandId",bean.getId());
        startActivity(intent);
    }
}