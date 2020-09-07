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
<p class="path">当前位置:/夜鹰进销存系统/进货管理/商品列表</p>

<table border="0" cellspacing="0" cellpadding="0" class="tb tblist">
       <tr>
       	 <td style="width:50px;"><input type="checkbox" id="cbSelectAll" style="width:30px;"/></td><td style="width:150px;">产品图片</td><td style="width:150px;">编号</td><td style="width:150px;">二维码</td><td>产品名称</td><td style="width:80px;">价格</td>
       </tr>
       <%for (Map<String, Object> m : list) { %>
        <tr>
       		<td><input type="checkbox" value="<%=m.get("id")%>" class="cb"/></td>
       		<td><img src="./upload/<%=m.get("imgurl")%>" style="width:120px;height:35px;padding:5px 0px;"></td>
       		<td><%=m.get("pronum")%></td>
       		<td><img src="./twocode/<%=m.get("procodeurl")%>" style="width:38px;height:38px;padding:5px 0px;"></td>
       		<td><%=m.get("proname")%></td>       		
       		<td><%=m.get("price")%></td>
       </tr>
    	<% } %>
    </table>
    
    <div class="pager">
       <input type="button" value="添加" id="addSelect" class="btn"/>	<%=pagestr%>
    </div>
    
    <script src="./js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
    $("#cbSelectAll").click(function () {
        $("input.cb").prop("checked", $(this).prop("checked"));
    });
    $("#addSelect").click(function () {
        if (!($("input.cb:checked").length > 0)) {
            alert("请选择一条需要操作的记录");
            return false;
        }

        var s = "0";
        $("input.cb:checked").each(function (index, item) {
            s += "," + $(item).val();
        });
        s+= ",0";
        $.post("./ajax?" + Math.random(), { rnum: "8", idstr: s}, function (res) {
            location.reload();
            window.parent.frames["right"].document.location.href="./orderright";
            //window.parent.frames["right"].document.location.reload();
        });
    });
    </script>

</body>
</html>