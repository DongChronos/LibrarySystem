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
	private String bookAuthor = null; //书籍作者
	private int bookClassfication = 0; //书籍的类型
	private Date date = null; //申请时间
	private UserInfor userInfor = null; //用户信息
	
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
	 * 获取书籍作者
	 * @return
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}

	/**
	 * 设置书籍作者
	 * @param bookAuthor
	 */
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
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

	@Override
	public String toString() {
		return "ApplyInfor [userID=" + userID + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor
				+ ", bookClassfication=" + bookClassfication + ", date=" + date + ", userInfor=" + userInfor + "]";
	}
}
