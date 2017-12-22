package com.Library.dao.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Library.dao.SearchBookDao;
import com.Library.entity.BookInfor;
import com.Library.entity.BorrowInfor;
import com.Library.entity.UserInfor;
import com.Library.utils.Packager;

/**
 * 借书信息 数据库操作的实现
 * @author ubuntu
 *
 */

public class SearchBookDaoImpl extends JDBCBase implements SearchBookDao, Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = 3343161940871638295L;

	@Override
	public int getAllBorrowInfor() {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BorrowInfor";
		
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
			System.out.println("获取信息表条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

	@Override
	public int getBorrowInforByUser(String condition) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BorrowInfor bri,UserInfor ui WHERE bri.UserID=ui.UserID AND ui.PeopleName='"+condition+"'";
		
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
			System.out.println("(借阅人)获取信息表条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

	@Override
	public int getBorrowInforByBook(String condition) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BorrowInfor bri, BookInfor bi "
				+" WHERE bri.BookID=bi.BookID AND bi.BookName='"+condition+"'";
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				RowNum = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			System.out.println("(借阅的书籍书名)获取信息表条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

	@Override
	public int getBorrowInforBybookType(int bookType) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BorrowInfor Bri,BookInfor bi  WHERE bi.BookClassfincationID="+bookType+" AND bri.BookID=bi.BookID";
		
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
			System.out.println("(借阅的书籍类型)获取信息表条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}
	
	@Override
	public int getBorrowInforByMonth(String condition) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "select COUNT(*) from BorrowInfor WHERE Start LIKE '"+condition+"%'";
		

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
			System.out.println("(年月信息)获取信息表条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}
	
	@Override
	public int getBorrowInforByOvertime() {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT * FROM BorrowInfor WHERE Overtime=1";
		
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
			System.out.println("(超时)获取信息表条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}
	
	@Override
	public int getBorrowInforByPhone(String Phone) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM UserInfor ui, BorrowInfor bri WHERE ui.UserID=bri.UserID AND ui.Phone='"+Phone+"'";
		
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
			System.out.println("(电话号码)获取信息表条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

	@Override
	public List<BorrowInfor> getPaginationAllBorrowInfor(int startIndex, int pageSize) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		List<BorrowInfor> borrowInfors = new ArrayList<BorrowInfor>();
		
		String sql = "SELECT * FROM BorrowInfor bri, BookInfor bi, UserInfor ui "
				+ " WHERE bri.BookID=bi.BookID AND bri.UserID=ui.UserID";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps,startIndex, pageSize);
			while(rs.next())
			{
				bookInfor = Packager.packBookInfor(rs);
				userInfor = Packager.packUSerInfor(rs);
				borrowInfors.add(Packager.packBorrowInfor(rs, userInfor, bookInfor));
			}
		}
		catch(SQLException e)
		{
			System.out.println("分页获取所有的借阅表信息发生错误");
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfors;
	}

	@Override
	public List<BorrowInfor> getPaginationBorrowInforByUser(String condition, int startIndex, int pageSize) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		List<BorrowInfor> borrowInfors = new ArrayList<BorrowInfor>();
		
		String sql = "SELECT * FROM BorrowInfor bri, BookInfor bi, UserInfor ui "
				+ " WHERE bri.BookID=bi.BookID AND bri.UserID=ui.UserID AND ui.PeopleName='" +condition +"'";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps,startIndex, pageSize);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				bookInfor = Packager.packBookInfor(rs);
				borrowInfors.add(Packager.packBorrowInfor(rs, userInfor, bookInfor));
			}
		}
		catch(SQLException e)
		{
			System.out.println("(借阅人)分页获取借阅表信息发生错误");
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfors;
	}

	@Override
	public List<BorrowInfor> getPaginationBorrowInforByBook(String condition, int startIndex, int pageSize) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		List<BorrowInfor> borrowInfors = new ArrayList<BorrowInfor>();
		
		String sql = "SELECT * FROM BorrowInfor bri, BookInfor bi, UserInfor ui "
				+ " WHERE bri.BookID=bi.BookID AND bri.UserID=ui.UserID AND bi.BookName='"+ condition +"'";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps,startIndex, pageSize);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				bookInfor = Packager.packBookInfor(rs);
				borrowInfors.add(Packager.packBorrowInfor(rs, userInfor, bookInfor));
			}
		}
		catch(SQLException e)
		{
			System.out.println("(借阅的书)分页获取借阅表信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfors;
	}

	@Override
	public List<BorrowInfor> getPaginationBorrowInforBybookTye(int bookType, int startIndex, int pageSize) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		List<BorrowInfor> borrowInfors = new ArrayList<BorrowInfor>();
		
		String sql = "SELECT * FROM UserInfor ui, BorrowInfor Bri,BookInfor bi  WHERE bi.BookClassfincationID="+bookType+" AND bri.BookID=bi.BookID AND bri.UserID=ui.UserID";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps,startIndex, pageSize);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				bookInfor = Packager.packBookInfor(rs);
				borrowInfors.add(Packager.packBorrowInfor(rs, userInfor, bookInfor));
			}
		}
		catch(SQLException e)
		{
			System.out.println("(借阅的书)分页获取借阅表信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfors;
	}

	@Override
	public List<BorrowInfor> getPaginationBorrowInforByMonth(String condition, int startIndex, int pageSize) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		List<BorrowInfor> borrowInfors = new ArrayList<BorrowInfor>();
		
		String sql = "select * from BorrowInfor bri, BookInfor bi, UserInfor ui "
				+" WHERE bri.BookID=bi.BookID AND ui.UserID=bri.UserID AND bri.Start LIKE '"+condition+"%'";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps,startIndex, pageSize);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				bookInfor = Packager.packBookInfor(rs);
				borrowInfors.add(Packager.packBorrowInfor(rs, userInfor, bookInfor));
			}
		}
		catch(SQLException e)
		{
			System.out.println("(年月信息)分页获取借阅表信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfors;
	}

	@Override
	public List<BorrowInfor> getPaginationBorrowInforByOvertime(int startIndex, int pageSize) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		List<BorrowInfor> borrowInfors = new ArrayList<BorrowInfor>();
		
		String sql = "SELECT * FROM BorrowInfor bri, BookInfor bi, UserInfor ui WHERE bri.UserID=ui.UserID AND bri.BookID=bi.BookID AND bri.Overtime=1";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps,startIndex, pageSize);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				bookInfor = Packager.packBookInfor(rs);
				borrowInfors.add(Packager.packBorrowInfor(rs, userInfor, bookInfor));
			}
		}
		catch(SQLException e)
		{
			System.out.println("(超时)分页获取借阅表信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfors;
	}

	@Override
	public List<BorrowInfor> getPaginationBorrowInforByPhone(String Phone, int startIndex, int pageSize) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		List<BorrowInfor> borrowInfors = new ArrayList<BorrowInfor>();
		
		String sql = "SELECT * FROM UserInfor ui, BorrowInfor bri, BookInfor bi WHERE bri.BookID=bi.BookID AND ui.UserID=bri.UserID AND ui.Phone='"+Phone+"'";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps,startIndex, pageSize);
			while(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				bookInfor = Packager.packBookInfor(rs);
				borrowInfors.add(Packager.packBorrowInfor(rs, userInfor, bookInfor));
			}
		}
		catch(SQLException e)
		{
			System.out.println("(电话号码)分页获取借阅表信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfors;
	}

	public BorrowInfor getBorrowInforByPhoneAndBookID(String phone, String bookID)
	{
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		BorrowInfor borrowInfor = null;
		
		String sql = "SELECT * FROM UserInfor ui, BorrowInfor bri, BookInfor bi "
				+" WHERE ui.UserID=bri.UserID AND bi.BookID=bri.BookID AND ui.Phone='"+phone+"' AND bri.BookID='"+bookID+"'";
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			if(rs.next())
			{
				userInfor = Packager.packUSerInfor(rs);
				bookInfor = Packager.packBookInfor(rs);
				borrowInfor = Packager.packBorrowInfor(rs, userInfor, bookInfor);
			}
		}
		catch(SQLException e)
		{
			System.out.println("根据Phone以及BookID获取借书信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfor;
	}
	
	@Override
	public List<BorrowInfor> getAlltheBorrowInfor()
	{
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		BookInfor bookInfor = null;
		UserInfor userInfor = null;
		List<BorrowInfor> borrowInfors = new ArrayList<BorrowInfor>();
		
		String sql = "SELECT * FROM BorrowInfor bri, BookInfor bi, UserInfor ui "
				+ " WHERE bri.BookID=bi.BookID AND bri.UserID=ui.UserID";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps);
			while(rs.next())
			{
				bookInfor = Packager.packBookInfor(rs);
				userInfor = Packager.packUSerInfor(rs);
				borrowInfors.add(Packager.packBorrowInfor(rs, userInfor, bookInfor));
			}
		}
		catch(SQLException e)
		{
			System.out.println("分页获取所有的借阅表信息发生错误");
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return borrowInfors;
	}
}
