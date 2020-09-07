<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.util.regex.*" %>
<%
    Map<String, Object> objbyid = (Map<String, Object>)request.getAttribute("objbyid");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="./css/comm.css"/>
		<script src="./ueditor/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
		<script src="./ueditor/ueditor.all.js" type="text/javascript" charset="utf-8"></script>
		<script src="./ueditor/lang/zh-cn/zh-cn.js" type="text/javascript" charset="utf-8" ></script>
	</head>
	<body>
		<form action="proedit" method="post" enctype="multipart/form-data">
			<p class="path">当前位置：夜鹰进销存系统/商品中心/编辑商品</p>
			<table border="0" cellspacing="0" cellpadding="0" class="tb">
				<tr>
					<td class="lable">类别</td><td id="typeidbox" data-typeid="<%=objbyid.get("typeid").toString()%>">
					
					    <select name="typeid">
					    	<option <%=(objbyid.get("typeid").toString().equals("1"))?"selected=\"selected\"":""%> value="1">小米</option>
					    	<option <%=(objbyid.get("typeid").toString().equals("2"))?"selected=\"selected\"":""%> value="2">红米</option>
					    	<option <%=(objbyid.get("typeid").toString().equals("3"))?"selected=\"selected\"":""%> value="3">电视</option>
					    	<option <%=(objbyid.get("typeid").toString().equals("4"))?"selected=\"selected\"":""%> value="4">笔记本</option>
					    	<option <%=(objbyid.get("typeid").toString().equals("5"))?"selected=\"selected\"":""%> value="5">盒子</option>
					    	<option <%=(objbyid.get("typeid").toString().equals("6"))?"selected=\"selected\"":""%> value="6">路由器</option>
					    	<option <%=(objbyid.get("typeid").toString().equals("7"))?"selected=\"selected\"":""%> value="7">智能硬件</option>
					    	<option <%=(objbyid.get("typeid").toString().equals("8"))?"selected=\"selected\"":""%> value="8">服务</option>
					    	<option <%=(objbyid.get("typeid").toString().equals("9"))?"selected=\"selected\"":""%> value="9">社区</option>
					    </select>
				    </td>
				</tr>
				<tr><td class="lable">名称</td><td><input type="hidden" name="oldurl" value="<%=objbyid.get("imgurl")%>"/><input type="text" name="proname" value="<%=objbyid.get("proname")%>"/></td></tr>
				<tr><td class="lable">编号</td><td><input type="text" name="pronum" value="<%=objbyid.get("pronum")%>"/></td></tr>
				<tr><td class="lable">价格</td><td><input type="text" name="price" value="<%=objbyid.get("price")%>"/></td></tr>
				<tr><td class="lable">特色</td><td><input type="text" name="brief" value="<%=objbyid.get("brief")%>"/></td></tr>
				<tr><td class="lable">产地</td><td><input type="text" name="address" value="<%=objbyid.get("address")%>"/></td></tr>
				<tr><td class="lable">图片</td><td><input type="file" name="file1"/></td></tr>
				<tr><td class="lable">内容</td><td><script id="editor" type="text/plain" style="width:100%;height:200px;"></script>
				<input type="hidden" name="descriptions" id="descriptions"/>
				<input type="hidden" name="id"  value="<%=objbyid.get("id")%>"/>
				<div style="display:none;" id="htmlcontents"><%=objbyid.get("descriptions").toString()%></div>
				</td></tr>
				<tr><td class="lable">&nbsp;</td><td><input type="submit" name="btnsave" value="保存" id="btnsave"/></td></tr>
			</table>
		</form>
<script src="./js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
			$(function(){
				var temptypeid=$("#typeidbox").attr("data-typeid");
				$.post("./ajax?" + Math.random(), {rnum:"6",typeid:temptypeid}, function (res) {
		         	$("#typeidbox").html(res.msg);
		     	});
				
			});
			//Html编码获取Html转义实体
			function htmlEncode(value){
			  return $('<div/>').text(value).html();
			}
			//Html解码获取Html实体
			function htmlDecode(value){
			  return $('<div/>').html(value).text();
			}

 			var ue = UE.getEditor('editor');
 			$("#btnsave").click(function(e){
 				var str=UE.getEditor('editor').getContent();
 				//str=htmlEncode(str);
 				$("#descriptions").val(str);
 				
 			});
 			
 			 ue.ready(function () {
 				 
 				var str=$("#htmlcontents").html();
 				//console.log(str);
 				//str=htmlDecode(str);
 				//console.log(str);
 				ue.execCommand('insertHtml',str);
 	         });
 </script>
	</body>
</html>