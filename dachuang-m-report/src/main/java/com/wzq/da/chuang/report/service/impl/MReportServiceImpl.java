package com.wzq.da.chuang.report.service.impl;

import com.wzq.da.chuang.model.dto.report.ReportSelectParam;
import com.wzq.da.chuang.model.pojos.report.MReport;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.report.MReportMapper;
import com.wzq.da.chuang.report.service.MReportService;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MReportServiceImpl implements MReportService{

    @Resource
    private MReportMapper mReportMapper;

    @Override
    public int insertSelective(MReport mReport) {
        mReport.setCApproval(0);
        mReport.setEApproval(0);
        mReport.setSApproval(0);
        mReport.setTApproval(0);
        return mReportMapper.insertSelective(mReport);
    }

    @Override
    public MReport selectByProjectId(Long projectId) {
        Example example = new Example(MReport.class);
        example.createCriteria().andEqualTo("projectId",projectId);
        return mReportMapper.selectOneByExample(example);
    }

    @Override
    public int updateByPrimaryKeySelective(MReport mReport) {
        return mReportMapper.updateByPrimaryKeySelective(mReport);
    }

    @Override
    public MReport selectByPrimaryKey(Long reportId) {
        return mReportMapper.selectByPrimaryKey(reportId);
    }

    @Override
    public List<MReport> select(MReport mReport) {
        Example example = new Example(MReport.class);
        Example.Criteria criteria = example.createCriteria();
        if (mReport.getReportId()!=null){
            criteria.andEqualTo("ReportId",mReport.getReportId());
        }
        if (mReport.getProjectId()!=null){
            criteria.andEqualTo("ProjectId",mReport.getProjectId());
        }
        if (!StringUtils.isEmpty(mReport.getUserId())){
            criteria.andLike("UserId",mReport.getUserId());
        }
        if (!StringUtils.isEmpty(mReport.getExpert())){
            criteria.andLike("expert",mReport.getExpert());
        }
        return mReportMapper.selectByExample(example);
    }

    @Override
    public List<MReport> selectByThree(ReportSelectParam reportSelectParam) {
        return mReportMapper.selectByThree(reportSelectParam);
    }
}
