package com.bawie.liu.beifen.presenter;


import com.bawie.liu.beifen.core.DataCall;
import com.bawie.liu.beifen.http.Result;
import com.bawie.liu.beifen.modle.CartModel;

/**
 * @author dingtao
 * @date 2018/12/6 14:41
 * qq:1940870847
 */
public class CartPresenter extends BasePresenter {


    public CartPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Result getData(Object... args) {
        Result result = CartModel.goodsList();//调用网络请求获取数据
        return result;
    }

}
