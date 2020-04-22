package com.hotel.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hotel.R;
import com.hotel.base.BaseActivity;
import com.hotel.interfaces.login.RegisterConstract;
import com.hotel.persenter.login.RegisterPersenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterConstract.Persenter> {
    @BindView(R.id.edit_username)
    EditText editUsername;


    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        persenter.getVerify();
    }

    @Override
    protected RegisterConstract.Persenter createPersenter() {
        return new RegisterPersenter();
    }


}
