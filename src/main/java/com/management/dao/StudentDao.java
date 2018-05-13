package com.management.dao;

import com.management.model.Student;
import org.springframework.stereotype.Component;

@Component
public interface StudentDao {
    int deleteByPrimaryKey(Integer sid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}