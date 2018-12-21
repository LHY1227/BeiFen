package com.bawie.liu.beifen.core;

import com.bawie.liu.beifen.http.Result;

/**
 * @author dingtao
 * @date 2018/12/6 14:42
 * qq:1940870847
 */
public interface DataCall<T> {

    void success(T data);

    void fail(Result result);

}
