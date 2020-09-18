<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/comm.css"/>
</head>
<body>

<p class="path">当前位置：夜鹰进销存管理系统/库存管理/商品<%=request.getParameter("t").equals("1")?"入库":"出库" %> </p>
<form action="../proinout" method="post">
<input type="hidden" name="t" value="<%=request.getParameter("t")%>"/>
<input type="hidden" name="tbproid" id="tbproid"/>
<table border="0" cellspacing="0" cellpadding="0" class="tb">
	<tr><td class="lable">商品编号</td><td><input type="text" name="tbpronum" id="tbpronum"/><input type="button" id="btnsearch" value="查询" style="width:50px;height:24px;"/> <span class="errormsg" id="errormsg"></span></td></tr>
	<tr><td class="lable">商品名称</td><td><input type="text" name="tbproname" id="tbproname" readonly="readonly"/></td></tr>
	<tr><td class="lable">商品类别</td><td><input type="text" name="tbpropath" id="tbpropath"/></td></tr>
	<tr><td class="lable">商品图片</td><td><img src="" id="proimg"/><input type="hidden" name="tbproimg" id="tbproimg" /></td></tr>
	<tr><td class="lable"><%=request.getParameter("t").equals("1")?"入库":"出库" %>前数量</td><td><input type="text" readonly="readonly" name="tbprooldcount" id="tbprooldcount"/></td></tr>
	<tr><td class="lable"><%=request.getParameter("t").equals("1")?"入库":"出库" %>数量</td><td><input type="text" class="blur" name="tbprocount" id="tbprocount"/></td></tr>
	<!-- <tr><td class="lable">单位</td><td><input type="text" name="tbprounit" /></td></tr> -->
	<tr><td class="lable">单价</td><td><input type="text" name="tbprice" class="blur" id="tbprice"/></td></tr>	
	<tr><td class="lable">金额</td><td ><span  id="tbzprice"></span></td></tr>
	<tr><td class="lable">&nbsp;</td><td><input type="submit" name="btnsave" value="保存" id="btnsave"/></td></tr>
</table>
</form>
<script src="../js/jquery-1.11.0.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">


       console.log($("#tbprooldcount").val())
	$("#btnsave").click(function(e){
		var proid=$("#tbproid").val();
		var price11=$("#tbprice").val();//取出当前行商品的数量，并且将数量转为数字 	
		var oldnum11=$("#tbprocount").val(); 
		if(!proid.length>0)
		{
			alert("请先输入商品编号点击查询按钮确认商品库中有该商品");
			$("#tbpronum").focus();
			e.preventDefault();
			return false;
		}
		if(!price11.length>0)
		{
			alert("请输入商品售价");
			$("#tbprice").focus();
			e.preventDefault();
			return false;
		}
		if(!oldnum11.length>0)
		{
			alert("请输入出库数量");
			$("#tbprocount").focus();
			e.preventDefault();
			return false;
		}
		
		var oldcount=$("#tbprooldcount").val();
		/* var currentcount=$("#tbprocount").val(); */ 
		oldcount=parseFloat(oldcount);
		/* currentcount=parseFloat(currentcount); */
		
		console.log(oldcount)
		if(oldnum11>oldcount)
		{
			alert("出库数量大于库存数量,请修改出库数量");
			
			$("#tbprocount").focus();
			e.preventDefault();
			return false;
		}
		
	});

	$("#btnsearch").click(function(){
		var pronum=$("#tbpronum").val();
		$.post("../ajax?" + Math.random(), {rnum:"7",pronum:pronum}, function (res) {
			/* console.log(res.id); */
         	if(res.id=="null")
         	{
         			$("#errormsg").text("商品库中没有该商品,请联系管理员!");
             		$("#tbproid").val("");
             		$("#tbproname").val("");	
             		$("#tbprooldcount").val("");	         		
             		$("#tbproimg").val("");	
             		$("#proimg").prop("src","");	
             		$("#tbpropath").val("");	
         	}
         	else
         	{
         		var imgpath="../upload/"+res.imgurl;
         		$("#errormsg").text("");
         		$("#tbproid").val(res.id);
         		$("#tbproname").val(res.proname);	
         		$("#tbprooldcount").val(res.procount);	         		
         		$("#tbproimg").val(res.imgurl);	
         		$("#proimg").prop("src",imgpath);	
         		$("#tbpropath").val(res.fullpath);	
         		/* console.log(res.procount) */
         	}
     	});	
	});
	
	$(".blur").blur(function(){
		
		//1、运算每一行的小计  = 单价*数量
		//2、对所有行的小计进行合计。
		var sum=0;
		/* each(function(位置,元素) */
		
	        
	    
			 var price1= parseFloat($("#tbprice").val());//取出当前行商品的数量，并且将数量转为数字
			 
			
			var oldnum1=parseFloat($("#tbprocount").val()); 
			sum=price1.toFixed(3) * oldnum1.toFixed(3);
		
			/* $(item).parent().next().find("span").text(xiaoji.toFixed(2)); */
			
			$("#tbzprice").text(sum.toFixed(2));
			
			
		});
		
	
 </script>

</body>
</html>