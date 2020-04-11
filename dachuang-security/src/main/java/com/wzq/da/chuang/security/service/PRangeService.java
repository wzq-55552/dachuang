package com.wzq.da.chuang.security.service;

import com.wzq.da.chuang.model.pojos.user.PRange;

import java.util.List;

public interface PRangeService{


    /**
     * 通过identityId查询权限集
     * @param identityId 角色id
     * @return
     */
    List<PRange> selectByIdentityId(String identityId);

}
