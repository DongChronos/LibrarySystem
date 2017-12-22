package com.Library.entity;

import java.io.Serializable;

/**
 * 书籍信息类
 * 存储书籍的基本信息
 * @author ubuntu
 *
 */
public class BookInfor implements Serializable{

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = -1252260609983770627L;
	
	private int BookID = 0; //标识
	private int BookClassfication = 0; //书的类型ID
	private String BookName = null; //书的名字
	private String Bookauthor = null; //书的作者
	private String Bookintro = null; //书的简介
	private String WordNumber = null; //书的字数
	private int BookNumber = 0; //书本数量
	private int Status = 0; //书的状态
	
	/**
	 * 无参狗仔函数
	 */
	public BookInfor()
	{
		
	}
	
	/**
	 * 有参构造函数
	 * BookID
	 */
	public BookInfor(int BookID)
	{
		this.BookID = BookID;
	}
	
	/**
	 * 有参构造函数
	 * BookID
	 * BookClassficationID
	 */
	public BookInfor(int BookID, int BookClassfication)
	{
		this.BookID = BookID;
		this.BookClassfication = BookClassfication;
	}

	/**
	 * 获取标识
	 * @return
	 */
	public int getBookID() {
		return BookID;
	}

	/**
	 * 设置标识
	 * @param bookID
	 */
	public void setBookID(int bookID) {
		BookID = bookID;
	}

	/**
	 * 获取书籍类型
	 * @return
	 */
	public int getBookClassfication() {
		return BookClassfication;
	}

	/**
	 * 设置书籍类型
	 * @param bookClassfication
	 */
	public void setBookClassfication(int bookClassfication) {
		BookClassfication = bookClassfication;
	}

	/**
	 * 获取书籍名字
	 * @return
	 */
	public String getBookName() {
		return BookName;
	}

	/**
	 * 设置书籍名称
	 * @param bookName
	 */
	public void setBookName(String bookName) {
		BookName = bookName;
	}

	/**
	 * 获取书的作者
	 * @return
	 */
	public String getBookauthor() {
		return Bookauthor;
	}

	/**
	 * 设置书的作者
	 * @param bookauthor
	 */
	public void setBookauthor(String bookauthor) {
		Bookauthor = bookauthor;
	}

	/**
	 * 获取书籍的简介
	 * @return
	 */
	public String getBookintro() {
		return Bookintro;
	}

	/**
	 * 设置书籍的简介
	 * @param bookintro
	 */
	public void setBookintro(String bookintro) {
		Bookintro = bookintro;
	}

	/**
	 * 获取书的字数
	 * @return
	 */
	public String getWordNumber() {
		return WordNumber;
	}

	/**
	 * 设置书的字数
	 * @param wordNumber
	 */
	public void setWordNumber(String wordNumber) {
		WordNumber = wordNumber;
	}

	/**
	 * 获取书的数量
	 * @return
	 */
	public int getBookNumber() {
		return BookNumber;
	}

	/**
	 * 设置书的数量
	 * @param bookNumber
	 */
	public void setBookNumber(int bookNumber) {
		BookNumber = bookNumber;
	}

	/**
	 * 获取书的状态
	 * @return
	 */
	public int getStatus() {
		return Status;
	}

	/**
	 * 设置书的状态
	 * @param status
	 */
	public void setStatus(int status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "BookInfor [BookID=" + BookID + ", BookClassfication=" + BookClassfication + ", BookName=" + BookName
				+ ", Bookintro=" + Bookintro + ", WordNumber=" + WordNumber + ", BookNumber=" + BookNumber + ", Status="
				+ Status + "]";
	}
}
