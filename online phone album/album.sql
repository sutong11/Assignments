SET FOREIGN_KEY_CHECKS=0;
set charset utf8;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `friend`;
DROP TABLE IF EXISTS `group`;
DROP TABLE IF EXISTS `photo`;
DROP TABLE IF EXISTS `album`;
DROP TABLE IF EXISTS `user`;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`user_id` 	int(20) NOT NULL auto_increment,
`user_name` varchar(20) collate utf8_bin NOT NULL,
`password` varchar(20) collate utf8_bin NOT NULL,
`email` varchar(20) collate utf8_bin default NULL,
`phone` varchar(20) collate utf8_bin default NULL,
`signnature` varchar(50) collate utf8_bin default NULL,
PRIMARY KEY  (`user_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

insert into user (user_name, password,email,phone) values ('aaa','sssddd','12345@qq.cv','13211112222');
insert into user (user_name, password,email,phone) values ('bbb','sssddd','12345@qq.cv','13211112222');
insert into user (user_name, password,email,phone) values ('ccc','sssddd','12345@qq.cv','13211112222');
insert into user (user_name, password,email,phone) values ('ddd','sssddd','12345@qq.cv','13211112222');
insert into user (user_name, password,email,phone) values ('xt','sssddd','12345@qq.cv','13211112222');
-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
`album_id` 	int(11) NOT NULL auto_increment,
`album_name` 	varchar(50) collate utf8_bin NOT NULL,
`album_info` 	varchar(100) collate utf8_bin default NULL,
`album_user_id` int(11) NOT NULL,
`album_date` 	date NOT NULL,
`album_authority` int(11) NOT NULL,
`photo_num` int(11) default 0,
PRIMARY KEY  (`album_id`),
CONSTRAINT `album_user` FOREIGN KEY (`album_user_id`) REFERENCES user(user_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
`photo_id` 	int(11) NOT NULL auto_increment,
`photo_name` 	varchar(20) collate utf8_bin NOT NULL,
`photo_album_id` int(11) NOT NULL,
`photo_url` 	varchar(100) collate utf8_bin NOT NULL,
`photo_info` 	varchar(100) collate utf8_bin default NULL,
`photo_date` 	date NOT NULL,
PRIMARY KEY  (`photo_id`),
KEY `photo_album_id` (`photo_album_id`),
CONSTRAINT `photo_ibfk_1` FOREIGN KEY (`photo_album_id`) REFERENCES `album` (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `group_id` int(11) NOT NULL auto_increment,
  `group_name` varchar(20) collate utf8_bin NOT NULL,
  `group_user_id` int(11) NOT NULL,
  PRIMARY KEY  (`group_id`),
  KEY `group_user_id` (`group_user_id`),
  CONSTRAINT `group_ibfk_1` FOREIGN KEY (`group_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `friend_id` int(11) NOT NULL,
  `friend_user_id` int(11) NOT NULL,
  `friend_group_id` int(20) NOT NULL,
  PRIMARY KEY  (`friend_id`),
  KEY `friend_group_id` (`friend_group_id`),
  KEY `friend_user_id` (`friend_user_id`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`friend_group_id`) REFERENCES `group` (`group_id`),
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`friend_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ----------------------------
-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL auto_increment,
  `comment_user_id` int(11) NOT NULL,
  `comment_photo_id` int(11) NOT NULL,
  `comment_date` date NOT NULL,
  `comment_info` varchar(100) collate utf8_bin NOT NULL,
  PRIMARY KEY  (`comment_id`),
  KEY `comment_user_id` (`comment_user_id`),
  KEY `comment_photo_id` (`comment_photo_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`comment_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`comment_photo_id`) REFERENCES `photo` (`photo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Records 
-- ----------------------------
