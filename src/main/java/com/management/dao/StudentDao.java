package com.management.dao;

import com.management.common.BaseDao;
import com.management.model.Student;
import org.springframework.stereotype.Component;

@Component
public interface StudentDao extends BaseDao<Student> {
    Student getBySno(String sno);
}