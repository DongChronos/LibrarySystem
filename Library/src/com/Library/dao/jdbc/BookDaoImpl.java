package com.Library.dao.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Library.dao.BookDao;
import com.Library.entity.BookInfor;
import com.Library.utils.Packager;

/**
 * Book的数据库操作
 * @author ubuntu
 *
 */
public class BookDaoImpl extends JDBCBase implements Serializable, BookDao {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = 4791916283287226685L;

	@Override
	public BookInfor getBookByID(int bookID) {
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookInfor bookInfor = null;
		
		String sql = "select * from BookInfor "
				+"WHERE BookID=?";
		Object[] parma = {bookID};
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps, parma);
			if(rs.next())
			{
				bookInfor = Packager.packBookInfor(rs);
			}
		}
		catch(SQLException e)
		{			
			System.out.println("通过书籍ID号获取书籍信息发生错误");
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return bookInfor;
	}
	
	@Override
	public BookInfor getBookByName(String condition) {
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookInfor bookInfor = null;
		
		//对Book信息进行打包
		String sql = "SELECT * FROM BookInfor bi"
				+ " WHERE bi.BookName=?";
		Object[] parma = {condition};
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps, parma);
			if(rs.next())
			{
				bookInfor = Packager.packBookInfor(rs);
			}
		}
		catch(SQLException e)
		{			
			System.out.println("通过书名获取书籍信息发生错误");
		}
		finally
		{
			JDBCUtil.close(rs,ps,conn);
		}
		return bookInfor;
	}

	@Override
	public void save(BookInfor bookInfor) {
		String sql = "INSERT INTO BookInfor(BookClassfincationID, BookName, BookAuthor, BookIntro, WordNumber, BookNumber, Status) VALUES(?,?,?,?,?,?,?)";
		Object[] BIParam = 
			{
					bookInfor.getBookClassfication(),
					bookInfor.getBookName(),
					bookInfor.getBookauthor(),
					bookInfor.getBookintro(),
					bookInfor.getWordNumber(),
					bookInfor.getBookNumber(),
					bookInfor.getStatus()
			};
		
		saveOrUpdateOrDelete(sql, BIParam);
		
		System.out.println("保存bookInfor信息完成");
	}

	@Override
	public void update(BookInfor bookInfor) {
		StringBuilder sql = new StringBuilder("UPDATE BookInfor SET BookClassfincationID=?, BookName=?, BookAuthor=?, BookIntro=?, WordNumber=?, BookNumber=?, Status=?");
		ArrayList<Object> biList = new ArrayList<Object>();
		biList.add(bookInfor.getBookClassfication());
		biList.add(bookInfor.getBookName());
		biList.add(bookInfor.getBookauthor());
		biList.add(bookInfor.getBookintro());
		biList.add(bookInfor.getWordNumber());
		biList.add(bookInfor.getBookNumber());
		biList.add(bookInfor.getStatus());
		
		sql.append(" WHERE BookID=?");
		biList.add(bookInfor.getBookID());
		System.out.println("the bookInfor sql of update: "+ sql.toString());
		
		Object[] BIparam = biList.toArray();
		saveOrUpdateOrDelete(sql.toString(), BIparam);
	}

	@Override
	public List<BookInfor> getAllBookInfor() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn = JDBCUtil.getConnection();
		
		String sql  = "SELECT * FROM BookInfor";
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("获取所有书名发生错误");
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;
	}

	@Override
	public List<BookInfor> getPaginationBookInfor(int startIndex, int maxCount)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM BookInfor";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps, startIndex, maxCount);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("分页获取书籍信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;		
	}
	
	@Override
	public List<BookInfor> getBookInforbyIntro(String condition) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM BookInfor"
				+ "WHERE BookIntro LIKE '%" + condition + "%'";
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("通过简介获取信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;
	}

	@Override
	public List<BookInfor> getBookInforByAuthor(String condition) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM BookInfor WHERE BookAuthor='" + condition + "'";
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("通过作者获取BookInfor发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;
	}

	@Override
	public List<BookInfor> getBookInforByClassfication(int bookType) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM BookInfor"
				+" WHERE BookClassfincationID=" + bookType;
		
		try
		{
			ps = conn.prepareStatement(sql);
			rs = query(ps);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("通过书籍类型获取BookInfor发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;
	}
	
	@Override
	public List<BookInfor> getPaginationBookInforType(int bookType, int startIndex, int maxCount)

	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn = JDBCUtil.getConnection();
		
		String sql = "SELECT * FROM BookInfor"
				+" WHERE BookClassfincationID=" + bookType;
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = query(ps,startIndex, maxCount);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("通过书籍类型获取BookInfor发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;
	}
	
	public List<BookInfor> getPaginationBookInforByIntro(String condition, int startIndex, int maxCount)
	{
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql = "SELECT * FROM BookInfor bi WHERE bi.BookIntro LIKE '%"+ condition+ "%'";
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=query(ps, startIndex, maxCount);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("分页方式通过简介获取信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;
	}
	
	public List<BookInfor> getPaginationBookInforByIntroType(int bookType, String condition, int startIndex, int maxCount)
	{
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql = "SELECT * FROM BookInfor bi WHERE bi.BookClassfincationID="+bookType+" bi.BookIntro LIKE '%"+ condition+ "%'";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=query(ps, startIndex, maxCount);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("分页方式通过类型以及简介获取信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;
	}

	public List<BookInfor> getPaPaginationBookInforByAuthor(String condition, int startIndex, int maxCount)
	{
		List<BookInfor> bookInfors = new ArrayList<BookInfor>();
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql = "SELECT * FROM BookInfor WHERE BookAuthor='" + condition + "'";
		
		try
		{
			conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=query(ps, startIndex, maxCount);
			while(rs.next())
			{
				bookInfors.add(Packager.packBookInfor(rs));
			}
		}
		catch(SQLException e)
		{
			System.out.println("分页方式通过作者获取信息发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return bookInfors;
	}
	
	@Override
	public int getRowNumOfInfor() {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BookInfor";
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
			System.out.println("获取数据库书籍总条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

	@Override
	public int getRowNumOfBookType(int bookType) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BookInfor WHERE BookClassfincationID="+bookType;
		
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
			System.out.println("获取数据库特定类型书籍总条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

	@Override
	public int getRowNumOfIntro(String condition) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BookInfor WHERE BookIntro LIKE '%"+condition+"%'";
		
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
			System.out.println("根据简介获取数据库书籍总条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

	@Override
	public int getRowNumOfIntrotype(int bookType, String condition) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BookInfor WHERE BookClassfincationID="+bookType+" AND BookIntro LIKE '%"+condition+"%'";
		
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
			System.out.println("根据简介以及书类型获取数据库书籍总条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}
	
	@Override
	public int getRowNumOfAuthor(String condition) {
		Connection conn=JDBCUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int RowNum = 0;
		
		String sql = "SELECT COUNT(*) FROM BookInfor WHERE BookAuthor='"+condition+"'";
		
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
			System.out.println("根据作者获取数据库书籍总条数发生错误");
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.close(rs, ps, conn);
		}
		return RowNum;
	}

}
