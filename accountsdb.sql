/*
Navicat MySQL Data Transfer

Source Server         : MySQL Conn
Source Server Version : 50505
Source Host           : 127.0.0.1:3307
Source Database       : accountsdb

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-02-20 14:20:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `account_type` enum('savings','current','fixed') NOT NULL,
  `account_number` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'savings', '0011001111');
INSERT INTO `account` VALUES ('2', 'current', '1100115511');

-- ----------------------------
-- Table structure for statement
-- ----------------------------
DROP TABLE IF EXISTS `statement`;
CREATE TABLE `statement` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `account_id` int(7) NOT NULL DEFAULT '1',
  `datefield` datetime(6) NOT NULL,
  `amount` double(7,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of statement
-- ----------------------------
INSERT INTO `statement` VALUES ('1', '1', '2019-02-13 12:09:54.000000', '20000');
INSERT INTO `statement` VALUES ('2', '1', '2019-01-14 12:10:17.000000', '5000');
INSERT INTO `statement` VALUES ('3', '2', '2019-03-13 12:10:38.000000', '100000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `othernames` varchar(100) NOT NULL,
  `role` enum('superadmin','developer') NOT NULL,
  `loginCount` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique_index` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'testadmin', 'adminpassword', 'admin', 'admin', 'developer', '0');
INSERT INTO `user` VALUES ('2', 'testuser', 'userpassword', 'test', 'test', 'developer', '0');
INSERT INTO `user` VALUES ('3', 'admin', 'admin', 'Ebuka', 'Chukwuebuka', 'superadmin', '1');
