package com.forms.design.mvp.view;

/**
 * Created by forms on 2017/12/23.
 */

public interface LoginView {
    void showLoading();

    void hideLoading();

    void success(String msg);

    void failure(String msg);

    void clearEdit();
}
