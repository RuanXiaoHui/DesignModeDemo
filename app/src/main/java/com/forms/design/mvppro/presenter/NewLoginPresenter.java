package com.forms.design.mvppro.presenter;

import android.text.TextUtils;
import com.forms.design.mvppro.entity.User;
import com.forms.design.mvppro.mode.UserLoginListener;
import com.forms.design.mvppro.newmvp.LoginContract;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by forms on 2017/12/23.
 */

public class NewLoginPresenter extends LoginContract.Presenter {
    @Override
    public void login(User user) {
        if (TextUtils.isEmpty(user.getUserName()) || TextUtils.isEmpty(user.getPwd())) {
            mView.failure("用户名密码不能为空");
        }else{
            mView.showLoading();
            mModel.login(user, new UserLoginListener() {
                @Override
                public void loginSuccess(String msg) {
                    mView.hideLoading();
                    mView.success(msg);
                }

                @Override
                public void loginFailure(String msg) {
                    mView.hideLoading();
                    mView.failure(msg);
                }
            });
        }
    }


    //结合RxJava使用
    @Override
    public void rxLogin(User user) {
        if (TextUtils.isEmpty(user.getUserName()) || TextUtils.isEmpty(user.getPwd())) {
            mView.failure("用户名密码不能为空");
        }else{
            mView.showLoading();
            mModel.rxLogin(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onCompleted() {
                            mView.hideLoading();
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.hideLoading();
                        }

                        @Override
                        public void onNext(String s) {
                            mView.hideLoading();
                            mView.failure(s);
                        }
                    });
        }
    }


    @Override
    public void clear() {
        mView.clearEdit();
    }
}
