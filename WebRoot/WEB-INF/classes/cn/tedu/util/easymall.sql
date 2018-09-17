-- 创建数据库 easymall
create database easymall;

-- 使用easymall库
use easymall;

-- 创建user表 id，用户名，密码，昵称，邮箱
 create table user(
 id int primary key auto_increment,-- 用户名
 username varchar(50), -- 用户名
 password varchar(50), -- 密码
 nickname varchar(50), -- 昵称
 email varchar(50) -- 邮箱
 );
 -- 插入数据
 insert into user values(null,'admin','123','超级管理员','123@qq.com');
 insert into user values(null,'张飞','123','管理员','123@qq.com');