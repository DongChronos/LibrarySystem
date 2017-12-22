package com.Library.dao;

import java.util.List;

import com.Library.entity.Admin;
import com.Library.entity.BorrowInfor;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserInfor;

/**
 * 针对所有客户
 * @author ubuntu
 *
 */
public interface UserDao {

	/**
	 * 通过邮箱或者手机查找
	 * @param condition 邮箱或者手机号码
	 * @param usertype 用户类型
	 * @return 返回用户对象
	 */
	Object getUserByCondition(String condition, int userType);
	
	/**
	 * 通过IDCards查找
	 * @param condition 身份证号 
	 * @return
	 */
	Object getUserByIDCards(String condition);
	
	/**
	 * 保存注册的用户对象
	 * @param object
	 */
	void save(Object object);
	
	/**
	 * 保存或者跟新User对象
	 * @param object
	 */
	void update(Object object);
	
	/**
	 * 通过id超找用户对象
	 * @param userID 用户ID
	 * @param userType 用户类型
	 * @return 用户对象
	 */
	Object getUserByID(int userID, int userType);
	
	/**
	 * 通过用户的UserInforID查找用户信息
	 * @param userInforID 用户ID
	 * @return 用户信息
	 */
	UserInfor getUserInforByUserInforID(int userInforID);
	
	/**
	 * 获取所有的UserInfor信息
	 * @return UserInfor的信息以List形式
	 */
	List<UserInfor> getAllUserInfor();
	
	/**
	 * 通过Admin的级别搜索Admin信息、
	 * 模糊检索
	 * 若为空，则默认搜索所有Admin信息
	 * @param condition
	 * @return
	 */
	List<Admin> getAdminByCondition(String condition);
	
	/**
	 * 通过Student级别搜索Student信息
	 * @param condition
	 * @return
	 */
	List<Student> getStudentByCondition(String condition);
	
	/**
	 * 根据电话号码获取学生信息
	 * @param phone
	 * @return
	 */
	Student getStudentByPhone(String phone);
	
	/**
	 * 通过Teacher级别搜索Teacher信息
	 * @param condition
	 * @return
	 */
	List<Teacher> getTeacherByCondition(String condition);
	
	/**
	 * 根据电话号码获取老师信息
	 * @param phone
	 * @return
	 */
	Teacher getTeacherByPhone(String phone);
	
	/**
	 * 直接获取所有所有帐号被冻结的用户信息条数， 不需要任何参数
	 * @return
	 */
	int getFrozenAccount();
	
	/**
	 * 直接获取帐号被冻结的用户信息
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<UserInfor> getFrozenAccount(int startIndex, int pageSize);
	
	/**
	 * 根据借阅信息冻结账户
	 * @param borrowInfor
	 */
	void setFrozenAccount(BorrowInfor borrowInfor);
}
