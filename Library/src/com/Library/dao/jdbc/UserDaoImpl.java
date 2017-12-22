package com.Library.dao.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Library.dao.UserDao;
import com.Library.entity.Admin;
import com.Library.entity.BorrowInfor;
import com.Library.entity.Student;
import com.Library.entity.Teacher;
import com.Library.entity.UserConnection;
import com.Library.entity.UserInfor;
import com.Library.utils.Packager;
import com.Library.utils.Utils;

/**
 * User的数据库操作
 * @author ubuntu
 *
 */

public class UserDaoImpl extends JDBCBase implements UserDao, Serializable {

	/**
	 * 序列号， 不可更改
	 */
	private static final long serialVersionUID = 9195307943554584345L;

	@Override
	public Object getUserByCondition(String condition, int userType) {
		System.out.println("OBJ" + userType);
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object object = null;
		UserInfor userInfor = null;
		UserConnection userConnection = null;
		
		/*
		 * 对Admin信息进行查询并且将信息进行打包存储在object对象中
		 */
		if(userType == 0)
		{
			String sql = "SELECT * FROM UserInfor ui, UserConnection uc, Admin ad "
					+ " WHERE ui.UserID=uc.UserID AND uc.AdminID=ad.AdminID AND ui.Email=? OR ui.Phone=?";
			Object[] parma = {condition, condition};
			try
			{
				ps = conn.prepareStatement(sql);
				rs = query(ps, parma);
				if(rs.next())
				{
					userInfor = Packager.packUSerInfor(rs);
					userConnection = Packager.packUserConnection(rs);
					object = Packager.packAdmin(rs, userInfor, userConnection);
				}
			}
			catch(SQLException e)
			{
				System.out.println("通过邮箱或手机号码查找Admin帐号发生错误");
				e.printStackTrace();
			}
			finally
			{
				JDBCUtil.close(rs,ps,conn); //关闭所有连接
			}
		}
		
		/*
		 * 对Student信息进行查询并且将信息进行打包存储在object对象中
		 */
		else if(userType == 1)
		{
			String sql = "SELECT * FROM UserInfor ui, UserConnection uc, StudentInfor si, StudentCredit sc "
					+ " WHERE ui.UserID=uc.UserID AND uc.StudentID=si.StudentID AND si.StudentCreditID=sc.StudentCreditID AND ui.Email=? OR ui.Phone=?";
			Object[] parma = {condition, condition};
			try
			{
				ps = conn.prepareStatement(sql);
				rs = query(ps, parma);
				if(rs.next())
				{
					userInfor = Packager.packUSerInfor(rs);
					userConnection = Packager.packUserConnection(rs);
					object = Packager.packStudent(rs, userInfor, userConnection);
				}
			}
			catch(SQLException e)
			{
				System.out.println("通过邮箱或手机号码查找Student帐号发生错误");
				e.printStackTrace();
			}
			finally
			{
				JDBCUtil.close(rs, ps, conn);
			}
		}
		
		/*
		 * 对Teacher信息进行查询并且将信息进行打包存储在object对象中
		 */
		else if(userType == 2)
		{
			String sql = "SELECT * FROM UserInfor ui, UserConnection uc, TeacherInfor ti, TeacherCredit tc "
					+ " WHERE ui.UserID=uc.UserID AND uc.TeacherID=ti.TeacherID AND ti.TeacherCreditID=tc.TeacherCreditID AND ui.Email=? OR ui.Phone=?";
			Object[] parma = {condition, condition};
			try
			{
				ps = conn.prepareStatement(sql);
				rs = query(ps, parma);
				if(rs.next())
				{
					userInfor = Packager.packUSerInfor(rs);
					userConnection = Packager.packUserConnection(rs);
					object = Packager.packTeacher(rs, userInfor, userConnection);
				}
			}
			catch(SQLException e)
			{
				System.out.println("通过邮箱或者手机号码查找Teacher帐号发生错误");
				e.printStackTrace();
			}
			finally
			{
				JDBCUtil.close(rs, ps, conn);
			}
		}
		return object;
	}

	public 	Object getUserByIDCards(String condition)
	{
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object object = null;
		UserInfor userInfor = null;
		UserConnection userConnection = null;
		
		String sql = "SELECT * FROM StudentInfor si,TeacherInfor ti,UserConnection uc,UserInfor ui,StudentCredit sc "
				+ " WHERE ui.UserID=uc.UserID AND uc.StudentID=si.StudentID AND ti.TeacherID=uc.TeacherID AND sc.StudentCreditID=si.StudentCreditID AND si.IDCards=? OR ti.IDCards=?";
		Object[] IDparma = {condition,condition};
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps,IDparma);
			if(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				userConnection = Packager.packUserConnection(rs);
				if(userInfor.getUserTypeID() == 0)
				{
					object = Packager.packAdmin(rs, userInfor, userConnection);
				}
				else if(userInfor.getUserTypeID() == 1)
				{
					object = Packager.packStudent(rs, userInfor, userConnection);
				}
				else if(userInfor.getUserTypeID() == 2)
				{
					object = Packager.packTeacher(rs, userInfor, userConnection);
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("通过IDcards获取对象发生错误");
		}
		return object;
	}
	
	@Override
	public void save(Object object) {
		UserInfor userInfor = Utils.getUserInfor(object);
		String sql = "INSERT INTO UserInfor(PeopleName,LoginName,Password,Phone,Email,Gender,QQ,WeChat,Status,UserTypeID,AppBookNumber,BroBookNumber) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] UIparma = 
			{
				userInfor.getPeopleName(),
				userInfor.getLoginName(),
				userInfor.getPassword(),
				userInfor.getPhone(),
				userInfor.getEmail(),
				userInfor.isGender(),
				userInfor.getQQ(),
				userInfor.getWeChat(),
				userInfor.getStatus(),
				userInfor.getUserTypeID(),
				userInfor.getAppBookNumber(),
				userInfor.getBroBookNumber()
			};
		saveOrUpdateOrDelete(sql, UIparma);
		System.out.println("保存UserInfor信息成功");
		
		//获取新增的UserInfor的ID
		sql = "SELECT UserID FROM UserInfor "
				+ "WHERE Phone="+userInfor.getPhone();
		int countUserInforID = getCount(sql);
		
		if(object instanceof Admin)
		{
			//保存Admin信息
			sql = "INSERT INTO Admin(IDCards,PeopleIntro) VALUES(?,?)";
			Object[] ADparma = 
				{
					((Admin) object).getIntro(),
					((Admin) object).getIDCards()
				};
			saveOrUpdateOrDelete(sql, ADparma);
			System.out.println("保存Admin信息成功");
			
			sql = "SELECT AdminID FROM Admin "
					+ "WHERE IDCards="+((Admin) object).getIDCards();
			int AdminID = getCount(sql);
			
			sql = "INSERT INTO UserConnection(UserID,AdminID) VALUES(?,?)";
			Object[] UCparma = 
				{
						countUserInforID,
						AdminID
				};
			saveOrUpdateOrDelete(sql, UCparma);
			System.out.println("保存userConnection信息成功");
		}
		
		else if(object instanceof Student)
		{
			//保存Student信息
			sql = "INSERT INTO StudentInfor(IDCards,StudentCreditID,Experience) VALUES(?,?,?)";
			Object[] STparma = 
				{
					((Student) object).getIDCards(),
					((Student) object).getStudentCredit(),
					((Student) object).getExperience()
				};
			saveOrUpdateOrDelete(sql, STparma);
			System.out.println("保存Student信息成功");
			
			sql = "SELECT StudentID FROM StudentInfor "
					+ "WHERE IDCards="+((Student) object).getIDCards();
			int StudentID = getCount(sql);
			
			sql = "INSERT INTO UserConnection(UserID,StudentID) VALUES(?,?)";
			Object[] UCparma = 
				{
						countUserInforID,
						StudentID
				};
			saveOrUpdateOrDelete(sql, UCparma);
			System.out.println("保存UserConnection信息成功");
		}
		
		else if(object instanceof Teacher)
		{
			//保存Teacher信息
			sql = "INSERT INTO TeacherInfor(IDCards,TeacherCreditID,Experience) VALUES(?,?,?)";
			Object[] TEparma = 
				{
					((Teacher) object).getIDCards(),
					((Teacher) object).getTeacherCredit(),
					((Teacher) object).getExperience()
				};
			saveOrUpdateOrDelete(sql, TEparma);
			System.out.println("保存Teacher信息成功");
			
			sql = "SELECT TeacherID FROM TeacherInfor "
					+ "WHERE IDCards="+((Teacher) object).getIDCards();
			int TeacherID = getCount(sql);
			
			sql = "INSERT INTO UserConnection(UserID,TeacherID) VALUES(?,?)";
			Object[] UCparma = 
				{
						countUserInforID,
						TeacherID
				};
			saveOrUpdateOrDelete(sql, UCparma);
			System.out.println("保存UserConnection信息成功");
		}
	}

	@Override
	public void update(Object object) {
		if(object instanceof Admin)
		{
			Admin admin = (Admin) object;
			//跟新Admin中的信息
			UserInfor userInfor = admin.getUserInfor();
			StringBuilder sql = new StringBuilder("UPDATE UserInfor SET LoginName=?, Phone=?, Email=?, Status=?, Password=?");
			ArrayList<Object> uiList = new ArrayList<Object>();
			uiList.add(userInfor.getLoginName());
			uiList.add(userInfor.getPhone());
			uiList.add(userInfor.getEmail());
			uiList.add(userInfor.getStatus());
			uiList.add(userInfor.getPassword());
			
			if(userInfor.getPeopleName() != null)
			{
				sql.append(", PeopleName=?");
				uiList.add(userInfor.getPeopleName());
			}
			if(userInfor.getQQ() != null)
			{
				sql.append(", QQ=?");
				uiList.add(userInfor.getQQ());
			}
			if(userInfor.getWeChat() != null)
			{
				sql.append(", WeChat=?");
				uiList.add(userInfor.getWeChat());
			}
			if(userInfor.getAppBookNumber() != 0)
			{
				sql.append(", AppBookNumber=?");
				uiList.add(userInfor.getAppBookNumber());
			}
			if(userInfor.getBroBookNumber() != 0)
			{
				sql.append(", BroBookNumber=?");
				uiList.add(userInfor.getBroBookNumber());
			}
			
			sql.append(" WHERE UserID=?");
			uiList.add(userInfor.getUserID());
			System.out.println("the sql of update: "+sql.toString());
			
			Object[] UIparma = uiList.toArray();
			saveOrUpdateOrDelete(sql.toString(), UIparma);
			
			sql.delete(0, sql.length());
			uiList.clear();
			
			//跟新Admin中的信息
			sql.append("UPDATE Admin SET PeopleIntro=?");
			uiList.add(admin.getIntro());
			sql.append(" WHERE AdminID=?");
			uiList.add(admin.getAdminID());
			System.out.println("the admin sql of update: "+sql.toString());
			Object[] ADparma = uiList.toArray();
			saveOrUpdateOrDelete(sql.toString(), ADparma);
		}
		
		else if(object instanceof Student)
		{
			Student student = (Student) object;
			//跟新UserInfor信息
			UserInfor userInfor = student.getUserInfor();
			StringBuilder sql = new StringBuilder("UPDATE UserInfor SET LoginName=?, Phone=?, Email=?, Status=?, Password=?");
			ArrayList<Object> uiList = new ArrayList<Object>();
			uiList.add(userInfor.getLoginName());
			uiList.add(userInfor.getPhone());
			uiList.add(userInfor.getEmail());
			uiList.add(userInfor.getStatus());
			uiList.add(userInfor.getPassword());
			
			if(userInfor.getPeopleName() != null)
			{
				sql.append(", PeopleName=?");
				uiList.add(userInfor.getPeopleName());
			}
			if(userInfor.getQQ() != null)
			{
				sql.append(", QQ=?");
				uiList.add(userInfor.getQQ());
			}
			if(userInfor.getWeChat() != null)
			{
				sql.append(", WeChat=?");
				uiList.add(userInfor.getWeChat());
			}
			if(userInfor.getAppBookNumber() != 0)
			{
				sql.append(", AppBookNumber=?");
				uiList.add(userInfor.getAppBookNumber());
			}
			if(userInfor.getBroBookNumber() != 0)
			{
				sql.append(", BroBookNumber=?");
				uiList.add(userInfor.getBroBookNumber());
			}
			sql.append(" WHERE UserID=?");
			uiList.add(userInfor.getUserID());
			System.out.println("the sql of update: "+sql.toString());
			
			Object[] UIparma = uiList.toArray();
			saveOrUpdateOrDelete(sql.toString(), UIparma);
			
			sql.delete(0, sql.length());
			uiList.clear();
			
			//跟新Student信息
			sql.append("UPDATE StudentInfor SET StudentCreditID=?, Experience=?");
			uiList.add(student.getStudentCredit());
			uiList.add(student.getExperience());
			sql.append(" WHERE StudentID=?");
			uiList.add(student.getStudentID());
			System.out.println("the student sql of update: "+sql.toString());
			Object[] STparma = uiList.toArray();
			saveOrUpdateOrDelete(sql.toString(), STparma);
		}
		
		else if(object instanceof Teacher)
		{
			Teacher teacher = (Teacher) object;
			//跟新UserInfor信息
			UserInfor userInfor = teacher.getUserInfor();
			StringBuilder sql = new StringBuilder("UPDATE UserInfor SET LoginName=?, Phone=?, Email=?, Status=?, Password=?");
			ArrayList<Object> uiList = new ArrayList<Object>();
			uiList.add(userInfor.getLoginName());
			uiList.add(userInfor.getPhone());
			uiList.add(userInfor.getEmail());
			uiList.add(userInfor.getStatus());
			uiList.add(userInfor.getPassword());
			
			if(userInfor.getPeopleName() != null)
			{
				sql.append(", PeopleName=?");
				uiList.add(userInfor.getPeopleName());
			}
			if(userInfor.getQQ() != null)
			{
				sql.append(", QQ=?");
				uiList.add(userInfor.getQQ());
			}
			if(userInfor.getWeChat() != null)
			{
				sql.append(", WeChat=?");
				uiList.add(userInfor.getWeChat());
			}
			if(userInfor.getAppBookNumber() != 0)
			{
				sql.append(", AppBookNumber=?");
				uiList.add(userInfor.getAppBookNumber());
			}
			if(userInfor.getBroBookNumber() != 0)
			{
				sql.append(", BroBookNumber=?");
				uiList.add(userInfor.getBroBookNumber());
			}
			sql.append(" WHERE UserID=?");
			uiList.add(userInfor.getUserID());
			System.out.println("the sql of update: "+sql.toString());
			
			Object[] UIparma = uiList.toArray();
			saveOrUpdateOrDelete(sql.toString(), UIparma);
			
			sql.delete(0, sql.length());
			uiList.clear();
			
			//跟新Teacher信息
			sql.append("UPDATE TeacherInfor SET TeacherCreditID=?, Experience=?");
			uiList.add(teacher.getTeacherCredit());
			uiList.add(teacher.getExperience());
			sql.append(" WHERE TeacherID=?");
			uiList.add(teacher.getTeacherID());
			System.out.println("the teacher sql of update: " + sql.toString());
			Object[] TEparma = uiList.toArray();
			saveOrUpdateOrDelete(sql.toString(), TEparma);
		}
	}

	@Override
	public Object getUserByID(int userID, int userType) {
		Object object = null;
		UserInfor userInfor = null;
		UserConnection userConnection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = JDBCUtil.getConnection();
		
		if(userType == 0)
		{
			//查找管理员对象
			String sql = "SELECT * FROM UserInfor ui,Admin ad,UserConnection uc "
					+ " WHERE ui.UserID=uc.UserID AND uc.AdminID=ad.AdminID AND ad.AdminID="+userID;
			try
			{
				ps = conn.prepareStatement(sql);
				rs = query(ps);
				if(rs.next())
				{
					userInfor = Packager.packUSerInfor(rs);
					userConnection = Packager.packUserConnection(rs);
					object = Packager.packAdmin(rs, userInfor, userConnection);
				}
			}
			catch(SQLException e)
			{
				System.out.println("通过ID查找Admin对象发生错误");
				e.printStackTrace();
			}
			finally
			{
				JDBCUtil.close(rs,ps,conn);
			}
		}
		else if(userType == 1)
		{
			//查找学生帐号
			String sql = "SELECT * FROM UserInfor ui,StudentInfor si,UserConnection uc "
					+ " WHERE ui.UserID=uc.UserID AND uc.StudentID=si.StudentID AND si.StudentID="+userID;
			try
			{
				ps = conn.prepareStatement(sql);
				rs = query(ps);
				if(rs.next())
				{
					userInfor = Packager.packUSerInfor(rs);
					userConnection = Packager.packUserConnection(rs);
					object = Packager.packStudent(rs, userInfor, userConnection);
				}
			}
			catch(SQLException e)
			{
				System.out.println("通过ID查找Student对象发生错误");
				e.printStackTrace();
			}
			finally
			{
				JDBCUtil.close(rs,ps,conn);
			}
			
		}
		else if(userType == 2)
		{
			//查找老师对象
			String sql = "SELECT * FROM UserInfor ui,TeacherInfor ti,UserConnection uc "
					+ " WHERE ui.UserID=uc.UserID AND uc.TeacherID=ti.TeacherID AND ti.TeacherID="+userID;
			try
			{
				ps = conn.prepareStatement(sql);
				rs = query(ps);
				if(rs.next())
				{
					userInfor = Packager.packUSerInfor(rs);
					userConnection = Packager.packUserConnection(rs);
					object = Packager.packTeacher(rs, userInfor, userConnection);
				}
			}
			catch(SQLException e)
			{
				System.out.println("通过ID查找Teacher对象发生错误");
				e.printStackTrace();
			}
			finally
			{
				JDBCUtil.close(rs,ps,conn);
			}
		}
		return object;
	}

	@Override
	public UserInfor getUserInforByUserInforID(int userInforID) {
		UserInfor userInfor = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM UserInfor WHERE UserID="+userInforID;
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			if(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
			}
		}
		catch(SQLException e)
		{
			System.out.println("通过UserID查找UserInfor对象发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return userInfor;
	}

	@Override
	public List<UserInfor> getAllUserInfor() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<UserInfor> userInfors = new ArrayList<UserInfor>();
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM UserInfor";
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				userInfors.add(Packager.packUSerInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("获取所有userInfor信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return userInfors;
	}

	@Override
	public List<Admin> getAdminByCondition(String condition) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfor userInfor = null;
		UserConnection userConnection = null;
		Connection conn = JDBCUtil.getConnection();
		List<Admin> admins = new ArrayList<Admin>();
		String sql = "SELECT * FROM UserInfor ui,Admin ad,UserConnection uc"
				+" WHERE ui.UserID=uc.UserID AND uc.AdminID=ad.AdminID";
		if(!"".equals(condition) && condition != null)
		{
			sql += " AND (ad.PeopleIntro LIKE '%"+ condition +"%') AND (ad.IDCards LIKE '%" + condition +"%')";
		}
		System.out.println("the List<Admin> sql of select: "+sql.toString());
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				userConnection = Packager.packUserConnection(rs);
				admins.add(Packager.packAdmin(rs, userInfor, userConnection));
			}
		}
		catch(SQLException e)
		{
			System.out.println("通过模糊检索Admin信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return admins;
	}

	@Override
	public List<Student> getStudentByCondition(String condition) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfor userInfor = null;
		UserConnection userConnection = null;
		List<Student> students = new ArrayList<Student>();
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM StudentInfor si,UserInfor ui, UserConnection uc"
				+" WHERE si.StudentID=uc.StudentID AND uc.UserID=ui.UserID";
		if(!"".equals(condition) && condition != null)
		{
			sql += " AND (si.Experience LIKE '%"+ condition +"%') AND (si.IDCards LIKE '%" + condition +"%') AND (si.Experience LIKE '%" + condition +"%')";
		}
		System.out.println("the List<Student> sql of select: "+sql.toString());
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				userConnection = Packager.packUserConnection(rs);
				students.add(Packager.packStudent(rs, userInfor, userConnection));
			}
		}
		catch(SQLException e)
		{
			System.out.println("模糊查找Student信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return students;
	}

	@Override
	public Student getStudentByPhone(String phone)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfor userInfor = null;
		UserConnection userConnection = null;
		Student student = null;
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "select * from StudentInfor si, UserInfor ui, UserConnection uc, StudentCredit sc "
				+" WHERE si.StudentID=uc.StudentID AND uc.UserID=ui.UserID AND sc.StudentCreditID=si.StudentCreditID AND ui.Phone='"+phone+"'";
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				userConnection = Packager.packUserConnection(rs);
				student = Packager.packStudent(rs, userInfor, userConnection);
			}
		}
		catch(SQLException e)
		{
			System.out.println("根据电话号码获取信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return student;
	}
	
	@Override
	public List<Teacher> getTeacherByCondition(String condition) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfor userInfor = null;
		UserConnection userConnection = null;
		List<Teacher> teachers = new ArrayList<Teacher>();
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM TeacherInfor ti,UserInfor ui, UserConnection uc, TeacherCredit tc "
				+" WHERE ti.TeacherID=uc.TeacherID AND uc.UserID=ui.UserID AND tc.TeacherCreditID=ti.TeacherCreditID";
		if(!"".equals(condition) && condition != null)
		{
			sql += " AND (ti.Experience LIKE '%"+ condition +"%') AND (ti.IDCards LIKE '%" + condition +"%') AND (ti.Experience LIKE '%" + condition +"%')";
		}
		System.out.println("the List<Teacher> sql of select: "+sql.toString());
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				userConnection = Packager.packUserConnection(rs);
				teachers.add(Packager.packTeacher(rs, userInfor, userConnection));
			}
		}
		catch(SQLException e)
		{
			System.out.println("模糊查找Teacher信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return teachers;
	}

	@Override
	public Teacher getTeacherByPhone(String phone)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserInfor userInfor = null;
		UserConnection userConnection = null;
		Teacher teacher = null;
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "select * from TeacherInfor ti, UserInfor ui, UserConnection uc, TeacherCredit tc "
				+" WHERE ti.TeacherID=uc.TeacherID AND uc.UserID=ui.UserID AND tc.TeacherCreditID=ti.TeacherCreditID AND ui.Phone='"+phone+"'";
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				userConnection = Packager.packUserConnection(rs);
				teacher = Packager.packTeacher(rs, userInfor, userConnection);
			}
		}
		catch(SQLException e)
		{
			System.out.println("根据电话号码获取信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return teacher;
	}
	
	@Override
	public int getFrozenAccount() {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM UserInfor WHERE Status=0";
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			if(rs.next())
			{
				RowNum = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			System.out.println("获取冻结帐号信息条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

	@Override
	public List<UserInfor> getFrozenAccount(int startIndex, int pageSize) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<UserInfor> userInfors = new ArrayList<UserInfor>();
		
		String sql = "SELECT * FROM UserInfor WHERE Status=0";
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				userInfors.add(Packager.packUSerInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("获取所有userInfor信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return userInfors;
	}
	
	@Override
	public void setFrozenAccount(BorrowInfor borrowInfor)
	{
		String sql = "UPDATE UserInfor SET Status=? WHERE UserID=?";
		Object[] fuiparam = 
			{
					0,
					borrowInfor.getUserID()
			};
		saveOrUpdateOrDelete(sql, fuiparam);
		System.out.println("对超时30天用户已经冻结账户");
	}
}
