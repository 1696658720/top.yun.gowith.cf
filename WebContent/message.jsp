<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传结果</title>
</head>
<body>
<center>
<h1>${message}</h1><br>
<h2>该页面显示3秒后自动跳转到云盘主页</h2>
<script type="text/javascript">
setTimeout(function(){
	window.location="http://yun.gowith.cf/";
	},3000)
</script>
<h2><a href="index.jsp">直接跳转</a></h2>
</center>
</body>
</html>