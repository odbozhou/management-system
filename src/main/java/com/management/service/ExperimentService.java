package com.management.service;/**
 * Created by jiajia on 2018/5/16.
 */

import com.management.common.BaseService;
import com.management.model.Experiment;

import java.util.List;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 实验
 * @date 2018/5/16 22:54
 */
public interface ExperimentService extends BaseService<Experiment> {
    List<Experiment> listByTid(Integer tid);

    List<Experiment> listByGid(Integer gid);
}
