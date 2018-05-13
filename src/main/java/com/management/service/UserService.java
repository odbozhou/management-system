package com.management.service;/**
 * Created by jiajia on 2018/5/12.
 */

import com.management.common.BaseService;
import com.management.model.User;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 用户服务
 * @date 2018/5/12 11:07
 */
public interface UserService extends BaseService<User>{
    void signin(User user);

    User createNewUser(User userParam);
}
