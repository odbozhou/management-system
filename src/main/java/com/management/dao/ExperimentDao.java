package com.management.dao;

import com.management.model.Experiment;
import org.springframework.stereotype.Component;

@Component
public interface ExperimentDao {
    int deleteByPrimaryKey(Integer eid);

    int insert(Experiment record);

    int insertSelective(Experiment record);

    Experiment selectByPrimaryKey(Integer eid);

    int updateByPrimaryKeySelective(Experiment record);

    int updateByPrimaryKeyWithBLOBs(Experiment record);

    int updateByPrimaryKey(Experiment record);
}