package com.Library.dao;

import java.util.List;

import com.Library.entity.BorrowInfor;

/**
 * 借书信息的数据库操作接口
 * @author ubuntu
 *
 */

public interface SearchBookDao {

	/**
	 * 获取所有的借书信息条数
	 * @return
	 */
	int getAllBorrowInfor();
	
	/**
	 * 根据借阅人获取借书信息条数
	 * @param condition 借阅人信息
	 * @return
	 */
	int getBorrowInforByUser(String condition);
	
	/**
	 * 根据书名获取借书信息条数
	 * @param condition
	 * @return
	 */
	int getBorrowInforByBook(String condition);
	
	/**
	 * 根据书籍类型获取借书信息条数
	 * @param bookType
	 * @return
	 */
	int getBorrowInforBybookType(int bookType);
	
	/**
	 * 根据年月信息获取借书信息
	 * @param condition 年月信息
	 * @return
	 */
	int getBorrowInforByMonth(String condition);
	
	/**
	 * 直接获取超时未归还书的借阅信息的条数， 不需要任何参数
	 * @return
	 */
	int getBorrowInforByOvertime();
	
	/**
	 * 直接获取对应电话号码的借阅信息条数
	 * @param Phone 电话号码
	 * @return
	 */
	int getBorrowInforByPhone(String Phone);
	
	/**
	 * 以分页的方式获取所有的信息
	 * @param startIndex 起始索引
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	List<BorrowInfor> getPaginationAllBorrowInfor(int startIndex, int pageSize);
	
	/**
	 * 以分页的方式获取对应用户的借书信息
	 * @param condition 借阅人信息
	 * @param startIndex 起始索引
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	List<BorrowInfor> getPaginationBorrowInforByUser(String condition, int startIndex, int pageSize);
	
	/**
	 * 以分页的方式获取对应书名的借书情况
	 * @param condition 书名
	 * @param startIndex 起始索引
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	List<BorrowInfor> getPaginationBorrowInforByBook(String condition, int startIndex, int pageSize);
	
	/**
	 * 以分页的方式获取书籍类型的借书情况
	 * @param bookType 书籍类型
	 * @param startIndex 起始索引
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	List<BorrowInfor> getPaginationBorrowInforBybookTye(int bookType, int startIndex, int pageSize);
	
	/**
	 * 通过年月信息以分页的方式获取书籍借阅信息
	 * @param condition 年月信息
	 * @param startIndex 起始索引
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	List<BorrowInfor> getPaginationBorrowInforByMonth(String condition, int startIndex, int pageSize);
	
	/**
	 * 以分页的方式获取超时未归还书的借阅信息
	 * @param startIndex 起始索引
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	List<BorrowInfor> getPaginationBorrowInforByOvertime(int startIndex, int pageSize);
	
	/**
	 * 以分页的方式获取对应电话号码的借阅信息
	 * @param Phone 电话号码
	 * @param startIndex 起始索引
	 * @param pageSize 页面显示的最大值
	 * @return
	 */
	List<BorrowInfor> getPaginationBorrowInforByPhone(String Phone, int startIndex, int pageSize);
	
	/**
	 * 根据电话号码以及书籍的id获取借书信息
	 * @param phone 电话号码
	 * @param bookID 书籍信息
	 * @return
	 */
	BorrowInfor getBorrowInforByPhoneAndBookID(String phone, String bookID);
	
	/**
	 * 获取所有的借书信息
	 * @return
	 */
	List<BorrowInfor> getAlltheBorrowInfor();
	
	/**
	 * 根据用户ID获取借书信息
	 * @param userID
	 * @return
	 */
	List<BorrowInfor> getBorrowInforByUserID(int userID);
}
