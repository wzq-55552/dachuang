package com.wzq.da.chuang.report.service;

import com.wzq.da.chuang.model.dto.report.ReportSelectParam;
import com.wzq.da.chuang.model.pojos.report.MReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MReportService{

    int insertSelective (MReport mReport);

    MReport selectByProjectId(Long projectId);

    int updateByPrimaryKeySelective (MReport mReport);

    MReport selectByPrimaryKey(Long reportId);

    List<MReport> select(MReport mReport);

    List<MReport> selectByThree(ReportSelectParam reportSelectParam);
}
