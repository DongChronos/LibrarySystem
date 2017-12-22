package com.Library.entity;

import java.io.Serializable;

public class UserInfor implements Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = 5468029208085308085L;
	
	private int UserID = 0; //标识
	private String PeopleName = null; //真实姓名
	private String LoginName = null; //登录名
	private String Password = null; //登录密码
	private String Phone = null; //电话号码
	private String Email = null; //电子邮箱
	private boolean Gender = false; //性别
	private String QQ = null; //QQ帐号
	private String WeChat = null; //微信号
	private int Status = 0; //帐号状态
	private int UserTypeID = 0; //用户类型
	private String PeopleImg = null; //用户头像
	private int AppBookNumber = 0; //申请购书的数量
	private int BroBookNumber = 0; //正在借阅数的数量
	
	
	/**
	 * 无参构造函数
	 */
	public UserInfor()
	{
		
	}
	
	/**
	 * 有参构造函数
	 * PeopleID
	 */
	public UserInfor(int UserID)
	{
		this.UserID = UserID;
	}
	
	/**
	 * 获取标识
	 */
	public int getUserID() {
		return UserID;
	}
	
	/**
	 * 设置标识
	 */
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	
	/**
	 * 获取真实姓名
	 */
	public String getPeopleName() {
		return PeopleName;
	}
	
	/**
	 * 设置真实姓名
	 */
	public void setPeopleName(String peopleName) {
		PeopleName = peopleName;
	}
	
	/**
	 * 获取登录名
	 */
	public String getLoginName() {
		return LoginName;
	}
	
	/**
	 * 设置登录名
	 */
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	
	/**
	 * 获取密码
	 * @return
	 */
	public String getPassword() {
		return Password;
	}

	/**
	 * 设置密码
	 * @param password
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * 获取电话号码
	 */
	public String getPhone() {
		return Phone;
	}
	
	/**
	 * 设置电话号码
	 */
	public void setPhone(String phone) {
		Phone = phone;
	}
	
	/**
	 * 获取电子邮箱
	 */
	public String getEmail() {
		return Email;
	}
	
	/**
	 * 设置电子邮箱
	 */
	public void setEmail(String email) {
		Email = email;
	}
	
	/**
	 * 获取性别
	 */
	public boolean isGender() {
		return Gender;
	}
	
	/**
	 * 设置性别
	 */
	public void setGender(boolean gender) {
		Gender = gender;
	}
	
	/**
	 * 获取QQ帐号
	 */
	public String getQQ() {
		return QQ;
	}
	
	/**
	 * 设置QQ帐号
	 */
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	
	/**
	 * 获取微信帐号
	 */
	public String getWeChat() {
		return WeChat;
	}
	
	/**
	 * 设置微信帐号
	 */
	public void setWeChat(String weChat) {
		WeChat = weChat;
	}
	
	/**
	 * 获取帐号状态
	 */
	public int getStatus() {
		return Status;
	}
	
	/**
	 * 设置帐号状态
	 */
	public void setStatus(int status) {
		Status = status;
	}
	
	/**
	 * 获取用户类型
	 */
	public int getUserTypeID() {
		return UserTypeID;
	}
	
	/**
	 * 设置用户类型
	 */
	public void setUserTypeID(int userTypeID) {
		UserTypeID = userTypeID;
	}
	
	/**
	 * 获取用户头像
	 */
	public String getPeopleImg() {
		return PeopleImg;
	}
	
	/**
	 * 设置用户头像
	 */
	public void setPeopleImg(String peopleImg) {
		PeopleImg = peopleImg;
	}
	
	/**
	 * 获取用户申请购书数量
	 */
	public int getAppBookNumber() {
		return AppBookNumber;
	}
	
	/**
	 * 设置用户申请购书数量
	 */
	public void setAppBookNumber(int appBookNumber) {
		AppBookNumber = appBookNumber;
	}
	
	/**
	 * 获取用户正在借阅的数量
	 */
	public int getBroBookNumber() {
		return BroBookNumber;
	}
	
	/**
	 *设置用户正在借阅的数量 
	 */
	public void setBroBookNumber(int broBookNumber) {
		BroBookNumber = broBookNumber;
	}

	/**
	 * 转化为字符串
	 */
	@Override
	public String toString() {
		return "UserInfor [UserInforID=" + UserID + ", PeopleName=" + PeopleName + ", LoginName=" + LoginName
				+ ", Password=" + Password + ", Phone=" + Phone + ", Email=" + Email + ", Gender=" + Gender + ", QQ="
				+ QQ + ", WeChat=" + WeChat + ", Status=" + Status + ", UserTypeID=" + UserTypeID + ", PeopleImg="
				+ PeopleImg + ", AppBookNumber=" + AppBookNumber + ", BroBookNumber=" + BroBookNumber + "]";
	}

	
}
