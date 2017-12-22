package com.Library.bean;

import java.io.Serializable;
import java.util.Date;

public class LoginInfor implements Serializable {

	/**
	 * 学列号，不可更改
	 */
	private static final long serialVersionUID = 6673531694415480032L;
	
	private String loginName = null; //登录名
	private int UserID = 0; //用户ID
	private String ip; //登录的ip地址
	private Date loginTime = null; //登录时间
	
	/**
	 * 无参构造函数
	 */
	public LoginInfor()
	{
		
	}

	/**
	 * 获取登录名
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置登录名
	 * @param loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 获取登录ip地址
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置登录ip地址
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取登录时间
	 * @return
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * 设置登录时间
	 * @param loginTime
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	/**
	 * 获取用户ID
	 * @return
	 */
	public int getUserID() {
		return UserID;
	}

	/**
	 * 设置用户ID
	 * @param userID
	 */
	public void setUserID(int userID) {
		UserID = userID;
	}

	@Override
	public String toString() {
		return "LoginInfor [loginName=" + loginName + ", ip=" + ip + ", loginTime=" + loginTime + "]";
	}
}
