package com.forms.design.mvp.mode;

import com.forms.design.mvp.entity.User;

/**
 * Created by forms on 2017/12/23.
 */

public interface UserLoginMode {

    void login(User user, UserLoginListener listener);
}
