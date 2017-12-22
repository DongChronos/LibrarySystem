<%@page import="com.Library.entity.BookInfor"%>
<%@page import="com.Library.globle.Constant"%>
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
<title>bookDetails</title>
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
		margin-top:200px;
		margin-bottom:50px;
		margin-left:300px;
		text-align: left;
	}
	
	#borrowsub{
		margin-top:-10px;
		margin-left:900px;
		background: linear-gradient(to bottom, #8EDB15 0%,#8EDB15 37%,#8EDB15 66%,#8EDB15 100%);
		color:#fff;
		font-size:1em;
		font-weight: 500;
		padding:0.4em 0.5em;
		text-align: center;
		text-shadow: rgba(80, 80, 80, 0.08) 1px 1px, rgba(80, 80, 80, 0.07) 2px 2px, rgba(80, 80, 80, 0.09) 3px 3px, rgba(80, 80, 80, 0.04) 4px 4px, rgba(80, 80, 80, 0.06) 5px 5px;
		cursor:pointer;
		border: none;
		outline: none;
		-webkit-appearance: none;
		background: #7ac209;
		background: -moz-linear-gradient(top, #7ac209 0%, #7ac209 37%, #7ac209 66%, #7ac209 100%);
		background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#7ac209), color-stop(37%,#7ac209), color-stop(66%,#7ac209), color-stop(100%,#7ac209));
		background: -webkit-linear-gradient(top, #7ac209 0%,#7ac209 37%,#7ac209 66%,#7ac209 100%);
		background: -o-linear-gradient(top, #7ac209 0%,#7ac209 37%,#7ac209 66%,#7ac209 100%);
		background: -ms-linear-gradient(top, #7ac209 0%,#7ac209 37%,#7ac209 66%,#7ac209 100%);
		background: linear-gradient(to bottom, #8EDB15 0%,#8EDB15 37%,#8EDB15 66%,#8EDB15 100%);
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#7ac209', endColorstr='#7ac209',GradientType=0
	}
</style>
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
	   
	<%BookInfor bookInfor = (BookInfor) request.getAttribute(Constant.BOOK_INFOR); %>
	<%if(bookInfor != null){ %>
		<div id="infor">
			<a style="color:red" href="book_detail.jhtml?bookID=<%=bookInfor.getBookID() %>"><strong><font size="6"><%=bookInfor.getBookName() %></font></strong></a>
		   		<a style="color:white">作者：<font size="4"><%=bookInfor.getBookauthor()%></font></a>
		   		<p style="color:#12ff12; margin-right:250px;">简介：<font><%=bookInfor.getBookintro() %></font></p>
		   		<p style="color:#ff9625">字数：<font><%=bookInfor.getWordNumber() %></font></p>
		   		<p style="color:#00c2ff">剩余数量：<font><%=bookInfor.getBookNumber() %></font></p>
		   		<form action="borrow.jhtml?bookID=<%=bookInfor.getBookID() %>" method="post">
					<input id="borrowsub" type="submit"  value="Borrow"/>
				</form>
		</div>
	<%}%>
</body>
</html>