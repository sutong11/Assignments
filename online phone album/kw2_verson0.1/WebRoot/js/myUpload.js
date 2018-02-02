function uploadimgtest() {
	var albumName;
	var obj = document.getElementById("tfeestype").value;
	if (obj == null) {
		alert("请选择您要上传的相册！");
		albumName = "请选择您要上传的相册！";
		return false;
	}
	albunName = obj;
	if (albunName == null) {
		return false;
	}
	return true;
}
function uploadimginf() {
	var params = $("#photo_inf").serialize();
	$.ajax( {
		url : "photo_savePhotoInf",
		type : "POST",
		data : params,
		async : false,
		success : function(data) {
			var check = eval("(" + data + ")");
		}
	});

}
