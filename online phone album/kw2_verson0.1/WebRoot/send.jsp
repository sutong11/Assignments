<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="java.util.*" %>

<%@ page import="java.sql.*"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>找回密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	String email = request.getParameter("email"); 
    	String username=request.getParameter("username");
    	byte b[]=username.getBytes("ISO-8859-1");
    	username=new String(b);
    	String psd="";String email_="";
    	int flag=0;
    	
    	//连接数据库，找出该用户的姓名
    	String url ="jdbc:mysql://localhost:3306/kw";
    	Class.forName("org.gjt.mm.mysql.Driver").newInstance(); 
    	Connection conn= DriverManager.getConnection(url,"root","root");
    	String sql="select * from user where user_name=?";
    	PreparedStatement pStmt = conn.prepareStatement(sql);
    	pStmt.setString(1,username);
    	ResultSet rs=pStmt.executeQuery(); 
    	System.out.println("ResultSet");
    	while(rs.next()){
    		psd=rs.getString("password");
    		email_=rs.getString("email");
    		System.out.println("password="+psd);
    		System.out.println("email_="+email_);
    	}
    	rs.close();     pStmt.close();    conn.close(); 
    	if(email.equals(email_)){
    		flag=1;
    	} 
    	%>
    	<script>
    		if(<%=flag%>==0){
    			alert("邮件地址与用户名不匹配!<%=email%>和<%=email_%>");
    			window.location="fgtpsd.jsp"
    		}
    	</script>
    	
    	<% 
    	if(flag==0){
    		return;
    	}
    	Properties props = new Properties();
    	props.put("mail.smtp.host","smtp.qq.com");//指定SMTP服务器
    	props.put("mail.smtp.auth","true");//指定是否需要SMTP验证
    	try{
    		Session mailSession = Session.getDefaultInstance(props);
    		mailSession.setDebug(true);//是否在控制台显示debug信息
    		
    		Message message=new MimeMessage(mailSession);
    		message.setFrom(new InternetAddress("2514384194@qq.com"));//发件人
    		message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));//收件人
    		
    		message.setSubject("酷我相册找回密码");//邮件主题
    		message.setText("您酷我相册的账号密码是："+psd);//邮件内容
    		message.saveChanges();
    		
    		Transport transport = mailSession.getTransport("smtp");
    		transport.connect("smtp.qq.com","2514384194","13972880026");
    		transport.sendMessage(message,message.getAllRecipients());
    		transport.close();
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
    %>
    <script>
    	if(<%=flag%>==1){
    		alert("已发送至邮箱，请注意查收!");
    		window.location="index.jsp"
    	}
    </script>
  </body>
</html>
