package com.Library.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.dao.BookDao;
import com.Library.dao.jdbc.BookDaoImpl;
import com.Library.entity.BookInfor;
import com.Library.globle.Constant;


/**
 * 书籍页面具体信息类
 * 获取书籍的具体信息
 */
@WebServlet("/BookDetailAction")
public class BookDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String bookID = null; //书籍的唯一ID
	private BookInfor bookInfor = null; //存储需要详细显示的书籍信息
	private BookDao bookDao = new BookDaoImpl(); //书籍数据库搜索
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailAction() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.bookID = request.getParameter("bookID").trim();
		if(!"".equals(this.bookID))
		{
			try
			{
				this.bookInfor = bookDao.getBookByID(Integer.parseInt(bookID));
			}
			catch(Exception e)
			{
				System.out.println("输入的bookID有错误");
				this.bookInfor = null;
			}
			
			request.setAttribute(Constant.BOOK_INFOR, this.bookInfor);
			request.getRequestDispatcher("/book_details.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
