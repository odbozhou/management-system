package com.management.service.impl;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.common.BaseDao;
import com.management.common.BaseServiceImpl;
import com.management.dao.ExperimentDao;
import com.management.model.Experiment;
import com.management.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 实验业务实现
 * @date 2018/5/9 23:29
 */
@Service
public class ExperimentServiceImpl extends BaseServiceImpl<Experiment> implements ExperimentService {
    @Autowired
    private ExperimentDao experimentDao;

    public List<Experiment> list() {
        return experimentDao.getAll();
    }

    @Override
    public BaseDao<Experiment> getDao() {
        return experimentDao;
    }

    @Override
    public List<Experiment> getAll() {
        return experimentDao.getAll();
    }
}
