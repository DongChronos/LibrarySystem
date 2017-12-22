package com.Library.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.dao.UserDao;
import com.Library.dao.jdbc.UserDaoImpl;
import com.Library.entity.Admin;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserInfor;
import com.Library.entity.Userfactory;
import com.Library.globle.Constant;
import com.Library.service.UserService;
import com.Library.service.impl.UserSerciveImpl;
import com.Library.utils.Utils;

/**
 * 注册类
 * 实现注册功能
 */
@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String CREATE_SUCCESS_URL = Constant.CREATE_ACCOUNT_SUCCESS;
	
	private String RealName = null; //真实姓名
	private String LoginName = null; //登录名
	private String Password = null; //密码
	private boolean Gender = false; //性别
	private int userType = -1; //用户类型
	private String Phone = null; //电话号码
	private String Email = null; //电子邮箱
	private String QQ = null; //QQ帐号
	private String WeChat = null; //微信帐号
	private String IDcards = null; //身份证
	private UserInfor userInfor = new UserInfor(); //用户基本信息
	private UserDao userDao = new UserDaoImpl(); //数据库操作
	private UserService userService = new UserSerciveImpl(); //用户操作
       

	/**
	 * 用户注册组件
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行RegisterAction方法");
		//获取注册信息
		this.catchUserInfor(request, response);
		
		//创建对象保存信息
		boolean flag = false;
		try
		{
			if(Utils.isIDCards(this.IDcards))
			{
				if(Utils.isEmail(this.Email) && Utils.isPhone(this.Phone))
				{
					System.out.println("电话和者邮箱正确");
					if(this.isUniqueIDCards(this.IDcards))
					{
						if(this.isUserEcisted(this.Email, this.userType)&& this.isUserEcisted(this.Phone, this.userType) && this.userType != -1)
						{
							//保存基本信息
							this.saveUserInfor();
							Userfactory userFactory = new Userfactory();
							Object object = userFactory.getUser(userInfor);
							
							//设置独有信息
							object = this.setUniqueInfor(object);
							userService.registerUser(object);
							System.out.println("注册成功");
							flag = true;
						}
						else
						{
							request.setAttribute(Constant.ERROR, "用户已经存在或注册类型不对");
							System.out.println("用户已经存在或注册类型不对");
							flag = false;
						}
					}
					else
					{
						request.setAttribute(Constant.ERROR, "该身份证已经注册过");
						System.out.println("该身份证已经注册过");
						flag = false;
					}
				}
				else
				{
					request.setAttribute(Constant.ERROR, "邮箱或手机不符合要求");
					System.out.println("邮箱或手机不符合要求");
					flag = false;
				}
			}
			else
			{
				request.setAttribute(Constant.ERROR, "身份证不符合要求");
				System.out.println("用户身份证不符合要求");
				flag = false;
			}
			if(flag)
			{
				response.sendRedirect(request.getContextPath() + this.CREATE_SUCCESS_URL);
			}
			else
			{
				this.forward(request, response);
			}
		}
		catch(Exception e)
		{
			System.out.println("注册信息保存出现错误");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 获取注册信息
	 * @param request
	 * @param response
	 */
	private void catchUserInfor(HttpServletRequest request, HttpServletResponse response)
	{
		this.RealName = request.getParameter("Realname").trim();
		this.LoginName = request.getParameter("loginName").trim();
		this.Password = request.getParameter("Password").trim();
		this.Phone = request.getParameter("Phone").trim();
		this.Email = request.getParameter("Email").trim();
		this.QQ = request.getParameter("QQ").trim();
		this.WeChat = request.getParameter("WeChat").trim();
		this.IDcards = request.getParameter("IDCards").trim();
		if(request.getParameter("gender").equals("0"))
		{
			this.Gender = false;
		}
		else
		{
			this.Gender = true;
		}
		
		if(request.getParameter("UserType").equals("1"))
		{
			this.userType = 1;
		}
		else
		{
			this.userType = 2;
		}
	}
	
	/**
	 * 判断用户是否已经存在
	 * @param condition
	 * @param userType
	 * @return
	 */
	private boolean isUserEcisted(String condition, int userType)
	{
		Object object = null;
		if(userType == 0)
		{
			//管理员
			object = this.userDao.getUserByCondition(condition, userType);
		}
		else if(userType == 1)
		{
			//学生
			object = this.userDao.getUserByCondition(condition, userType);
		}
		else if(userType == 2)
		{
			//老师
			object = this.userDao.getUserByCondition(condition, userType);
		}
		return object == null;
	}
	
	/**
	 * 判断是否已存在IDcards
	 * @param condition
	 * @return
	 */
	private boolean isUniqueIDCards(String condition)
	{
		Object object = null;
		object = this.userDao.getUserByIDCards(condition);
		return object == null;
	}
	/**
	 * 设置独有信息
	 * @param object
	 * @return
	 */
	private Object setUniqueInfor(Object object)
	{
		if(object instanceof Admin)
		{
			((Admin) object).setIDCards(this.IDcards);
		}
		else if(object instanceof Student)
		{
			((Student) object).setIDCards(this.IDcards);
			((Student) object).setStudentCredit(1);
		}
		else if(object instanceof Teacher)
		{
			((Teacher) object).setIDCards(this.IDcards);
			((Teacher) object).setTeacherCredit(1);
		}
		return object;
	}
	
	/**
	 * 保存基本信息
	 */
	private void saveUserInfor()
	{
		if(this.LoginName != null && "".equals(this.LoginName) == false)
		{
			userInfor.setLoginName(this.LoginName);
		}
		if(this.RealName != null && "".equals(this.RealName) == false)
		{
			userInfor.setPeopleName(this.RealName);
		}
		if(this.Password != null && "".equals(this.Password) == false)
		{
			userInfor.setPassword(Utils.toMD5(this.Password));
		}
		if(this.Phone != null && "".equals(this.Phone) == false)
		{
			userInfor.setPhone(this.Phone);
		}
		if(this.Email != null && "".equals(this.Email) == false)
		{
			userInfor.setEmail(this.Email);
		}
		if(this.QQ != null && "".equals(this.QQ) == false)
		{
			userInfor.setQQ(this.QQ);
		}
		if(this.WeChat != null && "".equals(this.WeChat) == false)
		{
			userInfor.setWeChat(this.WeChat);
		}
		userInfor.setGender(this.Gender);
		userInfor.setUserTypeID(this.userType);	
		userInfor.setStatus(1);
	}
	
	private void forward(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}
}
