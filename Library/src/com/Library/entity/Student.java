package com.Library.entity;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = -7988479899503819047L;

	private int StudentID = 0; //学生ID
	private String IDCards = null; //学生身份证
	private int StudentCredit = 0; //学生信用ID
	private int Experience = 0; //学生经验
	private int BorCredit = 0; //学生可以申请购书的数量
	private int Credit = 0; //学生可以借阅的数量
	private UserInfor userInfor = null; //学生基本信息
	private UserConnection userConnection = null; //用户联系信息
	
	/**
	 * 无参构造函数
	 */
	public Student()
	{
		
	}
	
	/**
	 * 有参构造函数
	 * @param uerInfor
	 */
	public Student(UserInfor userInfor)
	{
		this.userInfor = userInfor;
	}

	/**
	 * 获取学生的ID
	 * @return
	 */
	public int getStudentID() {
		return StudentID;
	}

	/**
	 * 设置学生的ID
	 * @param studentID
	 */
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	/**
	 * 获取学生身份证
	 * @return
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
	 * 获取学生信用ID
	 * @return
	 */
	public int getStudentCredit() {
		return StudentCredit;
	}

	/**
	 * 设置学生信用ID
	 * @param studentCredit
	 */
	public void setStudentCredit(int studentCredit) {
		StudentCredit = studentCredit;
	}

	/**
	 * 获取学生经验
	 * @return
	 */
	public int getExperience() {
		return Experience;
	}

	/**
	 * 设置学生信用经验
	 * @param experience
	 */
	public void setExperience(int experience) {
		Experience = experience;
	}

	/**
	 * 获取学生申请购书的数量
	 * @return
	 */
	public int getBorCredit() {
		return BorCredit;
	}

	/**
	 * 设置学生申请购书的数量
	 * @param borCredit
	 */
	public void setBorCredit(int borCredit) {
		BorCredit = borCredit;
	}

	/**
	 * 获取学生借阅的数量
	 * @return
	 */
	public int getCredit() {
		return Credit;
	}

	/**
	 * 设置学生借阅的数量
	 * @param credit
	 */
	public void setCredit(int credit) {
		Credit = credit;
	}

	/**
	 * 获取学生基本信息
	 * @return
	 */
	public UserInfor getUserInfor() {
		return userInfor;
	}

	/**
	 * 设置学生基本信息
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
		return "Student [StudentID=" + StudentID + ", IDCards=" + IDCards + ", StudentCredit=" + StudentCredit
				+ ", Experience=" + Experience + ", BorCredit=" + BorCredit + ", Credit=" + Credit + ", userInfor="
				+ userInfor + ", userConnection=" + userConnection + "]";
	}
}
