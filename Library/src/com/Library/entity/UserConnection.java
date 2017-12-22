package com.Library.entity;

import java.io.Serializable;

/**
 * 用户信息联系类
 * @author ubuntu
 *
 */
public class UserConnection implements Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = 7397800904505861353L;
	
	private int UserConnectionID; //用户联系表ID
	private int UserInforID; //用户基本信息ID
	private int AdminID; //管理员ID
	private int StudentID; //学生ID
	private int TeacherID; //老师ID
	
	public UserConnection()
	{
		
	}

	/**
	 * 获取用户联系表的ID
	 * @return
	 */
	public int getUserConnection() {
		return UserConnectionID;
	}

	/**
	 * 设置用户联系表ID
	 * @param userConnection
	 */
	public void setUserConnectionID(int userConnectionID) {
		UserConnectionID = userConnectionID;
	}

	/**
	 * 获取用户基本信息ID
	 * @return
	 */
	public int getUserInforID() {
		return UserInforID;
	}

	/**
	 * 设置用户基本信息ID
	 * @param userInforID
	 */
	public void setUserInforID(int userInforID) {
		UserInforID = userInforID;
	}

	/**
	 * 获取管理员信息ID
	 * @return
	 */
	public int getAdminID() {
		return AdminID;
	}

	/**
	 * 设置管理员信息ID
	 * @param adminID
	 */
	public void setAdminID(int adminID) {
		AdminID = adminID;
	}

	/**
	 * 获取学生信息ID
	 * @return
	 */
	public int getStudentID() {
		return StudentID;
	}

	/**
	 * 设置学生信息ID
	 * @param studentID
	 */
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	/**
	 * 获取老师信息ID
	 * @return
	 */
	public int getTeacherID() {
		return TeacherID;
	}

	/**
	 * 设置学生信息ID
	 * @param teacherID
	 */
	public void setTeacherID(int teacherID) {
		TeacherID = teacherID;
	}

	/**
	 * 转换为字符串
	 */
	@Override
	public String toString() {
		return "UserConnection [UserConnection=" + UserConnectionID + ", UserInforID=" + UserInforID + ", AdminID="
				+ AdminID + ", StudentID=" + StudentID + ", TeacherID=" + TeacherID + "]";
	}
	
	

}
