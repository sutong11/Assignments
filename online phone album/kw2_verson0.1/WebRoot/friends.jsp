<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的好友</title>
    <link href="css/friends.css" rel="stylesheet" type="text/css" />
    <link href="css/test.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.js"></script>
  </head>
  
  <body>
  	  <img src="image/bg.JPG" class="blur"/>
      <div id="header">
	  	<div class="h_tit tit">酷我相册</div>
	  	<div id="list1">
	 	 <div class="line"></div>
	  		<ul>
				<li><a href="index.jsp">首页</a></li>
				<li><a href="success.jsp">返回</a></li>
			</ul>
	  	</div>
   	   </div>
   		<div class="h_right">
			<div class="dropdown">
			<!--data-toggle="dropdown" 表示触发下拉事件-->
			    <button class="btn dropdown-toggle" data-toggle="dropdown">
					    选择好友分组
			    <span class="caret"></span>
			    </button>
			  <!--添加下拉列表-->
			  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
			    <li><a tabindex="-1" href="#">好友</a></li>
			    <li><a tabindex="-1" href="classmate.jsp">同学</a></li>
			    <li class="divider"></li>
			    <li  class="disabled"><a tabindex="-1" data-toggle="modal" href="#createGroup" >添加分组</a>
				</li>
			    <li class="disabled"><a tabindex="-1" data-toggle="modal" href="#deleteGroup">删除分组</a></li>
			  </ul>
			</div>
		</div>
		<div><ul><li style="list-style-type: none;">
			<div class="modal hide fade" id="createGroup">
		 		<div class="modal-header">
		 			<a href="#" class="close" data-dismiss="modal">X</a>
		   			<h4>添加分组</h4>
		 		</div>
		 		<div class="modal-body">
		   			<form class="form-horizontal">
		     			<div class="control-group">
			   				<label class="control-label">添加分组名称</label>
			   					<div class="controls">
			   						<input type="text">
			   					</div>
			   			</div>
			   			<div class="modal-footer">
					   		<button type="button" class="btn btn-primary">提交</button>
		  				</div>
			 		</form>
			 	</div>
			 	</div>
			</li></ul></div>  
			<div><ul><li style="list-style-type: none;">
			<div class="modal hide fade" id="deleteGroup">
		 		<div class="modal-header">
		 			<a href="#" class="close" data-dismiss="modal">X</a>
		   			<h4>添加分组</h4>
		 		</div>
		 		<div class="modal-body">
		   			<form class="form-horizontal">
		     			<div class="control-group">
			   				<label class="control-label">删除分组名称</label>
			   					<div class="controls">
			   						<input type="text">
			   					</div>
			   			</div>
			   			<div class="modal-footer">
					   		<button type="button" class="btn btn-primary">提交</button>
		  				</div>
			 		</form>
			 	</div>
			 	</div>
			</li></ul></div> 
   	  <div class="content">      
   	  	<%@include file="photoWall.jsp"%>
   	   </div>
  </body>
</html>
