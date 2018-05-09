/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.17 : Database - ca
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ca` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `ca`;

/*Table structure for table `ca_app_infor` */

DROP TABLE IF EXISTS `ca_app_infor`;

CREATE TABLE `ca_app_infor` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'APP唯一主键',
  `app_version` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'APP版本号',
  `app_update_description` varchar(3000) COLLATE utf8_bin NOT NULL COMMENT 'APP更新说明',
  `forced_update` int(2) NOT NULL DEFAULT '0' COMMENT '是否强制更新0 不强制，1强制',
  `app_url` varchar(500) COLLATE utf8_bin NOT NULL COMMENT 'APP下载地址',
  `app_name` varchar(500) CHARACTER SET utf8 NOT NULL COMMENT 'APP名称',
  `app_create_date` datetime DEFAULT NULL COMMENT 'APP更新时间',
  `app_delete` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除0 正常，1 删除',
  `app_md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'APP校验的MD5值',
  `project_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '所属项目ID',
  `app_size` bigint(20) NOT NULL COMMENT 'APP文件大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ca_app_infor` */

insert  into `ca_app_infor`(`id`,`app_version`,`app_update_description`,`forced_update`,`app_url`,`app_name`,`app_create_date`,`app_delete`,`app_md5`,`project_id`,`app_size`) values 
('111','fff','ff',0,'ff','ff','2017-06-16 14:21:35',0,NULL,'0b25ef49ae5741729406d3752d3e8909',0),
('3e8c5e90048d47648237f8ce3bc42cc8','','',0,'C:\\upload\\','','2017-06-29 09:11:40',0,NULL,'aa',0),
('5e4d7c25cceb4cea852ef6b5734b34d4','dff','ff',0,'C:\\upload\\文件应用.txt','dff文件应用.txt','2017-06-20 16:00:57',0,NULL,'0b25ef49ae5741729406d3752d3e8909',2),
('861fda3ee7ab4d5fbae3f32ff7bbbdbd','1.2','好好好',0,'C:\\upload\\文件应用.txt','1.2文件应用.txt','2017-06-23 10:52:43',0,NULL,'0b25ef49ae5741729406d3752d3e8909',2),
('88f1961da4d947ee8b062e467cd4cda7','1.2','bbb',0,'C:\\upload\\Read Me.txt','1.2Read Me.txt','2017-06-21 14:41:25',0,NULL,'0b25ef49ae5741729406d3752d3e8909',0),
('9105ead0fcc94728a90ae0d90acde56b','1.3','测试',0,'C:\\upload/Read Me.txt','1.3Read Me.txt','2017-06-27 13:34:57',0,NULL,'0b25ef49ae5741729406d3752d3e8909',0),
('9fb59f6b68844bcfa0ddf62544c0056a','','',0,'/upload/prod/测试User.txt','测试User.txt','2018-03-02 14:30:54',0,NULL,'f1a43788b8374a7eba63c1a7fd7d4d25',1),
('b329c88e86044cf6a5d7ca42b5f9f164','1.2','很好',0,'C:\\upload\\index.jsp','1.2index.jsp','2017-06-23 10:51:08',0,NULL,'0b25ef49ae5741729406d3752d3e8909',6),
('b462b0abd4d74ba28962f68cd3e46225','1.1','dd',0,'C:\\upload\\selection.json','1.1selection.json','2017-06-20 10:07:21',0,NULL,'0b25ef49ae5741729406d3752d3e8909',53),
('d94b662b17294831b2976d6c2d3a48d2','1.3','',0,'C:\\upload\\Read Me.txt','1.3Read Me.txt','2017-07-05 16:59:17',0,NULL,'010b8231a9d3467cb73ad5206040a161',0),
('df1fb0a6528a41c08bd608d7289269b7','1.1','ccc',0,'C:\\upload\\style.css','1.1style.css','2017-06-20 11:17:14',0,NULL,'0b25ef49ae5741729406d3752d3e8909',3),
('dfbca005eae44f9cab0f033654e436c2','1.1','',0,'C:\\upload/Read Me.txt','1.1Read Me.txt','2017-07-03 15:23:40',0,NULL,'fc82e0eb174a4684bb727e84c3a701a8',0),
('e803165628a643519205382755c17869','1.2','好好好',0,'C:\\upload\\文件应用.txt','1.2文件应用.txt','2017-06-23 10:52:43',0,NULL,'0b25ef49ae5741729406d3752d3e8909',2),
('efdb8ce3dac4430b850f19a2d2223597','1.1','aa',0,'C:\\upload\\Read Me.txt','1.1Read Me.txt','2017-06-21 14:39:54',0,NULL,'0b25ef49ae5741729406d3752d3e8909',0),
('ss','ss','ss',0,'ss','ss','2017-06-16 13:47:22',0,NULL,'0b25ef49ae5741729406d3752d3e8909',0);

/*Table structure for table `ca_code` */

DROP TABLE IF EXISTS `ca_code`;

CREATE TABLE `ca_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识码',
  `code` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '编码',
  `value` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '编码对应的值',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `issuer` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `type` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '类型',
  `is_delete` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='CA_编码表';

/*Data for the table `ca_code` */

insert  into `ca_code`(`id`,`code`,`value`,`create_date`,`update_date`,`issuer`,`type`,`is_delete`) values 
(1,'1','type','2017-05-02 10:09:53','2017-05-02 10:09:55','张三','类型',0),
(2,'2','colour','2017-05-02 10:10:46','2017-05-02 10:10:48','张三','颜色',0),
(12,'5','shuba','2017-05-03 10:08:35','2017-05-03 10:08:34','shuba','类型',0),
(13,'5','shuba','2017-05-17 09:38:30','2017-05-17 09:38:30','shuba','类型',0),
(14,'5','shuba','2017-05-19 09:01:58','2017-05-19 09:01:58','shuba','类型',0),
(15,'5','shuba','2017-05-19 09:04:22','2017-05-19 09:04:22','shuba','类型',0),
(16,'5','shuba','2017-05-19 10:34:04','2017-05-19 10:34:03','shuba','类型',1),
(17,'5','shuba','2017-05-19 10:34:51','2017-05-19 10:34:51','shuba','类型',1),
(18,'74F63C7153454E38B7B222280B0975AC','ss','2017-05-25 10:21:49','2017-05-25 10:11:10','树八','aa',1),
(19,'74F54C7153454E38B7B222280B0975AC','cc','2017-05-26 09:44:10','2017-05-26 09:51:20','巴蜀','cc',1),
(20,'QWERTYUIOPLKJHGFDSAZXCVBNMLKJHGF','2','2017-05-25 20:32:56',NULL,'李四','2',0),
(21,'QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ','水电','2017-05-31 10:55:16','2017-05-31 13:57:45','是的','电费',0),
(22,'E3C850FE7839499CBFF10474E52B28CE','aa','2017-05-31 16:22:03','2017-05-26 15:07:40','吉塔','aa',0);

/*Table structure for table `ca_dictionary` */

DROP TABLE IF EXISTS `ca_dictionary`;

CREATE TABLE `ca_dictionary` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `dict_code` varchar(50) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator_id` varchar(50) DEFAULT NULL,
  `creator_name` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater_id` varchar(50) DEFAULT NULL,
  `updater_name` varchar(50) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  `deleter_id` varchar(50) DEFAULT NULL,
  `deleter_name` varchar(50) DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

/*Data for the table `ca_dictionary` */

insert  into `ca_dictionary`(`id`,`parent_id`,`name`,`dict_code`,`order`,`remark`,`create_date`,`creator_id`,`creator_name`,`update_date`,`updater_id`,`updater_name`,`is_delete`,`deleter_id`,`deleter_name`,`delete_date`) values 
(0,-1,'数据字典','root',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL),
(53,0,'资源类型','res_type',NULL,'资源的类型','2017-07-17 17:35:21','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(54,53,'GIS资源','gis',NULL,'GIS资源','2017-07-17 18:02:50','1','张三','2017-07-18 14:24:58',NULL,NULL,0,NULL,NULL,NULL),
(55,53,'文件资源','file',NULL,'文件资源','2017-07-17 18:04:50','1','张三','2017-07-18 14:24:54',NULL,NULL,0,NULL,NULL,NULL),
(56,53,'数据资源','data',NULL,'数据资源','2017-07-17 18:05:10','1','张三','2017-07-18 14:24:49',NULL,NULL,0,NULL,NULL,NULL),
(57,53,'服务资源','service',NULL,'服务资源','2017-07-17 18:05:24','1','张三','2017-07-18 14:24:43',NULL,NULL,0,NULL,NULL,NULL),
(58,0,'GIS服务类型','gis_type',NULL,'','2017-07-18 14:10:04','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(59,58,'WMS','wms',NULL,'','2017-07-18 14:11:31','1','张三','2017-07-18 14:25:27',NULL,NULL,0,NULL,NULL,NULL),
(60,58,'WMTS','wmts',NULL,'','2017-07-18 14:16:42','1','张三','2017-07-18 14:25:22',NULL,NULL,0,NULL,NULL,NULL),
(61,58,'WFS','wfs',NULL,'','2017-07-18 14:20:44','1','张三','2017-07-18 14:25:17',NULL,NULL,0,NULL,NULL,NULL),
(62,58,'WCS','wcs',NULL,'','2017-07-18 14:21:01','1','张三','2017-07-18 14:25:13',NULL,NULL,0,NULL,NULL,NULL),
(63,58,'OTHER','other',NULL,'','2017-07-18 14:23:56','1','张三','2017-07-18 14:25:09',NULL,NULL,0,NULL,NULL,NULL),
(64,0,'领域','domain',NULL,'','2017-07-18 14:26:52','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(65,64,'道路交通','dljt_001',NULL,'','2017-07-18 14:29:16','1','张三','2017-07-18 15:24:26',NULL,NULL,0,NULL,NULL,NULL),
(66,64,'安全信用','aqxy_002',NULL,'','2017-07-18 14:29:33','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(67,64,'经济建设','jjjs_003',NULL,'','2017-07-18 14:30:03','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(68,64,'教育信息','jyxx_004',NULL,'','2017-07-18 14:30:21','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(69,64,'电商数据','dssj_005',NULL,'','2017-07-18 14:30:40','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(70,64,'衣食行业','yshy_006',NULL,'','2017-07-18 14:31:32','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(71,64,'房产数据','fcsj_007',NULL,'','2017-07-18 14:31:50','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(72,64,'旅游信息','lyxx_008',NULL,'','2017-07-18 14:32:17','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(73,64,'新闻资讯','xwzx_009',NULL,'','2017-07-18 14:32:37','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(74,64,'医药信息','yyxx_010',NULL,'','2017-07-18 14:33:09','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(75,0,'保密级别','secret_level',NULL,'','2017-07-18 14:34:08','1','张三','2017-07-26 14:05:59',NULL,NULL,0,NULL,NULL,NULL),
(76,75,'公开','gk_01',NULL,'','2017-07-18 14:35:10','1','张三','2018-01-02 11:25:50',NULL,NULL,0,NULL,NULL,NULL),
(77,75,'秘密','mm_02',NULL,'','2017-07-18 14:35:25','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(78,75,'机密','jm_03',NULL,'','2017-07-18 14:35:36','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(79,75,'绝密','jm_04',NULL,'','2017-07-18 14:35:48','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(80,0,'主题分类','theme_type',NULL,'','2017-07-18 14:36:40','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(81,80,'生态环境','sthj_001',NULL,'','2017-07-18 14:38:26','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(82,80,'安全键康','aqjk_002',NULL,'','2017-07-18 14:38:47','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(83,80,'金融服务','jrfw_003',NULL,'','2017-07-18 14:41:07','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(84,80,'环境监测','hjjc_004',NULL,'','2017-07-18 14:42:00','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(85,80,'通讯舆情','txyq_005',NULL,'','2017-07-18 14:42:24','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(86,80,'电子商务','dzsw_006',NULL,'','2017-07-18 14:42:45','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(87,80,'政务公开','zwgk_007',NULL,'','2017-07-18 14:43:14','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(88,0,'更新频率','update_frequency',NULL,'','2017-07-18 14:44:01','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(89,88,'一个月','month_1',NULL,'','2017-07-18 14:44:36','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(90,88,'三个月','month_3',NULL,'','2017-07-18 14:44:46','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(91,88,'半年','month_6',NULL,'','2017-07-18 14:44:58','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(92,88,'一年','month_12',NULL,'','2017-07-18 14:45:18','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(93,88,'二年','month_24',NULL,'','2017-07-18 14:45:28','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(94,0,'授权对象类型','target_type',NULL,'','2017-07-18 14:46:49','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(95,94,'用户','user',NULL,'','2017-07-18 14:47:00','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(96,94,'角色','role',NULL,'','2017-07-18 14:47:07','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(97,94,'机构','org',NULL,'','2017-07-18 14:47:17','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(98,0,'审核状态','audit_status',NULL,'','2017-07-18 14:48:05','1','张三','2017-07-18 14:48:16',NULL,NULL,0,NULL,NULL,NULL),
(99,98,'待审核','pending',NULL,'','2017-07-18 14:48:53','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(100,98,'通过','approve',NULL,'','2017-07-18 14:49:30','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(101,98,'不通过','unapproved',NULL,'','2017-07-18 14:50:29','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(102,98,'驳回','rejected',NULL,'','2017-07-18 14:51:11','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(103,0,'目录发布状态','publish_status',NULL,'','2017-07-18 14:52:09','1','张三','2017-07-18 14:52:28',NULL,NULL,0,NULL,NULL,NULL),
(104,0,'目录发布类型','publish_type',NULL,'','2017-07-18 14:52:45','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(105,103,'已发布','published',NULL,'','2017-07-18 14:53:17','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(106,103,'未发布','unpublished',NULL,'','2017-07-18 14:53:36','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(107,104,'内网','inside',NULL,'','2017-07-18 14:54:12','1','张三','2017-07-27 09:07:03',NULL,NULL,0,NULL,NULL,NULL),
(108,104,'外网','outside',NULL,'','2017-07-18 14:54:25','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(109,104,'手机端','mobile',NULL,'','2017-07-18 14:54:46','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(110,0,'资源发布类型','release_type',NULL,'','2017-07-18 14:55:22','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(111,110,'GIS资源','gis',NULL,'','2017-07-18 14:55:55','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(112,110,'文件资源','file',NULL,'','2017-07-18 14:56:06','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(113,110,'数据资源','data',NULL,'','2017-07-18 14:56:14','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(114,0,'权限来源','authority_source',NULL,'','2017-07-18 14:57:34','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(115,114,'授权','authorize',NULL,'','2017-07-18 14:58:06','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(116,114,'申请','apply',NULL,'','2017-07-18 14:58:22','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(117,0,'请求方式','request_type',NULL,'','2017-07-18 14:59:13','1','张三',NULL,NULL,NULL,0,NULL,NULL,NULL),
(118,117,'POST','post',NULL,'服务资源请求方式：POST','2017-07-18 14:59:24','1','张三','2017-07-18 15:21:35',NULL,NULL,0,NULL,NULL,NULL),
(119,117,'GET','get',NULL,'服务资源请求方式：GET','2017-07-18 14:59:33','1','张三','2017-07-18 15:21:22',NULL,NULL,0,NULL,NULL,NULL);

/*Table structure for table `ca_file_info` */

DROP TABLE IF EXISTS `ca_file_info`;

CREATE TABLE `ca_file_info` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键ID',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '文件名',
  `uf_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '对应的upload_file的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ca_file_info` */

/*Table structure for table `ca_loginfo` */

DROP TABLE IF EXISTS `ca_loginfo`;

CREATE TABLE `ca_loginfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `type` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '日志类型',
  `Login_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '登陆ip',
  `Create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `Update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `issuer` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='CA_日志信息表';

/*Data for the table `ca_loginfo` */

insert  into `ca_loginfo`(`id`,`user_id`,`type`,`Login_ip`,`Create_date`,`Update_date`,`issuer`) values 
(1,1,'添加','192.168.1.1','2017-05-02 10:12:24','2017-05-02 10:12:26','张三'),
(2,2,'修改','192.168.1.2','2017-05-02 10:13:33','2017-05-02 10:13:34','李四'),
(8,1,'查询',NULL,'2017-05-03 10:08:38','2017-05-03 10:08:38','张三'),
(9,1,'查询',NULL,'2017-05-17 09:38:33','2017-05-17 09:38:33','张三'),
(10,1,'查询',NULL,'2017-05-19 09:02:01','2017-05-19 09:02:01','张三'),
(11,1,'查询',NULL,'2017-05-22 14:26:07','2017-05-22 14:26:07','张三'),
(12,1,'查询',NULL,'2017-05-22 14:28:23','2017-05-22 14:28:23','张三'),
(13,1,'查询',NULL,'2017-05-22 14:41:24','2017-05-22 14:41:24','张三'),
(14,1,'查询',NULL,'2017-05-22 14:47:49','2017-05-22 14:47:49','张三'),
(15,1,'查询',NULL,'2017-05-22 14:50:40','2017-05-22 14:50:40','张三'),
(18,2,'飞费','192.168.1.1','2017-05-31 10:06:27','2017-05-31 10:10:17','才的');

/*Table structure for table `ca_org_manager` */

DROP TABLE IF EXISTS `ca_org_manager`;

CREATE TABLE `ca_org_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `org_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `is_lock` int(11) DEFAULT '0' COMMENT '是否启用:0启用 1停用',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `creator_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人姓名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `locker_id` bigint(20) DEFAULT NULL COMMENT '停用人ID',
  `locker_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '停用人姓名',
  `lock_date` datetime DEFAULT NULL COMMENT '停用时间',
  `deleter_id` bigint(20) DEFAULT NULL COMMENT '删除人ID',
  `deleter_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人姓名',
  `delete_date` datetime DEFAULT NULL COMMENT '删除时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否已删除:1已删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ca_org_manager` */

insert  into `ca_org_manager`(`id`,`org_id`,`user_id`,`is_lock`,`creator_id`,`creator_name`,`create_date`,`locker_id`,`locker_name`,`lock_date`,`deleter_id`,`deleter_name`,`delete_date`,`is_delete`) values 
(109,3,9,0,1,'张三','2017-07-11 10:19:57',NULL,NULL,NULL,NULL,NULL,NULL,0),
(110,3,108,0,1,'张三','2017-07-11 10:19:57',NULL,NULL,NULL,NULL,NULL,NULL,0),
(111,3,112,0,1,'张三','2017-07-11 10:19:57',NULL,NULL,NULL,NULL,NULL,NULL,0),
(112,3,118,0,1,'张三','2017-07-11 10:19:57',NULL,NULL,NULL,NULL,NULL,NULL,0),
(113,3,123,0,1,'张三','2017-07-11 10:19:57',NULL,NULL,NULL,NULL,NULL,NULL,0),
(114,2,9,0,1,'张三','2017-07-11 10:53:03',NULL,NULL,NULL,NULL,NULL,NULL,0),
(115,2,108,0,1,'张三','2017-07-11 10:53:03',NULL,NULL,NULL,NULL,NULL,NULL,0),
(116,2,112,1,1,'张三','2017-07-11 10:53:03',1,'张三','2017-07-11 14:29:22',NULL,NULL,NULL,0),
(117,2,118,1,1,'张三','2017-07-11 10:53:03',1,'张三','2017-07-11 14:29:27',NULL,NULL,NULL,0),
(118,2,123,0,1,'张三','2017-07-11 10:53:03',NULL,NULL,NULL,NULL,NULL,NULL,0),
(119,2,2,0,1,'张三','2017-07-11 11:36:06',NULL,NULL,NULL,NULL,NULL,NULL,0),
(120,2,94,0,1,'张三','2017-07-11 11:36:06',NULL,NULL,NULL,NULL,NULL,NULL,0),
(121,2,110,0,1,'张三','2017-07-11 11:36:06',NULL,NULL,NULL,NULL,NULL,NULL,0),
(122,2,8,0,1,'张三','2017-07-12 11:29:49',NULL,NULL,NULL,NULL,NULL,NULL,0),
(123,1,9,1,1,'张三','2017-07-12 11:30:14',1,'张三','2017-07-12 11:32:00',1,'张三','2017-07-12 11:32:20',1),
(124,1,110,0,1,'张三','2017-07-12 11:30:14',NULL,NULL,NULL,1,'张三','2017-07-12 11:32:09',1),
(125,1,123,0,1,'张三','2017-07-12 11:30:14',NULL,NULL,NULL,1,'张三','2017-07-12 11:32:20',1),
(126,1,94,1,1,'张三','2017-07-12 11:31:54',1,'张三','2017-07-12 11:32:24',1,'张三','2017-07-12 11:32:31',1),
(127,1,108,0,1,'张三','2017-07-12 11:31:54',NULL,NULL,NULL,1,'张三','2017-07-12 11:32:31',1),
(128,1,2,0,1,'张三','2017-07-12 11:32:31',NULL,NULL,NULL,NULL,NULL,NULL,0),
(129,1,9,0,1,'张三','2017-07-12 11:32:31',NULL,NULL,NULL,NULL,NULL,NULL,0),
(130,1,110,0,1,'张三','2017-07-12 11:32:31',NULL,NULL,NULL,NULL,NULL,NULL,0),
(131,1,112,0,1,'张三','2017-07-12 11:32:31',NULL,NULL,NULL,1,'张三','2017-12-28 12:58:14',1),
(132,1,123,0,1,'张三','2017-07-12 11:32:31',NULL,NULL,NULL,NULL,NULL,NULL,0),
(133,3,7,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0),
(134,3,8,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0),
(135,3,11,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0),
(136,3,87,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0),
(137,3,90,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0),
(138,3,95,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0),
(139,3,101,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0),
(140,3,110,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0),
(141,3,116,0,1,'张三','2017-07-18 10:49:35',NULL,NULL,NULL,NULL,NULL,NULL,0);

/*Table structure for table `ca_organization` */

DROP TABLE IF EXISTS `ca_organization`;

CREATE TABLE `ca_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识码',
  `parent_id` bigint(20) NOT NULL COMMENT '父机构的id',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '机构名称',
  `code` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '机构编码',
  `description` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '机构描述',
  `create_date` datetime DEFAULT NULL COMMENT '机构创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '最后的修改人的id',
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '机构地址',
  `phone` char(12) COLLATE utf8_bin DEFAULT NULL COMMENT '机构的联系电话',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '与机构联系的邮箱',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '组织机构状态0正常，1表示删除',
  `issuer` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `link_man` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='CA_组织机构表';

/*Data for the table `ca_organization` */

insert  into `ca_organization`(`id`,`parent_id`,`name`,`code`,`description`,`create_date`,`update_date`,`updater_id`,`address`,`phone`,`email`,`is_delete`,`issuer`,`link_man`) values 
(0,-1,'云平台','root',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL),
(1,0,'国土局','c1','desc２','2017-05-25 09:54:46','2017-07-04 15:53:33',NULL,'cheshicheshi','13138212523','tset@163.com',0,'系统管理员','习近'),
(2,0,'人保局','c2','desc','2017-05-24 13:55:38','2017-06-27 10:00:01',NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李克'),
(3,0,'公安局','c3','desc','2017-06-10 13:56:45',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(4,0,'环卫局','c4','desc','2017-05-12 13:55:41',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(5,3,'洪山分局','c5','desc','2017-05-05 13:55:49','2017-05-31 11:53:58',NULL,'address　地址','131282121523','tset@163.com',0,'系统管理员','lessonss'),
(6,3,'武昌分局','c6','desc','2017-06-02 13:56:43',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(7,3,'江汉分局','c7','desc','2017-05-15 13:55:46',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(8,3,'汉阳分局','c8','desc','2017-05-07 13:55:50',NULL,NULL,'address　地址','13128122523','tset@163.com',0,'系统管理员','李伟'),
(9,6,'财务科','c9','desc','2017-05-22 13:55:53',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(10,6,'监查科','c10','desc','2017-05-05 13:55:44',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(11,6,'事务科','c11','desc','2017-05-08 13:56:33',NULL,NULL,'address　地址','13128211523','tset@163.com',0,'系统管理员','李伟'),
(12,6,'民生科','c12','desc','2017-05-24 13:56:30',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(13,6,'医务科','c13','desc','2017-05-31 13:56:40',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(14,9,'财务一部','c14','desc','2017-05-05 13:56:35',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(15,9,'财务二部','c15','desc','2017-05-06 13:56:38',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(16,9,'财务三部','c16','desc','2017-05-09 13:55:55',NULL,NULL,'address　地址','131282212523','tset@163.com',0,'系统管理员','李伟'),
(17,9,'财务四部','c17','desc','2017-05-12 13:55:57',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(18,9,'财务五部','c18','desc','2017-05-01 13:55:59',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(19,16,'事务三组','74F54C7153454E38B7B222280B0975AC','desc','2017-05-26 09:44:10','2017-05-26 10:00:28',NULL,'武汉','13128212523','tset@163.com',0,'系统管理员','李伟'),
(20,16,'事务一组','c20','desc','2017-05-04 13:56:08',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(21,16,'事务二组','c21','desc','2017-05-03 13:56:06',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(22,16,'事务三组','c22','desc','2017-05-16 13:56:04',NULL,NULL,'address　地址','13128212523','tset@163.com',0,'系统管理员','李伟'),
(53,22,'张三','E3C850FE7839499CBFF10474E52B28CE','desc','2017-05-26 15:04:21','2017-04-27 11:28:40',NULL,'董斌','13128212523','tset@163.com',0,'系统管理员','李伟'),
(54,22,'事务四组','E3C850FE7839499CBFF10474E52B28CE','desc','2017-05-26 15:07:36','2017-05-26 15:07:40',11,'李四','13128212523','tset@163.com',0,'系统管理员','李伟'),
(55,22,'事务五组','E3C850FE7839499CBFF10474E52B28CE','desc','2017-05-26 15:12:10','2017-05-26 15:11:40',11,'武汉','13128212523','tset@163.com',0,'系统管理员','李伟'),
(56,22,'事务六组','E3C850FE7839499CBFF10474E52B28CE','desc','2017-05-26 16:05:40','2017-05-26 16:13:05',11,'武汉','13128212523','tset@163.com',0,'系统管理员','李伟'),
(57,7,'1','EQFE913212343KDWQEIEKQWEIFJEWQWE','desc','2017-05-26 18:04:02',NULL,NULL,'address　地址','4','5',1,'系统管理员','李伟'),
(58,1,'测45试','AAAAAC',NULL,'2017-05-27 14:41:59','2017-05-31 11:48:54',NULL,'士大45夫似的',NULL,NULL,1,'系45管理员',NULL),
(59,4,'武昌1环卫局','BAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA','测试111111','2017-05-31 14:11:51','2017-06-02 16:28:09',NULL,'武汉市武昌区','18507108472','a@126.com',0,'张三三','李克强'),
(60,4,'江夏环卫局','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA','测试','2017-05-31 14:46:37','2017-05-31 16:49:01',NULL,'测试','12345678','@163.com',1,'张三三','LI'),
(61,60,'','','','2017-05-31 14:57:22','2017-05-31 15:01:51',NULL,'','','',1,'',''),
(62,1,'测试','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',NULL,'2017-05-31 16:33:03',NULL,NULL,'士大夫似的',NULL,NULL,0,'系管理员',NULL),
(63,5,'洪山','E3C850FE7839499CBFF10474E52B28CE',NULL,'2017-05-31 16:39:50',NULL,5,'吉塔','13128212523','tset@163.com',0,'吉塔','aa'),
(64,4,'汉阳环卫局','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA','测试','2017-05-31 16:40:58',NULL,NULL,'测试','18507108471','test@163.com',0,'李四四','李四四'),
(65,4,'江夏环卫局','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA','测试','2017-05-31 16:46:28',NULL,NULL,'测试','18507108471','test@163.com',0,'李四四','李四四'),
(66,4,'汉口环卫局','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA','测试','2017-05-31 16:48:26',NULL,NULL,'测试','18507108471','test@163.com',0,'王五五','王五五'),
(67,4,'武昌环卫局','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA','测试','2017-06-01 09:06:22',NULL,NULL,'测试','18520147954','test@163.com',0,NULL,'LISS'),
(68,1,'测试1','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',NULL,'2017-06-01 09:41:53','2017-06-14 13:33:45',NULL,'士大夫似的',NULL,NULL,1,'系管理员',NULL),
(69,2,'一级机构','yjjg','asdfbb','2017-06-01 16:20:10','2017-06-01 16:23:46',NULL,'asdf','13828282821','a@a.a',0,NULL,'测试人1'),
(70,69,'二级机构','ejjg','ddd','2017-06-01 16:22:10',NULL,NULL,'ccc','13133332222','b@b.b',0,NULL,'王五'),
(71,1,'1测试','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',NULL,'2017-06-01 16:40:33',NULL,NULL,'士大夫似的',NULL,NULL,1,'2系管理员',NULL),
(72,1,'4','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',NULL,'2017-06-01 16:40:59',NULL,NULL,'士大夫似的',NULL,NULL,1,'2系管理员',NULL),
(73,1,'4','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',NULL,'2017-06-01 16:41:17',NULL,NULL,'r',NULL,NULL,1,'2系管理员',NULL),
(74,1,'4','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',NULL,'2017-06-01 16:41:22',NULL,NULL,'r',NULL,NULL,0,'2',NULL),
(75,1,'4','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',NULL,'2017-06-01 16:41:27',NULL,NULL,'r',NULL,NULL,1,'',NULL),
(76,6,'武昌分局','AAAA','aa','2017-06-08 16:18:06',NULL,NULL,'武汉','15872549525','dbongin@163.com',0,'张三','董斌'),
(77,4,'光谷环卫局','aa','光谷','2017-06-08 16:29:43',NULL,NULL,'光谷','15872549525','tset@163.com',0,'张三','张三'),
(78,1,'江夏','gfd','测试','2017-06-13 14:10:22',NULL,NULL,'测试','18507108471','test@163.com',1,'张三','郭嘉'),
(79,1,'江夏','hfgdh','测试','2017-06-13 14:12:24',NULL,NULL,'测','18507108471','test@163.com',1,'张三','郭嘉'),
(80,1,'江夏','gfdsg','测试','2017-06-13 14:14:32',NULL,NULL,'车位','18507108471','test@163.com',1,'张三','郭嘉'),
(81,0,'111','222','cccc','2017-06-14 13:30:58',NULL,NULL,'b.b.b','13111111111','a@a.a',1,'张三','333'),
(82,62,'222ddd','222','dddddd','2017-06-14 13:31:31','2017-06-14 13:33:30',NULL,'cccccc','13188899898','b@b.b',1,'张三','222'),
(84,4,'试试1121111','ss','测试1','2017-06-14 17:46:34','2017-06-14 17:54:20',NULL,'测试1','18524741874','tes@163.com',1,'张三','试试'),
(85,4,'试试','dfs','试试','2017-06-15 09:23:29',NULL,NULL,'试试','18521741874','test@163.com',1,'张三','ss'),
(86,4,'试试','ss','测试','2017-06-15 11:00:44',NULL,NULL,'测试','18524718471','test@163.com',1,'张三','ss'),
(88,4,'测试父节点','ss','测试','2017-06-15 11:13:50',NULL,NULL,'测试','18527108471','test@163.com',0,'张三','ss'),
(89,88,'测试子节点1','ss','测试','2017-06-15 11:14:30',NULL,NULL,'测试','18521741847','test@163.com',1,'张三','ss'),
(90,0,'aaa','bbb','ccc','2017-06-15 11:29:53',NULL,NULL,'bbb','13133333333','aa@aa.aa',1,'张三','ccc'),
(91,90,'bbb1','bb','测试','2017-06-15 11:30:55','2017-06-27 17:37:29',NULL,'测试','18501748471','test@163.com',1,'张三','bb'),
(92,91,'ccc','ddd','asdf','2017-06-15 11:50:45',NULL,NULL,'asdf','13133333333','aa@aa.acom',1,'张三','eee'),
(93,90,'ccc','ccc','测试','2017-06-15 13:27:59',NULL,NULL,'测是','18524107471','test@163.com',1,'张三','dd'),
(94,88,'测试子节点2','dd','测试','2017-06-15 13:33:23',NULL,NULL,'测试','18520107471','test@163.com',0,'张三','dd'),
(95,3,'青山分局','111','bbb','2017-06-27 09:37:59',NULL,NULL,'aaa','13511111111','test@163.com',0,'张三','222'),
(97,62,'aaa','bbb','ccc','2017-06-27 20:13:03',NULL,NULL,'bbb','13511111111','2222@a.a',1,'张三','ccc'),
(98,90,'托福','b2','是','2017-06-28 10:32:13',NULL,NULL,'广寒','18501748471','test89@163.com',1,'张三','bb'),
(99,90,'bbb1','vv','水电费','2017-06-28 10:34:07',NULL,NULL,'找茬','13133333333','vv90@163.com',1,'张三','vv'),
(100,0,'aaa','bbb','是佛','2017-06-28 10:36:04',NULL,NULL,'是佛','13133333333','aa45@163.com',1,'张三','ccc'),
(101,0,'警察局','c100','ddd','2017-07-04 13:29:38','2017-07-04 13:30:05',NULL,'武汉','15872549525','test@163.com',1,'张三','张三'),
(102,0,'fasdf','fsd','asdf','2018-02-27 11:30:14',NULL,NULL,'asdf','15288741250','2@3.c',1,'张三','aasdf'),
(103,102,'fsdfa','sadf','sadf','2018-02-27 11:30:48',NULL,NULL,'fsdaf','15211305210','15211305210@3.c',1,'张三','15211305210'),
(104,1,'fsad','fsadf','','2018-02-27 11:49:17',NULL,NULL,'fads','15277456321','fasdf@3.c',1,'张三','asdf'),
(105,62,'fsdaf','sdf','','2018-02-27 12:28:16',NULL,NULL,'fdsa','15877413210','11@3.c',1,'张三','fsda'),
(106,1,'asdfa','fsdfaf','adsf','2018-03-06 11:05:19',NULL,NULL,'asdf','15874123201','22@3.c',1,'张三','asdff'),
(107,1,'fdsafa','dfasf','adfaf','2018-03-06 11:06:14',NULL,NULL,'fsadf','15288741203','safd@3.c',1,'张三','sdafdf');

/*Table structure for table `ca_permission` */

DROP TABLE IF EXISTS `ca_permission`;

CREATE TABLE `ca_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识码',
  `parent_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '父权限的ID',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `code` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '权限编码',
  `type` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '权限分类',
  `is_delete` int(2) NOT NULL DEFAULT '0' COMMENT '权限状态0正常，1表示删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `issuer` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `order_num` int(3) NOT NULL DEFAULT '0' COMMENT '菜单的显示顺序,用于排序',
  `app` varbinary(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='CA_权限属性表';

/*Data for the table `ca_permission` */

insert  into `ca_permission`(`id`,`parent_id`,`name`,`code`,`type`,`is_delete`,`create_date`,`update_date`,`issuer`,`order_num`,`app`) values 
(0,'-1','根结点','root','menu',0,NULL,NULL,NULL,0,'ca'),
(1,'0','机构管理','org','menu',0,NULL,NULL,NULL,1,'ca'),
(2,'0','角色管理','role','menu',0,NULL,NULL,NULL,2,'ca'),
(3,'0','用户管理','user','menu',0,NULL,NULL,NULL,3,'ca'),
(101,'1','新增','org:add','button',0,NULL,NULL,NULL,511,'ca'),
(102,'1','修改','org:modify','button',0,NULL,NULL,NULL,512,'ca'),
(103,'1','删除','org:remove','button',0,NULL,NULL,NULL,513,'ca'),
(104,'1','查询','org:search','button',0,NULL,NULL,NULL,514,'ca'),
(105,'2','新增','role:add','button',0,NULL,NULL,NULL,111,'ca'),
(106,'2','修改','role:modify','button',0,NULL,NULL,NULL,112,'ca'),
(107,'2','删除','role:remove','button',0,NULL,NULL,NULL,113,'ca'),
(108,'2','查询','role:search','button',0,NULL,NULL,NULL,114,'ca'),
(109,'3','新增','user:add','button',0,'2017-05-02 15:00:33','2017-05-02 15:00:33','张三',211,'ca'),
(110,'3','修改','user:modify','button',0,'2017-05-02 10:15:44','2017-05-02 10:15:46','张三',212,'ca'),
(111,'3','删除','user:remove','button',0,'2017-05-02 10:16:24','2017-05-02 10:16:25','李四',213,'ca'),
(112,'3','查询','user:search','button',0,NULL,NULL,NULL,214,'ca'),
(139,'0','应用管理','projectApp','menu',0,'2017-06-14 09:39:25',NULL,'张三',4,'ca'),
(140,'139','新增','projectApp:add','button',0,NULL,NULL,NULL,311,'ca'),
(141,'139','修改','projectApp:modify','button',0,NULL,NULL,NULL,312,'ca'),
(142,'139','删除','projectApp:remove','button',0,NULL,NULL,NULL,313,'ca'),
(143,'139','查询','projectApp:search','button',0,NULL,NULL,NULL,314,'ca'),
(151,'0','系统字典','dictionary','menu',0,NULL,NULL,NULL,15,'ca'),
(200,'0','仪表盘','dashboard','menu',0,NULL,NULL,NULL,190,'rmc'),
(210,'0','我的任务','myTask','menu',0,NULL,NULL,NULL,200,'rmc'),
(211,'210','我的待办任务','myTaskDoing','menu',0,NULL,NULL,NULL,210,'rmc'),
(212,'210','我的已办任务','myTaskDone','menu',0,NULL,NULL,NULL,220,'rmc'),
(220,'0','资源目录','resCatalog','menu',0,NULL,NULL,NULL,230,'rmc'),
(221,'220','目录管理','catalogManage','menu',0,NULL,NULL,NULL,240,'rmc'),
(222,'220','资源编目','catalog','menu',0,NULL,NULL,NULL,250,'rmc'),
(230,'0','资源注册','resRegister','menu',0,NULL,NULL,NULL,260,'rmc'),
(231,'230','我的注册列表','myRegisterList','menu',0,NULL,NULL,NULL,270,'rmc'),
(232,'230','GIS资源','gisRegisterList','menu',0,NULL,NULL,NULL,280,'rmc'),
(233,'230','数据资源','dataRegisterList','menu',0,NULL,NULL,NULL,290,'rmc'),
(234,'230','服务资源','serviceRegister','menu',0,NULL,NULL,NULL,300,'rmc'),
(235,'230','文件资源','fileRegister','menu',0,NULL,NULL,NULL,310,'rmc'),
(240,'0','资源发布','resRelease','menu',0,NULL,NULL,NULL,320,'rmc'),
(241,'240','我的发布列表','myReleaseList','menu',0,NULL,NULL,NULL,330,'rmc'),
(242,'240','GIS资源','gisReleaseList','menu',0,NULL,NULL,NULL,340,'rmc'),
(243,'240','数据资源','dataReleaseList','menu',0,NULL,NULL,NULL,350,'rmc'),
(244,'240','文件资源','fileReleaseList','menu',0,NULL,NULL,NULL,360,'rmc'),
(250,'0','资源配置','resConfig','menu',0,NULL,NULL,NULL,370,'rmc'),
(251,'250','资源属性管理','resPropConfig','menu',0,NULL,NULL,NULL,380,'rmc'),
(252,'250','元数据管理','resMetaConfig','menu',0,NULL,NULL,NULL,390,'rmc'),
(253,'250','代理配置','resProxyConfig','menu',0,NULL,NULL,NULL,400,'rmc'),
(254,'250','缓存配置','resCacheConfig','menu',0,NULL,NULL,NULL,410,'rmc'),
(260,'0','专题管理','subject','menu',0,NULL,NULL,NULL,420,'rmc'),
(261,'260','专题配置','subjectConfig','menu',0,NULL,NULL,NULL,430,'rmc'),
(262,'260','专题授权','subjectAuth','menu',0,NULL,NULL,NULL,440,'rmc'),
(270,'0','资源权限管理','resAuthority','menu',0,NULL,NULL,NULL,450,'rmc'),
(271,'270','资源权属管理','resOwnerManage','menu',0,NULL,NULL,NULL,460,'rmc'),
(272,'270','资源授权','resAuth','menu',0,NULL,NULL,NULL,470,'rmc'),
(280,'0','日志管理','logManage','menu',0,NULL,NULL,NULL,480,'rmc'),
(281,'280','日志统计','logDashboard','menu',0,NULL,NULL,NULL,490,'rmc'),
(282,'280','资源访问日志','resVisitLog','menu',0,NULL,NULL,NULL,500,'rmc'),
(283,'280','资源注册日志','resRegisterLog','menu',0,NULL,NULL,NULL,510,'rmc'),
(284,'280','资源发布日志','resReleaseLog','menu',0,NULL,NULL,NULL,520,'rmc'),
(285,'280','系统操作日志','sysOperLog','menu',0,NULL,NULL,NULL,530,'rmc'),
(290,'0','行业资讯管理','industryNewsManage','menu',0,NULL,NULL,NULL,540,'rmc'),
(300,'0','通知公告管理','notificationManage','menu',0,NULL,NULL,NULL,550,'rmc'),
(310,'0','问题管理','problemManage','menu',0,NULL,NULL,NULL,560,'rmc'),
(320,'0','典型案例管理','typicalCaseManage','menu',0,NULL,NULL,NULL,570,'rmc');

/*Table structure for table `ca_project_infor` */

DROP TABLE IF EXISTS `ca_project_infor`;

CREATE TABLE `ca_project_infor` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '项目唯一主键',
  `project_description` varchar(2184) COLLATE utf8_bin NOT NULL COMMENT '项目简介',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `project_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '项目名称',
  `app_id` varchar(100) COLLATE utf8_bin NOT NULL COMMENT 'APPID',
  `project_manager` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '项目负责人',
  `contact_number` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ca_project_infor` */

insert  into `ca_project_infor`(`id`,`project_description`,`create_date`,`update_date`,`project_name`,`app_id`,`project_manager`,`contact_number`,`email`) values 
('010b8231a9d3467cb73ad5206040a161','','2017-07-04 14:33:23',NULL,'测试3','vfgff','ff','1587255',''),
('0b25ef49ae5741729406d3752d3e8909','云平台测试','2017-06-15 10:28:46',NULL,'云平台','com.ztemap','张三','15871421224',NULL),
('aa','aa','2017-06-16 11:45:21','2017-06-27 11:19:20','aa','aa','aa','aa','aa'),
('f1a43788b8374a7eba63c1a7fd7d4d25','','2017-07-12 15:07:19','2017-07-19 10:07:44','dd','cc','cc','15872549525','test@163.com'),
('fc82e0eb174a4684bb727e84c3a701a8','测试','2017-06-26 11:14:42','2017-07-04 14:32:34','测试','cs','测试','18507108471','test@163.com');

/*Table structure for table `ca_role` */

DROP TABLE IF EXISTS `ca_role`;

CREATE TABLE `ca_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识码',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `code` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '角色编码',
  `description` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '角色的相关描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `issuer` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '角色状态0正常，1表示删除',
  `appcode` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '应用编码',
  `orgId` bigint(20) NOT NULL COMMENT '所属机构',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='CA_角色信息表';

/*Data for the table `ca_role` */

insert  into `ca_role`(`id`,`name`,`code`,`description`,`create_date`,`update_date`,`issuer`,`is_delete`,`appcode`,`orgId`) values 
(1,'管理员','admin','啦啦啦啦','2017-05-02 10:17:37','2017-06-01 15:09:44','机构管理员',0,'QWERTYUIOPLKJHGFDSAZXCVBNMLKJHG',1),
(2,'超级管理员','administrator','蛮三刀','2017-05-18 19:41:41','2017-06-01 15:10:26','机构管理员',0,'wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww',1),
(34,'管理员','QWERTYUIOPLKJHGFDSAZXCVBNMLKJHGF','dddd','2017-05-24 18:04:08','2017-06-01 16:24:37','机构管理员',1,'QWERTYUIOPLKJHGFDSAZXCVBNMLKJHGF',2),
(35,'董斌','QWERTYUIOPLKJHGFDSAZXCVBNMLKJHGF','dccceee','2017-05-25 19:59:36','2017-06-15 13:14:44','机构管理员',0,'QWERTYUIOPLKJHGFDSAZXCVBNMLKJHGF',4),
(36,'这些3','SSDSSSSSSSSSSSSSSSSSSSSSSSSSSSSS','XF3G','2017-05-27 10:43:42','2017-05-27 10:50:12','相册3',1,',jdbcType=BIGINT',1),
(37,'role','aaa',NULL,NULL,NULL,'bbb',1,'eee',1),
(38,'role2','aaaq1',NULL,NULL,NULL,'bbb1',1,'eee1',1),
(39,'role3','aaa2',NULL,NULL,NULL,'bbb2',0,'eee2',1),
(40,'role4','aaa3',NULL,NULL,NULL,'bbb3',1,'eee3',1),
(41,'role5','aaa4',NULL,NULL,NULL,'bbb4',1,'eee4',1),
(42,'role6','aaa5',NULL,NULL,NULL,'bbb5',1,'eee5',1),
(43,'role7','aaa6',NULL,NULL,NULL,'bbb6',1,'eee6',2),
(44,'role8','aaa7',NULL,NULL,NULL,'bbb7',1,'eee7',1),
(45,'洪口','E3C850FE7839499CBFF10474E52B28CE','测试','2017-05-31 17:00:07','2017-06-01 15:08:50','机构管理员',0,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',5),
(46,'武林至尊','SSDSSSSSSSSSSSSSSSSSSSSSSSSSSSSS','测试eee','2017-06-01 09:53:50','2017-06-01 16:25:24','机构管理员',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',0),
(47,'李修贤1','E3C850FE7839499CBFF10474E52B28CE','测试','2017-06-01 14:45:04','2017-06-27 09:37:26','机构管理员',0,'E3C850FE7839499CBFF10474E52B28CE',2),
(50,'洪山','E3C850FE7839499CBFF10474E52B28CE','测试','2017-06-05 14:34:50','2017-06-27 20:50:29','机构管理员',0,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',2),
(51,'洪山','E3C850FE7839499CBFF10474E52B28CE','测试','2017-06-05 14:40:17',NULL,'吉塔',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',5),
(52,'洪山','E3C850FE7839499CBFF10474E52B28CE','测试','2017-06-05 14:40:27',NULL,'吉塔FFD',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',5),
(53,'洪山','E3C850FE7839499CBFF10474E52B28CE','测试','2017-06-05 14:40:54',NULL,'吉塔FFD',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',22),
(54,'洪山','E3C850FE7839499CBFF10474E52B28CE','','2017-06-05 14:41:27',NULL,'吉塔FFD',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',22),
(55,'洪山','E3C850FE7839499CBFF10474E52','测试','2017-06-05 14:42:24',NULL,'吉塔FFD',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',2),
(56,'董永','E3C850FE7839499CBFF10474E52B2888',NULL,'2017-06-05 16:54:58','2017-06-05 16:57:25','董永',1,'E3C850FE7839499CBFF10474E52B2899',7001),
(57,'国画','FDSFSD','CES','2017-06-13 13:26:58','2017-06-27 08:50:44','机构管理员',1,'DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',2),
(58,'高度','fdsg','FDS','2017-06-13 13:37:25',NULL,'张三',1,'SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS',1),
(59,'就会','fdh','GHH','2017-06-13 14:05:21',NULL,'张三',1,'DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD',1),
(60,'发过','gfds','FDS','2017-06-13 14:07:39',NULL,'张三',1,'FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF',1),
(61,'测试bbb','bbb','测试','2017-06-26 10:35:04',NULL,'张三',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',91),
(62,'科长','001','承认','2017-06-27 09:38:21','2017-06-28 09:24:41','机构管理员',0,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',3),
(63,'测试新增','ddd','CES','2017-06-29 13:38:15',NULL,'张三',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',1),
(64,'科长的','DFFF','CES','2017-07-03 17:16:22','2017-07-04 11:17:04','机构管理员',0,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB',1),
(65,'科长','df','dfdf ','2017-07-04 10:32:24',NULL,'张三',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',0),
(66,'科长7','admin7','dfs ','2017-07-04 10:54:32','2017-12-28 11:49:04','机构管理员',0,'sad',1),
(67,'李珉宇','sas','测试角色','2017-07-04 16:41:57',NULL,'张三',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',69),
(68,'李珉宇','sa','1','2017-07-04 16:59:58',NULL,'张三',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',0),
(69,'小角色','sa','111','2017-07-04 17:47:11',NULL,'张三',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',0),
(70,'小小角色','sa','121','2017-07-04 17:47:24',NULL,'张三',1,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',0);

/*Table structure for table `ca_role_permission` */

DROP TABLE IF EXISTS `ca_role_permission`;

CREATE TABLE `ca_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识码',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `issuer` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1963 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='CA_角色权限';

/*Data for the table `ca_role_permission` */

insert  into `ca_role_permission`(`id`,`role_id`,`permission_id`,`create_date`,`update_date`,`issuer`) values 
(123,13,1,'2017-06-06 20:41:39',NULL,'ceshi'),
(124,13,2,'2017-06-06 20:41:39',NULL,'ceshi'),
(125,13,3,'2017-06-06 20:41:39',NULL,'ceshi'),
(126,13,4,'2017-06-06 20:41:39',NULL,'ceshi'),
(127,13,5,'2017-06-06 20:41:39',NULL,'ceshi'),
(128,13,6,'2017-06-06 20:41:39',NULL,'ceshi'),
(391,50,2,'2017-06-27 10:58:08',NULL,'张三'),
(392,50,3,'2017-06-27 10:58:08',NULL,'张三'),
(393,50,105,'2017-06-27 10:58:08',NULL,'张三'),
(394,50,106,'2017-06-27 10:58:08',NULL,'张三'),
(395,50,109,'2017-06-27 10:58:08',NULL,'张三'),
(396,50,111,'2017-06-27 10:58:08',NULL,'张三'),
(1862,1,0,'2017-07-10 10:30:29',NULL,'张三'),
(1863,1,1,'2017-07-10 10:30:29',NULL,'张三'),
(1864,1,2,'2017-07-10 10:30:29',NULL,'张三'),
(1865,1,3,'2017-07-10 10:30:29',NULL,'张三'),
(1866,1,139,'2017-07-10 10:30:29',NULL,'张三'),
(1867,1,4,'2017-07-10 10:30:29',NULL,'张三'),
(1868,1,5,'2017-07-10 10:30:29',NULL,'张三'),
(1869,1,6,'2017-07-10 10:30:29',NULL,'张三'),
(1870,1,7,'2017-07-10 10:30:29',NULL,'张三'),
(1871,1,8,'2017-07-10 10:30:29',NULL,'张三'),
(1872,1,9,'2017-07-10 10:30:29',NULL,'张三'),
(1873,1,151,'2017-07-10 10:30:29',NULL,'张三'),
(1874,1,10,'2017-07-10 10:30:29',NULL,'张三'),
(1875,1,11,'2017-07-10 10:30:29',NULL,'张三'),
(1876,1,12,'2017-07-10 10:30:29',NULL,'张三'),
(1877,1,13,'2017-07-10 10:30:29',NULL,'张三'),
(1878,1,101,'2017-07-10 10:30:29',NULL,'张三'),
(1879,1,102,'2017-07-10 10:30:29',NULL,'张三'),
(1880,1,103,'2017-07-10 10:30:29',NULL,'张三'),
(1881,1,104,'2017-07-10 10:30:29',NULL,'张三'),
(1882,1,105,'2017-07-10 10:30:29',NULL,'张三'),
(1883,1,106,'2017-07-10 10:30:29',NULL,'张三'),
(1884,1,107,'2017-07-10 10:30:29',NULL,'张三'),
(1885,1,108,'2017-07-10 10:30:29',NULL,'张三'),
(1886,1,109,'2017-07-10 10:30:29',NULL,'张三'),
(1887,1,110,'2017-07-10 10:30:29',NULL,'张三'),
(1888,1,111,'2017-07-10 10:30:29',NULL,'张三'),
(1889,1,112,'2017-07-10 10:30:29',NULL,'张三'),
(1890,1,140,'2017-07-10 10:30:29',NULL,'张三'),
(1891,1,141,'2017-07-10 10:30:29',NULL,'张三'),
(1892,1,142,'2017-07-10 10:30:29',NULL,'张三'),
(1893,1,143,'2017-07-10 10:30:29',NULL,'张三'),
(1895,1,210,NULL,NULL,NULL),
(1896,1,211,NULL,NULL,NULL),
(1897,1,212,NULL,NULL,NULL),
(1898,1,220,NULL,NULL,NULL),
(1899,1,221,NULL,NULL,NULL),
(1900,1,222,NULL,NULL,NULL),
(1901,1,230,NULL,NULL,NULL),
(1902,1,231,NULL,NULL,NULL),
(1903,1,232,NULL,NULL,NULL),
(1904,1,233,NULL,NULL,NULL),
(1905,1,234,NULL,NULL,NULL),
(1906,1,235,NULL,NULL,NULL),
(1907,1,240,NULL,NULL,NULL),
(1908,1,241,NULL,NULL,NULL),
(1909,1,242,NULL,NULL,NULL),
(1910,1,243,NULL,NULL,NULL),
(1911,1,244,NULL,NULL,NULL),
(1912,1,250,NULL,NULL,NULL),
(1913,1,251,NULL,NULL,NULL),
(1914,1,252,NULL,NULL,NULL),
(1915,1,253,NULL,NULL,NULL),
(1916,1,254,NULL,NULL,NULL),
(1917,1,260,NULL,NULL,NULL),
(1918,1,261,NULL,NULL,NULL),
(1919,1,262,NULL,NULL,NULL),
(1920,1,270,NULL,NULL,NULL),
(1921,1,271,NULL,NULL,NULL),
(1922,1,272,NULL,NULL,NULL),
(1923,1,280,NULL,NULL,NULL),
(1924,1,281,NULL,NULL,NULL),
(1925,1,282,NULL,NULL,NULL),
(1926,1,283,NULL,NULL,NULL),
(1927,1,284,NULL,NULL,NULL),
(1928,1,285,NULL,NULL,NULL),
(1929,1,290,NULL,NULL,NULL),
(1930,1,300,NULL,NULL,NULL),
(1931,1,310,NULL,NULL,NULL),
(1932,1,320,NULL,NULL,NULL),
(1933,1,200,NULL,NULL,NULL),
(1951,62,0,'2018-01-02 16:27:56',NULL,'李四'),
(1952,62,3,'2018-01-02 16:27:56',NULL,'李四'),
(1953,62,112,'2018-01-02 16:27:56',NULL,'李四'),
(1954,2,0,'2018-01-02 18:08:35',NULL,'张三'),
(1955,2,3,'2018-01-02 18:08:35',NULL,'张三'),
(1956,66,0,'2018-02-09 11:41:30',NULL,'张三'),
(1957,66,2,'2018-02-09 11:41:30',NULL,'张三'),
(1958,66,3,'2018-02-09 11:41:30',NULL,'张三'),
(1959,66,139,'2018-02-09 11:41:30',NULL,'张三'),
(1960,66,108,'2018-02-09 11:41:30',NULL,'张三'),
(1961,66,109,'2018-02-09 11:41:30',NULL,'张三'),
(1962,66,110,'2018-02-09 11:41:30',NULL,'张三');

/*Table structure for table `ca_upload_file` */

DROP TABLE IF EXISTS `ca_upload_file`;

CREATE TABLE `ca_upload_file` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `file_key` varchar(100) DEFAULT NULL COMMENT '代表文件的KEY值',
  `file_path` varchar(200) DEFAULT NULL COMMENT '文件存储的本地路径',
  `content_type` varchar(50) DEFAULT NULL COMMENT '文件的MimeType',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件的大小',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ca_upload_file` */

insert  into `ca_upload_file`(`id`,`file_key`,`file_path`,`content_type`,`file_size`) values 
('04DD1136BC824589AD9531527F0BE269','3ef2f4fa4e281cad636477442bd8ca83_4668538880','D:\\upload\\prod\\3ef2f4fa4e281cad636477442bd8ca83_4668538880','application/octet-stream',4668538880),
('219C08A2C5054DC6A7035B6E2F5CC635','b2b1791464051a3560ab043b19f43ad1_2292287488','D:\\upload\\prod\\b2b1791464051a3560ab043b19f43ad1_2292287488','application/octet-stream',2292287488),
('2F8C777EA70341F4B5A7BDB78A5DAF27','89a80c21def6e46a31d90468f6fbe5b7_200079360','D:\\upload\\prod\\89a80c21def6e46a31d90468f6fbe5b7_200079360','application/octet-stream',200079360),
('37FE6752CBC44511982AF419B784EB3B','13d9ed878d7b13b4398e4756cd4dee30_1676573224','D:\\upload\\prod\\13d9ed878d7b13b4398e4756cd4dee30_1676573224','application/x-msdownload',1676573224),
('51BB673CCFE441CCBDC5057026CC0AED','8dfb050dcc3e172799ad8afa06ffb82d_161653','D:\\upload\\prod\\8dfb050dcc3e172799ad8afa06ffb82d_161653','application/octet-stream',161653),
('7031BD6589C84F4CA387BDDB70962E22','41697b46c9882c8f8609946217361f43_998579021','D:\\upload\\prod\\41697b46c9882c8f8609946217361f43_998579021','application/octet-stream',998579021),
('83BCB85EF2C84917A098EA48839BB618','fa96a7dd7d284ed36d672afdfbcf23d1_197994816','D:\\upload\\prod\\fa96a7dd7d284ed36d672afdfbcf23d1_197994816','application/octet-stream',197994816),
('AF0C745180FF4B6898634D775A886692','7813adb13cd108a3c834749b6cfe6fbf_126466777','D:\\upload\\prod\\7813adb13cd108a3c834749b6cfe6fbf_126466777','application/pdf',126466777),
('CE8EF7A890654BC79A9C9C9708A16D5C','ddcbd7389ce4c025747e2d933558dd41_1856900894','D:\\upload\\prod\\ddcbd7389ce4c025747e2d933558dd41_1856900894','application/octet-stream',1856900894),
('E4A7A5E9785F4195A761E678EA1778C5','8b63cd0a046be012c8afd7c3cb163dff_246','D:\\upload\\prod\\8b63cd0a046be012c8afd7c3cb163dff_246','text/plain',246),
('E5083B9AF9544FE09EB1FA089F64EB07','3acd5ac7c2fffd39a5cbdbb826f79353_2981289','D:\\upload\\prod\\3acd5ac7c2fffd39a5cbdbb826f79353_2981289','application/octet-stream',2981289),
('ECA9B38A582D4013BD6BEECD6A0B9A39','39949262892cc180285d8f543c6f6217_1747164520','D:\\upload\\prod\\39949262892cc180285d8f543c6f6217_1747164520','application/octet-stream',1747164520),
('F2220E8590A34257888E7B3866EC58EE','2bb8e5bd29c859231f0051713f8af052_264048640','D:\\upload\\prod\\2bb8e5bd29c859231f0051713f8af052_264048640','application/octet-stream',264048640),
('F3EBFD42F62E430E9A561FF4CB416F38','65ced0828a3cc65ba887757fb08ed021_38991365','D:\\upload\\prod\\65ced0828a3cc65ba887757fb08ed021_38991365','application/octet-stream',38991365);

/*Table structure for table `ca_user` */

DROP TABLE IF EXISTS `ca_user`;

CREATE TABLE `ca_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识码',
  `org_id` bigint(20) NOT NULL COMMENT '组织机构ID',
  `login_name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '登陆名',
  `password` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '登陆密码',
  `username` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `job_num` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `login_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '登陆IP',
  `is_lock` int(11) NOT NULL DEFAULT '0' COMMENT '锁定值 1：锁定 0：解锁',
  `lockdate` datetime DEFAULT NULL COMMENT '锁定时间',
  `trycount` int(11) DEFAULT '0' COMMENT '登陆错误次数',
  `phone` char(12) COLLATE utf8_bin DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '账号状态0正常，1表示删除',
  `issuer` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '创建人',
  `direct_manager_id` bigint(20) DEFAULT NULL COMMENT '直属主管的ID',
  `direct_manager_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '直属主管的Name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='CA_用户信息表';

/*Data for the table `ca_user` */

insert  into `ca_user`(`id`,`org_id`,`login_name`,`password`,`username`,`job_num`,`last_login_date`,`create_date`,`update_date`,`login_ip`,`is_lock`,`lockdate`,`trycount`,`phone`,`email`,`is_delete`,`issuer`,`direct_manager_id`,`direct_manager_name`) values 
(1,2,'zhangsan','96e79218965eb72c92a549dd5a330112','张三','1','2018-03-06 11:04:40','2017-05-02 10:23:20','2017-06-28 10:01:09','192.168.1.198',0,'2017-05-25 14:56:23',0,'13540861109','yh@163.com',0,'机构管理员',2,'李四'),
(2,2,'lisi','96e79218965eb72c92a549dd5a330112','李四','2','2018-01-05 18:14:17','2017-05-31 11:05:34','2017-07-06 17:20:29',NULL,0,NULL,0,NULL,NULL,0,'机构管理员',NULL,NULL),
(3,2,'wangwu','96e79218965eb72c92a549dd5a330112','王五','4',NULL,'2017-05-31 11:06:09','2017-06-28 15:44:34',NULL,0,NULL,0,NULL,NULL,1,'王五',NULL,NULL),
(4,2,'zhaoliu','96e79218965eb72c92a549dd5a330112','赵六','5',NULL,'2017-05-31 11:06:57','2017-06-28 15:52:07',NULL,0,NULL,0,NULL,NULL,0,'机构管理员',NULL,NULL),
(5,69,'qianqi','96e79218965eb72c92a549dd5a330112','钱七','6',NULL,'2017-05-31 11:07:41','2017-06-26 15:44:42',NULL,0,NULL,0,NULL,NULL,1,'钱七',NULL,NULL),
(6,69,'sunba','96e79218965eb72c92a549dd5a330112','孙八','7','2017-07-12 11:38:35','2017-05-31 11:08:22','2017-06-03 15:44:40',NULL,0,NULL,0,NULL,NULL,1,'孙八',NULL,NULL),
(7,7,'wanger','96e79218965eb72c92a549dd5a330112','王二','8',NULL,'2017-05-31 11:09:12','2017-06-02 15:44:38',NULL,0,NULL,0,NULL,NULL,0,'王二',NULL,NULL),
(8,8,'murong','96e79218965eb72c92a549dd5a330112','慕容复','9',NULL,'2017-05-31 11:09:50','2017-06-14 16:36:09',NULL,0,NULL,0,NULL,NULL,0,'机构管理员',NULL,NULL),
(9,4,'guojing','96e79218965eb72c92a549dd5a330112','郭靖','10',NULL,'2017-05-31 11:10:16','2017-06-27 17:52:21',NULL,1,'2017-07-12 11:21:19',0,NULL,NULL,0,'机构管理员',NULL,NULL),
(10,10,'zhangsanfeng','96e79218965eb72c92a549dd5a330112','张三丰','11',NULL,'2017-05-31 11:10:53','2017-06-07 15:44:45',NULL,0,NULL,0,NULL,NULL,0,'张三丰',NULL,NULL),
(11,2,'miaorenfeng','96e79218965eb72c92a549dd5a330112','苗人凤','12',NULL,'2017-05-31 11:12:11','2017-06-27 17:53:55',NULL,1,'2017-07-11 11:54:31',0,NULL,NULL,0,'机构管理员',NULL,NULL),
(87,1,'qiaofeng','96e79218965eb72c92a549dd5a330112','乔峰45','3','2018-01-02 18:10:56','2017-05-31 10:10:52','2017-06-27 19:37:42',NULL,0,NULL,0,'15871421224',NULL,0,'机构管理员',NULL,NULL),
(88,70,'zhangwuji','e10adc3949ba59abbe56e057f20f883e','张无忌','3',NULL,'2017-05-31 15:17:45','2017-05-29 15:44:48','192.168.1.23',0,NULL,0,'15871421224',NULL,0,'张无忌',NULL,NULL),
(89,5,'A01','9e9c2aea25302f2a9116c63519f6e46b','吉塔','吉塔',NULL,'2017-05-31 17:26:15','2017-06-02 17:45:15',NULL,0,NULL,0,'15872549525','yh@163.com',1,'机构管理员',NULL,NULL),
(90,70,'wenjiabao','e10adc3949ba59abbe56e057f20f883e','温家宝','110',NULL,'2017-05-31 17:52:05','2017-06-28 15:49:45',NULL,0,NULL,0,'18524716852','test@163.com',0,'机构管理员',NULL,NULL),
(94,4,'ceshi','96e79218965eb72c92a549dd5a330112','李四',NULL,NULL,'2017-06-01 15:52:39','2017-07-06 17:47:00',NULL,0,NULL,0,'13577777777','y.9h@163.com',1,'机构管理员',NULL,NULL),
(95,3,'zhangquandan','e10adc3949ba59abbe56e057f20f883e','张全蛋','0011',NULL,'2017-06-01 16:16:06','2017-06-17 15:45:00',NULL,0,NULL,0,'18524754857','test@163.com',1,'张三',NULL,NULL),
(101,69,'xijinp','e10adc3949ba59abbe56e057f20f883e','习近平','001',NULL,'2017-06-01 17:09:12','2017-06-29 08:03:36',NULL,0,NULL,0,'18524748547','t@163.com',0,'机构管理员',NULL,NULL),
(102,1,'wangwu','e10adc3949ba59abbe56e057f20f883e','王五','3',NULL,'2017-06-05 13:51:49','2017-06-16 15:44:56',NULL,0,NULL,0,'15871421224','hello@126.com',1,'王五',NULL,NULL),
(103,1,'wangwu','e10adc3949ba59abbe56e057f20f883e','王五','3',NULL,'2017-06-05 13:55:18','2017-06-12 15:44:53',NULL,0,NULL,0,'15871421224','hello@126.com',1,'王五',NULL,NULL),
(104,1234,'xiexun','e10adc3949ba59abbe56e057f20f883e','谢逊','3',NULL,'2017-06-05 16:20:31','2017-06-05 16:28:08','192.168.101.23',0,NULL,0,'15871421224','xiexun@126.com',1,'xiexun',NULL,NULL),
(105,1,'lala','e10adc3949ba59abbe56e057f20f883e','啦啦','0014',NULL,'2017-06-13 10:48:18','2017-06-14 15:45:13',NULL,0,NULL,0,'18507108471','rest@163.com',1,'张三',NULL,NULL),
(106,1,'xixixi','e10adc3949ba59abbe56e057f20f883e','嘻嘻嘻','0010',NULL,'2017-06-13 11:03:35','2017-06-22 15:45:06',NULL,0,NULL,0,'18514754784','test@163.com',0,'张三',NULL,NULL),
(107,1,'lalal','e10adc3949ba59abbe56e057f20f883e','啦啦啦','00141',NULL,'2017-06-13 11:16:50','2017-06-12 15:45:16',NULL,0,NULL,0,'18501741874','tes@163.com',1,'张三',NULL,NULL),
(108,1,'lala','e10adc3949ba59abbe56e057f20f883e','啦啦啦','9527',NULL,'2017-06-13 11:25:43','2017-06-08 20:50:57',NULL,1,'2017-07-11 11:54:31',0,'18507108471','test@163.com',0,'机构管理员',NULL,NULL),
(109,0,'gdg','b52c96bea30646abf8170f333bbd42b9','吕碧城','999',NULL,'2017-06-28 09:14:52','2017-06-07 15:45:11',NULL,0,NULL,0,'15858639088','sleind345y@163.com',1,'张三',NULL,NULL),
(110,2,'gdg','b52c96bea30646abf8170f333bbd42b9','吕碧城','666',NULL,'2017-06-28 10:08:28','2017-06-28 20:39:26',NULL,0,NULL,0,'15872549525','kollor78@163.com',0,'机构管理员',NULL,NULL),
(111,3,'tanxiong','e10adc3949ba59abbe56e057f20f883e','物理','110',NULL,'2017-06-30 18:01:00',NULL,NULL,0,NULL,0,'18507108471','test@163.com',0,'张三',NULL,NULL),
(112,0,'dgdg','549b89844a00213a9ec5f7203694a0cf','地方好烦1213','kk',NULL,'2017-07-03 13:54:37','2017-12-28 16:41:44',NULL,1,'2017-07-11 11:54:31',0,'13135785421','223aa@163.com',0,'机构管理员',88,'张无忌'),
(113,0,'gdg','b960a17584bb8590189dc527ce1f87bd','的辐射','dd',NULL,'2017-07-03 13:55:15',NULL,NULL,0,NULL,0,'13115478595','adsf@163.com',1,'张三',NULL,NULL),
(114,0,'gdg','a7fd119c654703b19d8f50f1ef49d2d0','按付款','dds',NULL,'2017-07-03 13:58:54',NULL,NULL,0,NULL,0,'13154857025','hjd@163.com',1,'张三',NULL,NULL),
(115,0,'ddbb','96e79218965eb72c92a549dd5a330112','五五开','001',NULL,'2017-07-03 15:29:27',NULL,NULL,0,NULL,0,'15872549525','test@163.com',1,'张三',NULL,NULL),
(116,0,'choukai','96e79218965eb72c92a549dd5a330112','五五开','001',NULL,'2017-07-03 16:43:41','2017-07-04 13:49:51',NULL,0,NULL,0,'15872549525','test@163.com',1,'机构管理员',NULL,NULL),
(117,3,'zzz','453e41d218e071ccfb2d1c99ce23906a','卢本伟','001',NULL,'2017-07-05 13:25:36',NULL,NULL,0,NULL,0,'15872549525','test@163.com',1,'张三',NULL,NULL),
(118,2,'hhh','e10adc3949ba59abbe56e057f20f883e','哈哈','0000001',NULL,'2017-07-05 16:21:10',NULL,NULL,1,'2017-07-11 11:54:31',0,'18507108471','test@163.com',0,'张三',110,'吕碧城'),
(119,0,'jinyong','e10adc3949ba59abbe56e057f20f883e','金庸','1241',NULL,'2017-07-05 16:24:15',NULL,NULL,0,NULL,0,'18507105471','test@163.com',1,'张三',116,'五五开'),
(120,0,'66kai','e10adc3949ba59abbe56e057f20f883e','开发','666',NULL,'2017-07-05 17:11:14',NULL,NULL,0,NULL,0,'18520107871','test@163.com',1,'张三',NULL,NULL),
(121,0,'wuwukai','96e79218965eb72c92a549dd5a330112','丑开','001',NULL,'2017-07-05 17:29:36',NULL,NULL,0,NULL,0,'15872549525','test@163.com',1,'张三',NULL,NULL),
(122,0,'wuwuka','e10adc3949ba59abbe56e057f20f883e','方大虎','120',NULL,'2017-07-05 17:35:16',NULL,NULL,0,NULL,0,'18520108471','test@163.com',1,'张三',116,'五五开'),
(123,4,'aasir','e10adc3949ba59abbe56e057f20f883e','郭芙','0728','2017-07-07 13:27:42','2017-07-07 09:59:50',NULL,NULL,0,NULL,0,'18507108471','t@16.com',0,'李四',9,'郭靖'),
(124,1,'zhangsansan','74076e3ee9be5c57e1df75b775432478','张三三','001',NULL,'2017-07-12 10:33:59',NULL,NULL,0,NULL,0,'15872549525','test@163.com',1,'张三',118,'哈哈'),
(125,1,'zhangzhang','e10adc3949ba59abbe56e057f20f883e','张张','1111',NULL,'2018-01-29 11:24:49',NULL,NULL,0,NULL,0,'15879635521','2@3.com',1,'张三',118,'哈哈'),
(126,1,'zhanghaha','16ec37c499f64fc60e95650b500e30a4','张三三','15212',NULL,'2018-02-24 14:07:25',NULL,NULL,0,NULL,0,'15277412360','22033025213@33.ca',0,'张三',7,'王二');

/*Table structure for table `ca_user_log` */

DROP TABLE IF EXISTS `ca_user_log`;

CREATE TABLE `ca_user_log` (
  `id` varchar(50) NOT NULL,
  `login_name` varchar(60) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ca_user_log` */

insert  into `ca_user_log`(`id`,`login_name`,`login_date`,`ip`,`create_date`) values 
('0AD4F18354E449C4A9EF21D0A340AE17','zhangsan','2017-07-07 17:19:29','192.168.1.100','2017-07-07 17:19:29'),
('14BAAF266CB648CBB113331375BD849B','zhangsan','2017-07-07 16:35:50','192.168.1.100','2017-07-07 16:35:50'),
('15D6279C95534BECB0D711D3D82702AE','zhangsan','2017-07-08 19:15:06','192.168.1.100','2017-07-08 19:15:06'),
('25BFE63DA11E49EBB69A2ADE57830199','zhangsan','2017-07-07 16:27:28','192.168.1.100','2017-07-07 16:27:28'),
('25D2AF7248EA4393B4415DBB449A2847','zhangsan','2017-07-07 14:16:15','192.168.1.100','2017-07-07 14:16:15'),
('397BBA0130AF4C4EB8B7184118A2A8D1','zhangsan','2017-07-07 17:35:37','192.168.1.100','2017-07-07 17:35:37'),
('3BA22D42254148C2836557099389DD63','zhangsan','2017-07-08 18:44:35','192.168.1.100','2017-07-08 18:44:35'),
('46562B1F455B4E198D4DF99CA14019CD','zhangsan','2017-07-07 17:19:15','192.168.1.100','2017-07-07 17:19:15'),
('4EE32B61AA934EA69BD118099E9AC63D','zhangsan','2017-07-07 17:34:18','192.168.1.100','2017-07-07 17:34:18'),
('51DA8BF430C1460B872D50B6035804AC','zhangsan','2017-07-07 17:05:50','192.168.1.100','2017-07-07 17:05:50'),
('52DFE446F6A045108C4FE648A9848DB6','zhangsan','2017-07-07 16:13:38','192.168.1.100','2017-07-07 16:13:38'),
('56A0720062624F7DB5DE319D608D326E','zhangsan','2017-07-07 16:29:46','192.168.1.100','2017-07-07 16:29:46'),
('58D4170857034EB2AB2F225046F1F0A2','zhangsan','2017-07-08 19:16:13','192.168.1.100','2017-07-08 19:16:13'),
('6217D6420BEE47F9BFDC5437D36E1128','zhangsan','2017-07-07 16:53:50','192.168.1.100','2017-07-07 16:53:50'),
('6935AC07FDA948CFA0EBCCF937E91B6B','zhangsan','2017-07-07 16:26:54','192.168.1.100','2017-07-07 16:26:54'),
('69763981786E4C8DAA7FCEFA8FF93D81','zhangsan','2017-07-07 14:42:14','192.168.1.100','2017-07-07 14:42:14'),
('7B48BCE0943F4751BF4D0F515ED92A65','zhangsan','2017-07-07 17:29:54','192.168.1.100','2017-07-07 17:29:54'),
('7CD1738359044AC280AA1963FD62CC35','zhangsan','2017-07-07 16:37:49','192.168.1.100','2017-07-07 16:37:49'),
('8A4EC5FDCDAB4D37A7AA1621167226AE','zhangsan','2017-07-07 17:30:55','192.168.1.100','2017-07-07 17:30:55'),
('8B65F6726F4A42BA97E5CE6C8C92FCE3','zhangsan','2017-07-07 16:39:16','192.168.1.100','2017-07-07 16:39:16'),
('97389CBCAE4840629428F960503FDE23','zhangsan','2017-07-07 15:54:52','192.168.1.100','2017-07-07 15:54:52'),
('9F4E950A833A4090A0F10DF671217BD7','zhangsan','2017-07-07 17:36:09','192.168.1.100','2017-07-07 17:36:09'),
('A4D7CB6575104838A52C4ADC72ACC2D0','zhangsan','2017-07-07 16:21:55','192.168.1.100','2017-07-07 16:21:55'),
('A5388D774B83401981BF6BCDF51A1A49','zhangsan','2017-07-08 21:13:37','192.168.1.100','2017-07-08 21:13:37'),
('B1C362552E7B4102AD98BABD4022373F','zhangsan','2017-07-07 16:23:32','192.168.1.100','2017-07-07 16:23:32'),
('B304685E48204349B3163EE0EB2AD26C','zhangsan','2017-07-07 14:50:06','192.168.1.100','2017-07-07 14:50:06'),
('BE3FA19FF890424CB2CD4ADABDAE4889','zhangsan','2017-07-07 16:41:13','192.168.1.100','2017-07-07 16:41:13'),
('C107E5B7E48F4A99B0F683BE06554D96','zhangsan','2017-07-07 16:16:36','192.168.1.100','2017-07-07 16:16:36'),
('C9E2A4E85E3C47E1BBBB6C29EF5C573D','zhangsan','2017-07-08 20:21:18','192.168.1.100','2017-07-08 20:21:18'),
('CAD6733BC5964B62A722A9B49DA03BBB','zhangsan','2017-07-07 14:42:34','192.168.1.100','2017-07-07 14:42:34'),
('D34F612EFB434EBD8A0BB9AC3C61609D','zhangsan','2017-07-07 17:55:11','192.168.1.100','2017-07-07 17:55:11'),
('D575E09EC16341ECB9E166F80F802850','zhangsan','2017-07-07 15:23:17','192.168.1.100','2017-07-07 15:23:17'),
('D58C7D21382A4E70A958AC5D9246B3E0','zhangsan','2017-07-07 11:02:05','192.168.1.100','2017-07-07 11:02:05'),
('DDE5FEA025684119A12414B88580BFB9','zhangsan','2017-07-07 16:25:49','192.168.1.100','2017-07-07 16:25:49'),
('E1BEDA4F6C1D4F409A8187D41D74E8C8','zhangsan','2017-07-07 17:58:55','192.168.1.100','2017-07-07 17:58:55'),
('FE1E092A8D764FB3B27021A939F1857E','zhangsan','2017-07-07 10:47:16','192.168.1.100','2017-07-07 10:47:16'),
('FE55FF9B51CD4C54A5FE21CCA5D0E991','zhangsan','2017-07-08 18:52:21','192.168.1.100','2017-07-08 18:52:21');

/*Table structure for table `ca_user_role` */

DROP TABLE IF EXISTS `ca_user_role`;

CREATE TABLE `ca_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识码',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `issuer` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=449 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='CA_用户角色关联关系表';

/*Data for the table `ca_user_role` */

insert  into `ca_user_role`(`id`,`user_id`,`role_id`,`create_date`,`update_date`,`issuer`) values 
(8,4,38,'2017-05-31 11:14:05',NULL,'郭靖'),
(9,5,39,'2017-05-31 11:14:20',NULL,'苗人凤'),
(10,6,40,'2017-05-31 11:14:37',NULL,'张三丰'),
(11,7,41,'2017-05-31 11:14:54',NULL,'逍遥子'),
(42,2,62,'2017-06-28 19:42:22',NULL,'张三'),
(43,2,61,'2017-06-28 19:42:22',NULL,'张三'),
(44,2,58,'2017-06-28 19:42:22',NULL,'张三'),
(45,2,50,'2017-06-28 19:42:22',NULL,'张三'),
(50,107,62,'2017-06-29 08:07:40',NULL,'张三'),
(51,107,61,'2017-06-29 08:07:40',NULL,'张三'),
(52,107,58,'2017-06-29 08:07:40',NULL,'张三'),
(53,107,50,'2017-06-29 08:07:40',NULL,'张三'),
(55,107,45,'2017-06-29 08:07:40',NULL,'张三'),
(56,107,35,'2017-06-29 08:07:40',NULL,'张三'),
(221,2,37,'2017-06-29 08:45:04',NULL,'张三'),
(222,4,37,'2017-06-29 08:45:04',NULL,'张三'),
(223,7,37,'2017-06-29 08:45:04',NULL,'张三'),
(226,10,37,'2017-06-29 08:45:04',NULL,'张三'),
(227,11,37,'2017-06-29 08:45:04',NULL,'张三'),
(229,88,37,'2017-06-29 08:45:04',NULL,'张三'),
(230,90,37,'2017-06-29 08:45:04',NULL,'张三'),
(232,95,37,'2017-06-29 08:45:04',NULL,'张三'),
(233,101,37,'2017-06-29 08:45:04',NULL,'张三'),
(234,106,37,'2017-06-29 08:45:04',NULL,'张三'),
(235,107,37,'2017-06-29 08:45:04',NULL,'张三'),
(236,108,37,'2017-06-29 08:45:04',NULL,'张三'),
(238,110,37,'2017-06-29 08:45:04',NULL,'张三'),
(291,9,39,'2017-06-29 19:02:35',NULL,'张三'),
(292,9,40,'2017-06-29 19:02:35',NULL,'张三'),
(296,109,62,'2017-06-29 19:21:16',NULL,'张三'),
(297,109,61,'2017-06-29 19:21:16',NULL,'张三'),
(320,1,63,'2017-06-29 20:20:02',NULL,'张三'),
(324,11,63,'2017-06-29 20:20:02',NULL,'张三'),
(340,110,47,'2017-07-06 15:54:43',NULL,'张三'),
(341,110,47,'2017-07-06 15:54:43',NULL,'张三'),
(342,118,47,'2017-07-06 15:54:43',NULL,'张三'),
(343,118,47,'2017-07-06 15:54:43',NULL,'张三'),
(351,2,64,'2017-07-07 11:09:06',NULL,'张三'),
(353,112,64,'2017-07-07 11:09:06',NULL,'张三'),
(420,1,1,'2017-07-10 16:15:41',NULL,'张三'),
(421,2,1,'2017-07-10 16:15:41',NULL,'张三'),
(423,90,1,'2017-07-10 16:15:41',NULL,'张三'),
(424,95,1,'2017-07-10 16:15:41',NULL,'张三'),
(425,106,1,'2017-07-10 16:15:41',NULL,'张三'),
(428,111,1,'2017-07-10 16:15:41',NULL,'张三'),
(429,116,1,'2017-07-10 16:15:41',NULL,'张三'),
(434,94,39,'2017-08-01 10:40:01',NULL,'张三'),
(435,94,40,'2017-08-01 10:40:02',NULL,'张三'),
(436,94,45,'2017-08-01 10:40:02',NULL,'张三'),
(437,94,62,'2017-08-01 10:40:02',NULL,'张三'),
(438,94,64,'2017-08-01 10:40:02',NULL,'张三'),
(439,7,66,'2017-12-28 16:54:27',NULL,'张三'),
(440,8,1,'2018-01-02 15:37:34',NULL,'李四'),
(441,8,47,'2018-01-02 15:37:34',NULL,'李四'),
(444,110,2,'2018-01-02 16:26:29',NULL,'李四'),
(446,87,2,'2018-01-02 18:09:00',NULL,'张三'),
(448,126,45,'2018-02-24 14:08:01',NULL,'张三');

/* Function  structure for function  `getChildList` */

/*!50003 DROP FUNCTION IF EXISTS `getChildList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`huiqing`@`%` FUNCTION `getChildList`(rootId VARCHAR(50)) RETURNS longtext CHARSET utf8 COLLATE utf8_bin
BEGIN
	DECLARE pTemp LONGTEXT;  
        DECLARE cTemp LONGTEXT;
        
        SET pTemp = '$';  
        SET cTemp =cast(rootId as CHAR);
        
        
        WHILE cTemp is not null DO  
         SET pTemp = concat(pTemp,',',cTemp);  
         SELECT group_concat(id) INTO cTemp FROM ca_organization   
         WHERE FIND_IN_SET(parent_id,cTemp)>0; 
                
       END WHILE;  
       RETURN pTemp;
    END */$$
DELIMITER ;

/* Function  structure for function  `getDictionaryChildList` */

/*!50003 DROP FUNCTION IF EXISTS `getDictionaryChildList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`huiqing`@`%` FUNCTION `getDictionaryChildList`(rootId VARCHAR(50)) RETURNS longtext CHARSET utf8 COLLATE utf8_bin
BEGIN
	DECLARE pTemp LONGTEXT;  
        DECLARE cTemp LONGTEXT;
        
        SET pTemp = '$';  
        SET cTemp =cast(rootId as CHAR);
        
        
        WHILE cTemp is not null DO  
         SET pTemp = concat(pTemp,',',cTemp);  
         SELECT group_concat(id) INTO cTemp FROM ca_dictionary
         WHERE FIND_IN_SET(parent_id,cTemp)>0; 
                
       END WHILE;  
       RETURN pTemp;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
