package com.Library.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 借阅信息表
 * @author ubuntu
 *
 */
public class BorrowInfor implements Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = -1275536742888509923L;

	private int userID = 0; //借阅人ID
	private int bookID = 0; //借阅的书的ID
	private Date Start = null; //借阅的开始时间
	private Date Finish = null; //借阅应该的结束时间
	private boolean Overtime = false; //是否超时
	private UserInfor userInfor = null; //借阅人的用户信息
	private BookInfor bookInfor = null; //借阅的书籍信息
	
	/**
	 * 无参构造函数
	 */
	public BorrowInfor()
	{
		
	}
	
	/**
	 * 有参构造函数
	 * @param userID 用户的ID
	 * @param bookID 书籍的ID
	 */
	public BorrowInfor(int userID, int bookID)
	{
		this.userID = userID;
		this.bookID = bookID;
	}
	
	/**
	 * 有参构造
	 * @param userInfor 用户信息
	 * @param bookInfor 书籍信息
	 */
	public BorrowInfor(UserInfor userInfor, BookInfor bookInfor)
	{
		this.userInfor = userInfor;
		this.bookInfor = bookInfor;
	}

	/**
	 * 获取用户的ID
	 * @return
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * 设置用户的ID
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * 获取书籍的ID
	 * @return
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * 设置书籍的ID
	 * @param bookID
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	/**
	 * 获取借阅的开始时间
	 * @return
	 */
	public Date getStart() {
		return Start;
	}

	/**
	 * 设置借阅的开始时间
	 * @param start
	 */
	public void setStart(Date start) {
		Start = start;
	}

	/**
	 * 获取借阅的结束时间
	 * @return
	 */
	public Date getFinish() {
		return Finish;
	}

	/**
	 * 设置借阅的结束时间
	 * @param finish
	 */
	public void setFinish(Date finish) {
		Finish = finish;
	}

	/**
	 * 获取是否超时
	 * @return
	 */
	public boolean isOvertime() {
		return Overtime;
	}

	/**
	 * 设置是否超时
	 * @param overtime
	 */
	public void setOvertime(boolean overtime) {
		Overtime = overtime;
	}

	/**
	 * 获取用户信息
	 * @return
	 */
	public UserInfor getUserInfor() {
		return userInfor;
	}

	/**
	 * 设置用户信息
	 * @param userInfor
	 */
	public void setUserInfor(UserInfor userInfor) {
		this.userInfor = userInfor;
	}

	/**
	 * 获取书籍信息
	 * @return
	 */
	public BookInfor getBookInfor() {
		return bookInfor;
	}

	/**
	 * 设置书籍信息
	 * @param bookInfor
	 */
	public void setBookInfor(BookInfor bookInfor) {
		this.bookInfor = bookInfor;
	}

	@Override
	public String toString() {
		return "BorrowInfor [userID=" + userID + ", bookID=" + bookID + ", Start=" + Start + ", Finish=" + Finish
				+ ", Overtime=" + Overtime + ", userInfor=" + userInfor + ", bookInfor="
				+ bookInfor + "]";
	}
}
