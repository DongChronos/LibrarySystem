# LibrarySystem(图书管理系统)
 该系统实现了一些基本的图书借阅管理系统
 普通用户(学生、老师):实现了查找(模糊查找、精确查找、通过作者查找)、借阅、申请增加新的书籍、登录、注销、注册、还书需要管理进行
 管理员:实现了管理借书信息(全局、用户姓名、按照月份、按照书籍类型)、还书(通过手机号码)、每次管理员登录将对借阅信息进行检索是否存在超时行为，若存在则将超 时信息栏置为True，如果超时30天就冻结账户
 
 The system implements some basic library lending management systems
 Ordinary users (students, teachers):implements search(Fuzzy Lookup,accurate lookup,author lookup), borrow book, apply new book, sign in, sign out, register, return via admin
 Admin: implements admin borrowed Information(global, realname, according to month, according to book classfication), return(via phone number), whether there is overtime behavior for searching the borrowed information each time where the admin login, if there is timeout behavior of borrowed information, the column  of 'Overtime' will be set 'True', if the account over 30 days, the account is frozen
 
 
## DatabaseLink(数据库连接)
 在jdbc.properties 写入数据库的信息即可连接
 if want to link the database you will input the database information to the jdbc.properties
