package com.wzq.da.chuang.report.service.impl;

import com.wzq.da.chuang.model.pojos.report.MFile;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.report.MFileMapper;
import com.wzq.da.chuang.report.service.MFileService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class MFileServiceImpl implements MFileService{

    @Resource
    private MFileMapper mFileMapper;

    @Override
    public int insertSelective(MFile mFile) {
        return mFileMapper.insertSelective(mFile);
    }

    @Override
    public MFile selectByPrimaryKey(Long fileId) {
        return mFileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public int deleteByPrimaryKey(String fileId) {
        return mFileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public List<MFile> selectByReportId(Long reportId) {
        Example example = new Example(MFile.class);
        example.createCriteria().andEqualTo("reportId",reportId);
        return mFileMapper.selectByExample(example);
    }
}
