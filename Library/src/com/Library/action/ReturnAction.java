package com.Library.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.bean.PageBean;
import com.Library.dao.SearchBookDao;
import com.Library.dao.jdbc.SearchBookDaoImpl;
import com.Library.entity.BorrowInfor;
import com.Library.globle.Constant;
import com.Library.service.BorrowReturnService;
import com.Library.service.PageService;
import com.Library.service.impl.BorrowReturnServiceImpl;
import com.Library.service.impl.PageServiceImpl;

/**
 * 还书控制类
 * 实现还书功能
 */
@WebServlet("/returnAction")
public class ReturnAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String pageNum = null; //当前页面
	private int pageSize = 10; //页面显示信息的最大值
	private String Phone = null; //管理输入的电话号码
	private String actionType = null; //管理员的行为  search为查找用户 return为还书
	private PageBean<BorrowInfor> bipageBean = null; //借阅信息
	private PageService pageService = new PageServiceImpl(); //分页操作
	private BorrowReturnService borrowReturnService = new BorrowReturnServiceImpl(); //还书操作
	private SearchBookDao searchBookDao = new SearchBookDaoImpl(); //搜索借书信息的操作
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.actionType = request.getParameter("actionType").trim();
		this.pageNum = request.getParameter("page").trim();
		if(!"".equals(this.actionType))
		{
			this.Phone = request.getParameter("Phone").trim();
			if("search".equals(this.actionType))
			{
				this.forward(request, response, Phone, false, this.pageNum);
				request.setAttribute(Constant.ACCOUNT_BORROW, this.bipageBean);
				request.getRequestDispatcher("/return.jsp").forward(request, response);
				return;
			}
			else if("return".equals(this.actionType))
			{
				this.forward(request, response, Phone, true, this.pageNum);
				response.sendRedirect(request.getContextPath() + "/return.jhtml?Phone=" + this.Phone + "&actionType=search&page=");
				return;
			}
		}
		else
		{
			request.getRequestDispatcher("/return.jsp").forward(request, response);
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
	 * 页面的检索以及还书操作
	 * @param request
	 * @param response
	 * @param Phone 电话号码
	 * @param flag false为search行为， true为return行为
	 */
	private void forward(HttpServletRequest request, HttpServletResponse response, String Phone, boolean flag, String page)
	{
		//return操作
		if(flag)
		{
			String bookID = request.getParameter("bookID").trim();
			BorrowInfor borrowInfor = searchBookDao.getBorrowInforByPhoneAndBookID(Phone, bookID);
			System.out.println("borrorInfor = " + borrowInfor);
			borrowReturnService.ReturnBook(borrowInfor);
		}
		//search操作
		else
		{
			if(!"".equals(page))
			{
				this.bipageBean = this.pageService.setBorrowInforOfPhone(Phone, Integer.parseInt(page), pageSize);
			}
			else
			{
				this.bipageBean = this.pageService.setBorrowInforOfPhone(Phone, 1, pageSize);
			}
		}
	}
}
