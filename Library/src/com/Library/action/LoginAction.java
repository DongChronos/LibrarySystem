package com.Library.action;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Library.bean.LoginInfor;
import com.Library.dao.BorrowReturnDao;
import com.Library.dao.SearchBookDao;
import com.Library.dao.UserDao;
import com.Library.dao.jdbc.BorrowReturnDaoImpl;
import com.Library.dao.jdbc.SearchBookDaoImpl;
import com.Library.dao.jdbc.UserDaoImpl;
import com.Library.entity.Admin;
import com.Library.entity.BorrowInfor;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserInfor;
import com.Library.globle.Constant;
import com.Library.service.UserService;
import com.Library.service.impl.UserSerciveImpl;
import com.Library.utils.Utils;

/**
 * 登录类
 * 实现登录功能
 * 并且以管理登录时 将对借阅信息进行更新以及帐号进行管理
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private static String OK_URL; //登录成功的URL
	private static String AD_URL; //管理员登录之后的URL
	private String username = null; //登录名
	private String password = null; //登录密码
	private String codeNumbers = null; //验证码
	private int userType = -1;
	private UserService userService = new UserSerciveImpl(); //用户服务
	private UserDao userDao = new UserDaoImpl(); //数据库操作
	
    public LoginAction() {
        super();
    }
    
    public void init() throws ServletException{
    	super.init();
    	OK_URL = super.getServletContext().getInitParameter("ok_url");
    	AD_URL = super.getServletContext().getInitParameter("ad_url");
    	System.out.println("OK_URL: "+ OK_URL);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String initType = request.getParameter("type").trim();
		if(!"".equals(initType) && "login".equals(initType))
		{
			this.username = request.getParameter("loginName").trim();
			this.password = request.getParameter("password").trim();
			this.codeNumbers = request.getParameter("checkcode").trim();
			System.out.println("PassWORD = " + Utils.toMD5(this.password));
			if("".equals(request.getParameter("UserType").trim()))
			{
				System.out.println("没有选择类型");
				request.setAttribute(Constant.ERROR, "没有选择类型");
				this.forword(request,response,true,null);
				return;
			}
			this.userType = Integer.parseInt(request.getParameter("UserType").trim());
			
			//如果用户名或者密码或者验证码为空则跳转为登录页面
			if(this.username == null ||"".equals(this.username)
					||this.password == null ||"".equals(this.password)
					||this.codeNumbers == null ||"".equals(this.codeNumbers))
			{
				System.out.println("用户名或者密码或者验证码为空");
				request.setAttribute(Constant.ERROR, "用户名或者密码或者验证码为空");
				this.forword(request,response,true,null);
				return;
			}
			
			//session域中拿到当前争取的验证码
			String rightCode = (String) request.getSession().getAttribute(Constant.CHECK_NUMBER_NAME);
			System.out.println(rightCode+" "+codeNumbers);
			
			//验证验证码
			if(!userService.chechCode(rightCode, codeNumbers))
			{
				request.setAttribute(Constant.ERROR, "验证码错误");
				System.out.println("验证码不正确");
				forword(request, response, true, null);
				return;
			}
			
			
			
			//通过用户名查找用户对象
			//这里的用户名为email或者phone
			Object object = userDao.getUserByCondition(username, userType);			
			if(object == null)
			{
				request.setAttribute(Constant.ERROR, "尚未注册！");
				System.out.println("尚未注册！");
				forword(request, response, true, null);
				return;
			}
			
			//获取公共信息
			UserInfor userInfor = Utils.getUserInfor(object);
			
			//判断用户类型是否正确
			if(userInfor.getUserTypeID() != userType)
			{
				request.setAttribute(Constant.ERROR, "用户类型错误!");
				System.out.println("用户类型错误！");
				forword(request, response, true, null);
				return;
			}
			
			//判断帐号是否被冻结
			if(object != null && Utils.getUserInfor(object).getStatus() != 1)
			{
				request.setAttribute(Constant.ERROR, "账号已经被冻结！");
				System.out.println("账号已经被冻结！");
				forword(request, response, true, null);
				return;
			}
			
			//验证用户名和密码
			if(object != null && !userInfor.getPassword().equals(Utils.toMD5(this.password)))
			{
				request.setAttribute(Constant.ERROR, "用户名或密码错误！");
				System.out.println("用户名或密码错误！");
				forword(request, response, true, null);
				return;
			}
			System.out.println("执行完成");
			
			this.forword(request, response, false, object);
		}
		else
		{
			//禁止url任意传参
			forword(request, response, true, null);
			System.out.println("the type is null");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 对登录用户进行跳转操作
	 * @param request
	 * @param response
	 * @param isError
	 * @param object
	 * @throws IOException
	 * @throws ServletException 
	 */
	private void forword(HttpServletRequest request,HttpServletResponse response,boolean isError,Object object) throws IOException, ServletException
	{
		if(object != null && !isError)
		{
			//保存用户信息，访问记录，登录信息
			System.out.println("登录成功，保存信息");
			this.saveUserInfor(request,object);
		}
		
		if(isError)
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		else
		{
			if(this.userType == 0)
			{
				System.out.println("Login Success"+AD_URL);
				this.OvertimeAndFrozen();
				response.sendRedirect(request.getContextPath() + AD_URL);
			}
			else
			{
				System.out.println("Login Success"+OK_URL);
				response.sendRedirect(request.getContextPath() + OK_URL);
			}
		}
	}
	
	/**
	 * 保存用户登录信息,并且存储到session中
	 * @param request
	 * @param object
	 */
	private void saveUserInfor(HttpServletRequest request,Object object)
	{
		//重新开启session，方便计算用户登录时间
		request.getSession().invalidate();
		HttpSession session = request.getSession();
		
		LoginInfor loginInfor = new LoginInfor();
		loginInfor.setIp(request.getRemoteAddr());
		loginInfor.setLoginName(Utils.getUserInfor(object).getLoginName());
		loginInfor.setUserID(Utils.getUserInfor(object).getUserID());
		loginInfor.setLoginTime(new Date());
		session.setAttribute("loginInfor", loginInfor);
		
		//把用户状态存入session中
		if(object instanceof Admin)
		{
			session.setAttribute(Constant.USER_TYPE, 0);
			session.setAttribute(Constant.USER_KEY, (Admin) object);
		}
		else if (object instanceof Student)
		{
			session.setAttribute(Constant.USER_TYPE, 1);
			session.setAttribute(Constant.USER_KEY, (Student) object);
		}
		else if(object instanceof Teacher)
		{
			session.setAttribute(Constant.USER_TYPE, 2);
			session.setAttribute(Constant.USER_KEY, (Teacher) object);
		}
	}
	
	/**
	 * 进行判断用户是否会超时或者冻结
	 */
	private void OvertimeAndFrozen()
	{
		//获取当前时间
		Date nowDate = Utils.CalendarToDate(Calendar.getInstance());
		
		SearchBookDao searchBookDao = new SearchBookDaoImpl();
		List<BorrowInfor> borrowInfors = searchBookDao.getAlltheBorrowInfor();
		for(BorrowInfor borrowInfor:borrowInfors)
		{			
			//超时
			if(borrowInfor.getFinish().before(nowDate))
			{
				BorrowReturnDao borrowReturnDao = new BorrowReturnDaoImpl();
				borrowReturnDao.update(borrowInfor);
				if(Utils.FrozenDate(borrowInfor.getFinish()))
				{
					UserDao userDao = new UserDaoImpl();
					userDao.setFrozenAccount(borrowInfor);
				}
				System.out.println("True");
			}
			//没有超时
			else
			{
				System.out.println("False");
			}
		}
	}
}
