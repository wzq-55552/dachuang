package com.wzq.da.chuang.report.service.impl;

import com.wzq.da.chuang.model.pojos.user.College;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.user.CollegeMapper;
import com.wzq.da.chuang.report.service.CollegeService;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService{

    @Resource
    private CollegeMapper collegeMapper;

    @Override
    public List<College> selectAll() {
        return collegeMapper.selectAll();
    }

    @Override
    public College selectByPrimaryKey(Long id) {
        return collegeMapper.selectByPrimaryKey(id);
    }
}
