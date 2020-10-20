package com.wzq.da.chuang.report.controller;

import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.model.dto.report.ReportInsertParam;
import com.wzq.da.chuang.model.dto.report.UserInfomationDto;
import com.wzq.da.chuang.model.pojos.report.Project;
import com.wzq.da.chuang.model.pojos.user.College;
import com.wzq.da.chuang.model.pojos.user.UserInformation;
import com.wzq.da.chuang.report.service.CollegeService;
import com.wzq.da.chuang.report.service.ProjectService;
import com.wzq.da.chuang.report.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "大创项目controller")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @Resource
    private UserService userService;

    @Resource
    private CollegeService collegeService;

    /**
     * 查看自己对应的项目
     * @param userId
     * @return
     */
    @GetMapping("/project/select/{userId}")
    @ApiOperation(value = "查看自己对应的项目，不同角色看到的不同，userId在路径上")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<List<Project>> selectByUserId(@PathVariable("userId") String userId){
        if (!StringUtils.isEmpty(userId)){
            List<Project> projects = projectService.selectSelfProject(userId);
            return new ResponseResult<List<Project>>(ResponseResult.CodeStatus.OK,"获取项目信息成功",projects);
        }
        return new ResponseResult<List<Project>>(ResponseResult.CodeStatus.FAIL,"参数为空",null);
    }

    /**
     * 用户查看自己信息
     * @param userId
     * @return
     */
    @GetMapping("/user/select/{userId}")
    @ApiOperation(value = "用户查看自己信息")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<UserInfomationDto> selectUser(@PathVariable("userId") String userId){
        if (!StringUtils.isEmpty(userId)){
            UserInformation userInformation = userService.selectByPrimaryKey(userId);
            if (userInformation == null){
                return new ResponseResult<UserInfomationDto>(ResponseResult.CodeStatus.FAIL,"用户不存在",null);
            }

            UserInfomationDto userInfomationDto = new UserInfomationDto();
            userInfomationDto.setUser(userInformation);

            College college = collegeService.selectByPrimaryKey(userInformation.getCollegeId());

            userInfomationDto.setCollege(college);

            return new ResponseResult<UserInfomationDto>(ResponseResult.CodeStatus.OK,"获取信息成功",userInfomationDto);
        }
        return new ResponseResult<UserInfomationDto>(ResponseResult.CodeStatus.FAIL,"参数为空",null);
    }

}
