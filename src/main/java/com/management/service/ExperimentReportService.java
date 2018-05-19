package com.management.service;/**
 * Created by jiajia on 2018/5/16.
 */

import com.management.common.BaseService;
import com.management.model.ExperimentReport;

import java.util.List;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 实验报告
 * @date 2018/5/16 22:54
 */
public interface ExperimentReportService extends BaseService<ExperimentReport> {
    List<ExperimentReport> listByEids(List<Integer> eids);

    List<ExperimentReport> listBySid(Integer sid);
}
