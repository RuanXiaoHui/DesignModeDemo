package com.forms.design.mvc.mode;

import com.forms.design.mvc.entity.User;

/**
 * Created by forms on 2017/12/22.
 */

public interface UserLoginMode {
    void login(User user, UserLoginListener listener);
}
