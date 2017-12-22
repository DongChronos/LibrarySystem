package com.Library.entity;

public interface IUser {

	/**
	 * 获取用户实体
	 * @param userInfor 用户的基本信息
	 * @return object对象[老师 学生 管理员]
	 */
	Object getUser(UserInfor userInfor); 
}
