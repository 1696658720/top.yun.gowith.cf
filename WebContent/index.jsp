<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的云盘</title>
</head>
<body style="background:black;color:white">

<div align="center">
<h1>我的云盘</h1>
<script type="text/javascript">
function filedirsr(){
	document.getElementById("filedirsr").style.display="";
	document.getElementById("uploadsr").style.display="none";
	document.getElementById("downloadsr").style.display="none";
}
function uploadsr(){
	document.getElementById("filedirsr").style.display="none";
	document.getElementById("uploadsr").style.display="";
	document.getElementById("downloadsr").style.display="none";
}
function downloadsr(){
	document.getElementById("filedirsr").style.display="none";
	document.getElementById("uploadsr").style.display="none";
	document.getElementById("downloadsr").style.display="";
}
</script>
<button style="color:orange;text-decoration:none;font-weight: bold" onclick="filedirsr()">文档管理</button>
<button style="color:blue;text-decoration:none;font-weight: bold" onclick="uploadsr()">文件上传</button>
<button style="color:green;text-decoration:none;font-weight: bold" onclick="downloadsr()">离线下载</button>
<br><br>
<form id="filedirsr" method="post" action="" enctype="multipart/form-data" style="display:">
<table border="5">
<tr>
<td>文件名称</td>
<td>创建时间</td>
<td>文件大小</td>
<td>下载地址</td>
</tr>
</table>
</form>
<form id="uploadsr" method="post" action="/top.yun.gowith.cf/Upload" enctype="multipart/form-data" style="display:none">
	<input type="file" name="uploadFile" />
	<input type="submit" value="上传" />	
</form>
<form id="downloadsr" method="post" action="/top.yun.gowith.cf/Download" style="display:none">
	<input type="text" name="downloadFile" />
	<input type="submit" value="下载" />
</form>
</div>

</body>
</html>