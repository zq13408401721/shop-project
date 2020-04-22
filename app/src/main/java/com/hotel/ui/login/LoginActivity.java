package com.hotel.ui.login;

import android.widget.Button;
import android.widget.EditText;

import com.hotel.R;
import com.hotel.base.BaseActivity;
import com.hotel.interfaces.login.LoginConstract;
import com.hotel.models.bean.UserBean;
import com.hotel.persenter.login.LoginPersenter;
import com.hotel.utils.SpUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginConstract.Persenter> implements LoginConstract.View {
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected LoginConstract.Persenter createPersenter() {
        return new LoginPersenter();
    }

    @Override
    public void loginReturn(UserBean result) {
        //登录成功以后存入sp
        SpUtils.getInstance().setValue("token",result.getData().getToken());
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String nickname = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        persenter.login(nickname,password);
    }
}
