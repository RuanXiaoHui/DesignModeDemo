package com.forms.design.mvc.mode;

import android.os.Handler;
import android.text.TextUtils;

import com.forms.design.mvc.entity.User;

/**
 * Created by forms on 2017/12/22.
 */

public class UserLoginModeImp implements UserLoginMode {
    @Override
    public void login(final User user, final UserLoginListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(user.getUserName()) || TextUtils.isEmpty(user.getPwd())) {
                    listener.loginFailure("用户名以及密码不能为空");
                } else {
                    if (!user.getUserName().equals("jack") || !user.getPwd().equals("123456")) {
                        listener.loginFailure("用户名密码不匹配");
                    } else {
                        listener.loginSuccess("登录成功");
                    }
                }
            }
        }, 3000);
    }
}
