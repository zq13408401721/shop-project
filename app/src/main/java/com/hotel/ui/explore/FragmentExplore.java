package com.hotel.ui.explore;

import android.util.Log;
import android.widget.Toast;

import com.hotel.R;
import com.hotel.base.BaseFragment;
import com.hotel.interfaces.explore.ExploreConstract;
import com.hotel.models.bean.ExploreBean;
import com.hotel.models.bean.ExploreBeanProxy;
import com.hotel.persenter.explore.ExplorePersenter;
import com.hotel.widget.MyTab;

import java.util.Map;

import butterknife.BindView;

public class FragmentExplore extends BaseFragment<ExploreConstract.Persenter> implements ExploreConstract.View, MyTab.TabClick {

    @BindView(R.id.myTab)
    MyTab myTab;

    @Override
    protected int getLayout() {
        return R.layout.fragment_explore;
    }

    @Override
    protected void initView() {
        //设置myTab切换监听
        myTab.initVeiw();
        myTab.addTabClickListener(this);
    }

    @Override
    protected void initData() {
        Map<String, String> map = ExploreBeanProxy.getExploreParams();
        persenter.getExplore(map);
    }

    @Override
    protected ExploreConstract.Persenter createPersenter() {
        return new ExplorePersenter();
    }

    @Override
    public void getExploreReturn(ExploreBean result) {
        Log.i("result", result.getMetadata().getCurrent_tab_id());
    }

    @Override
    public void tabClickFun(int pos) {
        if (pos == 0) {
            Toast.makeText(context, "点击的是左边", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "点击的是右边", Toast.LENGTH_SHORT).show();
        }
    }
}
