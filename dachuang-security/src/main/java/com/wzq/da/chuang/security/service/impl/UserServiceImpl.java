package com.wzq.da.chuang.security.service.impl;

import com.wzq.da.chuang.model.pojos.user.UserInformation;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.user.UserInformationMapper;
import com.wzq.da.chuang.security.service.UserService;
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserInformationMapper userMapper;


    @Override
    public UserInformation getByUserId(String s) {
        return userMapper.selectByPrimaryKey(s);
    }

}
