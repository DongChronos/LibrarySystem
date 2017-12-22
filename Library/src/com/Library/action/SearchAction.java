package com.Library.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.bean.PageBean;
import com.Library.dao.BookDao;
import com.Library.dao.jdbc.BookDaoImpl;
import com.Library.entity.BookInfor;
import com.Library.globle.Constant;
import com.Library.service.PageService;
import com.Library.service.impl.PageServiceImpl;

/**
 * 书籍搜索类
 * 实现书籍搜索功能
 */
@WebServlet("/SearchAction")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int pageSize = 10; //每页显示10条数据
	private String bookType = null; //书籍的类型
	private String searchInfor = null; //用户的搜索内容
	private String author = null; //用户搜索的作者
	private BookDao bookDao = new BookDaoImpl(); //书籍的数据库操作
	private String pageNum = null; //第几页
	private List<BookInfor> bookInfors = new ArrayList<BookInfor>(); //书籍的结果集
	private BookInfor bookInfor = new BookInfor(); //单本数
	private PageService pageService = new PageServiceImpl(); //分页的操作
	private PageBean<BookInfor> pageBean = null; //分页信息存储
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookType = request.getParameter("bookType").trim();
		pageNum = request.getParameter("page").trim();
		searchInfor = request.getParameter("searchInfor").trim();
		author = request.getParameter("author").trim();
		
		//根据URL信息进行判断，以及请求
		this.searchInfor_bookType_page(request, response, searchInfor, bookType, pageNum, author);
		request.setAttribute(Constant.BOOK_INFOR, this.bookInfor);
		request.setAttribute(Constant.BOOK_INFORS, this.bookInfors);
		request.setAttribute(Constant.PAGE_BEAN, this.pageBean);
		System.out.println("获取书籍信息执行完毕");
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
	/**
	 * 根据URL具体参数，获取数据库内容
	 * @param request
	 * @param response
	 * @param searchInfor 用户搜索内容
	 * @param bookType 书籍类型
	 * @param page 请求的分页数
	 */
	private void searchInfor_bookType_page(HttpServletRequest request, HttpServletResponse response, String searchInfor, String bookType, String page, String author)
	{
		//如果搜索内容不为空，则针对搜索内容进行匹配
		if(!"".equals(searchInfor))
		{
			this.bookInfor = null;
			//如果通过作者进行检索，则直接匹配该作者的所有书籍
			if(!"".equals(author))
			{
				if(!"".equals(page))
				{
					this.pageBean = pageService.setBookAuthorPageBean(searchInfor, Integer.parseInt(page), pageSize);
				}
				else
				{
					this.pageBean = pageService.setBookAuthorPageBean(searchInfor, 1, pageSize);
				}
			}
			//如果进行全局检索，则先进行匹配到书名
			else
			{
				this.bookInfor = bookDao.getBookByName(searchInfor);
				//如果页数不为空,则查找相应的页数信息
				if(!"".equals(page))
				{
					this.pageBean = pageService.setBookItroPageBean(searchInfor, Integer.parseInt(page), pageSize);
				}
				//如果页数为空，则直接找第一页的信息
				else
				{					
					this.pageBean = pageService.setBookItroPageBean(searchInfor, 1, pageSize);
				}
			}
		}
		//如果搜索内容为空，则针对书籍类型以及，页面进行检索
		else
		{
			this.bookInfor = null;
			//如果书籍类型不为空，则针对书籍类型及其页数进行查找
			if(!"".equals(bookType))
			{
				//如果书籍页数不为空，则获取相应页数的内容
				if(!"".equals(page))
				{
					this.pageBean = pageService.setBookTypePageBean(Integer.parseInt(bookType), Integer.parseInt(page), pageSize);
				}
				//如果书籍页数为空，则获取第一页信息
				else
				{
					this.pageBean = pageService.setBookTypePageBean(Integer.parseInt(bookType), 1, pageSize);
				}
			}
			//如果书籍的类型为空，则获取所有的书籍信息
			else
			{
				//根据书籍的页数，进行获取相应的书籍信息
				if(!"".equals(page))
				{
					this.pageBean = pageService.setBasicPageBean(Integer.parseInt(page), pageSize);
				}
				else
				{
					this.pageBean = pageService.setBasicPageBean(1, pageSize);
				}
			}
		}
	}
}
