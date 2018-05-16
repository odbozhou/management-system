-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.13 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 electronic_report 的数据库结构
CREATE DATABASE IF NOT EXISTS `electronic_report` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `electronic_report`;


-- 导出  表 electronic_report.course 结构
CREATE TABLE IF NOT EXISTS `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程';

-- 数据导出被取消选择。


-- 导出  表 electronic_report.experiment 结构
CREATE TABLE IF NOT EXISTS `experiment` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `gid` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `outline` varchar(500) NOT NULL,
  `content` text NOT NULL,
  `time` datetime NOT NULL,
  `place` varchar(200) NOT NULL,
  `status` tinyint(2) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实验';

-- 数据导出被取消选择。


-- 导出  表 electronic_report.experiment_report 结构
CREATE TABLE IF NOT EXISTS `experiment_report` (
  `erid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `sid` int(11) NOT NULL,
  `eid` int(11) NOT NULL,
  `objective` varchar(500) NOT NULL,
  `step` varchar(500) NOT NULL,
  `problem` varchar(500) NOT NULL,
  `result` varchar(500) NOT NULL,
  `summary` varchar(500) NOT NULL,
  `remarks` varchar(500) NOT NULL,
  `read_status` tinyint(2) NOT NULL,
  `status` tinyint(2) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`erid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实验报告';

-- 数据导出被取消选择。


-- 导出  表 electronic_report.grade 结构
CREATE TABLE IF NOT EXISTS `grade` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级';

-- 数据导出被取消选择。


-- 导出  表 electronic_report.student 结构
CREATE TABLE IF NOT EXISTS `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` tinyint(3) NOT NULL,
  `gid` int(11) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生';

-- 数据导出被取消选择。


-- 导出  表 electronic_report.teacher 结构
CREATE TABLE IF NOT EXISTS `teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sex` tinyint(3) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师';

-- 数据导出被取消选择。


-- 导出  表 electronic_report.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `passwd` varchar(80) NOT NULL,
  `role` varchar(20) NOT NULL,
  `status` tinyint(3) NOT NULL DEFAULT '1',
  `delete_status` tinyint(3) NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uniq_login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
