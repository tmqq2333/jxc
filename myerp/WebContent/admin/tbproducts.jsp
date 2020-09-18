<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/comm.css"/>
</head>
<body>

<p class="path">当前位置：夜鹰进销存管理系统/材料中心/新增产地</p>
<form action="../tbproducts" method="post">
<input type="hidden" name="parentid" value="null"/>
<table border="0" cellspacing="0" cellpadding="0" class="tb">
	<tr><td class="lable">产地名称</td><td><input type="text" name="production"/></td></tr>	
	<tr><td class="lable">产地负责人</td><td><input type="text" name="Attendant"/></td></tr>
	<tr><td class="lable">负责人电话</td><td><input type="text" name="Tel"/></td></tr>
	<tr><td class="lable">&nbsp;</td><td><input type="submit" name="btnsave" value="保存" id="btnsave"/></td></tr>
</table>
</form>
</body>
</html>