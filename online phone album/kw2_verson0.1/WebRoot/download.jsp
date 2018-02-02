<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>download</title>
	</head>
	
<style>
	body{TEXT-ALIGN: center;margin-top:200px}
</style>

	<body>

		<% 
	
		request.setAttribute("uploadFiles",application.getAttribute("uploadFiles"));
		
		 %>
		<table align="center" width="50%" border="1">
			<tr>
				<td align="center">
					文件下载
				</td>
			</tr>
			<c:forEach var="uploadFiles" items="${uploadFiles}">
				<tr>
					<td>
						<a
							href="download.action?name=${uploadFiles.uploadRealName }&realname=${uploadFiles.uploadFileName }">${uploadFiles.uploadFileName
							}</a>
					</td>
				</tr>

			</c:forEach>


		</table>
	</body>
</html>