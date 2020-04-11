package com.wzq.da.chuang.security.service.impl;

import com.wzq.da.chuang.model.pojos.user.PRange;
import com.wzq.da.chuang.model.pojos.user.Permission;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.user.PermissionMapper;
import com.wzq.da.chuang.security.service.PermissionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService{

    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> selectByList(List<PRange> pRanges) {
        List<Permission> permissions = new ArrayList<>();
        if (pRanges!=null && pRanges.size()!=0){
            pRanges.forEach(pRange -> {
                Permission permission = permissionMapper.selectByPrimaryKey(pRange.getPId());
                if (permission!=null){
                    permissions.add(permission);
                }
            });
            return permissions;
        }
        return null;
    }

}
