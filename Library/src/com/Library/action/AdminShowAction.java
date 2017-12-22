package com.Library.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.bean.PageBean;
import com.Library.entity.BorrowInfor;
import com.Library.entity.UserInfor;
import com.Library.globle.Constant;
import com.Library.service.PageService;
import com.Library.service.impl.PageServiceImpl;

/**
 * 后台显示类
 * 后台显示的页面
 * 1.被借阅图书的显示
 * 2.借阅人的查阅
 * 3.User-Book之间的借阅关系的查阅，包括是否超时
 * 
 * AdminShow class
 * 1. show the infor of borrowing
 * 2. display the borrowing infor by User
 * 3. show the infor about the relation of User and Book  (including isOvertime)
 */
@WebServlet("/AdminShowAction")
public class AdminShowAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String condition = null; //获取管理员输入的信息 the infor of lookup
	private int pageSize = 10; //每个页面免显示10条信息 Each page displays 10 information
	private String page = null; //当前页面数 Current page number
    private String index = null; //管理的类型 the type of admin
    private PageBean<BorrowInfor> pageBean = null; //分页信息存储(借书信息)
    private PageBean<UserInfor> upageBean = null; //分页信息存储(冻结用户信息)
    private PageService pageService = new PageServiceImpl(); //分页服务操作
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminShowAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.index = request.getParameter("index").trim();
		this.page = request.getParameter("page").trim();
		this.condition = request.getParameter("condition").trim();
		if(!"".equals(index))
		{
			this.forward(request, response, index, page, condition);
		}
		else
		{
			this.forward(request, response, "1", page, condition);
		}
		
		request.setAttribute(Constant.USER_PAGE_BEAN, this.upageBean);
		request.setAttribute(Constant.PAGE_BEAN, this.pageBean);
		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Paging operation based on the information entered by the administrator and the type of lookup
	 * 根据管理员输入的信息以及查找类型进行分页操作
	 * @param request 
	 * @param response
	 * @param index 操作
	 * @param page 当前页面
	 * @param condition 管理员输入的内容
	 */
	private void forward(HttpServletRequest request, HttpServletResponse response, String index, String page, String condition)
	{
		if("1".equals(index))
		{
			if(!"".equals(page))
			{
				this.pageBean = pageService.setBasicBorrowInforpageBean(Integer.parseInt(page), this.pageSize);
				this.upageBean = null;
			}
			else
			{
				this.pageBean = pageService.setBasicBorrowInforpageBean(1, this.pageSize);
				this.upageBean = null;
			}
		}
		else if("2".equals(index))
		{
			if(!"".equals(condition))
			{
				if(!"".equals(page))
				{
					this.pageBean = pageService.setBorrowInforOfBookPageBean(condition, Integer.parseInt(page), this.pageSize);
					this.upageBean = null;
				}
				else
				{
					this.pageBean = pageService.setBorrowInforOfBookPageBean(condition, 1, pageSize);
					this.upageBean = null;
				}
			}
			else
			{
				this.pageBean = null;
				this.upageBean = null;
			}
		}
		else if("3".equals(index))
		{
			if(!"".equals(condition))
			{
				if(!"".equals(page))
				{
					this.pageBean = pageService.setBorrowInforOfTypePageBean(Integer.parseInt(condition), Integer.parseInt(page), pageSize);
					this.upageBean = null;
				}
				else
				{
					System.out.println("Condition  = "+ condition);
					this.pageBean = pageService.setBorrowInforOfTypePageBean(Integer.parseInt(condition), 1, pageSize);
					this.upageBean = null;
				}
			}
			else
			{
				this.pageBean = null;
				this.upageBean = null;
			}
		}
		else if("4".equals(index))
		{
			if(!"".equals(condition))
			{
				if(!"".equals(page))
				{
					this.pageBean = pageService.setBorrowInforOfUserPageBean(condition, Integer.parseInt(page), pageSize);
					this.upageBean = null;
				}
				else
				{
					this.pageBean = pageService.setBorrowInforOfUserPageBean(condition, 1, pageSize);
					this.upageBean = null;
				}
			}
			else
			{
				this.pageBean = null;
				this.upageBean = null;
			}
		}
		else if("5".equals(index))
		{
			if(!"".equals(condition))
			{
				if(!"".equals(page))
				{
					this.pageBean = pageService.setBorrowInforOfMonth(condition, Integer.parseInt(page), pageSize);
					this.upageBean = null;
				}
				else
				{
					this.pageBean = pageService.setBorrowInforOfMonth(condition, 1, pageSize);
					this.upageBean = null;
				}
			}
			else
			{
				this.pageBean = null;
				this.upageBean = null;
			}
		}
		else if("6".equals(index))
		{
			if(!"".equals(page))
			{
				this.pageBean = pageService.setBorrowInforOfOverTime(Integer.parseInt(page), pageSize);
				this.upageBean = null;
			}
			else
			{
				this.pageBean = pageService.setBorrowInforOfOverTime(1, pageSize);
				this.upageBean = null;
			}
		}
		else if("7".equals(index))
		{
			if(!"".equals(page))
			{
				this.upageBean = pageService.setFrozenAccount(Integer.parseInt(page), pageSize);
				this.pageBean = null;
			}
			else
			{
				this.upageBean = pageService.setFrozenAccount(Integer.parseInt(page), pageSize);
				this.pageBean = null;
			}
		}
	}
}
