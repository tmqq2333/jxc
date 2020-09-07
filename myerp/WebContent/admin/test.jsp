<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../ueditor/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="../ueditor/ueditor.all.js" type="text/javascript" charset="utf-8"></script>
<script src="../ueditor/lang/zh-cn/zh-cn.js" type="text/javascript" charset="utf-8" ></script>
</head>
<body>

 <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
 <input type="button" value="提交"/>
 
 <script type="text/javascript">
 var ue = UE.getEditor('editor');
 </script>

</body>
</html>