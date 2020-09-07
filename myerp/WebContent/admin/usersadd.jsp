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

<p class="path">当前位置：夜鹰进销存管理系统/系统配置/新增用户</p>
<form action="../usersadd" method="post">
<table border="0" cellspacing="0" cellpadding="0" class="tb">
	<tr><td class="lable">用户名</td><td><input type="text" name="username"/></td></tr>
	<tr><td class="lable">密码</td><td><input type="text" name="password"/></td></tr>
	<tr><td class="lable">真实姓名</td><td><input type="text" name="truename"/></td></tr>
	<tr><td class="lable">电话</td><td><input type="text" name="tel"/></td></tr>
	<tr><td class="lable">备注</td><td><input type="text" name="memo"/></td></tr>
	<tr><td class="lable">&nbsp;</td><td><input type="submit" name="btnsave" value="保存" id="btnsave"/></td></tr>
</table>
</form>
</body>
</html>