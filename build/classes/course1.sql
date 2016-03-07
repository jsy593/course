/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.36 : Database - course
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`course` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `course`;

/*Table structure for table `bulletinboard` */

DROP TABLE IF EXISTS `bulletinboard`;

CREATE TABLE `bulletinboard` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bulletinboard` */

/*Table structure for table `classes` */

DROP TABLE IF EXISTS `classes`;

CREATE TABLE `classes` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `name` varchar(30) NOT NULL COMMENT '班级名称',
  `majorId` bigint(12) NOT NULL COMMENT '专业id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `classes` */

insert  into `classes`(`id`,`name`,`majorId`,`createTime`) values (1,'软件1班',1,'2016-03-08 00:00:00'),(2,'声乐1班',2,'2016-03-02 00:00:00'),(4,'软件2班',1,'2016-03-15 00:00:00');

/*Table structure for table `college` */

DROP TABLE IF EXISTS `college`;

CREATE TABLE `college` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '学院id',
  `name` varchar(30) NOT NULL COMMENT '学院名称',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `college` */

insert  into `college`(`id`,`name`,`createTime`) values (1,'计算机学院','2016-03-01 15:41:58'),(2,'音乐舞蹈学院','2016-02-16 00:00:00'),(4,'设计学院','2015-09-01 15:43:03'),(5,'Callege','2016-03-08 09:50:32'),(6,'材料学院','2016-03-16 09:50:35'),(11,'sada','2016-03-04 00:00:00'),(12,'sadsa','2016-03-02 00:00:00'),(13,'dsadsa','2016-03-21 00:00:00'),(16,'asdsa','2016-03-25 00:00:00'),(17,'fgdgfgf','2016-03-22 00:00:00'),(18,'发很多','2016-03-29 00:00:00'),(19,'eee','2016-03-03 00:00:00'),(20,'jj','2016-03-08 00:00:00');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `courseNumber` bigint(12) DEFAULT NULL COMMENT '课程编号',
  `name` varchar(50) NOT NULL COMMENT '课程名称',
  `credit` int(2) NOT NULL COMMENT '学分',
  `teacherId` bigint(12) NOT NULL COMMENT '任课老师id',
  `schooltime` varchar(50) NOT NULL DEFAULT '' COMMENT '上课时间',
  `address` varchar(50) NOT NULL COMMENT '上课地址',
  `specialtyId` bigint(12) DEFAULT NULL COMMENT '所属专业id',
  `numberLimit` int(3) NOT NULL COMMENT '最大的选课人数',
  `isFinish` int(1) DEFAULT '0' COMMENT '是否可选（0：不可  1：可选）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

/*Table structure for table `major` */

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '专业id',
  `name` varchar(30) NOT NULL COMMENT '专业名称',
  `collegeId` bigint(12) NOT NULL COMMENT '学院id',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `major` */

insert  into `major`(`id`,`name`,`collegeId`,`createTime`) values (1,'软件工程',1,'2016-03-07 00:00:00'),(2,'声乐学',2,'2016-03-09 00:00:00');

/*Table structure for table `studentcourse` */

DROP TABLE IF EXISTS `studentcourse`;

CREATE TABLE `studentcourse` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT COMMENT '学生和课程中间表id',
  `studentId` bigint(12) NOT NULL COMMENT ' 学生id',
  `courseId` bigint(12) NOT NULL COMMENT '课程id',
  `grade` int(3) DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `studentcourse` */

/*Table structure for table `user` */

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`userNumber`,`username`,`password`,`classid`,`credit`,`identity`,`imageUrl`,`sex`,`createTime`) values (1,20160000,'教务处管理员','123',NULL,NULL,0,'upload/1/1.jpg',0,'2016-03-01 00:00:00'),(2,20161000,'李小璐','123',NULL,0,1,'images/2.jpg',2,'2016-03-02 00:00:00'),(3,20161001,'贾乃亮','123',NULL,0,1,'images/2.jpg',1,'2016-03-02 00:00:00'),(4,20162000,'贾甜馨','123',NULL,0,2,'images/2.jpg',0,'2016-03-02 00:00:00'),(5,20160001,'jsy','123',NULL,0,1,'images/2.jpg',2,'2016-03-06 17:18:56'),(6,20160002,'aaa','123',NULL,0,0,'images/2.jpg',2,'2016-03-08 17:19:02'),(7,23433,'12','12',NULL,0,1,'assets/avatars/user.jpg',2,'2016-03-13 00:00:00'),(8,1233213,'21','21',NULL,0,1,'assets/avatars/user.jpg',0,'2016-03-21 00:00:00'),(9,20160002,'1234','1234',1,0,1,'images/2.jpg',2,'2016-03-15 00:00:00'),(10,20161003,'ji','123',1,0,1,'images/2.jpg',1,'2016-03-21 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
