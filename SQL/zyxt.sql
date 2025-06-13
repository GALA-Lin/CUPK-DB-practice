/*
 Navicat MySQL Data Transfer

 Source Server         : GALA_Lin
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : zyxt

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 13/06/2025 11:34:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 作业入账表
-- ----------------------------
DROP TABLE IF EXISTS `作业入账表`;
CREATE TABLE `作业入账表`  (
  `单据号` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `入账金额` decimal(10, 2) NULL DEFAULT NULL,
  `入账人` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `入账日期` date NULL DEFAULT NULL,
  PRIMARY KEY (`单据号`) USING BTREE,
  CONSTRAINT `作业入账表_ibfk_1` FOREIGN KEY (`单据号`) REFERENCES `作业结算表` (`单据号`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 作业入账表
-- ----------------------------
INSERT INTO `作业入账表` VALUES ('zy2018001', 11900.00, '王五', '2018-05-28');
INSERT INTO `作业入账表` VALUES ('zy2018002', 10900.00, '王五', '2018-05-28');
INSERT INTO `作业入账表` VALUES ('zy2018003', 10400.00, '王五', '2018-05-28');
INSERT INTO `作业入账表` VALUES ('zy2018004', 10600.00, '王五', '2018-05-28');
INSERT INTO `作业入账表` VALUES ('zy2018005', 10600.00, '赵六', '2018-05-28');

-- ----------------------------
-- Table structure for 作业结算表
-- ----------------------------
DROP TABLE IF EXISTS `作业结算表`;
CREATE TABLE `作业结算表`  (
  `单据号` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `开工日期` date NULL DEFAULT NULL,
  `完工日期` date NULL DEFAULT NULL,
  `施工单位` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `施工内容` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `人工费` decimal(10, 2) NULL DEFAULT NULL,
  `设备费` decimal(10, 2) NULL DEFAULT NULL,
  `其它费用` decimal(10, 2) NULL DEFAULT NULL,
  `结算金额` decimal(10, 2) NULL DEFAULT NULL,
  `结算人` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `结算日期` date NULL DEFAULT NULL,
  PRIMARY KEY (`单据号`) USING BTREE,
  INDEX `施工单位`(`施工单位`) USING BTREE,
  CONSTRAINT `作业结算表_ibfk_1` FOREIGN KEY (`单据号`) REFERENCES `作业预算表` (`单据号`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `作业结算表_ibfk_2` FOREIGN KEY (`施工单位`) REFERENCES `施工单位表` (`施工单位名称`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 作业结算表
-- ----------------------------
INSERT INTO `作业结算表` VALUES ('zy2018001', '2018-05-04', '2018-05-25', '作业公司作业一队', '堵漏', 2500.00, 1000.00, 1400.00, 11900.00, '李四', '2018-05-26');
INSERT INTO `作业结算表` VALUES ('zy2018002', '2018-05-04', '2018-05-23', '作业公司作业二队', '检泵', 1500.00, 1000.00, 2400.00, 10900.00, '李四', '2018-05-26');
INSERT INTO `作业结算表` VALUES ('zy2018003', '2018-05-06', '2018-05-23', '作业公司作业二队', '调剖', 2000.00, 500.00, 1400.00, 10400.00, '李四', '2018-05-26');
INSERT INTO `作业结算表` VALUES ('zy2018004', '2018-05-04', '2018-05-24', '作业公司作业三队', '解堵', 2000.00, 1000.00, 1600.00, 10600.00, '李四', '2018-05-26');
INSERT INTO `作业结算表` VALUES ('zy2018005', '2018-05-04', '2018-05-28', '作业公司作业三队', '防砂', 1000.00, 2000.00, 1300.00, 11300.00, '李四', '2018-06-01');

-- ----------------------------
-- Table structure for 作业预算表
-- ----------------------------
DROP TABLE IF EXISTS `作业预算表`;
CREATE TABLE `作业预算表`  (
  `单据号` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `预算单位` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `井号` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `预算金额` decimal(10, 2) NULL DEFAULT NULL,
  `预算人` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `预算日期` date NULL DEFAULT NULL,
  PRIMARY KEY (`单据号`) USING BTREE,
  INDEX `预算单位`(`预算单位`) USING BTREE,
  INDEX `井号`(`井号`) USING BTREE,
  CONSTRAINT `作业预算表_ibfk_1` FOREIGN KEY (`预算单位`) REFERENCES `单位代码表` (`单位代码`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `作业预算表_ibfk_2` FOREIGN KEY (`井号`) REFERENCES `油水井表` (`井号`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 作业预算表
-- ----------------------------
INSERT INTO `作业预算表` VALUES ('zy2018001', '112201001', 'y001', 10000.00, '张三', '2018-05-01');
INSERT INTO `作业预算表` VALUES ('zy2018002', '112201002', 'y003', 11000.00, '张三', '2018-05-01');
INSERT INTO `作业预算表` VALUES ('zy2018003', '112201002', 's001', 10500.00, '张三', '2018-05-01');
INSERT INTO `作业预算表` VALUES ('zy2018004', '112202001', 's002', 12000.00, '张三', '2018-05-01');
INSERT INTO `作业预算表` VALUES ('zy2018005', '112202002', 'y005', 12000.00, '张三', '2018-05-01');

-- ----------------------------
-- Table structure for 单位代码表
-- ----------------------------
DROP TABLE IF EXISTS `单位代码表`;
CREATE TABLE `单位代码表`  (
  `单位代码` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `单位名称` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`单位代码`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 单位代码表
-- ----------------------------
INSERT INTO `单位代码表` VALUES ('1122', '采油厂');
INSERT INTO `单位代码表` VALUES ('112201', '采油一矿');
INSERT INTO `单位代码表` VALUES ('112201001', '采油一矿一队');
INSERT INTO `单位代码表` VALUES ('112201002', '采油一矿二队');
INSERT INTO `单位代码表` VALUES ('112201003', '采油一矿三队');
INSERT INTO `单位代码表` VALUES ('112202', '采油二矿');
INSERT INTO `单位代码表` VALUES ('112202001', '采油二矿一队');
INSERT INTO `单位代码表` VALUES ('112202002', '采油二矿二队');

-- ----------------------------
-- Table structure for 施工单位表
-- ----------------------------
DROP TABLE IF EXISTS `施工单位表`;
CREATE TABLE `施工单位表`  (
  `施工单位名称` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`施工单位名称`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 施工单位表
-- ----------------------------
INSERT INTO `施工单位表` VALUES ('作业公司作业一队');
INSERT INTO `施工单位表` VALUES ('作业公司作业三队');
INSERT INTO `施工单位表` VALUES ('作业公司作业二队');

-- ----------------------------
-- Table structure for 材料费明细表
-- ----------------------------
DROP TABLE IF EXISTS `材料费明细表`;
CREATE TABLE `材料费明细表`  (
  `单据号` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `物码` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `消耗数量` decimal(10, 2) NULL DEFAULT NULL,
  `单价` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`单据号`, `物码`) USING BTREE,
  INDEX `物码`(`物码`) USING BTREE,
  CONSTRAINT `材料费明细表_ibfk_1` FOREIGN KEY (`单据号`) REFERENCES `作业结算表` (`单据号`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `材料费明细表_ibfk_2` FOREIGN KEY (`物码`) REFERENCES `物码表` (`物码`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 材料费明细表
-- ----------------------------
INSERT INTO `材料费明细表` VALUES ('zy2018001', 'wm001', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018001', 'wm002', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018001', 'wm003', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018001', 'wm004', 100.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018002', 'wm001', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018002', 'wm002', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018002', 'wm003', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018003', 'wm001', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018003', 'wm002', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018003', 'wm003', 250.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018004', 'wm001', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018004', 'wm002', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018004', 'wm004', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018005', 'wm001', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018005', 'wm002', 200.00, 10.00);
INSERT INTO `材料费明细表` VALUES ('zy2018005', 'wm004', 300.00, 10.00);

-- ----------------------------
-- Table structure for 油水井表
-- ----------------------------
DROP TABLE IF EXISTS `油水井表`;
CREATE TABLE `油水井表`  (
  `井号` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `井别` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `单位代码` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`井号`) USING BTREE,
  INDEX `单位代码`(`单位代码`) USING BTREE,
  CONSTRAINT `油水井表_ibfk_1` FOREIGN KEY (`单位代码`) REFERENCES `单位代码表` (`单位代码`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 油水井表
-- ----------------------------
INSERT INTO `油水井表` VALUES ('s001', '水井', '112201002');
INSERT INTO `油水井表` VALUES ('s002', '水井', '112202001');
INSERT INTO `油水井表` VALUES ('s003', '水井', '112202001');
INSERT INTO `油水井表` VALUES ('y001', '油井', '112201001');
INSERT INTO `油水井表` VALUES ('y002', '油井', '112201001');
INSERT INTO `油水井表` VALUES ('y003', '油井', '112201002');
INSERT INTO `油水井表` VALUES ('y004', '油井', '112201003');
INSERT INTO `油水井表` VALUES ('y005', '油井', '112202002');

-- ----------------------------
-- Table structure for 物码表
-- ----------------------------
DROP TABLE IF EXISTS `物码表`;
CREATE TABLE `物码表`  (
  `物码` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `名称规格` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `计量单位` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`物码`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 物码表
-- ----------------------------
INSERT INTO `物码表` VALUES ('wm001', '材料一', '吨');
INSERT INTO `物码表` VALUES ('wm002', '材料二', '米');
INSERT INTO `物码表` VALUES ('wm003', '材料三', '桶');
INSERT INTO `物码表` VALUES ('wm004', '材料四', '袋');

SET FOREIGN_KEY_CHECKS = 1;
