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
	 * @param bookClassfication 书籍分类
	 * @param date 申请日期
	 * @param applyNumber 原本的申请数量
	 */
	void InsertApply(int userID, String bookName, int bookClassfication, Date date, int applyNumber);
	
	/**
	 * 根据用户ID获取申请购书信息
	 * @param userID 用户ID
	 * @return 
	 */
	List<ApplyInfor> getApplyInforByUserID(int userID);
	
	/**
	 * 删除申请购书信息
	 * @param userID 申请人ID
	 * @param bookName 书籍名称
	 * @param applyNumber 原本的申请数量
	 */
	void deleteApply(int userID, String bookName, int applyNumber);
}
