package com.wzq.da.chuang.model.mappers.report;

import com.wzq.da.chuang.model.dto.report.ReportSelectParam;
import com.wzq.da.chuang.model.pojos.report.MReport;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface MReportMapper extends MyMapper<MReport> {

    List<MReport> selectByThree(@Param("reportSelectParam") ReportSelectParam reportSelectParam);
}