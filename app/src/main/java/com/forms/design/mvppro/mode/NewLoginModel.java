package com.forms.design.mvppro.mode;

import android.os.Handler;
import android.os.SystemClock;

import com.forms.design.mvppro.entity.User;
import com.forms.design.mvppro.newmvp.LoginContract;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by forms on 2017/12/23.
 */

public class NewLoginModel implements LoginContract.Model {

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



    //结合RxJava使用
    @Override
    public Observable<String> rxLogin(final User user) {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                SystemClock.sleep(3000);
                String strName = user.getUserName();
                String strPwd = user.getPwd();
                if (strName.equals("jack") && strPwd.equals("123456")) {
                    subscriber.onNext("登录成功");
                    subscriber.onCompleted();
                } else {
                    subscriber.onNext("用户名或者密码不正确");
                    subscriber.onCompleted();
                }
            }
        });
    }
}
