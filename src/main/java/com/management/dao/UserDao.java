package com.management.dao;

import com.management.common.BaseDao;
import com.management.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends BaseDao<User>{
    User loadUserByUsername(String userName);
}