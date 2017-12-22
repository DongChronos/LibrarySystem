package com.Library.entity;

import java.io.Serializable;

public class Admin implements Serializable {


	/**
	 * 序列号， 不可更改
	 */
	private static final long serialVersionUID = 4803624912076962000L;
	
	private int AdminID = 0; //管理员ID
	private String IDCards = null; //管理员身份证
	private String Intro = null; //管理员简介
	private UserInfor userInfor = null; //管理员基本信息
	private UserConnection userConnection = null; //用户联系表信息
	
	/**
	 * 无参构造函数
	 */
	public Admin()
	{
		
	}
	
	/**
	 * userInfor
	 * 有参构造函数
	 */
	public Admin(UserInfor userInfor)
	{
		this.userInfor = userInfor;
	}

	/**
	 * 获取管理员ID
	 * @return
	 */
	public int getAdminID() {
		return AdminID;
	}

	/**
	 * 设置管理员ID
	 * @param adminID
	 */
	public void setAdminID(int adminID) {
		AdminID = adminID;
	}

	/**
	 * 获取身份证
	 * @return
	 */
	public String getIDCards() {
		return IDCards;
	}

	/**
	 * 设置身份证
	 * @param iDCards
	 */
	public void setIDCards(String iDCards) {
		IDCards = iDCards;
	}

	/**
	 * 获取简介
	 * @return
	 */
	public String getIntro() {
		return Intro;
	}

	/**
	 * 设置简介
	 * @param intro
	 */
	public void setIntro(String intro) {
		Intro = intro;
	}

	/**
	 * 获取管理员基本信息
	 * @return
	 */
	public UserInfor getUserInfor() {
		return userInfor;
	}

	/**
	 * 设置管理员基本信息
	 * @param userInfor
	 */
	public void setUserInfor(UserInfor userInfor) {
		this.userInfor = userInfor;
	}
	
	/**
	 * 获取用户信息联系表
	 * @return
	 */
	public UserConnection getUserConnection() {
		return userConnection;
	}

	/**
	 * 设置用户信息联系表
	 * @param userConnection
	 */
	public void setUserConnection(UserConnection userConnection) {
		this.userConnection = userConnection;
	}

	@Override
	public String toString() {
		return "Admin [AdminID=" + AdminID + ", IDCards=" + IDCards + ", Intro=" + Intro + ", userInfor=" + userInfor
				+ ", userConnection=" + userConnection + "]";
	}
}
