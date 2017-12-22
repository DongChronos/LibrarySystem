<%@page import="com.Library.globle.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/style1.css" rel='stylesheet' type='text/css' />
<script src="https://cdn.bootcss.com/jquery/1.11.2/jquery.js"></script>
<title>After Borrow book</title>
</head>
<body>
	<style>
		#message{
			text-align:center;
			margin-top:200px;
		}
	</style>

	<div id="message">
		<p><font size="32" color="yellow"><%=request.getAttribute(Constant.BOOK_MESSAGE)%></font></p>
		<div style="margin-top:100px;"><a href="<%=request.getContextPath() %>/index.jsp"><font size="6" color="red">返回主页</font></a></div>
	</div>
</body>
</html>