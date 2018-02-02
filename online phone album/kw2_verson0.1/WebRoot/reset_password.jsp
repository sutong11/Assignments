<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户密码更改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="css/XS.css" />
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="css/private_set.css">
	
	
	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/my.js"></script>
  </head>
  
  <body>
    <%@include file="head2.jsp"%>
      <div id="main">
    	<div class="wcontainer clearfix">
        	<div class="setting-left l">
            	 <ul class="wrap-boxes">
                    <li > <a href="reset_userinfo.jsp" class="onactive">个人资料</a></li>
                    <li class="nav-active"><a href="reset_password.jsp">修改密码</a></li>
                 </ul>
            </div>
            
            <div class="setting-right">
            	  <div class="setting-right-wrap wrap-boxes settings">
            	    <div class="pwd-reset-wrap setting-resetpwd">
                      <form method="post" id="resetpwdform">
                      	<div>
							<input id="uid" type="hidden" name="user.userid" value="${user.userId}">
						</div>
                        <div class="wlfg-wrap">
                          <label class="label-name" for="">当前密码  </label>                     
                          <div class="rlf-group">
                            <input id="psw"  type="password" name="user.oldpassword" value="${user.password}" class="rlf-input "
                              placeholder="请输入当前密码" disabled>
                            <p class="rlf-tip-wrap"></p>
                          </div>
                        </div>
                        <div class="wlfg-wrap">
                          <label class="label-name" for="newpass">新密码 </label>                                                 
                          <div class="rlf-group">
                            <input data-validate="password" name="user.password" id="psw1" value=""
								onfocus="pswCheck('psw1','psw1tip','savechange')" onkeyup="pswCheck('psw1','psw1tip','savechange')"
								onblur="pswCheck('psw1','psw1tip','savechange')"
                              class="rlf-input " placeholder="请输入密码" type="password" required>
                            <p class="rlf-tip-wrap" id="psw1tip"></p>
                          </div>
                        </div>
                        <div class="wlfg-wrap">
                          <label class="label-name" for="confirm">确认密码  </label>                                                
                          <div class="rlf-group">
                            <input name="confirm_password" id="psw2" value=""
								onfocus="pswCheck('psw2','psw2tip','savechange')" onkeyup="pswCheck('psw2','psw2tip','savechange')"
								onblur="pswCheck('psw2','psw2tip','savechange')"
                            	class="rlf-input" placeholder="请输入密码"
                            	type="password" required>
                            <p class="rlf-tip-wrap" id="psw2tip"></p>
                          </div>
                        </div>
                        <div class="wlfg-wrap">
                          <label class="label-name" for=""></label>
                          <div class="rlf-group">
                            <button id="savechange" hidefocus="true" type="button"
                              class="rlf-btn-green btn-block" disabled onclick="newpsw()">保存</button>
                          </div>
                        </div>
                      </form>
                    </div>
                        
          		</div>
            </div>
            
        </div>
    </div>
  </body>
</html>
<script type="text/javascript">
	$(function() {
		$(document).bind("click",function(){
			$("#search-suggest").hide();
		})
	})
</script>
