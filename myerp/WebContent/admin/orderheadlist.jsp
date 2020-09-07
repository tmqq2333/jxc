<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/comm.css"/>
</head>
<body>
<p class="path">商城后台系统/订单中心/订单列表</p>

<table border="0" cellspacing="0" cellpadding="0" class="tb tblist">
       <tr>
       	  <td style="width:160px;">订单号</td><td style="width:100px;">姓名</td><td style="width:100px;">电话</td><td>地址</td><td style="width:100px;">金额</td><td style="width:180px;">时间</td><td style="width:120px;">操作</td>   
       </tr>
       <%for (Map<String, Object> m : list) { %>
        <tr>
       		<td><%=m.get("orderid")%></td>
       		<td><%=m.get("sname")%></td>
       		<td><%=m.get("stel")%></td>
       		<td><%=m.get("saddress")%></td>
       		<td><%=m.get("sumprice")%></td>
       		<td><%=m.get("ctime")%></td>
       		<td><a href="orderview?orderid=<%=m.get("orderid")%>">查看详细</a><a href="orderdelete?orderid=<%=m.get("orderid")%>">删除</a>  </td>
       </tr>
    	<% } %>
    </table>
    
    <div class="pager">
       		<%=pagestr%>
    </div>
    
   
</body>
</html>