<br><%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML >
<div id="header">
	<div class="container" id="nav">
		<div id="logo">
			<a href="index.jsp">酷我相册</a>
		</div>
		<div id="login-area" class="pull-right">
			
				<button id="J_Login" hidefocus="true" data-toggle="modal"
					data-target="#login" class="login-btn">
					登陆
				</button>
				<button id="J_Register" hidefocus="true" data-toggle="modal"
					data-target="#register" class="register-btn">
					注册
				</button>
			
		</div>
	</div>
</div>
<div id="search-suggest" style="z-index:9999">
	<ul id="search-reasult">
	
	</ul>
</div>
<!--定义一个modal的div包围整个登陆对话框内容.hide表示隐藏 .fade是动画效果-->
<div class="modal hide fade" id="login">
	<!--用.modal-header定义一个对话框的头-->
	<div class="modal-header">
		<!--添加一个关闭的选项-->
		<a href="#" class="close" data-dismiss="modal">x</a>
		<h4>
			用户登录
		<h4>
	</div>
	<!--用.modal-body定义登陆对话框的主体内容-->
	<div class="modal-body">
		<form id="login-form" class="form-horizontal">
			<div class="control-group">
				<label class="control-label">
					用户名
				</label>
				<div class="controls">
					<input type="text" name="user.userName"  autocomplete="off">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					密码
				</label>
				<div class="controls">
					<input type="password" name="user.password">
				</div>
			</div>
			<div>
				<font color="red"><div align="center" id="login-msg"></div> </font>
				<button id="sub_btn" class="btn  btn-primary pull-right"
					type="button" onclick=userLogin();>
					登录
				</button>
			</div>
		</form>
	</div>
</div>

<!--定义一个modal的div包围整个注册对话框内容.hide表示隐藏 .fade是动画效果-->
<div class="modal hide fade" id="register">
	<!--用.modal-header定义一个对话框的头-->
	<div class="modal-header">
		<!--添加一个关闭的选项-->
		<a href="#" class="close" data-dismiss="modal">x</a>
		<h4>
			用户注册
			<h4>
	</div>
	<!--用.modal-body定义注册对话框的主体内容-->
	<div class="modal-body">
		<form id="register-form" class="form-horizontal">
			<div class="control-group">
				<label class="control-label">
					用户名  :
				</label>
				<div class="controls">
					<input id="userName" type="text" name="user.userName"
						onfocus="userNameOnfocus()" onkeyup=userNameOnkeyup();
						onblur="userNameOnblur()" required>
					<span id="username_tips"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					密码 :
				</label>
				<div class="controls">
					<input id="password" type="password" name="user.password"
						onfocus="passwordOnfocus()" onkeyup=passwordOnkeyup();
						onblur="passwordOnblur()" required>
					<span id="password_tips"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >
					邮箱 :
				</label>
				<div class="controls">
					<input id="email" type="text" name="user.email"
						onfocus="emailOnfocus()" onkeyup=emailOnkeyup();
						onblur="emailOnblur()"  autocomplete="off">
					<span id="email_tips"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >
					手机号 :
				</label>
				<div class="controls">
					<input id="phone" type="text" name="user.phone"
						onfocus="phoneOnfocus()" onkeyup=phoneOnkeyup();
						onblur="phoneOnblur()"  autocomplete="off">
					<span id="phone_tips"></span>
				</div>
			</div>
			<div style="text-align: right">
				<font color="red"><div align="center" id="register-msg"></div>
				</font>
				<button id="register-sub" type="button" class="btn btn-primary"
					disabled="disabled" onclick=userRegister();>
					提交
				</button>
				<button type="reset" class="btn btn-primary ">
					重置
				</button>
			</div>
		</form>
	</div>
</div>
<div class="content">
	<div class="c_pic"></div>
	<div class="c_form">
		<div class="f_top"></div>
		<div>
			<center><form class="l_form" id="login-form1">
				<div style="color: white">请使用酷我账户登录</div>
				<div style="color: white">用户名：<input type="text" name="user.userName"></div>
				<div style="color: white">密&nbsp;&nbsp;码：<input type="password" name="user.password"></div>
				<div style="color: white"><input type="checkbox">记住密码&nbsp;&nbsp;&nbsp;&nbsp;      
					<a href="fgtpsd.jsp" style="text-decoration: none">忘记密码？</a>
				</div>
				<div class="h_bottom"><input type="button" id="h_login" value="登录" onclick=userLogin1();></a>
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="h_reg" value="注册" data-toggle="modal"
					data-target="#register">
				</div><br/>
			</form>
			<div class="c_bottom">最新公告</div>
			<a href="#" style="color: white">酷我相册，珍藏照片</a>
			</center>
		</div>
	</div>
	<div class="footer">
		<div  class="ft_top">
			<a class="ft_a" href="#">查看更多</a>
		</div>
		<div  class="ft_content">
			<center><h1>为散落的相片找个家</h1></center><br/>
			<ul>
				<li><h3>.相片快捷上传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.多人相册亲密分享
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.随时随地浏览相册</h3>
				</li>
			</ul>
		</div>
	</div>
	<div class="b_footer">
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
</div>
<script type="text/javascript">
	$(function() {
		$(document).bind("click",function(){
			$("#search-suggest").hide();
		})
	})
</script>

