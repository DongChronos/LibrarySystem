package com.Library.utils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Library.entity.Admin;
import com.Library.entity.ApplyInfor;
import com.Library.entity.BookClassfication;
import com.Library.entity.BookInfor;
import com.Library.entity.BorrowInfor;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserConnection;
import com.Library.entity.UserInfor;

/**
 * 对信息进行打包
 * @author ubuntu
 *
 */
public class Packager implements Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = 8538682016171810928L;

	/**
	 * 打包userInfor中的数据
	 * @param rs
	 * @return
	 */
	public static UserInfor packUSerInfor(ResultSet rs)
	{
		UserInfor userInfor = new UserInfor();
		try
		{
			userInfor.setUserID(rs.getInt("UserID"));
			userInfor.setPeopleName(rs.getString("PeopleName"));
			userInfor.setLoginName(rs.getString("LoginName"));
			userInfor.setPassword(rs.getString("Password"));
			userInfor.setPhone(rs.getString("Phone"));
			userInfor.setEmail(rs.getString("Email"));
			userInfor.setGender(rs.getBoolean("Gender"));
			userInfor.setQQ(rs.getString("QQ"));
			userInfor.setWeChat(rs.getString("WeChat"));
			userInfor.setStatus(rs.getInt("Status"));
			userInfor.setUserTypeID(rs.getInt("UserTypeID"));
			userInfor.setAppBookNumber(rs.getInt("AppBookNumber"));
			userInfor.setBroBookNumber(rs.getInt("BroBookNumber"));
		}
		catch(SQLException e)
		{
			System.out.println("对USerInfor进行打包发生错误");
			e.printStackTrace();
		}
		return userInfor;
	}
	
	/**
	 * 打包UserConnection中的数据
	 * @param rs
	 * @return
	 */
	public static UserConnection packUserConnection(ResultSet rs)
	{
		UserConnection userConnection = new UserConnection();
		try
		{
			userConnection.setUserConnectionID(rs.getInt("UserConnectionID"));
			userConnection.setUserInforID(rs.getInt("UserID"));
			userConnection.setAdminID(rs.getInt("AdminID"));
			userConnection.setStudentID(rs.getInt("StudentID"));
			userConnection.setTeacherID(rs.getInt("TeacherID"));
		}
		catch(SQLException e)
		{
			System.out.println("对UserConnection信息进行打包发生错误");
			e.printStackTrace();
		}
		return userConnection;
	}
	
	/**
	 * 打包管理员中的信息
	 * @param rs
	 * @param userInfor
	 * @param userConnection
	 * @return
	 */
	public static Admin packAdmin(ResultSet rs, UserInfor userInfor, UserConnection userConnection)
	{
		Admin admin = new Admin();
		try
		{
			admin.setIntro(rs.getString("PeopleIntro"));
			admin.setAdminID(rs.getInt("AdminID"));
			admin.setIDCards(rs.getString("IDCards"));
			admin.setIntro(rs.getString("PeopleIntro"));
			admin.setUserConnection(userConnection);
			admin.setUserInfor(userInfor);
		}
		catch(SQLException e)
		{
			System.out.println("对管理员信息进行打包发生错误");
			e.printStackTrace();
		}
		return admin;
	}
	
	/**
	 * 打包学生中的信息
	 * @param rs
	 * @param userInfor
	 * @param userConnection
	 * @return
	 */
	public static Student packStudent(ResultSet rs, UserInfor userInfor, UserConnection userConnection)
	{
		Student student = new Student();
		try
		{
			student.setStudentID(rs.getInt("StudentID"));
			student.setIDCards(rs.getString("IDCards"));
			student.setStudentCredit(rs.getInt("StudentCreditID"));
			student.setExperience(rs.getInt("Experience"));
			student.setBorCredit(rs.getInt("BorCredit"));
			student.setCredit(rs.getInt("Credit"));
			student.setUserConnection(userConnection);
			student.setUserInfor(userInfor);
		}
		catch(SQLException e)
		{
			System.out.println("对Student信息进行打包发生错误");
			e.printStackTrace();
		}
		return student;
	}
	
	/**
	 * 打包老师中的信息
	 * @param rs
	 * @param userInfor
	 * @param userConnection
	 * @return
	 */
	public static Teacher packTeacher(ResultSet rs, UserInfor userInfor, UserConnection userConnection)
	{
		Teacher teacher = new Teacher();
		try
		{
			teacher.setTeacherID(rs.getInt("TeacherID"));
			teacher.setIDCards(rs.getString("IDCards"));
			teacher.setTeacherCredit(rs.getInt("TeacherCreditID"));
			teacher.setExperience(rs.getInt("Experience"));
			teacher.setBorCredit(rs.getInt("BorCredit"));
			teacher.setCredit(rs.getInt("Credit"));
			teacher.setUserConnection(userConnection);
			teacher.setUserInfor(userInfor);
		}
		catch(SQLException e)
		{
			System.out.println("对老师信息进行打包发生了错误");
			e.printStackTrace();
		}
		return teacher;
	}
	
	/**
	 * 对BookInfor信息进行打包
	 * @param rs
	 * @return
	 */
	public static BookInfor packBookInfor(ResultSet rs)
	{
		BookInfor bookInfor = new BookInfor();
		try
		{
			bookInfor.setBookID(rs.getInt("BookID"));
			bookInfor.setBookClassfication(rs.getInt("BookClassfincationID"));
			bookInfor.setBookName(rs.getString("BookName"));
			bookInfor.setBookauthor(rs.getString("BookAuthor"));
			bookInfor.setBookintro(rs.getString("BookIntro"));
			bookInfor.setWordNumber(rs.getString("WordNumber"));
			bookInfor.setBookNumber(rs.getInt("BookNumber"));
			bookInfor.setStatus(rs.getInt("Status"));
		}
		catch(SQLException e)
		{
			System.out.println("对BookInfor进行打包发生错误");
			e.printStackTrace();
		}
		return bookInfor;
	}
	
	/**
	 * 对借阅信息进行打包
	 * @param rs
	 * @param userInfor
	 * @param bookInfor
	 * @return
	 */
	public static BorrowInfor packBorrowInfor(ResultSet rs, UserInfor userInfor, BookInfor bookInfor)
	{
		BorrowInfor borrowInfor = new BorrowInfor();
		try
		{
			borrowInfor.setUserID(rs.getInt("UserID"));
			borrowInfor.setBookID(rs.getInt("BookID"));
			borrowInfor.setStart(rs.getDate("Start"));
			borrowInfor.setFinish(rs.getDate("Finish"));
			borrowInfor.setOvertime(rs.getBoolean("Overtime"));
			borrowInfor.setUserInfor(userInfor);
			borrowInfor.setBookInfor(bookInfor);
		}
		catch(SQLException e)
		{
			System.out.println("对BorrowInfor进行打包发生错误");
			e.printStackTrace();
		}
		return borrowInfor;
	}
	
	/**
	 * 对书籍类型进行打包
	 * @param rs
	 * @return
	 */
	public static BookClassfication packBookClassfication(ResultSet rs)
	{
		BookClassfication bookClassfication = new BookClassfication();
		try
		{
			bookClassfication.setClassficationID(rs.getInt("ClassficationID"));
			bookClassfication.setClassficationName(rs.getString("ClassficationName"));
		}
		catch(SQLException e)
		{
			System.out.println("对BookClassfication进行打包发生错误");
			e.printStackTrace();
		}
		return bookClassfication;
	}
	
	/**
	 * 对申请信息进行打包
	 * @param rs
	 * @return
	 */
	public static ApplyInfor packApplyInfor(ResultSet rs, UserInfor userInfor)
	{
		ApplyInfor applyInfor = new ApplyInfor();
		try
		{
			applyInfor.setUserID(rs.getInt("UserID"));
			applyInfor.setBookClassfication(rs.getInt("BookClassfication"));
			applyInfor.setBookName(rs.getString("BookName"));
			applyInfor.setBookAuthor(rs.getString("BookAuthor"));
			applyInfor.setDate(rs.getDate("Apply"));
			applyInfor.setUserInfor(userInfor);
		}
		catch(SQLException e)
		{
			System.out.println("对ApplyInfor进行打包发生错误");
			e.printStackTrace();
		}
		return applyInfor;
	}
}
