package com.wzq.da.chuang.security.service.impl;

import com.wzq.da.chuang.model.pojos.user.PRange;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.user.PRangeMapper;
import com.wzq.da.chuang.security.service.PRangeService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class PRangeServiceImpl implements PRangeService{

    @Resource
    private PRangeMapper pRangeMapper;

    @Override
    public List<PRange> selectByIdentityId(String identityId) {
        Example example = new Example(PRange.class);
        example.createCriteria().andEqualTo("identityId",identityId);
        return pRangeMapper.selectByExample(example);
    }
}
