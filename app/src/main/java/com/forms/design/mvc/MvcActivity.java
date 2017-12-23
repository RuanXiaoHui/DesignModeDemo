package com.forms.design.mvc;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.forms.design.R;
import com.forms.design.mvc.entity.User;
import com.forms.design.mvc.mode.UserLoginListener;
import com.forms.design.mvc.mode.UserLoginModeImp;

public class MvcActivity extends AppCompatActivity implements View.OnClickListener {

    private UserLoginModeImp userLoginModeImp;
    private EditText etUserName;
    private EditText etPwd;
    private AppCompatButton btnLogin;
    private AppCompatButton btnReset;
    private String userName;
    private String pwd;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        initView();
        initListener();
    }

    private void initView() {
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);
        btnReset = (AppCompatButton) findViewById(R.id.btnReset);
        mDialog=new ProgressDialog(this);
        mDialog.setCancelable(false);
        mDialog.setMessage("正在登陆中...");
    }

    private void initListener() {
        userLoginModeImp = new UserLoginModeImp();
        btnLogin.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:
                mDialog.show();
                userName=etUserName.getText().toString();
                pwd=etPwd.getText().toString();
                userLoginModeImp.login(new User(userName,pwd),new UserLoginEvent());
                break;
            case R.id.btnReset:
                etUserName.setText("");
                etPwd.setText("");
                break;
        }
    }

    public  class UserLoginEvent implements UserLoginListener{

        @Override
        public void loginSuccess(String str) {
            if (mDialog.isShowing()){
                mDialog.dismiss();
            }
            Toast.makeText(MvcActivity.this, str, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void loginFailure(String str) {
            if (mDialog.isShowing()){
                mDialog.dismiss();
            }
            Toast.makeText(MvcActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    }
}
