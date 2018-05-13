package com.management.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiajia on 2018/5/9.
 */
public interface BaseDao<T> {
    int insert(T t);
    int delete(Serializable id);
    int update(T t);
    T get(Serializable id);

    List<T> getAll();
}
