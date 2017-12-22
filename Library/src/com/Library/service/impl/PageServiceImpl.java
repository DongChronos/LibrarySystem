package com.Library.service.impl;

import com.Library.bean.PageBean;
import com.Library.dao.BookDao;
import com.Library.dao.SearchBookDao;
import com.Library.dao.UserDao;
import com.Library.dao.jdbc.BookDaoImpl;
import com.Library.dao.jdbc.SearchBookDaoImpl;
import com.Library.dao.jdbc.UserDaoImpl;
import com.Library.entity.BookInfor;
import com.Library.entity.BorrowInfor;
import com.Library.entity.UserInfor;
import com.Library.service.PageService;

/**
 * pageService的接口实现，完成数据库的操作
 * @author ubuntu
 *
 */

public class PageServiceImpl implements PageService {

	@Override
	public PageBean<BookInfor> setBasicPageBean(int pageNum, int pageSize) {
		BookDao bookDao = new BookDaoImpl();
		
		//获取数据库中的总条数
		int RowNum = bookDao.getRowNumOfInfor();
		
		//创建pageBean对象
		PageBean<BookInfor> pb = new PageBean<BookInfor>(pageNum, pageSize, RowNum);
		
		//获取pageBean中的startIndex对象
		int startIndex = pb.getStartIndex();
		
		//拿到每页的数据
		pb.setList(bookDao.getPaginationBookInfor(startIndex, pageSize));
		return pb;
	}

	@Override
	public PageBean<BookInfor> setBookTypePageBean(int bookType, int pageNum, int pageSize) {
		BookDao bookDao = new BookDaoImpl();
		
		//获取对应类型的书籍的条数
		int RowNum = bookDao.getRowNumOfBookType(bookType);
		
		//创建pageBean对象
		PageBean<BookInfor> pb = new PageBean<BookInfor>(pageNum, pageSize, RowNum);
		
		//获取pageBean中的startIndex对象
		int startIndex = pb.getStartIndex();
		
		//拿着每页的数据
		pb.setList(bookDao.getPaginationBookInforType(bookType, startIndex, pageSize));
		return pb;
	}

	@Override
	public PageBean<BookInfor> setBookItroPageBean(String condition, int pageNum, int pageSize) {
		BookDao bookDao = new BookDaoImpl();
		
		//获取相关简介的书籍条数
		int RowNum = bookDao.getRowNumOfIntro(condition);
		
		//创建pageBean对象
		PageBean<BookInfor> pb = new PageBean<BookInfor>(pageNum, pageSize, RowNum);
		
		//获取pageBean中的startIndex对象
		int startIndex = pb.getStartIndex();
		//拿到每页的数据
		if(RowNum != 0)
		{
			pb.setList(bookDao.getPaginationBookInforByIntro(condition, startIndex, pageSize));
		}
		return pb;
	}

	@Override
	public PageBean<BookInfor> setBookAuthorPageBean(String condition, int pageNum, int pageSize) {
		BookDao bookDao = new BookDaoImpl();
		
		//获取对应作者的书籍条数
		int RowNum = bookDao.getRowNumOfAuthor(condition);
		
		//创建pageBean对象
		PageBean<BookInfor> pb = new PageBean<BookInfor>(pageNum, pageSize, RowNum);
		
		//获取pageBean中的startIndex对象
		int startIndex = pb.getStartIndex();
		//拿到每页的数据
		pb.setList(bookDao.getPaPaginationBookInforByAuthor(condition, startIndex, pb.getPageSize()));
		//pb.setList(bookDao.getBookInforByAuthor(condition));
		return pb;
	}

	@Override
	public PageBean<BorrowInfor> setBasicBorrowInforpageBean(int pageNum, int pageSize) {
		SearchBookDao borrowDao = new SearchBookDaoImpl();
		
		//获取借阅表的数据量
		int RowNum = borrowDao.getAllBorrowInfor();
		
		//创建pageBean对象、
		PageBean<BorrowInfor> pb = new PageBean<BorrowInfor>(pageNum, pageSize, RowNum);
		
		//获取pageBean中的startIndex对象
		int startIndex = pb.getStartIndex();
		pb.setList(borrowDao.getPaginationAllBorrowInfor(startIndex, pb.getPageSize()));
		return pb;
	}

	@Override
	public PageBean<BorrowInfor> setBorrowInforOfUserPageBean(String condition, int pageNum, int pageSize) {
		SearchBookDao borrowDao = new SearchBookDaoImpl();
		
		int RowNum = borrowDao.getBorrowInforByUser(condition);
		
		//创建pageBean对象、
		PageBean<BorrowInfor> pb = new PageBean<BorrowInfor>(pageNum, pageSize, RowNum);
		
		//获取pageBean中的startIndex对象
		int startIndex = pb.getStartIndex();
		if(RowNum != 0)
		{
			pb.setList(borrowDao.getPaginationBorrowInforByUser(condition, startIndex, pb.getPageSize()));
		}
		return pb;
	}

	@Override
	public PageBean<BorrowInfor> setBorrowInforOfBookPageBean(String condition, int pageNum, int pageSize) {
		SearchBookDao borrowDao = new SearchBookDaoImpl();
		
		int RowNum = borrowDao.getBorrowInforByBook(condition);
		
		PageBean<BorrowInfor> pb = new PageBean<BorrowInfor>(pageNum, pageSize, RowNum);

		int startIndex = pb.getStartIndex();
		if(RowNum != 0)
		{
			pb.setList(borrowDao.getPaginationBorrowInforByBook(condition, startIndex, pb.getPageSize()));
		}
		return pb;
	}

	@Override
	public PageBean<BorrowInfor> setBorrowInforOfTypePageBean(int bookType, int pageNum, int pageSize) {
		SearchBookDao borrowDao = new SearchBookDaoImpl();
		
		int RowNum = borrowDao.getBorrowInforBybookType(bookType);
		
		PageBean<BorrowInfor> pb = new PageBean<BorrowInfor>(pageNum, pageSize, RowNum);
		
		int startIndex = pb.getStartIndex();
		if(RowNum != 0)
		{
			pb.setList(borrowDao.getPaginationBorrowInforBybookTye(bookType, startIndex, pb.getPageSize()));
		}
		return pb;
	}

	@Override
	public PageBean<BorrowInfor> setBorrowInforOfMonth(String condition, int pageNum, int pageSize) {
		SearchBookDao borrowDao = new SearchBookDaoImpl();
		
		int RowNum = borrowDao.getBorrowInforByMonth(condition);

		PageBean<BorrowInfor> pb = new PageBean<BorrowInfor>(pageNum, pageSize, RowNum);
		
		int startIndex = pb.getStartIndex();
		if(RowNum != 0)
		{
			pb.setList(borrowDao.getPaginationBorrowInforByMonth(condition, startIndex, pb.getPageSize()));
		}
		return pb;
	}

	@Override
	public PageBean<BorrowInfor> setBorrowInforOfOverTime(int pageNum, int pageSize) {
		SearchBookDao borrowDao = new SearchBookDaoImpl();
		
		int RowNum = borrowDao.getBorrowInforByOvertime();

		PageBean<BorrowInfor> pb = new PageBean<BorrowInfor>(pageNum, pageSize, RowNum);
		
		int startIndex = pb.getStartIndex();
		if(RowNum != 0)
		{
			pb.setList(borrowDao.getPaginationBorrowInforByOvertime(startIndex, pb.getPageSize()));
		}
		return pb;
	}

	@Override
	public PageBean<UserInfor> setFrozenAccount(int pageNum, int pageSize) {
		UserDao userDao = new UserDaoImpl();
		
		int RowNum = userDao.getFrozenAccount();
		
		PageBean<UserInfor> pb = new PageBean<UserInfor>(pageNum, pageSize, RowNum);
		
		int startIndex = pb.getStartIndex();
		if(RowNum != 0)
		{
			pb.setList(userDao.getFrozenAccount(startIndex, pb.getPageSize()));
		}
		return pb;
	}

	@Override
	public PageBean<BorrowInfor> setBorrowInforOfPhone(String Phone, int pageNum, int pageSize) {
		SearchBookDao borrowDao = new SearchBookDaoImpl();
		
		int RowNum = borrowDao.getBorrowInforByPhone(Phone);
		
		PageBean<BorrowInfor> pb = new PageBean<BorrowInfor>(pageNum, pageSize, RowNum);
		
		int startIndex = pb.getStartIndex();
		if(RowNum != 0)
		{
			pb.setList(borrowDao.getPaginationBorrowInforByPhone(Phone, startIndex, pb.getPageSize()));
		}
		return pb;
	}
}
