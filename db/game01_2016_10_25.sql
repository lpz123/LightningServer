/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : game01
Target Host     : localhost:3306
Target Database : game01
Date: 2016-10-25 19:26:00
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_game_role
-- ----------------------------
DROP TABLE IF EXISTS `t_game_role`;
CREATE TABLE `t_game_role` (
  `roleId` bigint(20) NOT NULL,
  `roleName` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_game_role
-- ----------------------------
