<%@page import="com.Library.entity.BorrowInfor"%>
<%@page import="com.Library.globle.Constant"%>
<%@page import="com.Library.entity.*"%>
<%@page import="com.Library.utils.*"%>
<%@page import="com.Library.bean.PageBean"%>
<%@page import="com.Library.entity.BookInfor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="description" content="Bootstrap Metro Dashboard" /> 
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
<title>AdminInterface</title>
<body>
<style>
	#infor{
		margin-top:50px;
		margin-bottom:50px;
		margin-left:100px;
		text-align: left;
		width:80%;
	}
	
	#form_search{
		text-align: center;
	}
</style>
<%
	Object object = request.getSession().getAttribute(Constant.USER_KEY);
	Object userType = request.getSession().getAttribute(Constant.USER_TYPE);
	UserInfor userInfor = Utils.getUserInfor(object);
%>
<div class="navbar"> 
   <div class="navbar-inner"> 
    <div class="container-fluid"> 
     <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> 
     <a class="brand" href="admin.jsp"><span>ADMIN</span></a> 
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
       <li><a href="admin_show.jhtml?index=1&page=1&condition="><i class="icon-bar-chart"></i><span class="hidden-tablet">Book-User</span></a></li> 
       <li><a href="admin_show.jhtml?index=2&page=1&condition="><i class="icon-envelope"></i><span class="hidden-tablet"> BookInfor_Name</span></a></li> 
       <li><a href="admin_show.jhtml?index=3&page=1&condition="><i class="icon-tasks"></i><span class="hidden-tablet"> BookInfor_Type</span></a></li> 
       <li><a href="admin_show.jhtml?index=4&page=1&condition="><i class="icon-eye-open"></i><span class="hidden-tablet"> UserInfor</span></a></li> 
       <li><a href="admin_show.jhtml?index=5&page=1&condition="><i class="icon-dashboard"></i><span class="hidden-tablet"> Select_Month</span></a></li> 
       <li><a href="admin_show.jhtml?index=6&page=1&condition="><i class="icon-dashboard"></i><span class="hidden-tablet"> OverTime</span></a></li> 
       <li><a href="admin_show.jhtml?index=7&page=1&condition="><i class="icon-dashboard"></i><span class="hidden-tablet"> Black_List</span></a></li>
       <li><a href="return.jhtml?actionType=&page="><i class="icon-dashboard"></i><span class="hidden-tablet"> Return</span></a></li>  
      </ul> 
     </div> 
    </div> 
    <div id="content" class="span10"> 
		<form id="form_search" method="get" action="return.jhtml">
		<input type="text" name="Phone" value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">	
		<input type="hidden" name="actionType" value="search">
		<input type="hidden" name="page">
		<input type="submit"  value="Search" style="margin-top:-12px;"/>
	  </form>
	   <%
	  	if(request.getAttribute(Constant.ACCOUNT_BORROW) != null)
	  	{
	  		PageBean<BorrowInfor> bipageBean = (PageBean<BorrowInfor>) request.getAttribute(Constant.ACCOUNT_BORROW);
	  		if(bipageBean.getList() != null){
	  		List<BorrowInfor> borrowInfors = bipageBean.getList();
	  %>
	  		<table border="1" id="infor">
	 		<tr>
	 			<th style="color:green; margin-top:8px;"><font size="4">RealName</font></th>
	 			<th style="color:green; margin-top:8px;"><font size="4">BookName</font></th>
    			<th style="color:green; margin-top:8px;"><font size="4">Phone</font></th>
    			<th style="color:green; margin-top:8px;"><font size="4">BorrowDate</font></th>
    			<th style="color:green; margin-top:8px;"><font size="4">ReturnDate</font></th>
    			<th style="color:green; margin-top:8px;"><font size="4">Overtime</font></th>
	 		</tr>
	  <%
	  		for(BorrowInfor borrowInfor:borrowInfors)
	  		{
	  %>
	  			<form method="post" action="return.jhtml?">
		  			<tr>
		 				<th><font size="4"><%=borrowInfor.getUserInfor().getPeopleName()%></font></th>
		 				<th><font size="4"><%=borrowInfor.getBookInfor().getBookName()%></font></th>
		 				<th><font size="4"><%=borrowInfor.getUserInfor().getPhone()%></font></th>
		 				<th><font size="4"><%=borrowInfor.getStart()%></font></th>
		 				<th><font size="4"><%=borrowInfor.getFinish()%></font></th>
		 				<th><font size="4"><%=borrowInfor.isOvertime()%></font></th>
		 				<th><input type="submit" value="Return" style="text-align: center; width:100%"></th>
		 			</tr>
		 			<input type="hidden" name="actionType" value="return">
		 			<input type="hidden" name="page">
		 			<input type="hidden" name="Phone" value="<%=borrowInfor.getUserInfor().getPhone()%>">
		 			<input type="hidden" name="bookID" value="<%=borrowInfor.getBookID()%>">
		 		</form>
	  <%
	  			}
	  		}
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