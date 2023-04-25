/*
 Navicat Premium Data Transfer

 Source Server         : 150.158.145.167
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : 150.158.145.167:3306
 Source Schema         : kaltsit

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 25/04/2023 16:42:19
*/
CREATE database if NOT EXISTS `kaltsit` default character set utf8mb4 collate utf8mb4_general_ci;
use `kaltsit`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `type` int(3) NULL DEFAULT NULL COMMENT '日志类型',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求URI',
  `create_date` datetime NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `del_flag` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1644591115101327362', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 283, '127.0.0.1', '/user/login', '2023-04-08 14:40:52', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591116519002114', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 244, '127.0.0.1', '/menu/list', '2023-04-08 14:40:52', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591151155564545', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 377, '127.0.0.1', '/menu/list', '2023-04-08 14:41:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591158097137665', 4, 'admin', '查询用户列表', 'getUserList', '[]', 22, '127.0.0.1', '/user/list', '2023-04-08 14:41:02', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591159082799105', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 247, '127.0.0.1', '/sysLog/list', '2023-04-08 14:41:02', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591168620646402', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 320, '127.0.0.1', '/menu/list', '2023-04-08 14:41:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591318038532097', 4, 'admin', '查询用户列表', 'getUserList', '[]', 14, '127.0.0.1', '/user/list', '2023-04-08 14:41:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591318105640962', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 34, '127.0.0.1', '/sysLog/list', '2023-04-08 14:41:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591322367053826', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 252, '127.0.0.1', '/menu/list', '2023-04-08 14:41:41', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591327807066114', 4, 'admin', '查询用户列表', 'getUserList', '[]', 14, '127.0.0.1', '/user/list', '2023-04-08 14:41:42', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591327874174977', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 39, '127.0.0.1', '/sysLog/list', '2023-04-08 14:41:42', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591345989373953', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 287, '127.0.0.1', '/menu/list', '2023-04-08 14:41:47', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591506551525378', 4, 'admin', '查询用户列表', 'getUserList', '[]', 17, '127.0.0.1', '/user/list', '2023-04-08 14:42:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591506685743106', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 49, '127.0.0.1', '/sysLog/list', '2023-04-08 14:42:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591514101272578', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"2\"}]', 54, '127.0.0.1', '/sysLog/list', '2023-04-08 14:42:27', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591537895559169', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 349, '127.0.0.1', '/menu/list', '2023-04-08 14:42:32', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591906373554177', 4, 'admin', '查询用户列表', 'getUserList', '[]', 17, '127.0.0.1', '/user/list', '2023-04-08 14:44:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1644591906474217473', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 48, '127.0.0.1', '/sysLog/list', '2023-04-08 14:44:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1644592008232226818', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 304, '127.0.0.1', '/menu/list', '2023-04-08 14:44:24', '1', 0);
INSERT INTO `sys_log` VALUES ('1644597943772155905', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 82, '127.0.0.1', '/user/login', '2023-04-08 15:08:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1644597959777619969', 4, 'admin', '查询用户列表', 'getUserList', '[]', 18, '127.0.0.1', '/user/list', '2023-04-08 15:08:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1644597960620675073', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 216, '127.0.0.1', '/sysLog/list', '2023-04-08 15:08:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1644597965209243650', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 450, '127.0.0.1', '/menu/list', '2023-04-08 15:08:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1644597993118142466', 2, 'admin', '菜单 - 更新', 'updateMenu', '[{\"children\":[],\"delFlag\":0,\"id\":\"1529139036333633537\",\"nameZh\":\"用户管理\",\"orderNum\":10,\"parentId\":\"1529138714366267394\",\"status\":2,\"type\":1,\"updateDate\":\"2023-04-08 15:08:11.26\",\"updateUserId\":\"1\",\"url\":\"/sys/UserManagement\"}]', 141, '127.0.0.1', '/menu/update', '2023-04-08 15:08:11', '1', 0);
INSERT INTO `sys_log` VALUES ('1644597995026550786', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 364, '127.0.0.1', '/menu/list', '2023-04-08 15:08:12', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604108149157889', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 498, '127.0.0.1', '/user/login', '2023-04-08 15:32:29', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604118702026753', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 479, '127.0.0.1', '/menu/list', '2023-04-08 15:32:32', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604123479339009', 4, 'admin', '查询用户列表', 'getUserList', '[]', 13, '127.0.0.1', '/user/list', '2023-04-08 15:32:33', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604124360142850', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 233, '127.0.0.1', '/sysLog/list', '2023-04-08 15:32:33', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604133566640130', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 399, '127.0.0.1', '/menu/list', '2023-04-08 15:32:35', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604244908634113', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 37, '127.0.0.1', '/user/login', '2023-04-08 15:33:02', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604246548606978', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 312, '127.0.0.1', '/menu/list', '2023-04-08 15:33:02', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604255658635266', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 346, '127.0.0.1', '/menu/list', '2023-04-08 15:33:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604257848061954', 4, 'admin', '查询用户列表', 'getUserList', '[]', 25, '127.0.0.1', '/user/list', '2023-04-08 15:33:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604257999056897', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 48, '127.0.0.1', '/sysLog/list', '2023-04-08 15:33:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604648497147905', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 41, '127.0.0.1', '/user/login', '2023-04-08 15:34:38', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604651558989825', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 297, '127.0.0.1', '/menu/list', '2023-04-08 15:34:39', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604692851912706', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 36, '127.0.0.1', '/user/login', '2023-04-08 15:34:49', '1', 0);
INSERT INTO `sys_log` VALUES ('1644604694575771649', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 325, '127.0.0.1', '/menu/list', '2023-04-08 15:34:49', '1', 0);
INSERT INTO `sys_log` VALUES ('1647787692917514241', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 691, '127.0.0.1', '/user/login', '2023-04-17 10:22:55', '1', 0);
INSERT INTO `sys_log` VALUES ('1647787719312269313', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 746, '127.0.0.1', '/menu/list', '2023-04-17 10:23:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1647787724836167681', 4, 'admin', '查询用户列表', 'getUserList', '[]', 101, '127.0.0.1', '/user/list', '2023-04-17 10:23:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1647787725322706946', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 223, '127.0.0.1', '/sysLog/list', '2023-04-17 10:23:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648237269277704193', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 367, '127.0.0.1', '/user/login', '2023-04-18 16:09:22', '1', 0);
INSERT INTO `sys_log` VALUES ('1648237738687356930', 4, 'admin', '查询用户列表', 'getUserList', '[]', 26, '127.0.0.1', '/user/list', '2023-04-18 16:11:14', '1', 0);
INSERT INTO `sys_log` VALUES ('1648237738687356931', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 91, '127.0.0.1', '/sysLog/list', '2023-04-18 16:11:14', '1', 0);
INSERT INTO `sys_log` VALUES ('1648238165474566146', 4, 'admin', '查询用户列表', 'getUserList', '[]', 44, '127.0.0.1', '/user/list', '2023-04-18 16:12:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648238165541675010', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 70, '127.0.0.1', '/sysLog/list', '2023-04-18 16:12:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648238963101216769', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 119, '127.0.0.1', '/sysLog/list', '2023-04-18 16:16:06', '1', 0);
INSERT INTO `sys_log` VALUES ('1648239007695056897', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 65, '127.0.0.1', '/user/login', '2023-04-18 16:16:17', '1', 0);
INSERT INTO `sys_log` VALUES ('1648239018604441602', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 436, '127.0.0.1', '/menu/list', '2023-04-18 16:16:19', '1', 0);
INSERT INTO `sys_log` VALUES ('1648239844542590977', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 36, '127.0.0.1', '/user/login', '2023-04-18 16:19:36', '1', 0);
INSERT INTO `sys_log` VALUES ('1648239878063468545', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2631, '127.0.0.1', '/menu/list', '2023-04-18 16:19:44', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240031897956354', 2, 'admin', '菜单 - 保存', 'saveMenu', '[{\"children\":[],\"createDate\":\"2023-04-18 16:20:20.964\",\"createUserId\":\"1\",\"delFlag\":0,\"id\":\"1648240031407222785\",\"nameZh\":\"角色管理\",\"orderNum\":4,\"parentId\":\"1529138714366267394\",\"status\":1,\"type\":1,\"updateDate\":\"2023-04-18 16:20:20.964\",\"updateUserId\":\"1\",\"url\":\"/sys/role\"}]', 1355, '127.0.0.1', '/menu/add', '2023-04-18 16:20:21', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240035253399554', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 391, '127.0.0.1', '/menu/list', '2023-04-18 16:20:22', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240040924098562', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 383, '127.0.0.1', '/menu/list', '2023-04-18 16:20:23', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240194985078785', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 46, '127.0.0.1', '/user/login', '2023-04-18 16:21:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240212525658114', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 366, '127.0.0.1', '/menu/list', '2023-04-18 16:21:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240222118031362', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 385, '127.0.0.1', '/menu/list', '2023-04-18 16:21:06', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240258117742593', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 142, '127.0.0.1', '/user/login', '2023-04-18 16:21:15', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240271392714754', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 840, '127.0.0.1', '/menu/list', '2023-04-18 16:21:18', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240290401300482', 4, 'admin', '查询用户列表', 'getUserList', '[]', 337, '127.0.0.1', '/user/list', '2023-04-18 16:21:23', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240290690707458', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 419, '127.0.0.1', '/sysLog/list', '2023-04-18 16:21:23', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240297338679297', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 439, '127.0.0.1', '/menu/list', '2023-04-18 16:21:24', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240357103316993', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 42, '127.0.0.1', '/user/login', '2023-04-18 16:21:39', '1', 0);
INSERT INTO `sys_log` VALUES ('1648240654273949698', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 1192, '127.0.0.1', '/user/login', '2023-04-18 16:22:49', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241151168950273', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 37, '127.0.0.1', '/user/login', '2023-04-18 16:24:48', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241256097853441', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1023, '127.0.0.1', '/menu/list', '2023-04-18 16:25:13', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241435630841857', 4, 'admin', '查询用户列表', 'getUserList', '[]', 19, '127.0.0.1', '/user/list', '2023-04-18 16:25:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241435630841858', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 34, '127.0.0.1', '/sysLog/list', '2023-04-18 16:25:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241436872355842', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1526, '127.0.0.1', '/menu/list', '2023-04-18 16:25:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241747636727810', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2583, '127.0.0.1', '/menu/list', '2023-04-18 16:27:10', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241777735053313', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 375, '127.0.0.1', '/menu/list', '2023-04-18 16:27:17', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241808424775681', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 1371, '127.0.0.1', '/menu/list', '2023-04-18 16:27:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241813114007553', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 565, '127.0.0.1', '/menu/list', '2023-04-18 16:27:26', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241934828515330', 2, 'admin', '菜单 - 更新', 'updateMenu', '[{\"children\":[],\"delFlag\":0,\"id\":\"1648240031407222785\",\"nameZh\":\"角色管理\",\"orderNum\":4,\"parentId\":\"1529138714366267394\",\"status\":1,\"type\":1,\"updateDate\":\"2023-04-18 16:27:54.539\",\"updateUserId\":\"1\",\"url\":\"/sys/RoleManagement\"}]', 362, '127.0.0.1', '/menu/update', '2023-04-18 16:27:55', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241938452393986', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 413, '127.0.0.1', '/menu/list', '2023-04-18 16:27:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241955103780865', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 303, '127.0.0.1', '/menu/list', '2023-04-18 16:28:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1648241960237608961', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 319, '127.0.0.1', '/menu/list', '2023-04-18 16:28:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648242045889490946', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 391, '127.0.0.1', '/menu/list', '2023-04-18 16:28:21', '1', 0);
INSERT INTO `sys_log` VALUES ('1648242057646125057', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1584, '127.0.0.1', '/menu/list', '2023-04-18 16:28:24', '1', 0);
INSERT INTO `sys_log` VALUES ('1648242061479718914', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 852, '127.0.0.1', '/menu/list', '2023-04-18 16:28:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648242061555216386', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 26, '127.0.0.1', '/sysLog/list', '2023-04-18 16:28:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648242062847062017', 4, 'admin', '查询用户列表', 'getUserList', '[]', 33, '127.0.0.1', '/user/list', '2023-04-18 16:28:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648242424928743425', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 409, '127.0.0.1', '/menu/list', '2023-04-18 16:29:52', '1', 0);
INSERT INTO `sys_log` VALUES ('1648243068578246658', 4, 'admin', '查询用户列表', 'getUserList', '[]', 18, '127.0.0.1', '/user/list', '2023-04-18 16:32:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648243068641161217', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 36, '127.0.0.1', '/sysLog/list', '2023-04-18 16:32:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648243075830198273', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 698, '127.0.0.1', '/menu/list', '2023-04-18 16:32:27', '1', 0);
INSERT INTO `sys_log` VALUES ('1648243807652360193', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 270, '127.0.0.1', '/menu/list', '2023-04-18 16:35:21', '1', 0);
INSERT INTO `sys_log` VALUES ('1648243811813109762', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 417, '127.0.0.1', '/menu/list', '2023-04-18 16:35:22', '1', 0);
INSERT INTO `sys_log` VALUES ('1648244265762631682', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1138, '127.0.0.1', '/menu/list', '2023-04-18 16:37:11', '1', 0);
INSERT INTO `sys_log` VALUES ('1648244549452771329', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2353, '127.0.0.1', '/menu/list', '2023-04-18 16:38:18', '1', 0);
INSERT INTO `sys_log` VALUES ('1648245388191936513', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 3071, '127.0.0.1', '/menu/list', '2023-04-18 16:41:38', '1', 0);
INSERT INTO `sys_log` VALUES ('1648246128046190593', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 602, '127.0.0.1', '/menu/list', '2023-04-18 16:44:35', '1', 0);
INSERT INTO `sys_log` VALUES ('1648247087698751489', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 390, '127.0.0.1', '/menu/list', '2023-04-18 16:48:23', '1', 0);
INSERT INTO `sys_log` VALUES ('1648247781780566017', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 418, '127.0.0.1', '/menu/list', '2023-04-18 16:51:09', '1', 0);
INSERT INTO `sys_log` VALUES ('1648249861211631617', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2176, '127.0.0.1', '/menu/list', '2023-04-18 16:59:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648249983601422337', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 363, '127.0.0.1', '/menu/list', '2023-04-18 16:59:54', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254047135412226', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 321, '127.0.0.1', '/menu/list', '2023-04-18 17:16:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254051698814978', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 272, '127.0.0.1', '/menu/list', '2023-04-18 17:16:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254054362198018', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 309, '127.0.0.1', '/menu/list', '2023-04-18 17:16:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254301184405506', 2, 'admin', '菜单 - 保存', 'saveMenu', '[{\"children\":[],\"createDate\":\"2023-04-18 17:17:03.08\",\"createUserId\":\"1\",\"delFlag\":0,\"id\":\"1648254300911775745\",\"nameZh\":\"角色管理\",\"orderNum\":0,\"parentId\":\"1529138714366267394\",\"path\":\"/sys/RoleManagement.vue\",\"status\":1,\"type\":1,\"updateDate\":\"2023-04-18 17:17:03.08\",\"updateUserId\":\"1\",\"url\":\"/sys/RoleManagement\"}]', 82, '127.0.0.1', '/menu/add', '2023-04-18 17:17:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254303000539138', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 348, '127.0.0.1', '/menu/list', '2023-04-18 17:17:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254310873247746', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 323, '127.0.0.1', '/menu/list', '2023-04-18 17:17:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254313318526977', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 351, '127.0.0.1', '/menu/list', '2023-04-18 17:17:06', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254332985618433', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1250, '127.0.0.1', '/menu/list', '2023-04-18 17:17:11', '1', 0);
INSERT INTO `sys_log` VALUES ('1648254355169292289', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 345, '127.0.0.1', '/menu/list', '2023-04-18 17:17:16', '1', 0);
INSERT INTO `sys_log` VALUES ('1648255766976212993', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 347, '127.0.0.1', '/menu/list', '2023-04-18 17:22:53', '1', 0);
INSERT INTO `sys_log` VALUES ('1648348965640364033', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 413, '127.0.0.1', '/user/login', '2023-04-18 23:33:13', '1', 0);
INSERT INTO `sys_log` VALUES ('1648348984435040257', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 503, '127.0.0.1', '/menu/list', '2023-04-18 23:33:17', '1', 0);
INSERT INTO `sys_log` VALUES ('1648348988402851841', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 425, '127.0.0.1', '/menu/list', '2023-04-18 23:33:18', '1', 0);
INSERT INTO `sys_log` VALUES ('1648348991762489346', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 455, '127.0.0.1', '/menu/list', '2023-04-18 23:33:19', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494117922717698', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 390, '127.0.0.1', '/user/login', '2023-04-19 09:10:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494128865656834', 4, 'admin', '查询用户列表', 'getUserList', '[]', 113, '127.0.0.1', '/user/list', '2023-04-19 09:10:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494131155746818', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 665, '127.0.0.1', '/sysLog/list', '2023-04-19 09:10:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494141796696066', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 382, '127.0.0.1', '/menu/list', '2023-04-19 09:10:06', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494197648048130', 2, 'admin', '菜单 - 更新', 'updateMenu', '[{\"children\":[],\"delFlag\":0,\"id\":\"1648254300911775745\",\"name\":\"RoleManagement\",\"nameZh\":\"角色管理\",\"orderNum\":0,\"parentId\":\"1529138714366267394\",\"path\":\"/sys/RoleManagement.vue\",\"status\":1,\"type\":1,\"updateDate\":\"2023-04-19 09:10:18.838\",\"updateUserId\":\"1\",\"url\":\"/sys/RoleManagement\"}]', 115, '127.0.0.1', '/menu/update', '2023-04-19 09:10:19', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494201007685633', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 713, '127.0.0.1', '/menu/list', '2023-04-19 09:10:20', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494325511405570', 2, 'admin', '菜单 - 更新', 'updateMenu', '[{\"children\":[],\"delFlag\":0,\"id\":\"1648254300911775745\",\"name\":\"RoleManagement\",\"nameZh\":\"角色管理\",\"orderNum\":40,\"parentId\":\"1529138714366267394\",\"path\":\"/sys/RoleManagement.vue\",\"status\":1,\"type\":1,\"updateDate\":\"2023-04-19 09:10:49.107\",\"updateUserId\":\"1\",\"url\":\"/sys/RoleManagement\"}]', 334, '127.0.0.1', '/menu/update', '2023-04-19 09:10:49', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494327822467074', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 418, '127.0.0.1', '/menu/list', '2023-04-19 09:10:50', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494345706979330', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 314, '127.0.0.1', '/menu/list', '2023-04-19 09:10:54', '1', 0);
INSERT INTO `sys_log` VALUES ('1648494349469270018', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 560, '127.0.0.1', '/menu/list', '2023-04-19 09:10:55', '1', 0);
INSERT INTO `sys_log` VALUES ('1648498204714123265', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 617, '127.0.0.1', '/menu/list', '2023-04-19 09:26:14', '1', 0);
INSERT INTO `sys_log` VALUES ('1648498899068567554', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 507, '127.0.0.1', '/menu/list', '2023-04-19 09:29:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1648499185774411778', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 543, '127.0.0.1', '/menu/list', '2023-04-19 09:30:08', '1', 0);
INSERT INTO `sys_log` VALUES ('1648499205206622210', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 357, '127.0.0.1', '/menu/list', '2023-04-19 09:30:13', '1', 0);
INSERT INTO `sys_log` VALUES ('1648499229646831618', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 654, '127.0.0.1', '/menu/list', '2023-04-19 09:30:19', '1', 0);
INSERT INTO `sys_log` VALUES ('1648499301121966081', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 336, '127.0.0.1', '/menu/list', '2023-04-19 09:30:36', '1', 0);
INSERT INTO `sys_log` VALUES ('1648499451403878401', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 489, '127.0.0.1', '/menu/list', '2023-04-19 09:31:12', '1', 0);
INSERT INTO `sys_log` VALUES ('1648500613549047809', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 335, '127.0.0.1', '/menu/list', '2023-04-19 09:35:49', '1', 0);
INSERT INTO `sys_log` VALUES ('1648502492324610049', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 371, '127.0.0.1', '/menu/list', '2023-04-19 09:43:17', '1', 0);
INSERT INTO `sys_log` VALUES ('1648504400502243330', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 600, '127.0.0.1', '/menu/list', '2023-04-19 09:50:51', '1', 0);
INSERT INTO `sys_log` VALUES ('1648506466914189313', 2, 'admin', '菜单 - 更新', 'updateMenu', '[{\"children\":[],\"delFlag\":0,\"icon\":\"el-icon-user\",\"id\":\"1648254300911775745\",\"name\":\"RoleManagement\",\"nameZh\":\"角色管理\",\"orderNum\":40,\"parentId\":\"1529138714366267394\",\"path\":\"/sys/RoleManagement.vue\",\"status\":1,\"type\":1,\"updateDate\":\"2023-04-19 09:59:03.671\",\"updateUserId\":\"1\",\"url\":\"/sys/RoleManagement\"}]', 896, '127.0.0.1', '/menu/update', '2023-04-19 09:59:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1648506472014462978', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 631, '127.0.0.1', '/menu/list', '2023-04-19 09:59:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1648513942346711041', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2827, '127.0.0.1', '/menu/list', '2023-04-19 10:28:46', '1', 0);
INSERT INTO `sys_log` VALUES ('1648518534874710017', 4, 'admin', '查询用户列表', 'getUserList', '[]', 21, '127.0.0.1', '/user/list', '2023-04-19 10:47:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648518537152217090', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 331, '127.0.0.1', '/sysLog/list', '2023-04-19 10:47:02', '1', 0);
INSERT INTO `sys_log` VALUES ('1648518986718691330', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1967, '127.0.0.1', '/menu/list', '2023-04-19 10:48:49', '1', 0);
INSERT INTO `sys_log` VALUES ('1648518990271266818', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 385, '127.0.0.1', '/menu/list', '2023-04-19 10:48:50', '1', 0);
INSERT INTO `sys_log` VALUES ('1648518993920311298', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 458, '127.0.0.1', '/menu/list', '2023-04-19 10:48:51', '1', 0);
INSERT INTO `sys_log` VALUES ('1648519038103109633', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1372, '127.0.0.1', '/menu/list', '2023-04-19 10:49:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648519684105617409', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 52, '127.0.0.1', '/sysLog/list', '2023-04-19 10:51:35', '1', 0);
INSERT INTO `sys_log` VALUES ('1648519685229690882', 4, 'admin', '查询用户列表', 'getUserList', '[]', 29, '127.0.0.1', '/user/list', '2023-04-19 10:51:36', '1', 0);
INSERT INTO `sys_log` VALUES ('1648519691101716481', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 413, '127.0.0.1', '/menu/list', '2023-04-19 10:51:37', '1', 0);
INSERT INTO `sys_log` VALUES ('1648519701914632194', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1586, '127.0.0.1', '/menu/list', '2023-04-19 10:51:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1648520003787079681', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1908, '127.0.0.1', '/menu/list', '2023-04-19 10:52:52', '1', 0);
INSERT INTO `sys_log` VALUES ('1648520818404802562', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1512, '127.0.0.1', '/menu/list', '2023-04-19 10:56:06', '1', 0);
INSERT INTO `sys_log` VALUES ('1648521030808551426', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1196, '127.0.0.1', '/menu/list', '2023-04-19 10:56:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648521061162729473', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1250, '127.0.0.1', '/menu/list', '2023-04-19 10:57:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1648521092615815170', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1021, '127.0.0.1', '/menu/list', '2023-04-19 10:57:11', '1', 0);
INSERT INTO `sys_log` VALUES ('1648521113604112386', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1281, '127.0.0.1', '/menu/list', '2023-04-19 10:57:16', '1', 0);
INSERT INTO `sys_log` VALUES ('1648521658184155138', 4, 'admin', '查询用户列表', 'getUserList', '[]', 64, '127.0.0.1', '/user/list', '2023-04-19 10:59:26', '1', 0);
INSERT INTO `sys_log` VALUES ('1648521659094319105', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 283, '127.0.0.1', '/sysLog/list', '2023-04-19 10:59:26', '1', 0);
INSERT INTO `sys_log` VALUES ('1648521686361489410', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 400, '127.0.0.1', '/menu/list', '2023-04-19 10:59:33', '1', 0);
INSERT INTO `sys_log` VALUES ('1648521728300335105', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1268, '127.0.0.1', '/menu/list', '2023-04-19 10:59:43', '1', 0);
INSERT INTO `sys_log` VALUES ('1648522049349140482', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 467, '127.0.0.1', '/menu/list', '2023-04-19 11:00:59', '1', 0);
INSERT INTO `sys_log` VALUES ('1648522133625290753', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 458, '127.0.0.1', '/menu/list', '2023-04-19 11:01:19', '1', 0);
INSERT INTO `sys_log` VALUES ('1648522707754205185', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 390, '127.0.0.1', '/menu/list', '2023-04-19 11:03:36', '1', 0);
INSERT INTO `sys_log` VALUES ('1648522736715874305', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 374, '127.0.0.1', '/menu/list', '2023-04-19 11:03:43', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523351265296385', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2253, '127.0.0.1', '/menu/list', '2023-04-19 11:06:10', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523557943820290', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1915, '127.0.0.1', '/menu/list', '2023-04-19 11:06:59', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523617138032642', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 539, '127.0.0.1', '/menu/list', '2023-04-19 11:07:13', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523637593653250', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 711, '127.0.0.1', '/menu/list', '2023-04-19 11:07:18', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523672754503682', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 413, '127.0.0.1', '/menu/list', '2023-04-19 11:07:26', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523718162038785', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1268, '127.0.0.1', '/menu/list', '2023-04-19 11:07:37', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523729369219073', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 47, '127.0.0.1', '/sysLog/list', '2023-04-19 11:07:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523730480709633', 4, 'admin', '查询用户列表', 'getUserList', '[]', 18, '127.0.0.1', '/user/list', '2023-04-19 11:07:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1648523745378877442', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 392, '127.0.0.1', '/menu/list', '2023-04-19 11:07:44', '1', 0);
INSERT INTO `sys_log` VALUES ('1648524094231724033', 4, 'admin', '查询用户列表', 'getUserList', '[]', 20, '127.0.0.1', '/user/list', '2023-04-19 11:09:07', '1', 0);
INSERT INTO `sys_log` VALUES ('1648524094231724034', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 36, '127.0.0.1', '/sysLog/list', '2023-04-19 11:09:07', '1', 0);
INSERT INTO `sys_log` VALUES ('1648524817799495681', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 44, '127.0.0.1', '/user/login', '2023-04-19 11:11:59', '1', 0);
INSERT INTO `sys_log` VALUES ('1648524825550569474', 4, 'admin', '查询用户列表', 'getUserList', '[]', 22, '127.0.0.1', '/user/list', '2023-04-19 11:12:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648524825613484033', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 45, '127.0.0.1', '/sysLog/list', '2023-04-19 11:12:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648525218829484034', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 434, '127.0.0.1', '/menu/list', '2023-04-19 11:13:35', '1', 0);
INSERT INTO `sys_log` VALUES ('1648525681930977281', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 345, '127.0.0.1', '/sysLog/list', '2023-04-19 11:15:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648525682140692481', 4, 'admin', '查询用户列表', 'getUserList', '[]', 19, '127.0.0.1', '/user/list', '2023-04-19 11:15:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648525694652301314', 4, 'admin', '查询用户列表', 'getUserList', '[]', 31, '127.0.0.1', '/user/list', '2023-04-19 11:15:28', '1', 0);
INSERT INTO `sys_log` VALUES ('1648525694723604482', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 51, '127.0.0.1', '/sysLog/list', '2023-04-19 11:15:28', '1', 0);
INSERT INTO `sys_log` VALUES ('1648525733877432321', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2554, '127.0.0.1', '/menu/list', '2023-04-19 11:15:38', '1', 0);
INSERT INTO `sys_log` VALUES ('1648525790915772417', 4, 'admin', '查询用户列表', 'getUserList', '[]', 17, '127.0.0.1', '/user/list', '2023-04-19 11:15:51', '1', 0);
INSERT INTO `sys_log` VALUES ('1648525791054184449', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 50, '127.0.0.1', '/sysLog/list', '2023-04-19 11:15:51', '1', 0);
INSERT INTO `sys_log` VALUES ('1648526115450044417', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 548, '127.0.0.1', '/menu/list', '2023-04-19 11:17:09', '1', 0);
INSERT INTO `sys_log` VALUES ('1648526366273617922', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 49, '127.0.0.1', '/sysLog/list', '2023-04-19 11:18:09', '1', 0);
INSERT INTO `sys_log` VALUES ('1648526367515131905', 4, 'admin', '查询用户列表', 'getUserList', '[]', 22, '127.0.0.1', '/user/list', '2023-04-19 11:18:09', '1', 0);
INSERT INTO `sys_log` VALUES ('1648526613091631105', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 912, '127.0.0.1', '/menu/list', '2023-04-19 11:19:07', '1', 0);
INSERT INTO `sys_log` VALUES ('1648526749960159234', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 39, '127.0.0.1', '/sysLog/list', '2023-04-19 11:19:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1648526751226839041', 4, 'admin', '查询用户列表', 'getUserList', '[]', 21, '127.0.0.1', '/user/list', '2023-04-19 11:19:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1648526912619462658', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 417, '127.0.0.1', '/menu/list', '2023-04-19 11:20:19', '1', 0);
INSERT INTO `sys_log` VALUES ('1648559940519510017', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 30, '127.0.0.1', '/user/login', '2023-04-19 13:31:33', '1', 0);
INSERT INTO `sys_log` VALUES ('1648559966805213185', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 53, '127.0.0.1', '/sysLog/list', '2023-04-19 13:31:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1648559967849594882', 4, 'admin', '查询用户列表', 'getUserList', '[]', 16, '127.0.0.1', '/user/list', '2023-04-19 13:31:40', '1', 0);
INSERT INTO `sys_log` VALUES ('1648559993573261313', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 3994, '127.0.0.1', '/menu/list', '2023-04-19 13:31:46', '1', 0);
INSERT INTO `sys_log` VALUES ('1648560659087671298', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2469, '127.0.0.1', '/menu/list', '2023-04-19 13:34:25', '1', 0);
INSERT INTO `sys_log` VALUES ('1648561333959573505', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2045, '127.0.0.1', '/menu/list', '2023-04-19 13:37:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1648562379326930945', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 37, '127.0.0.1', '/sysLog/list', '2023-04-19 13:41:15', '1', 0);
INSERT INTO `sys_log` VALUES ('1648562379389845505', 4, 'admin', '查询用户列表', 'getUserList', '[]', 25, '127.0.0.1', '/user/list', '2023-04-19 13:41:15', '1', 0);
INSERT INTO `sys_log` VALUES ('1648562469030510594', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1861, '127.0.0.1', '/menu/list', '2023-04-19 13:41:36', '1', 0);
INSERT INTO `sys_log` VALUES ('1648565529819660290', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 29, '127.0.0.1', '/user/login', '2023-04-19 13:53:46', '1', 0);
INSERT INTO `sys_log` VALUES ('1648566303714885634', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 28, '127.0.0.1', '/user/login', '2023-04-19 13:56:50', '1', 0);
INSERT INTO `sys_log` VALUES ('1648566346526146561', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 29, '127.0.0.1', '/user/login', '2023-04-19 13:57:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648566371134128130', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 584, '127.0.0.1', '/menu/list', '2023-04-19 13:57:06', '1', 0);
INSERT INTO `sys_log` VALUES ('1648566913793179650', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 507, '127.0.0.1', '/user/login', '2023-04-19 13:59:16', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569367440048129', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 132, '127.0.0.1', '/user/login', '2023-04-19 14:09:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569377246330881', 4, 'admin', '查询用户列表', 'getUserList', '[]', 18, '127.0.0.1', '/user/list', '2023-04-19 14:09:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569377317634050', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 34, '127.0.0.1', '/sysLog/list', '2023-04-19 14:09:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569381440634881', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 358, '127.0.0.1', '/menu/list', '2023-04-19 14:09:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569424981704706', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 426, '127.0.0.1', '/menu/list', '2023-04-19 14:09:15', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569743199354882', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 462, '127.0.0.1', '/menu/list', '2023-04-19 14:10:30', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569887353389058', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 77, '127.0.0.1', '/user/login', '2023-04-19 14:11:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569905808326657', 4, 'admin', '查询用户列表', 'getUserList', '[]', 9, '127.0.0.1', '/user/list', '2023-04-19 14:11:09', '1', 0);
INSERT INTO `sys_log` VALUES ('1648569905946738689', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 43, '127.0.0.1', '/sysLog/list', '2023-04-19 14:11:09', '1', 0);
INSERT INTO `sys_log` VALUES ('1648570348030574594', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 41, '127.0.0.1', '/user/login', '2023-04-19 14:12:55', '1', 0);
INSERT INTO `sys_log` VALUES ('1648570753561051137', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 36, '127.0.0.1', '/user/login', '2023-04-19 14:14:31', '1', 0);
INSERT INTO `sys_log` VALUES ('1648570824268627969', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 458, '127.0.0.1', '/user/login', '2023-04-19 14:14:48', '1', 0);
INSERT INTO `sys_log` VALUES ('1648570945706311681', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 37, '127.0.0.1', '/user/login', '2023-04-19 14:15:17', '1', 0);
INSERT INTO `sys_log` VALUES ('1648571327908069378', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 39, '127.0.0.1', '/user/login', '2023-04-19 14:16:48', '1', 0);
INSERT INTO `sys_log` VALUES ('1648573391358836738', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 35, '127.0.0.1', '/user/login', '2023-04-19 14:25:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1648573473059684353', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 37, '127.0.0.1', '/user/login', '2023-04-19 14:25:20', '1', 0);
INSERT INTO `sys_log` VALUES ('1648573623807164418', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 35, '127.0.0.1', '/user/login', '2023-04-19 14:25:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648573730518646786', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 34, '127.0.0.1', '/user/login', '2023-04-19 14:26:21', '1', 0);
INSERT INTO `sys_log` VALUES ('1648574019107733505', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 39, '127.0.0.1', '/user/login', '2023-04-19 14:27:30', '1', 0);
INSERT INTO `sys_log` VALUES ('1648574407198294017', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 36, '127.0.0.1', '/user/login', '2023-04-19 14:29:02', '1', 0);
INSERT INTO `sys_log` VALUES ('1648574894823882754', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 165, '127.0.0.1', '/user/login', '2023-04-19 14:30:59', '1', 0);
INSERT INTO `sys_log` VALUES ('1648574921172500481', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 45, '127.0.0.1', '/user/login', '2023-04-19 14:31:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1648574961605591041', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 37, '127.0.0.1', '/user/login', '2023-04-19 14:31:15', '1', 0);
INSERT INTO `sys_log` VALUES ('1648574972414312450', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 380, '127.0.0.1', '/menu/list', '2023-04-19 14:31:17', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575031239426050', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 412, '127.0.0.1', '/menu/list', '2023-04-19 14:31:31', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575035878326273', 4, 'admin', '查询用户列表', 'getUserList', '[]', 21, '127.0.0.1', '/user/list', '2023-04-19 14:31:32', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575035941240833', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 45, '127.0.0.1', '/sysLog/list', '2023-04-19 14:31:32', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575126450126850', 4, 'admin', '查询用户列表', 'getUserList', '[]', 18, '127.0.0.1', '/user/list', '2023-04-19 14:31:54', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575126513041409', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 38, '127.0.0.1', '/sysLog/list', '2023-04-19 14:31:54', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575157521530882', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 366, '127.0.0.1', '/menu/list', '2023-04-19 14:32:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575260965650434', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 2080, '127.0.0.1', '/menu/list', '2023-04-19 14:32:26', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575579355267074', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 413, '127.0.0.1', '/menu/list', '2023-04-19 14:33:42', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575592588296193', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 893, '127.0.0.1', '/menu/list', '2023-04-19 14:33:45', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575843936157698', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 37, '127.0.0.1', '/user/login', '2023-04-19 14:34:45', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575867420065793', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 620, '127.0.0.1', '/menu/list', '2023-04-19 14:34:51', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575951872376833', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 23, '127.0.0.1', '/user/login', '2023-04-19 14:35:11', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575961783517186', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 44, '127.0.0.1', '/sysLog/list', '2023-04-19 14:35:13', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575962915979266', 4, 'admin', '查询用户列表', 'getUserList', '[]', 13, '127.0.0.1', '/user/list', '2023-04-19 14:35:13', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575989201682434', 4, 'admin', '查询用户列表', 'getUserList', '[]', 302, '127.0.0.1', '/user/list', '2023-04-19 14:35:20', '1', 0);
INSERT INTO `sys_log` VALUES ('1648575989398814722', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 348, '127.0.0.1', '/sysLog/list', '2023-04-19 14:35:20', '1', 0);
INSERT INTO `sys_log` VALUES ('1648576169774858241', 4, 'admin', '查询用户列表', 'getUserList', '[]', 20, '127.0.0.1', '/user/list', '2023-04-19 14:36:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648576169900687362', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 41, '127.0.0.1', '/sysLog/list', '2023-04-19 14:36:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648587970935701506', 4, 'admin', '查询用户列表', 'getUserList', '[]', 462, '127.0.0.1', '/user/list', '2023-04-19 15:22:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648587971065724930', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 484, '127.0.0.1', '/sysLog/list', '2023-04-19 15:22:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648702103304134658', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 345, '127.0.0.1', '/user/login', '2023-04-19 22:56:27', '1', 0);
INSERT INTO `sys_log` VALUES ('1648702116763656193', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 411, '127.0.0.1', '/menu/list', '2023-04-19 22:56:31', '1', 0);
INSERT INTO `sys_log` VALUES ('1648702118332325890', 4, 'admin', '系统日志 - 分页查询', 'list', '[{\"limit\":\"10\",\"page\":\"1\"}]', 167, '127.0.0.1', '/sysLog/list', '2023-04-19 22:56:31', '1', 0);
INSERT INTO `sys_log` VALUES ('1648702119053746178', 4, 'admin', '查询用户列表', 'getUserList', '[]', 19, '127.0.0.1', '/user/list', '2023-04-19 22:56:31', '1', 0);
INSERT INTO `sys_log` VALUES ('1648702584260780033', 4, 'admin', '查询用户列表', 'getUserList', '[]', 18, '127.0.0.1', '/user/list', '2023-04-19 22:58:22', '1', 0);
INSERT INTO `sys_log` VALUES ('1648710038457495554', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 306, '127.0.0.1', '/user/login', '2023-04-19 23:27:59', '1', 0);
INSERT INTO `sys_log` VALUES ('1648710054223884289', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 414, '127.0.0.1', '/menu/list', '2023-04-19 23:28:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648710058669846529', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 281, '127.0.0.1', '/menu/list', '2023-04-19 23:28:04', '1', 0);
INSERT INTO `sys_log` VALUES ('1648710061408727041', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 294, '127.0.0.1', '/menu/list', '2023-04-19 23:28:05', '1', 0);
INSERT INTO `sys_log` VALUES ('1648710076529192962', 4, 'admin', '查询用户列表', 'getUserList', '[]', 15, '127.0.0.1', '/user/list', '2023-04-19 23:28:08', '1', 0);
INSERT INTO `sys_log` VALUES ('1648710076722130945', 4, 'admin', '系统日志 - 分页查询', 'pageList', '[{\"limit\":\"10\",\"page\":\"1\"}]', 63, '127.0.0.1', '/sysLog/pageList', '2023-04-19 23:28:09', '1', 0);
INSERT INTO `sys_log` VALUES ('1648710083202330626', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 289, '127.0.0.1', '/menu/list', '2023-04-19 23:28:10', '1', 0);
INSERT INTO `sys_log` VALUES ('1648710543837573121', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 313, '127.0.0.1', '/menu/list', '2023-04-19 23:30:00', '1', 0);
INSERT INTO `sys_log` VALUES ('1648717093113233410', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 536, '127.0.0.1', '/menu/list', '2023-04-19 23:56:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648717101166297089', 4, 'admin', '查询用户列表', 'getUserList', '[]', 28, '127.0.0.1', '/user/list', '2023-04-19 23:56:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648717101451509762', 4, 'admin', '系统日志 - 分页查询', 'pageList', '[{\"size\":\"10\",\"page\":\"1\"}]', 72, '127.0.0.1', '/sysLog/pageList', '2023-04-19 23:56:03', '1', 0);
INSERT INTO `sys_log` VALUES ('1648717131201708034', 4, 'admin', '系统日志 - 分页查询', 'pageList', '[{\"size\":\"20\",\"page\":\"1\"}]', 71, '127.0.0.1', '/sysLog/pageList', '2023-04-19 23:56:10', '1', 0);
INSERT INTO `sys_log` VALUES ('1648717167243362305', 4, 'admin', '系统日志 - 分页查询', 'pageList', '[{\"size\":\"20\",\"page\":\"4\"}]', 74, '127.0.0.1', '/sysLog/pageList', '2023-04-19 23:56:19', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848458944831489', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 2391, '127.0.0.1', '/user/login', '2023-04-20 08:38:01', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848485503164417', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1353, '127.0.0.1', '/menu/list', '2023-04-20 08:38:08', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848500166451201', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 1723, '127.0.0.1', '/menu/list', '2023-04-20 08:38:11', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848520588517377', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 4479, '127.0.0.1', '/menu/list', '2023-04-20 08:38:16', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848678659252225', 2, 'admin', '菜单 - 更新', 'updateMenu', '[{\"children\":[],\"delFlag\":0,\"icon\":\"el-icon-user\",\"id\":\"1529139036333633537\",\"name\":\"UserManagement\",\"nameZh\":\"用户管理\",\"orderNum\":10,\"parentId\":\"1529138714366267394\",\"path\":\"/sys/UserManagement.vue\",\"status\":1,\"type\":1,\"updateDate\":\"2023-04-20 08:38:51.726\",\"updateUserId\":\"1\",\"url\":\"/sys/UserManagement\"}]', 3973, '127.0.0.1', '/menu/update', '2023-04-20 08:38:54', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848686531960834', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1511, '127.0.0.1', '/menu/list', '2023-04-20 08:38:56', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848786238955521', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 1150, '127.0.0.1', '/user/login', '2023-04-20 08:39:19', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848891801198593', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 38, '127.0.0.1', '/user/login', '2023-04-20 08:39:45', '1', 0);
INSERT INTO `sys_log` VALUES ('1648848936193712130', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 5343, '127.0.0.1', '/menu/list', '2023-04-20 08:39:55', '1', 0);
INSERT INTO `sys_log` VALUES ('1648849057748836353', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1867, '127.0.0.1', '/menu/list', '2023-04-20 08:40:24', '1', 0);
INSERT INTO `sys_log` VALUES ('1648849102195875841', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 332, '127.0.0.1', '/menu/list', '2023-04-20 08:40:35', '1', 0);
INSERT INTO `sys_log` VALUES ('1648849107363258369', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1082, '127.0.0.1', '/menu/list', '2023-04-20 08:40:36', '1', 0);
INSERT INTO `sys_log` VALUES ('1648849152615604225', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 32, '127.0.0.1', '/user/login', '2023-04-20 08:40:47', '1', 0);
INSERT INTO `sys_log` VALUES ('1648849156998651905', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 752, '127.0.0.1', '/menu/list', '2023-04-20 08:40:48', '1', 0);
INSERT INTO `sys_log` VALUES ('1648850977691492354', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 693, '127.0.0.1', '/menu/list', '2023-04-20 08:48:02', '1', 0);
INSERT INTO `sys_log` VALUES ('1648851018351075330', 2, 'admin', '菜单 - 更新', 'updateMenu', '[{\"children\":[],\"delFlag\":0,\"icon\":\"el-icon-user\",\"id\":\"1529139036333633537\",\"name\":\"UserManagement\",\"nameZh\":\"用户管理\",\"orderNum\":10,\"parentId\":\"1529138714366267394\",\"path\":\"/sys/UserManagement.vue\",\"status\":2,\"type\":1,\"updateDate\":\"2023-04-20 08:48:11.253\",\"updateUserId\":\"1\",\"url\":\"/sys/UserManagement\"}]', 385, '127.0.0.1', '/menu/update', '2023-04-20 08:48:12', '1', 0);
INSERT INTO `sys_log` VALUES ('1648851022394384385', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 852, '127.0.0.1', '/menu/list', '2023-04-20 08:48:13', '1', 0);
INSERT INTO `sys_log` VALUES ('1648851196407668738', 5, 'admin', '用户登录', 'login', '{\"delFlag\":0,\"username\":\"admin\"}', 71, '127.0.0.1', '/user/login', '2023-04-20 08:48:54', '1', 0);
INSERT INTO `sys_log` VALUES ('1648851200169959425', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2\"}]', 813, '127.0.0.1', '/menu/list', '2023-04-20 08:48:55', '1', 0);
INSERT INTO `sys_log` VALUES ('1648851245195812866', 4, 'admin', '菜单 - 查询', 'getMenuList', '[{\"id\":\"0\",\"status\":\"1,2,3\"}]', 1898, '127.0.0.1', '/menu/list', '2023-04-20 08:49:06', '1', 0);

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
  `type` int(3) NULL DEFAULT NULL COMMENT '类型 0：目录 1：一级菜单 2：二级菜单 3：按钮',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` int(3) NULL DEFAULT NULL COMMENT '是否隐藏，1-显示；2-隐藏；3-禁用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime NULL DEFAULT NULL COMMENT '新建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(3) NULL DEFAULT NULL COMMENT '删除标记',
  `del_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0', NULL, NULL, NULL, '根节点', NULL, NULL, NULL, NULL, NULL, NULL, '2022-05-01 10:46:11', '1', '2022-05-01 10:46:18', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529137618646228993', '0', '/tools', NULL, '实用工具', NULL, NULL, 0, 20, 1, NULL, '2022-05-25 00:29:53', '1', '2023-01-13 23:51:29', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529138229139853314', '0', '/index', '/HomePageView.vue', '首页', 'index', 'el-icon-s-home', 1, 10, 1, NULL, '2022-05-25 00:32:19', '1', '2023-03-03 14:36:52', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529138714366267394', '0', '/sys', NULL, '系统管理', NULL, NULL, 0, 50, 1, NULL, '2022-05-25 00:34:14', '1', '2022-05-25 00:34:14', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529139036333633537', '1529138714366267394', '/sys/UserManagement', '/sys/UserManagement.vue', '用户管理', 'UserManagement', 'el-icon-user', 1, 10, 2, NULL, '2022-05-25 00:35:31', '1', '2023-04-20 08:48:11', '1', 0, NULL);
INSERT INTO `sys_menu` VALUES ('1529139209180860417', '1529138714366267394', '/sys/MenuManagement', '/sys/MenuManagement.vue', '菜单管理', 'MenuManagement', 'el-icon-menu', 1, 20, 1, NULL, '2022-05-25 00:36:12', '1', '2022-05-25 00:36:12', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1556910085169508354', '1529138714366267394', '/sys/SysLog', '/sys/SysLog.vue', '系统日志', 'SysLog', 'el-icon-tickets', 1, 30, 1, NULL, '2022-08-09 15:47:45', '1', '2023-01-13 23:50:50', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1579714971074351105', '1529137618646228993', '/tools/TimeTool', '/tools/TimeTool.vue', '时间戳转换', 'TimeTool', 'el-icon-time', 1, 10, 1, NULL, '2022-10-11 14:06:13', '1', '2023-03-03 14:27:05', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1612459431350546434', '1529137618646228993', '/tools/VuexTest', '/tools/VuexTest.vue', 'vuex-demo', 'VuexTest', 'el-icon-s-open', 1, 20, 3, NULL, '2023-01-09 22:41:01', '1', '2023-01-09 22:41:01', NULL, 0, NULL);
INSERT INTO `sys_menu` VALUES ('1648254300911775745', '1529138714366267394', '/sys/RoleManagement', '/sys/RoleManagement.vue', '角色管理', 'RoleManagement', 'el-icon-user', 1, 40, 1, NULL, '2023-04-18 17:17:03', '1', '2023-04-19 09:59:04', '1', 0, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `del_flag` int(2) NULL DEFAULT NULL,
  `del_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理員', NULL, '2023-04-19 22:45:48', '1', '2023-04-19 22:45:52', '1', 0, NULL);

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
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_date` datetime NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `del_flag` int(3) NULL DEFAULT NULL,
  `del_token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '1c963798a6065cdd54ede5e2d1c030be', '3JS1Ur4YUsLAcBEIMF+qbA==', '超级管理员', 18, '666@123.com', '18888888888', '超级管理员', '2022-06-08 17:44:38', '1', '2022-06-08 17:44:38', '1', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
