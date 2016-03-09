/*
Navicat MySQL Data Transfer

Source Server         : hostConnection
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : course

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-03-09 16:00:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bulletinboard`
-- ----------------------------
DROP TABLE IF EXISTS `bulletinboard`;
CREATE TABLE `bulletinboard` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bulletinboard
-- ----------------------------

-- ----------------------------
-- Table structure for `classes`
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `name` varchar(30) NOT NULL COMMENT '班级名称',
  `majorId` bigint(12) NOT NULL COMMENT '专业id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', '软件1班', '1', '2016-03-08 00:00:00');
INSERT INTO `classes` VALUES ('2', '声乐1班', '2', '2016-03-02 00:00:00');
INSERT INTO `classes` VALUES ('4', '软件2班', '1', '2016-03-15 00:00:00');

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '学院id',
  `name` varchar(30) NOT NULL COMMENT '学院名称',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '计算机学院', '2016-03-01 15:41:58');
INSERT INTO `college` VALUES ('2', '音乐舞蹈学院', '2016-02-16 00:00:00');
INSERT INTO `college` VALUES ('4', '设计学院', '2015-09-01 15:43:03');
INSERT INTO `college` VALUES ('5', 'Callege', '2016-03-08 09:50:32');
INSERT INTO `college` VALUES ('6', '材料学院', '2016-03-16 09:50:35');
INSERT INTO `college` VALUES ('11', 'sada', '2016-03-04 00:00:00');
INSERT INTO `college` VALUES ('12', 'sadsa', '2016-03-02 00:00:00');
INSERT INTO `college` VALUES ('13', 'dsadsa', '2016-03-21 00:00:00');
INSERT INTO `college` VALUES ('16', 'asdsa', '2016-03-25 00:00:00');
INSERT INTO `college` VALUES ('17', 'fgdgfgf', '2016-03-22 00:00:00');
INSERT INTO `college` VALUES ('18', '发很多', '2016-03-29 00:00:00');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `courseNumber` bigint(12) DEFAULT NULL COMMENT '课程编号',
  `name` varchar(50) NOT NULL COMMENT '课程名称',
  `credit` int(2) NOT NULL COMMENT '学分',
  `teacherId` bigint(12) NOT NULL COMMENT '任课老师id',
  `schooltime` varchar(50) NOT NULL DEFAULT '' COMMENT '上课时间',
  `address` varchar(50) NOT NULL COMMENT '上课地址',
  `specialtyId` bigint(12) NOT NULL COMMENT '所属专业id',
  `numberLimit` int(3) NOT NULL COMMENT '最大的选课人数',
  `isFinish` int(1) DEFAULT '0' COMMENT '是否可选（0：不可  1：可选）',
  `createTime` datetime DEFAULT NULL COMMENT '添加课程的时间',
  `isChange` int(1) DEFAULT '0' COMMENT '老师是否可修改（0：不可修改 1：可修改 2:已修改)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '1', '历史学', '2', '2', '周三5,6节；周五9,10节（16周）', '仁和楼304', '1', '213', '1', '2016-03-07 00:00:00', '2');
INSERT INTO `course` VALUES ('3', '23', '政治学', '23', '3', '周二5,6节；周五9,10节（16周）', '德泽楼212', '2', '80', '0', '2016-03-23 00:00:00', '0');
INSERT INTO `course` VALUES ('4', '223', 'try', '3', '3', 'today', '232', '1', '69', '0', '2016-03-16 00:00:00', '0');
INSERT INTO `course` VALUES ('5', '12', '经济学位', '2', '2', '周三5,6节；周四9,10节（16周）', '新福楼321', '1', '345', '1', '2016-03-07 00:00:00', '2');
INSERT INTO `course` VALUES ('6', '123', '123', '123', '2', '12343', '312321', '1', '13', '1', '2016-03-28 00:00:00', '0');

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '专业id',
  `name` varchar(30) NOT NULL COMMENT '专业名称',
  `collegeId` bigint(12) NOT NULL COMMENT '学院id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1', '软件工程', '1', '2016-03-07 00:00:00');
INSERT INTO `major` VALUES ('2', '声乐学', '2', '2016-03-09 00:00:00');

-- ----------------------------
-- Table structure for `studentcourse`
-- ----------------------------
DROP TABLE IF EXISTS `studentcourse`;
CREATE TABLE `studentcourse` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '学生和课程中间表id',
  `studentId` bigint(12) NOT NULL COMMENT ' 学生id',
  `courseId` bigint(12) NOT NULL COMMENT '课程id',
  `grade` int(3) DEFAULT NULL COMMENT '成绩',
  `createTime` datetime NOT NULL COMMENT '选课时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentcourse
-- ----------------------------
INSERT INTO `studentcourse` VALUES ('14', '4', '5', null, '2016-03-09 09:48:22');
INSERT INTO `studentcourse` VALUES ('15', '10', '6', null, '2016-03-09 13:38:32');
INSERT INTO `studentcourse` VALUES ('16', '8', '6', null, '2016-03-09 13:38:52');

-- ----------------------------
-- Table structure for `teachercourse`
-- ----------------------------
DROP TABLE IF EXISTS `teachercourse`;
CREATE TABLE `teachercourse` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `courseId` bigint(12) NOT NULL COMMENT '课程id',
  `content` varchar(200) NOT NULL COMMENT '修改原因',
  `isAgree` int(1) DEFAULT '0' COMMENT '管理员是否同意(0:待查看 1：同意 2 ：拒绝)',
  `createTime` datetime DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`id`),
  KEY `pk_course` (`courseId`),
  CONSTRAINT `pk_course` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachercourse
-- ----------------------------
INSERT INTO `teachercourse` VALUES ('1', '6', '上多个萨达', '0', '2016-03-07 17:36:05');
INSERT INTO `teachercourse` VALUES ('6', '1', '第三方第三方东方', '1', '2016-03-08 10:47:38');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `userNumber` bigint(12) DEFAULT NULL COMMENT '用户编号',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `classid` bigint(12) DEFAULT NULL COMMENT '所属班级',
  `credit` int(10) DEFAULT '0' COMMENT '学分',
  `identity` int(1) NOT NULL COMMENT '身份（0：管理员 1：老师  2:学生 )',
  `imageUrl` varchar(100) DEFAULT NULL COMMENT '头像地址',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别(1 男 2 女 0 未知)',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `PK_USER_CLASS` (`classid`),
  CONSTRAINT `PK_USER_CLASS` FOREIGN KEY (`classid`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '20160000', '教务处管理员', '1234', null, null, '0', 'upload/1/1.jpg', '0', '2016-03-01 00:00:00');
INSERT INTO `user` VALUES ('2', '20161000', '李小璐', '123', null, '0', '1', 'upload/2/2.jpg', '2', '2016-03-02 00:00:00');
INSERT INTO `user` VALUES ('3', '20161001', '贾乃亮3', '123', null, '0', '1', 'images/2.jpg', '2', '2016-03-02 00:00:00');
INSERT INTO `user` VALUES ('4', '20162000', '贾甜馨', '123', '2', '0', '2', 'images/2.jpg', '0', '2016-03-02 00:00:00');
INSERT INTO `user` VALUES ('5', '20160121', 'jsy23', '12345', '2', '0', '2', 'images/2.jpg', '1', '2016-03-08 00:00:00');
INSERT INTO `user` VALUES ('6', '20160002', 'aaa', '123', null, '0', '0', 'images/2.jpg', '2', '2016-03-08 17:19:02');
INSERT INTO `user` VALUES ('8', '1233213', '21', '21', '4', '0', '2', 'assets/avatars/user.jpg', '0', '2016-03-21 00:00:00');
INSERT INTO `user` VALUES ('10', '20162010', '萨达萨达', '123', '1', '0', '2', 'images/2.jpg', '0', '2016-03-07 00:00:00');
