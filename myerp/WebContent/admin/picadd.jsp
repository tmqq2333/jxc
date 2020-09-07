<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/comm.css"/>
		
	</head>
	<body>
		<form action="picadd" method="post" enctype="multipart/form-data">
			<p class="path">融智信息系统/图片中心/发布图片</p>
			<table border="0" cellspacing="0" cellpadding="0" class="tb">
				<tr><td class="lable">标题</td><td><input type="text" name="title"/></td></tr>
				<tr><td class="lable">图片</td><td><input type="file" name="file1"/></td></tr>
				<tr><td class="lable">内容</td><td><textarea name="contents"></textarea></td></tr>
				<tr><td class="lable">&nbsp;</td><td><input type="submit" name="btnsave" value="保存" id="btnsave"/></td></tr>
			</table>
		</form>
		
	</body>
</html>