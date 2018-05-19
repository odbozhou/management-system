package com.management.service.impl;/**
 * Created by jiajia on 2018/5/13.
 */

import com.management.common.BaseDao;
import com.management.common.BaseServiceImpl;
import com.management.dao.StudentDao;
import com.management.model.Student;
import com.management.model.User;
import com.management.service.StudentService;
import com.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 学生
 * @date 2018/5/13 16:42
 */

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private UserService userService;

    @Override
    public BaseDao<Student> getDao() {
        return studentDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Student student) {
        User userParam = new User();
        if (!StringUtils.isEmpty(student.getSno())) {
            userParam.setLoginName(student.getSno());
            userParam.setPasswd("123456");
            userParam.setRole("ROLE_STUDENT");
            User user = userService.createNewUser(userParam);
            logger.info("user id = {}", user.getUserId());
            student.setCreateTime(new Date());
            student.setUpdateTime(new Date());
            student.setUserId(user.getUserId());
            logger.info("student user id = {}", student.getUserId());
            save(student);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int remove(Integer id) {
        Student student = get(id);
        if (null == student) {
            return 0;
        }
        delete(id);
        return userService.delete(student.getUserId());
    }

    @Override
    public void renew(Student student) {
        Student oldStudent = get(student.getSid());
        if (null != oldStudent) {
            User user = userService.get(oldStudent.getUserId());
            if (!user.getLoginName().equals(student.getPhone())) {
                user.setLoginName(student.getPhone());
                user.setUserName(student.getPhone());
                user.setUpdateTime(new Date());
                userService.update(user);
            }
        }
        update(student);
    }

    @Override
    public Student getBySno(String userName) {
        return studentDao.getBySno(userName);
    }
}
