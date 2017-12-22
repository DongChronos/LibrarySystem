package com.Library.globle;

import java.awt.Color;
import java.awt.Font;


/**
 * 配置全局变量
 * @author ubuntu
 *
 */
public class Constant {
	/**
	 * 错误信息提示
	 */
	public static String ERROR = "error_message";
	
	/**
	 * 创建账户成功页面
	 */
	public static String CREATE_ACCOUNT_SUCCESS ="/create_account_success.jsp";
	
	/**
	 * 创建账户失败页面
	 */
	public static String CREATE_ACCOUNT_FALSE ="/create_account_false.jsp";
	
	/**
	 * session中用户
	 */
	public static String USER_KEY = "user";
	
	/**
	 * session中用户的类型
	 */
	public static String USER_TYPE = "user_type";
	
	/**
	 * 验证码
	 */
	public static String CHECK_NUMBER_NAME = "identify_code";
	
	/**
	 * 验证码长度
	 */
	public static final Integer IMAGE_WIDTH=120;
	
	/**
	 * 验证码宽度
	 */
	public static final Integer IMAGE_HEIGHT=40;
	
	/**
	 * 验证码字体
	 */
	public static Font[] codeFont={new Font("Times New Roman",Font.PLAIN,30),
			new Font("Times New Roman",Font.PLAIN,30),new Font("Times New Roman",Font.PLAIN,30),
			new Font("Times New Roman",Font.PLAIN,30),new Font("Times New Roman",Font.PLAIN,30)};
	
	/**
	 * 验证码每个字符颜色
	 */
	public static Color[] color={Color.BLACK,Color.BLUE,Color.RED,Color.DARK_GRAY};
	
	/**
	 * 验证码字库
	 */
	public static final String IMAGE_CHAR="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
	 * 保存在ServletContext中的用户列表
	 */
	public static String ONLINE_USERS = "userMap";
	
	/**
	 * 保存用户session
	 */
	public static String ONLINE_SESSION = "sessionMap";
	
	/**
	 * 书籍信息
	 */
	public static String BOOK_INFOR = "bookInfor";
	
	/**
	 * 书籍信息结果集
	 */
	public static String BOOK_INFORS = "nookInfors";
	
	/**
	 * 书籍分页信息
	 */
	public static String PAGE_BEAN = "pageBean";
	
	/**
	 * 冻结用户信息
	 */
	public static String USER_PAGE_BEAN = "upageBean";
	
	/**
	 * 准备借书的信息
	 */
	public static String BORROW_BOOK_INFOR = "borrow_book_infor";
	
	/**
	 * 借书操作发送的信
	 */
	public static String BOOK_MESSAGE = "book_message";
	
	/**
	 * 帐号借书信息
	 */
	public static String ACCOUNT_BORROW = "account_borrow";
}
