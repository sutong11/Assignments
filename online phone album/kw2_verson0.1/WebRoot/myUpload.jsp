<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.wust.model.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>上传照片</title>
		<link href="css/uploadify.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery1.6.1.js"></script>
		<script language="javascript" src="js/swfobject.js"></script>
		<script language="javascript" src="js/jquery.uploadify.v2.1.0.min.js"></script>
		<script language="javascript" src="js/myUpload.js"></script>
		<script type="text/javascript">
	      $(document).ready(function() {
		        	
	$('#fileInput').uploadify({   
		 		'uploader': 'js/swf/uploadify.swf',
		        'script': 'upload.action',   //指定服务端处理类的
		        'folder': 'upload',   
		 		'cancelImg': 'image/cancel.png',
		        'fileDataName': 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
		        'queueID': 'fileQueue',   
		        'auto': false,//是否选取文件后自动上传   
		        'multi': true,//是否支持多文件上传
		        'simUploadLimit' : 5,//每次最大上传文件数   
		        'buttonText': '',//按钮上的文字   
		        'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
		        'onComplete': function (event, queueID, fileObj, response, data){ 
		       		document.getElementById("realpath").value = response;	      		
		       		if(uploadimgtest()){
		       			uploadimginf();}
		         }  
		       }); 
		    });  
    </script>
		
	</head>
	<body>		
		<img src="image/bg.JPG" class="blur"/>
			<div class="header">
				<div class="h_left">酷我相册</div>
				<div class="h_right">
					<a href="success.jsp" style="color:white">返回</a>
				</div>
			</div>
		<form id="photo_inf">
			<div class="pick">
				<%
					Object obj = session.getAttribute("albumNum");
					int abn = 0;
					String s = "请选择相册";
					if (null != obj) {
						String s1 = obj.toString();
						abn = Integer.parseInt(s1);
					}
					if (abn == 0) {
						s = "您还没有相册";
				%>
				<%=s%><span class="caret"></span>

				<%
					} else {
				%>
				<select name="tfeestype" id="tfeestype" onchange="check(this)">
					<option value=<%=s %>><%=s %></option>
					<%
						for (int i = 0; i < abn; i++) {
								String albname = "album" + i;
								Album tempAlbum = (Album) session.getAttribute(albname);
					%>
					<option value="<%=tempAlbum.getAlbumName()%>"><%=tempAlbum.getAlbumName()%></option>
					<%
					}%>
				</select>
				<%
					}
				%>
				<a href="javascript:$('#fileInput').uploadifyUpload();">开始上传</a>&nbsp;&nbsp;
				<a href="javascript:$('#fileInput').uploadifyClearQueue();">取消上传队列</a>
				</div>
				
				<br/><br/>
			<div class="pic">
				<div id="container">
	   		<ul>
	      		<input type="file" name="fileInput" id="fileInput"/>
			</ul><br><br>	
			<input type="text" style="display:none" name="realpath" id="realpath"/>
			<!--显示结果-->
			<center><div id="fileQueue">
			<span class="lat" id="lat"></span>
			</div></center>
		</div>
			</div>
		</form>
	</body>
</html>
