package com.Library.service.impl;

import com.Library.dao.BorrowReturnDao;
import com.Library.dao.jdbc.BorrowReturnDaoImpl;
import com.Library.entity.BorrowInfor;
import com.Library.service.BorrowReturnService;

public class BorrowReturnServiceImpl implements BorrowReturnService {

	public BorrowReturnDao borrowReturnDao = new BorrowReturnDaoImpl();
	@Override
	public boolean BorrowBook(BorrowInfor borrowInfor) {
		boolean flag = false;
		try
		{
			borrowReturnDao.save(borrowInfor);
			flag = true;
		}
		catch(Exception e)
		{
			flag = false;
			System.out.println("借书操作发生错误");
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean ReturnBook(BorrowInfor borrowInfor) {
		boolean flag = false;
		
		try
		{
			borrowReturnDao.delete(borrowInfor);
			flag = true;
		}
		catch(Exception e)
		{
			flag = false;
			System.out.println("还书操作发生错误");
			e.printStackTrace();
		}
		return flag;
	}
}
