package com.management.dao;

import com.management.common.BaseDao;
import com.management.model.Experiment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExperimentDao extends BaseDao<Experiment> {
    List<Experiment> listByTid(Integer tid);

    List<Experiment> listByGid(Integer gid);
}