package com.management.service.impl;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.dao.CourseDao;
import com.management.model.Course;
import com.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 课程业务实现
 * @date 2018/5/9 23:29
 */
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseDao courseDao;

    public List<Course> list() {
        return courseDao.getAll();
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }
}
