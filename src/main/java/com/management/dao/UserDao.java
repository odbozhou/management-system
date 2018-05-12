package com.management.dao;

import com.management.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loadUserByUsername(String userName);
}