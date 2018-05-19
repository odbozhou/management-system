package com.management.service.impl;/**
 * Created by jiajia on 2018/5/13.
 */

import com.management.common.BaseDao;
import com.management.common.BaseServiceImpl;
import com.management.dao.TeacherDao;
import com.management.model.Teacher;
import com.management.model.User;
import com.management.service.TeacherService;
import com.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 教师
 * @date 2018/5/13 16:42
 */

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private UserService userService;

    @Override
    public BaseDao<Teacher> getDao() {
        return teacherDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Teacher teacher) {
        User userParam = new User();
        userParam.setLoginName(teacher.getPhone().toString());
        userParam.setPasswd("admin123");
        userParam.setRole("ROLE_TEACHER");
        User user = userService.createNewUser(userParam);
        logger.info("user id = {}", user.getUserId());
        teacher.setCreateTime(new Date());
        teacher.setUpdateTime(new Date());

        teacher.setUserId(user.getUserId());


        logger.info("teacher user id = {}", teacher.getUserId());

        save(teacher);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int remove(Integer id) {
        Teacher teacher = get(id);
        if (null == teacher) {
            return 0;
        }
        delete(id);
        return userService.delete(teacher.getUserId());
    }

    @Override
    public void renew(Teacher teacher) {
        Teacher oldTeacher = get(teacher.getTid());
        if (null != oldTeacher) {
            User user = userService.get(oldTeacher.getUserId());
            if (!user.getLoginName().equals(teacher.getPhone())) {
                user.setLoginName(teacher.getPhone());
                user.setUserName(teacher.getPhone());
                user.setUpdateTime(new Date());
                userService.update(user);
            }
        }
        update(teacher);
    }

    @Override
    public Teacher getByPhone(String phone) {
        return teacherDao.getByPhone(phone);
    }
}
