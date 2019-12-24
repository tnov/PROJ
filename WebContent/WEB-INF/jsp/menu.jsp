<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList" %>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/test.js" %>" ></script>
<title>メニュー</title>
</head>
<body>
<header></header>
<form method="POST" action="<%= request.getContextPath() + "/authorized/move" %>">
<h1>メニュー</h1>
<%
for (int i = 0 ; i < Integer.parseInt((String)request.getAttribute("menuCnt")) ; i++) {
	ArrayList<String> list = (ArrayList<String>)request.getAttribute("functionPath");
%>
<div class="link" >
	<%= request.getAttribute("functionId") %>
	<input type="hidden" name="functionPath" value="<%= request.getContextPath() + list.get(i) %>"/>
</div>
<%
}
%>
</form>
<footer></footer>
</body>
</html>
