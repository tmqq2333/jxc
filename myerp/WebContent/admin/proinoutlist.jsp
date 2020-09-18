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
<p class="path">当前位置:/夜鹰进销存系统/库存管理/明细查询</p>

<table border="0" cellspacing="0" cellpadding="0" class="tb tblist">
       <tr>
       	 <td style="width:50px;">ID</td><td style="width:150px;">产品图片</td><td style="width:150px;">商品名称</td><td style="width:150px;">类别</td><td style="width:150px;">编号</td><td style="width:50px;">出入标记</td><td style="width:150px;">商品基数</td><td style="width:150px;">出库数量</td><td style="width:80px;">售价</td><td style="width:80px;">总金额</td><!-- <td style="width:50px;">单位</td> --><td style="width:120px;">时间</td>   
       </tr>
       <%for (Map<String, Object> m : list) { %>
        <tr>
       		<td><%=m.get("id")%></td>
       		<td><img src="./upload/<%=m.get("proimgurl")%>" style="width:120px;height:35px;padding:5px 0px;"></td>
       		<td><%=m.get("proname")%></td>
       		<td class="typename" title="<%=m.get("protype")%>"><%=m.get("protype")%></td>
       		<td><%=m.get("pronum")%></td>
       		<td title="<%=m.get("opttype")%>"><%=m.get("opttype").equals(1)?"入库":"出库"%></td>
       		<td><%=m.get("prooldnum")%></td>
       		<td><%=m.get("proaddnum")%></td>      
       		<td><%=m.get("price")%></td>
       		<td><%=m.get("zprice")%></td> 
       		<%-- <td><%=m.get("unitname")%></td>    --%>   
       		<td><%=m.get("ctime")%></td>
       </tr>
    	<% } %>
    </table>
    
    <div class="pager">
       		<%=pagestr%>
    </div>
    <!--无js,直接打开，无需后缀jsp,且无需/admin/xxxx  -->
    
    <script src="./js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    	
    </script>

</body>
</html>