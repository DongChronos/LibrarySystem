package com.Library.service.impl;

import com.Library.dao.UserDao;
import com.Library.dao.jdbc.UserDaoImpl;
import com.Library.entity.Admin;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserInfor;
import com.Library.service.UserService;

public class UserSerciveImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl(); //数据库操作

	@Override
	public boolean chechCode(String rightCode, String code) {
		boolean flag = false;
		try
		{
			//这里的i条件仅判断正确的验证码是否为空，用户输入的验证码是否为空由JS判断
			if((rightCode != null) || !"".equals(rightCode))
			{
				rightCode = rightCode.toUpperCase();
				code = code.toUpperCase();
				//如果正确，则返回true
				if(rightCode.equals(code))
				{
					flag = true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("验证码验证发生错误");
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean checkEmailAndPhone(String email, String phone, Object object) {
		boolean flag = false;
		UserInfor userInfor = null;
		try
		{
			boolean isPhone = false, isEmail = false;
			if(object instanceof Admin)
			{
				Admin admin = (Admin) object;
				userInfor = admin.getUserInfor();
			}
			else if(object instanceof Student)
			{
				Student student = (Student) object;
				userInfor = student.getUserInfor();
			}
			else if(object instanceof Teacher)
			{
				Teacher teacher = (Teacher) object;
				userInfor = teacher.getUserInfor();
			}
			System.out.println(userInfor.getPhone());
			
			//判断手机号是否跟原来相同
			if(!phone.equals(userInfor.getPhone()))
			{
				//并且在数据库中没有重复
				if(userDao.getUserByCondition(phone, userInfor.getUserTypeID()) == null)
				{
					isPhone = true;
				}
			}
			if(!email.equals(userInfor.getEmail()))
			{
				if(userDao.getUserByCondition(email, userInfor.getUserTypeID()) == null)
				{
					isEmail = true;
				}
			}
			if(isEmail && isPhone)
			{
				flag = true;
			}
		}
		catch(Exception e)
		{
			System.out.println("检测邮箱跟手机出现错误");
		}
		return flag;
	}

	@Override
	public boolean registerUser(Object object) {
		boolean flag = false;
		try
		{
			userDao.save(object);
			flag = true;
		}
		catch(Exception e)
		{
			System.out.println("在UserServiceImpl中注册失败");
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public boolean UpdateUser(Object object)
	{
		boolean flag = false;
		try
		{
			userDao.update(object);
			flag = true;
		}
		catch(Exception e)
		{
			System.out.println("在UserServiceImpl中跟新失败");
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
