package com.forms.design.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.forms.design.mvp.entity.User;
import com.forms.design.mvp.mode.UserLoginListener;
import com.forms.design.mvp.mode.UserLoginMode;
import com.forms.design.mvp.mode.UserLoginModeImp;
import com.forms.design.mvp.view.LoginView;

/**
 * Created by forms on 2017/12/23.
 */

public class LoginPresenter {

    private LoginView loginView;
    private UserLoginMode loginMode;
    private Context mContext;

    public LoginPresenter(Context context, LoginView loginView) {
        this.loginView = loginView;
        this.mContext = context;
        loginMode = new UserLoginModeImp();
    }

    public void login(User user) {
        if (TextUtils.isEmpty(user.getUserName()) || TextUtils.isEmpty(user.getPwd())) {
            Toast.makeText(mContext, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            loginView.showLoading();
            loginMode.login(user, new UserLoginListener() {
                @Override
                public void loginSuccess(String msg) {
                    loginView.success(msg);
                    loginView.hideLoading();
                }

                @Override
                public void loginFailure(String msg) {
                    loginView.failure(msg);
                    loginView.hideLoading();
                }
            });
        }
    }

    public void clear() {
        loginView.clearEdit();
    }
}
