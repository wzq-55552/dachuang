package com.wzq.da.chuang.report.service;

import com.wzq.da.chuang.model.pojos.report.MFile;

import java.util.List;

public interface MFileService{

    int insertSelective (MFile mFile);

    MFile selectByPrimaryKey(Long fileId);

    int deleteByPrimaryKey(String fileId);

    List<MFile> selectByReportId(Long reportId);

}
