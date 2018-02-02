function askforcom(){
	var succ = 1;
	var par = document.getElementById("pid").value;
	$.ajax( {
		url : "comment_findCommentByphotoId",
		type : "POST",
		data : {pid:par},
		async : false,
		success : function(data) {
			succ = 0;
			showcom();
		}
	});
	if(succ == 1){
		$("#oldcom").html('暂无评论');
	}	
}
function showcom(){
}

function load() {
	var par = document.getElementById("pid").value;
	$.ajax( {
		url : "photo_findPhotoById",
		type : "POST",
		data : par,
		async : false,
		success : function(data) {
			askforcom();
		}
	});
	var flg = document.getElementById("mytemp").value;
	if (flg == "Y") {
		window.location.href = "jump.jsp";
	}
}
function comfoc() {
	document.getElementById("comm").value = null;
}
function makecomm() {
	var contn = document.getElementById("comm").value;
	if (null == contn || "" == contn || "写点评论吧~~" == contn) {
		alert("请输入您要评论的内容!");
		return;
	} else {
		var params = $("#pcomment").serialize();
		$.ajax( {
			url : "comment_addcomment",
			type : "POST",
			data : params,
			async : false,
			success : function(data) {
					alert('评论成功!');
			}
		});
		window.location.href = 'jump.jsp';
	}
	
}