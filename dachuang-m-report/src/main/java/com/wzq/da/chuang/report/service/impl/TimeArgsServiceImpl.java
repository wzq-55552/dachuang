package com.wzq.da.chuang.report.service.impl;

import com.wzq.da.chuang.model.pojos.report.TimeArgs;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.report.TimeArgsMapper;
import com.wzq.da.chuang.report.service.TimeArgsService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TimeArgsServiceImpl implements TimeArgsService{

    @Resource
    private TimeArgsMapper timeArgsMapper;

    @Override
    public TimeArgs select() {
        return timeArgsMapper.selectByPrimaryKey(1);
    }
}
