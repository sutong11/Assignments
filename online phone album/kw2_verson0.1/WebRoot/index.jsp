<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//basePath.forward(request,   response);
%>
<!DOCTYPE HTML >
<html>
	<head>
	
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>酷我相册首页</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="css/head.css" />
		<link rel="stylesheet" type="text/css" href="css/font-awesome.css">

		<script src="js/jquery.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/my.js"></script> 
	<% 
		session.setAttribute("pflg","Y");
		session.setAttribute("flg","Y");
	%>
	</head>
	<body>
		<%@include file="head.jsp"%>
	</body>
</html>

