package com.Library.service;

import com.Library.entity.BorrowInfor;


/**
 * 借书以及还书的数据库操作接口
 * 服务于所有的借书还书操作
 * @author ubuntu
 *
 */

public interface BorrowReturnService {

	/**
	 * 借书操作
	 * @param borrowInfor 借阅书籍存储的信息
	 * @return
	 */
	boolean BorrowBook(BorrowInfor borrowInfor);
	
	/**
	 * 还书操作
	 * @param borrowInfor需要还的书的信息
	 * @return
	 */
	boolean ReturnBook(BorrowInfor borrowInfor);
}
