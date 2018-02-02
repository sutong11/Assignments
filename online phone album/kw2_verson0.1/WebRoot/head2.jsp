<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML >
<div id="header">
	<div class="container" id="nav">
		<div id="logo">
			<a href="index.jsp">酷我相册</a>
		</div>
	</div>
</div>
<div class="back">
	<a href="success.jsp">返回</a>
</div>

<script type="text/javascript">
	$(function() {
		$(document).bind("click",function(){
			$("#search-suggest").hide();
		})
	})
</script>

