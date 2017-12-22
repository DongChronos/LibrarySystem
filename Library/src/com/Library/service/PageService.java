package com.Library.service;

import com.Library.bean.PageBean;
import com.Library.entity.BookInfor;
import com.Library.entity.BorrowInfor;
import com.Library.entity.UserInfor;

/**
 * 分页的服务层
 * 服务于所有的分页操作
 * @author ubuntu
 *
 */

public interface PageService {

	/**
	 * 初始化，设置最基本的pageBean(获取所有的书籍，没有任何信息)
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示的信息最大数量
	 * @return
	 */
	PageBean<BookInfor> setBasicPageBean(int pageNum, int pageSize);
	
	/**
	 * 初始化，设置含有bookType的pageBean(获取对应bookType的书籍，没有搜索信息)
	 * @param bookType 书籍类型
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示的信息最大数量
	 * @return
	 */
	PageBean<BookInfor> setBookTypePageBean(int bookType, int pageNum, int pageSize);
	
	/**
	 * 初始化，设置含有简介的pageBean(获取相关简介的书籍，有搜索信息，书籍类型为全部)
	 * @param condition 简介
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示的数据最大条数
	 * @return
	 */
	PageBean<BookInfor> setBookItroPageBean(String condition, int pageNum, int pageSize);
	
	/**
	 * 初始化，设置关于作者的pageBean(获取对应作者的书籍信息)
	 * @param condition 作者名字
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示的最大条数
	 * @return
	 */
	PageBean<BookInfor> setBookAuthorPageBean(String condition, int pageNum, int pageSize);
	
	/**
	 * 初始化，设置关于借阅表的分页信息(无限制)
	 * @param pageNum 当前页数
	 * @param pageSize 页面显示的最大条数
	 * @return
	 */
	PageBean<BorrowInfor> setBasicBorrowInforpageBean(int pageNum, int pageSize);
	
	/**
	 * 初始化，设置关于 借阅表的分页信息(借阅人)
	 * @param condition 借阅人信息
	 * @param pageNum 当前页数
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	PageBean<BorrowInfor> setBorrowInforOfUserPageBean(String condition, int pageNum, int pageSize);
	
	/**
	 * 初始化，设置关于借阅表的分页信息(书名)
	 * @param condition 书名
	 * @param pageNum 当前页数
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	PageBean<BorrowInfor> setBorrowInforOfBookPageBean(String condition, int pageNum, int pageSize);
	
	/**
	 * 初始化蟒蛇值关于借阅表的分页信息(书籍类型)
	 * @param bookType 书籍类型
	 * @param pageNum 当前页数
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	PageBean<BorrowInfor> setBorrowInforOfTypePageBean(int bookType, int pageNum, int pageSize);
	
	/**
	 * 根据年月来进行分页获取书籍信息
	 * @param condition 年月信息
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	PageBean<BorrowInfor> setBorrowInforOfMonth(String condition, int pageNum, int pageSize);
	
	/**
	 * 以分页的方式获取所有超时的借书信息
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	PageBean<BorrowInfor> setBorrowInforOfOverTime(int pageNum, int pageSize);
	
	/**
	 * 以分页的方式获取所有帐号冻结的信息
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	PageBean<UserInfor> setFrozenAccount(int pageNum, int pageSize);
	
	/**
	 * 以分页的方式获取对应电话号码的借阅信息
	 * @param Phone 电话号码
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示信息的最大值
	 * @return
	 */
	PageBean<BorrowInfor> setBorrowInforOfPhone(String Phone, int pageNum, int pageSize);
}
