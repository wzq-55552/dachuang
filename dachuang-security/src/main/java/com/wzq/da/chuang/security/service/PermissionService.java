package com.wzq.da.chuang.security.service;

import com.wzq.da.chuang.model.pojos.user.PRange;
import com.wzq.da.chuang.model.pojos.user.Permission;

import java.util.List;

public interface PermissionService{

    /**
     * 通过关联集合获取到对应的权限集合，可拿到权限url、权限名
     * @param pRanges 关联集合
     * @return
     */
    List<Permission> selectByList(List<PRange> pRanges);


}
