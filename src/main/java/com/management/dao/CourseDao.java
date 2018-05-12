package com.management.dao;

import com.management.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseDao {
    int deleteByPrimaryKey(Integer cid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> getAll();
}