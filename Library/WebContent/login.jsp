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
<title>Login</title>
</head>
<body>
<!--SIGN UP-->
   <h1>Library Login</h1> 
  <div class="login-form"> 
   <div class="close"> 
   </div> 
   <div class="head-info"> 
    <label class="lbl-1"> </label> 
    <label class="lbl-2"> </label> 
    <label class="lbl-3"> </label> 
   </div> 
   <div class="clear"> 
   </div> 
   <div class="avtar"> 
    <img src="images/avtar.png" /> 
   </div> 
   <form method="post" action="login.jhtml?type=login"> 
    <label id="messagelabel" style="color:white"></label>
    <% if(request.getAttribute("error_message") != null){ %>
   	<p style="color:red;"><%=request.getAttribute("error_message")%></p>
   <% }%> 
    <input type="text" class="text" id="UserName" onfocus="this.value = '';" placeholder="Phone/Email" name="loginName"/> <br>
    <div class="key"> 
     <input type="password" id="password" placeholder="Password" name="password"/> <br>
    </div> 
    <div class="key"> 
     <div class="btn-group1">
     	<button type="button" value="0">Admin</button>
    	<button type="button" value="1">Student</button>
    	<button type="button" value="2">Teacher</button>
    	<input type="hidden" value="" id="UserType" name="UserType">
  	</div>
  	 <input type="text" class="text" id="checkcode" onfocus="this.value = '';" placeholder="CheckCode" name="checkcode" style="width: 30%;"/>   	 
  	 <img alt="验证码" id="imagecode" src="<%=request.getContextPath() %>/code.jhtml"  onclick="javascript:reloadCode();">
  	</div>
    <div class="signin"> 
    	<input type="submit" id="submit" value="Login" /> 
    </div> 
   </form> 
  </div> 
  <div class="copy-rights">  
  	<p>If you don't have a account please click the Link:<a href="register.jsp">Create Free Account</a></p>
  </div> 
  
 <script>
    $('.btn-group1 button').click(function() {
      $('.btn-group1 button').removeClass('active');
      $(this).addClass('active');
      $('#UserType').val($(this).val());
    });
    
    $(document).ready(function(c) {
    	$('.close').on('click', function(c){
    		$('.login-form').fadeOut('slow', function(c){
    	  		$('.login-form').remove();
    		});
    	});	  
    	
  		$('#UserName').blur(function(){
  			if($('#UserName').val()==""){
  				$('#messagelabel').html("UserName can not be empty");
  				$("#submit").attr('disabled',true); 
  			}else{
  				$('#messagelabel').html("");
  				$("#submit").attr('disabled',false); 
  			}
  		})
  		$('#password').blur(function(){
  			if($('#password').val()==""){
  				$('#messagelabel').html("Password can not be empty");
  				$("#submit").attr('disabled',true); 
  			}else{
  				$('#messagelabel').html("");
  				$("#submit").attr('disabled',false); 
  			}
  		})
    });
    
    function reloadCode(){
		var time = new Date().getTime();
		document.getElementById("imagecode").src="<%=request.getContextPath() %>/code.jhtml?d="+time;
	}
    
  </script>
</body>
</html>