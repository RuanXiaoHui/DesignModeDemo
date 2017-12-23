package com.forms.design.mvppro.newmvp;

import android.app.ProgressDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.forms.design.R;
import com.forms.design.mvppro.base.BaseActivity;
import com.forms.design.mvppro.entity.User;
import com.forms.design.mvppro.mode.NewLoginModel;
import com.forms.design.mvppro.presenter.NewLoginPresenter;

/**
 * Created by forms on 2017/12/23.
 */

/*****
 * NewLoginPresenter继承BasePresenter
 * NewLoginModel继承BaseModel
 *
 * 1.首先创建BaseView接口
 * 2.随后创建BaseModel接口  显示UI，如弹对话框，显示吐司
 * 3.创建BasePresenter的抽象类，里面有泛型，需要V以及M，然后对外开个setViewAndModel方法
 * 4.创建BaseAct，需要制定P以及M的泛型，P以及M不能New出来，所以要通过对象获取。在工具类utils下面
 * 5.创建一个契约管理类，里面对MODE VIEW进行接口化包装，Model里面是类似于登录逻辑，View是用来显示UI，
 * Presenter使用抽象方法对其操作。所有的都必须继承BaseXXX
 * 6.定义三个类对契约类的接口以及抽象方法进行一一的进行实现以及继承
 * 7.最后：Act里面继承BaseAct，然后传入两个泛型，一个是Presenter，另外一个是MODE，都是从6中进行实现
 * 契约类的类，然后在在ACT的iniPresenter中将ACT实现的契约类中的VIEW和从BASE中获取到的MODEL进行传递到
 * BasePresenter中。然后当点击按钮的时候，调用presenter中的登录，随后在BasePresenter中获取传入的model
 * ，然后在presenter实现类中去实现model的登录，Model会将结果传递会监听函数，presenter拿到这个监听，
 * 就通过ACT传递过来的VIEW,进行更新UI了
 */
public class NewMvLoginActivity extends BaseActivity<NewLoginPresenter, NewLoginModel>
        implements LoginContract.View,View.OnClickListener {

    private EditText etUserName;
    private EditText etPwd;
    private AppCompatButton btnLogin;
    private AppCompatButton btnReset;
    private ProgressDialog progressDialog;

    @Override
    public int getLayoutID() {
        return R.layout.activity_mvp;
    }

    @Override
    public void initView() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);
        btnReset = (AppCompatButton) findViewById(R.id.btnReset);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在登录中...");
    }

    @Override
    public void initListener() {
        btnLogin.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void initPresenter() {
       //将View和Model传给P层
       mPresenter.setViewAndModel(this,mModel);
    }

    ///////////////////////View/////////////////////////////////////////
    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void success(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void clearEdit() {
        etPwd.setText("");
        etUserName.setText("");
    }
    ////////////////////////////////////////////////////////////////////

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                mPresenter.login(new User(etUserName.getText().toString(),
                        etPwd.getText().toString()));
                break;
            case R.id.btnReset:
                mPresenter.clear();
                break;
        }
    }
}
