<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/comm.css"/>
</head>
<body>
<%request.setCharacterEncoding("utf-8");%>
<p class="path">当前位置：夜鹰进销存管理系统/商品中心/新增类别</p>
<form action="../tbtypeadd" method="post">
<input type="hidden" name="parentid" value="<%=request.getParameter("pid")%>"/>
<table border="0" cellspacing="0" cellpadding="0" class="tb">
	<tr><td class="lable">类别名称</td><td><input type="text" name="typename"/></td></tr>	
	<tr><td class="lable">&nbsp;</td><td><input type="submit" name="btnsave" value="保存" id="btnsave"/></td></tr>
</table>
</form>
</body>
</html>