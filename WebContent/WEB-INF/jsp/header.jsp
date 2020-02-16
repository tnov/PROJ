<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<title><%=request.getParameter("title")%></title>
</head>
<body>
<header>
<div class="headerGroup">
	<div class="headerUser">
	<% if ((String)request.getSession().getAttribute("userId") != null) {
	%>
		<%=(String)request.getSession().getAttribute("userId")%>
	<% }
	%>

	</div>
	<div class="headerTitle">
		<%=request.getParameter("title")%>
	</div>
	<div class="headerDate">
		<%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())%>
	</div>
</div>
</header>

</body>
</html>
