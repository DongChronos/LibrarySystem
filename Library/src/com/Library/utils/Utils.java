package com.Library.utils;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Library.entity.Admin;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserInfor;
import com.Library.globle.Constant;

/**
 * 静态工具类
 * 为各个类提供服务
 * @author ubuntu
 *
 */
public class Utils implements Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = 114018163536390322L;
	
	/**
	 * 检测邮箱是否符合邮箱格式
	 * @param email 待检测的邮箱字符串
	 * @return
	 */
	public static boolean isEmail(String email)
	{
		String rule = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(rule);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * 检查手机格式是否正确
	 * @param phone 手机代检测的字符串
	 * @return
	 */
	public static boolean isPhone(String phone)
	{
		String rule = "(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7}";
		Pattern pattern = Pattern.compile(rule);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}
	
	/**
	 * 判断身份证是否正确
	 * @param idCards 身份证字符串
	 * @return
	 */
	public static boolean isIDCards(String idCards)
	{
		boolean flag = false;
		if(idCards.length() == 18)
		{
			flag = true;
		}
		else
		{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 将字符串进行MD5加密
	 * @param secur
	 * @return
	 */
	public static String toMD5(String secur)
	{
		String result = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(secur.getBytes());
			result = new BigInteger(1,md.digest()).toString(32);
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将时间字符串转换为sql的Date类型
	 * @param ymd 获取的表单中的时间
	 * @return sql的Date类型
	 */
	public static final Date stringToDate(String ymd)
	{
		Date sqlDate = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date;
			date = sdf.parse(ymd);
			sqlDate = new Date(date.getTime());
		}
		catch(ParseException e)
		{
			System.out.println("在工具类中将字符串转换为sql的date类型发生错误");
			e.printStackTrace();
		}
		return sqlDate;
	}
	
	/**
	 * 装换用户类型
	 * @param gender
	 * 		String
	 * @return
	 * 		Boolean
	 * 		0-男，1-女
	 */
	public static boolean changeUserGenderType(String gender)
	{
		boolean flag = false;
		if(gender.equals("男"))
		{
			flag = false;
		}
		else
		{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取用户类型
	 * @param object
	 * @return
	 */
	public static UserInfor getUserInfor(Object object)
	{
		UserInfor userInfor = null;
		if(object instanceof Admin)
		{
			userInfor = ((Admin) object).getUserInfor();
		}
		else if(object instanceof Student)
		{
			userInfor = ((Student) object).getUserInfor();
		}
		else if(object instanceof Teacher)
		{
			userInfor = ((Teacher) object).getUserInfor();
		}
		return userInfor;
	}

	/**
	 * 判断用户是否可以借书或者申请购书
	 * @param nowCredit 当前已经借的书或者已经申请的树的数量
	 * @param maxCredit 最多可以借的书或者申请的书
	 * @return
	 */
	public static boolean checkCredit(int nowCredit, int maxCredit)
	{
		return nowCredit < maxCredit;
	}
	
	/**
	 * 获取当前时间
	 * @param calendar 传入的calender类型的时间数据
	 * @return
	 */
	public static Date CalendarToDate(Calendar calendar)
	{
		java.util.Date uDate = calendar.getTime();
		return new Date(uDate.getTime());
	}
	
	/**
	 * 获取当前时间之后的一个月
	 * @param calendar 传入的calendar类型的时间数据
	 * @return
	 */
	public static Date CalenderToDatePlus(Calendar calendar)
	{
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DATE) + 30);
		java.util.Date uDate = calendar.getTime();
		return new Date(uDate.getTime());
	}
	
	/**
	 * 判断用户借书的deadline是否超过30天
	 * @param date
	 * @return
	 */
	public static boolean FrozenDate(Date date)
	{
		boolean flag = false;
		
		//当前时间转换为sqlDate
		Calendar nowCalendar = Calendar.getInstance();
		java.util.Date uDate = nowCalendar.getTime();
		Date snDate = new Date(uDate.getTime());
		
		//sqlDate转换为Calendar，加30天再转换为sqlDate
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(date);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.get(Calendar.DATE) + 30);
		uDate = endCalendar.getTime();
		Date seDate = new Date(uDate.getTime());
		
		if(seDate.before(snDate))
		{
			flag = true;
		}		
		return flag;
	}
	
	/**
	 * 获取罚金
	 * @return
	 */
	public static int getPenalty(Date date)
	{
		//获取当前时间
		Calendar nowCalendar = Calendar.getInstance();
		
		//将sqlDate转换为Calendar
		Calendar finCalendar = Calendar.getInstance();
		finCalendar.setTime(date);
		int penalty = (int)((nowCalendar.getTimeInMillis()-finCalendar.getTimeInMillis())/(3600*24*1000))*Constant.PENALTY;	
		if(penalty < 0)
		{
			penalty = 0;
		}
		return penalty;
	}
}
