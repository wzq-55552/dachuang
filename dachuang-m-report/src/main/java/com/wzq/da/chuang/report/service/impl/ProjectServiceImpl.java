package com.wzq.da.chuang.report.service.impl;

import com.wzq.da.chuang.model.mappers.report.MReportMapper;
import com.wzq.da.chuang.model.mappers.user.UserInformationMapper;
import com.wzq.da.chuang.model.pojos.report.MReport;
import com.wzq.da.chuang.model.pojos.report.Project;
import com.wzq.da.chuang.model.pojos.user.UserInformation;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.wzq.da.chuang.model.mappers.report.ProjectMapper;
import com.wzq.da.chuang.report.service.ProjectService;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private UserInformationMapper userInformationMapper;

    @Resource
    private MReportMapper mReportMapper;

    @Override
    public List<Project> selectSelfProject(String userId) {

        UserInformation userInformation = userInformationMapper.selectByPrimaryKey(userId);

        String identityId = userInformation.getIdentityId();

        //学生 ，看自己对应的项目
        if (identityId.equals("1")){
            Example example = new Example(Project.class);
            example.createCriteria().andEqualTo("userId",userId);
            return projectMapper.selectByExample(example);
        }else if (identityId.equals("2")){
            //指导老师的项目
            Example example = new Example(Project.class);
            example.createCriteria().andEqualTo("oneId",userId);
            List<Project> projects = projectMapper.selectByExample(example);

            Example example2 = new Example(Project.class);
            example2.createCriteria().andEqualTo("twoId",userId);
            List<Project> projects1 = projectMapper.selectByExample(example2);

            if (projects1!=null && projects1.size()>0){
                projects.addAll(projects1);
            }
            return projects;
        }else if (identityId.equals("3")){
            //二级学院管理者的项目,看该学院的内容
            Example example = new Example(Project.class);
            example.createCriteria().andEqualTo("collegeId",userInformation.getCollegeId());
            return projectMapper.selectByExample(example);
        }else if (identityId.equals("4")){
            //大创管理者，全部
            return projectMapper.selectAll();
        }else{
            //评审专家
            List<Project> projects = new ArrayList<>();
            Example example = new Example(MReport.class);
            example.createCriteria().andEqualTo("expert",userId);
            List<MReport> mReports = mReportMapper.selectByExample(example);
            mReports.forEach(mReport -> {
                Project project = projectMapper.selectByPrimaryKey(mReport.getProjectId());
                projects.add(project);
            });

            return projects;

        }
    }

    @Override
    public Project selectByPrimaryKey(Long projectId) {
        return projectMapper.selectByPrimaryKey(projectId);
    }

    @Override
    public int updateByPrimaryKeySelective(Project project) {
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public List<Project> selectByCollegeId(Long collegeId) {
        Example example = new Example(Project.class);
        example.createCriteria().andEqualTo("collegeId",collegeId);
        return projectMapper.selectByExample(example);
    }

    @Override
    public List<Project> select(Project project) {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(project.getProjectName())){
            criteria.andLike("projectName",project.getProjectName());
        }
        if (!StringUtils.isEmpty(project.getOneId())){
            criteria.andLike("oneId",project.getOneId());
        }
        return projectMapper.selectByExample(example);
    }
}
