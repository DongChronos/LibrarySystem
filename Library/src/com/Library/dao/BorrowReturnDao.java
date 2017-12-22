package com.Library.dao;

import com.Library.entity.BorrowInfor;

/**
 * 借阅的数据库操作
 * 针对借阅信息表
 * @author ubuntu
 *
 */

public interface BorrowReturnDao {

	/**
	 * 插入一条新的借阅信息
	 * @param borrowInfor 借阅的信息
	 * @return
	 */
	void save(BorrowInfor borrowInfor);
	
	/**
	 * 跟新一条新的借阅信息
	 * @param borrowInfor 借阅的信息
	 * @return
	 */
	void update(BorrowInfor borrowInfor);
	
	/**
	 * 删除一条借阅的信息
	 * @param borrowInfor 借阅的信息
	 * @return
	 */
	void delete(BorrowInfor borrowInfor);
}
