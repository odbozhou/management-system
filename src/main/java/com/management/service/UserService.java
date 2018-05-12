package com.management.service;/**
 * Created by jiajia on 2018/5/12.
 */

import com.management.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 用户服务
 * @date 2018/5/12 11:07
 */
public interface UserService extends UserDetailsService{
    User save(User user);

    void signin(User user);
}
