package com.forms.design.mvppro.newmvp;

import com.forms.design.mvppro.base.BaseModel;
import com.forms.design.mvppro.base.BasePresenter;
import com.forms.design.mvppro.base.BaseView;
import com.forms.design.mvppro.entity.User;
import com.forms.design.mvppro.mode.UserLoginListener;

import rx.Observable;

/**
 * Created by forms on 2017/12/23.
 */

public class LoginContract {

    public interface Model extends BaseModel {

        void login(User user, UserLoginListener listener);

        //结合RxJava使用
        Observable<String> rxLogin(User user);

    }

    public interface View extends BaseView {

        void success(String msg);

        void failure(String msg);

        void clearEdit();
    }

    public abstract static class Presenter extends BasePresenter<View, Model> {

        public abstract void login(User user);

        //结合RxJava使用
        public abstract  void rxLogin(User user);

        public abstract void clear();
    }
}
