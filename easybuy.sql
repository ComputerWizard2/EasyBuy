/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : easybuy

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-12-11 21:53:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for easybuy_comment
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_comment`;
CREATE TABLE `easybuy_comment` (
  `ec_id` int(11) NOT NULL AUTO_INCREMENT,
  `ec_content` varchar(200) NOT NULL,
  `ec_create_time` datetime NOT NULL,
  `ec_reply` varchar(200) DEFAULT NULL,
  `ec_reply_time` datetime DEFAULT NULL,
  `ec_nick_time` varchar(10) NOT NULL,
  PRIMARY KEY (`ec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of easybuy_comment
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_news
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_news`;
CREATE TABLE `easybuy_news` (
  `en_id` int(11) NOT NULL AUTO_INCREMENT,
  `en_title` varchar(40) NOT NULL,
  `en_content` varchar(1000) NOT NULL,
  `en_create_time` datetime NOT NULL,
  PRIMARY KEY (`en_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of easybuy_news
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_order
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_order`;
CREATE TABLE `easybuy_order` (
  `eo_id` int(11) NOT NULL AUTO_INCREMENT,
  `eo_user_id` varchar(10) NOT NULL,
  `eo_user_name` varchar(20) NOT NULL,
  `eo_user_address` varchar(200) NOT NULL,
  `eo_create_time` date NOT NULL,
  `eo_cost` float(10,2) NOT NULL,
  `eo_status` int(11) NOT NULL COMMENT '非空 1待审核 2.审核通过 3. 配货 4. 卖家已发货 5.已收货',
  PRIMARY KEY (`eo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of easybuy_order
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_order_detail`;
CREATE TABLE `easybuy_order_detail` (
  `eod_id` int(11) NOT NULL AUTO_INCREMENT,
  `eo_id` int(11) NOT NULL,
  `ep_id` int(11) NOT NULL,
  `eod_quantity` int(255) NOT NULL,
  `eod_cost` float(10,2) NOT NULL,
  PRIMARY KEY (`eod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of easybuy_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_product
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_product`;
CREATE TABLE `easybuy_product` (
  `ep_id` int(11) NOT NULL,
  `ep_name` varchar(20) NOT NULL,
  `ep_description` varchar(100) DEFAULT NULL,
  `ep_price` double(10,2) NOT NULL,
  `ep_stock` int(11) NOT NULL,
  `epc_id` int(11) NOT NULL,
  `epc_child_id` int(11) DEFAULT NULL,
  `ep_file_name` varchar(200) NOT NULL,
  PRIMARY KEY (`ep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of easybuy_product
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_product_category
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_product_category`;
CREATE TABLE `easybuy_product_category` (
  `epc_id` int(11) NOT NULL AUTO_INCREMENT,
  `epc_name` varchar(20) NOT NULL,
  `epc_paent_id` int(11) NOT NULL,
  PRIMARY KEY (`epc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of easybuy_product_category
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_user
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_user`;
CREATE TABLE `easybuy_user` (
  `eu_user_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `eu_user_name` varchar(20) NOT NULL,
  `eu_password` varchar(20) NOT NULL,
  `eu_sex` varchar(1) NOT NULL,
  `eu_birthday` datetime DEFAULT NULL,
  `eu_identity_code` varchar(18) DEFAULT NULL,
  `eu_email` varchar(80) DEFAULT NULL,
  `eu_mobile` varchar(11) NOT NULL,
  `eu_address` varchar(200) NOT NULL,
  `eu_login` tinyint(4) NOT NULL,
  `eu_status` tinyint(4) NOT NULL,
  PRIMARY KEY (`eu_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of easybuy_user
-- ----------------------------
INSERT INTO `easybuy_user` VALUES ('admin', 'admin', '123', '2', '2017-11-14 19:11:19', '1234567891234567', '23456@163.com', '12345678912', 'sharwe', '1', '1');
INSERT INTO `easybuy_user` VALUES ('user ', 'snow', '123', '1', '1999-12-21 19:15:41', '123456789345453', 'qwew@134.com', '21324567', 'spi', '1', '1');
