package com.management.dao;

import com.management.common.BaseDao;
import com.management.model.ExperimentReport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExperimentReportDao extends BaseDao<ExperimentReport> {
    List<ExperimentReport> listByEids(List<Integer> eids);

    List<ExperimentReport> listBySid(Integer sid);
}