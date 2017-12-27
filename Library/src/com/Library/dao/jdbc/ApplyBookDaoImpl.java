package com.Library.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Library.dao.ApplyBookDao;
import com.Library.entity.ApplyInfor;
import com.Library.utils.Packager;

public class ApplyBookDaoImpl extends JDBCBase implements ApplyBookDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7517402884571523067L;

	@Override
	public void InsertApply(int userID, String bookName, int bookClassfication, Date date, int applyNumber) {
		String sql = "INSERT INTO ApplyBook(UserID, BookClassfication, BookName, Apply) VALUES(?,?,?,?)";
		Object[] ABparam = 
			{
				userID,
				bookClassfication,
				bookName,
				date
			};
		saveOrUpdateOrDelete(sql, ABparam);
		System.out.println("保存ApplyBook信息完成");
		
		sql = "UPDATE UserInfor SET AppBookNumber = ? WHERE UserID=?";
		Object[] UIparam = 
			{
					applyNumber + 1,
					userID
			};
		saveOrUpdateOrDelete(sql, UIparam);
		System.out.println("保存UserInfor信息完成");

	}

	@Override
	public List<ApplyInfor> getApplyInforByUserID(int userID) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ApplyInfor> applyInfors = new ArrayList<ApplyInfor>();
		
		String sql = "select * from ApplyBook WHERE UserID="+userID;
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps);
			while(rs.next())
			{
				applyInfors.add(Packager.packApplyInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("根据UserID获取借书信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return applyInfors;
	}

	@Override
	public void deleteApply(int userID, String bookName, int applyNumber) {
		String sql = "DELETE ApplyBook WHERE UserID=? AND BookName=?";
		Object[] ABparam = 
			{
					userID,
					bookName
			};
		saveOrUpdateOrDelete(sql, ABparam);
		System.out.println("删除申请购书信息完成");
		
		sql = "UPDATE UserInfor SET AppBookNumber=? WHERE UserID=?";
		Object[] UIparam = 
			{
					applyNumber -1,
					userID
			};
		saveOrUpdateOrDelete(sql, UIparam);
		System.out.println("更新userInfor信息完成");
		
	}

}
