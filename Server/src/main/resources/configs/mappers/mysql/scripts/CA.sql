/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.14 : Database - ca
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ca` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ca`;

/*Table structure for table `ca_organization` */

DROP TABLE IF EXISTS `ca_organization`;

CREATE TABLE `ca_organization` (
  `id` varchar(36) DEFAULT 'UUID()',
  `parent_id` varchar(36) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creater_id` varchar(36) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater_id` varchar(36) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ca_organization` */

insert  into `ca_organization`(`id`,`parent_id`,`name`,`code`,`description`,`create_date`,`creater_id`,`update_date`,`updater_id`,`address`,`phone`,`email`) values ('1','0','总部','zb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2','1','研发中心','yfzx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('3','1','财务部','cwb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('4','1','人事部','rsb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('5','2','研发一部','yf1b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('6','2','研发二部','yf2b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('7','2','研发三部','yf3b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `ca_permission` */

DROP TABLE IF EXISTS `ca_permission`;

CREATE TABLE `ca_permission` (
  `id` varchar(36) DEFAULT 'uuid()',
  `parent_id` varchar(36) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ca_permission` */

insert  into `ca_permission`(`id`,`parent_id`,`name`,`code`,`type`) values ('C16C9D14488B42A99660966195347B9B','0','用户权限','user:*','1'),('E92BD668AF09476C954BB1F1463CFD43','C16C9D14488B42A99660966195347B9B','用户添加','user:add','1'),('4E2EC92E4EE343CB8D095102C0E4650B','C16C9D14488B42A99660966195347B9B','用户删除','user:delete','1'),('0E261E23C82E4CCDA75094FD87C9B422','C16C9D14488B42A99660966195347B9B','用户修改','user:update','1'),('064440FD2B0C47A7BE949BAA1D77A538','C16C9D14488B42A99660966195347B9B','用户查看','user:view','1');

/*Table structure for table `ca_role` */

DROP TABLE IF EXISTS `ca_role`;

CREATE TABLE `ca_role` (
  `id` varchar(36) DEFAULT 'uuid()',
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ca_role` */

insert  into `ca_role`(`id`,`name`,`description`,`code`) values ('1','系统管理员',NULL,'systemManager'),('2','普通用户',NULL,'NormalUser');

/*Table structure for table `ca_role_permission` */

DROP TABLE IF EXISTS `ca_role_permission`;

CREATE TABLE `ca_role_permission` (
  `id` varchar(36) DEFAULT 'uuid()',
  `role_id` varchar(36) DEFAULT NULL,
  `permission_id` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ca_role_permission` */

insert  into `ca_role_permission`(`id`,`role_id`,`permission_id`) values ('986C4E99C0A64F6FB05BEE83AA8D3F02','1','064440FD2B0C47A7BE949BAA1D77A538');

/*Table structure for table `ca_user` */

DROP TABLE IF EXISTS `ca_user`;

CREATE TABLE `ca_user` (
  `id` varchar(36) DEFAULT NULL,
  `org_id` varchar(36) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `login_name` varchar(30) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `job_num` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ca_user` */

insert  into `ca_user`(`id`,`org_id`,`username`,`login_name`,`password`,`job_num`,`create_date`,`update_date`,`last_login_date`) values ('C5F0CA07EA864022B1BF072DB4161119','5','李四','lisi','e10adc3949ba59abbe56e057f20f883e','No.113',NULL,NULL,NULL),('72F45FD93ACA428E99FF14D6416BC129','6','王五','wangwu','123456','No.114',NULL,NULL,NULL),('8936AFF6DF2F404AB81042767248AFD3','7','赵六','zhaoliu','123456','No.115',NULL,NULL,NULL);

/*Table structure for table `ca_user_role` */

DROP TABLE IF EXISTS `ca_user_role`;

CREATE TABLE `ca_user_role` (
  `id` varchar(36) DEFAULT 'uuid()',
  `role_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ca_user_role` */

insert  into `ca_user_role`(`id`,`role_id`,`user_id`) values ('E746174977064628A3ECC740B59920BB','1','C5F0CA07EA864022B1BF072DB4161119'),('B536DD016BA0403B87ED57BC69A95B51','2','72F45FD93ACA428E99FF14D6416BC129'),('4F358EC132F44DE69C30442EF0E372E0','2','8936AFF6DF2F404AB81042767248AFD3'),('CDCFAC27444C41AABF9EB3A68C292711','2','C5F0CA07EA864022B1BF072DB4161119');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
