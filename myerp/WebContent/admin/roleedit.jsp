<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.util.regex.*" %>
<%
   Map<String, Object> objbyid = (Map<String, Object>)request.getAttribute("objbyid");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑用户</title>
<link rel="stylesheet" type="text/css" href="css/comm.css"/>
</head>
<body>
<p class="path">当前位置：夜鹰进销存管理系统/系统配置/编辑角色</p>
<form action="roleedit" method="post">
<input type="hidden" name="id" value="<%=objbyid.get("id")%>"/>
<table class="tb">
	<tr><td class="lable">角色名称</td><td><input type="text" name="rolename" value="<%=objbyid.get("rolename")%>"/></td></tr>
	<tr><td class="lable">备注</td><td><input type="text" name="memo" value="<%=objbyid.get("memo")%>"/></td></tr>
	<tr><td class="lable">&nbsp;</td><td><input type="submit" id="btnsave" value="保存" /></td></tr>
</table>
</form>

</body>
</html>

