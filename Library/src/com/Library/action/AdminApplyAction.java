package com.Library.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.dao.ApplyBookDao;
import com.Library.dao.jdbc.ApplyBookDaoImpl;
import com.Library.entity.ApplyInfor;
import com.Library.globle.Constant;

/**
 * 管理员对申请书籍的操作
 */
@WebServlet("/AdminApplyAction")
public class AdminApplyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String actionType = null; //操作类型
	private ApplyBookDao applyBookDao = new ApplyBookDaoImpl(); //申请数据库操作
	private List<ApplyInfor> applyInfors = null; //申请信息存储
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminApplyAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.actionType = request.getParameter("actionType").trim();
		if("".equals(this.actionType) || "show".equals(this.actionType))
		{
			this.applyInfors = this.applyBookDao.getAllApplyInfor();
			request.setAttribute(Constant.APPLY_LIST, this.applyInfors);
			request.getRequestDispatcher("/admin_apply.jsp").forward(request, response);
			return;
		}
		else if("search".equals(this.actionType))
		{
			String bookName = request.getParameter("bookName").trim();
			this.applyInfors = this.applyBookDao.getApplyInforByBookName(bookName);
			request.setAttribute(Constant.APPLY_LIST, this.applyInfors);
			request.getRequestDispatcher("/admin_apply.jsp").forward(request, response);
			return;
		}
		else if("insert".equals(this.actionType))
		{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
