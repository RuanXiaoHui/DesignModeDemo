package com.forms.design.mvp.mode;

/**
 * Created by forms on 2017/12/23.
 */

public interface UserLoginListener {

    void loginSuccess(String msg);

    void loginFailure(String msg);
}
