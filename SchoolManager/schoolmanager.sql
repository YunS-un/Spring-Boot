/*
Navicat MySQL Data Transfer

Source Server         : s1
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : schoolmanager

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2022-09-24 16:32:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', '系主任', '123456', '1@qq.com', '3');

-- ----------------------------
-- Table structure for `award`
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `award_id` int NOT NULL,
  `info` varchar(200) NOT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `award_id` (`award_id`),
  CONSTRAINT `award_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `award_ibfk_2` FOREIGN KEY (`award_id`) REFERENCES `award_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of award
-- ----------------------------
INSERT INTO `award` VALUES ('3', '3', '1', '我学习成绩好', '1');
INSERT INTO `award` VALUES ('4', '4', '2', '学习成绩很好', '1');

-- ----------------------------
-- Table structure for `award_info`
-- ----------------------------
DROP TABLE IF EXISTS `award_info`;
CREATE TABLE `award_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `money` int NOT NULL,
  `require` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of award_info
-- ----------------------------
INSERT INTO `award_info` VALUES ('1', '校三等奖学金', '1000', '学习成绩优秀，专业成绩前20。');
INSERT INTO `award_info` VALUES ('2', '校二等奖学金', '2000', '学习成绩优秀，专业成绩前10。');
INSERT INTO `award_info` VALUES ('3', '校一等奖学金', '3000', '学习成绩优秀，专业成绩前3。');

-- ----------------------------
-- Table structure for `clazz`
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clazz_name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `info` varchar(18) NOT NULL,
  `student_id` int NOT NULL,
  `teacher_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `clazz_ibfk_3` (`student_id`),
  CONSTRAINT `clazz_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `clazz_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', '软件一班', '软件技术学习', '1', '2');
INSERT INTO `clazz` VALUES ('2', '软件一班', '计算机知识学习', '2', '2');
INSERT INTO `clazz` VALUES ('3', '大数据一班', '计算机知识学习', '3', '2');
INSERT INTO `clazz` VALUES ('4', '软件二班', '计算机知识学习', '4', '2');
INSERT INTO `clazz` VALUES ('6', '软件三班', '计算机知识学习', '6', '1');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `course_id` int NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '3', '1', '86');
INSERT INTO `course` VALUES ('2', '3', '2', '50');
INSERT INTO `course` VALUES ('3', '3', '4', '60');
INSERT INTO `course` VALUES ('4', '6', '4', '100');
INSERT INTO `course` VALUES ('5', '6', '5', '90');
INSERT INTO `course` VALUES ('10', '2', '2', '90');

-- ----------------------------
-- Table structure for `course_info`
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `course_time` time NOT NULL,
  `course_address` varchar(255) NOT NULL,
  `teacher_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_teache` (`teacher_id`),
  CONSTRAINT `course_teache` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of course_info
-- ----------------------------
INSERT INTO `course_info` VALUES ('1', 'Java基础', '09:00:00', '第一教学楼T102', '1');
INSERT INTO `course_info` VALUES ('2', 'Windows系统', '10:05:00', '第三教学楼T302', '2');
INSERT INTO `course_info` VALUES ('3', 'Python基础', '02:00:00', '第二实验楼S602', '1');
INSERT INTO `course_info` VALUES ('4', '网络基础', '04:00:00', '第三教学楼T504', '1');
INSERT INTO `course_info` VALUES ('5', 'PHP基础', '07:00:00', '第四实验楼S705', '2');

-- ----------------------------
-- Table structure for `leave`
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `info` varchar(255) NOT NULL,
  `departure` varchar(255) NOT NULL,
  `destination` varchar(255) NOT NULL,
  `return_time` date NOT NULL,
  `status` char(1) DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `leave_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES ('1', '3', '探亲', '四川', '重庆', '2022-09-01', '1', '按时归来');
INSERT INTO `leave` VALUES ('2', '3', '相亲', '重庆', '江苏', '2022-09-03', '1', '可以出门');
INSERT INTO `leave` VALUES ('3', '3', '回家', '成都', '江苏', '2022-09-30', '0', '');
INSERT INTO `leave` VALUES ('4', '4', '探亲', '四川', '重庆', '2022-09-10', '1', null);
INSERT INTO `leave` VALUES ('5', '3', '回家', '西藏', '新疆', '2022-09-24', null, null);

-- ----------------------------
-- Table structure for `punishment`
-- ----------------------------
DROP TABLE IF EXISTS `punishment`;
CREATE TABLE `punishment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `punishment_time` date NOT NULL,
  `info` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `punishment_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of punishment
-- ----------------------------
INSERT INTO `punishment` VALUES ('1', '3', '警告', '2022-09-03', '违反校规');
INSERT INTO `punishment` VALUES ('2', '4', '记过', '2022-09-08', '违反校规');
INSERT INTO `punishment` VALUES ('3', '4', '记过', '2022-09-03', '违反校规');

-- ----------------------------
-- Table structure for `punishment_info`
-- ----------------------------
DROP TABLE IF EXISTS `punishment_info`;
CREATE TABLE `punishment_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `punishment_id` int NOT NULL,
  `info` varchar(200) NOT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `punishment_id` (`punishment_id`),
  CONSTRAINT `punishment_info_ibfk_1` FOREIGN KEY (`punishment_id`) REFERENCES `punishment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of punishment_info
-- ----------------------------
INSERT INTO `punishment_info` VALUES ('1', '1', '已经改过', '1');
INSERT INTO `punishment_info` VALUES ('2', '2', '改过自新', null);

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` bit(1) DEFAULT b'0',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `birthday` date DEFAULT NULL,
  `nation` varchar(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `img_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/static/picture/deskImg.png',
  `role` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '张三', '123456', '310895305@qq.com', '', '17898745635', '2022-09-01', '苗族', '贵州', '/static/picture/deskImg.png', '1');
INSERT INTO `student` VALUES ('2', '吕布', '123456', '66666666666@qq.com', '', '17545637893', '1990-07-15', '汉族', '四川', null, '1');
INSERT INTO `student` VALUES ('3', '花木兰', '123456', '1@qq.com', '', '17896541234', '1998-02-07', '汉', '成都市', '/static/picture/17896541234/dcb7891d-71f1-4014-90cf-3337acd398ba.jpg', '1');
INSERT INTO `student` VALUES ('4', '王二皮', '123456', '1124161283@qq.com', '', '17945697895', null, null, null, '/static/picture/deskImg.png', '1');
INSERT INTO `student` VALUES ('6', 'Yun', '123456', '17623224480@163.com', '', '17895464235', null, null, null, '/static/picture/deskImg.png', '1');
INSERT INTO `student` VALUES ('38', '许小二', '123456', '1250864092@qq.com', '', '18182286009', '1999-10-26', '汉', '重庆市', '/static/picture/18182286009/52c56565-4d84-455c-a12f-342c7ecaa9e2.jpg', '1');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` bit(1) DEFAULT b'0',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'XXXXXXXXXXX',
  `birthday` date DEFAULT NULL,
  `nation` varchar(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `img_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '/static/picture/deskImg.png',
  `role` int NOT NULL,
  `invitation_code` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_course` (`invitation_code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '王五', '123456', '905532947@qq.com', '', '17623227895', null, null, null, '/static/picture/deskImg.png', '2', '666666');
INSERT INTO `teacher` VALUES ('2', '吕布', '123456', '12@qq.com', '', '17545637893', '1990-07-20', '汉', '重庆市', '/static/picture/17545637893/ba30af9b-f167-4c2f-a884-95d5bf80022f.jpg', '2', '666666');
INSERT INTO `teacher` VALUES ('7', '云', '123456', '17865623548@163.com', '', '17865623548', '2022-09-02', '汉族', '重庆市', '/static/picture/deskImg.png', '1', '666666');
