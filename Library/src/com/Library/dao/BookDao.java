package com.Library.dao;

import java.util.List;

import com.Library.entity.BookInfor;

/**
 * 针对所有的书籍
 * @author ubuntu
 *
 */
public interface BookDao {
	
	/**
	 * 通过书籍的ID号进行查找
	 * @param bookID 书籍的ID号
	 * @return
	 */
	BookInfor getBookByID(int bookID);
	
	/**
	 * 通过书名获取书籍
	 * @param condition 作者名
	 * @return
	 */
	BookInfor getBookByName(String condition);
	
	/**
	 * 保存书籍对象
	 * @param bookInfor
	 */
	void save(BookInfor bookInfor);
	
	/**
	 * 保存或者跟新对象
	 * @param bookInfor
	 */
	void update(BookInfor bookInfor);
	
	/**
	 * 获取所有的书籍信息
	 * @return
	 */
	List<BookInfor> getAllBookInfor();
	
	/**
	 * 通过简介获取书籍列表
	 * 模糊匹配
	 * @param condition 书籍的简介
	 * @return
	 */
	List<BookInfor> getBookInforbyIntro(String condition);
	
	/**
	 * 通过作者获取书籍列表
	 * @param condition 作者
	 * @return
	 */
	List<BookInfor> getBookInforByAuthor(String condition);
	
	/**
	 * 通过书籍分类获取书籍列表
	 * @param booktype 书籍的分类
	 * @return
	 */
	List<BookInfor> getBookInforByClassfication(int BookType);
	
	/**
	 * 以分页的方式获取书籍信息
	 * @param startIndex 起始索引
	 * @param maxCount 最大页数
	 * @return
	 */
	List<BookInfor> getPaginationBookInfor(int startIndex, int maxCount);
	
	/**
	 * 通过书籍分类，以分页的方式获取书籍信息
	 * @param bookType 书的分类
	 * @param startIndex 起始索引
	 * @param maxcount 最大值
	 * @return
	 */
	List<BookInfor> getPaginationBookInforType(int bookType, int startIndex, int maxCount);
	
	/**
	 * 以分页的方式通过简介内容进行检索书籍信息
	 * @param condition 简介内容
	 * @param startIndex 起始索引
	 * @param maxCount 搜索量的最大值
	 * @return
	 */
	List<BookInfor> getPaginationBookInforByIntro(String condition, int startIndex, int maxCount);
	
	/**
	 * 以分页的方式通过书籍的分类以及简介检索书籍信息
	 * @param bookType 书籍的类型
	 * @param condition 书籍简介
	 * @param startIndex
	 * @param maxCount
	 * @return
	 */
	List<BookInfor> getPaginationBookInforByIntroType(int bookType, String condition, int startIndex, int maxCount);
	
	/**
	 * 通过作者以分页的方式获取书籍信息
	 * @param condition 作者名字
	 * @param startIndex 起始索引
	 * @param maxCount 页面最大的显示值
	 * @return
	 */
	List<BookInfor> getPaPaginationBookInforByAuthor(String condition, int startIndex, int maxCount);
	
	/**
	 * 获取数据库的总条数
	 * @return
	 */
	int getRowNumOfInfor();
	
	/**
	 * 以书籍类型进行分类获取书籍库中的总条数
	 * @param booktype
	 * @return
	 */
	int getRowNumOfBookType(int bookType);
	
	/**
	 * 根据简介获取数据库中的总条数
	 * @param condition
	 * @return
	 */
	int getRowNumOfIntro(String condition);
	
	/**
	 * 根据书籍的类型以及简介获取数据库中的总条数
	 * @param bookType 书籍的类型
	 * @param condition 书籍的简介
	 * @return
	 */
	int getRowNumOfIntrotype(int bookType, String condition);
	
	/**
	 * 通过作者获取书籍的信息条数
	 * @param condition
	 * @return
	 */
	int getRowNumOfAuthor(String condition);
}
