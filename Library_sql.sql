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

--������Ϣ��
use Library
go
create table UserInfor
(
	UserID int primary key identity(1,1) NOT NULL, --����ID
	PeopleName varchar(128) NOT NULL, --��������
	LoginName varchar(128) NULL, --��¼��
	Password varchar(256) NOT NULL, --��¼����
	Phone varchar(11) NULL unique, --�绰
	Email varchar(128) NULL unique, --Email
	Gender bit NULL, --�Ա� 0������ 1����Ů
	QQ varchar(128) NULL, --QQ����
	WeChat varchar(128) NULL, --΢�ź�
	Status int NOT NULL, --�˺�״̬ 0���� 1����
	UserTypeID int NOT NULL, --�û����� 0����Ա 1ѧ�� 2��ʦ
	PeopleImg image NULL, --�û�ͷ��
	AppBookNumber int NOT NULL, --�������빺�������
	BroBookNumber int NOT NULL --���ڽ��ĵ�����
);

--ѧ����Ϣ��
use Library
go
create table StudentInfor
(
	StudentID int primary key identity(1,1) NOT NULL, --ѧ��ID
	IDCards varchar(18) NOT NULL unique, --���֤����
	StudentCreditID int NOT NULL, --ѧ������ID
	Experience int NOT NULL --ѧ������
);

--ѧ�����ñ�
create table StudentCredit
(
	StudentCreditID int primary key NOT NULL, --ѧ������ID
	BorCredit int NOT NULL, --���Խ蹺���������
	Credit int NOT NULL --�����������������
);
-----------------------------
--��ʼ��ѧ�����ñ�
INSERT INTO StudentCredit(StudentCreditID, BorCredit, Credit) VALUES(1,4,2);
INSERT INTO StudentCredit(StudentCreditID, BorCredit, Credit) VALUES(2,6,4);
INSERT INTO StudentCredit(StudentCreditID, BorCredit, Credit) VALUES(3,8,6);
-----------------------------

--��ʦ��Ϣ��
create table TeacherInfor
(
	TeacherID int primary key identity(1,1) NOT NULL, --��ʦID
	IDCards varchar(18) NOT NULL unique, --���֤����
	TeacherCreditID int NOT NULL, --��ʦ����ID
	Experience int NOT NULL --��ʦ�ľ���
);

--��ʦ���ñ�
create table TeacherCredit
(
	TeacherCreditID int primary key NOT NULL, --��ʦ����ID
	BorCredit int NOT NULL, --�������빺���������
	Credit int NOT NULL --���Խ���������
);
-----------------------------
--��ʼ����ʦ���ñ�
INSERT INTO TeacherCredit(TeacherCreditID, BorCredit, Credit) VALUES(1,8,6);
INSERT INTO TeacherCredit(TeacherCreditID, BorCredit, Credit) VALUES(2,10,8);
INSERT INTO TeacherCredit(TeacherCreditID, BorCredit, Credit) VALUES(3,12,10);
-----------------------------

--����Ա��Ϣ��
create table Admin
(
	AdminID int primary key identity(1,1) NOT NULL, --�û�ID
	IDCards varchar(18) NOT NULL unique, --���֤����
	PeopleIntro varchar(128) NULL --���
);

--�û���ϵ��
create table UserConnection
(
	UserConnectionID int primary key identity(1,1) NOT NULL, --�û���Ϣ��ID
	UserID int NOT NULL, --�û�ID
	AdminID int NULL, --����ԱID
	StudentID int NULL, --ѧ��ID
	TeacherID int NULL --��ʦID
);

--ͼ������
create table BookClass
(
	ClassficationID int primary key, --ͼ�����ID
	ClassficationName varchar(128) --ͼ�������
);
-------------------------------
--�鼮������ʼ��
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(1,'����');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(2,'���');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(3,'����');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(4,'����');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(5,'����');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(6,'��ʵ');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(7,'����');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(8,'��ʷ');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(9,'��Ϸ');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(10,'����');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(11,'�ƻ�');
INSERT INTO BookClass(ClassficationID, ClassficationName) VALUES(12,'����');
-------------------------------


--�鼮�����
drop table BookInfor
create table BookInfor
(
	BookID int primary key identity(1,1) NOT NULL, --���ID
	BookClassfincationID int NOT NULL, --��ķ���
	BookName varchar(128) NOT NULL, --�������
	BookAuthor varchar(128) NOT NULL, --�������
	BookIntro varchar(512) NOT NULL, --��ļ��
	WordNumber varchar(16) NOT NULL, --����
	BookNumber int NOT NULL, --�������
	Status int NOT NULL --���״̬ 0�����ѹ� 1�������ڲɹ�
);

--�鼮�����
create table ApplyBook
(
	UserID int NOT NULL, --�����˵�ID
	BookClassfication int NOT NULL, --�鼮����
	BookName varchar(128) NOT NULL, --ͼ������
	BookAuthor varchar(128) NOT NULL, --�鼮������
	Apply date  NOT NULL --����ʱ��
);

--������Ϣ��
create table BorrowInfor
(
	UserID int NOT NULL, --������ID
	BookID int NOT NULL, --���ĵ���ID
	Start date NOT NULL, --���ĵĿ�ʼʱ��
	Finish date NOT NULL, --���ĵĽ���ʱ��
	Overtime bit NOT NULL, --�Ƿ�ʱ 0δ��ʱ 1��ʱ
	primary key(UserID, BookID)
);
>>>>>>> 5bc953dcd5d37d8b9f47488c52b417960069ece2
