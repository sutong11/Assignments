function createalbum() {
	var params = $("#create_album_form").serialize();
	$.ajax({
		url : "album_addAlbum",
		type : "POST",
		data : params,
		async : false,
		success : function(data) {
			var check = eval("(" + data + ")");
			if (check.succ != 0) {
				window.location = "success.jsp";
			} else {
				$('#inf-msg').html('错误提示：相册名已存在!');
				$('#inf-msg').fadeIn();
				$('#inf-msg').fadeOut(4000);

			}
		}
	});
}
function pageload() {
			$.ajax({
			url : "album_findAlbums",
			type : "POST",
			data : null,
			async : false,
			success : function(data) {
				
			}
		});

	var temp = document.getElementById("mytemp").value;
	if (temp == "Y") {
				window.location.href = "jump.jsp";
	}
}

function deleteA(a) {
	var pnum = document.getElementById("numalbum" + a).value;
	var albumid = document.getElementById("album" + a).value;
	document.getElementById("tempdelete").value = albumid;
	if (pnum > 0) {
		alert("相册不是空的，不能删除！");
	} else {
		var result = confirm("确定删除这个相册？");
		if (result) {
			var params = $("#tttt").serialize();
			$.ajax({
				url : "album_deleteAlbum",
				type : "POST",
				data : params,
				async : false,
				success : function(data) {
					var check = eval("(" + data + ")");
					if (check.succ != 0) {
						alert("出了点小问题。未删除！");
					} else {
						window.location = success.jsp
						$('#delete-msg').html('相册已删除！');
						$('#delete-msg').fadeIn();
						$('#delete-msg').fadeOut(10000);
					}
				}
			});

		}
	}
}