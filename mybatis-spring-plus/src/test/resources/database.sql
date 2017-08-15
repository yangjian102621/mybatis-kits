CREATE DATABASE demo DEFAULT CHARACTER SET utf8 ;

USE demo;

DROP TABLE IF EXISTS user;
CREATE TABLE user(
	  id              VARCHAR(14) NOT NULL COMMENT '主键',
    name            VARCHAR(30) NOT NULL COMMENT '名称',
    age             INT(5) COMMENT '年龄',
    sex             TINYINT(1) NOT NULL COMMENT '性别',
    PRIMARY KEY (id),
    UNIQUE (name)
) COMMENT='用户表';

INSERT INTO user (id, name, age, sex) VALUES
    ("0123456-01","first name",21,1),
    ("0123456-02","two name",18,2),
    ("0123456-03","three name",34,1),
    ("0123456-04","four name",26,2),
    ("0123456-05","five name",50,1);
