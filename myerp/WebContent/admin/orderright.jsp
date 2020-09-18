<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.util.regex.*" %>
<%
List<Map<String, Object>> carlistall = (List<Map<String, Object>>)request.getAttribute("carlistall");
Map<String, Object> obj=(Map<String,Object>)request.getSession().getAttribute("currentuser");	
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/comm.css"/>
		<style type="text/css">
			
			.pronumber{
				width:60px;
				height: 30px;
				border: solid 1px #ccc;
				position: relative;
			}
			
			 .pronumber span{
				display: block;
				width: 6px;
				height: 30px;
				line-height: 0px;
				font-size: 14px;
				color: #757575;
				float: left;
				cursor: pointer;/* 鼠标指针 */
				/* background-color:#eee; */
			} 
			
			 .pronumber input{
				width:60px;
				height:30px;
				outline: none;
				float: left;
				text-align: center;
				border: 0;
				border-left:solid 1px #ccc;
				border-right:solid 1px #ccc;
			}
			.innerclass{
				padding-left:3px;
				padding-top:20px;
			}
			.innerclass p{
				height:50px;
			} 
			.innerclass p input.forminput{
				border:solid 1px #ccc;
				height:26px;
				width:300px;
				margin-left:5px;
				padding-left:2px;
			}
			#receiverinfo{
				display:none;
			}
			span{
			cursor:default
			}
		</style>
	</head>
	<body>
		<p class="path">当前位置:/夜鹰进销存系统/进货管理/进货下单</p>
		<table border="0" cellspacing="0" cellpadding="0" class="tb tblist" id="cartable">
			<tr><td style="width: 150px;height: 30px;">商品图片</td><td>商品名称</td><td style="width:100px;">单价</td><td style="width:100px;">数量</td><td style="width: 100px;">小计</td><td style="width:50px;">操作</td></tr>
			<%for (Map<String, Object> m : carlistall) { %>
			<tr class="proidd" data-proid="<%=m.get("proid")%>"><td><img src="upload/<%=m.get("imgurl")%>" style="width:120px;height:38px;padding:5px 0; "></td><td><%=m.get("proname")%></td><td ><span><input type="text" class="proprice" style="width:46px;height:30px;margin:auto,0;	text-align: center; " value="<%=m.get("price")%>"/>元</span></td><td><div class="pronumber"><input type="text" class="procount"  value="<%=m.get("procount")%>"/><span style="	position: absolute;right:-20px;top:15px;">吨</span> </div>  </td><td><span></span>元</td><td><span class="del">删除</span></td></tr>
			<%}%>
		</table>
		
		<p id="carbuy" class="pager"> <span style="padding-left: 20px;color: #ff6700;font-size:18px;">合计 <b id="sum"></b>元 </span> <input type="button" id="btnorder" class="btn" value="去结算"/></p>
		<div id="receiverinfo" class="innerclass">
			<%if(obj!=null){%>
				 <p>姓名:<input class="forminput" type="text" name="tbname" id="tbname" value="<%=obj.get("truename")%>"/></p>
				<%--<p>电话:<input class="forminput" type="text" name="tbtel" id="tbTel" value="<%=obj.get("tel")%>"/></p>
				<p>地址:<input class="forminput" type="text" name="tbaddress" id="tbaddress" value="<%=obj.get("memo")%>" /></p>--%>
				<p> 
					<input type="button" id="btngotoorder" class="btn" value="确认下单"/>
				</p>
			<%}else{%>
				<p class="path">登录超时,请重新<a href="/login.jsp" target="_top">登录</a></p>
			<%}%>
		</div>	
		
		<!--购物车结束-->
		
		
		<script src="js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			//给id为 btnorder的元素绑定一个点击事件
			$("#btnorder").click(function(e){
				//点击id为 btnorder的按钮，就会执行此处的代码
				$("#receiverinfo").slideDown();
			});
			
			//给立即下单的按钮绑定一个点击事件
		$("#btngotoorder").click(function(e){
				//要去判断这三个文本框里面是否输入了内容  判断的标准：去获取每个文本框里面的值，看这个值的长度是否大于0，如果大于0，就表示输入了内容
				 if(!$("#tbname").val().length>0)//判断长度是否大于0，取反之后就数不大于0，即：没有输入内容。
				{
					alert("请输入收货人姓名!");
					$("#tbname").focus();//让一个表单元素获得焦点 
					return false;
				}
				
				/* if(!$("#tbTel").val().length>0)//判断长度是否大于0，取反之后就数不大于0，即：没有输入内容。
				{
					alert("请输入收货人联系电话!");
					$("#tbTel").focus();//让一个表单元素获得焦点 
					return false;
				}
				
				if(!$("#tbaddress").val().length>0)//判断长度是否大于0，取反之后就数不大于0，即：没有输入内容。
				{
					alert("请输入收货人详细地址!");
					$("#tbaddress").focus();//让一个表单元素获得焦点 
					return false;
				}  */
				
				//检查购物车里面是否又商品
				var len=$("#cartable").find("tr").length;
				if(len<2)
				{
					e.preventDefault();
					alert("请先在左侧选择商品");
					return false;
				}
				
				$.ajax({
				    url:'uiaddorder', //要请求的url地址
				    type:'POST', //请求方法 GET or POST
				    async:true, //是否使用异步请求的方式
				    timeout:5000, //请求超时的时间，以毫秒计
				    data:{
				    	tbname:$("#tbname").val(),
				     
				    },
				    dataType:'json', //预期的服务器返回参数类型
				    beforeSend:function(){
				        
				    },//再发送请求前调用的方法
				    success:function(data) {
				       location.href="./admin/result.jsp";
				    }, //请求成功时回调方法，data为服务器返回的数据
				    error:function(){
				       console.log("未能回调")
				    }, //请求发生错误时调用方法
				    complete:function(){
						jisuan();
				    } 
				});
				
			}); 
			
			
			//给所有的加号绑定点击事件
			/* $(".pronumber span.right").click(function(){
				//点击加号的时候，把当前加号按钮所在的行的商品数量取出来，加上1  然后再放回去。
				var oldvalue=$(this).prev().val();
				var newvalue=parseInt(oldvalue)+1;
				var that=this;
				var proid=$(that).parent().parent().parent().attr("data-proid");
				$.ajax({
				    url:'uiupdatecarcount', //要请求的url地址
				    type:'POST', //请求方法 GET or POST
				    async:true, //是否使用异步请求的方式
				    timeout:5000, //请求超时的时间，以毫秒计
				    data:{
				        id :proid,
				        countvalue:newvalue
				    },
				    dataType:'json', //预期的服务器返回参数类型
				    beforeSend:function(){
				        
				    },//再发送请求前调用的方法
				    success:function(data) {
				        console.log(data);
				    }, //请求成功时回调方法，data为服务器返回的数据
				    error:function(){
				       
				    }, //请求发生错误时调用方法
				    complete:function(){
				    	$(that).prev().val(newvalue);
						jisuan();
				    } //回调方法 无论success或者error都会执行
				});
				
			}); */
			//给所有的减号绑定点击事件
		/* 	$(".pronumber span.left").click(function(){
				//点击加号的时候，把当前加号按钮所在的行的商品数量取出来，加上1  然后再放回去。
				var oldvalue=$(this).next().val();
				var newvalue=parseInt(oldvalue)-1;
				if(newvalue<1)
				{
					newvalue=1;
				}
				
				var that=this;
				var proid=$(that).parent().parent().parent().attr("data-proid");
				$.ajax({
				    url:'uiupdatecarcount', //要请求的url地址
				    type:'POST', //请求方法 GET or POST
				    async:true, //是否使用异步请求的方式
				    timeout:5000, //请求超时的时间，以毫秒计
				    data:{
				        id :proid,
				        countvalue:newvalue
				    },
				    dataType:'json', //预期的服务器返回参数类型
				    beforeSend:function(){
				        
				    },//再发送请求前调用的方法
				    success:function(data) {
				        console.log(data);
				    }, //请求成功时回调方法，data为服务器返回的数据
				    error:function(){
				       
				    }, //请求发生错误时调用方法
				    complete:function(){
				    	$(that).prev().val(newvalue);
						jisuan();
				    } //回调方法 无论success或者error都会执行
				});
				
				
				$(this).next().val(newvalue);
				jisuan();
			}); */
			
			//点击计算.pronumber span.js
			$(".proidd input").blur(function(){
				//点击加号的时候，把当前加号按钮所在的行的商品数量取出来，加上1  然后再放回去。
			
		     	var oldvalue=$(this).parents("tr").find("input.procount").val();
				var oldprice=$(this).parents("tr").find("input.proprice").val();;//上一个兄弟节点的上一个的值
				var newvalue=parseFloat(oldvalue);
				var newprice=parseFloat(oldprice);//浮点数.attr("data-proid"); 
				var that=this;
				var proid=$(that).parents("tr").attr("data-proid");
				
				/*  $("#proidd").each(function(index,item){
						//把每一行的单价和数量取出来进行运算，并且把运算的结果交给小计
					
					 var proid=$(item).attr("data-proid");
						return proid
					}); */
					
				
				/*循环获取id  */
				//数据id，商品id
				//修改数量
				$.ajax({
				    url:'uiupdatecarcount', //要请求的url地址
				    type:'POST', //请求方法 GET or POST
				    async:true, //是否使用异步请求的方式
				    timeout:5000, //请求超时的时间，以毫秒计
				    data:{
				    	id :proid,
				    	newvalue:newvalue,
				        newprice:newprice
				    	
				    },
				 
				    dataType:'json', //预期的服务器返回参数类型
				    beforeSend:function(){
				        
				    },//再发送请求前调用的方法
				    success:function(data) {
				        console.log(data);
				    }, //请求成功时回调方法，data为服务器返回的数据
				    error:function(){
				    	console.log("数据未传输")
				    }, //请求发生错误时调用方法
				    complete:function(){
				  /*   	$(that).prev().val(newvalue);
				    	$(that).prev().prev().val(newprice); */
						jisuan();
				    } //回调方法 无论success或者error都会执行
				});
				
				
				//$(this).next().val(newvalue);//改变的值放入数量里，之前 newvalue=parseint(oldvalue)+1;值有变化
				/*val(newvalue)放入值  */
				jisuan();
			});
			
			//删除
			$("span.del").click(function(){
				var that=this;
				var proid=$(that).parent().parent().attr("data-proid");
				$.ajax({
				    url:'uicardelete', //要请求的url地址
				    type:'POST', //请求方法 GET or POST
				    async:true, //是否使用异步请求的方式
				    timeout:5000, //请求超时的时间，以毫秒计
				    data:{
				        id :proid
				    },
				    dataType:'json', //预期的服务器返回参数类型
				    beforeSend:function(){
				        
				    },//再发送请求前调用的方法
				    success:function(data) {
				        console.log(data);
				        $(that).parent().parent().remove();
				    }, //请求成功时回调方法，data为服务器返回的数据
				    error:function(){
				       
				    }, //请求发生错误时调用方法
				    complete:function(){
						jisuan();
				    } 
				});
			});
			
			
			
			
			function jisuan()
			{
				
				//1、运算每一行的小计  = 单价*数量
				//2、对所有行的小计进行合计。
				var sum=0;
				/* each(function(位置,元素) */
				$(".pronumber").each(function(index,item){
					//把每一行的单价和数量取出来进行运算，并且把运算的结果交给小计
					var num= parseFloat($(item).find("input").val());//取出当前行商品的数量，并且将数量转为数字
					var price=parseFloat($(item).parent().prev().find("input").val());//取出商品的单价
					var xiaoji=parseFloat(price*num);
					
					$(item).parent().next().find("span").text(xiaoji.toFixed(2));
					sum=sum+xiaoji;
				});
				$("#sum").text(sum.toFixed(2));
			}
			
			jisuan();
		</script>
		
	</body>
</html>
    