package com.Library.entity;

import java.io.Serializable;

public class Teacher implements Serializable {

	/**
	 * 序列号，不可改变
	 */
	private static final long serialVersionUID = -6001941311981304972L;

	private int TeacherID = 0; //老师ID
	private String IDCards = null; //老师身份证
	private int  TeacherCredit = 0; //老师信用ID
	private int Experience = 0; //老师的经验
	private int BorCredit = 0; //可以申请购书的数量
	private int Credit = 0; //可以借阅的数量
	private UserInfor userInfor = null; //老师基本信息
	private UserConnection userConnection = null; //用户联系信息
	
	/**
	 * 无参构造函数
	 */
	public Teacher()
	{
		
	}
	
	/**
	 * 有参构造函数
	 * @param userInfor
	 */
	public Teacher(UserInfor userInfor)
	{
		this.userInfor = userInfor;
	}
	
	/**
	 * 获取老师的ID
	 * @return
	 */
	public int getTeacherID() {
		return TeacherID;
	}

	/**
	 * 设置老师的ID
	 * @param teacherID
	 */
	public void setTeacherID(int teacherID) {
		TeacherID = teacherID;
	}

	/**
	 * 获取老师身份证
	 */
	public String getIDCards() {
		return IDCards;
	}

	/**
	 * 设置学生身份证
	 * @param iDCards
	 */
	public void setIDCards(String iDCards) {
		IDCards = iDCards;
	}

	/**
	 * 获取老师信用ID
	 * @return
	 */
	public int getTeacherCredit() {
		return TeacherCredit;
	}

	/**
	 * 设置老师的信用ID
	 * @param teacherCredit
	 */
	public void setTeacherCredit(int teacherCredit) {
		TeacherCredit = teacherCredit;
	}

	/**
	 * 获取老师的经验
	 * @return
	 */
	public int getExperience() {
		return Experience;
	}

	/**
	 * 设置老师的经验
	 * @param experience
	 */
	public void setExperience(int experience) {
		Experience = experience;
	}
	
	
	/**
	 * 获取可以申请购书的数量
	 * @return
	 */
	public int getBorCredit() {
		return BorCredit;
	}

	/**
	 * 设置可以申请购书的数量
	 * @param borCredit
	 */
	public void setBorCredit(int borCredit) {
		BorCredit = borCredit;
	}

	/**
	 * 获取可以借阅的数量
	 * @return
	 */
	public int getCredit() {
		return Credit;
	}

	/**
	 * 设置可以借阅的数量
	 * @param credit
	 */
	public void setCredit(int credit) {
		Credit = credit;
	}

	/**
	 * 获取老师的基本信息
	 * @return
	 */
	public UserInfor getUserInfor() {
		return userInfor;
	}

	
	/**
	 * 设置老师的基本信息
	 * @param userInfor
	 */
	public void setUserInfor(UserInfor userInfor) {
		this.userInfor = userInfor;
	}

	/**
	 * 获取用户联系信息
	 * @return
	 */
	public UserConnection getUserConnection() {
		return userConnection;
	}

	/**
	 * 设置用户联系信息
	 * @param userConnection
	 */
	public void setUserConnection(UserConnection userConnection) {
		this.userConnection = userConnection;
	}

	@Override
	public String toString() {
		return "Teacher [TeacherID=" + TeacherID + ", IDCards=" + IDCards + ", TeacherCredit=" + TeacherCredit
				+ ", Experience=" + Experience + ", BorCredit=" + BorCredit + ", Credit=" + Credit + ", userInfor="
				+ userInfor + ", userConnection=" + userConnection + "]";
	}
}
