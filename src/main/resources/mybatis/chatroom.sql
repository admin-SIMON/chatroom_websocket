DROP DATABASE IF EXISTS `chatroom`;

CREATE DATABASE `chatroom`;

USE `chatroom`;

CREATE TABLE `User`(
	`uid` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
	`uUsername` VARCHAR(20) NOT NULL COMMENT '昵称',
	`uPassword` VARCHAR(20) COMMENT '密码',
	`uImage_url` VARCHAR(255) NOT NULL COMMENT '头像路径'
);

INSERT INTO `User` VALUES (DEFAULT,'admin','admin','https://biyintech.picp.vip/images/ac6f629f-72e2-455f-9246-b2a94a46d564.jpg'),
(DEFAULT,'simon','simon','https://biyintech.picp.vip/images/ac6f629f-72e2-455f-9246-b2a94a46d564.jpg'),
(DEFAULT,'chris','chris','https://biyintech.picp.vip/images/0b0e6361-c1e2-4a6d-b3ce-cbd82b9c1a07.jpg'),
(DEFAULT,'ace','ace','https://biyintech.picp.vip/images/2488dad9-7185-4e74-84ab-5b5ce75ecb9b.jpg');

SELECT * FROM `User`;

CREATE TABLE `MsgEx`(
	`mId` INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '聊天信息ID',
	`mFrom` INT NOT NULL COMMENT '发送用户',
	`mTo` INT NOT NULL COMMENT '接收用户',
	`mContent` VARCHAR(255) NOT NULL COMMENT '消息内容',
	`mTime` DATETIME NOT NULL DEFAULT NOW() COMMENT '发送时间',
	`mStatus` INT NOT NULL DEFAULT 0 COMMENT '消息接收状态 0 为已接收/1 为未接收'
);

INSERT INTO `MsgEx` VALUES (DEFAULT,1,2,'朗格里格朗',DEFAULT,0);

INSERT INTO `MsgEx` VALUES (DEFAULT,1,2,'朗格里格朗未接收',DEFAULT,1);

#SELECT * FROM `MsgEx` WHERE mFrom=1 AND mTo=2;

#SELECT u.*,COUNT(m.mFrom) unread FROM `MsgEx` m ,`User` u WHERE m.mTo=2 AND mStatus=1 AND u.uid=m.mFrom GROUP BY m.mFrom;