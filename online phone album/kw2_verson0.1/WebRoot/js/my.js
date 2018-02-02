function userLogin() {
	//封装所有的form表单里所有参数到params数组里
	var params = $("#login-form").serialize();
	console.log(params);
	$.ajax( {
		url : "user_userLogin",
		type : "POST",
		data : params,
		async : false,
		success : function(data) {
			var check = eval("(" + data + ")");
			if (check.succ != 0) {
				if(check.urlAndQuery!=null){
					window.location = check.urlAndQuery;
				}
				else{
					window.location = "success.jsp";
				}
			} else {
				$('#login-msg').html('提示：用户名或密码错误！');
				$('#login-msg').fadeIn();
				$('#login-msg').fadeOut(3000);
			}
		}
	});
}
function userLogin1() {
	//封装所有的form表单里所有参数到params数组里
	var params = $("#login-form1").serialize();
	console.log(params);
	$.ajax( {
		url : "user_userLogin",
		type : "POST",
		data : params,
		async : false,
		success : function(data) {
			var check = eval("(" + data + ")");
			if (check.succ != 0) {
				if(check.urlAndQuery!=null){
					window.location = check.urlAndQuery;
				}
				else{
					window.location = "success.jsp";
				}
			} else {
				$('#login-msg').html('提示：用户名或密码错误！');
				$('#login-msg').fadeIn();
				$('#login-msg').fadeOut(3000);
			}
		}
	});
}
function enterLogin(){
	if (event.keyCode==13)   //回车键的键值为13
		document.getElementById("sub_btn").click();  //调用登录按钮的登录事件
}

/*****************************个人设置 start***************************/
//修改用户信息
function editUserInfo() {
	var params = $("#changeInfo-form").serialize();
	$.ajax( {
		url : "user_editUserInfo",
		type : "POST",
		data : params,
		async : false,
		success : function(data) {
			var check = eval("(" + data + ")");
			if(check.succ==1){
				alert('修改成功！');
			}
			window.location = "reset_userinfo.jsp";
		}
	});
}
//修改密码
function newpsw(){
	var psw=$('#psw1').val();
	var uid=$('#uid').val();
	$.ajax( {
		url : "user_editUserPassword",
		type : "POST",
		data : {uid:uid,psw:psw},
		async : false,
		success : function(data) {
			var check = eval("(" + data + ")");
			if(check.succ==1){
				alert('修改成功！');
			}
			window.location = "reset_password.jsp";
		}
	});
}
function pswCheck(pswid,tipid,subid){
	$("#"+subid).attr("disabled","disabled");
	if($("#"+pswid).val().length<6 || $("#"+pswid).val().length>16)
	{
		$("#"+tipid).html("请输入6-16位数字或字母");
	}else{
		var psw=$('#psw').val();
		var psw1=$('#psw1').val();
		var psw2=$('#psw2').val();
		if(pswid=="psw1"){
			if(psw==psw1){
				$("#"+tipid).css({"color":"red"});
				$("#"+tipid).html("新密码不能与原密码一致");
			}else{
				if(psw2.length != 0){
					if(psw1==psw2){
						$("#"+tipid).css({"color":"red"});
						$("#"+tipid).html("ok!");
						$("#psw2tip").html("ok!");
						$("#"+subid).removeAttr("disabled");
					}else{
						$("#"+tipid).css({"color":"red"});
						$("#"+tipid).html("密码不一致");
					}
				}else{
					$("#"+tipid).css({"color":"red"});
					$("#"+tipid).html("ok!");
				}
			}
		}else{
			if(psw1.length != 0){
				if(psw1==psw2){
					$("#"+tipid).css({"color":"red"});
					$("#"+tipid).html("ok!");
					$("#psw1tip").html("ok!");
					$("#"+subid).removeAttr("disabled");
				}else{
					$("#"+tipid).css({"color":"red"});
					$("#"+tipid).html("密码不一致");
				}
			}else{
				$("#"+tipid).css({"color":"red"});
				$("#"+tipid).html("ok!");
			}
		}
	}
}
/*****************************个人设置 over ******************************/



/************************* 注册 start****************************/
function userRegister() {
	var params = $("#register-form").serialize();
	$.ajax( {
		url : "user_addUser",
		type : "POST",
		data : params,
		async : false,
		success : function(data) {
			var check = eval("(" + data + ")");	
			if (check.succ != 0) {	
				alert("注册成功!");
				window.location = "success.jsp";
			} else {
				$('#register-msg').html('提示：用户名已存在注册失败!');
				$('#register-msg').fadeIn();
				$('#register-msg').fadeOut(3000);
				
			}
		}
	});
}
function emailOnfocus()
{
	$("#register-sub").attr("disabled","disabled");
	$("#email_tips").css({"color":"#5E5E5E"});
	$("#email_tips").html("注册邮箱");
}
function emailOnkeyup(){
	if($("#email").val()==0)
	{
		$("#register-sub").attr("disabled","disabled");
	}
	else if(!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/))
	{
		$("#register-sub").attr("disabled","disabled");
	}
	else
	{
		$("#email_tips").css({"color":"#5E5E5E"});
		$("#email_tips").html("*");
		$("#register-sub").removeAttr("disabled");
	}
}
function emailOnblur()
{
	if($("#email").val()==0)
	{
		$("#email_tips").html("邮箱不能为空");
		$("#email_tips").css({"color":"red"});
		$("#register-sub").attr("disabled","disabled");
	}
	else if(!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/))
	{
		$("#email_tips").css({"color":"red"});
		$("#email_tips").html("邮箱格式不正确");
		$("#register-sub").attr("disabled","disabled");
	}
	else
	{
		$("#email_tips").css({"color":"#5E5E5E"});
		$("#email_tips").html("*");
		$("#register-sub").removeAttr("disabled");
	}
}

function passwordOnfocus(){
	$("#register-sub").attr("disabled","disabled");
	$("#password_tips").css({"color":"#5E5E5E"});
	$("#password_tips").html("请设置6-16位的密码");
	
}
function passwordOnkeyup(){
	if($("#password").val().length<6 || $("#password").val().length>16)
	{
		$("#register-sub").attr("disabled","disabled");
	}
	else
	{
		$("#password_tips").css({"color":"#5E5E5E"});
		$("#password_tips").html("*");
		$("#register-sub").removeAttr("disabled");
	}
}
function passwordOnblur(){
	if($("#password").val().length<6 || $("#password").val().length>16)
	{
		$("#password_tips").css({"color":"red"});
		$("#password_tips").html("密码长度需要为6-16位");
		$("#register-sub").attr("disabled","disabled");
	}
	else
	{
		$("#password_tips").css({"color":"#5E5E5E"});
		$("#password_tips").html("*");
		$("#register-sub").removeAttr("disabled");
	}
}


function userNameOnfocus(){
	$("#register-sub").attr("disabled","disabled");
	$("#username_tips").css({"color":"#5E5E5E"});
	$("#username_tips").html("填写2-10个字符的用户名");
}
function userNameOnkeyup(){
	if($("#userName").val().length<2 || $("#userName").val().length>10)
	{
		$("#register-sub").attr("disabled","disabled");
	}
	else
	{
		$("#username_tips").css({"color":"#5E5E5E"});
		$("#username_tips").html("*");
		$("#register-sub").removeAttr("disabled");
	}
}
function userNameOnblur(){
	if($("#userName").val().length<2 || $("#userName").val().length>10)
	{
		$("#username_tips").css({"color":"red"});
		$("#username_tips").html("用户名长度为2-10个字符");
		$("#register-sub").attr("disabled","disabled");
	}
	else
	{
		$("#username_tips").css({"color":"#5E5E5E"});
		$("#username_tips").html("*");
		$("#register-sub").removeAttr("disabled");
	}
}


function phoneOnfocus(){
	$("#register-sub").attr("disabled","disabled");
	$("#phone_tips").css({"color":"#5E5E5E"});
	$("#phone_tips").html("请设置手机号码");
	
}
function 
 checkMobile(str) {
    var 
 re = /^1\d{10}$/
    if (re.test(str)) {
        return true;
    } else {
       return false;
    }
}

function phoneOnkeyup(){
	if($("#phone").val()==0)
	{
		$("#register-sub").attr("disabled","disabled");
	}
	else if(!checkMobile($("#phone").val()))
	{
		$("#register-sub").attr("disabled","disabled");
	}
	else
	{
		$("#phone_tips").css({"color":"#5E5E5E"});
		$("#phone_tips").html("*");
		$("#register-sub").removeAttr("disabled");
	}
}

function phoneOnblur()
{
	if($("#phone").val()==0)
	{
		$("#phone_tips").html("手机号不能为空");
		$("#phone_tips").css({"color":"red"});
		$("#register-sub").attr("disabled","disabled");
	}
	else if(!checkMobile($("#phone").val()))
	{
		;
		$("#phone_tips").css({"color":"red"});
		$("#phone_tips").html("手机号格式不正确");
		$("#register-sub").attr("disabled","disabled");
	}
	else
	{
		$("#phone_tips").css({"color":"#5E5E5E"});
		$("#phone_tips").html("*");
		$("#register-sub").removeAttr("disabled");
	}
}
/************************* 注册 over****************************/


