CREATE database if NOT EXISTS `kaltsit` default character set utf8mb4 collate utf8mb4_general_ci;
use `kaltsit`;
/*
 Navicat Premium Data Transfer

 Source Server         : 150.158.145.167
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 150.158.145.167:3306
 Source Schema         : kaltsit

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 15/03/2023 15:06:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` int NULL DEFAULT NULL COMMENT '日志类型',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `time` bigint NULL DEFAULT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `create_date` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vue文件路径',
  `name_zh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单中文名',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int NULL DEFAULT NULL COMMENT '类型 0：目录 1：一级菜单 2：二级菜单 3：按钮',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `status` int NULL DEFAULT NULL COMMENT '是否隐藏，1-显示；2-隐藏；3-禁用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime NULL DEFAULT NULL COMMENT '新建时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int NULL DEFAULT NULL COMMENT '删除标记',
  `del_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0', NULL, NULL, NULL, '根节点', NULL, NULL, NULL, NULL, NULL, NULL, '2022-05-01 10:46:11', '2022-05-01 10:46:18', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529137618646228993', '0', '/tools', NULL, '实用工具', NULL, NULL, 0, 20, 1, NULL, '2022-05-25 00:29:53', '2023-01-13 23:51:29', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529138229139853314', '0', '/index', '/HomePageView.vue', '首页', 'index', 'el-icon-s-home', 1, 10, 1, NULL, '2022-05-25 00:32:19', '2023-03-03 14:36:52', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529138714366267394', '0', '/sys', NULL, '系统管理', NULL, NULL, 0, 50, 1, NULL, '2022-05-25 00:34:14', '2022-05-25 00:34:14', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529139036333633537', '1529138714366267394', '/sys/UserManagement', '/sys/UserManagement.vue', '用户管理', 'User', 'el-icon-user', 1, 10, 2, NULL, '2022-05-25 00:35:31', '2023-01-14 00:36:20', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529139209180860417', '1529138714366267394', '/sys/MenuManagement', '/sys/MenuManagement.vue', '菜单管理', 'Menu', 'el-icon-menu', 1, 20, 1, NULL, '2022-05-25 00:36:12', '2022-05-25 00:36:12', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1556910085169508354', '1529138714366267394', '/sys/SysLog', '/sys/SysLog.vue', '系统日志', 'SysLog', 'el-icon-tickets', 1, 30, 1, NULL, '2022-08-09 15:47:45', '2023-01-13 23:50:50', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1579714971074351105', '1529137618646228993', '/tools/TimeTool', '/tools/TimeTool.vue', '时间戳转换', 'Time', 'el-icon-time', 1, 10, 1, NULL, '2022-10-11 14:06:13', '2023-03-03 14:27:05', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1612459431350546434', '1529137618646228993', '/tools/VuexTest', '/tools/VuexTest.vue', 'vuex-demo', 'Vuex', 'el-icon-s-open', 1, 20, 3, NULL, '2023-01-09 22:41:01', '2023-01-09 22:41:01', 0, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名-账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `user_real_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT NULL,
  `del_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '1c963798a6065cdd54ede5e2d1c030be', '3JS1Ur4YUsLAcBEIMF+qbA==', '超级管理员', 18, '666@123.com', '18888888888', '超级管理员', '2022-06-08 17:44:38', '2022-06-08 17:44:38', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
