package com.Library.dao;

import java.sql.Date;
import java.util.List;

import com.Library.entity.ApplyInfor;

/**
 * 申请购书的数据库操作
 * @author ubuntu
 *
 */
public interface ApplyBookDao {

	/**
	 * 插入申请购书信息
	 * @param userID 申请人ID
	 * @param bookName 书籍名称
	 * @param bookAuthor 书籍的作者
	 * @param bookClassfication 书籍分类
	 * @param date 申请日期
	 * @param applyNumber 原本的申请数量
	 */
	void InsertApply(int userID, String bookName, String bookAuthor, int bookClassfication, Date date, int applyNumber);
	
	/**
	 * 删除申请购书信息(对于个人操作)[删除一本]
	 * @param userID 申请人ID
	 * @param bookName 书籍名称
	 * @param applyNumber 原本的申请数量
	 */
	void deleteApply(int userID, String bookName, int applyNumber);
	
	/**
	 * 获取申请的用户ID通过书籍名称
	 * @param bookName
	 * @return
	 */
	List<Integer> getUserIDBybookName(String bookName);
	
	/**
	 * 根据UserID以及BookName获取申请购书信息对象
	 * @param userID 用户ID
	 * @param bookName 书名
	 * @return
	 */
	ApplyInfor getApplyInforByUserIDAndBookName(int userID, String bookName);
	
	/**
	 * 获取所有的申请信息
	 */
	List<ApplyInfor> getAllApplyInfor();
	
	/**
	 * 根据用户ID获取申请购书信息
	 * @param userID 用户ID
	 * @return 
	 */
	List<ApplyInfor> getApplyInforByUserID(int userID);
	
	/**
	 * 根据申请书名获取书籍信息
	 * @param bookName 书的名字
	 * @return
	 */
	List<ApplyInfor> getApplyInforByBookName(String bookName);
}
