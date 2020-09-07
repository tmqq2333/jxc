<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.util.regex.*" %>
<%@ page language="java" import="com.rz.tbtype" %>
<%
List<tbtype> list = (List<tbtype>)request.getAttribute("list");
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
	background-color:#186B93;
	color:#fff;
}

.tblist tr:hover a{
	color:#fff;
}

</style>
</head>
<body>

<p class="path">当前位置：夜鹰进销存系统 >商品中心>类别列表</p>

<table class="tblist"  cellspacing="0" cellpadding="0">
<tr><td style="width:60px;">ID</td><td style="width:200px;">类别名称</td><td style="width:120px;">父类别</td><td >路径</td><td style="width:120px;">深度</td><td style="width:300px;">操作</td></tr>

<%for (tbtype m : list) { %>
<tr><td><%=m.getId()%></td><td style="text-align:left;padding-left:5px;" class="treebox" data-level="<%=m.getlevelnum()%>"> <%=m.getTypeName()%></td><td><%=m.getParentName()%></td><td style="text-align:left;padding-left:5px;"><%=m.getFullPath()%></td><td><%=m.getlevelnum()%></td><td> <a href="admin/tbtypeadd.jsp?pid=<%=m.getId()%>&pname=<%=m.getTypeName()%>">建子类别</a> <a href="tbtypeedit?id=<%=m.getId()%>">编辑</a><a href="tbtypedelete?id=<%=m.getId()%>" class="del">删除</a></td></tr>
<%}%>

</table>
<div>  </div>
<script src="js/jquery-1.11.0.js"></script>
<script>
	$(".treebox").each(function(index,item){
		var level=$(this).attr("data-level");
		var s="";
		for(var i=0;i<=level;i++)
		{
			s+="|--";
		}
		$(this).prepend(s);
		console.log(level);
		
	});
	$(".del").click(function(e){
		var flag=confirm("确认删除此信息吗?一旦删除，不能恢复!")
        if (!flag) {
        	e.preventDefault();
            return false;   
        }
		
	});
 </script>

</body>
</html>