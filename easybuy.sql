/*
Navicat MySQL Data Transfer

Source Server         : ning
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : easybuy

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2017-12-12 16:27:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for easybuy_comment
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_comment`;
CREATE TABLE `easybuy_comment` (
  `ec_id` int(11) NOT NULL auto_increment,
  `ec_content` varchar(255) NOT NULL default '',
  `ec_create_time` datetime NOT NULL default '0000-00-00 00:00:00',
  `ec_reply` varchar(255) default NULL,
  `ec_reply_time` datetime default NULL,
  `ec_nick_name` varchar(10) NOT NULL default '',
  PRIMARY KEY  (`ec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_comment
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_news
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_news`;
CREATE TABLE `easybuy_news` (
  `eu_id` int(11) NOT NULL auto_increment,
  `en_title` varchar(40) NOT NULL default '',
  `en_content` varchar(1000) NOT NULL default '',
  `en_create_time` timestamp NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`eu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_news
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_order
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_order`;
CREATE TABLE `easybuy_order` (
  `eo_id` int(11) NOT NULL auto_increment,
  `eo_user_id` varchar(10) NOT NULL default '',
  `eo_user_name` varchar(20) NOT NULL default '',
  `eo_user_address` varchar(255) NOT NULL default '',
  `eo_create_time` date NOT NULL default '0000-00-00',
  `eo_cost` float NOT NULL default '0',
  `eo_status` enum('1','2','3','4','5') default NULL,
  PRIMARY KEY  (`eo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_order
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_order_detail`;
CREATE TABLE `easybuy_order_detail` (
  `eod_id` int(11) NOT NULL auto_increment,
  `eo_ id` int(11) NOT NULL default '0',
  `ep_id` int(11) NOT NULL default '0',
  `eod_quantity` int(11) NOT NULL default '0',
  `eod_cost` float NOT NULL default '0',
  PRIMARY KEY  (`eod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for easybuy_product
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_product`;
CREATE TABLE `easybuy_product` (
  `ep_id` int(11) NOT NULL auto_increment,
  `ep_name` varchar(20) NOT NULL default '',
  `ep_description` varchar(100) default NULL,
  `ep_price` double NOT NULL default '0',
  `ep_stock` int(11) NOT NULL default '0',
  `epc_id` int(11) NOT NULL default '0',
  `epc_child_id` int(11) default NULL,
  `ep_file_name` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`ep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_product
-- ----------------------------
INSERT INTO `easybuy_product` VALUES ('1', 'TCL人工智能 HDR曲面超薄4K电视', '金属机身，32核', '3999', '99', '1', '6', 'TCLTV');
INSERT INTO `easybuy_product` VALUES ('2', '格力(GREE) 二级能效 圆柱柜机', '微联智能', '8999', '10', '1', '7', '空调');
INSERT INTO `easybuy_product` VALUES ('3', '茵曼冬装套头气质针织裙', '花杏色', '285', '100', '3', '15', 'yinman');
INSERT INTO `easybuy_product` VALUES ('4', 'iphone plus 手机壳', '防摔透明清爽', '12.9', '200', '5', '23', 'ke');

-- ----------------------------
-- Table structure for easybuy_product_category
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_product_category`;
CREATE TABLE `easybuy_product_category` (
  `epc_id` int(11) NOT NULL auto_increment,
  `epc_name` varchar(25) NOT NULL default '',
  `epc_parent_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`epc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_product_category
-- ----------------------------
INSERT INTO `easybuy_product_category` VALUES ('1', '家用电器', '0');
INSERT INTO `easybuy_product_category` VALUES ('2', '电脑办公', '0');
INSERT INTO `easybuy_product_category` VALUES ('3', '女装', '0');
INSERT INTO `easybuy_product_category` VALUES ('4', '男装', '0');
INSERT INTO `easybuy_product_category` VALUES ('5', '手机数码', '0');
INSERT INTO `easybuy_product_category` VALUES ('6', '电视', '1');
INSERT INTO `easybuy_product_category` VALUES ('7', '空调', '1');
INSERT INTO `easybuy_product_category` VALUES ('8', '洗衣机', '1');
INSERT INTO `easybuy_product_category` VALUES ('9', '冰箱', '1');
INSERT INTO `easybuy_product_category` VALUES ('10', '笔记本', '2');
INSERT INTO `easybuy_product_category` VALUES ('11', '平板电脑', '2');
INSERT INTO `easybuy_product_category` VALUES ('12', '鼠标', '2');
INSERT INTO `easybuy_product_category` VALUES ('13', '键盘', '2');
INSERT INTO `easybuy_product_category` VALUES ('14', '耳机/耳麦', '2');
INSERT INTO `easybuy_product_category` VALUES ('15', '连衣裙', '3');
INSERT INTO `easybuy_product_category` VALUES ('16', '打底裤', '3');
INSERT INTO `easybuy_product_category` VALUES ('17', '阔腿裤', '3');
INSERT INTO `easybuy_product_category` VALUES ('18', '毛呢大衣', '3');
INSERT INTO `easybuy_product_category` VALUES ('19', '西裤', '4');
INSERT INTO `easybuy_product_category` VALUES ('20', '夹克', '4');
INSERT INTO `easybuy_product_category` VALUES ('21', '男款羽绒服', '4');
INSERT INTO `easybuy_product_category` VALUES ('22', '男款运动裤', '4');
INSERT INTO `easybuy_product_category` VALUES ('23', '手机壳', '5');
INSERT INTO `easybuy_product_category` VALUES ('24', '手机存储卡', '5');
INSERT INTO `easybuy_product_category` VALUES ('25', '数码相机', '5');
INSERT INTO `easybuy_product_category` VALUES ('26', '移动电源', '5');

-- ----------------------------
-- Table structure for easybuy_user
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_user`;
CREATE TABLE `easybuy_user` (
  `eu_user_id` varchar(10) NOT NULL default '',
  `eu_user_name` varchar(20) NOT NULL default '',
  `eu_password` varchar(20) NOT NULL default '',
  `eu_sex` enum('B','G') default NULL,
  `eu_birthday` datetime default NULL,
  `eu_identity_code` varchar(18) default NULL,
  `eu_email` varchar(80) default NULL,
  `eu_mobile` varchar(255) NOT NULL default '',
  `eu_address` varchar(255) NOT NULL default '',
  `eu_login` tinyint(3) NOT NULL default '0',
  `eu_status` enum('1','2') default NULL,
  PRIMARY KEY  (`eu_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_user
-- ----------------------------
INSERT INTO `easybuy_user` VALUES ('admin', 'admin', 'admin', 'B', '2017-12-11 11:19:35', '13220119921121212', 'admin123@126.com', '123456789', '北京', '0', '2');
INSERT INTO `easybuy_user` VALUES ('ashi123', 'qiushi', '111111', 'B', '2017-12-11 00:00:00', '118119199612301019', 'shishi@126.com', '15888881111', '土星', '0', '1');
INSERT INTO `easybuy_user` VALUES ('chengcheng', 'pengcheng', '121200', 'B', '2017-01-06 00:00:00', '118118199810198989', 'cheng123@163.com', '18900001111', '地球', '0', '1');
INSERT INTO `easybuy_user` VALUES ('wenwen', 'zhongwen', '123456', 'G', '2017-12-11 15:15:17', '100119199801011212', 'wen123@126.com', '1234567890', '火星', '0', '1');
