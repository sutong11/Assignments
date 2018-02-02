<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>上传相片</title>
	</head>
<style type="text/css">

body{TEXT-ALIGN: center;margin-top:200px}
#container{ margin-left:auto; margin-right:auto;width:168px; }
#fileQueue{ margin-right:auto; width:168px; }


</style>	

	<link href="css/uploadify.css" rel="stylesheet" type="text/css" />
	<link href="css/t.css" rel="stylesheet" type="text/css" />
	<link href="css/success.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery1.6.1.js"></script>
	<script language="javascript" src="js/swfobject.js"></script>
	<script language="javascript" src="js/jquery.uploadify.v2.1.0.min.js"></script>
	
	<script type="text/javascript">
	      $(document).ready(function() {
		        $('#fileInput').uploadify({   
		 		'uploader': 'js/swf/uploadify.swf',
		        'script': 'upload_photo',   //指定服务端处理类的
		        'folder': 'upload',   
		 		'cancelImg': 'image/cancel.png',
		        'fileDataName': 'fileInput', //和input的name属性值保持一致就好，Struts2就能处理了   
		        'queueID': 'fileQueue',   
		        'auto': false,//是否选取文件后自动上传   
		        'multi': true,//是否支持多文件上传
		        'simUploadLimit' : 5,//每次最大上传文件数   
		        'buttonText': "选择照片",//按钮上的文字   
		        'displayData': 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比    
		        'onComplete': function (event, queueID, fileObj, response, data){   
		       		alert('图片上传成功！');
		                         //window.location = "download.jsp";
		         }  
		       });   
		    });  
    </script>
<body style="background-color: #FFFAFA">
  <div id="header">
	  <div class="h_tit">酷我相册</div>
	  <div id="list1">
	  <div class="line"></div>
	  	<ul>
		<li><a href="index.jsp">首页</a></li>
		<li><a href="index.jsp">退出</a></li>
		<li><a href="success.jsp">返回</a></li>
		</ul>
	  </div>
   </div>
	   	<div id="container">
	   		<ul>
	      		<li><input type="file" name="fileInput" id="fileInput" /></li>
	      		<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="javascript:$('#fileInput').uploadifyUpload();">开始上传</a></li>
				<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li><a href="javascript:$('#fileInput').uploadifyClearQueue();">取消上传队列</a></li>
			</ul><br><br>	
			<!--显示结果-->
			<center><div id="fileQueue"></div></center>
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
			</ul><br/>
		<center>酷我公司版权所有◎2015-今</center>
		</div>
</body>

</html>
