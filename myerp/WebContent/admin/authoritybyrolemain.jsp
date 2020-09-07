<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<frameset cols="50%,50%" scrolling="Yes" frameborder="no" border="0" framespacing="0">
    <frame src="../authoritybyroleleft?roleid=<%=request.getParameter("roleid")%>" name="left" id="left" border="0" frameborder="no" noresize="noresize" scrolling="yes" style="padding-left:5px;padding-right:5px;"/>
    <frame src="../authoritybyroleright?roleid=<%=request.getParameter("roleid")%>" name="right" id="right" style="padding-left:5px;padding-right:5px;" />
</frameset>
</html>