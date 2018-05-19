package com.management.service;

import com.management.common.BaseService;
import com.management.model.Teacher;

/**
 * Created by jiajia on 2018/5/13.
 */
public interface TeacherService extends BaseService<Teacher> {

    void add(Teacher teacher);

    int remove(Integer id);

    void renew(Teacher teacher);

    Teacher getByPhone(String userName);
}
