# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.20)
# Database: WeCook
# Generation Time: 2018-01-26 11:22:30 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Albums
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Albums`;

CREATE TABLE `Albums` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text,
  `user_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Friend_Requests
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Friend_Requests`;

CREATE TABLE `Friend_Requests` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_requester` int(11) DEFAULT NULL,
  `id_receiver` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `accepted_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Friend_Requests` WRITE;
/*!40000 ALTER TABLE `Friend_Requests` DISABLE KEYS */;

INSERT INTO `Friend_Requests` (`id`, `id_requester`, `id_receiver`, `status`, `created`, `accepted_at`)
VALUES
	(1,1,2,2,NULL,NULL);

/*!40000 ALTER TABLE `Friend_Requests` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Loves
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Loves`;

CREATE TABLE `Loves` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Loves` WRITE;
/*!40000 ALTER TABLE `Loves` DISABLE KEYS */;

INSERT INTO `Loves` (`id`, `user_id`, `post_id`, `created_at`)
VALUES
	(1,2,1,NULL),
	(65,1,1,NULL);

/*!40000 ALTER TABLE `Loves` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Notifications
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Notifications`;

CREATE TABLE `Notifications` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1 - love, 2- comment, 3 - friend request accepted, 4 - new friend request',
  `created_at` timestamp NULL DEFAULT NULL,
  `read` int(11) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table Photos
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Photos`;

CREATE TABLE `Photos` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `path` text,
  `user_id` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `recipe_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Photos` WRITE;
/*!40000 ALTER TABLE `Photos` DISABLE KEYS */;

INSERT INTO `Photos` (`id`, `path`, `user_id`, `album_id`, `created_at`, `recipe_id`)
VALUES
	(1,'/profiles/1/profile.jpg',1,1,NULL,NULL),
	(2,'/profiles/1/cover.jpg',1,1,NULL,NULL),
	(3,'/profiles/2/profile.jpg',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `Photos` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Post_Comments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Post_Comments`;

CREATE TABLE `Post_Comments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `text` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Post_Comments` WRITE;
/*!40000 ALTER TABLE `Post_Comments` DISABLE KEYS */;

INSERT INTO `Post_Comments` (`id`, `user_id`, `post_id`, `created_at`, `text`)
VALUES
	(1,2,1,NULL,'Awesome post, man! Good Job!'),
	(2,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `Post_Comments` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Profiles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Profiles`;

CREATE TABLE `Profiles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `profile_photo_id` int(11) DEFAULT NULL,
  `about` varchar(100) DEFAULT NULL,
  `birth_place` varchar(150) DEFAULT NULL,
  `lives_in` varchar(150) DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `joined` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `phone_number` varchar(100) DEFAULT NULL,
  `facebook_link` varchar(200) DEFAULT NULL,
  `twitter_link` varchar(200) DEFAULT NULL,
  `cover_photo_id` int(11) DEFAULT NULL,
  `first_name` text,
  `last_name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Profiles` WRITE;
/*!40000 ALTER TABLE `Profiles` DISABLE KEYS */;

INSERT INTO `Profiles` (`id`, `user_id`, `profile_photo_id`, `about`, `birth_place`, `lives_in`, `occupation`, `joined`, `status`, `website`, `phone_number`, `facebook_link`, `twitter_link`, `cover_photo_id`, `first_name`, `last_name`)
VALUES
	(1,1,1,'Hi, I?m James, I?m 36 and I work as a Digital Designer for the ?Daydreams? Agency in Pier 56.','Bucuresti, Romania','Iasi, Romania','Java Developer',NULL,'In a relationship','http://vladbutnaru.ro','0754827620','http://facebook.com/butnaru.vladut','http://twitter.com/wurgutvlad',2,'Vlad','Butnaru'),
	(2,2,3,'Hi, I?m James, I?m 36 and I work as a Digital Designer for the ?Daydreams? Agency in Pier 56.','Bucuresti, Romania','Iasi, Romania','Java Developer',NULL,'In a relationship','http://vladbutnaru.ro','0754827620','http://facebook.com/butnaru.vladut','http://twitter.com/wurgutvlad',2,'Admin','Superuser');

/*!40000 ALTER TABLE `Profiles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Timeline_Posts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Timeline_Posts`;

CREATE TABLE `Timeline_Posts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `published_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `post_type` int(11) DEFAULT NULL COMMENT '1 - text post, 2 - video post, 3 - image post, 4- share, 5 - recipe post',
  `text` varchar(1500) DEFAULT NULL,
  `link` varchar(200) DEFAULT NULL,
  `photo_id` int(11) DEFAULT NULL,
  `original_post_id` int(11) DEFAULT NULL,
  `recipe_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Timeline_Posts` WRITE;
/*!40000 ALTER TABLE `Timeline_Posts` DISABLE KEYS */;

INSERT INTO `Timeline_Posts` (`id`, `user_id`, `published_at`, `post_type`, `text`, `link`, `photo_id`, `original_post_id`, `recipe_id`)
VALUES
	(1,1,'2018-01-23 20:56:35',1,'Salutare ! Eu sunt vlad',NULL,1,NULL,NULL);

/*!40000 ALTER TABLE `Timeline_Posts` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Tokens
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Tokens`;

CREATE TABLE `Tokens` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `refreshed_at` timestamp NULL DEFAULT NULL,
  `code` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Tokens` WRITE;
/*!40000 ALTER TABLE `Tokens` DISABLE KEYS */;

INSERT INTO `Tokens` (`id`, `user_id`, `created_at`, `status`, `refreshed_at`, `code`)
VALUES
	(1,1,'2018-01-22 16:29:04',1,'2018-01-25 11:31:43','s62zcixCyznV1Rgihc0ww'),
	(2,1,'2018-01-23 10:25:46',1,'2018-01-23 10:25:46','yYuzquHvyMwHVqaam5FFE'),
	(3,1,'2018-01-23 11:13:38',1,'2018-01-25 10:48:20','92fHCG0hnNVQ9yjrP2I5X'),
	(4,1,'2018-01-23 11:16:30',1,'2018-01-23 11:16:30','kNae0zRT0I7RzvstknrsX'),
	(5,1,'2018-01-23 11:18:55',1,'2018-01-23 11:50:22','5o8VhZr1HpvTuz0TemFQW'),
	(6,1,'2018-01-23 11:52:06',1,'2018-01-23 15:34:21','KHviRlv9vqCdjBcxcWXne'),
	(7,1,'2018-01-23 20:55:00',1,'2018-01-25 18:47:05','TNshL52GDHHaGjBVroYCP');

/*!40000 ALTER TABLE `Tokens` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `last_login` date DEFAULT NULL,
  `number_of_logins` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;

INSERT INTO `Users` (`id`, `email`, `password`, `country`, `birth_date`, `first_name`, `last_name`, `last_login`, `number_of_logins`, `gender`, `status`)
VALUES
	(1,'vlad2me@gmail.com','test','Romania',NULL,'Vlad','Butnaru',NULL,0,1,1),
	(2,'admin@admin.com','admin','Romania',NULL,'Admin','Superuser',NULL,0,2,1);

/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
