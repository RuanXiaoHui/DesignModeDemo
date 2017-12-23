package com.forms.design.mvp.mode;

import android.os.Handler;

import com.forms.design.mvp.entity.User;

/**
 * Created by forms on 2017/12/23.
 */

public class UserLoginModeImp implements UserLoginMode {
    @Override
    public void login(final User user, final UserLoginListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String strName = user.getUserName();
                String strPwd = user.getPwd();
                if (strName.equals("jack") && strPwd.equals("123456")) {
                    listener.loginSuccess("登录成功");
                } else {
                    listener.loginFailure("用户名或者密码不正确");
                }
            }
        }, 3000);
    }
}
