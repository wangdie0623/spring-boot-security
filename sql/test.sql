/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 100207
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 100207
File Encoding         : 65001

Date: 2018-04-28 17:56:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员');
INSERT INTO `role` VALUES ('2', 'dev', '开发人员');
INSERT INTO `role` VALUES ('3', 'cust', '客户');

-- ----------------------------
-- Table structure for role_source
-- ----------------------------
DROP TABLE IF EXISTS `role_source`;
CREATE TABLE `role_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `source_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_source
-- ----------------------------
INSERT INTO `role_source` VALUES ('1', '1', '1');
INSERT INTO `role_source` VALUES ('2', '1', '2');
INSERT INTO `role_source` VALUES ('3', '1', '3');
INSERT INTO `role_source` VALUES ('4', '2', '2');
INSERT INTO `role_source` VALUES ('5', '2', '3');
INSERT INTO `role_source` VALUES ('6', '3', '3');
INSERT INTO `role_source` VALUES ('7', '1', '4');

-- ----------------------------
-- Table structure for source
-- ----------------------------
DROP TABLE IF EXISTS `source`;
CREATE TABLE `source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of source
-- ----------------------------
INSERT INTO `source` VALUES ('1', 'login', '/login', '登录');
INSERT INTO `source` VALUES ('2', 'manger', '/admin', '管理');
INSERT INTO `source` VALUES ('3', 'dev', '/create', '新增');
INSERT INTO `source` VALUES ('4', 'btn', '/btn/testBtn', '按钮');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '王五', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `user` VALUES ('2', '张三', 'e10adc3949ba59abbe56e057f20f883e', '1');
INSERT INTO `user` VALUES ('3', '李四', 'e10adc3949ba59abbe56e057f20f883e', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
INSERT INTO `user_role` VALUES ('3', '3', '3');
INSERT INTO `user_role` VALUES ('4', '1', '2');
