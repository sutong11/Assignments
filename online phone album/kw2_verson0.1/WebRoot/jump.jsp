<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<!DOCTYPE html>
	<html lang="zh-cn">
	<head>
	<meta charset="utf-8">
	<title>跳转</title>
	<meta name="viewport" content="width=device-width">
	<meta name="description" content="A collection of loading spinners animated with CSS">
	<meta property="og:title" content="Single Element CSS Spinners">
	<meta property="og:type" content="website">
	<meta property="og:description" content="A collection of loading spinners animated with CSS">
	<meta property="og:image" content="http://projects.lukehaas.me/css-loaders/images/css-loaders-screenshot.jpg">
	<meta property="og:url" content="http://projects.lukehaas.me/css-loaders/">
	<script src="script/modernizr-2.8.2.js"></script>
	<link rel="stylesheet" href="css/main1.css">
	<link rel="stylesheet" href="css/load4.css">
	<% 
		String target = session.getAttribute("target").toString();
		if(target.equals("success.jsp"))
		{
		session.setAttribute("flg","N");
		}else if(target.startsWith("picture_classic.jsp")){
		session.setAttribute("pflg","N");
		}else if(target.startsWith("comment.jsp")){
		session.setAttribute("cflg","N");
		}
	%>
	<script type="text/javascript">
	function jumpPage(){
		var target = document.getElementById("mytemp").value;
		window.location.href=target;
	}
	function jumpPage1(){
		setTimeout(jumpPage,900);
	}
	</script>
	</head>
	<body onload="jumpPage1()">
	<input type="hidden" name="mytemp" id="mytemp" value="<%=target %>"/>
	<img src="image/17_04.jpg" class="blur">
	<main style="margin-top:90px">
	<div class="inner">
		<div class="load-container load4">
			<div class="loader">Loading...</div>
		</div>
	</div>
	<div class="overlay hidden"></div>
	<div id="load4" class="source hidden">
		<textarea readonly class="markup">
			<div class="loader">Loading...</div>
		</textarea>
	</div>
</main>
</body>
</html>