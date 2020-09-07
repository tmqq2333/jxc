<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.util.regex.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="./css/comm.css"/>
	</head>
<body>
<%
List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
String pagestr = (String)request.getAttribute("pagestr");
%>
<div class="path">当前位置:/夜鹰进销存系统/库存管理/库存查询             <span class="rightbox"><input type="text" id="tbkey"/><input type="button" id="btnsearch" value="查询"/></span></div>

<table border="0" cellspacing="0" cellpadding="0" class="tb tblist">
       <tr>
       	 <td style="width:50px;">ID</td><td style="width:150px;">产品图片</td><td style="width:150px;">类别</td><td style="width:150px;">编号</td><td style="width:150px;">二维码</td><td>产品名称</td><td style="width:80px;">价格</td><td style="width:120px;">数量</td>
       </tr>
       <%for (Map<String, Object> m : list) { %>
        <tr>
       		<td><%=m.get("id")%></td>
       		<td><img src="./upload/<%=m.get("imgurl")%>" style="width:120px;height:38px;padding:5px 0px;"></td>
       		<td data-typeid="<%=m.get("typeid")%>" class="typename" title="<%=m.get("fullpath")%>"><%=m.get("typename")%></td>
       		<td><%=m.get("pronum")%></td>
       		<td><img src="./twocode/<%=m.get("procodeurl")%>" style="width:38px;height:38px;padding:5px 0px;"></td>
       		<td><%=m.get("proname")%></td>       		
       		<td><%=m.get("price")%>.00</td>
       		<td><%=m.get("procount")%></td>
       </tr>
    	<% } %>
    </table>
    
    <div class="pager">
       		<%=pagestr%>
    </div>
    
    <script src="./js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    
    var key = localStorage.getItem("keyprosearch");
    $("#tbkey").val(key);
    $("#btnsearch").click(function () {
        var key = $("#tbkey").val();
        localStorage.setItem("keyprosearch", key);
        location.href = "prolistforsearch?key=" + key+"&classid=0";	;
    });
  
    </script>

</body>
</html>