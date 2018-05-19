package com.management.service.impl;/**
 * Created by jiajia on 2018/5/9.
 */

import com.management.common.BaseDao;
import com.management.common.BaseServiceImpl;
import com.management.dao.ExperimentReportDao;
import com.management.model.ExperimentReport;
import com.management.service.ExperimentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 实验报告业务实现
 * @date 2018/5/9 23:29
 */
@Service
public class ExperimentReportServiceImpl extends BaseServiceImpl<ExperimentReport> implements ExperimentReportService {
    @Autowired
    private ExperimentReportDao experimentReportDao;

    public List<ExperimentReport> list() {
        return experimentReportDao.getAll();
    }

    @Override
    public BaseDao<ExperimentReport> getDao() {
        return experimentReportDao;
    }

    @Override
    public List<ExperimentReport> getAll() {
        return experimentReportDao.getAll();
    }

    @Override
    public List<ExperimentReport> listByEids(List<Integer> eids) {
        return experimentReportDao.listByEids(eids);
    }

    @Override
    public List<ExperimentReport> listBySid(Integer sid) {
        return experimentReportDao.listBySid(sid);
    }
}
