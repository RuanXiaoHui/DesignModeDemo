package com.forms.design.mvppro.base;

/**
 * Created by forms on 2017/12/23.
 */

import android.content.Context;

/****
 * @param <V>  T代表View
 * @param <M>  E代表Model
 */
public abstract class BasePresenter<V,M> {

    public Context mContext;
    public V mView;
    public M mModel;

    public void setViewAndModel(V view,M model){
        this.mView=view;
        this.mModel=model;
    }
}
