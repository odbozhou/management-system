package com.management.common;

import java.io.Serializable;

/**
 * Created by jiajia on 2018/5/9.
 */
public interface BaseDao<T> {
    int insert(T t);
    int delete(Serializable id);
    int update(T t);
    T get(Serializable id);
}
