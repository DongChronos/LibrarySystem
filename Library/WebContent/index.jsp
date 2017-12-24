<%@page import="com.Library.dao.jdbc.BookDaoImpl"%>
<%@page import="com.Library.bean.LoginInfor"%>
<%@page import="com.Library.entity.BookClassfication"%>
<%@page import="java.util.List"%>
<%@page import="com.Library.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/style2.css" rel='stylesheet' type='text/css' />
<script src="https://cdn.bootcss.com/jquery/1.11.2/jquery.js"></script>
<title>Index</title>
<style>
	#st-right {
		position: fixed;
		right: 0;
		top: 50px;
		padding: 10px;
		background-color: #202223;
		color: #fff;
		border-radius: 2px 0 0 2px;
		text-align: right;
	}
	#st-right a{
		color: #fff;
	}
	#st-right button {
		height: 25px;
		border: 0 none;	
		margin-top: 5px;
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
	#content a {
	   color:yellow;
	}
</style>
</head>
<body>
<!--SIGN UP-->
   <h1><strong>Index</strong></h1> 
   <div id="st-right">
   	<u><a href="<%=request.getContextPath() + "/infor.jsp"%>">Individual Information</a></u><br>
   	<u><a href="<%=request.getContextPath() + "/user_book.jsp"%>">Borrow Information</a></u>
   	<form action="logout.jhtml">
   		<button>Sign Out</button>
   	</form>
   </div>
  <div class="search">
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
	  
	  <div id="content">
	  	<%
	  		BookDao bookDao = new BookDaoImpl();
	  		List<BookClassfication> bookClassfications = bookDao.getBookClassfication();
	  		for(BookClassfication bookType:bookClassfications)
	  		{
	  	%>
	  			<a href="<%=request.getContextPath() + "/search.html?page=&searchInfor=&author=&bookType=" + bookType.getClassficationID()%>"><%=bookType.getClassficationName() %></a>
	  	<%
	  		}
	  	%>
	  </div>
	</div>
</div>
  
    
</body>
</html>