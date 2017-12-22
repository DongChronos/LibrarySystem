package com.Library.dao.jdbc;


import com.Library.dao.BorrowReturnDao;
import com.Library.dao.UserDao;
import com.Library.entity.BorrowInfor;

/**
 * 借书 还书的数据库操作实现
 * @author ubuntu
 *
 */

public class BorrowReturnDaoImpl extends JDBCBase implements BorrowReturnDao {

	/**
	 * 序列号， 不可更改
	 */
	private static final long serialVersionUID = 4539489118280990075L;

	@Override
	//借阅书籍的操作
	public void save(BorrowInfor borrowInfor) {
		String sql = "INSERT INTO BorrowInfor(UserID, BookID, Start, Finish, Overtime) VALUES(?,?,?,?,?)";
		Object[] bIparma =
			{
				borrowInfor.getUserID(),
				borrowInfor.getBookID(),
				borrowInfor.getStart(),
				borrowInfor.getFinish(),
				borrowInfor.isOvertime()
			};
		
		saveOrUpdateOrDelete(sql, bIparma);
		System.out.println("保存借阅信息成功");
		
		sql = "UPDATE BookInfor SET BookNumber=? WHERE BookID=?";
		Object[] BIparma = 
			{
				borrowInfor.getBookInfor().getBookNumber() - 1,
				borrowInfor.getBookID()
			};
		saveOrUpdateOrDelete(sql, BIparma);
		System.out.println("(借阅)跟新书籍信息成功");
		
		sql = "UPDATE UserInfor SET BroBookNumber=? WHERE UserID=?";
		Object[] UIparam = 
			{
				borrowInfor.getUserInfor().getBroBookNumber() + 1,
				borrowInfor.getUserID()
			};
		saveOrUpdateOrDelete(sql, UIparam);
		System.out.println("(借阅)更新用户信息成功");
	}

	@Override
	//用户未在deadline之前归还书籍，将会修改Overtime数据
	public void update(BorrowInfor borrowInfor) {
		String sql = "UPDATE BorrowInfor SET Overtime=? WHERE UserID=?";
		Object[] ubiparam = 
			{
				1,
				borrowInfor.getUserID()
			};
		saveOrUpdateOrDelete(sql, ubiparam);
		System.out.println("Overtime更新完成");
	}

	@Override
	//还书的操作
	public void delete(BorrowInfor borrowInfor) {
		UserDao userDao = new UserDaoImpl();
		String sql = "DELETE FROM BorrowInfor WHERE UserID=? AND BookID=?";
		Object[] dbriparam = 
			{
				borrowInfor.getUserID(),
				borrowInfor.getBookID()
			};
		saveOrUpdateOrDelete(sql, dbriparam);
		System.out.println("删除BorrowInfor表中的一条信息");
		
		sql = "UPDATE UserInfor SET BroBookNumber=? WHERE UserID=?";
		Object[] duiparam = 
			{
				borrowInfor.getUserInfor().getBroBookNumber() - 1,
				borrowInfor.getUserID()
			};
		saveOrUpdateOrDelete(sql, duiparam);
		System.out.println("更新用户信息完成");
		
		sql = "UPDATE BookInfor SET BookNumber=? WHERE BookID=?";
		Object[] dbiparam = 
			{
					borrowInfor.getBookInfor().getBookNumber() + 1,
					borrowInfor.getBookID()
			};
		saveOrUpdateOrDelete(sql, dbiparam);
		System.out.println("更新书籍信息完成");
		
		if(borrowInfor.getUserInfor().getUserTypeID() == 1)
		{
			sql = "UPDATE StudentInfor SET Experience=? WHERE StudentID=?";
			Object[] usiparam = 
				{
					userDao.getStudentByPhone(borrowInfor.getUserInfor().getPhone()).getExperience() + 1,
					userDao.getStudentByPhone(borrowInfor.getUserInfor().getPhone()).getStudentID()
				};
			saveOrUpdateOrDelete(sql, usiparam);
			System.out.println("(还书)学生信息表更新完成");
		}
		
		else if(borrowInfor.getUserInfor().getUserTypeID() == 2)
		{
			sql = "UPDATE TeacherInfor SET Experience=? WHERE TeacherID=?";
			Object[] utiparam = 
				{
						userDao.getTeacherByPhone(borrowInfor.getUserInfor().getPhone()).getExperience() + 1,
						userDao.getTeacherByPhone(borrowInfor.getUserInfor().getPhone()).getTeacherID()
				};
			saveOrUpdateOrDelete(sql, utiparam);
			System.out.println("(还书)老师信息表更新完成");
		}
	}

}
