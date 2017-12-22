package com.Library.bean;

import java.io.Serializable;
import java.util.List;


public class PageBean<T> implements Serializable {

	/**
	 *序列号，不可更改
	 */
	private static final long serialVersionUID = -1059849551475930939L;

	//已知数据
	private int pageNum; //当前页，从请求的URL传过来
	private int pageSize; //每页显示的数据条数
	private int totalRecord; //众的记录条数，查询数据库的到的数据
	
	//需要计算得来
	private int totalPage; //总页数 通过totalPage和pageSize计算得来
	//开始索引， 也就是我们在数据库中要从第几行数据开始拿，有了startIndex和pageSize
	//可以知道Limit语句的两个数据，就能获得每页需要的数据了
	private int startIndex;
	
	//将每页要显示的数据放在list集合中
	private List<T> list;
	
	//存储单条信息
	private T bookInfor;
	
	//分页显示的页数，比如页面上显示1、2、3、4、5页，start为1,end为5，这个也是算过来的
	private int start;
	private int end;
	
	//通过pageNum,pageSize,totalRecord计算得来totalPage startIndex
	/**
	 * 构造函数将pageNum,pageSize,totalRecord
	 */
	public PageBean(int pageNum, int pageSize, int totalRecord)
	{
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		
		//totalPage总页数
		if(totalRecord%pageSize == 0)
		{
			//说明整除，正好每页显示pageSize条数据，没有多余一些显示少于pageSize条数据的
			this.totalPage = totalRecord / pageSize;
		}
		else
		{
			//不整除，需要另加一页，来显示多余的数据
			this.totalPage = totalRecord / pageSize + 1;
		}
		
		//开始索引
		this.startIndex = (pageNum - 1) * pageSize;
		
		this.start= 1;
		this.end = 5;
		
		//显示页数算法
		//显示五页
		if(totalPage <= 5)
		{
			this.end = this.totalPage;
		}
		else
		{
			//总页数小于5,end久违总也数的值
			this.start = pageNum - 2 ;
			this.end = pageNum + 2;
			
			if(start <= 0)
			{
				//比如当前页数是第一页或者第二页
				this.start = 1;
				this.end  = 5;
			}
			
			if(end > this.totalPage)
			{
				this.end = totalPage;
				this.start = end - 5;
			}
			
			if(this.start <0)
			{
				this.start = 0;
			}
		}
		
		if(totalRecord < pageSize)
		{
			this.pageSize = totalRecord;
		}
	}

	/**
	 * 获取当前页
	 * @return
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * 设置当前页
	 * @param pageNum
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 获取每页显示的数据条数
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页显示的数据条数
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取总共的记录数
	 * @return
	 */
	public int getTotalRecord() {
		return totalRecord;
	}

	/**
	 * 设置总共的记录数
	 * @param totalRecord
	 */
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置总页数
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 获取起始索引
	 * @return
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * 设置起始索引
	 * @param startIndex
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * 获取每页要显示的数据
	 * @return
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置每页要显示的数据
	 * @param list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * 获取单条信息
	 * @return
	 */
	public T getbookInfor()
	{
		return bookInfor;
	}
	
	/**
	 * 设置单条信息
	 * @param bookInfor
	 */
	public void setbookInfor(T bookInfor)
	{
		this.bookInfor = bookInfor;
	}
	
	/**
	 * 获取分页的起始页数
	 * @return
	 */
	public int getStart() {
		return start;
	}

	/**
	 * 设置分页的起始页数
	 * @param start
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * 获取分页的结束页数
	 * @return
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * 设置分页的结束页数
	 * @param end
	 */
	public void setEnd(int end) {
		this.end = end;
	}
}
