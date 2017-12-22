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
<title>Register</title>
</head>
<body>
<!--SIGN UP-->
   <h1>Create Free Account</h1> 
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
   <form method="post" action="register.jhtml">
   <label id="messagelabel" style="color:white"></label>
   <% if(request.getAttribute("error_message") != null){ %>
   	<p style="color:red;"><%=request.getAttribute("error_message")%></p>
   <% }%> 
    <input type="text" class="text" id="RealName" onfocus="this.value = '';" placeholder="RealName" name="Realname"/> 
    <input type="text" class="text" id="Username" onfocus="this.value = '';" placeholder="Username" name="loginName"/> 
    <input type="password" class="password" id="Password" onfocus="this.value = '';" placeholder="Password" name="Password"/>
    <div class="btn-group">
    	<button type="button" value="0">男</button>
    	<button type="button" value="1">女</button>
    	<input type="hidden" value="" id="gender" name="gender">
  	</div> 
  	<div class="btn-group2">
    	<button type="button" value="1">Student</button>
    	<button  type="button" value="2">Teacher</button>
    	<input type="hidden" value="" id="UserType" name="UserType">
  	</div> 
    <input type="text" class="text" id="Phone" onfocus="this.value = '';" placeholder="Phone" name="Phone"/>
    <input type="text" class="text" id="Email" onfocus="this.value = '';" placeholder="Email" name="Email"/>
    <input type="text" class="text" id="QQ" onfocus="this.value = '';" placeholder="QQ" name="QQ"/>   
    <input type="text" class="text" id="WeChat" onfocus="this.value = '';" placeholder="WeChat" name="WeChat"/> 
    <input type="text" class="text" id="IDCards" onfocus="this.value = '';" placeholder="IDCards" name="IDCards"/> 
    <div class="signin"> 
    	<input type="submit" value="Create" /> 
    </div> 
   </form> 
  </div> 
  <div class="copy-rights">  
  	<p>If you  have a account please click the Link:<a href="login.jsp">Login</a></p>
  </div> 
  <script>
    $('.btn-group button').click(function() {
      $('.btn-group button').removeClass('active');
      $(this).addClass('active');
      $('#gender').val($(this).val());
    });
    
    $('.btn-group2 button').click(function() {
        $('.btn-group2 button').removeClass('active');
        $(this).addClass('active');
        $('#UserType').val($(this).val());
      });
    
    $(document).ready(function(c) {
    	$('.close').on('click', function(c){
    		$('.login-form').fadeOut('slow', function(c){
    	  		$('.login-form').remove();
    		});
    	});
    	
    	$('#RealName').blur(function(){
  			if($('#RealName').val()==""){
  				$('#messagelabel').html("RealName can not be empty");
  				$("#submit").attr('disabled',true); 
  			}else{
  				$('#messagelabel').html("");
  				$("#submit").attr('disabled',false); 
  			}
  		})
  		
  		$('#Username').blur(function(){
  			if($('#Username').val()==""){
  				$('#messagelabel').html("Username can not be empty");
  				$("#submit").attr('disabled',true); 
  			}else{
  				$('#messagelabel').html("");
  				$("#submit").attr('disabled',false); 
  			}
  		})
  		
  		$('#Password').blur(function(){
  			if($('#Password').val()==""){
  				$('#messagelabel').html("Password can not be empty");
  				$("#submit").attr('disabled',true); 
  			}else{
  				$('#messagelabel').html("");
  				$("#submit").attr('disabled',false); 
  			}
  		})
  		
  		$('#Phone').blur(function(){
  			if($('#Phone').val()==""){
  				$('#messagelabel').html("Phone can not be empty");
  				$("#submit").attr('disabled',true); 
  			}else{
  				$('#messagelabel').html("");
  				$("#submit").attr('disabled',false); 
  			}
  		})
  		
  		$('#Email').blur(function(){
  			if($('#Email').val()==""){
  				$('#messagelabel').html("Email can not be empty");
  				$("#submit").attr('disabled',true); 
  			}else{
  				$('#messagelabel').html("");
  				$("#submit").attr('disabled',false); 
  			}
  		})
  		
  		$('#IDCards').blur(function(){
  			if($('#IDCards').val()==""){
  				$('#messagelabel').html("IDCards can not be empty");
  				$("#submit").attr('disabled',true); 
  			}else{
  				$('#messagelabel').html("");
  				$("#submit").attr('disabled',false); 
  			}
  		})
    });
  </script>
</body>
</html>