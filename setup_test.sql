-- Adminer 4.2.2 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `dipl_test`;
CREATE DATABASE `dipl_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dipl_test`;

DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `url` varchar(45) NOT NULL,
  `port` varchar(45) NOT NULL,
  `hearthbeat_interval` int(11) NOT NULL,
  `unresponsive_interval` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `protocol` varchar(255) NOT NULL,
  `notification_interval` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `app_info_has_notification_channel`;
CREATE TABLE `app_info_has_notification_channel` (
  `app_info_id` bigint(20) unsigned NOT NULL,
  `notification_channel_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`app_info_id`,`notification_channel_id`),
  KEY `fk_app_info_has_notification_channel_notification_channel1_idx` (`notification_channel_id`),
  KEY `fk_app_info_has_notification_channel_app_info1_idx` (`app_info_id`),
  CONSTRAINT `fk_app_info_has_notification_channel_app_info1` FOREIGN KEY (`app_info_id`) REFERENCES `app_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_app_info_has_notification_channel_notification_channel1` FOREIGN KEY (`notification_channel_id`) REFERENCES `notification_channel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `app_info_has_notification_group`;
CREATE TABLE `app_info_has_notification_group` (
  `notification_group_id` bigint(20) NOT NULL,
  `app_info_id` bigint(20) NOT NULL,
  PRIMARY KEY (`app_info_id`,`notification_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `app_info_has_user`;
CREATE TABLE `app_info_has_user` (
  `app_info_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`app_info_id`,`user_id`),
  KEY `fk_app_info_has_user_user1_idx` (`user_id`),
  KEY `fk_app_info_has_user_app_info1_idx` (`app_info_id`),
  CONSTRAINT `fk_app_info_has_user_app_info1` FOREIGN KEY (`app_info_id`) REFERENCES `app_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_app_info_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `app_user_role`;
CREATE TABLE `app_user_role` (
  `USER_ID` bigint(20) NOT NULL,
  `USER_ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`,`USER_ROLE_ID`),
  KEY `FK_48ybsmsj3f8iou4dktsa454da` (`USER_ROLE_ID`),
  CONSTRAINT `FK_48ybsmsj3f8iou4dktsa454da` FOREIGN KEY (`USER_ROLE_ID`) REFERENCES `user_roles` (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `email_notification`;
CREATE TABLE `email_notification` (
  `id` bigint(20) unsigned NOT NULL,
  `email_address` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`),
  CONSTRAINT `fk_table1_notification_channel1` FOREIGN KEY (`id`) REFERENCES `notification_channel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `notification_channel`;
CREATE TABLE `notification_channel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `channel_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `channel_name_UNIQUE` (`channel_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `notification_group`;
CREATE TABLE `notification_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `notification_policy_has_notification_channel`;
CREATE TABLE `notification_policy_has_notification_channel` (
  `notification_policy_id` bigint(20) unsigned NOT NULL,
  `notification_channel_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`notification_policy_id`,`notification_channel_id`),
  KEY `fk_notification_policy_has_notification_channel_notificatio_idx` (`notification_channel_id`),
  KEY `fk_notification_policy_has_notification_channel_notificatio_idx1` (`notification_policy_id`),
  CONSTRAINT `fk_notification_policy_has_notification_channel_notification_1` FOREIGN KEY (`notification_policy_id`) REFERENCES `resource_notification_policy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_policy_has_notification_channel_notification_2` FOREIGN KEY (`notification_channel_id`) REFERENCES `notification_channel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `resource_notification_policy`;
CREATE TABLE `resource_notification_policy` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cpu_warn_level` float unsigned NOT NULL,
  `cpu_critical_level` float unsigned NOT NULL,
  `cpu_notification_inverval` int(10) unsigned NOT NULL,
  `memory_warn_level` float unsigned NOT NULL,
  `memory_critical_level` float unsigned NOT NULL,
  `memory_notification_interval` int(10) unsigned NOT NULL,
  `disk_usage_warn_level` float unsigned NOT NULL,
  `disk_usage_critical_level` float unsigned NOT NULL,
  `disk_usage_notification_interval` int(10) unsigned NOT NULL,
  `server_unresponsive_interval` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `disk_io_critical_level` float DEFAULT NULL,
  `disk_io_notification_interval` int(11) DEFAULT NULL,
  `disk_io_warn_level` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `server`;
CREATE TABLE `server` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ip_address` varchar(45) NOT NULL,
  `domain` varchar(45) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `notification_policy_id` bigint(20) unsigned DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_server_notification_policy1_idx` (`notification_policy_id`),
  CONSTRAINT `fk_server_notification_policy1` FOREIGN KEY (`notification_policy_id`) REFERENCES `resource_notification_policy` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `server_has_notification_group`;
CREATE TABLE `server_has_notification_group` (
  `server_id` bigint(20) unsigned NOT NULL,
  `notification_group_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`server_id`,`notification_group_id`),
  KEY `fk_server_has_notification_group_notification_group1_idx` (`notification_group_id`),
  KEY `fk_server_has_notification_group_server1_idx` (`server_id`),
  CONSTRAINT `fk_server_has_notification_group_notification_group1` FOREIGN KEY (`notification_group_id`) REFERENCES `notification_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_server_has_notification_group_server1` FOREIGN KEY (`server_id`) REFERENCES `server` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `server_has_user`;
CREATE TABLE `server_has_user` (
  `server_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`server_id`,`user_id`),
  KEY `fk_server_has_user_user1_idx` (`user_id`),
  KEY `fk_server_has_user_server1_idx` (`server_id`),
  CONSTRAINT `fk_server_has_user_server1` FOREIGN KEY (`server_id`) REFERENCES `server` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_server_has_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_has_notification_group`;
CREATE TABLE `user_has_notification_group` (
  `user_id` bigint(20) unsigned NOT NULL,
  `notification_group_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`notification_group_id`),
  KEY `fk_user_has_notification_group_notification_group1_idx` (`notification_group_id`),
  KEY `fk_user_has_notification_group_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_notification_group_notification_group1` FOREIGN KEY (`notification_group_id`) REFERENCES `notification_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_notification_group_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `user_roles`(`user_role_id`, `role`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `user`(`id`, `firstName`, `lastName`, `email`, `phone`, `password`) VALUES (1, 'sample', 'admin', 'sample@localhost.com', 123456, '$2a$10$HccGXskYxmZB.qkRyFmOZ.li4vgzQB5buHZkM3jXjKkLu21ZzoYjy');
INSERT INTO `app_user_role` (`USER_ID`, `USER_ROLE_ID`) VALUES (1,1);


-- 2017-05-25 18:44:16
