package com.management.service.impl;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.common.BaseDao;
import com.management.common.BaseServiceImpl;
import com.management.dao.GradeDao;
import com.management.model.Grade;
import com.management.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 班级业务实现
 * @date 2018/5/9 23:29
 */
@Service
public class GradeServiceImpl extends BaseServiceImpl<Grade> implements GradeService {
    @Autowired
    private GradeDao gradeDao;

    public List<Grade> list() {
        return gradeDao.getAll();
    }

    @Override
    public BaseDao<Grade> getDao() {
        return gradeDao;
    }

    @Override
    public List<Grade> getAll() {
        return gradeDao.getAll();
    }
}
