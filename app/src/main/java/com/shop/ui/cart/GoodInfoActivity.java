package com.shop.ui.cart;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.R;
import com.shop.base.BaseActivity;
import com.shop.interfaces.cart.CartConstart;
import com.shop.models.bean.RelatedBean;
import com.shop.persenter.cart.CartPersenter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodInfoActivity extends BaseActivity<CartConstart.Persenter> implements CartConstart.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_des)
    TextView txtDes;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_nums)
    TextView txtNums;
    @BindView(R.id.layout_nums)
    ConstraintLayout layoutNums;
    @BindView(R.id.txt_param)
    TextView txtParam;
    @BindView(R.id.txt_metarial)
    TextView txtMetarial;
    @BindView(R.id.layout_metarial)
    ConstraintLayout layoutMetarial;
    @BindView(R.id.txt_size)
    TextView txtSize;
    @BindView(R.id.layout_size)
    ConstraintLayout layoutSize;
    @BindView(R.id.txt_color)
    TextView txtColor;
    @BindView(R.id.layout_color)
    ConstraintLayout layoutColor;
    @BindView(R.id.txt_norm)
    TextView txtNorm;
    @BindView(R.id.layout_norm)
    ConstraintLayout layoutNorm;
    @BindView(R.id.txt_place)
    TextView txtPlace;
    @BindView(R.id.myWebView)
    WebView webView;
    @BindView(R.id.txt_question)
    ConstraintLayout txtQuestion;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txt_collect)
    TextView txtCollect;
    @BindView(R.id.txt_cart)
    TextView txtCart;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.txt_addCart)
    TextView txtAddCart;

    @Override
    protected int getLayout() {
        return R.layout.activity_goodinfo;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void initData() {
        int relatedId = getIntent().getIntExtra("relatedId", 0);
        persenter.getRelatedData(relatedId);
    }

    @Override
    protected CartConstart.Persenter createPersenter() {
        return new CartPersenter();
    }

    @Override
    public void getRelatedDataReturn(RelatedBean result) {
        updateBanner(result.getData().getGallery());

        String price = getResources().getString(R.string.price_news_model).replace("$",String.valueOf(result.getData().getInfo().getRetail_price()));
        updatePrice(result.getData().getInfo().getName(),
                result.getData().getInfo().getGoods_brief(),price);

        updateWebView(result.getData().getInfo());
    }

    //填充banner数据
    private void updateBanner(List<RelatedBean.DataBeanX.GalleryBean> list){

    }

    //填充信息数据
    private void updatePrice(String name,String des,String price){
        txtTitle.setText(name);
        txtDes.setText(des);
        txtPrice.setText(price);
    }
    //填充规格数据
    private void updateParam(){
    }
    //商品介绍描述信息
    private void updateWebView(RelatedBean.DataBeanX.InfoBean infoBean){
        //用webview加载网页内容实现商品详情数据的展示
        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(infoBean.getGoods_desc()+"</body></html>");
        webView.loadData(sb.toString(),"text/html","utf-8");


        //通过解析数据内容，提取图片的地址，结合recyclerview加载对应的图片实现
       /* List<String> urlList = new ArrayList<>();
        String[] arr = infoBean.getGoods_desc().split("<p>");
        String head = "<img src=\"";
        String foot = ".jpg";
        for(int i=0; i<arr.length; i++){
            if(TextUtils.isEmpty(arr[i])) continue;
            int start = arr[i].indexOf(head)+head.length();
            if(start == -1) continue;
            int end = arr[i].indexOf(foot)+foot.length();
            String url = arr[i].substring(start,end);
            urlList.add(url);
            Log.i("url",url);
        }*/
    }

    //商品列表
    private void updateGoodList(){

    }


}
