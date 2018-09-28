# photograph
这是一个简单的springboot+mybatis+redis的使用，controller返回json数据，格式为Entity实体类相关属性，具体参照response包下的实体类。

数据库脚本：
/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.5.20-log : Database - photograph
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`photograph` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `photograph`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keywords` varchar(50) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`id`,`userid`,`title`,`description`,`keywords`,`datetime`,`parentid`) values 
(1,1,'test','aaa','aa','2018-08-28 00:00:00',-1),
(2,1,'test','aaa','aa','2018-08-28 00:00:00',-1),
(3,1,'test','aaa','aa','2018-08-28 00:00:00',-1),
(4,1,'test','aaa','aa','2018-08-28 00:00:00',-1);

/*Table structure for table `photograph` */

DROP TABLE IF EXISTS `photograph`;

CREATE TABLE `photograph` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryid` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keywords` varchar(100) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `icon` varchar(200) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `photograph` */

insert  into `photograph`(`id`,`categoryid`,`title`,`description`,`keywords`,`image`,`icon`,`datetime`) values 
(4,NULL,'22打打打','232323','c语言','\\file\\image\\5\\6\\3B45B9EA4FF044B899A11E2C70745F3F_a.jpg,\\file\\image\\5\\7\\E591F969F32848DDA2ABB1D9B60C4CCD_b.jpg,',NULL,'2018-09-20 15:33:13'),
(5,NULL,'2323','323213123a','c语言222','\\file\\image\\3\\f\\6B64E90C779349BD878F9CD3188B8FB1_001.jpg,\\file\\image\\5\\6\\8DE036D0A32F49439AB4C83E1FAC88A3_a.jpg,',NULL,'2018-09-20 17:37:07'),
(6,NULL,'321312qqq','232323aaa','wqeqe2','\\file\\image\\5\\8\\8F83672DA384434BBE1950C3A0D6FA34_c.jpg,\\file\\image\\5\\7\\0178729F2C144AB9AC21F3096CB37C32_b.jpg,',NULL,'2018-09-21 08:40:24'),
(7,NULL,'pppppp','lllll','mmmmmm','\\file\\image\\5\\6\\F6C02ACFA6274CFD85B39EE00507A3C8_a.jpg,\\file\\image\\5\\7\\2CE92AAE59BE4137A36A9236A84996B4_b.jpg,',NULL,'2018-09-21 10:35:33'),
(8,NULL,'pppppp666','666lllll','666mmmmmm','\\file\\image\\3\\f\\6974A8C401744C96B312903759519A18_001.jpg,\\file\\image\\d\\6\\D2554F61B1234A16B3BE515639A949EE_1b550f15de416d3876070652f32d0ce2.jpg,',NULL,'2018-09-21 10:37:49'),
(9,NULL,'测试大大大大','的额外','未全额全额','\\file\\image\\1\\e\\DD6E8E751AA247E6A6CE28B5A27D73E9_0ac3a6d84a2fe5b1f2c932c37c0aa5a5.jpg,\\file\\image\\3\\f\\17F9A5ECCFB1469DAEB3D90DB3E09613_001.jpg,',NULL,'2018-09-21 11:03:00'),
(10,NULL,'小11','笑傲','小爱','\\file\\image\\3\\f\\D64DA6D8FED84513A4F3FE908E219F3C_001.jpg,\\file\\image\\5\\7\\467D1873B8814D38A40A6B955679F1FC_b.jpg,',NULL,'2018-09-21 11:11:41');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `portrait` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`nickname`,`password`,`mobile`,`portrait`) values 
(1,'昵称','123','13588018398','/head/1.jpg'),
(2,'昵称1','123','13588018398','/head/1.jpg'),
(19,'aaa','bbb',NULL,NULL),
(20,'zoujie','22','17761300564',NULL),
(22,'zoujie','22','17761300564',NULL),
(23,'111','222','17761300564',NULL),
(24,'aaa1','1','17761300564',NULL),
(25,'6661','11','17761300564',NULL),
(26,'13213','231','12312',NULL),
(27,'1313','313','213',NULL),
(28,'222','11','11',NULL),
(29,'21','323','32','241405b98e9b57cdbe.jpg'),
(30,'222222','111','17761300564',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

——————————————————————————————————————————————————————————————————————————————————————————————————————
数据交互的样例：
{flag："",mes:"",data:"",token:""}

flag:布尔类型,用于表示请求返回的状态，一般成功返回true，错误或异常返回false
mes:字符串类型，用于携带错误异常信息
data：object类型，用于携带返回数据
token：用于登陆成功时，返回session_id，用于客户端服务器会话跟踪

————————————————————————————————————————————————————————————————————————————————————————————————————————



