<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.wust.model.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>我的相册</title>
		<link rel="stylesheet" type="text/css" href="css/success.css" />
		<link rel="stylesheet" type="text/css" href="css/t.css" />
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="shortcut icon" href="../favicon.ico">
		<link
			href='http://fonts.googleapis.com/css?family=Raleway:400,800,300'
			rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/set1.css" />
		<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.2.0/css/font-awesome.min.css" />
		<script src="js/album.js">
</script>
		<script src="js/jquery.min.js">
</script>
		<script src="js/bootstrap.js">
</script>
<%	
	String cur_jsp = "success.jsp";
	session.setAttribute("target", cur_jsp);
	String flg = "Y";
	String tempflg = null;
	Object objflg = session.getAttribute("flg");
	if(null == objflg){
		session.setAttribute("flg", "Y");
	}else{
		flg = session.getAttribute("flg").toString();
		session.setAttribute("flg", "Y");
	}
	System.out.println("----flg="+flg);
%>
	</head>

	<body onload="pageload()">
	<img src="images/bg1.jpg" class="blur">
	<input type="hidden" name="mytemp" id="mytemp" value="<%=flg %>"/>
		<div id="header">
			<div class="h_t" style="color: #2f3238">
				酷我相册
			</div>
			<div id="list1">
				<div class="line"></div>
				<ul>
					<li>
						<a href="index.jsp">首页</a>
					</li>
					<li>
						<a href="index.jsp">退出</a>
					</li>
					<li>
						<a href="friends.jsp">我的好友</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="h_right">
			<!--data-toggle="dropdown" 表示触发下拉事件-->
			<button class="btn dropdown-toggle" data-toggle="dropdown">
				${user.userName}
				<span class="caret"></span>
			</button>
			<!--添加下拉列表-->
			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
				<li>
					<a tabindex="-1" href="#">我的相册</a>
				</li>
				<li>
					<a tabindex="-1" href="reset_userinfo.jsp">个人资料</a>
				</li>
				<li>
					<a tabindex="-1" href="myUpload.jsp">上传相片</a>
				</li>
				<li class="divider"></li>
				<li class="disabled">
					<a tabindex="-1" href="#">批量下载</a>
				</li>
			</ul>
		</div>
			<div class="content">
			<div>
				<ul>
					<li class="item">
						<a class="item1 c_upload" href="myUpload.jsp">上传相片</a>
					</li>
					<li class="item">
						<a href="#create" data-toggle="modal" class="ct_album">创建相册</a>
						<div class="modal hide fade" id="create">
							<div class="modal-header">
								<a href="#" class="close" data-dismiss="modal">X</a>
								<h4>
									相册创建
								</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" id="create_album_form">
									<input class="modal hide fade" type="text"
										name="album.albumuserid" value="${user.userId}"
										style="display: hidden" />
									<div class="control-group">
										<label class="control-label" style="color: #2f3238">
											相册名称
										</label>
										<div class="controls">
											<input type="text" name="album.albumName">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="color: #2f3238">
											相册描述
										</label>
										<div class="controls">
											<textarea style="height: 100px; maxlength: 1000;"
												name="album.albumInfo"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" style="color: #2f3238">
											权限设置
										</label>
										<div class="controls" style="color:black">
											<input type="radio" name="album.authority"
												value="0" checked>
											公开
											<input type="radio" name="album.authority"
												value="1">
											私人
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											onclick="createalbum()">
											提交
										</button>
										<button type="reset" class="btn btn-primary">
											重置
										</button>
									</div>
								</form>
							</div>
						</div>
					</li>
					<li class="item">
						<a href="#">分类</a>
					</li>
					<li class="item">
						<a href="#">回收站</a>
					</li>
					<li class="item">
						<a href="#">时间轴</a>
					</li>
					<li class="item">
						<a href="#" id="delete-msg" style="color:red"></a>
					</li>
				</ul>
			</div>
			<!--  start create	-->
			<div class="container">
			
				<div class="content1">
				<%Object obj = session.getAttribute("albumNum");
				int abn = 0;
				if(null != obj){
					abn = Integer.parseInt(obj.toString());
				}
				if(abn == 0){
			%>
					<div class="grid">
						<div class="ln ln1">
							<a href="#m=1&amp;aid=279121089&amp;p=1"><img
									class="ztag201008041230345" src="image/noalbum.PNG"
									title="您还没有创建相册">
							</a>
						</div>
						<div class="ln ln2">
							<span class="ztag201008041230345">您还没有创建相册</span><span
								class="ztag201008041230345 fc10"></span>
						</div>
					</div>
					<%}else{ 
					%>
					<div class="grid">
					<form id="tttt">
					<input type="hidden" name="tempdelete" id="tempdelete"/>
					<%
						for(int i=0;i<abn;i++){
								String tempPath = "images//mm4.jpg";
								String alt = "";
								String s = "album"+i;
								obj = session.getAttribute(s);
								Album tempAlbum = null;
								if(null!=obj){
									tempAlbum = (Album)obj;
								}
								String authority = "公开";
								if(tempAlbum.getAlbumAuthority() == 1)
									authority = "仅自己可见";
								String photo = "photo"+i;
								obj = session.getAttribute(photo);
								Photo tempPhoto = null;
								if(null!=obj){
									tempPhoto = (Photo)obj;
									alt = tempPhoto.getPhotoInfo();
									System.out.println("---you 照片---");
									tempPath = tempPhoto.getPhotoUrl();
									System.out.println("---tempPath ="+tempPath);
									tempPath = basePath+"/upload//"+tempPath;
									System.out.println("---tempPath ="+tempPath);
								}	%>

						<figure class="effect-milo">
						<img src="<%=tempPath%>" alt="" />
						<input type="hidden" name="album-id" id="<%=s %>" value="<%=tempAlbum.getAlbumId()%>"/>
						<input type="hidden" name="album-photo-num" id="<%="num"+s %>" value="<%=tempAlbum.getPhotoNum()%>"/>
						<figcaption>
						<h2>
							<%=tempAlbum.getAlbumName() %>
							<span>(<%=tempAlbum.getPhotoNum() %>)张</span>
						</h2>
						<p>
							<%=tempAlbum.getAlbumInfo() %>
						</p>
						<p>
							<%=authority %>
						</p>
						<a class="r2"  onclick="deleteA(<%=i%>)">X</a>
						<a class="r1" href="picture_classic.jsp?album_id=<%=tempAlbum.getAlbumId()%>">View more</a>
						</figcaption>
						</figure>
					
			
					<%}%>
					</div>
					
					<%
					} %>
					</div>
				</form>
			<!-- /container -->
			<!--  end  	create-->
		</div>
		</div>
		<script>
// For Demo purposes only (show hover effect on mobile devices)
[].slice.call(document.querySelectorAll('a[href="#"')).forEach(function(el) {
	el.addEventListener('click', function(ev) {
		ev.preventDefault();
	});
});
</script>
	</body>
</html>
