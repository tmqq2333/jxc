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
<title>列表</title>
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
<p class="path">当前位置：夜鹰进销存系统 >权限配置>已经分配的权限</p>
<table class="tblist"  cellspacing="0" cellpadding="0">
<tr><td style="width:60px;"><input id="cbSelectAll" type="checkbox"/></td><td style="width:60px;">ID</td><td>菜单名称</td></tr>
<%for (Map<String, Object> m : list) { %>
<tr><td class="tdcenter"><input type="checkbox" value="<%=m.get("id")%>"  name="select"></td>
<td><%=m.get("id")%></td><td><%=m.get("MenuName")%></td></tr>
<%}%>

</table>
<div>  &nbsp;  <input id="addSelect" class="bgbtn2" type="button" value="移除"  /> &nbsp;&nbsp; 
 <%=pagestr%>  </div>
<script type="text/javascript" src="./js/jquery-1.11.0.js"></script>
    <script type="text/javascript">
        var roleid ="<%=request.getParameter("roleid")%>";
        $("#cbSelectAll").click(function () {
            $("input[type='checkbox'][name='select']").prop("checked", $(this).prop("checked"));
        });
        $("#addSelect").click(function () {
            if (!($("input[name='select']:checked").length > 0)) {
                alert("请选择一条需要操作的记录");
                return false;
            }

            var s = "0";
            $("input[name='select']:checked").each(function (index, item) {
                s += "," + $(item).val();
            });
            s+= ",0";
            $.post("./ajax?" + Math.random(), { rnum: "2", idstr: s, roleid: roleid }, function (res) {
                location.reload();
                window.parent.frames["left"].document.location.reload();
            });
        });
    </script>


</body>
</html>