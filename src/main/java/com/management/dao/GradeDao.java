package com.management.dao;

import com.management.model.Grade;
import org.springframework.stereotype.Component;

@Component
public interface GradeDao {
    int deleteByPrimaryKey(Integer gid);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer gid);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
}