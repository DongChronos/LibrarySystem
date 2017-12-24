<%@page import="com.Library.dao.jdbc.ApplyBookDaoImpl"%>
<%@page import="com.Library.dao.ApplyBookDao"%>
<%@page import="com.Library.entity.ApplyInfor"%>
<%@page import="com.Library.entity.BorrowInfor"%>
<%@page import="java.util.List"%>
<%@page import="com.Library.utils.Utils"%>
<%@page import="com.Library.dao.jdbc.SearchBookDaoImpl"%>
<%@page import="com.Library.dao.SearchBookDao"%>
<%@page import="com.Library.entity.UserInfor"%>
<%@page import="com.Library.globle.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="author" content="Dennis Ji" /> 
<meta name="keyword" content="Metro, Metro UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina" /> 
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.min.css" rel="stylesheet" /> 
<link id="base-style" href="css/style.css" rel="stylesheet" /> 
<link id="base-style-responsive" href="css/style-responsive.css" rel="stylesheet" /> 
<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&amp;subset=latin,cyrillic-ext,latin-ext" rel="stylesheet" type="text/css" /> 
<link rel="shortcut icon" href="img/favicon.ico" />
<script src="https://cdn.bootcss.com/jquery/1.11.2/jquery.js"></script>
<title>Apply</title>
</head>
<body>
<style>
	#infor{
		margin-top:50px;
		margin-bottom:50px;
		margin-left:100px;
		text-align: left;
		width:80%;
	}
	
	#applyinfor{
		text-align: center;
		margin-top: 200px;
	}
	
	#sub{
		margin-top:-10px;
	}
</style>

<%
	Object object = request.getSession().getAttribute(Constant.USER_KEY);
	Object userType = request.getSession().getAttribute(Constant.USER_TYPE);
	UserInfor userInfor = Utils.getUserInfor(object);
	ApplyBookDao applyBookDao = new ApplyBookDaoImpl();
	List<ApplyInfor> applyInfors = applyBookDao.getApplyInforByUserID(userInfor.getUserID());
%>

<div class="navbar"> 
   <div class="navbar-inner"> 
    <div class="container-fluid"> 
     <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> 
     <a class="brand" href="user_book.jsp"><span>User_Book</span></a> 
     <!-- start: Header Menu --> 
     <div class="nav-no-collapse header-nav"> 
      <ul class="nav pull-right"> 
       <li class="dropdown"> <a class="btn dropdown-toggle" data-toggle="dropdown" href="#"> <i class="halflings-icon white user"></i><%=userInfor.getPeopleName()%><span class="caret"></span> </a> 
        <ul class="dropdown-menu"> 
         <li class="dropdown-menu-title"> <span>Account Settings</span> </li> 
         <li><a href="infor.jsp"><i class="halflings-icon user"></i> Profile</a></li> 
         <li><a href="logout.jhtml"><i class="halflings-icon off"></i> Logout</a></li> 
        </ul> </li> 
      </ul> 
     </div> 
    </div> 
   </div> 
  </div> 
  <!-- start: Header --> 
  <div class="container-fluid-full"> 
   <div class="row-fluid"> 
    <!-- start: Main Menu --> 
    <div id="sidebar-left" class="span2"> 
     <div class="nav-collapse sidebar-nav"> 
      <ul class="nav nav-tabs nav-stacked main-menu"> 
       <li><a href="user_book.jsp"><i class="icon-dashboard"></i><span class="hidden-tablet"> User_Book</span></a></li>
       <li><a href="apply.jsp"><i class="icon-dashboard"></i><span class="hidden-tablet"> Apply_Book</span></a></li>
       <li><a href="index.jsp"><i class="icon-dashboard"></i><span class="hidden-tablet"> Back</span></a></li>  
      </ul> 
     </div> 
    </div> 
    <div id="content" class="span10"> 
    <div>
	   <form method="get" action="apply.html" id="applyinfor">
		<input type="text" name="bookName" value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">	
		<select name="bookType">
  			<option value="1">玄幻</option>
  			<option value="2">奇幻</option>
  			<option value="3">武侠</option>
  			<option value="4">仙侠</option>
  			<option value="5">都市</option>
  			<option value="6">现实</option>
  			<option value="7">军事</option>
  			<option value="8">历史</option>
  			<option value="9">游戏</option>
  			<option value="10">体育</option>
  			<option value="11">科幻</option>
  			<option value="12">灵异</option>
		</select>
		<input id="sub" type="submit" class="btn btn-primary" value="Apply"/>
	  </form>
	   
	   <%
	   		if(applyInfors.size() > 0)
	   		{
	   %>
	   		<table border="1" id="infor">
	 		<tr>
	 			<th style="color:green; margin-top:8px;"><font size="4">RealName</font></th>
	 			<th style="color:green; margin-top:8px;"><font size="4">BookName</font></th>
    			<th style="color:green; margin-top:8px;"><font size="4">Phone</font></th>
	 		</tr>
	   <%
	   		for(ApplyInfor applyInfor:applyInfors)
	   		{
	   %>
	   			<tr>
	   				<th><font size="4"><%=userInfor.getPeopleName()%></font></th>
	   				<th><font size="4"><%=applyInfor.getBookName()%></font></th>
	 				<th><font size="4"><%=userInfor.getPhone()%></font></th>
	   			</tr>
	   <%
	   		}
	   %>
	 		</table>
	   <%
	   		}
	   %>
	   
	</div>
    </div>
    <script src="js/jquery-1.9.1.min.js"></script> 
    <script src="js/jquery-migrate-1.0.0.min.js"></script> 
    <script src="js/jquery-ui-1.10.0.custom.min.js"></script> 
    <script src="js/jquery.ui.touch-punch.js"></script> 
    <script src="js/modernizr.js"></script> 
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/jquery.cookie.js"></script> 
    <script src="js/fullcalendar.min.js"></script> 
    <script src="js/jquery.dataTables.min.js"></script> 
    <script src="js/excanvas.js"></script> 
    <script src="js/jquery.flot.js"></script> 
    <script src="js/jquery.flot.pie.js"></script> 
    <script src="js/jquery.flot.stack.js"></script> 
    <script src="js/jquery.flot.resize.min.js"></script> 
    <script src="js/jquery.chosen.min.js"></script> 
    <script src="js/jquery.uniform.min.js"></script> 
    <script src="js/jquery.cleditor.min.js"></script> 
    <script src="js/jquery.noty.js"></script> 
    <script src="js/jquery.elfinder.min.js"></script> 
    <script src="js/jquery.raty.min.js"></script> 
    <script src="js/jquery.iphone.toggle.js"></script> 
    <script src="js/jquery.uploadify-3.1.min.js"></script> 
    <script src="js/jquery.gritter.min.js"></script> 
    <script src="js/jquery.imagesloaded.js"></script> 
    <script src="js/jquery.masonry.min.js"></script> 
    <script src="js/jquery.knob.modified.js"></script> 
    <script src="js/jquery.sparkline.min.js"></script> 
    <script src="js/counter.js"></script> 
    <script src="js/retina.js"></script> 
    <script src="js/custom.js"></script> 
    <!-- end: JavaScript-->  
   </div>
  </div>
</body>
</html>