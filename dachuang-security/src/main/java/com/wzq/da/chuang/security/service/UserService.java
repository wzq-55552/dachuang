package com.wzq.da.chuang.security.service;

import com.wzq.da.chuang.model.pojos.user.UserInformation;

public interface UserService{

    /**
     * 根据用户id查询用户
     * @param s 用户id
     * @return
     */
    UserInformation getByUserId(String s);

}
