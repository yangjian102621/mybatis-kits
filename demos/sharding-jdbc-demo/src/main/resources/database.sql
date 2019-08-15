-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 08, 2018 at 07:25 PM
-- Server version: 5.7.22-0ubuntu0.16.04.1
-- PHP Version: 7.0.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mybatis-kits-demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` varchar(14) NOT NULL COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `age` int(5) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) NOT NULL COMMENT '性别',
  `create_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `balance` decimal(10,3) NOT NULL DEFAULT '0.000' COMMENT '余额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `age`, `sex`, `create_time`, `update_time`, `status`, `balance`) VALUES
('0123456-01', 'first name', 32, 1, NULL, NULL, 1, '78.859'),
('0123456-02', 'two name', 18, 2, NULL, NULL, 1, '0.000'),
('0123456-03', 'three name', 34, 1, NULL, NULL, 1, '0.000'),
('0123456-04', 'four name', 26, 2, NULL, NULL, 1, '0.000'),
('0123456-05', 'five name', 50, 1, NULL, NULL, 1, '0.000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;