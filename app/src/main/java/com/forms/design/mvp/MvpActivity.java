package com.forms.design.mvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.forms.design.R;
import com.forms.design.mvp.entity.User;
import com.forms.design.mvp.presenter.LoginPresenter;
import com.forms.design.mvp.view.LoginView;

public class MvpActivity extends AppCompatActivity implements View.OnClickListener,
        LoginView {

    private EditText etUserName;
    private EditText etPwd;
    private AppCompatButton btnLogin;
    private AppCompatButton btnReset;
    private ProgressDialog progressDialog;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        initView();
        initListener();
    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);
        btnReset = (AppCompatButton) findViewById(R.id.btnReset);
        progressDialog = new ProgressDialog(this);
        loginPresenter = new LoginPresenter(this, this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在登录中...");
    }

    private void initListener() {
        btnLogin.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                loginPresenter.login(new User(etUserName.getText().toString(),
                        etPwd.getText().toString()));
                break;
            case R.id.btnReset:
                loginPresenter.clear();
                break;
        }
    }

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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearEdit() {
        etUserName.setText("");
        etPwd.setText("");
    }
}
