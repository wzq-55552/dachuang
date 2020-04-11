package com.wzq.da.chuang.security.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.user.IdentityMapper;
import com.wzq.da.chuang.security.service.IdentityService;
@Service
public class IdentityServiceImpl implements IdentityService{

    @Resource
    private IdentityMapper identityMapper;

}
