<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>忘记密码页面</title>
    <link href="./css/t.css" type="text/css" rel="stylesheet" />
  	<link href="./css/login.css" type="text/css" rel="stylesheet" />
  </head>
  
  <body>
   <div id="header">
	  <div class="h_title">酷我相册</div>
	  <div id="list1">
	  <div class="line"></div>
	  	<ul>
		<li><a href="index.jsp">首页</a></li>
		</ul>
	  </div>
   </div>
   
  <div id="content" style="background-image: url('image/430.png');">
    <div class="wrap"> 
  	<form name="fgtpsd"  action="send.jsp" method="post">
  		<section class="loginForm"> 
      		<header><h1>找回密码</h1></header> 
      		<div class="loginForm_content"> 
        	<fieldset> 
          	<div class="inputWrap"> 
            <input type="text" name="username" placeholder="请输入您的账户名" autofocus required> 
          	</div> 
          	<div class="inputWrap"> 
            <input type="text" name="email" placeholder="请输入注册时的邮箱" required> 
          	</div> 
        	</fieldset> 
        	<fieldset> 
          	<input type="submit" name="send" value="确认发送" > 
        	</fieldset> 
      		</div> 
    	</section> 
  	</form> 
  	</div>
  </div>
 	<div class="footer">
		<ul>
		<li><a href="#">关于酷我</a></li>
		<li>|</li>
		<li><a href="#">相册官方博客</a></li>
		<li>|</li>
		<li><a href="#">意见反馈</a></li>
		<li>|</li>
		<li><a href="#">常见问题</a></li>
		<li>|</li>
		<li><a href="#">招聘信息</a></li>
		<li>|</li>
		<li><a href="#">客户服务</a></li>
		<li>|</li>
		<li><a href="#">隐私政策</a></li>
		</ul>
		<center>酷我公司版权所有◎2015-今</center>
	</div>
  </body>
</html>
