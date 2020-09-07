/*
Navicat MySQL Data Transfer

Source Server         : myconn
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : myerp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-09-03 09:54:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbdealer`
-- ----------------------------
DROP TABLE IF EXISTS `tbdealer`;
CREATE TABLE `tbdealer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbdealer
-- ----------------------------

-- ----------------------------
-- Table structure for `tbguestbook`
-- ----------------------------
DROP TABLE IF EXISTS `tbguestbook`;
CREATE TABLE `tbguestbook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  `isshow` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbguestbook
-- ----------------------------

-- ----------------------------
-- Table structure for `tbinout`
-- ----------------------------
DROP TABLE IF EXISTS `tbinout`;
CREATE TABLE `tbinout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opttype` int(11) DEFAULT NULL COMMENT '-1为出库，1为入库',
  `proid` int(11) DEFAULT NULL,
  `pronum` varchar(255) DEFAULT NULL,
  `proname` varchar(50) DEFAULT NULL,
  `protype` varchar(255) DEFAULT NULL,
  `proimgurl` varchar(300) DEFAULT NULL,
  `prooldnum` int(11) DEFAULT NULL,
  `proaddnum` int(11) DEFAULT NULL COMMENT '入库或者出库的数量',
  `unitname` varchar(255) DEFAULT NULL COMMENT '计量单位名称',
  `ctime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbinout
-- ----------------------------
INSERT INTO `tbinout` VALUES ('1', '1', '17', 'gl-2020', '格力空调gl-2020', '家用电器/空调', '20200823095339843.jpg', '0', '100', '台', '2020-08-24 15:23:29');
INSERT INTO `tbinout` VALUES ('2', '-1', '17', 'gl-2020', '格力空调gl-2020', '家用电器/空调', '20200823095339843.jpg', '100', '30', '台', '2020-08-24 16:46:19');
INSERT INTO `tbinout` VALUES ('3', '1', '17', 'gl-2020', '格力空调gl-2020', '家用电器/空调', '20200823095339843.jpg', '70', '80', '台', '2020-08-24 16:54:13');
INSERT INTO `tbinout` VALUES ('4', '1', '20', 'daier-2020', '戴尔DELL灵越5000', '家用电器/笔记本电脑', '20200824171708716.png', '0', '1000', '台', '2020-08-24 17:18:05');
INSERT INTO `tbinout` VALUES ('5', '1', '18', 'hw-2020', '华为MateBook14', '家用电器/笔记本电脑', '20200824171445193.png', '0', '20000', '台', '2020-08-24 17:18:26');
INSERT INTO `tbinout` VALUES ('6', '1', '17', 'gl-2020', '格力空调gl-2020', '家用电器/空调', '20200823095339843.jpg', '150', '100', '台', '2020-08-24 17:53:59');
INSERT INTO `tbinout` VALUES ('7', '1', '17', 'gl-2020', '格力空调gl-2020', '家用电器/空调', '20200823095339843.jpg', '250', '100', '台', '2020-08-24 17:55:30');
INSERT INTO `tbinout` VALUES ('8', '1', '17', 'gl-2020', '格力空调gl-2020', '家用电器/空调', '20200823095339843.jpg', '350', '100', '台', '2020-08-24 17:56:43');
INSERT INTO `tbinout` VALUES ('9', '-1', '17', 'gl-2020', '格力空调gl-2020', '家用电器/空调', '20200823095339843.jpg', '450', '10', '台', '2020-08-24 17:57:20');
INSERT INTO `tbinout` VALUES ('10', '1', '21', 'hw-2020-13', 'MateBook 13', '家用电器/笔记本电脑', '20200824172055164.png', '0', '100', '台', '2020-09-02 19:08:58');
INSERT INTO `tbinout` VALUES ('11', '1', '21', 'hw-2020-13', 'MateBook 13', '家用电器/笔记本电脑', '20200824172055164.png', '100', '10', '台', '2020-09-03 09:35:40');
INSERT INTO `tbinout` VALUES ('12', '1', '21', 'hw-2020-13', 'MateBook 13', '家用电器/笔记本电脑', '20200824172055164.png', '110', '100', '台', '2020-09-03 09:43:31');

-- ----------------------------
-- Table structure for `tbmenubar`
-- ----------------------------
DROP TABLE IF EXISTS `tbmenubar`;
CREATE TABLE `tbmenubar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `MenuName` varchar(100) DEFAULT NULL,
  `ParentID` int(11) DEFAULT NULL,
  `depth` int(11) DEFAULT NULL,
  `ParentPath` varchar(100) DEFAULT NULL,
  `Href` varchar(100) DEFAULT NULL,
  `Target` varchar(100) DEFAULT NULL,
  `OrderList` int(11) DEFAULT NULL,
  `Visible` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbmenubar
-- ----------------------------
INSERT INTO `tbmenubar` VALUES ('1', '系统设置', '0', '1', '1', null, '', '1', '1');
INSERT INTO `tbmenubar` VALUES ('2', '商品中心', '0', '1', '2', null, '', '2', '1');
INSERT INTO `tbmenubar` VALUES ('3', '进货管理', '0', '1', '3', null, '', '3', '1');
INSERT INTO `tbmenubar` VALUES ('4', '销售管理', '0', '1', '4', null, '', '4', '1');
INSERT INTO `tbmenubar` VALUES ('5', '库存管理', '0', '1', '5', null, '', '7', '1');
INSERT INTO `tbmenubar` VALUES ('6', '报表管理', '0', '1', '6', null, '', '8', '1');
INSERT INTO `tbmenubar` VALUES ('7', '密码修改', '1', '2', '1,7', null, 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('8', '新增用户', '1', '2', '1,8', './admin/usersadd.jsp', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('9', '建根类别', '2', '2', '2,9', './admin/tbtypeadd.jsp', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('10', '管理类别', '2', '2', '2,10', './tbtypelist', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('11', '新增商品', '2', '2', '2,11', './admin/proadd.jsp', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('12', '管理商品', '2', '2', '2,12', './prolist', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('13', '创建进货单', '3', '2', '3,13', './admin/ordermain.jsp', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('14', '管理进货单', '3', '2', '3,14', './orderheadlist', 'main', '2', '1');
INSERT INTO `tbmenubar` VALUES ('15', '销售开单', '4', '2', '4,15', null, 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('16', '维护订单', '4', '2', '4,16', null, 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('17', '库存入库', '5', '2', '5,17', './admin/proinout.jsp?t=1', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('18', '商品出库', '5', '2', '5,18', './admin/proinout.jsp?t=-1', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('19', '商品查询', '5', '2', '5,19', 'prolistforsearch', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('20', '管理用户', '1', '2', '1,20', './userslist', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('21', '新增角色', '1', '2', '1,21', './admin/roleadd.jsp', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('22', '角色管理', '1', '2', '1,22', './rolelist', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('23', '入库管理', '0', '1', '23', '', null, '5', '0');
INSERT INTO `tbmenubar` VALUES ('24', '出库管理', '0', '1', '24', null, null, '6', '0');
INSERT INTO `tbmenubar` VALUES ('25', '明细查询', '5', '2', '5,25', './inoutlist', 'main', '1', '1');
INSERT INTO `tbmenubar` VALUES ('26', '进货报表', '6', '2', '6,26', './admin/jinhuo.jsp', 'main', '1', '1');

-- ----------------------------
-- Table structure for `tbnews`
-- ----------------------------
DROP TABLE IF EXISTS `tbnews`;
CREATE TABLE `tbnews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `contents` text,
  `createtime` varchar(100) DEFAULT NULL,
  `typeid` varchar(11) DEFAULT NULL,
  `source` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbnews
-- ----------------------------

-- ----------------------------
-- Table structure for `tborderhead`
-- ----------------------------
DROP TABLE IF EXISTS `tborderhead`;
CREATE TABLE `tborderhead` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(255) DEFAULT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `stel` varchar(255) DEFAULT NULL,
  `saddress` varchar(255) DEFAULT NULL,
  `sumprice` decimal(11,0) DEFAULT '0',
  `memberid` int(11) DEFAULT NULL,
  `ctime` varchar(255) DEFAULT NULL,
  `ptime` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tborderhead
-- ----------------------------
INSERT INTO `tborderhead` VALUES ('27', '20200826170541673', '超级管理员', '15397600038', '四川成都总部', '11499', '1', '2020-05-26 17:05:41', null, null);
INSERT INTO `tborderhead` VALUES ('28', '20200826170551874', '超级管理员', '15397600038', '四川成都总部', '4200', '1', '2020-06-26 17:05:51', null, null);
INSERT INTO `tborderhead` VALUES ('29', '20200826170708899', '超级管理员', '15397600038', '四川成都总部', '4200', '1', '2020-07-26 17:07:08', null, null);
INSERT INTO `tborderhead` VALUES ('30', '20200826171434818', '超级管理员', '15397600038', '四川成都总部', '49000', '1', '2020-08-26 17:14:34', null, null);
INSERT INTO `tborderhead` VALUES ('31', '20200903093415442', '超级管理员', '15397600038', '四川成都总部', '18800', '1', '2020-09-03 09:34:15', null, null);
INSERT INTO `tborderhead` VALUES ('32', '20200903094352419', '超级管理员', '15397600038', '四川成都总部', '5200', '1', '2020-09-03 09:43:52', null, null);
INSERT INTO `tborderhead` VALUES ('33', '20200903094553085', '超级管理员', '15397600038', '四川成都总部', '4200', '1', '2020-09-03 09:45:53', null, null);
INSERT INTO `tborderhead` VALUES ('34', '20200903094637149', '超级管理员', '15397600038', '四川成都总部', '6299', '1', '2020-09-03 09:46:37', null, null);

-- ----------------------------
-- Table structure for `tborderitems`
-- ----------------------------
DROP TABLE IF EXISTS `tborderitems`;
CREATE TABLE `tborderitems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(255) DEFAULT NULL,
  `proid` int(11) DEFAULT NULL,
  `proname` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `procount` int(11) DEFAULT NULL,
  `imgurl` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tborderitems
-- ----------------------------
INSERT INTO `tborderitems` VALUES ('41', '20200826170541673', '21', 'MateBook 13', '5200', '1', null);
INSERT INTO `tborderitems` VALUES ('42', '20200826170541673', '17', '格力空调gl-2020', '6299', '1', null);
INSERT INTO `tborderitems` VALUES ('43', '20200826170551874', '20', '戴尔DELL灵越5000', '4200', '1', null);
INSERT INTO `tborderitems` VALUES ('44', '20200826170708899', '20', '戴尔DELL灵越5000', '4200', '1', null);
INSERT INTO `tborderitems` VALUES ('45', '20200826171434818', '21', 'MateBook 13', '5200', '2', '20200824172055164.png');
INSERT INTO `tborderitems` VALUES ('46', '20200826171434818', '20', '戴尔DELL灵越5000', '4200', '3', '20200824171708716.png');
INSERT INTO `tborderitems` VALUES ('47', '20200826171434818', '18', '华为MateBook14', '5200', '5', '20200824171445193.png');
INSERT INTO `tborderitems` VALUES ('48', '20200903093415442', '21', 'MateBook 13', '5200', '2', '20200824172055164.png');
INSERT INTO `tborderitems` VALUES ('49', '20200903093415442', '20', '戴尔DELL灵越5000', '4200', '2', '20200824171708716.png');
INSERT INTO `tborderitems` VALUES ('50', '20200903094352419', '21', 'MateBook 13', '5200', '1', '20200824172055164.png');
INSERT INTO `tborderitems` VALUES ('51', '20200903094553085', '20', '戴尔DELL灵越5000', '4200', '1', '20200824171708716.png');
INSERT INTO `tborderitems` VALUES ('52', '20200903094637149', '17', '格力空调gl-2020', '6299', '1', '20200823095339843.jpg');

-- ----------------------------
-- Table structure for `tbproduct`
-- ----------------------------
DROP TABLE IF EXISTS `tbproduct`;
CREATE TABLE `tbproduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proname` varchar(255) DEFAULT NULL,
  `pronum` varchar(255) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `brief` varchar(255) DEFAULT NULL,
  `descriptions` text,
  `address` varchar(255) DEFAULT NULL,
  `procount` int(11) DEFAULT '0',
  `imgurl` varchar(255) DEFAULT NULL,
  `procodeurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbproduct
-- ----------------------------
INSERT INTO `tbproduct` VALUES ('17', '格力空调gl-2020', 'gl-2020', '33', '6299', '能耗低', '<p>格力（GREE）I享 变频冷暖 蒸发器自洁 智能WiFi 静音 圆柱客厅空调立式柜机 线下同款 KFR-72LW/(72580)FNhAa-A3</p><p><br/></p>', '青岛', '440', '20200823095339843.jpg', '20200902190447336.jpg');
INSERT INTO `tbproduct` VALUES ('18', '华为MateBook14', 'hw-2020', '34', '5200', '触控全面屏 多屏协同 轻薄本 深空灰', '<p>两年质保，7*24H咨询</p><p><br/></p><p><br/></p><p><br/></p>', '中国大陆', '20000', '20200824171445193.png', '20200902190442636.jpg');
INSERT INTO `tbproduct` VALUES ('20', '戴尔DELL灵越5000', 'daier-2020', '34', '4200', '轻薄笔记本', '<p>戴尔DELL灵越5000 14英寸酷睿i5网课学习轻薄笔记本电脑(十代i5-1035G1 8G 512G MX230 2G独显)银</p><p><br/></p>', '中国大陆', '1000', '20200824171708716.png', '20200902190438141.jpg');
INSERT INTO `tbproduct` VALUES ('21', 'MateBook 13', 'hw-2020-13', '34', '5200', ' 十代酷睿(i5 16G 512G MX250 触控屏 多屏协同)银', '<p>华为(HUAWEI)MateBook 13 2020款全面屏轻薄性能笔记本电脑 十代酷睿(i5 16G 512G MX250 触控屏 多屏协同)银</p><p><br/></p>', '中国大陆', '210', '20200824172055164.png', '20200902190431228.jpg');

-- ----------------------------
-- Table structure for `tbrolehasmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tbrolehasmenu`;
CREATE TABLE `tbrolehasmenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  `menuid` int(11) DEFAULT NULL,
  `menuname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbrolehasmenu
-- ----------------------------
INSERT INTO `tbrolehasmenu` VALUES ('93', '1', null, '22', null);
INSERT INTO `tbrolehasmenu` VALUES ('94', '1', null, '21', null);
INSERT INTO `tbrolehasmenu` VALUES ('95', '1', null, '20', null);
INSERT INTO `tbrolehasmenu` VALUES ('96', '1', null, '19', null);
INSERT INTO `tbrolehasmenu` VALUES ('97', '1', null, '18', null);
INSERT INTO `tbrolehasmenu` VALUES ('98', '1', null, '17', null);
INSERT INTO `tbrolehasmenu` VALUES ('99', '1', null, '16', null);
INSERT INTO `tbrolehasmenu` VALUES ('100', '1', null, '15', null);
INSERT INTO `tbrolehasmenu` VALUES ('101', '1', null, '14', null);
INSERT INTO `tbrolehasmenu` VALUES ('102', '1', null, '13', null);
INSERT INTO `tbrolehasmenu` VALUES ('103', '1', null, '12', null);
INSERT INTO `tbrolehasmenu` VALUES ('104', '1', null, '11', null);
INSERT INTO `tbrolehasmenu` VALUES ('105', '1', null, '10', null);
INSERT INTO `tbrolehasmenu` VALUES ('106', '1', null, '9', null);
INSERT INTO `tbrolehasmenu` VALUES ('107', '1', null, '8', null);
INSERT INTO `tbrolehasmenu` VALUES ('108', '1', null, '7', null);
INSERT INTO `tbrolehasmenu` VALUES ('109', '1', null, '6', null);
INSERT INTO `tbrolehasmenu` VALUES ('110', '1', null, '5', null);
INSERT INTO `tbrolehasmenu` VALUES ('111', '1', null, '4', null);
INSERT INTO `tbrolehasmenu` VALUES ('112', '1', null, '3', null);
INSERT INTO `tbrolehasmenu` VALUES ('113', '1', null, '2', null);
INSERT INTO `tbrolehasmenu` VALUES ('114', '1', null, '1', null);
INSERT INTO `tbrolehasmenu` VALUES ('115', '2', null, '22', null);
INSERT INTO `tbrolehasmenu` VALUES ('116', '2', null, '21', null);
INSERT INTO `tbrolehasmenu` VALUES ('117', '2', null, '20', null);
INSERT INTO `tbrolehasmenu` VALUES ('118', '2', null, '19', null);
INSERT INTO `tbrolehasmenu` VALUES ('119', '2', null, '18', null);
INSERT INTO `tbrolehasmenu` VALUES ('120', '2', null, '17', null);
INSERT INTO `tbrolehasmenu` VALUES ('121', '2', null, '16', null);
INSERT INTO `tbrolehasmenu` VALUES ('122', '2', null, '15', null);
INSERT INTO `tbrolehasmenu` VALUES ('123', '2', null, '14', null);
INSERT INTO `tbrolehasmenu` VALUES ('124', '2', null, '13', null);
INSERT INTO `tbrolehasmenu` VALUES ('125', '2', null, '12', null);
INSERT INTO `tbrolehasmenu` VALUES ('126', '2', null, '11', null);
INSERT INTO `tbrolehasmenu` VALUES ('127', '2', null, '10', null);
INSERT INTO `tbrolehasmenu` VALUES ('128', '2', null, '9', null);
INSERT INTO `tbrolehasmenu` VALUES ('129', '2', null, '8', null);
INSERT INTO `tbrolehasmenu` VALUES ('130', '2', null, '7', null);
INSERT INTO `tbrolehasmenu` VALUES ('131', '2', null, '6', null);
INSERT INTO `tbrolehasmenu` VALUES ('132', '2', null, '5', null);
INSERT INTO `tbrolehasmenu` VALUES ('133', '2', null, '4', null);
INSERT INTO `tbrolehasmenu` VALUES ('134', '2', null, '3', null);
INSERT INTO `tbrolehasmenu` VALUES ('135', '2', null, '2', null);
INSERT INTO `tbrolehasmenu` VALUES ('136', '2', null, '1', null);
INSERT INTO `tbrolehasmenu` VALUES ('137', '3', null, '3', null);
INSERT INTO `tbrolehasmenu` VALUES ('138', '3', null, '14', null);
INSERT INTO `tbrolehasmenu` VALUES ('139', '3', null, '13', null);
INSERT INTO `tbrolehasmenu` VALUES ('140', '1', null, '24', null);
INSERT INTO `tbrolehasmenu` VALUES ('141', '1', null, '23', null);
INSERT INTO `tbrolehasmenu` VALUES ('142', '1', null, '25', null);
INSERT INTO `tbrolehasmenu` VALUES ('143', '1', null, '26', null);
INSERT INTO `tbrolehasmenu` VALUES ('144', '4', null, '19', null);
INSERT INTO `tbrolehasmenu` VALUES ('145', '4', null, '5', null);
INSERT INTO `tbrolehasmenu` VALUES ('146', '5', null, '6', null);
INSERT INTO `tbrolehasmenu` VALUES ('147', '5', null, '26', null);

-- ----------------------------
-- Table structure for `tbroles`
-- ----------------------------
DROP TABLE IF EXISTS `tbroles`;
CREATE TABLE `tbroles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbroles
-- ----------------------------
INSERT INTO `tbroles` VALUES ('1', '超级管理员', '超级管理员');
INSERT INTO `tbroles` VALUES ('2', '管理员', '管理员');
INSERT INTO `tbroles` VALUES ('3', '经销商', '经销商');
INSERT INTO `tbroles` VALUES ('4', '仓库管理员', '只能查询商品库存信息');
INSERT INTO `tbroles` VALUES ('5', '采购员', '采购员');

-- ----------------------------
-- Table structure for `tbsaleorderhead`
-- ----------------------------
DROP TABLE IF EXISTS `tbsaleorderhead`;
CREATE TABLE `tbsaleorderhead` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(255) DEFAULT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `stel` varchar(255) DEFAULT NULL,
  `saddress` varchar(255) DEFAULT NULL,
  `sumprice` decimal(11,0) DEFAULT '0',
  `memberid` int(11) DEFAULT NULL,
  `ctime` varchar(255) DEFAULT NULL,
  `ptime` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbsaleorderhead
-- ----------------------------

-- ----------------------------
-- Table structure for `tbsaleorderitems`
-- ----------------------------
DROP TABLE IF EXISTS `tbsaleorderitems`;
CREATE TABLE `tbsaleorderitems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(255) DEFAULT NULL,
  `proid` int(11) DEFAULT NULL,
  `proname` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `procount` int(11) DEFAULT NULL,
  `imgurl` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbsaleorderitems
-- ----------------------------

-- ----------------------------
-- Table structure for `tbshoppingcar`
-- ----------------------------
DROP TABLE IF EXISTS `tbshoppingcar`;
CREATE TABLE `tbshoppingcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sessionid` varchar(32) DEFAULT NULL,
  `proname` varchar(255) DEFAULT NULL,
  `proid` int(11) DEFAULT NULL,
  `procount` int(11) DEFAULT NULL,
  `ctime` varchar(255) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbshoppingcar
-- ----------------------------
INSERT INTO `tbshoppingcar` VALUES ('75', '41EFB73C20B2B946AB68D8917961A513', 'MateBook 13', '21', '1', '2020-08-26 15:39:18', '20200824172055164.png', '5200');
INSERT INTO `tbshoppingcar` VALUES ('76', '41EFB73C20B2B946AB68D8917961A513', '戴尔DELL灵越5000', '20', '1', '2020-08-26 15:39:18', '20200824171708716.png', '4200');

-- ----------------------------
-- Table structure for `tbsupplier`
-- ----------------------------
DROP TABLE IF EXISTS `tbsupplier`;
CREATE TABLE `tbsupplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbsupplier
-- ----------------------------

-- ----------------------------
-- Table structure for `tbtype`
-- ----------------------------
DROP TABLE IF EXISTS `tbtype`;
CREATE TABLE `tbtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(255) DEFAULT NULL,
  `parentname` varchar(255) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `fullpath` varchar(255) DEFAULT NULL,
  `levelnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbtype
-- ----------------------------
INSERT INTO `tbtype` VALUES ('1', '家用电器', '/', '0', '家用电器', '0');
INSERT INTO `tbtype` VALUES ('2', '干杂调料', '/', '0', '干杂调料', '0');
INSERT INTO `tbtype` VALUES ('3', '烟酒饮料', '/', '0', '烟酒饮料', '0');
INSERT INTO `tbtype` VALUES ('4', '鸡鸭鱼肉', '/', '0', '鸡鸭鱼肉', '0');
INSERT INTO `tbtype` VALUES ('5', '水果蔬菜', '/', '0', '水果蔬菜', '0');
INSERT INTO `tbtype` VALUES ('31', '电视机', '家用电器', '1', '家用电器/电视机', '1');
INSERT INTO `tbtype` VALUES ('32', '电冰箱', '家用电器', '1', '家用电器/电冰箱', '1');
INSERT INTO `tbtype` VALUES ('33', '空调', '家用电器', '1', '家用电器/空调', '1');
INSERT INTO `tbtype` VALUES ('34', '笔记本', '家用电器', '1', '家用电器/笔记本电脑', '1');
INSERT INTO `tbtype` VALUES ('35', 'TCL', '电视机', '31', '家用电器/电视机/TCL', '2');
INSERT INTO `tbtype` VALUES ('36', '小米', '电视机', '31', '家用电器/电视机/小米', '2');

-- ----------------------------
-- Table structure for `tbuserhasrole`
-- ----------------------------
DROP TABLE IF EXISTS `tbuserhasrole`;
CREATE TABLE `tbuserhasrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `rolename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbuserhasrole
-- ----------------------------
INSERT INTO `tbuserhasrole` VALUES ('13', '1', null, '1', null);
INSERT INTO `tbuserhasrole` VALUES ('14', '12', null, '3', null);
INSERT INTO `tbuserhasrole` VALUES ('15', '13', null, '4', null);
INSERT INTO `tbuserhasrole` VALUES ('16', '13', null, '5', null);

-- ----------------------------
-- Table structure for `tbusers`
-- ----------------------------
DROP TABLE IF EXISTS `tbusers`;
CREATE TABLE `tbusers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `truename` varchar(20) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `creatorid` int(11) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbusers
-- ----------------------------
INSERT INTO `tbusers` VALUES ('1', 'admin', '123456', '超级管理员', '15397600038', '四川成都总部', null, null);
INSERT INTO `tbusers` VALUES ('2', 'tangyan05', '123456', '张三', '15397600036', '上海江浦路店', null, null);
INSERT INTO `tbusers` VALUES ('7', 'feifei', '123456', '郭靖', '15988886666', '北京复兴门外大街10086号', null, null);
INSERT INTO `tbusers` VALUES ('8', 'tangyan03', '123456', '刘湘', '15397600035', '重庆来福士广场店', null, null);
INSERT INTO `tbusers` VALUES ('11', 'tangyan02', '123456', '汤燕', '15397600033', '成都经销商火车南站西路店', null, null);
INSERT INTO `tbusers` VALUES ('12', 'tangyan01', '123456', '汤燕', '15397600032', '成都天府广场店', null, null);
INSERT INTO `tbusers` VALUES ('13', 'qingzewei', '123456', 'qingzewei', '13500000001', '测试账号', null, null);

-- ----------------------------
-- View structure for `v_product`
-- ----------------------------
DROP VIEW IF EXISTS `v_product`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_product` AS select `tbproduct`.`id` AS `id`,`tbproduct`.`proname` AS `proname`,`tbproduct`.`pronum` AS `pronum`,`tbproduct`.`typeid` AS `typeid`,`tbproduct`.`price` AS `price`,`tbproduct`.`brief` AS `brief`,`tbproduct`.`descriptions` AS `descriptions`,`tbproduct`.`address` AS `address`,`tbproduct`.`procount` AS `procount`,`tbproduct`.`imgurl` AS `imgurl`,`tbproduct`.`procodeurl` AS `procodeurl`,`tbtype`.`levelnum` AS `levelnum`,`tbtype`.`typename` AS `typename`,`tbtype`.`fullpath` AS `fullpath` from (`tbproduct` left join `tbtype` on((`tbproduct`.`typeid` = `tbtype`.`id`))) ;
