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
<div class="path">当前位置:/夜鹰进销存系统/系统管理/日志查询            
 <span class="rightbox"><input type="text" id="tbkey"/><input type="button" id="btnsearch" value="查询"/></span></div>

<table border="0" cellspacing="0" cellpadding="0" class="tb tblist">
       <tr>
       	 <td style="width:50px;">ID</td><td style="width:150px;">姓名</td><td style="width:150px;">类别</td><td>内容</td><td style="width:180px;">时间</td>
       </tr>
       <%for (Map<String, Object> m : list) { %>
        <tr>
       		<td><%=m.get("id")%></td>
       		<td><%=m.get("username")%></td>
       		<td data-typeid="<%=m.get("typeid")%>" class="typename"></td>
       		<td><%=m.get("msg")%></td>
       		<td><%=m.get("ctime")%></td>
       </tr>
    	<% } %>
    </table>
    
    <div class="pager">
       		<%=pagestr%>
    </div>
    
    <script src="./js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    	$("td.typename").each(function(index,item){
    		var typeid=$(item).attr("data-typeid");
    		switch(typeid)
    		{
    			case "1":$(item).text("登录日志");break; 
    			case "2":$(item).text("操作日志");break;
    			case "3":$(item).text("异常日志"); $(item).parents("tr").css({"background-color":"red","color":"#fff"});break;
    			
    		}
    	});
    	
	    var key = localStorage.getItem("keyprosearch");
	    $("#tbkey").val(key);
	    $("#btnsearch").click(function () {
	        var key = $("#tbkey").val();
	        localStorage.setItem("keyprosearch", key);
	        location.href = "tblist?key=" + key;
	    });
  
    </script>

</body>
</html>