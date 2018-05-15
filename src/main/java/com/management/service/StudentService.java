package com.management.service;

import com.management.common.BaseService;
import com.management.model.Student;

/**
 * Created by jiajia on 2018/5/13.
 */
public interface StudentService extends BaseService<Student> {

    void add(Student student);

    int remove(Integer id);

    void renew(Student student);
}
