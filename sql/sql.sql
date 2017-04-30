-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.1.22-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 shiro_user 的数据库结构
CREATE DATABASE IF NOT EXISTS `shiro_user` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shiro_user`;

-- 导出  表 shiro_user.permission 结构
CREATE TABLE IF NOT EXISTS `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `node` int(11) NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `url` varchar(50) NOT NULL DEFAULT '' COMMENT '对应url',
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT '权限类型 view-页面权限，api-api权限',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mark` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='权限';

-- 正在导出表  shiro_user.permission 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`id`, `node`, `name`, `url`, `type`, `add_time`, `update_time`, `mark`) VALUES
	(1, 0, '水滴配置', '#,fa-connectdevelop', '', '2017-03-29 23:43:46', '2017-03-29 23:43:46', 1),
	(2, 0, '报表', '#,fa-line-chart', '', '2017-03-29 23:44:10', '2017-03-29 23:44:10', 1),
	(3, 1, '水滴配置子节点1', 'http://116.62.40.239:7002/', '', '2017-04-05 16:33:34', '2017-04-05 16:33:34', 1),
	(4, 1, '水滴配置子节点2', '/4', '', '2017-04-05 16:34:00', '2017-04-05 16:34:00', 1),
	(5, 2, '报表子节点1', '/testShiro', '', '2017-04-05 16:34:26', '2017-04-05 16:34:26', 1),
	(6, 0, '测试配置', '#,fa-paper-plane', '', '2017-04-06 17:47:57', '2017-04-06 17:47:57', 1),
	(7, 6, '测试配置1', '/p7', '', '2017-04-06 17:48:44', '2017-04-06 17:48:44', 1);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;

-- 导出  表 shiro_user.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '' COMMENT '角色名',
  `permission_list` varchar(45) NOT NULL DEFAULT '' COMMENT '权限列表',
  `description` varchar(20) NOT NULL DEFAULT '' COMMENT '说明',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mark` int(11) NOT NULL DEFAULT '0' COMMENT '0-无效,1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  shiro_user.role 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `permission_list`, `description`, `add_time`, `update_time`, `mark`) VALUES
	(1, 'dev', '2,5', '开发者', '2017-03-29 15:27:30', '2017-03-29 15:27:30', 1),
	(2, 'admin', '1,3,4,6,7', '管理员', '2017-03-29 15:32:19', '2017-03-29 15:32:19', 1),
	(3, 'sale', '', '业务员', '2017-04-10 13:59:34', '2017-04-10 13:59:34', 1),
	(4, 'biz', '', '商家', '2017-04-10 14:04:21', '2017-04-10 14:04:21', 1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- 导出  表 shiro_user.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `unique_key` varchar(50) NOT NULL DEFAULT '' COMMENT '用户唯一key',
  `name` varchar(45) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(45) NOT NULL DEFAULT '' COMMENT '加密后密码',
  `salt` varchar(45) NOT NULL DEFAULT '' COMMENT '盐',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mark` int(11) NOT NULL DEFAULT '0' COMMENT '0-无效,1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  shiro_user.user 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `unique_key`, `name`, `password`, `salt`, `add_time`, `update_time`, `mark`) VALUES
	(1, 'a001', 'sun', '3d740930502576e5b0d355c6976fdaa7', '486d', '2017-04-01 12:35:51', '2017-04-01 12:35:51', 1),
	(2, '', 'liu', '402ad719c5ecf523f299640163a069e9', 'f7f2', '2017-04-01 14:08:59', '2017-04-01 14:08:59', 1),
	(3, '', 'zhao', 'a064754480e96068009c9c641f53ef44', 'bda3', '2017-04-01 17:28:59', '2017-04-01 17:28:59', 1),
	(4, '', 'zhang', 'a2d96d98303bc7bf03a33f247a92faa8', '0847', '2017-04-01 18:32:48', '2017-04-01 18:32:48', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  表 shiro_user.user_role 结构
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_key` varchar(45) NOT NULL DEFAULT '' COMMENT '用户唯一key',
  `role_list` varchar(45) NOT NULL DEFAULT '' COMMENT '角色id',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mark` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- 正在导出表  shiro_user.user_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `user_key`, `role_list`, `add_time`, `update_time`, `mark`) VALUES
	(1, 'zhang', '1', '2017-04-10 14:36:58', '2017-04-10 14:36:58', 1),
	(2, 'a001', '1,2', '2017-04-10 14:36:58', '2017-04-10 14:36:58', 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
