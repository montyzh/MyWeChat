# Host: localhost  (Version: 5.5.53)
# Date: 2018-07-08 01:14:45
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "add_friend_temp"
#

DROP TABLE IF EXISTS `add_friend_temp`;
CREATE TABLE `add_friend_temp` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

#
# Data for table "add_friend_temp"
#

/*!40000 ALTER TABLE `add_friend_temp` DISABLE KEYS */;
INSERT INTO `add_friend_temp` VALUES (70,92,'2018-07-07 22:28:18','24.86468068767116-102.8604516944173'),(71,91,'2018-07-07 22:28:33','24.86467929052149-102.860936758405'),(72,91,'2018-07-07 22:30:22','24.86471932307023-102.8606860048725'),(73,92,'2018-07-07 22:30:34','24.861569-102.862528');
/*!40000 ALTER TABLE `add_friend_temp` ENABLE KEYS */;

#
# Structure for table "circle"
#

DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `like` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

#
# Data for table "circle"
#

/*!40000 ALTER TABLE `circle` DISABLE KEYS */;
INSERT INTO `circle` VALUES (68,91,'今天好累啊，写了1000行bug！','2018-07-07 22:33:11','/upload/IMG_0795_1530973991752_55.JPG;/upload/IMG_0342_1530973991755_98.JPG;','聚贤街68附近附近','小黄,二哈,'),(69,92,'大吉大利，开始复习。','2018-07-07 22:35:39','/upload/IMG_20180612_161555_1530974139001_52.jpg;/upload/IMG_20180626_165320_1530974139004_1.jpg;','梁王路','二哈,二哈,');
/*!40000 ALTER TABLE `circle` ENABLE KEYS */;

#
# Structure for table "rely"
#

DROP TABLE IF EXISTS `rely`;
CREATE TABLE `rely` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `circle_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "rely"
#

/*!40000 ALTER TABLE `rely` DISABLE KEYS */;
INSERT INTO `rely` VALUES (1,69,'你好','二哈'),(2,69,'你好','二哈'),(3,69,'你好','二哈'),(4,68,'yes','二哈');
/*!40000 ALTER TABLE `rely` ENABLE KEYS */;

#
# Structure for table "users"
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cell_phone` varchar(32) NOT NULL COMMENT '电话号码',
  `password` varchar(64) NOT NULL COMMENT '登录密码',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `portrait` varchar(64) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

#
# Data for table "users"
#

INSERT INTO `users` VALUES (91,'10086','1','二哈','/portraits/john.jpg'),(92,'10010','1','小黄','/portraits/john.jpg');

#
# Structure for table "messages"
#

DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL COMMENT '发送方ID',
  `receiver_id` int(11) NOT NULL COMMENT '接收方ID',
  `msg_type` varchar(64) NOT NULL COMMENT '消息类别',
  `msg_content` text NOT NULL COMMENT '消息内容',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`),
  KEY `messages_users_fk` (`sender_id`),
  KEY `messages_users_fk_1` (`receiver_id`),
  CONSTRAINT `messages_users_fk` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `messages_users_fk_1` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

#
# Data for table "messages"
#

INSERT INTO `messages` VALUES (11,91,92,'text','你好，我是二哈。。。','2018-07-07 22:29:02'),(12,92,91,'text','我是小黄','2018-07-07 22:31:02');

#
# Structure for table "friends"
#

DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id_a` int(11) NOT NULL COMMENT 'A用户ID',
  `user_id_b` int(11) NOT NULL COMMENT 'B用户ID',
  PRIMARY KEY (`id`),
  KEY `friend_1_fk` (`user_id_a`),
  KEY `friend_2_fk` (`user_id_b`),
  CONSTRAINT `friend_1_fk` FOREIGN KEY (`user_id_a`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `friend_2_fk` FOREIGN KEY (`user_id_b`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "friends"
#

INSERT INTO `friends` VALUES (6,91,92),(7,92,91);

#
# Structure for table "v_last_session_message"
#

DROP VIEW IF EXISTS `v_last_session_message`;
CREATE VIEW `v_last_session_message` AS 
  select `m2`.`id` AS `id`,`m2`.`sender_id` AS `sender_id`,`m2`.`receiver_id` AS `receiver_id`,`m2`.`msg_type` AS `msg_type`,`m2`.`msg_content` AS `msg_content`,`m2`.`send_time` AS `send_time` from (`v_ls` `ls` left join `messages` `m2` on((`ls`.`id` = `m2`.`id`)));

#
# Structure for table "v_ls"
#

DROP VIEW IF EXISTS `v_ls`;
CREATE VIEW `v_ls` AS 
  select `s`.`session_id` AS `session_id`,max(`s`.`id`) AS `id` from `v_s` `s` group by `s`.`session_id`;

#
# Structure for table "v_s"
#

DROP VIEW IF EXISTS `v_s`;
CREATE VIEW `v_s` AS 
  select concat((case when (`m`.`receiver_id` < `m`.`sender_id`) then `m`.`receiver_id` else `m`.`sender_id` end),'/',(case when (`m`.`receiver_id` > `m`.`sender_id`) then `m`.`receiver_id` else `m`.`sender_id` end)) AS `session_id`,`m`.`id` AS `id`,`m`.`sender_id` AS `sender_id`,`m`.`receiver_id` AS `receiver_id`,`m`.`msg_type` AS `msg_type`,`m`.`msg_content` AS `msg_content`,`m`.`send_time` AS `send_time` from `messages` `m`;
