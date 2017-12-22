package com.Library.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.globle.Constant;

/**
 * 注销类
 * 实现注销功能
 */
@WebServlet("/LogoutAction")
public class LogoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute(Constant.USER_KEY) != null)
		{
			System.out.println("Will Delete Session: " + request.getSession().getAttribute(Constant.USER_KEY));
			request.getSession().removeAttribute(Constant.USER_KEY);
		}
		
		System.out.println("Will Go To: " + request.getContextPath() + "/default_cn.html");
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
}
