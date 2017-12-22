<%@page import="com.Library.utils.Packager"%>
<%@page import="com.Library.utils.Utils"%>
<%@page import="com.Library.entity.UserInfor"%>
<%@page import="com.Library.bean.LoginInfor"%>
<%@page import="com.Library.globle.Constant"%>
<%@page import="com.Library.entity.Student"%>
<%@page import="com.Library.entity.Admin"%>
<%@page import="com.Library.entity.Teacher"%>

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
<style>
	* {
            color: white;
        }

        body {
            background-color: black;
        }

        .userInformation {
            width: 600px;
            height: 500px;
            background-color: rgb(148, 148, 148);
            background: rgba(131, 129, 129, 0.5);
            position: absolute;
            margin: auto;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            line-height: 35px;
        }

        .userName {
           margin:100px 100px;
        }

        h4,
        #dgw,
        #nick {
            letter-spacing: 5px;
        }

        #dgw,
        #nick{
            position: absolute;
            margin: -37px 140px;
        }
        
         #qq {
            position: absolute;
            margin: -34px 70px;
        }

        #wechat,
        #email {
            letter-spacing: 1px;
        }

        .userImg {
            width: 110px;
            height: 120px;
            background-color: white;
            position: relative;
            margin: -275px 460px;
        }

        #nickbtn,
        #nickelse {
        	width:30px;
        	height:30px;
            position: absolute;
            border: none;
            margin: -35px 320px;
            outline: none;
        }

        #wechatSpan,
        #emailSpan {
            position: absolute;
            margin: -32px 100px;
        }
        
         #BookSpan {
            position: absolute;
            margin: -32px 170px;
        }

        #nickFirstbtn,
        #nickFirstelse {
        	width:30px;
        	height:30px;
            position: absolute;
            border: none;
            margin: -30px 320px;
            outline: none;
            
        }

        #weChatbtn,
        #WechatElse {
        	width:30px;
        	height:30px;
            position: absolute;
            border: none;
            margin: -32px 320px;
            outline: none;
        }
        #submitBtn{
            margin:-50px 100px;
            color:black;
            border:none;
            padding:10px 25px;
            outline: none;
            background-color: #d4d4d3;
            color: black;
            border-radius:3px;
        }
        #submitBtn:active{
            border:1px solid black;
        }
</style>
</head>
<body>
<%
	Admin Aobj = null;
	Student Sobj = null;
	Teacher Tobj = null;
	Object object = request.getSession().getAttribute(Constant.USER_KEY);
	
	String userType = request.getSession().getAttribute(Constant.USER_TYPE).toString();
	if("0".equals(userType))
	{
		Aobj = (Admin) request.getSession().getAttribute(Constant.USER_KEY);
	}
	else if("1".equals(userType))
	{
		Sobj = (Student) request.getSession().getAttribute(Constant.USER_KEY);
	}
	else if("2".equals(userType))
	{
		Tobj = (Teacher) request.getSession().getAttribute(Constant.USER_KEY);
	}
	UserInfor userInfor = Utils.getUserInfor(object);
%>
<!--SIGN UP-->
  <div class="userInformation">
        <form action="editor.jhtml" method="post">
            <div class="userName">
                <h4>RealName:</h4>
                <span id="dgw"><%=userInfor.getPeopleName()%></span>
                <h4>UserName:</h4>
                <span id="nick"><%=userInfor.getLoginName()%></span>
                <input type="hidden" name="loginName" id="nameInput" value="">
                <img src="images/修改.png" id="nickbtn" onclick="changeContext()">
                <img src="images/修改.png" id="nickelse" onclick="changeFirst()" style="display:none">

                <h4>qq:</h4>
                <span id="qq" style="letter-spacing:1px"><%=userInfor.getQQ()%></span>
                <input type="hidden" name="QQ" id="qqInput" value="">
                <img src="images/修改.png" id="nickFirstbtn" onclick="changeContextQ()">
                <img src="images/修改.png" id="nickFirstelse" onclick="changeFirstQ()" style="display:none;">

                <h4 id="wechat">wechat:</h4>
                <span id="wechatSpan" style="letter-spacing:1px"><%=userInfor.getWeChat()%></span>
                <input type="hidden" name="WeChat" id="wechatInput" value="">
               <img src="images/修改.png" id="weChatbtn" onclick="changeContextWechat()">
                <img src="images/修改.png" id="WechatElse" onclick="changeFirstWeChat()" style="display:none;">

                <h4 id="email">E-mail:</h4>
                <span id="emailSpan"><%=userInfor.getEmail()%></span>
                
                <h4 id="email">Phone:</h4>
                <span id="emailSpan"><%=userInfor.getPhone()%></span>
                
                <h4 id="email">Apply Book:</h4>
                <span id="BookSpan"><%=userInfor.getAppBookNumber()%></span>
                
                <h4 id="email">Borrow Book:</h4>
                <span id="BookSpan"><%=userInfor.getBroBookNumber()%></span>
                <%if(Sobj != null){%>
                		 <h4 id="email">Max Borrow book:</h4>
                         <span id="BookSpan"><%=Sobj.getCredit()%></span>
                         
                         <h4 id="email">Max Apply Book:</h4>
                         <span id="BookSpan"><%=Sobj.getBorCredit()%></span>
                         
                         <h4 id="email">Experience:</h4>
                         <span id="BookSpan"><%=Sobj.getExperience()%></span>
                <%}%>
                <%if(Tobj != null){%>
                		 <h4 id="email">Max Borrow book:</h4>
                         <span id="BookSpan"><%=Tobj.getCredit()%></span>
                         
                         <h4 id="email">Max Apply Book:</h4>
                         <span id="BookSpan"><%=Tobj.getBorCredit()%></span>
                         
                         <h4 id="email">Experience:</h4>
                         <span id="BookSpan"><%=Tobj.getExperience()%></span>
                <%}%>
            </div>
            <button type="submit" id="submitBtn">提交</button>
        </form>
        <div style="margin-top:-50px; margin-left:300px;"><a href="<%=request.getContextPath() %>/index.jsp"><font size="5" color="red">返回主页</font></a></div>
    </div>
  <script>
//用户名
 function changeContext() {
            var context = document.getElementById('nick');
            var c = context.innerHTML;
            context.innerHTML = "<input type='text' id='show' value='" + c + "'>";
            document.getElementById('show').style.color = "black";
            document.getElementById('nickbtn').style.display = "none";
            document.getElementById('nickelse').style.display = "block";
           
        }
        function changeFirst() {
            var reInput = document.getElementById('show');
            document.getElementById('nick').innerText = reInput.value;
            document.getElementById('nickelse').style.display = "none";
            document.getElementById('nickbtn').style.display = "block";
            document.getElementById('nameInput').value=document.getElementById('nick').innerText;
        }
        // qq
        function changeContextQ() {
            var context = document.getElementById('qq');
            var c = context.innerHTML;
            context.innerHTML = "<input type='text' id='showQ' value='" + c + "'>";
            document.getElementById('showQ').style.color = "black";
            document.getElementById('nickFirstbtn').style.display = "none";
            document.getElementById('nickFirstelse').style.display = "block";
        }
        function changeFirstQ() {
            var reInput = document.getElementById('showQ');
            document.getElementById('qq').innerText = reInput.value;
            document.getElementById('nickFirstelse').style.display = "none";
            document.getElementById('nickFirstbtn').style.display = "block";
            document.getElementById('qqInput').value=document.getElementById('qq').innerText;
        }


        //wechat
        function changeContextWechat() {
            var context = document.getElementById('wechatSpan');
            var c = context.innerHTML;
            context.innerHTML = "<input type='text' id='showWeChat' value='" + c + "'>";
            document.getElementById('showWeChat').style.color = "black";
            document.getElementById('weChatbtn').style.display = "none";
            document.getElementById('WechatElse').style.display = "block";
        }
        function changeFirstWeChat() {
            var reInput = document.getElementById('showWeChat');
            document.getElementById('wechatSpan').innerText = reInput.value;
            document.getElementById('WechatElse').style.display = "none";
            document.getElementById('weChatbtn').style.display = "block";
            document.getElementById('wechatInput').value=document.getElementById('wechatSpan').innerText;
        }function changeContext() {
            var context = document.getElementById('nick');
            var c = context.innerHTML;
            context.innerHTML = "<input type='text' id='show' value='" + c + "'>";
            document.getElementById('show').style.color = "black";
            document.getElementById('nickbtn').style.display = "none";
            document.getElementById('nickelse').style.display = "block";
           
        }
        function changeFirst() {
            var reInput = document.getElementById('show');
            document.getElementById('nick').innerText = reInput.value;
            document.getElementById('nickelse').style.display = "none";
            document.getElementById('nickbtn').style.display = "block";
            document.getElementById('nameInput').value=document.getElementById('nick').innerText;
        }
        // qq
        function changeContextQ() {
            var context = document.getElementById('qq');
            var c = context.innerHTML;
            context.innerHTML = "<input type='text' id='showQ' value='" + c + "'>";
            document.getElementById('showQ').style.color = "black";
            document.getElementById('nickFirstbtn').style.display = "none";
            document.getElementById('nickFirstelse').style.display = "block";
        }
        function changeFirstQ() {
            var reInput = document.getElementById('showQ');
            document.getElementById('qq').innerText = reInput.value;
            document.getElementById('nickFirstelse').style.display = "none";
            document.getElementById('nickFirstbtn').style.display = "block";
            document.getElementById('qqInput').value=document.getElementById('qq').innerText;
        }


        //wechat
        function changeContextWechat() {
            var context = document.getElementById('wechatSpan');
            var c = context.innerHTML;
            context.innerHTML = "<input type='text' id='showWeChat' value='" + c + "'>";
            document.getElementById('showWeChat').style.color = "black";
            document.getElementById('weChatbtn').style.display = "none";
            document.getElementById('WechatElse').style.display = "block";
        }
        function changeFirstWeChat() {
            var reInput = document.getElementById('showWeChat');
            document.getElementById('wechatSpan').innerText = reInput.value;
            document.getElementById('WechatElse').style.display = "none";
            document.getElementById('weChatbtn').style.display = "block";
            document.getElementById('wechatInput').value=document.getElementById('wechatSpan').innerText;
        }
  </script>
</body>
</html>