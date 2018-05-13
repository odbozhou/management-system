package com.management.common;/**
 * Created by jiajia on 2018/5/9.
 */

import java.io.Serializable;

/**
 * @author jiajia
 * @version V1.0
 * @Description: baseService
 * @date 2018/5/9 23:09
 */
public interface BaseService<T> {
    T save(T t);

    int delete(Serializable id);

    int update(T t);

    T get(Serializable id);
}
