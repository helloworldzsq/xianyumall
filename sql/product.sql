/*
 Navicat Premium Data Transfer

 Source Server         : mybatis
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : hellomall

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 22/09/2021 10:58:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` int(50) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `picture_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int(10) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(1) UNSIGNED ZEROFILL NOT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (7, '123', 'http://skd-mall.oss-cn-hangzhou.aliyuncs.com/val/f2d2a6a9a6564ae8a36592100942e0e7.jpg?Expires=1633923647&OSSAccessKeyId=LTAI5t8hEXyBpSVw9Z6Mw36A&Signature=v%2Bc9fyRmAYMIimM5FXBAmUUOhiI%3D', 123, 'admin', 0, '2021-09-20 07:40:48');
INSERT INTO `product` VALUES (8, '456', 'http://skd-mall.oss-cn-hangzhou.aliyuncs.com/val/e0054f284f8f4327bbb66b4122dedc83.jpg?Expires=1633991596&OSSAccessKeyId=LTAI5t8hEXyBpSVw9Z6Mw36A&Signature=0p0PdxqaqC9wxoTmkPx0wH%2B1WDk%3D', 456, 'admin', 0, '2021-09-21 02:33:16');
INSERT INTO `product` VALUES (9, '魁拔', 'http://skd-mall.oss-cn-hangzhou.aliyuncs.com/val/d8bb7502df274947bd039adce788b2f7.JPG?Expires=1633992292&OSSAccessKeyId=LTAI5t8hEXyBpSVw9Z6Mw36A&Signature=EYK265dArd03ynYHGJXC3onodtQ%3D', 123, 'admin', 0, '2021-09-21 02:44:52');
INSERT INTO `product` VALUES (10, '车流', 'http://skd-mall.oss-cn-hangzhou.aliyuncs.com/val/c0f11bc1cbe74a31bca89e14792b754c.jpeg?Expires=1633992818&OSSAccessKeyId=LTAI5t8hEXyBpSVw9Z6Mw36A&Signature=QA0KJG2cw52vpPChRvog7%2F42GCw%3D', 234, 'admin', 0, '2021-09-21 02:53:38');
INSERT INTO `product` VALUES (11, '星空', 'http://skd-mall.oss-cn-hangzhou.aliyuncs.com/val/30ec0a813f554f5a8935df949f9b433f.jpg?Expires=1633992843&OSSAccessKeyId=LTAI5t8hEXyBpSVw9Z6Mw36A&Signature=IZtD6iM9vzsJV4WEe8jQ2g9XXKA%3D', 345, 'admin', 0, '2021-09-21 02:54:04');
INSERT INTO `product` VALUES (12, '摄影', 'http://skd-mall.oss-cn-hangzhou.aliyuncs.com/val/35c0dbf78b494d91971034297acc0c14.jpg?Expires=1633994723&OSSAccessKeyId=LTAI5t8hEXyBpSVw9Z6Mw36A&Signature=tvaWO5JSfMbP7ExPfIYof12HzFU%3D', 456, 'admin', 0, '2021-09-21 03:25:24');

SET FOREIGN_KEY_CHECKS = 1;
