package com.Library.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.dao.ApplyBookDao;
import com.Library.dao.jdbc.ApplyBookDaoImpl;
import com.Library.globle.Constant;
import com.Library.utils.Utils;

/**
 * Servlet implementation class DeleteApplyAction
 */
@WebServlet("/DeleteApplyAction")
public class DeleteApplyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String userID = null; //用户的ID
	private String bookName = null; //申请的书籍名称
	private Object object = null; //用户对象
	private ApplyBookDao applyBookDao = new ApplyBookDaoImpl(); //申请书籍的数据库操作
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteApplyAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.object = request.getSession().getAttribute(Constant.USER_KEY);
		this.userID = request.getParameter("userID").trim();
		this.bookName = request.getParameter("bookName").trim();
		this.applyBookDao.deleteApply(Integer.parseInt(userID), bookName, Utils.getUserInfor(object).getAppBookNumber());
		request.setAttribute(Constant.APPLY_MESSAGE, "取消申请成功");
		request.getRequestDispatcher("/apply.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
