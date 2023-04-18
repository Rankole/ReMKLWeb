/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : xiyiji

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 08/05/2021 10:16:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, '小天鹅');
INSERT INTO `brand` VALUES (2, '米家');
INSERT INTO `brand` VALUES (3, '西门子');
INSERT INTO `brand` VALUES (4, '奥克斯');
INSERT INTO `brand` VALUES (5, '海尔');
INSERT INTO `brand` VALUES (6, '松下');

-- ----------------------------
-- Table structure for brand_type
-- ----------------------------
DROP TABLE IF EXISTS `brand_type`;
CREATE TABLE `brand_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `brand_id` int(0) NULL DEFAULT NULL COMMENT '品牌id',
  `brand_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `type_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号名称',
  `type_ins` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号简介',
  `img_path` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand_type
-- ----------------------------
INSERT INTO `brand_type` VALUES (1, 1, '小天鹅', '除菌洗衣机', '纳米银离子除菌, 让细菌无处藏身;\r\n提升筋内银离子晶体遇水释放,深入衣物纤维深处,\r\n消除细菌, 净衣抑菌;', '/images/xte/纳米银离子除菌洗衣机.jpg');
INSERT INTO `brand_type` VALUES (2, 1, '小天鹅', '高温煮洗洗衣机', '深层除菌双重奏:40℃预洗, 60℃主洗\r\n除菌率大于99%', '/images/xte/高温煮洗洗衣机.jpg');
INSERT INTO `brand_type` VALUES (3, 1, '小天鹅', '静音节能洗衣机', '低调洗衣, 享受静谧时光,内设不平衡自矫正系统,\r\n有效减少洗衣机抖动,洗衣脱水过程安静平稳', '/images/xte/静音节能洗衣机.jpg');
INSERT INTO `brand_type` VALUES (4, 2, '米家', '洗烘一体机', '智能洗烘二合一,一筒洗净烘干全家衣物;', '/images/mj/冷凝洗烘一体机.jpg');
INSERT INTO `brand_type` VALUES (5, 2, '米家', '变频滚筒', '智能称重, 少量衣物, 智能省水', '/images/mj/变频滚筒洗衣机.jpg');
INSERT INTO `brand_type` VALUES (6, 2, '米家', '超能洗', '旋风波轮, 立体水流, 强劲洗涤无残留', '/images/mj/超能洗.jpg');
INSERT INTO `brand_type` VALUES (7, 3, '西门子', '洗干一体机', '独立静衣程序, 利用65℃蒸汽深层分解\r\n织物上的细菌, 专为冬季厚重外套和服装设计', '/images/xmz/洗干一体机.jpg');
INSERT INTO `brand_type` VALUES (8, 3, '西门子', '除螨护肤洗衣机', '健康更护肤, 99%除菌率, 99%除螨率, \r\n双层门视窗, 隔热更强', '/images/xmz/除螨护肤洗衣机.jpg');
INSERT INTO `brand_type` VALUES (9, 3, '西门子', '超氧除菌', '快速祛味，智在清新,轻松去除火锅味，烟味等顽固异味\r\n活氧除菌，洗涤无忧\r\n智感除菌 - 两档活氧浓度随心选择，有效去除衣物细菌\r\n智感洁筒 - 90度高温水与活氧科技双重作用，有效减少筒内细菌，深层洁筒', '/images/xmz/超氧除菌洗衣机.jpg');
INSERT INTO `brand_type` VALUES (10, 4, '奥克斯', '洗脱双桶', '大容量, 好箱体, 大动力, 强力洗;\r\n桶内高速旋转,产生气流,吃干桶内水分,\r\n防止霉菌产生,无异味', '/images/aks/洗脱双桶洗衣机.jpg');
INSERT INTO `brand_type` VALUES (11, 4, '奥克斯', '单桶洗衣机', '节能省电, 双层波轮, 操作简单,洗脱分离,\r\n蓝光抑菌', '/images/aks/单桶洗衣机.jpg');
INSERT INTO `brand_type` VALUES (12, 4, '奥克斯', '洗护合一洗衣机', '仿生手洗, 洁净衣物, 立体水流, 反复摩擦', '/images/aks/洗护和一洗衣机.jpg');
INSERT INTO `brand_type` VALUES (13, 5, '海尔', '洗烘一体洗衣机', '双喷淋, 高温桶自洁;\r\n触控屏无旋钮设计;\r\n变频电机,一级节能;\r\n蒸汽除螨,巴氏除菌;\r\n脱水甩干,洗烘一体;', '/images/hr/洗烘一体机.jpg');
INSERT INTO `brand_type` VALUES (14, 5, '海尔', '波轮洗衣机', '10kg大容量, 轻松解决多件, 大件衣物洗涤难题\r\n让洗衣不再是负担', '/images/hr/波轮洗衣机.jpg');
INSERT INTO `brand_type` VALUES (15, 5, '海尔', '滚筒洗衣机', '2大科技:香薰,舒展芳香; 除菌,健康护衣;\r\n4大体验:巴氏杀菌, 去味护色, 充分漂净, 柔洗真丝', '/images/hr/滚筒洗衣机.jpg');
INSERT INTO `brand_type` VALUES (16, 6, '松下', '三维立体洗衣机', '洗涤剂通过松下泡沫净, 微泡沫深入纤维除渍\r\n洗涤剂在进水时, 通过高压水流冲击,\r\n迅速转化成大量细腻泡沫,让洗涤剂发挥更大能效,\r\n轻松清洗顽固污渍', '/images/sx/三维立体洗衣机.jpg');
INSERT INTO `brand_type` VALUES (17, 6, '松下', '轻松智慧洗衣机', '量衣称重, 自动匹配洗涤所需水量及洗涤时间,\r\n根据面料吸水性与水位变化情况, 智能补水', '/images/sx/轻松智慧洗衣机.jpg');
INSERT INTO `brand_type` VALUES (18, 6, '松下', '除菌除螨洗烘一体机', '环保式热风杀死螨虫, 高频劲漂水流快速\r\n冲洗螨虫尸体, 高温除菌, 守护肌肤健康', '/images/sx/除菌除螨洗烘一体机.jpg');

-- ----------------------------
-- Table structure for gift
-- ----------------------------
DROP TABLE IF EXISTS `gift`;
CREATE TABLE `gift`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` int(0) NULL DEFAULT 10 COMMENT '数量',
  `product_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品说明',
  `type` int(0) NULL DEFAULT 0 COMMENT '礼品类型; 0.洗衣券',
  `score` decimal(10, 0) NULL DEFAULT 100 COMMENT '兑换所需积分',
  `used_id` int(0) NULL DEFAULT NULL COMMENT '可使用洗衣机品牌id',
  `used_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可使用洗衣机品牌名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分礼品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gift
-- ----------------------------
INSERT INTO `gift` VALUES (1, '小天鹅洗衣券', 120, '小天鹅五一大放送', 0, 10, 1, '小天鹅');
INSERT INTO `gift` VALUES (2, '奥克斯洗衣券', 10, '奥克斯洗衣券', 0, 10, 4, '奥克斯');
INSERT INTO `gift` VALUES (3, '松下洗衣券', 10, '松下洗衣券', 0, 30, 6, '松下');
INSERT INTO `gift` VALUES (4, '米家洗衣券', 20, '米家洗衣券', 0, 5, 2, '米家');
INSERT INTO `gift` VALUES (5, '西门子洗衣券', 30, '西门子洗衣券', 0, 20, 3, '西门子');
INSERT INTO `gift` VALUES (6, '海尔洗衣券', 20, '海尔洗衣券', 0, 10, 5, '海尔');

-- ----------------------------
-- Table structure for maintenance_worker
-- ----------------------------
DROP TABLE IF EXISTS `maintenance_worker`;
CREATE TABLE `maintenance_worker`  (
  `id` int(0) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修工姓名',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修工性别',
  `age` int(0) NULL DEFAULT NULL COMMENT '维修工年龄',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修工电话',
  `work_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修工工号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintenance_worker
-- ----------------------------
INSERT INTO `maintenance_worker` VALUES (2, '赵小花', '男', 12, '1231231', 'n001');
INSERT INTO `maintenance_worker` VALUES (NULL, '火立龙', '男', 45, '1231231', 'hll001');
INSERT INTO `maintenance_worker` VALUES (NULL, '欧阳天秀', '男', 35, '12315', 'oytx001');
INSERT INTO `maintenance_worker` VALUES (NULL, '龙傲天', '男', 18, '12306', 'lat001');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `href` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单连接',
  `spread` int(0) NULL DEFAULT NULL COMMENT '是否展开子菜单',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子菜单打开时是否在新窗口打开页面',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父菜单id',
  `top_menu_id` int(0) NULL DEFAULT NULL COMMENT '顶菜单id',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '权限角色',
  `is_menu` int(0) NULL DEFAULT 0 COMMENT '是否是首页',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1114 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (7, '洗衣机列表', '&#xe665;', '/page/washingMachine>washingMachineArrayByUser', 0, '', NULL, 1, 2, 0);
INSERT INTO `menu` VALUES (8, '会员管理', '&#xe770;', '', 0, NULL, NULL, 1, 1, 0);
INSERT INTO `menu` VALUES (11, '洗衣机管理', '&#xe857;', '', 0, NULL, NULL, 1, 1, 0);
INSERT INTO `menu` VALUES (14, '洗衣机选择', '&#xe636;', '/page/washingMachine>washingMachineListByUser', 0, '', NULL, 1, 2, 1);
INSERT INTO `menu` VALUES (21, '我的预约', '&#xe6af;', '/page/reservation>reservationList', 0, '', 27, NULL, 2, 0);
INSERT INTO `menu` VALUES (22, '我的报修', '&#xe631;', '/page/repair>repairList', 0, '', NULL, 1, 2, 0);
INSERT INTO `menu` VALUES (23, '维修人员管理', '&#xe62a;\r\n', '/page/maintenanceWorker>maintenanceWorkerList', 0, NULL, NULL, 1, 1, 0);
INSERT INTO `menu` VALUES (24, '报修信息', '&#xe631;\r\n', '/page/repair>repairListByAdmin', 0, NULL, NULL, 1, 1, 0);
INSERT INTO `menu` VALUES (25, '洗衣机列表', '&#xe857;', '/page/washingMachine>washingMachineList', 0, NULL, 11, NULL, 1, 1);
INSERT INTO `menu` VALUES (26, '洗衣模式管理', '&#xe857;', '/page/washingModel>washingModelList', 0, NULL, 11, NULL, 1, 0);
INSERT INTO `menu` VALUES (27, '我的订单', '&#xe626;', '', 0, NULL, NULL, 1, 2, 0);
INSERT INTO `menu` VALUES (28, '我的使用', '&#xe665;', '/page/used>usedList', 0, '', 27, NULL, 2, 0);
INSERT INTO `menu` VALUES (29, '积分商城', '&#xe626;', '/page/gift>giftListByUser', 0, '', NULL, 1, 2, 0);
INSERT INTO `menu` VALUES (30, '会员中心', '&#xe66f;', '', 0, NULL, NULL, 1, 2, 0);
INSERT INTO `menu` VALUES (31, '我的信息', '&#xe6b2;', '/page/user>userDetailByUser', 0, '', 30, NULL, 2, 0);
INSERT INTO `menu` VALUES (32, '充值记录', '&#xe65e;', '/page/paymentInfo>paymentInfoList', 0, '', 30, NULL, 2, 0);
INSERT INTO `menu` VALUES (33, '我的礼品', '&#xe627;', '/page/userGift>userGiftList', 0, '', 30, NULL, 2, 0);
INSERT INTO `menu` VALUES (35, '会员信息', '&#xe66f;', '/page/user>userList', 0, NULL, 8, NULL, 1, 0);
INSERT INTO `menu` VALUES (36, '充值记录', '&#xe770;', '/page/paymentInfo>paymentInfoListByAdmin', 0, NULL, 8, NULL, 1, 0);
INSERT INTO `menu` VALUES (37, '积分商品管理', '&#xe857;', '/page/gift>giftList', 0, NULL, NULL, 1, 1, 0);

-- ----------------------------
-- Table structure for payment_info
-- ----------------------------
DROP TABLE IF EXISTS `payment_info`;
CREATE TABLE `payment_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型; 0.支出 1.收入',
  `payment_explain` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型说明',
  `money` decimal(10, 0) NULL DEFAULT NULL COMMENT '金额',
  `create_datetime` datetime(0) NULL DEFAULT NULL COMMENT '发生时间',
  `business_id` int(0) NULL DEFAULT NULL COMMENT '业务id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_info
-- ----------------------------
INSERT INTO `payment_info` VALUES (1, 2, 'hello', '收入', '充值', 1, '2021-05-04 19:45:40', NULL);
INSERT INTO `payment_info` VALUES (2, 2, 'hello', '收入', '充值', 1000, '2021-05-05 16:45:40', NULL);
INSERT INTO `payment_info` VALUES (3, 2, 'hello', '支出', '使用洗衣机', 2, '2021-05-05 17:46:19', NULL);
INSERT INTO `payment_info` VALUES (4, 2, 'hello', '支出', '使用洗衣机', 1, '2021-05-05 17:59:02', NULL);
INSERT INTO `payment_info` VALUES (5, 2, 'hello', '支出', '使用洗衣机', 3, '2021-05-05 19:50:43', NULL);

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `washing_machine_id` int(0) NULL DEFAULT NULL COMMENT '洗衣机id',
  `brand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机品牌',
  `brand_type` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机型号',
  `washing_machine_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机编号',
  `worker_id` int(0) NULL DEFAULT NULL COMMENT '维修工id',
  `worker_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修工姓名',
  `worker_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修工电话',
  `repair_dateime` datetime(0) NULL DEFAULT NULL COMMENT '报修时间',
  `solve_datetime` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态(0.待维修, 1.已维修)',
  `repair_user_id` int(0) NULL DEFAULT NULL COMMENT '报修用户id',
  `repair_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报修用户名',
  `repair_remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报修备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '报修记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (1, 3, '小天鹅', '除菌洗衣机', '2', 2, '赵小花', '1231231', '2021-04-21 21:41:25', '2021-05-05 17:58:16', 1, 2, 'hello', '222');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `washing_machine_id` int(0) NULL DEFAULT NULL COMMENT '洗衣机id',
  `brand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机品牌',
  `brand_type` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机型号',
  `washing_machine_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机编号',
  `washing_model_id` int(0) NULL DEFAULT NULL COMMENT '洗衣模式id',
  `washing_model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣模式',
  `washing_times` int(0) NULL DEFAULT NULL COMMENT '洗衣时长(单位:分钟)',
  `reservation_datetime` datetime(0) NULL DEFAULT NULL COMMENT '预约时间',
  `used_datetime` datetime(0) NULL DEFAULT NULL COMMENT '使用时间',
  `run_times` int(0) NULL DEFAULT NULL COMMENT '运行时长(单位:分钟)',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态;0.预约中, 1.运行中, 2.运行结束, 3.逾期未使用',
  `reser_user_id` int(0) NULL DEFAULT NULL COMMENT '预约者id',
  `reser_user_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约者用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '预约记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES (1, 4, '小天鹅', '除菌洗衣机', '3', 3, '3', 1, '2021-04-20 23:30:25', NULL, 1, 3, 2, 'hello');
INSERT INTO `reservation` VALUES (2, 4, '小天鹅', '除菌洗衣机', '3', 3, '3', 1, '2021-04-20 23:30:25', NULL, 1, 3, 2, 'hello');

-- ----------------------------
-- Table structure for top_menu
-- ----------------------------
DROP TABLE IF EXISTS `top_menu`;
CREATE TABLE `top_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '顶菜单key',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '顶菜单名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of top_menu
-- ----------------------------
INSERT INTO `top_menu` VALUES (1, '&#xe63c;', 'contentManagement', '内容管理');
INSERT INTO `top_menu` VALUES (2, 'icon-icon10', 'memberCenter', '用户中心');
INSERT INTO `top_menu` VALUES (3, '&#xe620;', 'systemeSttings', '系统设置');
INSERT INTO `top_menu` VALUES (4, '&#xe705;', 'seraphApi', '使用文档');

-- ----------------------------
-- Table structure for used
-- ----------------------------
DROP TABLE IF EXISTS `used`;
CREATE TABLE `used`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `washing_machine_id` int(0) NULL DEFAULT NULL COMMENT '洗衣机id',
  `brand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机品牌',
  `brand_type` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机型号',
  `washing_machine_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣机编号',
  `washing_model_id` int(0) NULL DEFAULT NULL COMMENT '洗衣模式id',
  `washing_model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '洗衣模式',
  `washing_times` int(0) NULL DEFAULT NULL COMMENT '洗衣时长(单位:分钟)',
  `used_datetime` datetime(0) NULL DEFAULT NULL COMMENT '使用时间',
  `run_times` int(0) NULL DEFAULT NULL COMMENT '运行时长(单位:分钟)',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态; 1.运行中, 2.运行结束',
  `used_user_id` int(0) NULL DEFAULT NULL COMMENT '使用者id',
  `used_user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用者用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of used
-- ----------------------------
INSERT INTO `used` VALUES (1, 3, '小天鹅', '除菌洗衣机', '2', 2, '脱水', 15, '2021-04-20 23:20:00', 15, 2, 2, 'hello');
INSERT INTO `used` VALUES (2, 4, '小天鹅', '除菌洗衣机', '3', 3, '3', 1, '2021-04-20 23:24:42', 1, 2, 2, 'hello');
INSERT INTO `used` VALUES (3, 4, '小天鹅', '除菌洗衣机', '3', 1, '超强洗', 45, '2021-04-21 21:49:08', 45, 2, 2, 'hello');
INSERT INTO `used` VALUES (4, 4, '小天鹅', '除菌洗衣机', '3', 1, '超强洗', 45, '2021-05-05 16:43:45', 45, 2, 2, 'hello');
INSERT INTO `used` VALUES (5, 4, '小天鹅', '除菌洗衣机', '3', 1, '超强洗', 45, '2021-05-05 17:46:20', 45, 2, 2, 'hello');
INSERT INTO `used` VALUES (6, 3, '小天鹅', '除菌洗衣机', '2', 1, '超强洗', 45, '2021-05-05 17:59:15', 45, 2, 2, 'hello');
INSERT INTO `used` VALUES (7, 20, '奥克斯', '洗脱双桶', 'akxt001', 14, '极速洗脱一体', 15, '2021-05-05 19:50:49', 15, 2, 2, 'hello');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role` int(0) NULL DEFAULT NULL COMMENT '角色权限',
  `status` int(0) NULL DEFAULT 1 COMMENT '人员状态 1.正常 2.注销 3.暂停',
  `balance` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `score` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '积分',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 1, 1, 0.00, 0.00, '12314', '北京市豆各庄');
INSERT INTO `user` VALUES (2, 'hello', 'hello', 2, 1, 995.00, 0.03, '123412123', '上海市虹桥体育场');
INSERT INTO `user` VALUES (3, '18317791951@126.com', 'hello', 2, 1, 0.00, 20.00, '11233', '河北省石家庄小学');
INSERT INTO `user` VALUES (4, 'sss', '123', 2, 1, 0.00, 0.00, '1234123', '伦敦');
INSERT INTO `user` VALUES (5, 'nicko', '123', 2, 1, 0.00, 0.00, '1231313', '日本东京');

-- ----------------------------
-- Table structure for user_gift
-- ----------------------------
DROP TABLE IF EXISTS `user_gift`;
CREATE TABLE `user_gift`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `gift_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
  `gift_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `amount` int(0) NULL DEFAULT NULL COMMENT '数量',
  `gift_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品说明',
  `gift_score` decimal(10, 0) NULL DEFAULT NULL COMMENT '兑换所需积分',
  `gift_used_id` int(0) NULL DEFAULT NULL COMMENT '可以使用品牌id',
  `gift_used_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可以使用品牌名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_gift
-- ----------------------------
INSERT INTO `user_gift` VALUES (1, 2, 'hello', 1, '小天鹅洗衣券', 0, '飒飒大苏打', 10, 1, '小天鹅');
INSERT INTO `user_gift` VALUES (2, 2, 'hello', 1, '小天鹅洗衣券', 1, '飒飒大苏打', 10, 1, '小天鹅');

-- ----------------------------
-- Table structure for washing_machine
-- ----------------------------
DROP TABLE IF EXISTS `washing_machine`;
CREATE TABLE `washing_machine`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `brand_id` int(0) NULL DEFAULT NULL COMMENT '品牌id',
  `brand_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `type_id` int(0) NULL DEFAULT NULL COMMENT '型号id',
  `type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `status` int(0) NULL DEFAULT 0 COMMENT '状态 0:待使用; 1:已预约; 2:报修中; 3:使用中',
  `reservation_user_id` int(0) NULL DEFAULT NULL COMMENT '预约人id',
  `reservation_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约人用户名',
  `reservation_datetime` datetime(0) NULL DEFAULT NULL COMMENT '预约时间',
  `used_user_id` int(0) NULL DEFAULT NULL COMMENT '使用人id',
  `used_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用人用户名',
  `used_datetime` datetime(0) NULL DEFAULT NULL COMMENT '开始使用时间',
  `repair_user_id` int(0) NULL DEFAULT NULL COMMENT '报修用户id',
  `repair_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报修用户名',
  `repair_datetime` datetime(0) NULL DEFAULT NULL COMMENT '报修时间',
  `repair_remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报修备注',
  `worker_id` int(0) NULL DEFAULT NULL COMMENT '维修工id',
  `worker_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修工姓名',
  `worker_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修工电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of washing_machine
-- ----------------------------
INSERT INTO `washing_machine` VALUES (3, 1, '小天鹅', 1, '除菌洗衣机', 1.00, '2', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (4, 1, '小天鹅', 1, '除菌洗衣机', 2.00, '3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (5, 1, '小天鹅', 1, '除菌洗衣机', 2.00, 'tecj001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (6, 1, '小天鹅', 1, '除菌洗衣机', 2.00, 'tecj002', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (7, 1, '小天鹅', 2, '高温煮洗洗衣机', 3.00, 'tegw001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (8, 1, '小天鹅', 2, '高温煮洗洗衣机', 3.00, 'tegw002', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (9, 2, '米家', 4, '洗烘一体机', 3.00, 'mjxh001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (10, 2, '米家', 4, '洗烘一体机', 2.00, 'mjxh002', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (11, 2, '米家', 5, '变频滚筒', 3.00, 'mjbp001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (12, 2, '米家', 5, '变频滚筒', 3.00, 'mjbp002', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (13, 2, '米家', 6, '超能洗', 3.00, 'mjcn001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (14, 2, '米家', 6, '超能洗', 3.00, 'mjcn002', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (15, 3, '西门子', 7, '洗干一体机', 3.00, 'xmxg001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (16, 3, '西门子', 7, '洗干一体机', 3.00, 'xmxg002', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (17, 3, '西门子', 8, '除螨护肤洗衣机', 5.00, 'xmcm001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (18, 3, '西门子', 8, '除螨护肤洗衣机', 5.00, 'xmcm002', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (19, 3, '西门子', 9, '超氧除菌', 5.00, 'xmcy001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (20, 4, '奥克斯', 10, '洗脱双桶', 3.00, 'akxt001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (21, 4, '奥克斯', 11, '单桶洗衣机', 2.00, 'akdt001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (22, 4, '奥克斯', 12, '洗护合一洗衣机', 5.00, 'akxh001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (23, 5, '海尔', 13, '洗烘一体洗衣机', 5.00, 'hexh001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (24, 5, '海尔', 14, '波轮洗衣机', 5.00, 'hebl001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (25, 6, '松下', 16, '三维立体洗衣机', 7.00, 'sxsw001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (26, 6, '松下', 17, '轻松智慧洗衣机', 6.00, 'sxzh001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `washing_machine` VALUES (27, 6, '松下', 18, '除菌除螨洗烘一体机', 5.00, 'sxcj001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for washing_model
-- ----------------------------
DROP TABLE IF EXISTS `washing_model`;
CREATE TABLE `washing_model`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `brand_id` int(0) NULL DEFAULT NULL COMMENT '品牌id',
  `brand_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `brand_type_id` int(0) NULL DEFAULT NULL COMMENT '型号id',
  `brand_type_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号名称',
  `model_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模式名称',
  `run_time` int(0) NULL DEFAULT NULL COMMENT '洗衣时长(单位:分钟)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of washing_model
-- ----------------------------
INSERT INTO `washing_model` VALUES (1, 1, '小天鹅', 1, '除菌洗衣机', '超强洗', 45);
INSERT INTO `washing_model` VALUES (2, 1, '小天鹅', 1, '除菌洗衣机', '脱水', 15);
INSERT INTO `washing_model` VALUES (3, 1, '小天鹅', 1, '除菌洗衣机', '3', 1);
INSERT INTO `washing_model` VALUES (4, 1, '小天鹅', 2, '高温煮洗洗衣机', '快洗', 15);
INSERT INTO `washing_model` VALUES (5, 1, '小天鹅', 2, '高温煮洗洗衣机', '标准洗', 45);
INSERT INTO `washing_model` VALUES (6, 1, '小天鹅', 3, '静音节能洗衣机', '小衣物速洗', 20);
INSERT INTO `washing_model` VALUES (7, 1, '小天鹅', 3, '静音节能洗衣机', '标准洗', 40);
INSERT INTO `washing_model` VALUES (8, 2, '米家', 4, '洗烘一体机', '大衣速洗', 25);
INSERT INTO `washing_model` VALUES (9, 2, '米家', 5, '变频滚筒', '智能洗', 35);
INSERT INTO `washing_model` VALUES (10, 2, '米家', 6, '超能洗', '深度清洁', 50);
INSERT INTO `washing_model` VALUES (11, 3, '西门子', 7, '洗干一体机', '热度杀菌', 45);
INSERT INTO `washing_model` VALUES (12, 3, '西门子', 8, '除螨护肤洗衣机', '除螨清洁', 45);
INSERT INTO `washing_model` VALUES (13, 3, '西门子', 9, '超氧除菌', '深度清洁', 45);
INSERT INTO `washing_model` VALUES (14, 4, '奥克斯', 10, '洗脱双桶', '极速洗脱一体', 15);
INSERT INTO `washing_model` VALUES (15, 4, '奥克斯', 11, '单桶洗衣机', '深度清洁', 35);
INSERT INTO `washing_model` VALUES (16, 4, '奥克斯', 12, '洗护合一洗衣机', '轻柔洗衣', 30);
INSERT INTO `washing_model` VALUES (17, 5, '海尔', 13, '洗烘一体洗衣机', '洗烘一体', 50);
INSERT INTO `washing_model` VALUES (18, 5, '海尔', 14, '波轮洗衣机', '洗脱烘干', 60);
INSERT INTO `washing_model` VALUES (19, 6, '松下', 16, '三维立体洗衣机', '智能全面', 45);
INSERT INTO `washing_model` VALUES (20, 6, '松下', 17, '轻松智慧洗衣机', '极速智能洗', 12);
INSERT INTO `washing_model` VALUES (21, 6, '松下', 18, '除菌除螨洗烘一体机', '极度清洁', 65);

SET FOREIGN_KEY_CHECKS = 1;
