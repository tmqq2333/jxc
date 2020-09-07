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
<title>用户列表</title>
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
	background-color:#ff6600;
	color:#fff;
}

.tblist tr:hover a{
	color:#fff;
}

</style>
</head>
<body>

<p class="path">当前位置：夜鹰进销存系统 >用户中心>用户列表</p>

<table class="tblist"  cellspacing="0" cellpadding="0">
<tr><td style="width:60px;">ID</td><td style="width:120px;">用户名</td><td style="width:120px;">密码</td><td style="width:120px;">电话</td><td>备注</td><td style="width:120px;">真实姓名</td><td style="width:300px;">操作</td></tr>

<%for (Map<String, Object> m : list) { %>
<tr><td><%=m.get("id")%></td><td><%=m.get("username")%></td><td>******</td><td><%=m.get("tel")%></td><td><%=m.get("memo")%></td><td><%=m.get("truename")%></td><td> <a href="admin/userhasrolemain.jsp?userid=<%=m.get("id")%>">角色分配</a> <a href="usersedit?id=<%=m.get("id")%>">编辑</a><a href="usersdelete?id=<%=m.get("id")%>">删除</a></td></tr>
<%}%>

</table>
<div><%=pagestr%>  </div>
<script src="js/jquery-1.11.0.js"></script>
<script>
     function fndelete() {
         var flag=confirm("确认删除此信息吗?一旦删除，不能恢复!")
         if (!flag) {
             return false;   
         }
     }

 </script>

</body>
</html>