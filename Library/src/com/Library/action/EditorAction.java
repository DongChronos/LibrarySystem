package com.Library.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.entity.Admin;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserInfor;
import com.Library.globle.Constant;
import com.Library.service.UserService;
import com.Library.service.impl.UserSerciveImpl;
import com.Library.utils.Utils;

/**
 * 修改信息类
 * 实现修改信息功能
 */
@WebServlet("/EditorAction")
public class EditorAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String LoginName = null; //登录名
	private String QQ = null; //QQ帐号
	private String WeChat = null; //微信帐号
	private Object object = null; //用户详细信息
	private UserInfor userInfor = null; //用户基本
	private UserService userService = new UserSerciveImpl(); //用户操作
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorAction() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//执行EditorAction方法
		System.out.println("EditorAction");
		
		//获取修改的数据
		this.catchUserInfor(request, response);
		
		boolean flag = false;
		try
		{
			this.object = request.getSession().getAttribute(Constant.USER_KEY);
			this.setInfor(this.object);
			userService.UpdateUser(this.object);
			flag = true;
		}
		catch(Exception e)
		{
			flag = false;
			System.out.println("修改信息发生错误");
			forward(request, response);
			e.printStackTrace();
		}
		if(flag)
		{
			if(this.object instanceof Admin)
			{
				request.getSession().setAttribute(Constant.USER_KEY, (Admin) object);
				this.forward(request, response);
				return;
			}
			else if(this.object instanceof Student)
			{
				request.getSession().setAttribute(Constant.USER_KEY, (Student) object);
				this.forward(request, response);
				return;
			}
			else
			{
				request.getSession().setAttribute(Constant.USER_KEY, (Teacher) object);
				this.forward(request, response);
				return;
			}
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
		this.LoginName = request.getParameter("loginName").trim();
		this.QQ = request.getParameter("QQ").trim();
		this.WeChat = request.getParameter("WeChat").trim();
		if(this.LoginName.equals(""))
		{
			this.LoginName = Utils.getUserInfor(request.getSession().getAttribute(Constant.USER_KEY)).getLoginName();
		}
		if(this.QQ.equals(""))
		{
			this.QQ = Utils.getUserInfor(request.getSession().getAttribute(Constant.USER_KEY)).getQQ();
		}
		if(this.WeChat.equals(""))
		{
			this.WeChat = Utils.getUserInfor(request.getSession().getAttribute(Constant.USER_KEY)).getWeChat();
		}
		
		userInfor = Utils.getUserInfor(request.getSession().getAttribute(Constant.USER_KEY));
		
		userInfor.setLoginName(this.LoginName);
		userInfor.setQQ(this.QQ);
		userInfor.setWeChat(this.WeChat);
	}
	
	/**
	 * 将修改的信息写入到用户的基本信息中
	 * @param object
	 */
	private void setInfor(Object object)
	{
		if(object instanceof Admin)
		{
			((Admin) object).setUserInfor(this.userInfor);
		}
		else if(object instanceof Student)
		{
			((Student) object).setUserInfor(this.userInfor);
		}
		else
		{
			((Teacher) object).setUserInfor(this.userInfor);
		}
	}
	
	/**
	 * 页面跳转
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	private void forward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		response.sendRedirect(request.getContextPath() + "/infor.jsp");
		//request.getRequestDispatcher("infor.jsp").forward(request, response);
	}
}
