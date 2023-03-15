CREATE database if NOT EXISTS `two_resource_1` default character set utf8mb4 collate utf8mb4_general_ci;
use `two_resource_1`;
/*
 Navicat Premium Data Transfer

 Source Server         : 150.158.145.167
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 150.158.145.167:3306
 Source Schema         : two_resource_1

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 15/03/2023 15:07:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1524722145007067138', 'AAA-AAA', 18, NULL, NULL, 0);
INSERT INTO `user` VALUES ('1524922451586297857', 'AAA-2', 18, NULL, NULL, 0);
INSERT INTO `user` VALUES ('1524952565539737602', 'AAA-3', 18, NULL, NULL, 0);
INSERT INTO `user` VALUES ('1524998193946923010', 'AAA-2', 18, NULL, NULL, 0);
INSERT INTO `user` VALUES ('1529139536345030658', 'AAA-2', 18, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
