package com.wzq.da.chuang.model.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelPojo implements Serializable {

    private static final long serialVersionUID = -60345996706689418L;

//        writer.addHeaderAlias("id", "项目id");
//        writer.addHeaderAlias("name", "项目名");
//        writer.addHeaderAlias("userName", "负责人");
//        writer.addHeaderAlias("teacherName", "指导老师"); //2个老师的话拼接
//        writer.addHeaderAlias("expertName", "评审专家");
//        writer.addHeaderAlias("report", "是否提交");
//        writer.addHeaderAlias("tApproval", "指导老师评审");
//        writer.addHeaderAlias("CApproval", "二级学院评审");
//        writer.addHeaderAlias("SApproval", "大创管理评审");
//        writer.addHeaderAlias("EApproval", "评审专家评审");

    private String id;

    private String name;

    private String userName;

    private String teacherName;

    private String expertName;

    private String report;

    private String tApproval;

    private String CApproval;

    private String SApproval;

    private String EApproval;

}
