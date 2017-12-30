package com.Library.action;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private String bookAuthor = null; //书籍的作者
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
		this.bookAuthor = request.getParameter("bookAuthor").trim();
		this.userID = Utils.getUserInfor(object).getUserID();
		this.date = Utils.CalendarToDate(Calendar.getInstance());
		
		if(this.isExist(userID ,bookName))
		{
			if(object instanceof Student)
			{
				this.applyNumber = ((Student) object).getCredit();
			}
			else
			{
				this.applyNumber = ((Teacher) object).getCredit();
			}
			if(Utils.getUserInfor(object).getAppBookNumber() < this.applyNumber)
			{
				applyBookDao.InsertApply(userID, bookName, bookAuthor, Integer.parseInt(bookClassfication), date, Utils.getUserInfor(object).getAppBookNumber());
				request.setAttribute(Constant.APPLY_MESSAGE, "申请成功");
				this.saveSessionInfor(request,object);
				request.getRequestDispatcher("/apply.jsp").forward(request, response);
				return;
			}
			else
			{
				request.setAttribute(Constant.APPLY_MESSAGE, "申请失败，已经超过你可以申请的数量");
				request.getRequestDispatcher("/apply.jsp").forward(request, response);
			}
		}
		else
		{
			request.setAttribute(Constant.APPLY_MESSAGE, "该书已经申请过");
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
	
	/**
	 * 申请书籍完成对session中的user对象进行更新
	 * @param request
	 * @param object 用户对象
	 */
	private void saveSessionInfor(HttpServletRequest request,Object object)
	{
		HttpSession session = request.getSession();
		if(object instanceof Student)
		{
			((Student) object).getUserInfor().setAppBookNumber(Utils.getUserInfor(object).getAppBookNumber()+1);
			session.setAttribute(Constant.USER_KEY, (Student) object);
		}
		else
		{
			((Teacher) object).getUserInfor().setAppBookNumber(Utils.getUserInfor(object).getAppBookNumber()+1);
			session.setAttribute(Constant.USER_KEY, (Teacher) object);
		}
	}

	/**
	 * 该用户是否已经申请过该书
	 * @param bookName
	 * @return
	 */
	private boolean isExist(int userID, String bookName)
	{
		Object object = null;
		object = this.applyBookDao.getApplyInforByUserIDAndBookName(userID, bookName);
		return object == null;
	}
}
