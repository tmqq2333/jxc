<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<frameset cols="50%,50%" scrolling="Yes" frameborder="no" border="0" framespacing="0">
    <frame src="../userhasroleleft?userid=<%=request.getParameter("userid")%>" name="left" id="left" border="0" frameborder="no" noresize="noresize" scrolling="yes" style="padding-left:5px;padding-right:5px;"/>
    <frame src="../userhasroleright?userid=<%=request.getParameter("userid")%>" name="right" id="right" style="padding-left:5px;padding-right:5px;" />
</frameset>
</html>