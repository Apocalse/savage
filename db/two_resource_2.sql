CREATE database if NOT EXISTS `two_resource_2` default character set utf8mb4 collate utf8mb4_general_ci;
use `two_resource_2`;
/*
 Navicat Premium Data Transfer

 Source Server         : 150.158.145.167
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 150.158.145.167:3306
 Source Schema         : two_resource_2

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 15/03/2023 15:07:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1524722300565434369', 'BBB', 18);

SET FOREIGN_KEY_CHECKS = 1;
