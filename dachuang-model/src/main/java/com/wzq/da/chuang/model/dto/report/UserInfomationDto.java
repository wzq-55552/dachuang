package com.wzq.da.chuang.model.dto.report;

import com.wzq.da.chuang.model.pojos.user.College;
import com.wzq.da.chuang.model.pojos.user.UserInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "用户信息dto",value = "用户信息dto")
public class UserInfomationDto implements Serializable {

    private static final long serialVersionUID = -7634588807435208808L;

    @ApiModelProperty(value = "用户",required = true)
    private UserInformation user;

    @ApiModelProperty(value = "学院",required = true)
    private College college;

}
