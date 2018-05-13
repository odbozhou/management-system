package com.management.dao;

import com.management.model.ExperimentReport;
import org.springframework.stereotype.Component;

@Component
public interface ExperimentReportDao {
    int deleteByPrimaryKey(Integer erid);

    int insert(ExperimentReport record);

    int insertSelective(ExperimentReport record);

    ExperimentReport selectByPrimaryKey(Integer erid);

    int updateByPrimaryKeySelective(ExperimentReport record);

    int updateByPrimaryKey(ExperimentReport record);
}