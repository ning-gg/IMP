#product_flower 花草表
CREATE TABLE product_flower ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT, 
	`p_type` tinyint(2) NOT NULL COMMENT '类别：鲜花1、礼篮等', 
	`name` varchar(100) NOT NULL COMMENT '花草名字', 
	`main_material` varchar(200) NOT NULL COMMENT '鲜花主材', 
	`acc_material` varchar(300) NOT NULL COMMENT '鲜花辅材', 
	`pack` varchar(300) NOT NULL COMMENT '包装', 
	`number` int(5) NOT NULL COMMENT '鲜花朵数', 
	`flower_language` varchar(1000) NOT NULL COMMENT '花语', 
	`for_festival` varchar(300) NOT NULL DEFAULT '' COMMENT '鲜花适用节日，默认空为所有节日', 
	`for_purpose` varchar(300) NOT NULL DEFAULT '' COMMENT '鲜花用途', 
	`attach` varchar(200) NOT NULL DEFAULT '' COMMENT '附送', 
	`distribute` varchar(200) NOT NULL DEFAULT '' COMMENT '配送', 
	`distribute_fee` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '配送费-xxxx可配送至全国xx多城市，市区免费配送上门', 
	`official_price` decimal(10,2) NOT NULL COMMENT '市场价', 
	`price` decimal(10,2) NOT NULL COMMENT '价格', 
	`check_flag` tinyint(2) DEFAULT 0 COMMENT '审核通过标识1通过 0未通过', 
	`check_user` varchar(30) DEFAULT '' COMMENT '审核人', 
	`deleted` tinyint(1) default 0 comment '是否被删除 0未 1删', 
	`create_time` datetime NOT NULL comment '创建日期', 
	`last_update_time` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`), 
	KEY `idx_ptype` (`p_type`), 
	KEY `idx_main_material` (`main_material`),
	KEY `idx_number` (`number`), 
	KEY `idx_deleted` (`deleted`), 
	KEY `idx_create_time` (`create_time`), 
	KEY `idx_for_festival` (`for_festival`), 
	KEY `idx_for_purpose` (`for_purpose`), 
	KEY `idx_price` (`price`) 
) DEFAULT CHARSET=utf8 COMMENT='花草表';


#product_plants 绿植
CREATE TABLE product_plants ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT, 
	`name` varchar(100) NOT NULL COMMENT '绿植名字', 
	`p_type` tinyint(2) NOT NULL COMMENT '类别：绿植', 
	`material` varchar(300) NOT NULL COMMENT '绿植材料', 
	`pack` varchar(300) NOT NULL COMMENT '包装', 
	`remark` varchar(300) NOT NULL DEFAULT '' COMMENT '备注', 
	`attach` varchar(200) NOT NULL DEFAULT '' COMMENT '附送', 
	`distribute` varchar(200) NOT NULL DEFAULT '' COMMENT '配送', 
	`distribute_fee` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '配送费-xxxx可配送至全国xx多城市，市区免费配送上门', 
	`official_price` decimal(10,2) NOT NULL COMMENT '市场价', 
	`price` decimal(10,2) NOT NULL COMMENT '价格', 
	`check_flag` tinyint(2) DEFAULT 0 COMMENT '审核通过标识1通过 0未通过', 
	`check_user` varchar(30) DEFAULT '' COMMENT '审核人', 
	`deleted` tinyint(1) default 0 comment '是否被删除 0未 1删',
	`create_time` datetime NOT NULL comment '创建日期', 
	`last_update_time` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`), 
	KEY `idx_ptype` (`p_type`), 
	KEY `idx_deleted` (`deleted`),
	KEY `idx_create_time` (`create_time`),
	KEY `idx_for_festival` (`for_festival`),
	KEY `idx_for_purpose` (`for_purpose`),
	KEY `idx_price` (`price`) 
) DEFAULT CHARSET=utf8 COMMENT='绿植表';


#product_cake 蛋糕
CREATE TABLE product_cake ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT, 
	`name` varchar(100) NOT NULL COMMENT '蛋糕名字', 
	`p_type` tinyint(2) NOT NULL COMMENT '类别：蛋糕', 
	`material` varchar(300) NOT NULL COMMENT '材料', 
	`brand_id` varchar(50) NOT NULL COMMENT '蛋糕品牌id', 
	`layer` varchar(300) NOT NULL COMMENT '层数', 
	`remark` varchar(300) NOT NULL DEFAULT '' COMMENT '备注', 
	`attach` varchar(200) NOT NULL DEFAULT '' COMMENT '附送', 
	`distribute` varchar(200) NOT NULL DEFAULT '' COMMENT '配送', 
	`distribute_fee` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '配送费-xxxx可配送至全国xx多城市，市区免费配送上门', 
	`official_price` decimal(10,2) NOT NULL COMMENT '市场价', 
	`price` decimal(10,2) NOT NULL COMMENT '价格', 
	`check_flag` tinyint(2) DEFAULT 0 COMMENT '审核通过标识1通过 0未通过', 
	`check_user` varchar(30) DEFAULT '' COMMENT '审核人', 
	`deleted` tinyint(1) default 0 comment '是否被删除 0未 1删', 
	`create_time` datetime NOT NULL comment '创建日期', 
	`last_update_time` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`), 
	KEY `idx_ptype` (`p_type`), 
	KEY `idx_brand_id` (`brand_id`),
	KEY `idx_deleted` (`deleted`),
	KEY `idx_create_time` (`create_time`),
	KEY `idx_for_festival` (`for_festival`),
	KEY `idx_for_purpose` (`for_purpose`),
	KEY `idx_price` (`price`) 
) DEFAULT CHARSET=utf8 COMMENT='蛋糕表';


#product_detail 内容详情表
CREATE TABLE product_detail ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT, 
	`p_type` tinyint(2) NOT NULL COMMENT '唯一标识 type', 
	`p_id` bigint(20) NOT NULL COMMENT '唯一标识 id', 
	`body` mediumtext NOT NULL COMMENT '内容详情介绍', 
	PRIMARY KEY (`id`), 
	UNIQUE KEY `uniq_ptype_pid` (`p_type`, `p_id`) 
) DEFAULT CHARSET=utf8 COMMENT='内容详情表';


#pic_product product图片表
CREATE TABLE pic_product ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT, 
	`p_type` tinyint(2) NOT NULL COMMENT '唯一标识 type', 
	`p_id` bigint(20) NOT NULL COMMENT '唯一标识 id', 
	`pic_type` tinyint(2) NOT NULL DEFAULT 0 COMMENT '0图片 1视频', 
	`file_url` VARCHAR(200) NOT NULL COMMENT '如果是视频，file_url是视频地址；如果是图片，file_url是大图地址', 
	`file_url_small` VARCHAR(200) DEFAULT '' COMMENT '如果是视频，file_url_small是缩略图地址；如果是图片，file_url_small是小图地址', 
	`last_update_time` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`), 
	UNIQUE KEY `uniq_ptype_pid` (`p_type`, `p_id`), 
	KEY `idx_pictype` (`pic_type`), 
	KEY `idx_file_url` (`file_url`), 
	KEY `idx_file_url_small` (`file_url_small`) 
) DEFAULT CHARSET=utf8 COMMENT='产品图片表';


#order_info 订单
CREATE TABLE order_info ( 
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT, 
	`order_no` varchar(30) NOT NULL COMMENT '订单号', 
	`p_type` tinyint(2) NOT NULL COMMENT '唯一标识 type', 
	`p_id` bigint(20) NOT NULL COMMENT '唯一标识 id', 
	`contact` varchar(255) NOT NULL COMMENT '联系人', 
	`contact_tel` varchar(20) NOT NULL COMMENT '联系电话', 
	`contact_email` varchar(50) NOT NULL DEFAULT '' COMMENT '联系邮件', 
	`consumer` varchar(255) NOT NULL COMMENT '接收人', 
	`consumer_tel` varchar(20) NOT NULL COMMENT '接收人电话',
	`consumer_address` varchar(200) NOT NULL DEFAULT '' COMMENT '接收人地址', 
	`price` decimal(10,2) NOT NULL COMMENT '单价-花草绿植蛋糕（无附加）', 
	`express_fee` decimal(10,2) NOT NULL DEFAULT 0 COMMENT '运费', 
	`num` int(5) NOT NULL DEFAULT 0 COMMENT '数量', 
	`all_prices` decimal(10, 2) NOT NULL COMMENT '总价', 
	`no_pay` decimal(10, 2) NOT NULL DEFAULT 0 COMMENT '未支付价格', 
	`status` tinyint(4) NOT NULL COMMENT '订单状态', 
	`create_time` datetime NOT NULL COMMENT '订单创建时间', 
	`user_trace` varchar(100) DEFAULT NULL, 
	`cookie_QN44` varchar(50) NOT NULL DEFAULT '' COMMENT '保存待支付订单推送用户的Cookie', 
	`last_update_time` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`), 
	UNIQUE KEY `uniq_ptype_pid` (`p_type`, `p_id`), 
	KEY `idx_contact_tel` (`contact_tel`), 
	KEY `idx_consumer_tel` (`consumer_tel`), 
	KEY `idx_price` (`p_price`), 
	KEY `idx_all_prices` (`all_prices`), 
	KEY `idx_status` (`p_status`)
) DEFAULT CHARSET=utf8 COMMENT='订单表';


#comment 评论
CREATE TABLE `comment` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'comment id', 
	`p_type` tinyint(2) NOT NULL COMMENT '唯一标识 type', 
	`p_id` bigint(20) NOT NULL COMMENT '唯一标识 id', 
	`order_no` varchar(30) NOT NULL DEFAULT '' COMMENT '订单号',
	`title` DEFAULT '' COMMENT '评论一句话摘要', 
	`source_website` VARCHAR(100) DEFAULT '' COMMENT '如果是抓取，取值是来源网站，如果是UGC，取值是meilime', 
	`type` tinyint(2) DEFAULT '' COMMENT '用于区分UGC 0,抓取 1和改写的2', 
	`rank` int(2) DEFAULT '' COMMENT '评价星级0-5', 
	`uid` BIGINT(20) DEFAULT '' COMMENT '如果是UGC，则有值', 
	`user_name` varchar(30) DEFAULT '' COMMENT '评论发表用户', 
	`create_date` DATETIME NOT NULL COMMENT '发布日期', 
	`content` varchar(1000) DEFAULT '' COMMENT '评论内容', 
	`abs_content` varchar(200) DEFAULT '' COMMENT '如果是抓取的，取值是评论摘要，否则为空', 
	`address` varchar(100) DEFAULT '' COMMENT '接收地址', 
	`customer_ip` varchar(30) DEFAULT '' COMMENT '评论用户ip', 
	`check_flag` tinyint(2) DEFAULT 0 COMMENT '审核通过标识1通过 0未通过', 
	`check_user` varchar(30) DEFAULT '' COMMENT '审核人', 
	`root_id` BIGINT(20) NOT NULL COMMENT 'root id', 
	`last_update_time` datetime NOT NULL COMMENT '最后更新时间', 
	PRIMARY KEY (`id`), 
	UNIQUE KEY `uniq_ptype_pid` (`p_type`, `p_id`) 
) DEFAULT CHARSET=utf8 COMMENT='评论表';


#comments_reply表 包括评论回应信息，用于评论展示，主要用于detail页面评论展示
CREATE TABLE `comment_reply` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'comment reply id', 
	`cid` BIGINT(20) NOT NULL COMMENT 'comment表id',	
	`uid` BIGINT(20) DEFAULT '' COMMENT 'Uid', 
	`user_name` varchar(30) VARCHAR() DEFAULT '' COMMENT '评论发表用户', 
	`create_date` DATETIME NOT NULL COMMENT '发布日期', 
	`content` varchar(1000) DEFAULT '' COMMENT '评论内容', 
	`customer_ip` varchar(30) DEFAULT '' COMMENT '评论用户ip', 
	`check_flag` tinyint(2) DEFAULT 1 COMMENT '审核通过标识1通过 0未通过', 
	`check_user` varchar(30) DEFAULT '' COMMENT '审核人', 
	`check_time` DATETIME NOT NULL COMMENT '审核时间',
	PRIMARY KEY (`id`), 
	KEY `idx_cid` (`cid`) 
) DEFAULT CHARSET=utf8 COMMENT='评论回复表';


#help_center 帮助中心
CREATE TABLE help_center ( 
	`id` int(4) unsigned NOT NULL AUTO_INCREMENT, 
	`parent_id` int(4) NOT NULL COMMENT '父级类目', 
	`name` varchar(100) NOT NULL COMMENT '分类名字', 
	`path` varchar(300) default '' COMMENT '访问路径', 
	`order` int(4) NOT NULL COMMENT '排序', 
	PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT='帮助中心分类';

#help_page 帮助单页内容 与 help_center一一对应
CREATE TABLE help_page ( 
	`id` int(4) unsigned NOT NULL AUTO_INCREMENT, 
	`h_id` int(4) NOT NULL COMMENT 'help center唯一标识id', 
	`body` mediumtext default '' COMMENT '内容详情', 
	PRIMARY KEY (`id`) 
) DEFAULT CHARSET=utf8 COMMENT='单页内容详情表';
