/*
 Navicat Premium Data Transfer

 Source Server         : IM_SOCKET
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : 123.206.101.41:3306
 Source Schema         : im_socket

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 01/01/2020 11:08:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 169 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_adv
-- ----------------------------
DROP TABLE IF EXISTS `mv_adv`;
CREATE TABLE `mv_adv`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `adv_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '广告名称',
  `image_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '广告图片地址',
  `target` int(255) NULL DEFAULT NULL COMMENT '打开方式：activity, http-url等',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '打开链接',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序',
  `disabled` int(255) NULL DEFAULT NULL COMMENT '是否废弃：0-否，1-是',
  `video_type_id` int(255) NULL DEFAULT NULL COMMENT '视频分类ID',
  `video_id` int(11) NULL DEFAULT NULL COMMENT '视频ID',
  `video_url_id` int(11) NULL DEFAULT NULL COMMENT '视频地址ID',
  `column_id` int(11) NULL DEFAULT NULL COMMENT '专栏ID',
  `type` int(255) NULL DEFAULT NULL COMMENT '广告类型：0-首页，1-视频，2-播放，3-我的，4-专栏',
  `duration` int(11) NULL DEFAULT NULL COMMENT '视频播放广告显示时长',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '广告' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_carousel
-- ----------------------------
DROP TABLE IF EXISTS `mv_carousel`;
CREATE TABLE `mv_carousel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `image_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片地址',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '链接地址',
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '打开方式：_blank, _self',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '所属栏目ID',
  `disabled` int(255) NULL DEFAULT NULL COMMENT '是否废弃：0-否，1-是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '轮播图' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_collect_record
-- ----------------------------
DROP TABLE IF EXISTS `mv_collect_record`;
CREATE TABLE `mv_collect_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` int(11) NULL DEFAULT NULL,
  `video_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频标题',
  `video_sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频子标题',
  `video_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频封面',
  `ctime` timestamp(0) NULL DEFAULT NULL COMMENT '收藏时间',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '收藏记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_column
-- ----------------------------
DROP TABLE IF EXISTS `mv_column`;
CREATE TABLE `mv_column`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `column_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专栏名称',
  `movie_number` int(11) NULL DEFAULT NULL COMMENT '影片数量',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专栏简介',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专栏封面',
  `video_type_id` int(11) NULL DEFAULT NULL COMMENT '视频分类ID',
  `video_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '专栏' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_feedback
-- ----------------------------
DROP TABLE IF EXISTS `mv_feedback`;
CREATE TABLE `mv_feedback`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uid` int(255) NULL DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `ctime` timestamp(0) NULL DEFAULT NULL COMMENT '反馈时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '意见反馈' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_notification
-- ----------------------------
DROP TABLE IF EXISTS `mv_notification`;
CREATE TABLE `mv_notification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '通知内容',
  `ctime` timestamp(0) NULL DEFAULT NULL COMMENT '发表时间',
  `creator` int(255) NULL DEFAULT NULL COMMENT '发送人',
  `creator_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发送人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_option
-- ----------------------------
DROP TABLE IF EXISTS `mv_option`;
CREATE TABLE `mv_option`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `option_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '键',
  `option_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '值',
  `key_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '键-名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_play_record
-- ----------------------------
DROP TABLE IF EXISTS `mv_play_record`;
CREATE TABLE `mv_play_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `video_id` int(11) NULL DEFAULT NULL COMMENT '视频ID',
  `video_url_id` int(11) NULL DEFAULT NULL COMMENT '视频选集ID',
  `video_url_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频选集名称',
  `ctime` timestamp(0) NULL DEFAULT NULL COMMENT '播放时间',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `video_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频标题',
  `video_sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频子标题',
  `video_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频封面',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '视频播放链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '播放记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_rank
-- ----------------------------
DROP TABLE IF EXISTS `mv_rank`;
CREATE TABLE `mv_rank`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简介',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序号',
  `rank_field` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '排行使用的字段，根据字段进行排序',
  `rank_table` int(11) NULL DEFAULT 0 COMMENT '排行的表，对应数据字典，默认0',
  `disabled` int(255) NULL DEFAULT NULL COMMENT '是否废弃',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '排行' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_ref_notification_user
-- ----------------------------
DROP TABLE IF EXISTS `mv_ref_notification_user`;
CREATE TABLE `mv_ref_notification_user`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `notification_id` int(11) NULL DEFAULT NULL COMMENT '通知ID',
  `notification_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '通知标题',
  `notification_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '通知详情',
  `read_status` int(1) NULL DEFAULT 0 COMMENT '是否已读：0-否，1-是',
  `ctime` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户收到的通知记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_ref_video_column
-- ----------------------------
DROP TABLE IF EXISTS `mv_ref_video_column`;
CREATE TABLE `mv_ref_video_column`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `video_id` int(11) NULL DEFAULT NULL,
  `column_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `video_id`(`video_id`) USING BTREE,
  INDEX `column_id`(`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 162 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_share_ip
-- ----------------------------
DROP TABLE IF EXISTS `mv_share_ip`;
CREATE TABLE `mv_share_ip`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'IP',
  `upper_id` int(11) NULL DEFAULT NULL COMMENT '上级用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '分享使用的IP与上级ID存储表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_share_record
-- ----------------------------
DROP TABLE IF EXISTS `mv_share_record`;
CREATE TABLE `mv_share_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `to_uid` int(11) NULL DEFAULT NULL COMMENT '下级用户ID',
  `to_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下级用户名称',
  `to_ctime` timestamp(0) NULL DEFAULT NULL COMMENT '下级注册时间',
  `watch_number` int(11) NULL DEFAULT NULL COMMENT '获得的观影次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '分享记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_tab
-- ----------------------------
DROP TABLE IF EXISTS `mv_tab`;
CREATE TABLE `mv_tab`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tab_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Tab名称',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序号',
  `disabled` int(255) NULL DEFAULT NULL COMMENT '是否可用：0-否，1-是',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `tab_cn_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '中文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Tab' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_tag
-- ----------------------------
DROP TABLE IF EXISTS `mv_tag`;
CREATE TABLE `mv_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` int(11) NULL DEFAULT NULL COMMENT '视频ID',
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签名称',
  `tag_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签KEY',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `video_id`(`video_id`) USING BTREE,
  INDEX `tag_name`(`tag_name`(191)) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_user_token
-- ----------------------------
DROP TABLE IF EXISTS `mv_user_token`;
CREATE TABLE `mv_user_token`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `expire_time` timestamp(0) NULL DEFAULT NULL,
  `ctime` timestamp(0) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_video
-- ----------------------------
DROP TABLE IF EXISTS `mv_video`;
CREATE TABLE `mv_video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '子标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '简介',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `ctime` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `play_number` int(11) NULL DEFAULT NULL COMMENT '播放次数',
  `like_number` int(11) NULL DEFAULT NULL COMMENT '收藏数量',
  `comment_number` int(11) NULL DEFAULT NULL COMMENT '评论数量',
  `score` double(255, 1) NULL DEFAULT NULL COMMENT '评分',
  `series_number` int(11) NULL DEFAULT NULL COMMENT '剧集数量',
  `disabled` int(255) NULL DEFAULT NULL COMMENT '是否下架：0-否，1-是',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序号',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `title`(`title`(191)) USING BTREE,
  INDEX `disabled`(`disabled`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '视频信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_video_type
-- ----------------------------
DROP TABLE IF EXISTS `mv_video_type`;
CREATE TABLE `mv_video_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '栏目名称',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序',
  `disabled` int(255) NULL DEFAULT NULL COMMENT '是否废弃：0-否，1-是',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '视频分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mv_video_url
-- ----------------------------
DROP TABLE IF EXISTS `mv_video_url`;
CREATE TABLE `mv_video_url`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `video_id` int(11) NULL DEFAULT NULL COMMENT '视频ID',
  `orders` int(255) NULL DEFAULT NULL COMMENT '排序',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '选集名称',
  `disabled` int(255) NULL DEFAULT NULL COMMENT '是否废弃',
  `duration` double(255, 0) NULL DEFAULT NULL COMMENT '时长',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '视频地址' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `type` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '字典类型0为string,1为number',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `indextable_dict_code`(`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dict_copy1
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_copy1`;
CREATE TABLE `sys_dict_copy1`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `type` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '字典类型0为string,1为number',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `indextable_dict_code`(`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典id',
  `item_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典项文本',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典项值',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort_order` int(10) NULL DEFAULT NULL COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态（1启用 0不启用）',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_table_dict_id`(`dict_id`) USING BTREE,
  INDEX `index_table_sort_order`(`sort_order`) USING BTREE,
  INDEX `index_table_dict_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ctime` timestamp(0) NULL DEFAULT NULL COMMENT '调用时间',
  `endtime` timestamp(0) NULL DEFAULT NULL COMMENT '结束时间',
  `exctime` double(5, 2) NULL DEFAULT 0.00 COMMENT '执行时间（秒）',
  `host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用方主机',
  `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `device` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `thread` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '线程',
  `request_clazz` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名',
  `request_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '参数（JSON）',
  `request_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型：POST/GET等',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '定义的消息',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求结果，如果出现异常就存储异常信息',
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `call_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用来源：api/admin',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `success` int(3) NULL DEFAULT NULL COMMENT '是否成功：1是，0否',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2738 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统操作日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_type` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '用户类型;0:admin;1:普通成员',
  `sex` tinyint(3) NULL DEFAULT 0 COMMENT '性别;0:保密,1:男,2:女',
  `last_login_time` timestamp(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `ctime` timestamp(0) NULL DEFAULT NULL COMMENT '注册时间',
  `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录密码;cmf_password加密',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '用户昵称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户登录邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '个性签名',
  `last_login_ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录ip',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '中国手机不带国家代码，国际手机号格式为：国家代码-手机号',
  `login_type` tinyint(3) NULL DEFAULT 0 COMMENT '账号类型，0SMS，1QQ，2微信，3新浪，4facebook，5twitter',
  `qq_openid` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'QQ openid',
  `wx_openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '三方openid',
  `source` tinyint(3) NULL DEFAULT 0 COMMENT '设备来源，0web，1android，2ios，3小程序',
  `online` tinyint(3) NULL DEFAULT 0 COMMENT '在线，0离线，1在线',
  `last_online_time` timestamp(0) NULL DEFAULT NULL COMMENT '离线时间',
  `lng` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '经度',
  `lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '纬度',
  `channel` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '渠道号',
  `max_watch_number` int(11) NULL DEFAULT NULL COMMENT '最大观影次数',
  `today_watch_number` int(11) NULL DEFAULT NULL COMMENT '今日观影次数',
  `upper_uid` int(11) NULL DEFAULT NULL COMMENT '上级ID',
  `share_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邀请码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_login`(`username`) USING BTREE,
  INDEX `user_nickname`(`nickname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
