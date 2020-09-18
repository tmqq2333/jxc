<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.util.regex.*" %>
<%
List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
String pagestr = (String)request.getAttribute("pagestr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>产地列表</title>
<style tyle="text/css">
*{
	margin:0;
	padding:0;
}

body{
	font-size:14px;

}
.path{
	border:solid 1px #ccc;
	margin:3px;
	height:30px;
	line-height:30px;
}
.tblist{
	width:calc(100% - 6px);
	border-collapse: collapse;
	margin:0 3px;
}

.tblist td{
	border:solid 1px #ccc;
	height:30px;
	text-align:center;
}

a{
	text-decoration:none;
	color:#333;
	margin-left:3px;
}


.tblist tr:nth-child(2n-1)
{
	background-color:#eee;
}

.tblist tr:hover{
	background-color:#186B93;
	color:#fff;
}

.tblist tr:hover a{
	color:#fff;
}

</style>
</head>
<body>

<p class="path">当前位置：夜鹰进销存系统 >材料中心>产地列表</p>

<table class="tblist"  cellspacing="0" cellpadding="0">
<tr><td style="width:60px;">ID</td><td style="width:200px;">产地名称</td><td style="width:200px;">产地负责人</td><td style="width:200px;">负责人电话</td><td style="width:300px;">操作</td></tr>

<%for (Map<String, Object> m : list) { %>
<tr><td><%=m.get("id")%></td><td><%=m.get("production")%></td><td><%=m.get("Attendant")%></td><td><%=m.get("Tel") %></td><td><a href="tbproductslistedit?id=<%=m.get("id")%>">编辑</a><a href="tbproductslistdelete?id=<%=m.get("id")%>">删除</a></td></tr>
<%}%>
</table>
<div><%=pagestr%>  </div>

</body>
</html>