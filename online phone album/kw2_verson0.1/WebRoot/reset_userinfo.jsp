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
    
    <title>个性设置</title>
    
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
                    <li class="nav-active"> <a href="reset_userinfo.jsp" class="onactive">个人资料</a></li>
                    <li ><a href="reset_password.jsp">修改密码</a></li>
                 </ul>
            </div>
            
            <div class="setting-right">
            	<div class="setting-right-wrap wrap-boxes settings">
                   <div class="setting-profile">
                        <form id="changeInfo-form" >
								<div>
									<input type="hidden" name="user.userid" value="${user.userId}">
								</div>
								<div class="wlfg-wrap">
                                <label class="label-name" for="user-name" >用户名</label>
                                <div class="rlf-group">
                                    <input  type="text" name="user.user_name" value="${user.userName}" id="user-name"  
                                    autocomplete="off"  data-validate="nick"  class="rlf-input" disabled/>
                                    <p class="rlf-tip-wrap"></p>
                                </div>
                            </div>
                            <div class="wlfg-wrap">
                                <label class="label-name" for="email" >邮箱</label>
                                <div class="rlf-group">
                                    <input  type="text" name="user.email" value="${user.email}" id="email"  
                                    autocomplete="off"  data-validate="email"  class="rlf-input" 
                                    placeholder="请输入邮箱."/>
                                    <p class="rlf-tip-wrap"></p>
                                </div>
                            </div>
           				
                            <div class="wlfg-wrap">
                                <label class="label-name" for="phone" >手机号</label>
                                <div class="rlf-group">
                                    <input  type="text" name="user.phone" value="${user.phone}" id="phone"  
                                    autocomplete="off"  data-validate="phone"  class="rlf-input" 
                                    placeholder="请输入手机号."/>
                                    <p class="rlf-tip-wrap"></p>
                                </div>
                            </div>
                            
                            <div class="wlfg-wrap">
                                <label class="label-name" for="aboutme">个性签名</label>
                                <div class="rlf-group">
                                    <textarea name="user.signnature" id="aboutme" cols="30" rows="5" >${user.signnature}</textarea>
                                    <p class="rlf-tip-wrap"></p>
                                </div>
                            </div>
                            <div class="wlfg-wrap">
                                <div class="rlf-group">
                                    <span id="profile-submit"  hidefocus="true"  aria-role="button" class="rlf-btn-green btn-block profile-btn" onclick="editUserInfo()">保存</span>
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

