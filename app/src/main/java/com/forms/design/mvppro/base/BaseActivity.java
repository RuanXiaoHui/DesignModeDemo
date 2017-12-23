package com.forms.design.mvppro.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.forms.design.mvppro.utils.MVPUtil;

/**
 * Created by forms on 2017/12/23.
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel>
        extends AppCompatActivity {

    public P mPresenter;
    public M mModel;
    public Context activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        activity = this;
        mPresenter = MVPUtil.getT(this, 0);
        mModel = MVPUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        initView();
        initListener();
        initPresenter();
    }

    public abstract int getLayoutID();

    public abstract void initView();

    public abstract void initListener();

    public abstract void initPresenter();

}
