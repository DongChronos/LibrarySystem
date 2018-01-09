<<<<<<< HEAD
use master
go
if exists(select * from sysdatabases where name='Library')
drop database Library
go
 
create database Library
on
(
	name = N'Library',
	filename = N'D:\Code\2017ClassProject\Library.mdf',
	size = 10mb,
	maxsize = 200mb,
	filegrowth = 1mb
)
log on
(
	name = N'Library_log',
	filename = N'D:\Code\2017ClassProject\Library.ldf',
	size = 10mb,
	maxsize = 1gb,
	filegrowth = 10mb
);

--人物信息表
use Library
go
create table UserInfor
(
	UserID int primary key identity(1,1) NOT NULL, --人物ID
	PeopleName varchar(128) NOT NULL, --人物名字
	LoginName varchar(128) NULL, --登录名
	Password varchar(256) NOT NULL, --登录密码
	Phone varchar(11) NULL unique, --电话
	Email varchar(128) NULL unique, --Email
	Gender bit NULL, --性别 0代表男 1代表女
	QQ varchar(128) NULL, --QQ号码
	WeChat varchar(128) NULL, --微信号
	Status int NOT NULL, --账号状态 0开放 1冻结
	UserTypeID int NOT NULL, --用户类型 0管理员 1学生 2老师
	PeopleImg image NULL, --用户头像
	AppBookNumber int NOT NULL, --正在申请购书的数量
	BroBookNumber int NOT NULL --正在借阅的数量
);

--学生信息表
use Library
go
create table StudentInfor
(
	StudentID int primary key identity(1,1) NOT NULL, --学生ID
	IDCards varchar(18) NOT NULL unique, --身份证号码
	StudentCreditID int NOT NULL, --学生信用ID
	Experience int NOT NULL --学生经验
);

--学生信用表
create table StudentCredit
(
	StudentCreditID int primary key NOT NULL, --学生信用ID
	BorCredit int NOT NULL, --可以借购买书的数量
	Credit int NOT NULL --可以申请的数的数量
);
-----------------------------
--初始化学生信用表
INSERT INTO StudentCredit(StudentCreditID, BorCredit, Credit) VALUES(1,4,2);
INSERT INTO StudentCredit(StudentCreditID, BorCredit, Credit) VALUES(2,6,4);
INSERT INTO StudentCredit(StudentCreditID, BorCredit, Credit) VALUES(3,8,6);
-----------------------------

--老师信息表
create table TeacherInfor
(
	TeacherID int primary key identity(1,1) NOT NULL, --老师ID
	IDCards varchar(18) NOT NULL unique, --身份证号码
	TeacherCreditID int NOT NULL, --老师信用ID
	Experience int NOT NULL --老师的经验
);

--老师信用表
create table TeacherCredit
(
	TeacherCreditID int primary key NOT NULL, --老师信用ID
	BorCredit int NOT NULL, --可以申请购买书的数量
	Credit int NOT NULL --可以借的书的数量
);
-----------------------------
--初始化老师信用表
INSERT INTO TeacherCredit(TeacherCreditID, BorCredit, Credit) VALUES(1,8,6);
INSERT INTO TeacherCredit(TeacherCreditID, BorCredit, Credit) VALUES(2,10,8);
INSERT INTO TeacherCredit(TeacherCreditID, BorCredit, Credit) VALUES(3,12,10);
-----------------------------

--管理员信息表
create table Admin
(
	AdminID int primary key identity(1,1) NOT NULL, --用户ID
	IDCards varchar(18) NOT NULL unique, --身份证号码
	PeopleIntro varchar(128) NULL --简介
);

--用户联系表
create table UserConnection
(
	UserConnectionID int primary key identity(1,1) NOT NULL, --用户信息表ID
	UserID int NOT NULL, --用户ID
	AdminID int NULL, --管理员ID
	StudentID int NULL, --学生ID
	TeacherID int NULL --老师ID
);

--图书分类表
create table BookClass
(
	ClassficationID int primary key, --图书分类ID
	ClassficationName varchar(128) --图书分类名
);
-------------------------------
--书籍分类表初始化
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(1,'玄幻');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(2,'奇幻');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(3,'武侠');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(4,'仙侠');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(5,'都市');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(6,'现实');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(7,'军事');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(8,'历史');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(9,'游戏');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(10,'体育');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(11,'科幻');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(12,'灵异');
-------------------------------


--书籍分类表
drop table BookInfor
create table BookInfor
(
	BookID int primary key identity(1,1) NOT NULL, --书的ID
	BookClassfincationID int NOT NULL, --书的分类
	BookName varchar(128) NOT NULL, --书的名称
	BookAuthor varchar(128) NOT NULL, --书的作者
	BookIntro varchar(512) NOT NULL, --书的简介
	WordNumber varchar(16) NOT NULL, --字数
	BookNumber int NOT NULL, --书的数量
	Status int NOT NULL --书的状态 0代表已购 1代表正在采购
);

--书籍申请表
create table ApplyBook
(
	UserID int NOT NULL, --申请人的ID
	BookClassfication int NOT NULL, --书籍类型
	BookName varchar(128) NOT NULL, --图书名称
	BookAuthor varchar(128) NOT NULL, --书籍的作者
	Apply date  NOT NULL --申请时间
);

--借阅信息表
create table BorrowInfor
(
	UserID int NOT NULL, --借阅人ID
	BookID int NOT NULL, --借阅的书ID
	Start date NOT NULL, --借阅的开始时间
	Finish date NOT NULL, --借阅的结束时间
	Overtime bit NOT NULL, --是否超时 0未超时 1超时
	primary key(UserID, BookID)
);
>>>>>>> 5bc953dcd5d37d8b9f47488c52b417960069ece2
