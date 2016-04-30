set @old_unique_checks=@@unique_checks, unique_checks=0;
set @old_foreign_key_checks=@@foreign_key_checks, foreign_key_checks=0;
set @old_sql_mode=@@sql_mode, sql_mode='traditional,allow_invalid_dates';

-- drop schema if exists `jfj` ;
create schema if not exists `jfj` default character set utf8 collate utf8_general_ci ;

DROP TABLE IF EXISTS `jfj`.`record` ;
create table if not exists `jfj`.`record` (
    `qrcode` VARCHAR(128) NOT NULL,
    `contract_id` VARCHAR(36) NOT NULL,
    `url_qrcode` VARCHAR(512) NOT NULL,
    `promotion_point` INT(32) NOT NULL,
	  `created_on` datetime NOT NULL,
	  `updated_on` datetime,
    `deleted` INT(4) NOT NULL default 0,

     constraint `fk_record_1` foreign key (`contract_id`) references `jfj`.`contract`(`contract_id`),
     primary key (`qrcode`)
) engine innodb;

DROP TABLE IF EXISTS `jfj`.`contract` ;
create table if not exists `jfj`.`contract` (
    `contract_id` VARCHAR(36) NOT NULL,
    `contract_status` VARCHAR(64) NOT NULL,
    `merchant_id` VARCHAR(32) NOT NULL,
    `product_id` VARCHAR(32) NOT NULL,
    `description` VARCHAR(255),
    `point_payload` TEXT NOT NULL,
	  `created_on` datetime NOT NULL,
	  `updated_on` datetime,
	  `expect_inject_on` datetime,
	  `qrcode_count` BIGINT NOT NULL,
    `deleted` INT(4) NOT NULL default 0,

     primary key (`contract_id`)
) engine innodb;


