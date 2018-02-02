<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.wust.model.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>我的相片</title>
		<script src="js/jquery.min.js">
</script>
		<script src="js/bootstrap.js">
</script>

		<meta name="description"
			content="Hover Effect Ideas: Inspiration for subtle hover effects" />
		<meta name="keywords"
			content="hover effect, inspiration, grid, thumbnail, transition, subtle, web design" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="../favicon.ico">
		<link
			href='http://fonts.googleapis.com/css?family=Raleway:400,800,500,600'
			rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/demo_3d.css" />
		<link rel="stylesheet" type="text/css" href="css/set2.css" />
		<link rel="stylesheet" type="text/css"
			href="fonts/font-awesome-4.2.0/css/font-awesome.min.css" />
		<!--[if IE]>
  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<script src="js/photo.js">
</script>

		<script type="text/javascript">

</script>
		<%
			Object obj = request.getParameter("album_id");
			String album_id = null;
			if (null != obj) {
				album_id = obj.toString();
			}

			obj = session.getAttribute("photoList");
			List photoList = null;
			int photoNum = 0;
			if (null != obj) {
				photoList = (List) obj;
				photoNum = photoList.size();
			}
			System.out.println("----photoNum="+photoNum);
			session.setAttribute("Id_forFindPhotos", album_id);
	String cur_jsp = "picture_classic.jsp?album_id="+album_id;
	session.setAttribute("target", cur_jsp);
	String flg = "Y";
	String tempflg = null;
	Object objflg = session.getAttribute("pflg");
	if(null == objflg){
		session.setAttribute("pflg", "Y");
	}else{
		flg = session.getAttribute("pflg").toString();
		session.setAttribute("pflg", "Y");
	}
	System.out.println("----pflg="+flg);
%>
	</head>
	<body onload="loadPic();">
	<input type="hidden" name="mytemp" id="mytemp" value="<%=flg %>"/>
		<input type="hidden" name="albumid" id="albumid" value="<%=album_id%>" />
		<div class="container">
			<h1 style="color:black">
				我的相片
			</h1>
			<div class="more">
				<ul id="sb-examples">
					<li class="selected">
						<a href="picture_classic.jsp?album_id=<%=album_id%>">普通效果</a>
					</li>
					<li>
						<a href="picture_3d.jsp?album_id=<%=album_id%>">3D效果</a>
					</li>
					<li>
						<a href="success.jsp">》返回</a>
					</li>
				</ul>
				<ul>
				<a href="#" id="delete-msg" style="color:red"></a>
				</ul>
			</div>
			<div class="content">
				<form id="photo-for-delete">
					<input type="hidden" name="tempdelete" id="tempdelete" />
					<div class="grid">
						<%
							String photoName = "没有照片";
							String photoInf = "";
							String photoDate = "";
							String photoUrl = "";
							String tempPath = basePath + "/upload//";
							if (photoNum > 0) {
								Photo tempPhoto = null;
								for (int i = 0; i < photoNum; i++) {
									tempPhoto = (Photo) photoList.get(i);
									photoName = tempPhoto.getPhotoName();
									String s = "photo"+i;
									int lastIndex = 3;
									lastIndex = photoName.lastIndexOf(".");
									photoName = photoName.substring(0, lastIndex);
									photoInf = tempPhoto.getPhotoInfo();
									photoDate = tempPhoto.getPhotoDate().toString().substring(
											0, 10);
									photoUrl = tempPhoto.getPhotoUrl();
						%>
						<figure class="effect-julia">
						<img src="<%=tempPath + photoUrl%>" alt="<%=photoInf%>" />
						<input type="hidden" name="album_id" id="<%=s%>"
							value="<%=tempPhoto.getPhotoId()%>" />

						<figcaption>
						<div>
						
							<%
								if (!photoInf.isEmpty()) {
							%>
							
							<p>
								<%=photoInf%>
							<br />
							</p>
							<%
								}
							%>
							<p>
								上传时间：<%=photoDate%>
							</p>
							<h2>
							<%=photoName%>
							</h2>
							
						</div>
						<a class="r2" onclick="deleteP(<%=i%>)">X</a>
						<a class="r1" href="comment.jsp?photo_id=<%=tempPhoto.getPhotoId()%>&album_id=<%=album_id %>">Go comment</a>
						</figcaption>
						</figure>

						<%
							}
							} else {
						%>
						<figure class="effect-julia">
						<img src="img/21.jpg" alt="img21" />
						<figcaption>
						<h2>
							<%=photoName%>
						</h2>
						</figcaption>
						</figure>
						<%
							}
						%>
					</div>
				</form>
			</div>
		</div>
		<!-- /container -->
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