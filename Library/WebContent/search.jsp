<%@page import="com.Library.globle.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.Library.bean.PageBean"%>
<%@page import="com.Library.entity.BookInfor"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/style2.css" rel='stylesheet' type='text/css' />
<script src="https://cdn.bootcss.com/jquery/1.11.2/jquery.js"></script>
<title>SearchBookInfor</title>
</head>
<body>
<style>
	#st-right {
		position: fixed;
		right: 0;
		top: 0px;
		padding: 10px;
		background-color: #262f3c;
		color: #fff;
		border-radius: 2px 0 0 2px;
		text-align: right;
		z-index: 10;
	}
	#st-right a{
		color: #fff;
	}
	#st-right button {
		height: 25px;
		border: 0 none;	
		margin-top: 10px;
		border-radius: 2px;
		background-color: #ff8080;
		color: #fff;
		cusor: pointer;
		transition: all .3s;
		
	}
	#st-right button:hover {
		background-color: transparent;
		background-color: #ff3636;
	}
	#author{
		position:absolute;
		width:100px;
		height:55px;
		opacity:0.5;
		margin-left:-380px;
		margin-top:-58px;
		border-radius:8px;
		outline:none;
	}
	
	#infor{
		margin-bottom:50px;
		margin-left:200px;
		text-align: left;
	}
</style>
</head>
<body>
<!--SIGN UP-->
   <div id="st-right">
   	<u><a href="<%=request.getContextPath() + "/infor.jsp"%>">Individual Information</a></u>
   	<form action="logout.jhtml">
   		<button>Sign Out</button>
   	</form>
   </div>
  <div class="search" style="position: fixed; top: 0; left: 0; width: 100%; margin: 0; background: rgba(24, 24, 25, 1);">
	<i> </i>
	<div class="s-bar">
	   <form method="get" action="search.html">
		<input type="text" name="searchInfor" value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">	
		
		<input type="submit"  value="Search"/>
		<input type="hidden" name="bookType">
		<input type="hidden" name="page">
	
		
		<select name="author" id="author">
  			<option value="">Globle</option>
  			<option value="YES">Author</option>
		</select>
	  </form>
	</div>
</div>
<div style="margin-top: 80px;">
	<%
		PageBean<BookInfor> pageBean = (PageBean<BookInfor>)request.getAttribute(Constant.PAGE_BEAN);
		List<BookInfor> bookInfors = pageBean.getList();
		BookInfor bI = (BookInfor) request.getAttribute(Constant.BOOK_INFOR);
	%>
	<%if(bI != null){ %>
		<div id="infor">
				<a style="color:red" href="book_detail.jhtml?bookID=<%=bI.getBookID() %>"><strong><font size="6"><%=bI.getBookName() %></font></strong></a>
		   		<a style="color:white">作者：<font size="4"><%=bI.getBookauthor()%></font></a>
		   		<p style="color:#12ff12; margin-right:250px;">简介：<font><%=bI.getBookintro() %></font></p>
		   		<p style="color:#ff9625">字数：<font><%=bI.getWordNumber() %></font></p>
		   		<p style="color:#00c2ff">剩余数量：<font><%=bI.getBookNumber() %></font></p>
		</div>
	<%} %>
	<%if(bookInfors != null){
		for(BookInfor bookInfor:bookInfors){%>
			<div id="infor">
		   		<a style="color:red" href="book_detail.jhtml?bookID=<%=bookInfor.getBookID() %>"><strong><font size="6"><%=bookInfor.getBookName() %></font></strong></a>
		   		<a style="color:white">作者：<font size="4"><%=bookInfor.getBookauthor()%></font></a>
		   		<p style="color:#12ff12; margin-right:250px;">简介：<font><%=bookInfor.getBookintro() %></font></p>
		   		<p style="color:#ff9625">字数：<font><%=bookInfor.getWordNumber() %></font></p>
		   		<p style="color:#00c2ff">剩余数量：<font><%=bookInfor.getBookNumber() %></font></p>
			</div> 
	<%	
		}
	} 
	%>
	<div style="text-align: center;">
		<!-- 如果是第一页，则有下一页的链接而没有上一页的连接 -->
		<%
			if(pageBean.getPageNum() == 1 && pageBean.getTotalPage() != 0 && pageBean.getTotalPage() != 1)
			{
				for(int i = pageBean.getStart(); i<= pageBean.getEnd(); i++)
				{
					if(pageBean.getPageNum() == i)
					{
		%>
						<a style="color:white"><%=i%></a>
		<%
					}
					if(pageBean.getPageNum() != i)
					{
		%>
						<a style="color:yellow" href="search.html?page=<%=i%>&searchInfor=<%=request.getParameter("searchInfor")%>&author=<%=request.getParameter("author")%>&bookType=<%=request.getParameter("bookType")%>"><%=i%></a>
		<%
					}
				}
		%>
				<a style="color:yellow" href="search.html?page=<%=pageBean.getPageNum()+1%>&searchInfor=<%=request.getParameter("searchInfor")%>&author=<%=request.getParameter("author")%>&bookType=<%=request.getParameter("bookType")%>">下一页</a>
		<%
			}
		%>
		
		<!-- 不是第一页，也不是最后一页，则有上一页以及下一页的链接 -->
		<%
			if(pageBean.getPageNum() >1 && pageBean.getPageNum() < pageBean.getTotalPage())
			{
		%>
				<a href="search.html?page=<%=pageBean.getPageNum()-1%>&searchInfor=<%=request.getParameter("searchInfor")%>&author=<%=request.getParameter("author")%>&bookType=<%=request.getParameter("bookType")%>" style="color:yellow">上一页</a>
		<%
				for(int i = pageBean.getStart(); i <= pageBean.getEnd();i++)
				{
					if(pageBean.getPageNum() == i)
					{
		%>
						<a style="color:white"><%=i%></a>
		<%
					}
					if(pageBean.getPageNum() != i)
					{
		%>
						<a style="color:yellow" href="search.html?page=<%=i%>&searchInfor=<%=request.getParameter("searchInfor")%>&author=<%=request.getParameter("author")%>&bookType=<%=request.getParameter("bookType")%>"><%=i%></a>
		<%
					}
				}
		%>
				<a style="color:yellow" href="search.html?page=<%=pageBean.getPageNum()+1%>&searchInfor=<%=request.getParameter("searchInfor")%>&author=<%=request.getParameter("author")%>&bookType=<%=request.getParameter("bookType")%>">下一页</a>			
		<%
			}
		%>
		
		<!-- 如果是最后一页，则没有下一页的链接 -->
		<%
			if(pageBean.getPageNum() == pageBean.getTotalPage() && pageBean.getTotalPage() != 0 && pageBean.getTotalPage() != 1)
			{
		%>
				<a href="search.html?page=<%=pageBean.getPageNum()-1%>&searchInfor=<%=request.getParameter("searchInfor")%>&author=<%=request.getParameter("author")%>&bookType=<%=request.getParameter("bookType")%>" style="color:yellow">上一页</a>
		<%
				for(int i = pageBean.getStart(); i <= pageBean.getEnd();i++)
				{
					if(pageBean.getPageNum() == i)
					{
		%>
						<a style="color:white"><%=i%></a>
		<%
					}
					if(pageBean.getPageNum() != i)
					{
		%>
						<a style="color:yellow" href="search.html?page=<%=i%>&searchInfor=<%=request.getParameter("searchInfor")%>&author=<%=request.getParameter("author")%>&bookType=<%=request.getParameter("bookType")%>"><%=i%></a>
		<%
					}
				}
			}
		%>

		<!-- 尾页 -->
		<%if(pageBean.getTotalPage() != 0 && pageBean.getTotalPage() != 1){ %>
			<a style="color:yellow" href="search.html?page=<%=pageBean.getTotalPage()%>&searchInfor=<%=request.getParameter("searchInfor")%>&author=<%=request.getParameter("author")%>&bookType=<%=request.getParameter("bookType")%>">尾页</a>
		<%} %>
	</div>
</div>
</body>
</html>