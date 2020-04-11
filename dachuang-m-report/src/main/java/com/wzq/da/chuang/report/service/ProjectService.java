package com.wzq.da.chuang.report.service;

import com.wzq.da.chuang.model.pojos.report.MReport;
import com.wzq.da.chuang.model.pojos.report.Project;

import java.util.List;

public interface ProjectService{

    List<Project> selectSelfProject(String userId);

    Project selectByPrimaryKey(Long projectId);

    int updateByPrimaryKeySelective(Project project);

    List<Project> selectByCollegeId(Long collegeId);

    List<Project> select(Project project);

}
