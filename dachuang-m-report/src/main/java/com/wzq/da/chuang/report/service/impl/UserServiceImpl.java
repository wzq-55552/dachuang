package com.wzq.da.chuang.report.service.impl;

import com.wzq.da.chuang.model.mappers.user.UserInformationMapper;
import com.wzq.da.chuang.model.pojos.user.UserInformation;
import com.wzq.da.chuang.report.service.UserService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserInformationMapper userMapper;

    @Override
    public List<UserInformation> selectExpert() {
        Example example = new Example(UserInformation.class);
        example.createCriteria().andEqualTo("identityId","5");
        return userMapper.selectByExample(example);
    }

    @Override
    public UserInformation selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

}
