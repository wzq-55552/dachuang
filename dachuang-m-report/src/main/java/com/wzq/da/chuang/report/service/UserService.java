package com.wzq.da.chuang.report.service;

import com.wzq.da.chuang.model.pojos.user.UserInformation;

import java.util.List;

public interface UserService {

    List<UserInformation> selectExpert();

    UserInformation selectByPrimaryKey(String userId);
}
