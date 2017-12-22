package com.Library.service;

/**
 * 用户服务接口
 * @author ubuntu
 *
 */
public interface UserService {

	/**
	 * 检查验证码是否一致
	 * @param rightCode 正确的验证码
	 * @param code 用户输入的验证码
	 * @return
	 */
	boolean chechCode(String rightCode, String code);
	
	/**
	 * 在用户修改数据时
	 * 验证email跟手机的唯一性
	 * @param email 
	 * @param phone
	 * @param object
	 * @return
	 */
	boolean checkEmailAndPhone(String email,String phone,Object object);
	
	/**
	 * 保存用户对象
	 * @param object 保存有新用户信息的数组
	 * @return
	 */
	boolean registerUser(Object object);
	
	/**
	 * 跟新用户信息
	 * @param object 用户的具体信息
	 * @return
	 */
	boolean UpdateUser(Object object);
}
