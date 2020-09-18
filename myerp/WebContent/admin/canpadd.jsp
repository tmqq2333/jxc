<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/comm.css"/>
		<script src="../ueditor/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
		<script src="../ueditor/ueditor.all.js" type="text/javascript" charset="utf-8"></script>
		<script src="../ueditor/lang/zh-cn/zh-cn.js" type="text/javascript" charset="utf-8" ></script>
	</head>
	<body>
		<form action="../canpadd" method="post" enctype="multipart/form-data">
			<p class="path">当前位置：夜鹰进销存系统/产品中心/新增产品</p>
			<table border="0" cellspacing="0" cellpadding="0" class="tb">
				<tr>
					<td class="lable">类别</td><td id="typeidbox">
					   
				    </td>
				</tr>
				<tr><td class="lable">名称</td><td><input type="text" name="proname"/></td></tr>
				<tr><td class="lable">编号</td><td><input type="text" name="pronum"/></td></tr>
				<!-- <tr><td class="lable">价格</td><td><input type="text" name="price"/></td></tr> -->
				<tr><td class="lable">特色</td><td><input type="text" name="brief"/></td></tr>
				<tr><td class="lable">产地</td><td><input type="text" name="address" value="重庆"/></td></tr>
				<tr><td class="lable">图片</td><td><input type="file" name="file1"/></td></tr>
				<tr><td class="lable">内容</td><td><script id="editor" type="text/plain" style="width:1024px;height:300px;"></script>
				<input type="hidden" name="descriptions" id="descriptions"/></td></tr>
				<tr style="display:none;"><td class="lable">属性</td><td><input type="checkbox" name="istop" value="1" style="width:30px;"/>首页推荐<input type="checkbox" name="isstar" value="1" style="width:30px;"/>首页明星单品 </td></tr>
				<tr><td class="lable">&nbsp;</td><td><input type="submit" name="btnsave" value="保存" id="btnsave"/></td></tr>
			</table>
		</form>
		<script src="../js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
		 <script type="text/javascript">
		 		
		 	
 			var ue = UE.getEditor('editor');
 			$("#btnsave").click(function(e){
 				var str=UE.getEditor('editor').getContent();
 				console.log(str);
 				$("#descriptions").val(str);
 				
 			});
 			$(function(){
 				$.post("./../ajax?" + Math.random(), { rnum: "5" }, function (res) {
 	                $("#typeidbox").html(res.msg);
 	                /* ajax封装不同预览器的xmlhttprequest,
 	                ("./../ajax?" + Math.random(), { rnum: "5" }建立连接，{ rnum: "5" }传递data,success成功返回，回调函数。
 	                		$.post jq封装的方法
 	                		*/
 	                		/* 用意，所有ajax放入一个地方，相当于分布式获取后端ajax中的特定方法 */
 	                		/*./../路径缺什么，补什么。  */
 	                		/* Math.random()随机数避免缓存，直接请求会从缓存读取，为了最新数据，给个随机数，从不同的地方（如同？id=123）读取，其实还是指向同一个地方 */
 	            /*res形参，msg后端的数据  */
 				});
 	                
 	                
 				
 			});
 			
 		</script>
	</body>
</html>