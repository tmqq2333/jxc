<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.util.regex.*" %>
<%
List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>

		</title>
		<link rel="stylesheet" type="text/css" href="css/comm.css" />
		<style type="text/css">
			html {
				width: 100%;
				height: 100%;
			}
			
			body {
				width: calc(100% - 1px);
				height: 100%;
				border-right: solid 1px #eee;
				font-size: 12px;
			}
			
			.leftitem {
				width: 100%;
				list-style: none;
				padding-top: 2px;
			}
			
			.leftitem li {
				height: 35px;
				line-height: 35px;
				border-bottom: solid 1px #eee;
			}
			
			.leftitem li a {
				display: block;
				height: 35px;
				line-height: 35px;
				text-decoration: none;
				text-align: left;
				background-image: url(./img/arrow-right.png);
				background-repeat: no-repeat;
				background-size: 15px 15px;
				background-position: 10px 10px;
				padding-left: 30px;
			}
			
			.leftitem li a:hover {
				background-color: #b8c4cb;
				color: white;
			}
		</style>
	</head>
	<body>
		<form name="form1" method="post" action="left.aspx" id="form1">
			<ul class="leftitem" id="leftitem">
				<%for (Map<String, Object> m : list) { %>
                <li><a href="<%=m.get("Href")%>" target="<%=m.get("Target")%>"><%=m.get("MenuName")%></a></li>
                <% } %><!--不同的Href，不同的 MenuName，只要不是0级，都要main布局 一个页面不同渲染，right一一指向-->
                <li><a href="./logout"  target="_top">退出系统</a></li>
			</ul>
		</form>
		<script src="js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		   /* function getQueryString(name) {
		        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		        var r = window.location.search.substr(1).match(reg);
		        if (r != null) {
		            return unescape(r[2]);
		        }
		    }
		    var id=getQueryString("id");
		    var navstr="<li><a href=\"login.jsp\"  target=\"_top\">退出系统</a></li>";
		    switch(id)
		    {
		    	case "1":navstr="<li><a href=\"passwordedit.jsp\"  target=\"main\">修改密码</a></li><li><a href=\"logout\"  target=\"_top\">退出系统</a></li>";break;
		    	case "2":navstr="<li><a href=\"newsadd.jsp\"  target=\"main\">发布信息</a></li><li><a href=\"newslist\"  target=\"main\">信息列表</a></li><li><a href=\"logout\"  target=\"_top\">退出系统</a></li>";break;
		    	case "3":navstr="<li><a href=\"guestbooklist\"  target=\"main\">查看留言</a></li><li><a href=\"logout\"  target=\"_top\">退出系统</a></li>";break;
		    	case "4":navstr="<li><a href=\"proadd.jsp\"  target=\"main\">新增商品</a></li><li><a href=\"prolist\"  target=\"main\">商品列表</a></li><li><a href=\"logout\"  target=\"_top\">退出系统</a></li>";break;
		    	case "5":navstr="<li><a href=\"orderheadlist\"  target=\"main\">查看订单</a></li><li><a href=\"logout\"  target=\"_top\">退出系统</a></li>";break;
		    	case "6":navstr="<li><a href=\"memberlist\"  target=\"main\">查看会员</a></li><li><a href=\"logout\"  target=\"_top\">退出系统</a></li>";break;
		    }
		    $("#leftitem").html(navstr);
		    */
		    window.parent.frames["main"].location.href = "admin/blank.jsp";

		</script>
	</body>

</html>
    