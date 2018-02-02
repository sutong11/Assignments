<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.wust.model.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>评论</title>
    		<script src="js/jquery.min.js">
</script>
    		<script src="js/jquery1.6.1.js">
</script>
		<script src="js/bootstrap.js">
</script>
    	 <script src="js/comment.js">

	 </script>
	<link rel="stylesheet" type="text/css" href="css/comment.css">
	<%
		String photo_id = request.getParameter("photo_id");
		String album_id = request.getParameter("album_id");
		System.out.println("album_id======"+album_id);
		int id = -1;
		if(null != photo_id){
			id = Integer.parseInt(photo_id);
		}
		session.setAttribute("commentphotoid",id);
		int aid = -1;
		if(null != album_id){
			aid = Integer.parseInt(album_id);
		}
		
	String cur_jsp = "comment.jsp?photo_id="+id+"&album_id="+album_id;
	session.setAttribute("target", cur_jsp);
	String flg = "Y";
	String tempflg = null;
	Object objflg = session.getAttribute("cflg");
	if(null == objflg){
		session.setAttribute("cflg", "Y");
	}else{
		flg = session.getAttribute("cflg").toString();
		session.setAttribute("cflg", "Y");
	}
	System.out.println("----cflg="+flg);
		
	 %>
	 
	 <%
	 	int comNum = 0;
	 	int comNum1 = 0;
	 	List comList = null;
	 	List userList = null;
	 	Object obj = session.getAttribute("tempList");
	 	Object objuser = session.getAttribute("userList");
	 	if(null != obj){
	 		comList = (List) obj;
	 		comNum = comList.size();
	 	}
	 	if(null != objuser){
	 		userList = (List) objuser;
	 		comNum1 = userList.size();
	 	}
	 	System.out.println("comNum="+comNum);
	 	System.out.println("comNum1="+comNum1);
	  %>
  </head>
  
  <body onload="load()">
   	<input type="hidden" name="mytemp" id="mytemp" value="<%=flg %>"/>
  
  <%
  	Object objp = session.getAttribute("photo");
  	Photo photo = null;
  	if(null != objp){
  		photo = (Photo) objp;
  	}
  	if(null != photo){
  	String s = "";
  	s = basePath+"/upload//"+photo.getPhotoUrl();
   %>
   
  <form id="pcomment">
  	<input type="hidden" name="comment.user.userId" id="uid" value="${user.userId}"/>
  	<input type="hidden" name="comment.photo.photoId" id="pid" value="<%=id %>"/>
  	<input type="hidden" name="album_id" id="album_id" value="<%=album_id %>"/>
   		<img src="image/bg.JPG" class="blur"/>
  		<div class="header">
			<div class="h_left">酷我相册</div>
			<div class="h_right">
				<a href="picture_classic.jsp?album_id=<%=album_id%>" style="color:white">返回</a>
			</div>
		</div>
  		<div class="pic1"><img class="pic" src="<%=s %>"></div>
  		<div><textarea disabled="disabled"  class="piccom" name="oldcom" id="oldcom">
  		<%if(comNum>0 && comNum == comNum1){
  			Comment tempCom = null;
  			User tempuser = null;
  			for(int i=0;i<comNum;i++){
  				tempCom = (Comment) comList.get(i);
  				tempuser = (User) userList.get(i);%>
  				
<%=tempCom.getCommentDate().toString().substring(0,10) %>
用户<%=tempuser.getUserName()%>说：
<%=tempCom.getCommentInfo() %>
<%} }%>
  		</textarea></div>
  		<textarea class="com" id="comm" name="comment.commentInfo" onfocus="comfoc()">写点评论吧~~</textarea>
  		<div class="btn"><input type="button" value="发表评论" onclick="makecomm()"></div>
  	</form>
  	<%}else{ %>
  	 <form>
  	<input type="hidden" name="pid" id="pid" value="<%=id %>"/>
  		<img src="image/bg.JPG" class="blur"/>
  		<div class="header">
			<div class="h_left">酷我相册</div>
			<div class="h_right">
				<a href="picture_classic.jsp?album_id=<%=album_id%>" style="color:white">返回</a>
			</div>
		</div>
  		<div class="pic"><img src="img/2.jpg"></div>
  	</form>
  	
  	<%} %>
  </body>
</html>
