package com.Library.entity;

import java.io.Serializable;
import java.sql.Date;

public class ApplyInfor implements Serializable {

	/**
	 * 序列号， 不可更改
	 */
	private static final long serialVersionUID = 672369170536516847L;

	private int userID = 0; //用户ID
	private String bookName = null; //申请的书籍名
	private int bookClassfication = 0; //书籍的类型
	private Date date = null; //申请时间
	
	/**
	 * 无参构造函数
	 */
	public ApplyInfor()
	{
		
	}
	
	/**
	 * 有参构造函数
	 * @param userID
	 */
	public ApplyInfor(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 获取用户ID
	 * @return
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * 设置用户信息
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * 获取书籍名称
	 * @return
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * 设置书籍名称
	 * @param bookName
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * 获取申请时间
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 设置书籍时间
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 获取书籍类型
	 * @return
	 */
	public int getBookClassfication() {
		return bookClassfication;
	}

	/**
	 * 设置书籍类型
	 * @param bookClassfication
	 */
	public void setBookClassfication(int bookClassfication) {
		this.bookClassfication = bookClassfication;
	}

	@Override
	public String toString() {
		return "ApplyInfor [userID=" + userID + ", bookName=" + bookName + ", bookClassfication=" + bookClassfication
				+ ", date=" + date + "]";
	}	
}
