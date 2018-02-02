<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.wust.model.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <title>我的相片</title>
        		<script src="js/jquery.min.js">
</script>
		<script src="js/bootstrap.js">
</script>
		
        
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Slicebox - 3D Image Slider with Fallback" />
        <meta name="keywords" content="jquery, css3, 3d, webkit, fallback, slider, css3, 3d transforms, slices, rotate, box, automatic" />
		<meta name="author" content="Pedro Botelho for Codrops" />
		<link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/demo_3d.css" />
		<link rel="stylesheet" type="text/css" href="css/slicebox.css" />
		<link rel="stylesheet" type="text/css" href="css/custom.css" />
		<link rel="stylesheet" href="http://dreamsky.github.io/main/blog/common/init.css">
		<style>
		</style>			
		<script type="text/javascript" src="js/modernizr.custom.46884.js"></script>
	</head>
	<body onload="loadPic();">
	<%
			Object obj = request.getParameter("album_id");
			String album_id = null;
			if(null != obj){
				album_id = obj.toString();
			}
			
			obj = session.getAttribute("photoList");
			List photoList = null;
			int photoNum = 0;
			if(null != obj){
				photoList = (List) obj;
				photoNum = photoList.size();
			}
			System.out.println("album_id="+album_id);
			System.out.println("photoNum="+photoNum);
			session.setAttribute("Id_forFindPhotos",album_id);
		%>
		<div class="container">
			<h1 style="color:black">我的相片</h1>
			
			<div class="more">
				<ul id="sb-examples">
					<li><a href="picture_classic.jsp?album_id=<%=album_id%>">普通效果</a></li>
					<li class="selected"><a href="picture_3d.jsp?album_id=<%=album_id%>">3D效果</a></li>
					<li><a href="success.jsp">》返回</a></li>
				</ul>
			</div>
			<div class="wrapper">
				<ul id="sb-slider" class="sb-slider">
				<%	
					String photoName = "没有照片"; 
					String photoInf = ""; 
					String photoDate = ""; 
					String photoUrl = ""; 
					String tempPath = basePath+"/upload//";
					if(photoNum > 0){
						Photo tempPhoto = null;
						for(int i=0;i<photoNum;i++)
						{
							tempPhoto = (Photo) photoList.get(i);
							photoName = tempPhoto.getPhotoName();
							int lastIndex = 3;
							lastIndex = photoName.lastIndexOf(".");
							photoName = photoName.substring(0,lastIndex);
							photoInf = tempPhoto.getPhotoInfo();
							photoDate = tempPhoto.getPhotoDate().toString().substring(0,10);
							photoUrl = tempPhoto.getPhotoUrl();
					%>
					<li>
						<a href="#"><img src="<%=tempPath+photoUrl%>" alt="<%=photoInf %>"/></a>
						<div class="sb-description">
							<h3><%=photoName %></h3>
						</div>
					</li>
					<%} }else{%>
					<li>
						<a href="#"><img src="images/1.jpg" alt="image1"/></a>
						<div class="sb-description">
							<h3><%=photoName %></h3>
						</div>
					</li>
					<%} %>
					
					<!--
					<li>
						<a href="#"><img src="images/2.jpg" alt="image2"/></a>
						<div class="sb-description">
							<h3>金秋的野外</h3>
						</div>
					</li>
					<li>
						<a href="#"><img src="images/3.jpg" alt="image1"/></a>
						<div class="sb-description">
							<h3>静谧的湖泊</h3>
						</div>
					</li>
					<li>
						<a href="#"><img src="images/4.jpg" alt="image1"/></a>
						<div class="sb-description">
							<h3>森林一角</h3>
						</div>
					</li>
					<li>
						<a href="#"><img src="images/5.jpg" alt="image1"/></a>
						<div class="sb-description">
							<h3>世界末日</h3>
						</div>
					</li>
					<li>
						<a href="#"><img src="images/6.jpg" alt="image1"/></a>
						<div class="sb-description">
							<h3>家边的美景</h3>
						</div>
					</li>
					<li>
						<a href="#"><img src="images/7.jpg" alt="image1"/></a>
						<div class="sb-description">
							<h3>去爬山吧</h3>
						</div>
					</li>
					
					-->
				</ul>

				<div id="shadow" class="shadow"></div>

				<div id="nav-arrows" class="nav-arrows">
					<a href="#">Next</a>
					<a href="#">Previous</a>
				</div>

			</div><!-- /wrapper -->
			<div class="footer-banner" style="width:728px; margin:30px auto"></div>
		</div>
		<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.slicebox.js"></script>
		<script type="text/javascript">
			$(function() {
				
				var Page = (function() {

					var $navArrows = $( '#nav-arrows' ).hide(),
						$shadow = $( '#shadow' ).hide(),
						slicebox = $( '#sb-slider' ).slicebox( {
							onReady : function() {

								$navArrows.show();
								$shadow.show();

							},
							orientation : 'r',
							cuboidsRandom : true,
							disperseFactor : 30
						} ),
						
						init = function() {

							initEvents();
							
						},
						initEvents = function() {

							// add navigation events
							$navArrows.children( ':first' ).on( 'click', function() {

								slicebox.next();
								return false;

							} );

							$navArrows.children( ':last' ).on( 'click', function() {
								
								slicebox.previous();
								return false;

							} );

						};

						return { init : init };

				})();

				Page.init();

			});
		</script>
	</body>
</html>	
