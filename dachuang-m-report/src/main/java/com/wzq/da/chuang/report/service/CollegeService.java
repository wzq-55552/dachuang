package com.wzq.da.chuang.report.service;

import com.wzq.da.chuang.model.pojos.user.College;

import java.util.List;

public interface CollegeService{

    List<College> selectAll();

    College selectByPrimaryKey(Long id);

}
