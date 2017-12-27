package com.Library.action;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.dao.ApplyBookDao;
import com.Library.dao.jdbc.ApplyBookDaoImpl;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.globle.Constant;
import com.Library.utils.Utils;

/**
 * 申请借书类
 */
@WebServlet("/ApplyAction")
public class ApplyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String bookName = null; //书的名字
	private String bookClassfication = null; //书的类型
	private int userID = 0; //用户ID
	private Date date = null; //当前日期
	private ApplyBookDao applyBookDao = new ApplyBookDaoImpl(); //书籍数据库操作
	private Object object = null; //用户对象
	private  int applyNumber = 0; //可以申请的数量
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.object = request.getSession().getAttribute(Constant.USER_KEY);
		this.bookName = request.getParameter("bookName");
		this.bookClassfication = request.getParameter("bookType");
		this.userID = Utils.getUserInfor(object).getUserID();
		this.date = Utils.CalendarToDate(Calendar.getInstance());
		
		if(object instanceof Student)
		{
			this.applyNumber = ((Student) object).getBorCredit();
		}
		else
		{
			this.applyNumber = ((Teacher) object).getBorCredit();
		}
		if(Utils.getUserInfor(object).getAppBookNumber() < this.applyNumber)
		{
			applyBookDao.InsertApply(userID, bookName, Integer.parseInt(bookClassfication), date, Utils.getUserInfor(object).getAppBookNumber());
			request.setAttribute(Constant.APPLY_MESSAGE, "申请成功");
			request.getRequestDispatcher("/apply.jsp").forward(request, response);
			return;
		}
		else
		{
			request.setAttribute(Constant.APPLY_MESSAGE, "申请失败，已经超过你可以申请的数量");
			request.getRequestDispatcher("/apply.jsp").forward(request, response);
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
