package com.forms.design.mvppro.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by forms on 2017/12/23.
 */

public class MVPUtil {

    public static <T> T getT(Object o,int i){

        try {
            return ((Class<T>) ((ParameterizedType)(o.getClass().getGenericSuperclass())).
                    getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
