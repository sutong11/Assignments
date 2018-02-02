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
		<link href="css/t.css" rel="stylesheet" type="text/css" />
		<link href="css/success.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery1.6.1.js">
</script>
		<script language="javascript" src="js/swfobject.js">
</script>
		<script language="javascript" src="js/jquery.uploadify.v2.1.0.min.js">
</script>

		<script language="javascript" src="js/myUpload.js">
</script>
	</head>
	<style type="text/css">
body {
	TEXT-ALIGN: center;
	margin-top: 200px
}

#container {
	margin-left: auto;
	margin-right: auto;
	width: 168px;
}

#fileQueue {
	margin-right: auto;
	width: 168px;
}
</style>
	<body>
		<form>
			<div class="h_right">
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
				<select name="tfeestype" id="tfeestype">
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
			</div>


<input type="file" onChange="document.getElementsByName('filePath')[0].value=this.value" />
<input type="hidden" name="filepath" id="filepath" />
			<input type="file" name="pic" id="pic" accept="image/gif, image/jpeg"/>
			
			<input type="" name="picpath" id="picpath"/>
			<br/>
			<input type="textarea" name="photoDes" id="photoDes"/>
			<br/>
			<input type="button" value="提交" onclick="uploadimg()"/>
			<button type="reset">取消</button>
		</form>
	</body>
</html>
