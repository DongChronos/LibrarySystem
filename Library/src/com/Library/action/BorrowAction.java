package com.Library.action;

import java.io.IOException;
import java.util.Calendar;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.dao.BookDao;
import com.Library.dao.jdbc.BookDaoImpl;
import com.Library.entity.BookInfor;
import com.Library.entity.BorrowInfor;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserInfor;
import com.Library.globle.Constant;
import com.Library.service.BorrowReturnService;
import com.Library.service.impl.BorrowReturnServiceImpl;
import com.Library.utils.Utils;

/**
 * 借书控制类
 * 实现借书功能
 */
@WebServlet("/borrowAction")
public class BorrowAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int bookID = 0; //书籍ID
	private UserInfor userInfor = null; //用户基本信息
	private BookInfor bookInfor = null; //书籍信息
	private Object object = null; //用户对象
	private BorrowReturnService borRutSer = new BorrowReturnServiceImpl(); //借书操作
	private BookDao bookDao = new BookDaoImpl(); //查询书籍信息数据库操作
	private Date Start = null; //当前时间(借书时间)
	private Date Finish = null; //还书时间
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.bookID = Integer.parseInt(request.getParameter("bookID").trim());
		this.object = request.getSession().getAttribute(Constant.USER_KEY);
		this.bookInfor = bookDao.getBookByID(bookID);
		this.userInfor = Utils.getUserInfor(object);
		this.Start = Utils.CalendarToDate(Calendar.getInstance());
		this.Finish = Utils.CalenderToDatePlus(Calendar.getInstance());
		this.forward(request, response, bookInfor, object, userInfor);
		request.getRequestDispatcher("/aftBor.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, BookInfor bookInfor, Object object, UserInfor userInfor) throws ServletException, IOException
	{
		if(object instanceof Student)
		{
			if(Utils.checkCredit(((Student) object).getUserInfor().getBroBookNumber(), ((Student) object).getCredit()))
			{
				BorrowInfor borrowInfor = new BorrowInfor(userInfor, bookInfor);
				borrowInfor.setBookID(borrowInfor.getBookInfor().getBookID());
				borrowInfor.setUserID(borrowInfor.getUserInfor().getUserID());
				borrowInfor.setStart(this.Start);
				borrowInfor.setFinish(this.Finish);
				borrowInfor.setOvertime(false);
				this.borRutSer.BorrowBook(borrowInfor);
				((Student) this.object).getUserInfor().setBroBookNumber((((Student) object).getUserInfor().getBroBookNumber() + 1));
				request.getSession().setAttribute(Constant.USER_KEY, (Student) this.object);
				request.setAttribute(Constant.BOOK_MESSAGE, "借阅成功");
			}
			else
			{
				request.setAttribute(Constant.BOOK_MESSAGE, "无法借阅，已经超过你的最大借书量");
			}
		}
		else if(object instanceof Teacher)
		{
			if(Utils.checkCredit(((Teacher) object).getUserInfor().getBroBookNumber(), ((Teacher) object).getCredit()))
			{
				BorrowInfor borrowInfor = new BorrowInfor(userInfor, bookInfor);
				borrowInfor.setBookID(borrowInfor.getBookInfor().getBookID());
				borrowInfor.setUserID(borrowInfor.getUserInfor().getUserID());
				borrowInfor.setStart(this.Start);
				borrowInfor.setFinish(this.Finish);
				borrowInfor.setOvertime(false);
				this.borRutSer.BorrowBook(borrowInfor);
				((Teacher) this.object).getUserInfor().setBroBookNumber((((Teacher) object).getUserInfor().getBroBookNumber() + 1));
				request.getSession().setAttribute(Constant.USER_KEY, (Teacher) this.object);
				request.setAttribute(Constant.BOOK_MESSAGE, "借阅成功");
			}
			else
			{
				request.setAttribute(Constant.BOOK_MESSAGE, "无法借阅，已经超过你的最大借书量");
			}
		}
	}
}
