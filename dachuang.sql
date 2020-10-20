/*
Navicat MySQL Data Transfer

Source Server         : 阿里云
Source Server Version : 80019
Source Host           : 47.113.80.250:3306
Source Database       : dachuang

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-04-12 19:40:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `collegeId` bigint NOT NULL AUTO_INCREMENT COMMENT '自增学院id',
  `collegeName` varchar(50) NOT NULL COMMENT '学院名字',
  PRIMARY KEY (`collegeId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '计算机科学与工程学院');
INSERT INTO `college` VALUES ('2', '政法学院');
INSERT INTO `college` VALUES ('3', '电子信息与电气工程学院');
INSERT INTO `college` VALUES ('4', '地理与旅游学院');
INSERT INTO `college` VALUES ('5', '数学与大数据学院');
INSERT INTO `college` VALUES ('6', '化学与材料工程学院');
INSERT INTO `college` VALUES ('7', '建筑与土木工程学院');
INSERT INTO `college` VALUES ('8', '旭日广东服装学院');
INSERT INTO `college` VALUES ('9', '生命科学学院');
INSERT INTO `college` VALUES ('10', '经济管理学院');
INSERT INTO `college` VALUES ('11', '体育学院');

-- ----------------------------
-- Table structure for `identity`
-- ----------------------------
DROP TABLE IF EXISTS `identity`;
CREATE TABLE `identity` (
  `identity_id` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '闊偂鍞d',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '闊偂鍞ょ憴鎺曞閹诲繗鍫?',
  `identity_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '闊偂鍞ょ憴鎺曞閼昏鲸鏋冮崥?',
  PRIMARY KEY (`identity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='身份表';

-- ----------------------------
-- Records of identity
-- ----------------------------
INSERT INTO `identity` VALUES ('1', '学生', 'student');
INSERT INTO `identity` VALUES ('2', '老师', 'teacher');
INSERT INTO `identity` VALUES ('3', '二级学院管理者', 'college administrators');
INSERT INTO `identity` VALUES ('4', '大创管理者，超级管理员', 'admin');
INSERT INTO `identity` VALUES ('5', '评审专家', 'expert');

-- ----------------------------
-- Table structure for `m_file`
-- ----------------------------
DROP TABLE IF EXISTS `m_file`;
CREATE TABLE `m_file` (
  `f_id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `f_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '鏂囦欢璺緞',
  `f_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '鏂囦欢鍚?',
  `f_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '鏂囦欢绫诲瀷锛宲df銆乨oc',
  `report_id` bigint NOT NULL COMMENT '是该中期报告的文件之一',
  PRIMARY KEY (`f_id`),
  KEY `file_report` (`report_id`),
  CONSTRAINT `file_report` FOREIGN KEY (`report_id`) REFERENCES `m_report` (`report_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of m_file
-- ----------------------------
INSERT INTO `m_file` VALUES ('5', 'http://47.113.80.250/group1/M00/00/00/rBJg-l6R5aCAKOYbAATuTCSFI4s35.docx', '附件3.微博话题讨论操作流程.docx', null, '6');
INSERT INTO `m_file` VALUES ('6', 'http://47.113.80.250/group1/M00/00/00/rBJg-l6R5mGAaZjLAAByACkbRYU171.doc', '附件1.惠州学院2020年寒假社会实践活动记录表.doc', null, '6');
INSERT INTO `m_file` VALUES ('8', 'http://47.113.80.250/group1/M00/00/00/rBJg-l6StZ-AYXr3AA9MNnBiV6g759.pdf', '附件1 广东省关于举办第六届“互联网+”大学生创新创业大赛广东省分赛的预通知.pdf', null, '7');
INSERT INTO `m_file` VALUES ('9', 'http://47.113.80.250/group1/M00/00/00/rBJg-l6Stu2ASsj5ABk5WWFP-Ck97.pptx', '附件4：项目商业计划书参考模板.pptx', null, '7');
INSERT INTO `m_file` VALUES ('10', 'http://47.113.80.250/group1/M00/00/00/rBJg-l6St9CAZAAMAAVUS0k1PtE571.pdf', '惠院计算机〔2020〕11号 计算机科学与工程学院2020年“互联网+”大学生创新创业大赛实施方案(1).pdf', null, '8');
INSERT INTO `m_file` VALUES ('11', 'http://47.113.80.250/group1/M00/00/00/rBJg-l6St-GAaro5AIah3CPxLEI352.pdf', '项目计划书——绿小萝室内空气治理 （最新版）(1).pdf', null, '8');
INSERT INTO `m_file` VALUES ('12', 'http://47.113.80.250/group1/M00/00/00/rBJg-l6SvAyAa4oAAACi6D_m-fA889.png', 'Diagram 1.png', null, '8');

-- ----------------------------
-- Table structure for `m_report`
-- ----------------------------
DROP TABLE IF EXISTS `m_report`;
CREATE TABLE `m_report` (
  `report_id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(25) NOT NULL COMMENT '负责人',
  `content` varchar(500) DEFAULT NULL COMMENT '中期文件解释，类似encoder',
  `project_id` bigint NOT NULL COMMENT '项目id',
  `t_comment` varchar(500) DEFAULT NULL COMMENT '导师评语',
  `t_approval` int DEFAULT NULL COMMENT '导师认可，未审核0，不通过1，通过2，退回修改3',
  `c_comment` varchar(500) DEFAULT NULL COMMENT '学院评语',
  `c_approval` int DEFAULT NULL COMMENT '学院认可，未审核0，不通过1，通过2，退回学生3，退回导师4',
  `expert` varchar(25) DEFAULT NULL COMMENT '评审专家id',
  `s_comment` varchar(500) DEFAULT NULL COMMENT '大创管理评语',
  `s_approval` int DEFAULT NULL COMMENT '大创管理认可，未审核0，通过2，退回修改3',
  `e_comment` varchar(500) DEFAULT NULL COMMENT '评审专家评语语',
  `e_approval` int DEFAULT NULL COMMENT '评审专家认可，未审核0，不通过1，通过2，暂缓通过3',
  PRIMARY KEY (`report_id`),
  KEY `report_user` (`user_id`),
  KEY `report_project` (`project_id`),
  CONSTRAINT `report_project` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='中期报告表';

-- ----------------------------
-- Records of m_report
-- ----------------------------
INSERT INTO `m_report` VALUES ('6', '1714080902133', '吴泽强大创项目内容简介', '6', '指导老师评论', '2', '二级学院管理者评论', '2', 'expert', '大创管理者评论', '2', '专家评论', '2');
INSERT INTO `m_report` VALUES ('7', '1714080902139', '朱俊源该大创项目的中期报告简介', '8', null, '0', null, '0', 'expert', null, '0', null, '0');
INSERT INTO `m_report` VALUES ('8', '1714080902120', '刘丹锐该大创项目的中期报告简介', '9', null, '0', null, '0', null, null, '0', null, '0');

-- ----------------------------
-- Table structure for `oauth_access_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('dachuang', 'res_report', '$2a$10$my4Hv0qDU8zY.HlYlkwHlep95f95Yy6F4i66DCUuxrKUkCYB18e1S', 'all', 'password,refresh_token', null, null, '18000000', '18000000', null, null);

-- ----------------------------
-- Table structure for `oauth_refresh_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for `p_range`
-- ----------------------------
DROP TABLE IF EXISTS `p_range`;
CREATE TABLE `p_range` (
  `identity_id` varchar(25) NOT NULL COMMENT '身份角色ID',
  `p_id` varchar(25) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`identity_id`,`p_id`),
  KEY `fk_permission` (`p_id`),
  CONSTRAINT `fk_identity` FOREIGN KEY (`identity_id`) REFERENCES `identity` (`identity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_permission` FOREIGN KEY (`p_id`) REFERENCES `permission` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限关联表';

-- ----------------------------
-- Records of p_range
-- ----------------------------
INSERT INTO `p_range` VALUES ('1', '17');
INSERT INTO `p_range` VALUES ('2', '18');
INSERT INTO `p_range` VALUES ('3', '19');
INSERT INTO `p_range` VALUES ('1', '2');
INSERT INTO `p_range` VALUES ('4', '20');
INSERT INTO `p_range` VALUES ('5', '21');
INSERT INTO `p_range` VALUES ('1', '3');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `p_id` varchar(25) NOT NULL COMMENT '权限ID',
  `description` varchar(50) NOT NULL COMMENT '权限描述',
  `url` varchar(150) NOT NULL COMMENT '链接地址',
  `p_name` varchar(50) NOT NULL COMMENT '权限名',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '中期报告所有', '/report/**', 'ReportAll');
INSERT INTO `permission` VALUES ('10', '专家认可', '/report/expert/approval', 'ExpertApproval');
INSERT INTO `permission` VALUES ('11', '大创管理者查询所有专家', '/report/admin/select/expert/all', 'SelectExpertAll');
INSERT INTO `permission` VALUES ('12', '大创管理者获取所有中期报告情况', '/report/admin/select/all', 'AdminSelectAll');
INSERT INTO `permission` VALUES ('13', '大创管理者给中期报告选派专家', '/report/admin/set/expert', 'SetExpert');
INSERT INTO `permission` VALUES ('14', '大创管理者认可中期报告', '/report/admin/approval', 'AdminApproval');
INSERT INTO `permission` VALUES ('15', '汇总生成报表', '/report/admin/excel', 'AdminExcel');
INSERT INTO `permission` VALUES ('16', '查询中期报告', '/report/select', 'ReportSelect');
INSERT INTO `permission` VALUES ('17', '学生所有', '/report/student/**', 'Student');
INSERT INTO `permission` VALUES ('18', '指导老师所有', '/report/teacher/**', 'Teacher');
INSERT INTO `permission` VALUES ('19', '二级学院所有', '/report/college/**', 'College');
INSERT INTO `permission` VALUES ('2', '提交中期报告', '/report/student/insert', 'ReportInsert');
INSERT INTO `permission` VALUES ('20', '大创管理所有', '/report/admin/**', 'Admin');
INSERT INTO `permission` VALUES ('21', '专家所有', '/report/expert/**', 'Expert');
INSERT INTO `permission` VALUES ('3', '修改中期报告', '/report/student/update', 'ReportUpdate');
INSERT INTO `permission` VALUES ('4', '文件删除', '/report/student/file/delete', 'ReportFileDelete');
INSERT INTO `permission` VALUES ('5', '文件上传，可多个', '/report/file/insert', 'ReportFileInsert');
INSERT INTO `permission` VALUES ('6', '文件上传，单个', '/report/file/insert/one', 'ReportFileInsertOne');
INSERT INTO `permission` VALUES ('7', '导师认可', '/report/teacher/approval', 'TeacherApproval');
INSERT INTO `permission` VALUES ('8', '二级学院认可', '/report/college/approval', 'CollegeApproval');
INSERT INTO `permission` VALUES ('9', '二级学院查看对应学院的报告', '/report/college/select/all', 'CollegeSelectAll');

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(25) NOT NULL COMMENT '负责人',
  `one_id` varchar(25) DEFAULT NULL COMMENT '指导老师1',
  `two_id` varchar(25) DEFAULT NULL COMMENT '指导老师2',
  `grade` int DEFAULT NULL COMMENT '等级，无0，校1，省2，国3',
  `project_name` varchar(50) NOT NULL COMMENT '项目名字',
  `collegeId` bigint NOT NULL COMMENT '学院id',
  `s_report` tinyint DEFAULT NULL COMMENT '申请报告提交状态，0未提交，1提交,默认0',
  `m_report` tinyint DEFAULT NULL COMMENT '中期报告提交状态，0未提交，1提交，默认0',
  `f_report` tinyint DEFAULT NULL COMMENT '结题报告提交状态，0未提交，1提交，默认0',
  PRIMARY KEY (`project_id`),
  KEY `project_user` (`user_id`),
  KEY `project_college` (`collegeId`),
  CONSTRAINT `project_college` FOREIGN KEY (`collegeId`) REFERENCES `college` (`collegeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目表';

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '1714080902122', 'peng', null, '2', '楼宇检验及维修信息化平台研发', '1', '0', '0', '0');
INSERT INTO `project` VALUES ('2', '1714080902132', 'ma', null, '1', '区域气象预警决策服务系统', '1', '0', '0', '0');
INSERT INTO `project` VALUES ('3', '1714080902121', 'ma', 'hu', '2', '基于RBAC的通用权限管理系统', '1', '0', '0', '0');
INSERT INTO `project` VALUES ('4', '1714080902123', 'teacher', null, '2', '词汇听写微信小程序辅助系统研发', '1', '0', '0', '0');
INSERT INTO `project` VALUES ('5', 'student1', 'teacher1', null, '3', '粤港澳大湾区建设下惠州市跨境电商发展路径研究', '10', '0', '0', '0');
INSERT INTO `project` VALUES ('6', '1714080902133', 'peng', null, '2', '面向蔬菜业田间管理的云平台开发', '1', '0', '1', '0');
INSERT INTO `project` VALUES ('7', 'student2', 'teacher2', null, '1', '蓝瓶子—塑料饮料瓶自动分类机', '5', '0', '0', '0');
INSERT INTO `project` VALUES ('8', '1714080902139', 'peng', null, '2', '称重物联网测控终端集贸市场零售APP', '1', '0', '1', '0');
INSERT INTO `project` VALUES ('9', '1714080902120', 'hu', null, '2', '互联网虚拟仿真教学辅助系统', '1', '0', '1', '0');

-- ----------------------------
-- Table structure for `time_args`
-- ----------------------------
DROP TABLE IF EXISTS `time_args`;
CREATE TABLE `time_args` (
  `m_out_date` datetime NOT NULL COMMENT '中期报告超时时间，yyyy-MM-dd hh:mm:ss',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '鑷id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='时间参数表';

-- ----------------------------
-- Records of time_args
-- ----------------------------
INSERT INTO `time_args` VALUES ('2020-10-25 15:33:45', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(25) NOT NULL COMMENT '用户ID',
  `password` varchar(150) NOT NULL COMMENT '用户密码',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户手机，11位',
  `identity_id` varchar(25) NOT NULL COMMENT '身份角色ID',
  `collegeId` bigint NOT NULL COMMENT '学院id',
  PRIMARY KEY (`user_id`),
  KEY `user_identity` (`identity_id`),
  KEY `user_college` (`collegeId`),
  CONSTRAINT `user_college` FOREIGN KEY (`collegeId`) REFERENCES `college` (`collegeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_identity` FOREIGN KEY (`identity_id`) REFERENCES `identity` (`identity_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1714080902120', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '刘丹锐', '65656541111', '1', '1');
INSERT INTO `user` VALUES ('1714080902121', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '郑桂洲', '17724248547', '1', '1');
INSERT INTO `user` VALUES ('1714080902122', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '黄史林', '17764564564', '1', '1');
INSERT INTO `user` VALUES ('1714080902123', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '徐略标', '17727242485', '1', '1');
INSERT INTO `user` VALUES ('1714080902132', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '潘强', '17724249627', '1', '1');
INSERT INTO `user` VALUES ('1714080902133', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '吴泽强', '17727242496', '1', '1');
INSERT INTO `user` VALUES ('1714080902139', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '朱俊源', '46546511111', '1', '1');
INSERT INTO `user` VALUES ('admin', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', 'admin', '17777777711', '4', '1');
INSERT INTO `user` VALUES ('computerAdmin', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '计算机管理', '11111111111', '3', '1');
INSERT INTO `user` VALUES ('economicsAdmin', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '经管学院管理', '11234565222', '3', '10');
INSERT INTO `user` VALUES ('expert', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', 'expert', '16666666611', '5', '1');
INSERT INTO `user` VALUES ('expert1', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '专家1', '17772444422', '5', '2');
INSERT INTO `user` VALUES ('expert2', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '专家2', '11654546422', '5', '5');
INSERT INTO `user` VALUES ('hu', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '胡辉', '11111111111', '2', '1');
INSERT INTO `user` VALUES ('ma', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '马驰', '11111111111', '2', '1');
INSERT INTO `user` VALUES ('mathAdmin', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '数学管理', '64564564566', '3', '5');
INSERT INTO `user` VALUES ('peng', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '彭树宏', '45646451111', '2', '1');
INSERT INTO `user` VALUES ('student1', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '张三', '17242485961', '1', '10');
INSERT INTO `user` VALUES ('student2', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '李四', '15478845644', '1', '5');
INSERT INTO `user` VALUES ('teacher', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '计算机老师', '17777777711', '2', '1');
INSERT INTO `user` VALUES ('teacher1', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '经管老师', '46452222222', '2', '10');
INSERT INTO `user` VALUES ('teacher2', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', '数学老师', '45645641112', '2', '5');
INSERT INTO `user` VALUES ('user', '$2a$10$d8CJrVBwmX4RNM3VwAe1oODM2ulrI5dz8iPZ63bfvcrIamcU5UVhW', 'user', '17772424811', '1', '2');
