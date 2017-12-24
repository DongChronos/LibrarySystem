package com.Library.entity;

import java.io.Serializable;

public class BookClassfication implements Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = 3842266528834255011L;

	private int classficationID = 0; //书籍类型ID
	private String classficationName = null; //书籍名字
	
	/**
	 * 获取书籍类型ID
	 * @return
	 */
	public int getClassficationID() {
		return classficationID;
	}
	
	/**
	 * 设置书籍类型ID
	 * @param classficationID
	 */
	public void setClassficationID(int classficationID) {
		this.classficationID = classficationID;
	}
	
	/**
	 * 获取书籍类型名字
	 * @return
	 */
	public String getClassficationName() {
		return classficationName;
	}
	
	/**
	 * 设置书籍类型名字
	 * @param classficationName
	 */
	public void setClassficationName(String classficationName) {
		this.classficationName = classficationName;
	}

	@Override
	public String toString() {
		return "BookClassfication [classficationID=" + classficationID + ", classficationName=" + classficationName
				+ "]";
	}
}
