function loadPic() {
	var temp = document.getElementById("mytemp").value;
	if (temp == "Y") {
	var params = document.getElementById("albumid").value;
	$.ajax( {
		url : "photo_findPhotosById",
		type : "POST",
		data : params,
		async : false,
		success : function(data) {
			window.location.href = "jump.jsp";
			var check = eval("(" + data + ")");
			if (check.succ != 0) {
				//alert("这个相册没有照片！");
			} else {
			}
		}
	});
	}
}

function deleteP(a) {
	var photoid = document.getElementById("photo" + a).value;
	document.getElementById("tempdelete").value = photoid;
	var result = confirm("确定删除这个照片？");
	if (result) {
		var params = $("#photo-for-delete").serialize();
		$.ajax( {
			url : "photo_deletePhoto",
			type : "POST",
			data : params,
			async : false,
			success : function(data) {
				window.location.href = "jump.jsp";
				var check = eval("(" + data + ")");
				if (check.succ != 0) {
					alert("出了点小问题。未删除！");
				} else {
					$('#delete-msg').html('照片已删除！');
					$('#delete-msg').fadeIn();
					$('#delete-msg').fadeOut(5000);
				}
			}
		});

	}
}