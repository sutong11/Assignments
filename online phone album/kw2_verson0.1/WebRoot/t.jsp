<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script src="js/jquery.min.js">
</script>
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="js/bootstrap.min.js">
</script>
		<script src="js/album.js">
</script>

	</head>

	<body>
		<form id="t">
			name：
			<input type="text" name="test.Name">
			<br />
			value：
			<input type="text" name="test.value">
			<br />
			<button id="sub_btn" type="button" onclick=mytest()>
			提交
			</button>
			<font color="red"><div align="center" id="inf-msg"></div> </font>
		</form>
	</body>
</html>