-- �������ݿ� easymall
create database easymall;

-- ʹ��easymall��
use easymall;

-- ����user�� id���û��������룬�ǳƣ�����
 create table user(
 id int primary key auto_increment,-- �û���
 username varchar(50), -- �û���
 password varchar(50), -- ����
 nickname varchar(50), -- �ǳ�
 email varchar(50) -- ����
 );
 -- ��������
 insert into user values(null,'admin','123','��������Ա','123@qq.com');
 insert into user values(null,'�ŷ�','123','����Ա','123@qq.com');