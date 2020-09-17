<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String msg =(String)request.getAttribute("msg");
	if(msg==null)
	{
		msg="";
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>重庆工商大学融智学院实训项目-进销存管理系统V1.0</title>
	<link rel="stylesheet" type="text/css" href="../css/comm.css" />
	<style type="text/css">
		body {
				background-color: #2f2f35;
				font-family: 'Microsoft YaHei';
				font-size: 14px;
				background-repeat: no-repeat;
				background-position: 0 0;
			}
						
			#logincontainer {
				width: 662px;
				height: 332px;
				position: absolute;
				left:calc(50% - 331px);
				top:calc(50% - 166px);
				border: 0;
				border-radius: 3px;
				color: back;
				background-color:#535356;
				border: solid 6px #c0eaeefa;
				/* background-image: url(../img/loginbg.png); */
			}
			
			#logincontainer p {
				position: relative;
				height: 46px;
				line-height: 46px;
				clear: both;
				color:#f0f9ff ;
			}
			
			#logincontainer p span {
				display: block;
				float: left;
			}
			
			#logincontainer p span.lablespan {
				width: 80px;
				text-align: right;
				padding-right: 3px;
			}
			
			#logincontainer p span input {
				height: 28px;
				border: solid 1px #ff6600;
				padding-left: 5px;
				margin-left: 5px;
				background-color: white;
				outline: none;
				border-radius: 5px;
			}
			
			#logincontainer p span input#btnlogin {
				margin-left: 54%;
				width: 130px;
				height: 28px;
				border: 0;
				margin-top:30px;
				border-radius: 5px;
				background-color: #919ab4;
				color: white;
			}
			
			#msg {
				padding-left: 80px;
				color: red;
			}
			
			#btnsearch {
				color: white;
				text-decoration: none;
				position: absolute;
				right: 34px;
				font-size: 16px;
			}
			
			#msglist {
				width: 350px;
				height: 500px;
				border: solid 1px white;
				position: absolute;
				right: 50px;
				top: 50px;
				list-style: none;
				border-radius: 5px;
				padding: 10px;
				overflow: hidden;
			}
			
			#msglist li a {
				color: white;
				line-height: 35px;
				text-decoration: none;
				display: block;
				font-size: 16px;
				height: 35px;
				overflow: hidden;
			}
			
			#msglist li a:before {
				content: '>';
				margin-right: 5px;
			}
						
			.title{
				margin: 0 25px;
				margin-top:25px;
				font-size:26px;
				text-align: center;
				
			}
			
			.loginform{
				margin-left: calc(50% - 153px);
				
			}

	</style>
</head>
<body>
    <form  method="post" action="/myerp/login">
    	<div id="logincontainer">
			<p class="title">朗润建材进销存管理系统V1.0</p>
			<div class="loginform">
				<p id="msg" style="color:red; "><strong><%=msg%></strong></p>
				<p> <span class="lablespan">用户名:</span><span><input name="username" type="text" id="username" /></span></p>
				<p> <span class="lablespan">密    码:</span><span><input name="password" type="password" id="password" /></span> </p>
				<p><span><input type="submit" name="btnlogin" value="登 录" id="btnlogin" /></span></p>
			</div>
		</div>
    </form>
    <script src="../js/jquery-1.11.0.js"></script>
    <script>
        $("#btnlogin").click(function (e) {
            var username = $("#username");
            if (!username.val().length > 0)
            {
                $("#msg").text("请输入用户名");
                username.focus();
                e.preventDefault();
                return false;
            }

            var password = $("#password");
            if (!password.val().length > 0) {
                $("#msg").text("请输入密码");
                password.focus();
                e.preventDefault();
                return false;
            }
        });
    </script>
</body>
</html>
    